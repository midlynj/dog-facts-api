import createView from "../createView.js";
import {getHeaders} from "../auth.js";
export {createPost}

export default function createPost(props) {
    return `

        <h2 style="margin: 10px">Add a post</h2>
        <form>
            <input type="text" placeholder="enter title" style="margin-top: 50px;margin-bottom: 20px" id="title"><br>
            <textarea id="content"></textarea><br>
            <input type="button" value="Save" id="save-post-button">
        </form>

        <h1 id="posts"></h1>
    `
}

export function createPostEvent() {
    let saveButton = document.getElementById("save-post-button");
    saveButton.addEventListener("click", function (event) {
        let newTitle = document.getElementById("title").value;
        let newContent = document.getElementById("content").value;
        console.log({title: newTitle, content: newContent})

        let createPost = {
            title: newTitle,
            content : newContent
        }

        if (newTitle.trim().length < 1 || newContent.trim().length < 1) {
           createView('/custom404')
            return;

        }

        let newPosts = {
            method: "POST",
            headers: getHeaders(),
            body:JSON.stringify(createPost)
        }

        console.log(newPosts);

        fetch("http://localhost:8080/api/posts", newPosts)
            .then(function (response) {
                if (!response.ok) {
                    console.log("new post was not created " + response.status)
                } else {
                    console.log("post created");
                    createView('/posts');
                }
            });




    })
}
