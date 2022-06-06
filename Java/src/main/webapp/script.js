fetch("http://localhost:8080/forumApp/users")
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

fetch("http://localhost:8080/forumApp/posts")
  .then((data) => {
    return data.json();
  })
  .then((postData) => {
    console.log(postData);
    let post = "";
    postData.map((values) => {
      post += `<div id="post-list" class="post-list"><a href="">
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
