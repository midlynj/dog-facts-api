import createView from "../createView.js";

export default function User(props) {
    return `

        <h2 style="margin: 10px">Sign up</h2>
        <form>
            <input type="text" placeholder="enter a username" style="margin-top: 50px;margin-bottom: 20px" id="username"><br>
             <input type="password" placeholder="enter a password" style="margin-top: 50px;margin-bottom: 20px" id="password"><br>
             <input type="email" placeholder="enter e-mail" style="margin-top: 50px;margin-bottom: 20px" id="e-mail">
             <input type="button" value="Register" id="register">
        </form>

        <h1 id="user"></h1>
    `

}

export function userEvents() {
    let registerButton = document.getElementById("register")
    registerButton.addEventListener("click", function (event) {
        let name = document.getElementById("username").value
        let pw = document.getElementById("password").value
        let email = document.getElementById("e-mail").value

        let user = {
            username: name,
            password: pw,
            email: email
        }

        let newUser = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body:JSON.stringify(user)
        }
        fetch("http://localhost:8080/api/users", newUser)
            .then(function (response) {
                if (!response.ok) {
                    console.log("new user was not created " + response.status)
                } else {
                    console.log("user created");
                    createView('/posts');
                }
            });
    })

}