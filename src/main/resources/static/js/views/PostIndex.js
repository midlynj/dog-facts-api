import createView from "../createView.js";
import {getHeaders} from "../auth.js";
import createPost from "./createPost.js";

export default function PostIndex(props) {
    return `
    <div class="blue">
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <div>
               <h3 id="title"></h3>
               <h6 id="content"></h6>
               <h6 id="author"></h6>
            </div>
        </main>
        <a data-link href="/create-post">Create a post</a><br>
    </div>   
    `;
}

export function postEvents() {
    async function posts(response) {
        let currentPosts = await response.json()
        console.log(response)
        console.log(currentPosts)

        let title = document.getElementById("title")
        let content = document.getElementById("content")
        let author = document.getElementById("author")

        let html = ` <div class="container">`;

        for (let i = 0; i < currentPosts.length; i++) {

            html += `

                        <div>
                        <table class="table table-dark">
  <thead>
    
  </thead>
  <tbody>
  <th>Title</th>
   <th>Content</th>
    <th>Category</th>
     <th>Author</th>
    <tr class="table-active">
    
    </tr>
    <tr>
      
    </tr>
    <tr>
      <th scope="row">${currentPosts[i].title}</th>
      <th colspan="1" class="table-active">${currentPosts[i].content}</th>
      <th scope="row">${currentPosts[i].categories[0].name}</th>
      <th colspan="1" class="table-active" >${currentPosts[i].author.userName}</th>
    </tr>
        <input type="button" value="❌" style="width: 40px;border-radius: 20px" id="delete">
        <input type="button" value="✏️" style="width: 40px;border-radius: 20px; float: left;" id="edit">
  </tbody>
</table>
                       
            
              
                     
                       
                        
                
                    
                    </div>
                `

        }

        author.innerHTML = html + `
                </div>
            `

        let deleteButton = document.querySelectorAll("#delete");
        for (let i = 0; i < currentPosts.length; i++) {
            deleteButton[i].addEventListener("click", function (event){

                let deletePost = {
                    method: "Delete",
                    headers: getHeaders(),

                }

                let id = currentPosts[i].id

                fetch(`http://localhost:8080/api/posts/${id}`, deletePost)
                    .then(function(response) {
                        if(!response.ok) {
                            console.log("post deletion error: " + response.status);
                        } else {
                            console.log("post delete");
                            createView('/posts');
                        }
                    });
            })
        }

        let editButton = document.querySelectorAll("#edit");
        for (let i = 0; i < currentPosts.length; i++) {
            editButton[i].addEventListener("click", function (event) {

               createPost()
                let newTitle = prompt("enter title")
                let newContent = prompt("enter content")



                let editPost = {
                    method: "PATCH",
                    headers: getHeaders(),
                    body: JSON.stringify({title: newTitle, content: newContent})
                }
console.log(editPost);

console.log(currentPosts);


                let id = currentPosts[i].id;

                fetch(`http://localhost:8080/api/posts/${id}`, editPost)
                    .then(function (response) {
                        if (!response.ok) {
                            console.log("post editing error: " + response.status);
                        } else {
                            console.log("post updated");
                            createView('/posts');
                        }
                    });

            })
        }

        }
    let request = {
        method: "GET",
        headers: getHeaders(),
    }
        fetch("http://localhost:8080/api/posts", request)

        .then(posts)
        .catch(function(error){
            console.log("error" + error)
        })

}