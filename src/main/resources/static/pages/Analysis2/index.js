var id = '';
var option = {};
var answerList = [];
var questionList = [];
var resultList = [];
var title = '';
var deadline ='';
var optionTitle = [];
onload = () => {
    let u = new URL(document.URL);
    // 创建一个对象用来保存参数
    let obj = {};
    // 对参数进行遍历
    for(let [k,v] of u.searchParams.entries()){
        obj[k] = v;
    }
    // 输出信息
    console.log(obj);
    console.log(obj.id);

    if(obj.id == "" || obj.id == null){
        alert("找不到对应的问卷，请检查链接是否正确")
        alert("页面即将自动关闭")
        window.opener = null;
        window.open('', '_self');
        window.close();
    }
    let params = {
        id: obj.id,//
    }
    $.ajax({
        url: API_BASE_URL + '/queryQuestionnaireList',
        type: 'POST',
        data: JSON.stringify(params),
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            var questionnaireRES = res.data[0]
            let params2 ={
                questionnaireId: obj.id
            }
            $.ajax({
                url: API_BASE_URL + '/queryQuestionList',
                type: 'POST',
                data: JSON.stringify(params2),
                dataType: 'json',
                contentType: 'application/json',
                success(res) {
                    Show(questionnaireRES, JSON.parse(res.problem))
                }
            })
        }
    })
}


function getOptionTitle(type) {
    if(type == "0")optionTitle.push("单选题");
    else if(type == "1")optionTitle.push("多选题");
    else if(type == "2")optionTitle.push("填空题");
    else if(type == "3")optionTitle.push("矩阵题");
    else if(type == "4")optionTitle.push("量话题");
}

function Show(questionnaire,resultList) {
    console.log("走这个函数了吗")
    console.log("resultList")
    console.log(resultList)
    //添加问卷标题
    let h1 ='<h1 class="header">' +questionnaire.questionName + '</h1>';
    $("#results").append(h1);
    let h2 ='<h5 class="header">' +questionnaire.questionContent + '</h5>';
    $("#results").append(h2);
    //resultList = [{"mustAnswer":true,"option":[{"chooseTerm":"开心","countt":17},{"chooseTerm":"不开心","countt":0}],"problemName":"实训开心吗","problemType":1},{"mustAnswer":false,"option":[{"chooseTerm":"好玩不了一点","countt":0},{"chooseTerm":"一点不好玩","countt":19}],"problemName":"实训好玩吗","problemType":2},{"mustAnswer":true,"problemName":"填空题目","problemType":3},{"juzhen":[[10,0,0],[1,9]],"leftTitle":"左侧1,左侧2","mustAnswer":true,"option":[{"chooseTerm":"矩阵选项1","countt":0},{"chooseTerm":"矩阵选项2","countt":0},{"chooseTerm":"矩阵选项3","countt":0}],"problemName":"矩阵题目","problemType":4},{"mustAnswer":true,"option":[{"chooseTerm":"文字1","countt":4,"fraction":"分数1"},{"chooseTerm":"文字2","countt":13,"fraction":"分数2"}],"problemName":"量表问题","problemType":5}]


    for(var i = 0; i < resultList.length; i++){
        setHtml(i, resultList[i]);
    };
    for(var i = 0; i < resultList.length; i++){
        drawChart(i, resultList[i]);
    };
    // 添加问卷截止时间提示
    let p_time ='<p class="ui yellow message">问卷截止时间' + questionnaire.stopTime + '</h1>';
    $("#results").append(p_time);
}

