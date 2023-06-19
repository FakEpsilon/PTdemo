onload = () => {
  $('#headerUsername').text($util.getItem('userInfo')[0].username)
  $('#headerDivB').text('创建调查问卷')

  $('#startTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
  $('#endTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
}

const handleCreateQuestionnaire = () => {
  let paramQues = JSON.parse(decodeURIComponent($util.getPageParam("createNewQuestionnaire")))//收集关于项目以及面向对象两个信息的对象字典
  // console.log(paramQues.projectIdd + "@@@" + paramQues.oriented)
  let params = {
    questionnaireName: $('#surveyName').val(),
    questionnaireDescription: $('#surveyDescription').val(),
    startTime: $('#start').val() && new Date($('#start').val()).getTime(),
    stopTime: $('#end').val() && new Date($('#end').val()).getTime(),
    // $('#startTime').val() &&
    projectId: paramQues.projectIdd,
    orientedWho: paramQues.oriented,
    userId: $util.getItem('userInfo')[0].id,
    createdBy: $util.getItem('userInfo')[0].username,
    lastUpdatedBy: $util.getItem('userInfo')[0].username,
    creationDate: getNowTime(),
    lastUpdateDate: getNowTime(),
  }
  let idNameDescription = {}
  // console.log($('#startTime').val())
  if (!params.questionnaireName) return alert('问卷名称不能为空！')
  if (!params.questionnaireDescription) return alert('问卷描述不能为空！')
  if (!params.startTime) return alert('开始时间不能为空！')
  if (!params.stopTime) return alert('结束时间不能为空！')
  $.ajax({
    url: API_BASE_URL + '/addQuestionnaireInfo',
    type: 'POST',
    data: JSON.stringify(params),
    dataType: 'json',
    contentType: 'application/json',
    success(res) {
      console.log(res)
      if (res.code === "666") {
        alert(res.message)
        idNameDescription["quesId"] = res.id
        idNameDescription["name"] = params.questionnaireName
        idNameDescription["description"] = params.questionnaireDescription
        $util.setPageParam('designQuestionnaire', encodeURIComponent(JSON.stringify(idNameDescription)))
        location.href = '/pages/designQuestionnaire/index.html'
      } else {
        alert(res.message)
      }
    }
  })
}