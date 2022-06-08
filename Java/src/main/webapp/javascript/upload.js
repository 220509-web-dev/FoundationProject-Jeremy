window.onload = function () {
  document.getElementById("upload-btn").addEventListener("click", upload);
};

function upload() {
  console.log("upload");
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
