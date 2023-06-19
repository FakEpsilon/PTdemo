const handleConfirmModify = () => {
  let idNameDescription = JSON.parse(decodeURIComponent($util.getPageParam('designQuestionnaire')))
  let questionId = idNameDescription.quesId
  $('#modifyTitleModal').modal('hide')
  $('.questionnaire-title > span').text($('#questionnaireTitleModify').val())
  $('.questionnaire-description > span').text($('#questionnaireDescriptionModify').val())
  let params={
    id: questionId,
    questionnaireName: $('#questionnaireTitleModify').val(),
    questionnaireDescription: $('#questionnaireDescriptionModify').val(),
    lastUpdateDate: getNowTime(),
  }
  if (!params.questionnaireName) return  alert('问卷名称不能为空！')
  if (!params.questionnaireDescription) return  alert('问卷描述不能为空！')
  $.ajax({
    url: 'http://127.0.0.1:8085/modifyQuestionnaireInfo',
    type: 'POST',
    data: JSON.stringify(params),
    dataType: 'json',
    contentType: 'application/json',
    success(res){
      console.log("走到着了吗\n" + params.questionnaireName + params.questionnaireDescription)
      console.log(res)
      if(res.code === "666")
        alert("修改成功")
      else{
        alert("修改失败")
      }
    }
  })
}

const onQuestionnaireTitleInput = (e) => {
  questionnaireTitle = e.value
}

const onQuestionnaireDescriptionInput = (e) => {
  questionnaireDescription = e.value
}
