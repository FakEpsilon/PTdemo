onload = () => {
  $('#headerUsername').text($util.getItem('userInfo')[0].username)
  $('#headerDivB').text('创建项目')
}

let addZero=(s) =>{
  return s < 10 ? ('0' + s) : s;
}
let getNowTime=() => {
  let date = new Date();
//年 getFullYear()：四位数字返回年份
  let year = date.getFullYear(); //getFullYear()代替getYear()
//月 getMonth()：0 ~ 11
  let month = date.getMonth() + 1;
//日 getDate()：(1 ~ 31)
  let day = date.getDate();
//时 getHours()：(0 ~ 23)
  let hour = date.getHours();
//分 getMinutes()： (0 ~ 59)
  let minute = date.getMinutes();
//秒 getSeconds()：(0 ~ 59)
  let second = date.getSeconds();
  let time = year + '-' + addZero(month) + '-' + addZero(day) + 'T' + addZero(hour) + ':' + addZero(minute) + ':' + addZero(second) + '.000+00:00';
  return time
}
console.log(getNowTime())

const handleCreateProject = () => {
  let params = {
    userId: $util.getItem('userInfo')[0].id,
    createdBy: $util.getItem('userInfo')[0].username,
    lastUpdatedBy: $util.getItem('userInfo')[0].username,
    projectName: $('#projectName').val(),
    projectContent: $('#projectDescribe').val(),
    creationDate: getNowTime(),
    lastUpdateDate: getNowTime(),
  }
  if (!params.projectName||params.projectName.length>"50") return alert('项目名称不能为空或太长！')
  if (!params.projectContent) return alert('项目描述不能为空！')
  $.ajax({
    url: API_BASE_URL + '/addProjectInfo',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      alert('创建成功！')
      console.log(res.data[0])
      location.href = "/pages/questionnaire/index.html"
    }
  })
}