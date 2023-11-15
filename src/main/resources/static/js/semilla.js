function goBack() {
    history.back()
}

/* OBSERVACIONES GENERALES */
document.querySelector('.observaciones-menu').addEventListener("click", (e) => {
    document.querySelector('.observaciones-menu').classList.toggle("active");
    document.querySelector('.observaciones-arrow').classList.toggle("active");
    document.querySelector('.observaciones-form-container').classList.toggle("active");
});

const edit_observations = document.getElementById("edit_observations");
const without_observations = document.getElementById("without_observations");
const with_observations = document.getElementById("with_observations");
const tab_no_editable_personal = document.getElementById("tab_no_editable_personal");
const tab_editable_personal = document.getElementById("tab_editable_personal");
const tab_no_editable_contacto = document.getElementById("tab_no_editable_contacto");
const tab_editable_contacto = document.getElementById("tab_editable_contacto");
const tab_no_editable_rol = document.getElementById("tab_no_editable_rol");
const tab_editable_rol = document.getElementById("tab_editable_rol");
const tab_no_editable_turn = document.getElementById("tab_no_editable_turn");
const tab_editable_turn = document.getElementById("tab_editable_turn");
const tab_no_editable_status = document.getElementById("tab_no_editable_status");
const tab_editable_status = document.getElementById("tab_editable_status");
const tab_no_editable_proyecto = document.getElementById("tab_no_editable_proyecto");
const tab_editable_proyecto = document.getElementById("tab_editable_proyecto");
const tab_no_editable_certificado = document.getElementById("tab_no_editable_certificado");
const tab_editable_certificado = document.getElementById("tab_editable_certificado");
const tab_no_editable_recomendada = document.getElementById("tab_no_editable_recomendada");
const tab_editable_recomendada = document.getElementById("tab_editable_recomendada");

