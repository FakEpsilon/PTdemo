onload = () => {
  $('#headerDivB').text('项目详情')

  let projectId = $util.getPageParam('seeProject')
  // console.log(projectId, 'projectId')
  fetchProjectInfo(projectId)
}

const fetchProjectInfo = (id) => {
  let params = {
    id:id
  }
  // console.log(id + "seeprojectID")
  $.ajax({
    url: API_BASE_URL + '/selectProjectById',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      let info = res.data[0]
      // console.log(info.creationDate)
      $('#projectName').text(info.projectName)
      $('#createTime').text(info.creationDate)
      $('#personInCharge').text(info.createdBy)
      $('#projectDescription').text(info.projectContent)
    }
  })

//   再查问卷
  let paramss = {
    projectId: id
  }
  /*根据项目ID查询所对应问卷flag标志知否以及发布*/
  $.ajax({
    url: API_BASE_URL + '/selectQuestionnaireById',
    type: "POST",
    data: JSON.stringify(paramss),
    dataType: "json",
    contentType: "application/json",
    success(res){
      if(res.data === null){
        return
      }
      console.log(res.data)
      res.data.map(item=>{
        let index = 1
        if(item.flag === "1"){
          $('#content').append(`
          <tr>
            <th>${index++}</th>
            <th>${item.questionnaireName}</th>
            <th>${item.issueDate.replace('T', ' ').substring(0,19)}</th>
            <th>
                <button type="button" style="font-size: 16px" class="btn btn-link" onclick="onIssue('${item.id}')">发布</button>
                <button type="button" style="font-size: 16px" class="btn btn-link" onclick="onClose('${item.id}','${item.flag}')">关闭</button>
                <button type="button" style="font-size: 16px" class="btn btn-link" onclick="onLink('${item.id}','${item.questionnaireName}','${item.questionnaireDescription}','${item.flag}')">链接</button>
                <button type="button" style="font-size: 16px" class="btn btn-link" >统计</button></div>
            </th>
          </tr>
          `)
        }else{
          $('#content').append(`
          <tr>
            <th>${index++}</th>
            <th>${item.questionnaireName}</th>
            <th>暂未发布</th>
            <th>
              <button type="button" style="font-size: 16px" class="btn btn-link" onclick="onIssue('${item.id}')">发布</button>
              <button type="button" style="font-size: 16px" class="btn btn-link" onclick="onClose('${item.id}','${item.flag}')">关闭</button>
              <button type="button" style="font-size: 16px" class="btn btn-link" onclick="onLink('${item.id}','${item.questionnaireName}','${item.questionnaireDescription}', '${item.flag}')">链接</button>
              <button type="button" style="font-size: 16px" class="btn btn-link" onclick="">统计</button>
            </th>
          </tr>
          `)
        }
      })
    }
  })
}

const onIssue = (id) => {
  let params = {
    id: id
  }
  /*根据问卷ID查询该问卷flag*/
  $.ajax({
    url: API_BASE_URL + '/selectQuestionnaireInfo',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res){
      console.log(res.data)
      if(res.data[0].flag === "1"){
        console.log()
        alert("已经发布，请点击链接查看！")
      }else{
        issueQues(params)
      }
    },
    error(res){
      alert(res.message)
    }
  })
  function issueQues(paramss) {
    let param = {
      id: paramss.id,
      issueDate: getNowTime()
    }
    /*根据问卷ID修改该问卷flag为1，表示发布*/
    $.ajax({
      url: API_BASE_URL + '/issueQuestionnaireById',
      type: "POST",
      data: JSON.stringify(param),
      dataType: "json",
      contentType: "application/json",
      success(res){
        console.log(param)
        $('#content').find('tr').slice(1).remove()
        alert("成功发布！")
        let projectId = $util.getPageParam('seeProject')
        // console.log(projectId, 'projectId')
        fetchProjectInfo(projectId)
      },
      error(res){
        alert(res.message)
      }
    })
  }
}

const onLink = (id, name, description, flag) => {/*这个it是问卷对象*///这个传参这特么神奇
  if(flag !== "1"){
    alert("这个问卷似乎还没有发布哦！")
    return
  }
  $.ajax({
    url: API_BASE_URL + '/answerSheet',
    type: "GET",
    data: {id: id},
    success(res){
      let it = []
      it.push(name)
      it.push(description)
      console.log(it)
      $util.setPageParam('answerSheetQuestions', res.data)//送问题列表
      $util.setPageParam('answerSheetQuestionnaire', it)//送问卷信息
      location.href = `../answerSheet/index.html?id=${id}`
    }
  })
}

const onClose = (id, flag) => {
  if(flag !== "1"){
    alert("这个问卷似乎还没有发布哦，只能关闭已发布的呢！")
    return
  }
  $.ajax({
    url: API_BASE_URL + '/closeQuestionnaireById',
    type: "POST",
    data: JSON.stringify({id: id}),
    dataType: "json",
    contentType: "application/json",
    success(){
      $('#content').find('tr').slice(1).remove()
      alert("成功关闭！")
      let projectId = $util.getPageParam('seeProject')
      fetchProjectInfo(projectId)
    },
    error(res){
      alert(res.message)
    }
  })
}