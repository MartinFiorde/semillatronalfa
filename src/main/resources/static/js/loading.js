/* LOADING */
const loadingOFF = () => {
  const loadingContainer = document.querySelector(".loading-container");
  loadingContainer.classList.add("loading-off");
}
window.addEventListener('load', loadingOFF);