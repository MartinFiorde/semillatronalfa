const d = document;

/* OBSERVACIONES GENERALES (SEMILLA) */
d.querySelector('.observaciones-menu').addEventListener("click", () => {
  d.querySelector('.observaciones-arrow').classList.toggle("active");
  d.querySelector('.observaciones-form-container').classList.toggle("active");
});
