const d = document;

/* PASSWORD */
d.querySelector('.password-control').addEventListener("click", ()=> {
  if (d.querySelector('.password-input').getAttribute('type') == 'password') {
    d.querySelector('.password-input').setAttribute('type', 'text');
    d.querySelector('.password-control').classList.add('view');
  } else {
    d.querySelector('.password-input').setAttribute('type', 'password');
    d.querySelector('.password-control').classList.remove('view');
  }
})

/* REMINDME */
d.querySelector('.check').addEventListener("click", ()=> {
  //alert('Recordado!')
})