function setHtml(index, question) {
    // console.log("bug")

    var numId = index + 1;
    var statistic_div =  '<div class="ui message"></div>';
    if(question.problemType == "1"){
        question["problemTitle"] = "单选题"
    }
    else if(question.problemType == "2"){
        question["problemTitle"] = "多选题"
    }
    else if(question.problemType == "3"){
        question["problemTitle"] = "填空题"
    }
    else if(question.problemType == "4"){
        question["problemTitle"] = "矩阵题"
    }
    else if(question.problemType == "5"){
        question["problemTitle"] = "量表题"
    }
    if(question.problemType == "4"){
        var h3 ='<h3 class="header"> '+numId+ '.'+ question.problemTitle+': '+question.problemName + '</h3>';
        statistic_div = $(statistic_div).append(h3);
        var div_chart = '<div class="chart" style="width: 400px;height:10px;"></div>';
        // var div_chart = '<div class="chart' + index + '"></div>';
        statistic_div = $(statistic_div).append(div_chart);
        $("#results").append(statistic_div);

        console.log(question.leftTitle)
        let s = question.leftTitle.split(",")//左侧
        for(let i=0;i<question.juzhen.length;i++){
            let k = i +1
            let h4 ='<h5 class="header"> '+' 第 '+ k +' 项 : '+s[i] + '</h5>';
            statistic_div = $(statistic_div).append(h4);
            let div_chart2 = '<div class="chart2" style="width: 400px;height:200px;"></div>';
            // var div_chart = '<div class="chart' + index + '"></div>';
            statistic_div = $(statistic_div).append(div_chart2);
            $("#results").append(statistic_div);
        }
    }
    else if(question.problemType == "3"){
        var h3 ='<h3 class="header"> '+numId+ '.'+ question.problemTitle+': '+question.problemName + '</h3>';
        statistic_div = $(statistic_div).append(h3);
        var div_chart = '<div class="chart" style="width: 400px;height:50px;"><h5>填空题暂不统计</h5></div>';
        // var div_chart = '<div class="chart' + index + '"></div>';
        statistic_div = $(statistic_div).append(div_chart);
        $("#results").append(statistic_div);
    }
    else{
        var h3 ='<h3 class="header"> '+numId+ '.'+ question.problemTitle+': '+question.problemName + '</h3>';
        statistic_div = $(statistic_div).append(h3);
        var div_chart = '<div class="chart" style="width: 400px;height:200px;"></div>';
        // var div_chart = '<div class="chart' + index + '"></div>';
        statistic_div = $(statistic_div).append(div_chart);
        $("#results").append(statistic_div);
    }
}

function drawChart(index, question) {
    console.log("drawChart1")
    if(question.problemType == "1"||question.problemType == "5"){  //单选或则量表
        console.log("drawChart2")
        var res = [];
        let option_temp = []
        for (var i = 0; i < question.option.length; i++) {
            res.push({value:question.option[i].countt, name:question.option[i].chooseTerm});
            option_temp.push(question.option[i].chooseTerm)
        }
        var option = {
            grid: {
                top: '10%', // 调整为合适的上边距值
                bottom: '10%', // 调整为合适的下边距值
                // 其他布局参数...
            },
            legend: {
                orient : 'vertical',
                x : 'left',
                data: option_temp
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            calculable : true,
            series : [
                {
                    name:'单选统计',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data: res
                }
            ]
        };
        var myChart = echarts.init($(".chart")[index]);
        myChart.setOption(option);
    }else if(question.problemType == "2"){//多选或则矩阵 ||question.type == "4"
        let option_temp = []
        let countt_temp = []
        for (var i = 0; i < question.option.length; i++) {
            option_temp.push(question.option[i].chooseTerm)
            countt_temp.push(question.option[i].countt)
        }
        option = {
            grid: {
                top: '10%', // 调整为合适的上边距值
                bottom: '10%', // 调整为合适的下边距值
                // 其他布局参数...
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['选择次数']
            },
            calculable : true,
            xAxis : [
                {
                    type : 'value',
                    boundaryGap : [0, 0.01]
                }
            ],
            yAxis : [
                {
                    type : 'category',
                    data: option_temp
                }
            ],
            series: [{
                name: '选择次数',
                type: 'bar',
                data: countt_temp
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        var myChart = echarts.init($(".chart")[index]);
        myChart.setOption(option);
    }
    else if(question.problemType == "4"){//多选或则矩阵 ||question.type == "4"
        for(let j=0;j<question.juzhen.length;j++){
            let option_temp = []
            let countt_temp = []
            for (var i = 0; i < question.option.length; i++) {
                option_temp.push(question.option[i].chooseTerm)
                countt_temp.push(question.juzhen[j][i])
            }
            option = {
                grid: {
                    top: '10%', // 调整为合适的上边距值
                    bottom: '10%', // 调整为合适的下边距值
                    // 其他布局参数...
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['选择次数']
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'value',
                        boundaryGap : [0, 0.01]
                    }
                ],
                yAxis : [
                    {
                        type : 'category',
                        data: option_temp
                    }
                ],
                series: [{
                    name: '选择次数',
                    type: 'bar',
                    data: countt_temp
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            var myChart = echarts.init($(".chart2")[j]);
            myChart.setOption(option);
        }
    }
    else if(question.problemType == "3"){ // 填空题
        // var myChart = $(".chart")[index];
        // for(var i = 0; i < question.answers.length; i++){
        //     var text = "<div class='ui green message'>回答" + (i+1) + ":   " + question.answers[i] + "</div>";
        //     myChart.innerHTML += text;
        // }
        // $(".chart:eq("+ index + ")").css("height","auto");
    }
}