const handleShowEdit = (n, cancel = false) => {
    event.preventDefault();
    if (n === 0) {
        tab_no_editable_personal.classList.toggle("deactiveShow");
        tab_editable_personal.classList.toggle("deactiveShow");
        if (cancel) {
            document.getElementsByName('firstName')[0].value = seed.firstName;
            document.getElementsByName('postulationDate')[0].value = seed.postulationDate;
            document.getElementsByName('city')[0].value = seed.city;
            document.getElementsByName('dni')[0].value = seed.dni;
            document.getElementsByName('gender')[0].value = seed.gender;
            document.getElementsByName('studies')[0].value = seed.studies;
            document.getElementsByName('birthDate')[0].value = seed.birthDate;
            document.getElementsByName('country')[0].value = seed.country;
            document.getElementsByName('meetSemilleroBy')[0].value = seed.meetSemilleroBy;
        }
    }
    if (n === 1) {
        tab_no_editable_contacto.classList.toggle("deactiveShow");
        tab_editable_contacto.classList.toggle("deactiveShow");
        if (cancel) {
            document.getElementsByName('telephone')[0].value = seed.telephone;
            document.getElementsByName('discordUser')[0].value = seed.discordUser;
            document.getElementsByName('email')[0].value = seed.email;
            document.getElementsByName('linkedin')[0].value = seed.linkedin;
        }
    }
    if (n === 2) {
        tab_no_editable_rol.classList.toggle("deactiveShow");
        tab_editable_rol.classList.toggle("deactiveShow");
        if (cancel) {
            document.getElementsByName('rol').forEach((element) => {
                element.checked = seed.rol === element.value;
            })
        }
    }
    if (n === 3) {
        tab_no_editable_turn.classList.toggle("deactiveShow");
        tab_editable_turn.classList.toggle("deactiveShow");
        if (cancel) {
            document.getElementsByName('turn').forEach((element) => {
                element.checked = seed.turn === element.value;
            })
        }
    }
    if (n === 4) {
        tab_no_editable_status.classList.toggle("deactiveShow");
        tab_editable_status.classList.toggle("deactiveShow");
        if (cancel) {
            document.getElementsByName('companyName')[0].value = seed.companyName;
            document.getElementById("input-empresa-estado").value = seed.companyName;
            document.getElementById("input-comentario-estado").value = seed.companyName;
            document.getElementsByName('startDate')[0].value = seed.startDate ? seed.startDate : null;
            document.getElementById("start-date-input").value = seed.startDate;
            document.getElementsByName('endDate')[0].value = seed.endDate ? seed.endDate : null;
            document.getElementById("end-date-input").value = seed.endDate;
            if (seed.secondary === "En espera de Proyecto") {
                editStatus(0);
            }
            if (seed.secondary === "Asignada a Proyecto") {
                editStatus(1);
            }
            if (seed.secondary === "No disponible temporalmente") {
                editStatus(2);
            }
            if (seed.secondary === "Consiguió trabajo en IT - Pasó a Quinto") {
                editStatus(3);
            }
            if (seed.secondary === "Consiguió trabajo en IT - Otra empresa") {
                editStatus(4);
            }
            if (seed.secondary === "Consiguió trabajo - No en IT") {
                editStatus(5);
            }
            if (seed.secondary === "Abandonó") {
                editStatus(6);
            }
            if (seed.primary === "Finalizó Proyecto") {
                editStatus(7);
            }
            if (seed.primary === "Redefinir") {
                editStatus(8);
            }
        }
    }
    if (n === 5) {
        tab_no_editable_proyecto.classList.toggle("deactiveShow");
        tab_editable_proyecto.classList.toggle("deactiveShow");
        if (cancel) {
            document.getElementsByName('projectNames').forEach((element) => {
                element.checked = seed.projectNames.indexOf(element.value) !== -1;
            })
        }
    }
    if (n === 6) {
        tab_no_editable_certificado.classList.toggle("deactiveShow");
        tab_editable_certificado.classList.toggle("deactiveShow");
        if (cancel) {
            document.getElementsByName('certificationString').forEach((element) => {
                element.checked = seed.certificationString === element.value;
            })
        }
    }
    if (n === 7) {
        tab_no_editable_recomendada.classList.toggle("deactiveShow");
        tab_editable_recomendada.classList.toggle("deactiveShow");
        if (cancel) {
            document.getElementsByName('recommendationComment')[0].value = seed.recommendationComment ? seed.recommendationComment : null ;
            document.getElementsByName('recommendationString').forEach((element) => {
                element.checked = seed.recommendationString === element.value;
            })
        }
    }
    if (n === 8) {
        edit_observations.classList.toggle("deactiveShow");
        if (with_observations) with_observations.classList.toggle("deactiveShow");
        if (without_observations) without_observations.classList.toggle("deactiveShow");
        if (cancel) {
            document.getElementsByName('observation')[0].value = seed.observation;
        }
    }
}

/* INPUTS DESPLEGABLES */
const genero = document.querySelectorAll('.gender');
const howMeet = document.querySelectorAll('.how-meet');

