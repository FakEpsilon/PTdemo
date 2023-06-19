let omit = false
let answer
let isSelected = false
let desList = []
onload = () => {
  let problemList = $util.getPageParam('answerSheetQuestions')
  let questionnaire = $util.getPageParam('answerSheetQuestionnaire')
  console.log(problemList)
  $('.questionnaire-title').text(questionnaire[0])
  $('.questionnaire-description').text(questionnaire[1])
  problemList.map((item, index)=>{
    if(item.questionMa === "1"){
    }
    switch (item.questionType){
      case "1":addSingleQuestion(item, index)//传递问题实体和索引
            break
      case "2":addMultipleQuestion(item, index)
            break
      case "3":addFillQuestion(item, index)
            break
      case "4":addMatrixQuestion(item, index)
            break
      case "5":addGaugeQuestion(item, index)
            break
      default:break
    }
  })
}

const addSingleQuestion = (problem, problemIndex) => {
  $('#problem').append(`
  <div class="question" id="question${problemIndex}" data-type="1" data-problemIndex="${problemIndex}">
      <div class="top">
        <span class="question-title" id="questionTitle">1.请编辑问题？</span>
        <span class="must-answer" id="mustAnswer">?答题</span>
      </div>
      <div class="bottom2"></div>    
  </div>
  `)
  if(problem.questionMa === "1"){
    $(`#question${problemIndex} #mustAnswer`).text('必答题')
  }else{
    $(`#question${problemIndex} #mustAnswer`).text('非必答题')
  }
  $(`#question${problemIndex} #questionTitle`).text(`${problemIndex + 1}.${problem.questionName}`)//题号和题目
  $(`#question${problemIndex} .bottom2`).html('')//先对HTML清零

  let params = {
    questionId: problem.id
  }
  $.ajax({
    url: API_BASE_URL + '/queryOptionById',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      res.data.map(item => {//遍历选项
        // console.log(problemIndex)
        $(`#question${problemIndex} .bottom2`).append(`
      <div style="display: flex; align-items: center;">
        <label class="radio-inline">
          <input type="radio" name="question${problemIndex}">${item.optionName ? item.optionName : ''}
        </label>
      </div>
    `)
      })
    },
    error(res) {
      // console.log(res)
    }
  })

}

const addMultipleQuestion = (problem, problemIndex) => {
  $('#problem').append(`
  <div class="question" id="question${problemIndex}" data-type="1" data-problemIndex="${problemIndex}">
      <div class="top">
        <span class="question-title" id="questionTitle">1.请编辑问题？</span>
        <span class="must-answer" id="mustAnswer">?答题</span>
      </div>
      <div class="bottom2"></div>    
  </div>
  `)
  if(problem.questionMa === "1"){
    $(`#question${problemIndex} #mustAnswer`).text('必答题')
  }else{
    $(`#question${problemIndex} #mustAnswer`).text('非必答题')
  }
  $(`#question${problemIndex} #questionTitle`).text(`${problemIndex + 1}.${problem.questionName}`)//题号和题目
  $(`#question${problemIndex} .bottom2`).html('')//先对HTML清零

  let params = {
    questionId: problem.id
  }
  $.ajax({
    url: API_BASE_URL + '/queryOptionById',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      res.data.map(item => {//遍历选项
        $(`#question${problemIndex} .bottom2`).append(`
      <div style="display: flex; align-items: center;">
        <label class="checkbox-inline">
          <input type="checkbox" data-question-id="question${problemIndex}">${item.optionName ? item.optionName : ''}
        </label>
      </div>
    `)
      })
    },
    error(res) {
      // console.log(res)
    }
  })
}

const addFillQuestion = (problem, problemIndex) => {
  $('#problem').append(`
  <div class="question" id="question${problemIndex}" data-type="1" data-problemIndex="${problemIndex}">
      <div class="top">
        <span class="question-title" id="questionTitle">1.请编辑问题？</span>
        <span class="must-answer" id="mustAnswer">?答题</span>
      </div>
      <div class="bottom2"></div>    
  </div>
  `)
  if(problem.questionMa === "1"){
    $(`#question${problemIndex} #mustAnswer`).text('必答题')
  }else{
    $(`#question${problemIndex} #mustAnswer`).text('非必答题')
  }
  $(`#question${problemIndex} #questionTitle`).text(`${problemIndex + 1}.${problem.questionName}`)//题号和题目
  $(`#question${problemIndex} .bottom2`).html(`
    <textarea class="question${problemIndex}" style="border: 1px solid #CCCCCC; width: 80%; height: 100px;" ></textarea>
  `)
}

