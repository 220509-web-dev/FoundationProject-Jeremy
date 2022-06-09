window.onload = function () {
  document.getElementById("login-btn").addEventListener("click", login);
};

function login() {
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
  let error = document.getElementById("error");

  fetch("/forumApp/auth/login", {
    method: "POST",
    body: JSON.stringify({ username, password }),
  })
    .then((response) =>
      response.json().then((data) => {
        console.log(data);
        if (response.ok) {
          let user = data;
          let header = document.getElementById("success");
          error.hidden = true;
          header.hidden = false;
          header.innerHTML = "Welcome " + user.username;
          header.innerHTML += "<img src='" + user.profilePic + "'>";
          window.setTimeout(function () {
            window.location.href = "index.html";
          }, 3000);
        } else {
          error.hidden = false;
          error.innerHTML = "invalid username + password";
        }
      })
    )
    .catch(() => {
      error.hidden = false;
      error.innerHTML = "Issue attempting to log in";
    });
}