genero.forEach(dropdown => {
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

howMeet.forEach(dropdown => {
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


/* AUTO HEIGHT VALOR */
document.querySelector('.tab-valor')

/* MODIFICAR ESTADO */
const editStatus = (n) => {
    event.preventDefault();
    const realInputPrimary = document.getElementById("real-input-primary");
    const realInputSecondary = document.getElementById("real-input-secondary");
    const inputModifiedStatus = document.getElementById("input-modified-status");
    const boxStatusComments = document.getElementById("box-status-comments");
    const boxEnterPriseName = document.getElementById("box-enterprise-name");
    const seed_start_date_div = document.getElementById("seed_start_date_div");
    const seed_end_date_div = document.getElementById("seed_end_date_div");

    if (n === 0 || n === 1 || n === 2) {
        realInputPrimary.value = "Activa";
        realInputSecondary.value = n === 0 ? "En espera de Proyecto" :
            (n === 1 ? "Asignada a Proyecto" : "No disponible temporalmente");
            inputModifiedStatus.value = `${realInputPrimary.value} - ${realInputSecondary.value}`
            inputModifiedStatus.classList.add("green");
            inputModifiedStatus.classList.remove("blue", "red", "black");
        n === 2 ? boxStatusComments.style.display = "flex" : boxStatusComments.style.display = "none";
        boxEnterPriseName.style.display = "none";
        n === 1 ? seed_start_date_div.style.display = "flex" : seed_start_date_div.style.display = "none";
        seed_end_date_div.style.display = "none";
    }
    if (n === 3 || n === 4) {
        realInputPrimary.value = "Inactiva";
        realInputSecondary.value = `Consiguió trabajo en IT - ${n === 3 ? 
            "Pasó a Quinto" : "Otra empresa"}`;
            inputModifiedStatus.value = `${realInputPrimary.value} - ${realInputSecondary.value}`
            inputModifiedStatus.classList.add("blue");
            inputModifiedStatus.classList.remove("green", "red", "black");
        boxStatusComments.style.display = "none";
        n === 4 ? boxEnterPriseName.style.display = "flex" : boxEnterPriseName.style.display = "none";
        seed_start_date_div.style.display = "none";
        seed_end_date_div.style.display = "flex";
    }
    if (n === 5 || n === 6) {
        realInputPrimary.value = "Inactiva";
        realInputSecondary.value = n === 5 ? "Consiguió trabajo - No en IT" : "Abandonó";

            inputModifiedStatus.value = `${realInputPrimary.value} - ${realInputSecondary.value}`
            inputModifiedStatus.classList.add("red");
            inputModifiedStatus.classList.remove("green", "blue", "black");

        boxStatusComments.style.display = "flex";
        boxEnterPriseName.style.display = "none";
        seed_start_date_div.style.display = "none";
        seed_end_date_div.style.display = "flex";
    }
    if (n === 7 || n === 8) {
        realInputPrimary.value = n === 7 ? "Finalizó Proyecto" : "Redefinir";
        realInputSecondary.value = "";

            inputModifiedStatus.value = `${realInputPrimary.value}`
            n === 7 ? inputModifiedStatus.classList.add("blue") : inputModifiedStatus.classList.add("black");
            n === 7 ? inputModifiedStatus.classList.remove("green", "black", "red")
                : inputModifiedStatus.classList.remove("green", "blue", "red");

        boxStatusComments.style.display = "flex";
        boxEnterPriseName.style.display = "none";
        seed_start_date_div.style.display = "none";
        n === 7 ? seed_end_date_div.style.display = "flex" : seed_end_date_div.style.display = "none";
    }
}
//-----------------------------------------------------------------------------------------------------------------------
const postulationDateTimeInput = document.getElementById("postulation-dateTime-input");
const postulationDateTimeInputBehind = document.getElementById("postulation-dateTime-input-behind");

const handlePostulationDateChange = (e) => {
    postulationDateTimeInputBehind.value = e.target.value;
}

postulationDateTimeInput.addEventListener("change", handlePostulationDateChange);
//-----------------------------------------------------------------------------------------------------------------------
const seed_start_date_behind = document.getElementById("seed_start_date_behind");
const seed_end_date_behind = document.getElementById("seed_end_date_behind");
const seed_start_date = document.getElementById("start-date-input");
const seed_end_date = document.getElementById("end-date-input");

const handleStartDateChange = (e) => {
    seed_start_date_behind.value = e.target.value;
}
const handleEndDateChange = (e) => {
    seed_end_date_behind.value = e.target.value;
}

seed_start_date.addEventListener("change", handleStartDateChange);
seed_end_date.addEventListener("change", handleEndDateChange);
//-----------------------------------------------------------------------------------------------------------------------
const realCompanyNameInput = document.getElementById("realCompanyNameInput");
const inputComentarioEstado = document.getElementById("input-comentario-estado");
const inputEmpresaEstado = document.getElementById("input-empresa-estado");

const handleCompanyNameChange = (e) => {
    realCompanyNameInput.value = e.target.value;
}

inputComentarioEstado.addEventListener("change", handleCompanyNameChange);
inputEmpresaEstado.addEventListener("change", handleCompanyNameChange);
//-----------------------------------------------------------------------------------------------------------------------
const delete_warn_container = document.getElementById("delete-warn-container");
const delete_success_container = document.getElementById("delete-success-container");

function handleWarn() {
    delete_warn_container.classList.toggle("active");
}

function alertSuccess() {
    delete_success_container.classList.add("active");

    setTimeout(() => {
        window.location.reload();
    }, 2000)
}

function handleDelete(id) {
    const url = new URL(window.location.href);
    url.pathname = `seed/api/observation/remove/${id}`;
    const options = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
    };

    fetch(url, options);
    handleWarn();
    alertSuccess();
}