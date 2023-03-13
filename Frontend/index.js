const name = document.getElementById("name");
const description = document.getElementById("description");
const endDate = document.getElementById("endDate");
const endTime = document.getElementById("endTime");
const priority = document.getElementById("priority");
const category = document.getElementById("category");
const status = document.getElementById("status");

const editName = document.getElementById("editName");
const editDescription = document.getElementById("editDescription");
const editendDate = document.getElementById("editendDate");
const editendTime = document.getElementById("editendTime");

const editpriority = document.getElementById("editpriority");
const editcategory = document.getElementById("editcategory");
const editStatus = document.getElementById("editStatus");

const addModal = document.querySelector(".modalAdd");
const edtModal = document.querySelector(".modalEdt");
const table = document.getElementById("todoTable");
const itemToFilter = document.getElementById("filter");
const tr = table.getElementsByTagName("tr");
const insertDataIntoTable = document.getElementById("insertDataIntoTable");

multipleOptions = document.getElementById("multipleOptions");
multipleChange = document.getElementById("multipleChange");

let tasks = [];

if (localStorage.getItem("todoList")) {
  tasks = JSON.parse(localStorage.getItem("todoList"));
  renderTodos(tasks);
}

const clearFields = () => {
  name.value = "";
  description.value = "";
  endDate.value = "";
  endTime.value = "";
  priority.value = "";
  category.value = "";
  status.value = "";
};

const clearEditFields = () => {
  editName.value = "";
  editDescription.value = "";
  editendDate.value = "";
  editendTime.value = "";
  editpriority.value = "";
  editcategory.value = "";
  editStatus.options.selectedIndex = "";
};

const switchCreateModal = () => {
  const addStatus = addModal.style.display;
  if (addStatus == "block") {
    addModal.style.display = "none";
    clearFields();
  } else {
    addModal.style.display = "block";
    clearFields();
  }
};

const addBtn = document.querySelector(".addBtn");
addBtn.addEventListener("click", switchCreateModal);

window.onclick = function (event) {
  const addModal = document.querySelector(".modalAdd");
  const edtModal = document.querySelector(".modalEdt");
  if (event.target == addModal) {
    switchCreateModal();
  } else if (event.target == edtModal) {
    switchEditModal();
  }
};

const switchEditModal = () => {
  const edtStatus = edtModal.style.display;
  if (edtStatus == "block") {
    edtModal.style.display = "none";
    clearEditFields();
  } else {
    edtModal.style.display = "block";
  }
};

const verifyEditClick = () => {
  if (editName.value == "") {
    alert(
      "Para editar é preciso que antes clique no botao laranja do item que quer editar"
    );
  } else {
    switchEditModal();
  }
};

const edtBtn = document.querySelector(".edtBtn");
edtBtn?.addEventListener("click", verifyEditClick);

const addTask = function () {
  if (
    validadeTask(
      name.value,
      description.value,
      endDate.value,
      endTime.value,
      priority.value,
      category.value,
      status
    )
  ) {
    if (!verifyTodoExists(name.value)) {
      alert("Tarefa ja existe!");
    } else {
      const statusOption = status.options[status.selectedIndex].text;
      const todo = {
        name: name.value.replace(/\s/g, ""),
        description: description.value,
        endDate: endDate.value,
        endTime: endTime.value,
        priority: priority.value,
        category: category.value,
        status: statusOption,
      };

      tasks.push(todo);

      localStorage.setItem("todoList", JSON.stringify(tasks));
      sendEmail("Nova tarefa", todo);

      clearEditFields();
      renderTodos(tasks);
      addModal.style.display = "none";
    }
  }
};

function renderTodos(todolist) {
  insertDataIntoTable.innerHTML = "";
  todolist.forEach((todo) => {
    insertDataIntoTable.innerHTML += `<tr id=${todo.name.replace(/\s/g, "")}>
    
      <td> ${todo.name} </td>
      <td> ${todo.description} </td>
      <td> ${todo.endDate} </td>
      <td> ${todo.endTime} </td>
      <td> ${todo.priority} </td>
      <td> ${todo.category} </td>
      <td> ${todo.status}</td>
      <td>
       <button class="edtBtn" onclick=editTodo(this) > Editar </button> <button class="delBtn" onclick=delTodo(${todo.name.replace(
         /\s/g,
         ""
       )}) > Deletar </button>
      </td>
      <td><input class=${todo.name.replace(/\s/g, "")} type="checkbox"/> </td>
      </tr> `;
  });
}

const delTodo = function (item) {
  tasks = tasks.filter(function (todo) {
    return todo.name != item.id;
  });
  localStorage.setItem("todoList", JSON.stringify(tasks));
  renderTodos(tasks);
};

