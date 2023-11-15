/* NAVBAR */
let prevScrollpos = window.pageYOffset;

window.onscroll = function() {
  var currentScrollPos = window.pageYOffset;
    /*if (prevScrollpos > currentScrollPos) {
      document.querySelector("nav").style.top = "0";
    } else {
      document.querySelector("nav").style.top = "-5.5rem";
    }*/
    if (prevScrollpos > currentScrollPos) {
      document.querySelector("nav").style.visibility = "visible";
      document.querySelector("nav").style.opacity = "1";
    } else {
      document.querySelector("nav").style.visibility = "hidden";
      document.querySelector("nav").style.opacity = "0";
    }
  prevScrollpos = currentScrollPos;
}