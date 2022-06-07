window.onload = function () {
  document.getElementById("login-btn").addEventListener("click", login);
};

function login() {
  console.log("login");
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;

  fetch("/forumApp/auth", {
    method: "POST",
    body: JSON.stringify({ username, password }),
  });

  alert("You have successfully logged in!");
}
