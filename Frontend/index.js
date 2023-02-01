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

let todos = [];

if (localStorage.getItem("todoList")) {
  todos = JSON.parse(localStorage.getItem("todoList"));
  renderTodos(todos);
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

const switchModal = () => {
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
addBtn.addEventListener("click", switchModal);

window.onclick = function (event) {
  const addModal = document.querySelector(".modalAdd");
  const edtModal = document.querySelector(".modalEdt");
  if (event.target == addModal) {
    switchModal();
  } else if (event.target == edtModal) {
    switchedtModal();
  }
};

const switchedtModal = () => {
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
    switchedtModal();
  }
};

const edtBtn = document.querySelector(".edtBtn");
edtBtn.addEventListener("click", verifyEditClick);

const add = function () {
  if (
    name.value == "" ||
    description.value == "" ||
    endDate.value == "" ||
    endTime.value == "" ||
    priority.value == "" ||
    category.value == ""
  ) {
    alert("Campos não podem ficar em branco");
  } else if (!verifyTodoExists(name.value)) {
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

    todos.push(todo);

    localStorage.setItem("todoList", JSON.stringify(todos));

    clearEditFields();
    renderTodos(todos);
    addModal.style.display = "none";
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
      <td> <button class="edtBtn" onclick=editTodo(this) /> <button class="delBtn" onclick=delTodo(${todo.name.replace(
        /\s/g,
        ""
      )}) /></td>
      </tr> `;
  });
}

const delTodo = function (item) {
  todos = todos.filter(function (todo) {
    return todo.name != item.id;
  });
  localStorage.setItem("todoList", JSON.stringify(todos));
  renderTodos(todos);
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
};

function update() {
  if (
    editDescription.value == "" ||
    editendDate.value == "" ||
    editendTime.value == "" ||
    editpriority.value == "" ||
    editcategory.value == ""
  ) {
    alert("Campos não podem ficar em branco");
  } else {
    selectedRow.cells[1].innerHTML = editDescription.value;
    selectedRow.cells[2].innerText = editendDate.value;
    selectedRow.cells[3].innerText = editendTime.value;
    selectedRow.cells[4].innerText = editpriority.value;
    selectedRow.cells[5].innerHTML = editcategory.value;
    selectedRow.cells[6].innerText =
      editStatus.options[editStatus.options.selectedIndex].text;
    edtModal.style.display = "none";
    if (editStatus.options[editStatus.options.selectedIndex].text == "") {
      alert("Campos não podem ficar em branco");
    }

    todos.forEach((todo) => {
      if (todo.name == editName.value.trim()) {
        console.log("entrou aqui");
        todo.description = editDescription.value;
        todo.endDate = editendDate.value;
        todo.endTime = editendTime.value;
        todo.priority = editpriority.value;
        todo.category = editcategory.value;
        todo.status = editStatus.options[editStatus.options.selectedIndex].text;
      }
    });
    console.log(todos);

    localStorage.setItem("todoList", JSON.stringify(todos));
    renderTodos(todos);

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
    const td = tr[i].getElementsByTagName("td")[6];

    if (td) {
      if (itemToFilter.value == td.innerText) {
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
  if (todos) {
    todos.forEach((todo) => {
      if (todo.name == name) {
        isOk = false;
      }
    });
  }
  return isOk;
};

itemToFilter.addEventListener("change", filter);
