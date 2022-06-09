fetch("/forumApp/users")
  .then((data) => {
    return data.json();
  })
  .then((userData) => {
    // console.log(completedata);
    let user = "";
    userData.map((values) => {
      user += `<div class="user-list">
      <div>
        <img
        id="userimg"
          src="${values.profilePic}"
          alt=""
          class="profile-pic"
          onerror="this.src='https://www.seekpng.com/png/detail/138-1387631_login-comments-windows-10-person-icon.png';";
      /></div>
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
    let current = 0;
    postData.map((values) => {
      current++;
      post += `<div id="post-list" class="post-list">
      <div id="test">
        <img
        src="${values.thumbnailUrl}"
          alt=""
          class="thumbnail"
          id="video+${current}"
          onerror="this.src='https://digitalfinger.id/wp-content/uploads/2019/12/no-image-available-icon-6-600x375.png';";
        />
        <div class="video">
        <div class="blur"></div>
        <div class="video-container">
          <video controls width="800px" height="600px" poster="https://res.cloudinary.com/drrkccbb4/image/upload/v1654707972/ForumApp/Minimal_Aesthetic_Hello_February_Facebook_Cover_mlkuj9.png">
            <source
            src="${values.videoUrl}"
              type="video/mp4"
              onerror="this.src='https://www.seekpng.com/png/detail/138-1387631_login-comments-windows-10-person-icon.png';"/>
            />
          </video>
          <div class="video-info">
          <img src="https://res.cloudinary.com/drrkccbb4/image/upload/v1654804588/ForumApp/xxxbutton_gygxyy.png" id="close_video+${current}" class="close" onclick="this.parentElement.parentElement.parentElement.classList.toggle('active'); this.parentElement.previousElementSibling.pause()">X</img>
            <h2 class="title">Title: 
            ${values.title}</h2>
            <p class="category2" ><span class="category">Category:</span> ${values.category}</p>
            <div class="flex-div info">
            <img src="${values.profilePic}" alt="" class="profile-pic" onerror="this.src='https://www.seekpng.com/png/detail/138-1387631_login-comments-windows-10-person-icon.png';";/>
            <p>${values.owner}</p> </div>
            <p><span class="description">DESCRIPTION:</span>
            ${values.description}</p>
           
        </div>
          </div>
         

        </div>
      </div>
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

function theFunction(e) {
  let video = document.getElementById(e.target.id).nextElementSibling;
  console.log("Video " + video);
  video.classList.toggle("active");
  video.firstElementChild.nextElementSibling.firstElementChild.play();
}
