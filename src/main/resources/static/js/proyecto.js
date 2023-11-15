function goBack() {
    history.back()
}

const getSeedsByRol = (rol) => {
    return seeds.filter(seed => seed.rol === rol);
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
const tab_no_editable_aliado = document.getElementById("tab_no_editable_aliado");
const tab_editable_aliado = document.getElementById("tab_editable_aliado");
const tab_no_editable_proposito = document.getElementById("tab_no_editable_proposito");
const tab_editable_proposito = document.getElementById("tab_editable_proposito");
const tab_no_editable_tipo = document.getElementById("tab_no_editable_tipo");
const tab_editable_tipo = document.getElementById("tab_editable_tipo");
const tab_no_editable_cronograma = document.getElementById("tab_no_editable_cronograma");
const tab_editable_cronograma = document.getElementById("tab_editable_cronograma");
const tab_no_editable_estado = document.getElementById("tab_no_editable_estado");
const tab_editable_estado = document.getElementById("tab_editable_estado");
const tab_no_editable_gestion = document.getElementById("tab_no_editable_gestion");
const tab_editable_gestion = document.getElementById("tab_editable_gestion");

const handleShowEdit = (n, cancel = false) => {
    event.preventDefault();

    if (n === 0) {
        edit_observations.classList.toggle("deactiveShow");
        if (with_observations) with_observations.classList.toggle("deactiveShow");
        if (without_observations) without_observations.classList.toggle("deactiveShow");
        if (cancel) {
            document.getElementsByName('observations')[0].value = project.observations ? project.observations : '';
        }
    }
    if (n === 1) {
        tab_no_editable_aliado.classList.toggle("deactiveShow");
        tab_editable_aliado.classList.toggle("deactiveShow");
        if (cancel) {
            document.getElementsByName("ally")[0].value = project.ally;
            document.getElementsByName("ods")[0].value = project.ods;
            document.getElementsByName("originPlace")[0].value = project.originPlace;
            document.getElementsByName("timestamp")[0].value = project.timestamp;
            document.getElementsByName("responsible")[0].value = project.responsible;
            document.getElementsByName("address")[0].value = project.address;
            document.getElementsByName("responsiblePhoneNumber")[0].value = project.responsiblePhoneNumber;
            document.getElementsByName("responsibleEmail")[0].value = project.responsibleEmail;
            document.getElementById("timestamp-dateTime-input").value = project.timestamp;
        }
    }
    if (n === 2) {
        tab_no_editable_proposito.classList.toggle("deactiveShow");
        tab_editable_proposito.classList.toggle("deactiveShow");
        if (cancel) {
            document.getElementsByName("purpose")[0].value = project.purpose;
        }
    }
    if (n === 3) {
        tab_no_editable_tipo.classList.toggle("deactiveShow");
        tab_editable_tipo.classList.toggle("deactiveShow");
        if (cancel) {
            document.getElementsByName("projectType").forEach((element) => {
                element.checked = project.projectType === element.value;
            })
        }
    }
    if (n === 4) {
        tab_no_editable_cronograma.classList.toggle("deactiveShow");
        tab_editable_cronograma.classList.toggle("deactiveShow");
        if (cancel) {
            document.getElementsByName("estimatedTime")[0].value = project.estimatedTime;
            document.getElementsByName("initialDate")[0].value = project.initialDate;
            document.getElementsByName("endingDate")[0].value = project.endingDate;
        }
    }
    if (n === 5) {
        tab_no_editable_estado.classList.toggle("deactiveShow");
        tab_editable_estado.classList.toggle("deactiveShow");
        if (cancel) {
            if(project.projectStage === "Relevamiento y reuniones iniciales"){editStatus(1)}
            if(project.projectStage === "Desarrollo de Aplicación"){editStatus(2)}
            if(project.projectStage === "Armado de testimoniales"){editStatus(3)}
            if(project.projectStage === "Demo final en servidor de prueba"){editStatus(4)}
            if(project.projectStage === "Propuesta con relevamiento inicial que no se inicia"){editStatus(5)}
            if(project.projectStage === "Proyecto pausado por pedido"){editStatus(6)}
            if(project.projectStage === "Difusión de testimoniales realizada"){editStatus(7)}
            if(project.projectStage === "Intercambio de reciprocidad"){editStatus(8)}
            if(project.projectStage === "Medición de impacto a largo plazo"){editStatus(9)}
            if(project.projectStage === "Propuesta no pasó a relevamiento"){editStatus(10)}
            if(project.projectStage === "Propuesta dada de baja en relevamiento"){editStatus(11)}
            if(project.projectStage === "Propuesta no se inicia, sin relevamiento inicial y ausencia de PO"){editStatus(12)}

        }
    }
    if (n === 6) {
        tab_no_editable_gestion.classList.toggle("deactiveShow");
        tab_editable_gestion.classList.toggle("deactiveShow");
        if (cancel) {
            document.getElementsByName("productOwnerFullName")[0].value = project.productOwnerFullName;
            document.getElementsByName("productOwnerDiscordUser")[0].value = project.productOwnerDiscordUser;
            document.getElementsByName("productOwnerTelephone")[0].value = project.productOwnerTelephone;
            document.getElementsByName("productOwnerEmail")[0].value = project.productOwnerEmail;
            document.getElementsByName("projectManagerFullName")[0].value = project.projectManagerFullName;
            document.getElementsByName("projectManagerDiscordUser")[0].value = project.projectManagerDiscordUser;
            document.getElementsByName("projectManagerTelephone")[0].value = project.projectManagerTelephone;
            document.getElementsByName("projectManagerEmail")[0].value = project.projectManagerEmail;
            document.getElementsByName("teamLeaderFullName")[0].value = project.teamLeaderFullName;
            document.getElementsByName("teamLeaderDiscordUser")[0].value = project.teamLeaderDiscordUser;
            document.getElementsByName("teamLeaderTelephone")[0].value = project.teamLeaderTelephone;
            document.getElementsByName("teamLeaderEmail")[0].value = project.teamLeaderEmail;
        }
    }
}

/* MODIFICAR ESTADO */
const input_modified_status = document.getElementById("input-modified-status");
const input_modified_secondary_status = document.getElementById("input-modified-secondary-status");
const input_modified_primary_status = document.getElementById("input-modified-primary-status");

const editStatus = (n) => {
    if (n >= 1 && n <= 4) {
        input_modified_primary_status.value = "Activo";
        input_modified_secondary_status.value = n === 1 ?
            "Relevamiento y reuniones iniciales" : (n === 2 ?
                "Desarrollo de Aplicación" : (n === 3 ?
                    "Armado de testimoniales" : "Demo final en servidor de prueba"));
        input_modified_status.classList.add("green");
        input_modified_status.classList.remove("blue", "red", "black");
    }
    if (n === 5 || n === 6) {
        input_modified_primary_status.value = "Pausado";
        input_modified_secondary_status.value = n === 5 ?
            "Propuesta con relevamiento inicial que no se inicia" : "Proyecto pausado por pedido";
        input_modified_status.classList.add("black");
        input_modified_status.classList.remove("blue", "red", "green");
    }
    if (n >= 7 && n <= 9) {
        input_modified_primary_status.value = "Cerrado";
        input_modified_secondary_status.value = n === 7 ?
            "Difusión de testimoniales realizada" : (n === 8 ?
                "Intercambio de reciprocidad" : "Medición de impacto a largo plazo");
        input_modified_status.classList.add("blue");
        input_modified_status.classList.remove("green", "red", "black");
    }
    if (n >= 10 && n <= 12) {
        input_modified_primary_status.value = "Descartado";
        input_modified_secondary_status.value = n === 10 ?
            "Propuesta no pasó a relevamiento" : (n === 11 ?
                "Propuesta dada de baja en relevamiento" : "Propuesta no se inicia, sin relevamiento inicial y ausencia de PO");
        input_modified_status.classList.add("red");
        input_modified_status.classList.remove("blue", "green", "black");
    }
    input_modified_status.value = `${input_modified_primary_status.value} - ${input_modified_secondary_status.value}`
}

//---------------------------------------------EDITAR MARCA TEMPORAL-----------------------------
const timestampDateTimeInput = document.getElementById("timestamp-dateTime-input");
const timestampDateTimeInputBehind = document.getElementById("timestamp-dateTime-input-behind");

const handleTimestampChange = (e) => {
    timestampDateTimeInputBehind.value = e.target.value;
}

timestampDateTimeInput.addEventListener("change", handleTimestampChange);

/* AUTO HEIGHT VALOR */
document.querySelector('.tab-valor')

//---------------------------------------TABLAS---------------------------------------------------------
let tabla_equipo_back = document.getElementById("tabla-equipo-back");
let tabla_equipo_front = document.getElementById("tabla-equipo-front");
let tabla_equipo_uxui = document.getElementById("tabla-equipo-uxui");
let tabla_equipo_scrum = document.getElementById("tabla-equipo-scrum");
let tabla_equipo_qa = document.getElementById("tabla-equipo-qa");
let tabla_equipo_analista = document.getElementById("tabla-equipo-analista");
let tabla_equipo_infra = document.getElementById("tabla-equipo-infra");

let list_of_backend = getSeedsByRol("Backend");
let list_of_frontend = getSeedsByRol("Frontend");
let list_of_qa = getSeedsByRol("QA");
let list_of_ux_ui_ixd = getSeedsByRol("UX/UI/IxD");
let list_of_scrum = getSeedsByRol("Scrum");
let list_of_analista = getSeedsByRol("Analista");
let list_of_infra = getSeedsByRol("Infraestructura");

if (list_of_backend.length > 0) {
    let tabla_equipo_back_body = document.createElement("tbody");

    for (seed of list_of_backend) {
        let tabla_equipo_back_row = document.createElement("tr");
        tabla_equipo_back_row.innerHTML = `
          <td><a href=/seed/item/${seed.id}>${seed.firstName}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.discordUser}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.telephone}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.email}</a></td>`

        tabla_equipo_back_body.appendChild(tabla_equipo_back_row);
    }
    tabla_equipo_back.appendChild(tabla_equipo_back_body);
} else {
    let tabla_equipo_back_footer = document.createElement("tfoot");
    tabla_equipo_back_footer.innerHTML = "<tr><td>SIN MIEMBROS</td><td></td><td></td><td></td></tr>"

    tabla_equipo_back.appendChild(tabla_equipo_back_footer);
}

if (list_of_frontend.length > 0) {
    let tabla_equipo_front_body = document.createElement("tbody");

    for (seed of list_of_frontend) {
        let tabla_equipo_front_row = document.createElement("tr");
        tabla_equipo_front_row.innerHTML = `
          <td><a href=/seed/item/${seed.id}>${seed.firstName}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.discordUser}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.telephone}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.email}</a></td>`

        tabla_equipo_front_body.appendChild(tabla_equipo_front_row);
    }
    tabla_equipo_front.appendChild(tabla_equipo_front_body);
} else {
    let tabla_equipo_front_footer = document.createElement("tfoot");
    tabla_equipo_front_footer.innerHTML = "<tr><td>SIN MIEMBROS</td><td></td><td></td><td></td></tr>"

    tabla_equipo_front.appendChild(tabla_equipo_front_footer);
}
if (list_of_ux_ui_ixd.length > 0) {
    let tabla_equipo_uxui_body = document.createElement("tbody");

    for (seed of list_of_ux_ui_ixd) {
        let tabla_equipo_uxui_row = document.createElement("tr");
        tabla_equipo_uxui_row.innerHTML = `
          <td><a href=/seed/item/${seed.id}>${seed.firstName}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.discordUser}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.telephone}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.email}</a></td>`

        tabla_equipo_uxui_body.appendChild(tabla_equipo_uxui_row);
    }
    tabla_equipo_uxui.appendChild(tabla_equipo_uxui_body);
} else {
    let tabla_equipo_uxui_footer = document.createElement("tfoot");
    tabla_equipo_uxui_footer.innerHTML = "<tr><td>SIN MIEMBROS</td><td></td><td></td><td></td></tr>"

    tabla_equipo_uxui.appendChild(tabla_equipo_uxui_footer);
}
if (list_of_scrum.length > 0) {
    let tabla_equipo_scrum_body = document.createElement("tbody");

    for (seed of list_of_scrum) {
        let tabla_equipo_scrum_row = document.createElement("tr");
        tabla_equipo_scrum_row.innerHTML = `
          <td><a href=/seed/item/${seed.id}>${seed.firstName}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.discordUser}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.telephone}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.email}</a></td>`

        tabla_equipo_scrum_body.appendChild(tabla_equipo_scrum_row);
    }
    tabla_equipo_scrum.appendChild(tabla_equipo_scrum_body);
} else {
    let tabla_equipo_scrum_footer = document.createElement("tfoot");
    tabla_equipo_scrum_footer.innerHTML = "<tr><td>SIN MIEMBROS</td><td></td><td></td><td></td></tr>"

    tabla_equipo_scrum.appendChild(tabla_equipo_scrum_footer);
}
if (list_of_qa.length > 0) {
    let tabla_equipo_qa_body = document.createElement("tbody");

    for (seed of list_of_qa) {
        let tabla_equipo_qa_row = document.createElement("tr");
        tabla_equipo_qa_row.innerHTML = `
          <td><a href=/seed/item/${seed.id}>${seed.firstName}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.discordUser}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.telephone}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.email}</a></td>`

        tabla_equipo_qa_body.appendChild(tabla_equipo_qa_row);
    }
    tabla_equipo_qa.appendChild(tabla_equipo_qa_body);
} else {
    let tabla_equipo_qa_footer = document.createElement("tfoot");
    tabla_equipo_qa_footer.innerHTML = "<tr><td>SIN MIEMBROS</td><td></td><td></td><td></td></tr>"

    tabla_equipo_qa.appendChild(tabla_equipo_qa_footer);
}
if (list_of_analista.length > 0) {
    let tabla_equipo_analista_body = document.createElement("tbody");

    for (seed of list_of_analista) {
        let tabla_equipo_analista_row = document.createElement("tr");
        tabla_equipo_analista_row.innerHTML = `
          <td><a href=/seed/item/${seed.id}>${seed.firstName}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.discordUser}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.telephone}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.email}</a></td>`

        tabla_equipo_analista_body.appendChild(tabla_equipo_analista_row);
    }
    tabla_equipo_analista.appendChild(tabla_equipo_analista_body);
} else {
    let tabla_equipo_analista_footer = document.createElement("tfoot");
    tabla_equipo_analista_footer.innerHTML = "<tr><td>SIN MIEMBROS</td><td></td><td></td><td></td></tr>"

    tabla_equipo_analista.appendChild(tabla_equipo_analista_footer);
}
if (list_of_infra.length > 0) {
    let tabla_equipo_infra_body = document.createElement("tbody");

    for (seed of list_of_infra) {
        let tabla_equipo_infra_row = document.createElement("tr");
        tabla_equipo_infra_row.innerHTML = `
          <td><a href=/seed/item/${seed.id}>${seed.firstName}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.discordUser}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.telephone}</a></td>
          <td><a href=/seed/item/${seed.id}>${seed.email}</a></td>`

        tabla_equipo_infra_body.appendChild(tabla_equipo_infra_row);
    }
    tabla_equipo_infra.appendChild(tabla_equipo_infra_body);
} else {
    let tabla_equipo_infra_footer = document.createElement("tfoot");
    tabla_equipo_infra_footer.innerHTML = "<tr><td>SIN MIEMBROS</td><td></td><td></td><td></td></tr>"

    tabla_equipo_infra.appendChild(tabla_equipo_infra_footer);
}

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
    url.pathname = `project/api/observation/remove/${id}`;
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