const addMatrixQuestion = (problem, problemIndex) => {
  $('#problem').append(`
  <div class="question" id="question${problemIndex}" data-type="1" data-problemIndex="${problemIndex}">
      <div class="top">
        <span class="question-title" id="questionTitle">1.请编辑问题？</span>
        <span class="must-answer" id="mustAnswer">?答题</span>
      </div>
      <div class="bottom2"></div>    
  </div>
  `)
  if(problem.questionMa === "1"){
    $(`#question${problemIndex} #mustAnswer`).text('必答题')
  }else{
    $(`#question${problemIndex} #mustAnswer`).text('非必答题')
  }
  $(`#question${problemIndex} #questionTitle`).text(`${problemIndex + 1}.${problem.questionName}`)//题号和题目
  $(`#question${problemIndex} .bottom2`).html('')//先对HTML清零

  let trs = problem.questionDescription ? problem.questionDescription.split(',') : []
  $(`#question${problemIndex} .bottom2`).append(`
    <table class="table" id="table${problemIndex}">
      <thead>
        <tr>
          <th></th>
        </tr>
      </thead>
      <tbody>
        
      </tbody>
    </table>
  `)
  trs.map((item, index) => {
    $(`#question${problemIndex} .bottom2 tbody`).append(`
      <tr class="tr${problemIndex}">
        <td>${item}</td>
      </tr>
    `)

    $.ajax({
      url: API_BASE_URL + '/queryOptionById',
      type: "POST",
      data: JSON.stringify({questionId: problem.id}),
      dataType: "json",
      contentType: "application/json",
      success(res) {
        res.data.map(() => {//遍历选项
          $(`#question${problemIndex} .bottom2 tbody .tr${problemIndex}`).append(`
        <td>
          <input type="radio" name="radio${problemIndex}">
        </td>
      `)
        })
      }
    })
  })///////////////////////////////////////////////////////////////////
  $.ajax({
    url: API_BASE_URL + '/queryOptionById',
    type: "POST",
    data: JSON.stringify({questionId: problem.id}),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      res.data.map(item => {
        $(`#question${problemIndex} .bottom2 thead tr`).append(`
      <th>${item.optionName}</th>
    `)
      })
    }
  })
}

const addGaugeQuestion = (problem, problemIndex) => {
  $('#problem').append(`
  <div class="question" id="question${problemIndex}" data-type="1" data-problemIndex="${problemIndex}">
      <div class="top">
        <span class="question-title" id="questionTitle">1.请编辑问题？</span>
        <span class="must-answer" id="mustAnswer">?答题</span>
      </div>
      <div class="bottom2" style="display: flex; justify-content: space-between"></div>    
  </div>
  `)
  if(problem.questionMa === "1"){
    $(`#question${problemIndex} #mustAnswer`).text('必答题')
  }else{
    $(`#question${problemIndex} #mustAnswer`).text('非必答题')
  }
  $(`#question${problemIndex} #questionTitle`).text(`${problemIndex + 1}.${problem.questionName}`)//题号和题目
  $(`#question${problemIndex} .bottom2`).html('')//先对HTML清零



  $.ajax({
    url: API_BASE_URL + '/queryOptionById',
    type: "POST",
    data: JSON.stringify({questionId: problem.id}),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      $(`#question${problemIndex} .bottom2`).append(`
      <div>${res.data[0].optionName}</div>
      `)
      res.data.map(item => {
        $(`#question${problemIndex} .bottom2`).append(`
      <div>
        <label class="radio-inline">
          <input type="radio" name="fraction${problemIndex}" />${item.optionFraction}
        </label>
      </div>
    `)
      })
      $(`#question${problemIndex} .bottom2`).append(`
      <div>${res.data[res.data.length - 1].optionName}</div>
      `)
    }
  })
}

const onSubmit = () => {
  let problemList = $util.getPageParam('answerSheetQuestions')
  problemList.map((item, index)=>{
    switch (item.questionType){
      case "1":singleChecked(item, index)//传递问题实体和索引
        break
      case "2":multipleChecked(item, index)
        break
      case "3":fillChecked(item, index)
        break
      case "4":matrixChecked(item, index)
        break
      case "5":gaugeChecked(item, index)
        break
      default:break
    }
    if(index === problemList.length-1 && omit === false){
      insertDb(problemList, desList)
      alert("成功提交，谢谢合作！")
      location.href = '../seeProject/index.html'
      return
    }
    if(index === problemList.length-1 && omit === true){
      alert("您有必填项未填，请检查！")
      omit = false
      desList = []
    }
  })
  // if(isFullCpr === 5&& isFull === 0){
  //   alert("成功提交，谢谢合作！")
  //   location.href = '../seeProject/index.html'
  // }
}

const singleChecked = (problem, problemIndex) => {
  let singleChoice = document.getElementsByName(`question${problemIndex}`)
  isSelected = false
  for (let i = 0; i < singleChoice.length; i++){
    let radioButton = singleChoice[i]
    if (radioButton.checked){
      desList.push(radioButton.nextSibling.textContent.trim())
      isSelected = true
    }
  }
  console.log("isSelect:" + isSelected)
  if(!isSelected && problem.questionMa === "1"){/*必答为真且没有一个选中说明有遗漏，omit置TRUE*/
    omit = true
  }
}

