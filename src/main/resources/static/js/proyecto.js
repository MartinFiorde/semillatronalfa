function goBack(){history.back()}

/* OBSERVACIONES GENERALES */
document.querySelector('.observaciones-menu').addEventListener("click", (e) => {
  document.querySelector('.observaciones-menu').classList.toggle("active");
  document.querySelector('.observaciones-arrow').classList.toggle("active");
  document.querySelector('.observaciones-form-container').classList.toggle("active");
});


/* AUTO HEIGHT VALOR */
document.querySelector('.tab-valor')

/* MODIFICAR ESTADO */
/* document.querySelector('.modificar-estado-menu').addEventListener("click", () => {
  document.querySelector('.modificar-estado-menu .arrow').classList.toggle("active");
  document.querySelector('.modificar-estado-options').classList.toggle("active");
});
document.querySelectorAll(".modificar-estado-option").forEach((n) =>
    n.addEventListener("click", () => {
      alert('Estado modificado!');
      document.querySelector('.modificar-estado-menu .arrow').classList.remove("active");
      document.querySelector('.modificar-estado-options').classList.remove("active");
    })
); */


