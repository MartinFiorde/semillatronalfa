const d = document;

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