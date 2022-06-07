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
        id="userimg"
          src="${values.profilePic}"
          alt=""
          class="profile-pic"
          onerror="this.src='https://www.seekpng.com/png/detail/138-1387631_login-comments-windows-10-person-icon.png';";
      /></a>
      <div class="flex-div">
        <div class="user-info">
          <h2>Username: </h2>
          <h4> ${values.username}</h4>
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
    console.log(postData);
    let post = "";
    postData.map((values) => {
      post += `<div id="post-list" class="post-list"><a href="${values.videoUrl}" target="_blank"  onclick="toggle();">
      <img
        src="${values.thumbnailUrl}"
        alt=""
        class="thumbnail" 
        onerror="this.src='https://c.tenor.com/qOwAMZ6sAVEAAAAd/on-my-own-empty-fridge.gif';";
    /></a>
    <div class="flex-div">
      <img id="userimg" src="${values.profilepic}" alt="" 
      onerror="this.src='https://www.seekpng.com/png/detail/138-1387631_login-comments-windows-10-person-icon.png';"/>
      <div class="vid-info">
        <a href="">${values.title}</a>
        <p>${values.owner}</p>
        <p>Category: ${values.category}</p>
      </div>

    </div>
    </div>`;
    });
    document.getElementById("posts").innerHTML = post;
  })
  .catch((err) => {
    console.log(err);
  });

const userimg = document.getElementById("userimg");

img.addEventListener("error", function handleError() {
  const defaultImage =
    "https://bobbyhadz.com/images/blog/javascript-show-div-on-select-option/banner.webp";

  img.src = defaultImage;
  img.alt = "default";
});

function toggle() {
  var video = document.querySelector(".movie_video");
  video.classList.toggle("active");
  var video = document.querySelector("video");
  video.classList.toggle("active");
  video.pause();
  video.currentTime = 0;
}
