function goBack(){history.back()}

/* OBSERVACIONES GENERALES */
document.querySelector('.observaciones-menu').addEventListener("click", (e) => {
    document.querySelector('.observaciones-menu').classList.toggle("active");
    document.querySelector('.observaciones-arrow').classList.toggle("active");
    document.querySelector('.observaciones-form-container').classList.toggle("active");
});

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
const editStatus = (n) =>{
    event.preventDefault();
    let realInputPrimary = document.getElementById("real-input-primary");
    let realInputSecondary = document.getElementById("real-input-secondary");
    let inputModifiedStatus = document.getElementById("input-modified-status");
    let inputModifiedStatusContainer = document.getElementById("input-modified-status-container");
    let boxStatusComments = document.getElementById("box-status-comments");
    let boxEnterPriseName = document.getElementById("box-enterprise-name");

    if(n === 0 || n === 1 || n === 2){
        if(realInputPrimary)realInputPrimary.value = "Activa";
        if(realInputSecondary)realInputSecondary.value = event.target.innerText;
        if(inputModifiedStatus){
            inputModifiedStatus.value = `${realInputPrimary.value} - ${realInputSecondary.value}`
            inputModifiedStatus.classList.add("green");
            inputModifiedStatus.classList.remove("blue","red","black");
        }
        if(boxStatusComments) n === 2 ? boxStatusComments.style.display = "flex" : boxStatusComments.style.display = "none";
        if(boxEnterPriseName)boxEnterPriseName.style.display = "none";
    }
    if(n === 3 || n === 4){
        if(realInputPrimary)realInputPrimary.value = "Inactiva";
        if(realInputSecondary)realInputSecondary.value = `Consiguió trabajo en IT - ${event.target.innerText}`;
        if(inputModifiedStatus){
            inputModifiedStatus.value = `${realInputPrimary.value} - ${realInputSecondary.value}`
            inputModifiedStatus.classList.add("blue");
            inputModifiedStatus.classList.remove("green","red","black");
        }
        if(boxStatusComments)boxStatusComments.style.display = "none";
        if(boxEnterPriseName)n === 4 ? boxEnterPriseName.style.display = "flex" : boxEnterPriseName.style.display = "none";
    }
    if(n === 5 || n === 6){
        if(realInputPrimary)realInputPrimary.value = "Inactiva";
        if(realInputSecondary)realInputSecondary.value = n === 5 ? "Consiguió trabajo - No en IT" : "Abandonó";
        if(inputModifiedStatus){
            inputModifiedStatus.value = `${realInputPrimary.value} - ${realInputSecondary.value}`
            inputModifiedStatus.classList.add("red");
            inputModifiedStatus.classList.remove("green","blue","black");
        }
        if(boxStatusComments)boxStatusComments.style.display = "flex";
        if(boxEnterPriseName)boxEnterPriseName.style.display = "none";
    }
    if(n === 7 || n === 8){
        if(realInputPrimary)realInputPrimary.value = n === 7 ? "Finalizó Proyecto" : "Redefinir";
        if(realInputSecondary)realInputSecondary.value = "";
        if(inputModifiedStatus){
            inputModifiedStatus.value = `${realInputPrimary.value}`
            n === 7 ? inputModifiedStatus.classList.add("blue") : inputModifiedStatus.classList.add("black");
            n === 7 ? inputModifiedStatus.classList.remove("green","black","red")
                : inputModifiedStatus.classList.remove("green","blue","red");
        }
        if(boxStatusComments)boxStatusComments.style.display = "flex";
        if(boxEnterPriseName)boxEnterPriseName.style.display = "none";
    }
}
//-----------------------------------------------------------------------------------------------------------------------
const postulationDateTimeInput = document.getElementById("postulation-dateTime-input");
const postulationDateTimeInputBehind = document.getElementById("postulation-dateTime-input-behind");

const handlePostulationDateChange = (e) =>{
    postulationDateTimeInputBehind.value = e.target.value;
    console.log(postulationDateTimeInputBehind.value);
}

if(postulationDateTimeInput)postulationDateTimeInput.addEventListener("change", handlePostulationDateChange);
//-----------------------------------------------------------------------------------------------------------------------
const realCompanyNameInput = document.getElementById("realCompanyNameInput");
const inputComentarioEstado = document.getElementById("input-comentario-estado");
const inputEmpresaEstado = document.getElementById("input-empresa-estado");

const handleCompanyNameChange = (e) =>{
    realCompanyNameInput.value = e.target.value;
}

if(inputComentarioEstado)inputComentarioEstado.addEventListener("change", handleCompanyNameChange);
if(inputEmpresaEstado)inputEmpresaEstado.addEventListener("change", handleCompanyNameChange);
