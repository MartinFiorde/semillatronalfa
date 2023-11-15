const estado = document.querySelectorAll('.estado');
const facilitado = document.querySelectorAll('.facilitado');
const real_facilitado = document.getElementById("real-offeredBySemillero-input");
const enfoque = document.querySelectorAll('.enfoque');
const tipo = document.querySelectorAll('.tipo');
const origen = document.querySelectorAll('.origen');
const destinatarios = document.querySelectorAll('.destinatarios');
const modalidad = document.querySelectorAll('.modalidad');
const ubicacion = document.querySelectorAll('.ubicacion');


estado.forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');
    const options = dropdown.querySelectorAll('.menu li');
    const selected = dropdown.querySelector('.selected');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });

    options.forEach(option => {
        option.addEventListener('click', () => {
            selected.value = option.innerText;
            select.classList.remove('select-clicked');
            caret.classList.remove('caret-rotate');
            menu.classList.remove('menu-open');

            options.forEach(option => {
                option.classList.remove('active');
            });
            option.classList.add('active');
        });
    });
});

facilitado.forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');
    const options = dropdown.querySelectorAll('.menu li');
    const selected = dropdown.querySelector('.selected');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });

    options.forEach(option => {
        option.addEventListener('click', () => {
            selected.value = option.innerText;
            if (option.innerText === "Si") {
                real_facilitado.value = true;
            } else if (option.innerText === "No") {
                real_facilitado.value = false;
            }
            select.classList.remove('select-clicked');
            caret.classList.remove('caret-rotate');
            menu.classList.remove('menu-open');

            options.forEach(option => {
                option.classList.remove('active');
            });
            option.classList.add('active');
        });
    });
});

enfoque.forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');
    const options = dropdown.querySelectorAll('.menu li');
    const selected = dropdown.querySelector('.selected');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });

    options.forEach(option => {
        option.addEventListener('click', () => {
            selected.value = option.innerText;
            select.classList.remove('select-clicked');
            caret.classList.remove('caret-rotate');
            menu.classList.remove('menu-open');

            options.forEach(option => {
                option.classList.remove('active');
            });
            option.classList.add('active');
        });
    });
});

tipo.forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');
    const options = dropdown.querySelectorAll('.menu li');
    const selected = dropdown.querySelector('.selected');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });

    options.forEach(option => {
        option.addEventListener('click', () => {
            selected.value = option.innerText;
            select.classList.remove('select-clicked');
            caret.classList.remove('caret-rotate');
            menu.classList.remove('menu-open');

            options.forEach(option => {
                option.classList.remove('active');
            });
            option.classList.add('active');
        });
    });
});

origen.forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');
    const options = dropdown.querySelectorAll('.menu li');
    const selected = dropdown.querySelector('.selected');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });

    options.forEach(option => {
        option.addEventListener('click', () => {
            selected.value = option.innerText;
            select.classList.remove('select-clicked');
            caret.classList.remove('caret-rotate');
            menu.classList.remove('menu-open');

            options.forEach(option => {
                option.classList.remove('active');
            });
            option.classList.add('active');
        });
    });
});

destinatarios.forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');
    const options = dropdown.querySelectorAll('.menu li');
    const selected = dropdown.querySelector('.selected');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });

    options.forEach(option => {
        option.addEventListener('click', () => {
            selected.value = option.innerText;
            select.classList.remove('select-clicked');
            caret.classList.remove('caret-rotate');
            menu.classList.remove('menu-open');

            options.forEach(option => {
                option.classList.remove('active');
            });
            option.classList.add('active');
        });
    });
});

modalidad.forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');
    const options = dropdown.querySelectorAll('.menu li');
    const selected = dropdown.querySelector('.selected');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });

    options.forEach(option => {
        option.addEventListener('click', () => {
            selected.value = option.innerText;
            select.classList.remove('select-clicked');
            caret.classList.remove('caret-rotate');
            menu.classList.remove('menu-open');

            options.forEach(option => {
                option.classList.remove('active');
            });
            option.classList.add('active');
        });
    });
});

function text(x) {
    if (x !== 0) {
        document.getElementById('ubicacion').style.display = 'inherit';
    } else {
        document.getElementById('ubicacion').style.display = 'none';
    }
}

let inputForImage = document.getElementById("image");

function preview(event) {
    let input = event.target;
    let preview = document.getElementById('previewImage');
    let labelLoadImage = document.getElementById('labelLoadImage');
    let loadImageFooter = document.getElementById("loadImageFooter");

    if (input.files && input.files[0]) {
        let reader = new FileReader();
        reader.onload = function (e) {
            preview.src = e.target.result;
            //labelLoadImage.classList.toggle("display-none");
            labelLoadImage.style.display = "none";
            //loadImageFooter.classList.toggle("display-flex");
            loadImageFooter.style.display = "flex"
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        preview.src = '#';
        //labelLoadImage.classList.toggle("display-flex");
        labelLoadImage.style.display = "flex";
        //loadImageFooter.classList.toggle("display-none");
        loadImageFooter.style.display = "none"

    }
}

inputForImage.addEventListener("change", preview);

const formulario = document.getElementById("formulario");
const alert_error_fields = document.getElementById("alert-error-fields");
const alert_error_hour = document.getElementById("alert-error-hour");

const validateForm = (event) => {
    event.preventDefault();
    const objForm = new FormData(formulario);
    let formValid = true;

    if(objForm.get("title") === "") formValid = false;
    if(objForm.get("instructor") === "") formValid = false;
    if(objForm.get("organizedBy") === "") formValid = false;
    if(objForm.get("status") === "") formValid = false;
    if(objForm.get("date") === "") formValid = false;
    if(objForm.get("startTime") === "") formValid = false;
    if(objForm.get("endingTime") === "") formValid = false;
    if(objForm.get("offeredBySemillero") === "") formValid = false;
    if(objForm.get("approach") === "") formValid = false;
    if(objForm.get("type") === "") formValid = false;
    if(objForm.get("origin") === "") formValid = false;
    if(objForm.get("destination") === "") formValid = false;
    if(objForm.get("modality") === "") formValid = false;
    if(objForm.get("modality") === "Presencial" && objForm.get("location") === "") formValid = false;

    if (formValid) {
        let timeValid = true;
        if(objForm.get("startTime") >= objForm.get("endingTime")) timeValid = false;

        if(timeValid){
            formulario.submit();
        }else{
            alert_error_hour.classList.add("active")
            setTimeout(() => {
                alert_error_hour.classList.remove('active')
            }, 2000);
        }
    } else {
        alert_error_fields.classList.add("active")
        setTimeout(() => {
            alert_error_fields.classList.remove('active')
        }, 2000);
    }

}

formulario.addEventListener("submit", validateForm);