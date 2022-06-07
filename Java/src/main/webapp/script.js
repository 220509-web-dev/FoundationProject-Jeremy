fetch("/forumApp/users")
  .then((data) => {
    return data.json();
  })
  .then((userData) => {
    // console.log(completedata);
    let user = "";
    userData.map((values) => {
      user += `<div class="user-list">
      <a href="">
        <img
          src="${values.profilePic}"
          alt=""
          class="profile-pic"
      /></a>
      <div class="flex-div">
        <img src="" alt="" />
        <div class="user-info">
          <h2>Username: </h2>
          <h2> ${values.username}</h2>
          <p>User ID: ${values.id}</p>
          <p>FirstName: ${values.firstName}</p>
          <p>LastName: ${values.lastName}</p>
        </div>
      </div>
    </div>`;
    });
    document.getElementById("users").innerHTML = user;
  })
  .catch((err) => {
    console.log(err);
  });

fetch("/forumApp/posts")
  .then((data) => {
    return data.json();
  })
  .then((postData) => {
    let post = "";
    postData.map((values) => {
      post += `<div id="post-list" class="post-list"><a href="" onclick="">
      <img
        src="${values.thumbnailUrl}"
        alt=""
        class="thumbnail"
    /></a>
    <div class="flex-div">
      <img src="" alt="" />
      <div class="vid-info">
        <a href="">${values.title}</a>
        <p>${values.description}</p>
      </div>
    </div>
    </div>`;
    });
    document.getElementById("posts").innerHTML = post;
  })
  .catch((err) => {
    console.log(err);
  });

window.onload = function () {
  document.getElementById("login-btn").addEventListener("click", login);
};

window.onload = function () {
  document.getElementById("register-btn").addEventListener("click", register);
};

window.onload = function () {
  document.getElementById("upload-btn").addEventListener("click", upload);
};
function login() {
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;

  fetch("/forumApp/auth", {
    method: "POST",
    body: JSON.stringify({ username, password }),
  });
}

function register() {
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
  let firstName = document.getElementById("firstName").value;
  let lastName = document.getElementById("lastName").value;
  let profilePic = document.getElementById("profilePic").value;
  console.log(username.value, password.value, firstName, lastName, profilePic);

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

function upload() {
  let title = document.getElementById("title").value;
  let description = document.getElementById("description").value;
  let categoryId = document.getElementById("category").value;
  let ownerId = document.getElementById("ownerId").value;
  let thumbnailUrl = document.getElementById("thumbnailUrl").value;
  let videoUrl = document.getElementById("videoUrl").value;

  fetch("/forumApp/posts", {
    method: "POST",
    body: JSON.stringify({
      title,
      description,
      categoryId,
      ownerId,
      thumbnailUrl,
      videoUrl,
    }),
  });
}
