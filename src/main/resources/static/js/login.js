/* PASSWORD */
if(document.querySelector('.password-control'))document.querySelector('.password-control').addEventListener("click", ()=> {
  if (document.querySelector('.password-input').getAttribute('type') == 'password') {
    document.querySelector('.password-input').setAttribute('type', 'text');
    document.querySelector('.password-control').classList.add('view');
  } else {
    document.querySelector('.password-input').setAttribute('type', 'password');
    document.querySelector('.password-control').classList.remove('view');
  }
})

/* REMINDME */
/* if(document.querySelector('.check'))document.querySelector('.check').addEventListener("click", ()=> {
  alert('Recordado!')
}) */