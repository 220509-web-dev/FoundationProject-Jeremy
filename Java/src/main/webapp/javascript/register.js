window.onload = function () {
  document.getElementById("register-btn").addEventListener("click", register);
};

function register() {
  console.log("register");
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
  let firstName = document.getElementById("firstName").value;
  let lastName = document.getElementById("lastName").value;
  let profilePic = document.getElementById("profilePic").value;

  fetch("/forumApp/users", {
    method: "POST",
    body: JSON.stringify({
      username,
      password,
      firstName,
      lastName,
      profilePic,
    }),
  });
}
