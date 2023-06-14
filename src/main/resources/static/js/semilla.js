const d = document;

/* NAVBAR */
let prevScrollpos = window.pageYOffset;

window.onscroll = function() {
  var currentScrollPos = window.pageYOffset;
    if (prevScrollpos > currentScrollPos) {
      d.querySelector("nav").style.top = "0";
    } else {
      d.querySelector("nav").style.top = "-5.5rem";
    }
  prevScrollpos = currentScrollPos;
}

/* OBSERVACIONES GENERALES */
d.querySelector('.observaciones-menu').addEventListener("click", () => {
  d.querySelector('.observaciones-arrow').classList.toggle("active");
  d.querySelector('.observaciones-form-container').classList.toggle("active");
});

/* MODIFICAR ESTADO */
d.querySelector('.modificar-estado-menu').addEventListener("click", () => {
  d.querySelector('.modificar-estado-menu .arrow').classList.toggle("active");
  d.querySelector('.modificar-estado-options').classList.toggle("active");
});
d.querySelectorAll(".modificar-estado-option").forEach((n) =>
  n.addEventListener("click", () => {
    alert('Estado modificado!');
    d.querySelector('.modificar-estado-menu .arrow').classList.remove("active");
    d.querySelector('.modificar-estado-options').classList.remove("active");
  })
);