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
  alert('Recordado!')
})

/* LOGIN */
/*
d.getElementById("loginForm").addEventListener("submit", function(event) {
  event.preventDefault();

  var username = d.getElementById("username").value;
  var password = d.getElementById("password").value;

  var user = {
      username: username,
      password: password
  };

  fetch("/login", {
      method: "POST",
      headers: {
          "Content-Type": "application/json"
      },
      body: JSON.stringify(user)
  })
  .then(function(response) {
      if (response.ok) {
          alert("NICE");
          //window.location.href = "/index.html";
      } else {
          alert("Credenciales inválidas");
      }
  })
  .catch(function(error) {
      console.log(error);
  });
});
*/