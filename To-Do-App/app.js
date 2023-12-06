let tasks = [];
let completedTasks = [];

function addTask() {
  const taskInput = document.getElementById("taskInput");
  const taskText = taskInput.value.trim();
  
  if (taskText !== '') {
    const newTask = {
      id: Date.now(),
      text: taskText,
      completed: false,
      createdAt: new Date()
    };
    
    tasks.push(newTask);
    renderTasks();
    taskInput.value = '';
  }
}

function completeTask(id) {
  const taskIndex = tasks.findIndex(task => task.id === id);
  if (taskIndex !== -1) {
    const completedTask = tasks.splice(taskIndex, 1)[0];
    completedTask.completed = true;
    completedTasks.push(completedTask);
    renderTasks();
  }
}

function deleteTask(id, listType) {
  if (listType === 'pending') {
    tasks = tasks.filter(task => task.id !== id);
  } else {
    completedTasks = completedTasks.filter(task => task.id !== id);
  }
  renderTasks();
}

function renderTasks() {
  const pendingTasksList = document.getElementById("pendingTasks");
  const completedTasksList = document.getElementById("completedTasks");
  
  pendingTasksList.innerHTML = '';
  completedTasksList.innerHTML = '';
  
  tasks.forEach(task => {
    const li = document.createElement("li");
    li.textContent = `${task.text} - Added: ${task.createdAt.toLocaleString()}`;
    
    const completeButton = document.createElement("button");
    completeButton.textContent = "Complete";
    completeButton.onclick = () => completeTask(task.id);
    
    const deleteButton = document.createElement("button");
    deleteButton.textContent = "Delete";
    deleteButton.onclick = () => deleteTask(task.id, 'pending');
    
    li.appendChild(completeButton);
    li.appendChild(deleteButton);
    pendingTasksList.appendChild(li);
  });
  
  completedTasks.forEach(task => {
    const li = document.createElement("li");
    li.textContent = `${task.text} - Completed: ${task.createdAt.toLocaleString()}`;
    
    const deleteButton = document.createElement("button");
    deleteButton.textContent = "Delete";
    deleteButton.onclick = () => deleteTask(task.id, 'completed');
    
    li.appendChild(deleteButton);
    completedTasksList.appendChild(li);
  });
}
