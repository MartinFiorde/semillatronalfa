const d = document;

/* OBSERVACIONES GENERALES (SEMILLA) */
d.querySelector('.observaciones-menu').addEventListener("click", () => {
  d.querySelector('.observaciones-arrow').classList.toggle("active");
  d.querySelector('.observaciones-form-container').classList.toggle("active");
});







/* tabs */
const buttonDP = d.querySelector('.button-datos-personales');
const tabDP = d.querySelector('.tab-datos-personales');
const buttonDDC = d.querySelector('.button-datos-de-contacto');
const tabDDC = d.querySelector('.tab-datos-de-contacto');
const buttonME = d.querySelector('.button-modificar-estado');
const tabME = d.querySelector('.tab-modificar-estado');
const buttonAP = d.querySelector('.button-asignar-proyecto');
const tabAP = d.querySelector('.tab-asignar-proyecto');
const buttonC = d.querySelector('.button-certificado');
const tabC =  d.querySelector('.tab-certificado');
const buttonR = d.querySelector('.button-recomendada');
const tabR = d.querySelector('.tab-recomendada');

buttonDP.addEventListener("click", () => {
  buttonDP.classList.toggle("active");
  tabDP.classList.toggle("active");

  buttonDDC.classList.remove("active");
  tabDDC.classList.remove("active");
  buttonME.classList.remove("active");
  tabME.classList.remove("active");
  buttonAP.classList.remove("active");
  tabAP.classList.remove("active");
  buttonC.classList.remove("active");
  tabC.classList.remove("active");
  buttonR.classList.remove("active");
  tabR.classList.remove("active");
});

buttonDDC.addEventListener("click", () => {
  buttonDDC.classList.toggle("active");
  tabDDC.classList.toggle("active");

  buttonDP.classList.remove("active");
  tabDP.classList.remove("active");
  buttonME.classList.remove("active");
  tabME.classList.remove("active");
  buttonAP.classList.remove("active");
  tabAP.classList.remove("active");
  buttonC.classList.remove("active");
  tabC.classList.remove("active");
  buttonR.classList.remove("active");
  tabR.classList.remove("active");
});