const editTodo = function (td) {
  selectedRow = td.parentElement.parentElement;
  editName.value = selectedRow.cells[0].innerHTML;
  editDescription.value = selectedRow.cells[1].innerHTML;
  editendDate.value = selectedRow.cells[2].innerText;
  editendTime.value = selectedRow.cells[3].innerText;
  editpriority.value = selectedRow.cells[4].innerText;
  editcategory.value = selectedRow.cells[5].innerHTML;
  editStatus.value = selectedRow.cells[6].innerText;

  switchEditModal();
};

function update() {
  if (
    validadeTask(
      editName.value,
      editDescription.value,
      editendDate.value,
      editendTime.value,
      editpriority.value,
      editcategory.value,
      editStatus
    )
  ) {
    selectedRow.cells[1].innerHTML = editDescription.value;
    selectedRow.cells[2].innerText = editendDate.value;
    selectedRow.cells[3].innerText = editendTime.value;
    selectedRow.cells[4].innerText = editpriority.value;
    selectedRow.cells[5].innerHTML = editcategory.value;
    selectedRow.cells[6].innerText =
      editStatus.options[editStatus.options.selectedIndex].text;
    edtModal.style.display = "none";

    tasks.forEach((todo) => {
      if (todo.name == editName.value.trim()) {
        todo.description = editDescription.value;
        todo.endDate = editendDate.value;
        todo.endTime = editendTime.value;
        todo.priority = editpriority.value;
        todo.category = editcategory.value;
        todo.status = editStatus.options[editStatus.options.selectedIndex].text;
      }
    });

    localStorage.setItem("todoList", JSON.stringify(tasks));
    renderTodos(tasks);

    clearEditFields();
  }
}

const cleanFilter = () => {
  for (let i = 0; i < tr.length; i++) {
    tr[i].style.display = "";
  }
};

const filter = () => {
  cleanFilter();
  for (let i = 0; i < tr.length; i++) {
    const statusSelected = tr[i].getElementsByTagName("td")[6];

    if (statusSelected) {
      if (itemToFilter.value == statusSelected.innerText) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
      if (itemToFilter.value == "Sem filtro") {
        cleanFilter();
      }
    }
  }
};

const verifyTodoExists = (name) => {
  let isOk = true;
  if (tasks) {
    tasks.forEach((todo) => {
      if (todo.name == name) {
        isOk = false;
      }
    });
  }
  return isOk;
};

const changeMany = () => {
  tasks.forEach((todo) => {
    if (document.getElementsByClassName(todo.name)[0].checked) {
      if (multipleOptions.value != 0) {
        todo.status = multipleOptions.value;
      }
    }
  });
  localStorage.setItem("todoList", JSON.stringify(tasks));
  renderTodos(tasks);
  multipleOptions.value = 0;
};

itemToFilter.addEventListener("change", filter);

const nameRegex = /[A-z]{4,15}/;
const priorityRegex = /[1-5]/;
const categoryRegex = /[A-z]{4,15}/;
const descriptionRegex = /[A-z]{4,15}/;

function validadeTask(
  name,
  description,
  endDate,
  endTime,
  priority,
  category,
  status
) {
  let isOk = false;
  const today = new Date();
  const endDateTime = endDate + "T" + endTime;
  const taskEndDate = new Date(endDateTime);

  if (
    name == "" ||
    description == "" ||
    endDate == "" ||
    endTime == "" ||
    priority == "" ||
    category == ""
  ) {
    alert("Campos não podem ficar em branco");
  } else if (!nameRegex.test(name)) {
    alert("Nome precisa possuir de 4-15 digitos alfanuméricos");
  } else if (!descriptionRegex.test(description)) {
    alert("Descrição precisa possuir de 4-15 digitos alfanuméricos");
  } else if (!priorityRegex.test(priority)) {
    alert("A prioridade precisa ser de 1 a 5");
  } else if (!categoryRegex.test(category)) {
    alert("Categoria pode apenas possuir de 4-15 digitos alfanuméricos");
  } else if (status.options[status.selectedIndex].text != undefined) {
    if (
      status.options[status.selectedIndex].text !== "done" &&
      taskEndDate < today
    ) {
      alert(
        "Data de término não pode ser anterior a hoje para tarefas com status a fazer(toDo) ou fazendo(doing)"
      );
    } else {
      isOk = true;
    }
  }
  return isOk;
}


//EmailService
function sendEmail(subject,todo) {
Email.send({
    Host : "smtp.elasticemail.com",
    Username : "th4r1k@gmail.com",
    Password : "94E0CB955C64D7AD21CE2F151F0CE1691585",
    To : 'th4r1k@gmail.com',
    From : "th4r1k@gmail.com",
    Subject : subject,
    Body : `Nome:${todo.name} Descrição:${todo.description} Data de término:${todo.endDate} Horário de término:${todo.endTime} Prioridade${todo.priority} Categoria:${todo.category} Status:${todo.status}`
}).then(
  message => alert(message)

);
}
