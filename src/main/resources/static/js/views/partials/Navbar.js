import {isLoggedIn} from "../../auth.js";

export default function Navbar(props) {
    if(isLoggedIn()) {
        let navbar = ""
        navbar += `
             <a href="/" data-link>Home</a>
             <a href="/posts" data-link>Posts</a>
            <a href="/me" data-link>About ME</a>
            <a href="/logout" data-link>Logout</a>
        `;
        return navbar
    }
    return `
        <nav>
            <a href="/" data-link>Home</a>
            <a href="/posts" data-link>Posts</a>
            <a href="/about" data-link>About</a>
            <a href="/login" data-link>Login</a>
            <a href="/register" data-link>Register</a>
        </nav>
        
    `;
}