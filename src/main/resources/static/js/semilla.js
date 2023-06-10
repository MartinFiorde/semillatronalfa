const d = document;

/* OBSERVACIONES GENERALES (SEMILLA) */
d.querySelector('.observaciones-menu').addEventListener("click", () => {
  d.querySelector('.observaciones-arrow').classList.toggle("active");
  d.querySelector('.observaciones-form-container').classList.toggle("active");
});

/* d.querySelectorAll(".nav-item").forEach((n) =>
  n.addEventListener("click", () => {
    hamburger.classList.remove("active");
    navMenu.classList.remove("active");
  })
); */
/* ADAPTAR ESTO A CADA BOTON */


/* tabs naranjas */
d.querySelector('.button-datos-personales').addEventListener("click", () => {
  d.querySelector('.button-datos-personales').classList.toggle("active");
  d.querySelector('.tab-datos-personales').classList.toggle("active");
});

d.querySelector('.button-datos-de-contacto').addEventListener("click", () => {
  d.querySelector('.button-datos-de-contacto').classList.toggle("active");
  d.querySelector('.tab-datos-de-contacto').classList.toggle("active");
});

/* d.querySelectorAll(".itemA").forEach((n) =>
  n.addEventListener("click", () => {

    d.querySelectorAll(".bA").classList.remove("active");
    d.querySelectorAll(".iA").classList.remove("active");
    d.querySelectorAll(".a").classList.remove("active");
    d.querySelectorAll(".b").classList.remove("active");
    d.querySelectorAll(".c").classList.remove("active");
    d.querySelectorAll(".d").classList.remove("active");
    d.querySelectorAll(".e").classList.remove("active");
    d.querySelectorAll(".f").classList.remove("active");
  })
); */