const multipleChecked = (problem, problemIndex) => {
  let checkboxes = document.querySelectorAll(`input[type="checkbox"][data-question-id="question${problemIndex}"]`);
  let isSelected = false
  // 遍历复选框列表
  let checkboxList = []
  checkboxes.forEach((item) =>{
    if (item.checked) {
      let optionText = item.nextSibling.textContent.trim();
      isSelected = true
      checkboxList.push(optionText)
    }
  })
  desList.push(checkboxList)
  console.log("isSelect:" + isSelected)
  if(!isSelected && problem.questionMa === "1"){/*必答为真且没有一个选中说明有遗漏，omit置TRUE*/
    omit = true
  }
}

const fillChecked = (problem, problemIndex) => {
  let isSelected = false
  // let textarea = document.getElementById(`question${problemIndex}`)
  let textContent = $(`.question${problemIndex}`).val()
  if(textContent !== ""){
    isSelected = true
  }
  desList.push(textContent)
  console.log("isSelect:" + isSelected)
  if(!isSelected && problem.questionMa === "1"){/*必答为真且没有一个选中说明有遗漏，omit置TRUE*/
    omit = true
  }
}

const matrixChecked = (problem, problemIndex) => {
  let singleChoice = document.getElementsByName(`radio${problemIndex}`)
  let isSelected = false
  for (let i = 0; i < singleChoice.length; i++){
    let radioButton = singleChoice[i]
    if (radioButton.checked){
      // answer = radioButton.nextSibling.textContent.trim()
      isSelected = true
      thContent(i)
    }
  }
  function thContent(radioIndex){
    let table = document.querySelector(`#table${problemIndex}`).querySelectorAll("thead th");
    table.forEach((item,index) => {
      if(radioIndex === index-1){
        // console.log(radioIndex)
        answer = item.textContent
        isSelected = true
      }
    })
  }
  desList.push(answer)
  console.log("isSelect:" + isSelected)
  if(!isSelected && problem.questionMa === "1"){/*必答为真且没有一个选中说明有遗漏，omit置TRUE*/
    omit = true
  }
}

const gaugeChecked = (problem, problemIndex) => {
  let singleChoice = document.getElementsByName(`fraction${problemIndex}`)
  let isSelected = false
  for (let i = 0; i < singleChoice.length; i++){
    let radioButton = singleChoice[i]
    if (radioButton.checked){
      answer = radioButton.nextSibling.textContent.trim()
      isSelected = true
    }
  }
  desList.push(answer)
  console.log("isSelect:" + isSelected)
  if(!isSelected && problem.questionMa === "1"){/*必答为真且没有一个选中说明有遗漏，omit置TRUE*/
    omit = true
  }
}

const addSingleAnswer = (problem, des) => {
  $.ajax({
    url: API_BASE_URL + '/addAnswerInfo',
    type: "POST",
    data: JSON.stringify({questionId: problem.id, singleOption: des}),
    dataType: "json",
    contentType: "application/json",
    success(res){
      // console.log(res.message)
    }
  })
}

const addMultipleAnswer = (problem, des) => {//只有此处的des是个数组
  des.map(item => {
    $.ajax({
      url: API_BASE_URL + '/addAnswerInfo',
      type: "POST",
      data: JSON.stringify({questionId: problem.id, multipleOption: item}),
      dataType: "json",
      contentType: "application/json",
      success(res){
        // console.log(res.message)
      }
    })
  })

}

const addFillAnswer = (problem, des) => {
  $.ajax({
    url: API_BASE_URL + '/addAnswerInfo',
    type: "POST",
    data: JSON.stringify({questionId: problem.id, fillOption: des}),
    dataType: "json",
    contentType: "application/json",
    success(res){
      // console.log(res.message)
    }
  })
}

const addMatrixAnswer = (problem, des) => {
  $.ajax({
    url: API_BASE_URL + '/addAnswerInfo',
    type: "POST",
    data: JSON.stringify({questionId: problem.id, singleOption: des}),
    dataType: "json",
    contentType: "application/json",
    success(res){
      // console.log(res.message)
    }
  })
}

const addGaugeAnswer = (problem, des) => {
  $.ajax({
    url: API_BASE_URL + '/addAnswerInfo',
    type: "POST",
    data: JSON.stringify({questionId: problem.id, singleOption: des}),
    dataType: "json",
    contentType: "application/json",
    success(res){
      // console.log(res.message)
    }
  })
}

const insertDb = (objectList, list) => {
  objectList.map((item, index)=>{
    switch (item.questionType){
      case "1":addSingleAnswer(item, list[index])//传递问题实体和索引
        break
      case "2":addMultipleAnswer(item, list[index])
        break
      case "3":addFillAnswer(item, list[index])
        break
      case "4":addMatrixAnswer(item, list[index])
        break
      case "5":addGaugeAnswer(item, list[index])
        break
      default:break
    }
  })
}