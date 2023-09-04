const d = document;

function goBack(){history.back()}

//Manejo de la alerta para confirmar
function handleWarn (){
    let alert = d.getElementById("delete-warn-container");
    alert.classList.toggle("active");
}

//Manejo de la alerta de éxito
function handleSuccess (){
    let alert = d.getElementById("delete-success-container");
    alert.classList.toggle("active");

    setTimeout(()=>{
        window.location.href = "./list";
    }, 2000);
}

//Manejo de la eliminación de un evento
function handleDelete (id){
    const url = `http://localhost:8080/event/delete/${id}`;
    const options = {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
    };

    fetch(url, options)
        .then(res => res.json())
        .then(data => console.log(data))

    handleWarn();
    handleSuccess();
}

//---------------IMPORTACiÓN------------------------------
class Attendance {
    constructor(dni, fullName, timestamp) {
        this.dni = dni;
        this.fullName = fullName;
        this.timestamp = timestamp;
    }
}

const importAttendances = d.getElementById("importarAsistentesArchivo");

const handleChange = async () => {

    const refactorDate = (date) =>{
        let fechaOriginal = new Date(date);
        return new Date(fechaOriginal.getUTCFullYear(),
            fechaOriginal.getUTCMonth(),
            fechaOriginal.getUTCDate(),
            fechaOriginal.getUTCHours(),
            fechaOriginal.getUTCMinutes());
    }

    let content = await readXlsxFile(importAttendances.files[0]);
    let realContent = content.splice(14, content.length);
    let attendancesToImport = [];

    for (let i = 0; i < realContent.length; i++) {
        let newAttendance = new Attendance(realContent[i][2], realContent[i][1], refactorDate(realContent[i][0]));
        attendancesToImport.push(newAttendance);
    }

   if (attendancesToImport.length > 0) {
       let mainContainer = d.getElementById("main-event-container-js");
        const url = `http://localhost:8080/attendance-list/import/${mainContainer.getAttribute('data-id')}`;
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(attendancesToImport)
        };

        fetch(url, options)
            .then(response => response.json())
            .then(data => {
                console.log("Respuesta del servidor:", data);
            })
            .catch(error => {
                console.error("Error en la solicitud:", error);
            });

        d.querySelector('.importacion-exito-container').classList.add('active')
        setTimeout(() => {
            d.querySelector('.importacion-exito-container').classList.remove('active')
            window.location.href = new URL(`/attendance-list/list/${mainContainer.getAttribute("data-id")}`
                , new URL(window.location.href).origin).href;
        }, 2000);

    } else {
        d.querySelector('.importacion-error-container').classList.add('active')
        setTimeout(() => {
            d.querySelector('.importacion-error-container').classList.remove('active')
        }, 2000);
    }
};

if(importAttendances)importAttendances.addEventListener("change", handleChange);