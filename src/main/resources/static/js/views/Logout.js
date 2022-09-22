import createView from "../createView.js";

export default function Logout(prop) {
    return  `
        <h1>Logging out...</h1>
    `
}

export function LogoutEvents() {
    window.setTimeout(function () {
        window.localStorage.removeItem("access_token");
        window.localStorage.removeItem("refresh_token");
        createView('/');
    },5)

}