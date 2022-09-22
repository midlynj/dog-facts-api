import createView from "../createView.js";


export default function custom404(props) {


    return`
        <style>
            body {
  background-color: black;
  font-family: cursive;
}

.error {
  font-size: 80px;
  color: #fff;
  text-align: center;
  animation: glow 1s ease-in-out infinite alternate;
}

@-webkit-keyframes glow {
  from {
    text-shadow: 0 0 10px #070606, 0 0 20px #0a0a0a, 0 0 30px #c70505, 0 0 40px #ec0505, 0 0 50px #e60073, 0 0 60px #e60073, 0 0 70px #e60073;
  }
  
  to {
    text-shadow: 0 0 20px #0c0c0c, 0 0 30px #ce1705, 0 0 40px #9b0606, 0 0 50px #690505, 0 0 60px #ff4da6, 0 0 70px #ff4da6, 0 0 80px #ff4da6;
  }
}
        
        </style>
        <body>
            <div id="main" style="font-weight: bold; font-size: xx-large">
                <div class="error">
                    <h1 style="font-weight: bold; font-size: xxx-large">Error 404!</h1>
                </div>
                    <div>
                        <img src="js/views/partials/bear.png">
                    </div>
            Title or Content Cannot be empty</div>    
            <a href="/create-post" data-link>Try again</a>
        </body>    
    `
}