import Navbar from "./views/partials/Navbar.js";

/**
 * Pushes the current URI to the URL bar and sets the HTML of the app div.
 * @param props - the data required for view rendering
 * @param route - the object containing information for the given endpoint
 */
export default function render(props, route) {
    let body = document.getElementById("black-bg");
    if (route.backgroundColor) {
        body.setAttribute("style", `background-color: ${route.backgroundColor};`);
        body.setAttribute("style",`color: white`)
    } else {
        console.log("no bueno")
        body.setAttribute("style",`background-color:powderblue`);
    }

    const app = document.querySelector('#app');
    const title = `REST Blog - ${route.title}`;
    document.title = title;
    app.innerHTML = `${Navbar(null)} ${route.returnView(props)}`;
    if (route.viewEvent){
        route.viewEvent();
    }
}
