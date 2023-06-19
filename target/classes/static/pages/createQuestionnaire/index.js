onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('创建问卷')
  let projectItem = JSON.parse(decodeURIComponent($util.getPageParam('createQuestionnaire')))//收集参数从questionnaire来的,包含id和name
  console.log(projectItem)
  fetchProjectList(projectItem)
}

let paramsList = { }
let valuee = 1
const fetchProjectList = (projectDefult) =>{
  let params= {
    createdBy: $util.getItem('userInfo')[0].username,
  }
  $.ajax({
    url: API_BASE_URL + '/queryProjectList',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res){
      // $('#selectProject').html('')//设置空，不需要不然会覆盖placeholder
      $('#selectProject').append(`<option value="${projectDefult.idd}" selected>${projectDefult.namee}</option>`)//设置默认值为当前项目,2下标为name
      valuee++
      res.data.map(item => {
        let currentId = item.id
        // console.log(currentId)
        // if(item.projectName !== proName){//option列表不再需要展示默认值，但是项目名可能会重复，还是保留吧
          $('#selectProject').append(`<option value="${currentId}">${item.projectName}</option>`)
          // valuee++
        // }
      })
    }
  })

  $('#empty').append(`<button type="button" class="btn btn-default" onclick="onCreateTemplate()">创 建</button>`)
}



const onCreateTemplate = () => {
  let seleP = $('#selectProject option:selected')
  paramsList["projectIdd"] = seleP.val()
  paramsList["oriented"] = $('#orientedWho option:selected').text()
  $util.setPageParam('createNewQuestionnaire', encodeURIComponent(JSON.stringify(paramsList)))
  location.href = "/pages/createNewQuestionnaire/index.html"
}

const importHistoryQuestionnaire = () => {
  $('#divider').css('display', 'flex')
  $('#templateB').html('')
  $('#templateB').append(`
    <div class="template-item">
      <div class="item-t">
        <img class="img" src="../../static/images/blank_template.png" >
        <div>
          <div class="title">测试</div>
          <div>页面测试数据</div>
        </div>
      </div>
      <div class="item-b">
        <button type="button" class="btn btn-default">导 入</button>
      </div>
    </div>
  `)
}

const surveyTypeTemplate = () => {
  $('#divider').css('display', 'flex')
  $('#templateB').html('')
  $('#templateB').append(`
    <div class="template-item">
      <div class="item-t">
        <img class="img" src="../../static/images/blank_template.png">
        <div>
          <div class="title">创建模板</div>
          <div>题库抽题，限时作答，成绩查询，自动阅卷</div>
        </div>
      </div>
      <div class="item-b">
        <button type="button" class="btn btn-default" onclick="createTemplate()">创 建</button>
      </div>
    </div>
    <div class="template-item">
      <div class="item-t">
        <img class="img" src="../../static/images/blank_template.png">
        <div>
          <div class="title">测试</div>
          <div></div>
        </div>
      </div>
      <div class="item-b">
        <button type="button" class="btn btn-default" onclick="handleEdit()" style="margin-right: 10px;">编 辑</button>
        <button type="button" class="btn btn-default">导 入</button>
      </div>
    </div>
  `)
}

const createTemplate = () => {
  $('#createTemplateModal').modal('show')
}

const handleEdit = () => {
  open('/pages/designQuestionnaire/index.html')
}
