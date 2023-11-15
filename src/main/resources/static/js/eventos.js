const d = document;
const params = new URLSearchParams(new URL(window.location.href).search);

//Arreglo para obtener el mes
const meses = {
    "01": 'Enero',
    "02": 'Febrero',
    "03": 'Marzo',
    "04": 'Abril',
    "05": 'Mayo',
    "06": 'Junio',
    "07": 'Julio',
    "08": 'Agosto',
    "09": 'Septiembre',
    "10": 'Octubre',
    "11": 'Noviembre',
    "12": 'Diciembre'
};

//----------------------------------------FILTROS------------------------------------------
d.querySelectorAll('.filtro-estado').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-facilitado').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-enfoque').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-tipo').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-origen').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-destinat').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-modalidad').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-evento-fecha').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-cantidad-asistentes').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

let real_facilitado = d.getElementById("realFacilitado");
let facilitado_si = d.getElementById("facilitadosi");
let facilitado_no = d.getElementById("facilitadono");

function handleChangeFacilitado() {
    real_facilitado.value = event.target.value;
}

if (facilitado_si) facilitado_si.addEventListener("change", handleChangeFacilitado);
if (facilitado_no) facilitado_no.addEventListener("change", handleChangeFacilitado);

function showBackOption() {
    let aBrowseBack = d.getElementById("a-browse-back");
    let h2BrowseResult = d.getElementById("h2-browse-result");
    let limpiar_filtros = d.getElementById("limpiar-filtros");

    if (aBrowseBack) aBrowseBack.style.display = "flex";
    if (h2BrowseResult) h2BrowseResult.style.display = "flex";
    if(limpiar_filtros) limpiar_filtros.style.display = "flex";
}

//----------------------------CARGAR DATOS------------------------------------------------
const eventsContainerTop = d.getElementById("events-container-top");

//Calcula la cantidad de eventos según el enfoque por año
const calcEventsByScope = (events, scope) => {
    let cantidad = 0;
    for (const event of events) {
        if (event.approach === scope) cantidad++;
    }
    return cantidad;
}

//Calcula la cantidad total de eventos por mes
const calcTotalEventsByMonth = (month, events) => {
    let cantidad = 0;
    for (const event of events) {
        let parts = event.date.split("-");
        if (parts[1] === month) cantidad++;
    }
    return cantidad;
}

//calcula la cantidad de eventos por enfoque en el mes
const calcEventsByScopeByMonth = (month, events, scope) => {
    let cantidad = 0;
    for (const event of events) {
        let parts = event.date.split("-");
        if (parts[1] === month) {
            if (event.approach === scope) cantidad++;
        }
    }
    return cantidad;
}

const getTotalEventsByMonth = (month, events_by_year, scope) => {
    let array = [];
    for (const event of events_by_year) {
        if (event.approach === scope) {
            let parts = event.date.split("-");
            if (parts[1] === month) array.push(event);
        }
    }

    array.sort((a, b) => {
        const dateA = new Date(a.date);
        const dateB = new Date(b.date);
        return dateA - dateB;
    });
    return array;
}

function get_events_by_scope(scope) {
    let array = [];
    for (const event of events) {
        if (event.approach === scope) array.push(event);
    }

    array.sort((a, b) => {
        const dateA = new Date(a.date);
        const dateB = new Date(b.date);
        if (dateB.getFullYear() !== dateA.getFullYear()) {
            return dateB.getFullYear() - dateA.getFullYear();
        } else if (dateA.getMonth() !== dateB.getMonth()) {
            return dateA.getMonth() - dateB.getMonth();
        } else {
            return dateA.getDate() - dateB.getDate();
        }
    });
    return array;
}

//Utilizado para no repetir código en handleChecked y handleCheckedLast
const loadAllEvents = (year_selected) => {
    let events_by_year = [];

    for (const e of events) {
        let parts = e.date.split("-");
        let date = new Date(parts[0], parts[1] - 1, parts[2]);
        if (date.getFullYear() == year_selected) {
            events_by_year.push(e);
        }
    }

    let tab_events_by_year_container = d.getElementById("tab-events");
    if (tab_events_by_year_container) {
        tab_events_by_year_container.innerHTML = "";

        //Creacion del contenedor que indica la cantidad de eventos
        let div_container_total_events_spans = d.createElement("div");
        div_container_total_events_spans.className = "total-eventos-year";

        let span_total_events_by_year = d.createElement("span");
        span_total_events_by_year.className = "big-count-year";
        span_total_events_by_year.innerHTML = `Total de eventos: ${events_by_year.length}`;

        let span_total_events_by_year_formacion = d.createElement("span");
        span_total_events_by_year_formacion.innerHTML = `Formación: ${calcEventsByScope(events_by_year, "Formación")}`

        let span_total_events_by_year_integracion = d.createElement("span");
        span_total_events_by_year_integracion.innerHTML = `Integración: ${calcEventsByScope(events_by_year, "Integración Social")}`

        let span_total_events_by_year_sensibilizacion = d.createElement("span");
        span_total_events_by_year_sensibilizacion.innerHTML = `Sensibilización: ${calcEventsByScope(events_by_year, "Sensibilización")}`

        div_container_total_events_spans.appendChild(span_total_events_by_year);
        div_container_total_events_spans.appendChild(span_total_events_by_year_formacion);
        div_container_total_events_spans.appendChild(span_total_events_by_year_integracion);
        div_container_total_events_spans.appendChild(span_total_events_by_year_sensibilizacion);

        tab_events_by_year_container.appendChild(div_container_total_events_spans);
    }
    let months_events_by_year = new Set();
    for (const event_by_year of events_by_year) {
        let parts = event_by_year.date.split("-");
        months_events_by_year.add(parts[1]);
    }

    let months_events_by_year_array = Array.from(months_events_by_year);
    months_events_by_year_array.sort((a, b) => b - a);

    for (const month of months_events_by_year_array) {
        let newEventsByMonthContainer = d.createElement("div");
        newEventsByMonthContainer.className = "eventos-mes-container";

        let newEventsByMonthSection = d.createElement("section");
        newEventsByMonthSection.className = "mes";

        let newTitleMonth = d.createElement("div");
        newTitleMonth.className = "title";

        let newTitleMonthSpan = d.createElement("span");
        newTitleMonthSpan.innerHTML = meses[month];

        let newTotalEventsContainer = d.createElement("div");
        newTotalEventsContainer.className = "total-eventos-mes";

        let newTotalEventsSpan = d.createElement("span");
        newTotalEventsSpan.className = "big-count-mes";
        newTotalEventsSpan.innerHTML = `Total de eventos: ${calcTotalEventsByMonth(month, events_by_year)}`;

        let newTotalFEventsSpan = d.createElement("span");
        newTotalFEventsSpan.innerHTML = `Formación: ${calcEventsByScopeByMonth(month, events_by_year, "Formación")}`;

        let newTotalIEventsSpan = d.createElement("span");
        newTotalIEventsSpan.innerHTML = `Integración: ${calcEventsByScopeByMonth(month, events_by_year, "Integración Social")}`;

        let newTotalSEventsSpan = d.createElement("span");
        newTotalSEventsSpan.innerHTML = `Sensibilización: ${calcEventsByScopeByMonth(month, events_by_year, "Sensibilización")}`;

        newTotalEventsContainer.appendChild(newTotalEventsSpan);
        newTotalEventsContainer.appendChild(newTotalFEventsSpan);
        newTotalEventsContainer.appendChild(newTotalIEventsSpan);
        newTotalEventsContainer.appendChild(newTotalSEventsSpan);

        newEventsByMonthSection.appendChild(newTitleMonth);
        newEventsByMonthSection.appendChild(newTotalEventsContainer);

        newTitleMonth.appendChild(newTitleMonthSpan);

        newEventsByMonthContainer.appendChild(newEventsByMonthSection);

        //Creamos las diferentes secciones según el enfoque
        //Enfoque de formacion
        let events_formacion_by_month = getTotalEventsByMonth(month, events_by_year, "Formación");

        let newFEventsSection = d.createElement("section");
        newFEventsSection.className = "formacion";

        let newFEventsTitleContainer = d.createElement("div");
        newFEventsTitleContainer.className = "title";

        let newFEventsTitleSpan = d.createElement("span");
        newFEventsTitleSpan.innerHTML = "Formación";

        newFEventsTitleContainer.appendChild(newFEventsTitleSpan);

        newFEventsSection.appendChild(newFEventsTitleContainer);

        if (events_formacion_by_month.length === 0) {

            let noEventsContainer = d.createElement("div");
            noEventsContainer.className = "no-eventos";

            let noEventsSpan = d.createElement("span");
            noEventsSpan.innerHTML = "No hay eventos";

            noEventsContainer.appendChild(noEventsSpan);
            newFEventsSection.appendChild(noEventsContainer);

        } else {

            for (const event_f of events_formacion_by_month) {
                let newEventLink = d.createElement("a");
                newEventLink.className = "formacion-evento";
                newEventLink.href = `/event/${event_f.id}`;

                let newEventTitleSpan = d.createElement("span");
                newEventTitleSpan.className = "evento-title";
                newEventTitleSpan.innerHTML = event_f.title;

                let newEventDateSpan = d.createElement("span");
                newEventDateSpan.className = "evento-fecha";
                newEventDateSpan.innerHTML = `${event_f.date.split("-")[2]}/${event_f.date.split("-")[1]}`;

                let newEventAttendanceSpan = d.createElement("span");
                newEventAttendanceSpan.className = "evento-asistentes";
                newEventAttendanceSpan.innerHTML = `${event_f.totalAttendance} asistentes`;

                newEventLink.appendChild(newEventTitleSpan);
                newEventLink.appendChild(newEventDateSpan);
                newEventLink.appendChild(newEventAttendanceSpan);

                newFEventsSection.appendChild(newEventLink);
            }
        }
        //Enfoque de integración social
        let events_integracion_by_month = getTotalEventsByMonth(month, events_by_year, "Integración Social");

        let newIEventsSection = d.createElement("section");
        newIEventsSection.className = "integracion";

        let newIEventsTitleContainer = d.createElement("div");
        newIEventsTitleContainer.className = "title";

        let newIEventsTitleSpan = d.createElement("span");
        newIEventsTitleSpan.innerHTML = "Integración social";

        newIEventsTitleContainer.appendChild(newIEventsTitleSpan);

        newIEventsSection.appendChild(newIEventsTitleContainer);

        if (events_integracion_by_month.length === 0) {

            let noEventsContainer = d.createElement("div");
            noEventsContainer.className = "no-eventos";

            let noEventsSpan = d.createElement("span");
            noEventsSpan.innerHTML = "No hay eventos";

            noEventsContainer.appendChild(noEventsSpan);
            newIEventsSection.appendChild(noEventsContainer);

        } else {

            for (const event_i of events_integracion_by_month) {
                let newEventLink = d.createElement("a");
                newEventLink.className = "integracion-evento";
                newEventLink.href = `/event/${event_i.id}`;

                let newEventTitleSpan = d.createElement("span");
                newEventTitleSpan.className = "evento-title";
                newEventTitleSpan.innerHTML = event_i.title;

                let newEventDateSpan = d.createElement("span");
                newEventDateSpan.className = "evento-fecha";
                newEventDateSpan.innerHTML = `${event_i.date.split("-")[2]}/${event_i.date.split("-")[1]}`;

                let newEventAttendanceSpan = d.createElement("span");
                newEventAttendanceSpan.className = "evento-asistentes";
                newEventAttendanceSpan.innerHTML = `${event_i.totalAttendance} asistentes`;

                newEventLink.appendChild(newEventTitleSpan);
                newEventLink.appendChild(newEventDateSpan);
                newEventLink.appendChild(newEventAttendanceSpan);

                newIEventsSection.appendChild(newEventLink);
            }
        }

        //Enfoque de sensibilización
        let events_sensibilizacion_by_month = getTotalEventsByMonth(month, events_by_year, "Sensibilización");

        let newSEventsSection = d.createElement("section");
        newSEventsSection.className = "sensibilizacion";

        let newSEventsTitleContainer = d.createElement("div");
        newSEventsTitleContainer.className = "title";

        let newSEventsTitleSpan = d.createElement("span");
        newSEventsTitleSpan.innerHTML = "Sensibilización";

        newSEventsTitleContainer.appendChild(newSEventsTitleSpan);

        newSEventsSection.appendChild(newSEventsTitleContainer);

        if (events_sensibilizacion_by_month.length === 0) {

            let noEventsContainer = d.createElement("div");
            noEventsContainer.className = "no-eventos";

            let noEventsSpan = d.createElement("span");
            noEventsSpan.innerHTML = "No hay eventos";

            noEventsContainer.appendChild(noEventsSpan);
            newSEventsSection.appendChild(noEventsContainer);

        } else {

            for (const event_s of events_sensibilizacion_by_month) {
                let newEventLink = d.createElement("a");
                newEventLink.className = "sensibilizacion-evento";
                newEventLink.href = `/event/${event_s.id}`;

                let newEventTitleSpan = d.createElement("span");
                newEventTitleSpan.className = "evento-title";
                newEventTitleSpan.innerHTML = event_s.title;

                let newEventDateSpan = d.createElement("span");
                newEventDateSpan.className = "evento-fecha";
                newEventDateSpan.innerHTML = `${event_s.date.split("-")[2]}/${event_s.date.split("-")[1]}`;

                let newEventAttendanceSpan = d.createElement("span");
                newEventAttendanceSpan.className = "evento-asistentes";
                newEventAttendanceSpan.innerHTML = `${event_s.totalAttendance} asistentes`;

                newEventLink.appendChild(newEventTitleSpan);
                newEventLink.appendChild(newEventDateSpan);
                newEventLink.appendChild(newEventAttendanceSpan);

                newSEventsSection.appendChild(newEventLink);
            }
        }

        newEventsByMonthContainer.appendChild(newFEventsSection);
        newEventsByMonthContainer.appendChild(newIEventsSection);
        newEventsByMonthContainer.appendChild(newSEventsSection);
        tab_events_by_year_container.appendChild(newEventsByMonthContainer);
    }
}

//se encarga de cargar los datos del último año al cargar la página
const handleCheckedLast = async () => {
    let allEventsYears = new Set();

    for (const e of events) {
        let parts = e.date.split("-");
        let date = new Date(parts[0], parts[1] - 1, parts[2]);
        allEventsYears.add(date.getFullYear());
    }

    let allEventsYearsArray = Array.from(allEventsYears);
    allEventsYearsArray.sort((a, b) => b - a);

    loadAllEvents(allEventsYearsArray[0]);
}

//se encarga de cambiar los valores de cada año al seleccionarlo
const handleChecked = async () => {
    let input_radio_selected = d.querySelector('input[name="tabs"]:checked');
    loadAllEvents((input_radio_selected) ? input_radio_selected.value : 0);
}

//Se encarga de cargar la cabecera de los años
async function handleLoadYearsHeader() {
    const yearsEventsContainer = d.createElement("div");
    yearsEventsContainer.id = "years-events-container";
    yearsEventsContainer.className = "years-events-container-class";
    eventsContainerTop.insertBefore(yearsEventsContainer, eventsContainerTop.firstChild);

    const allEventsYears = new Set();

    for (const e of events) {
        let parts = e.date.split("-");
        let date = new Date(parts[0], parts[1] - 1, parts[2]);
        allEventsYears.add(date.getFullYear());
    }

    let allEventsYearsArray = Array.from(allEventsYears);
    allEventsYearsArray.sort((a, b) => b - a);

    for (const eventYear of allEventsYearsArray) {
        const newInputRadio = d.createElement("input");
        newInputRadio.type = "radio";
        newInputRadio.name = "tabs";
        newInputRadio.id = eventYear;
        newInputRadio.value = eventYear;
        if (eventYear === allEventsYearsArray[0]) newInputRadio.checked = true;
        newInputRadio.addEventListener("change", handleChecked)

        const newLabel = d.createElement("label");
        newLabel.setAttribute("for", eventYear);

        const newSpan = d.createElement("span");
        newSpan.innerHTML = eventYear;

        newLabel.appendChild(newSpan);
        yearsEventsContainer.appendChild(newInputRadio);
        yearsEventsContainer.appendChild(newLabel);
    }
}

const loadAllEventsFiltered = () => {
    let tab_events = d.getElementById("tab-events");
    tab_events.className = "filtered-container"; // ACÁ CONTENEDOR PADRE--------------

    if (tab_events) {
        tab_events.innerHTML = "";
        //AGREGAR ACÁ UNA NUEVA CLASE PARA TAB_EVENTS SI HACE FALTA

        //TODOS LOS EVENTOS DE CADA ENFOQUE
        let total_events_f_array = get_events_by_scope("Formación");
        let total_events_i_array = get_events_by_scope("Integración Social");
        let total_events_s_array = get_events_by_scope("Sensibilización");

        //SECTION PARA LA PRIMER COLUMNA
        let total_events_section = d.createElement("div"); // ACÁ CREATE ELEMENT--------------
        total_events_section.className = "filtered-column-div filtered-total"

        let new_total_events_container = d.createElement("div"); // ACÁ CREATE ELEMENT--------------

        let new_total_events_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
        new_total_events_span.innerHTML = `Total de Eventos encontrados: ${events.length}`;

        new_total_events_container.appendChild(new_total_events_span);
        total_events_section.appendChild(new_total_events_container);

        //SECTION PARA LA COLUMNA DE FORMACIÓN
        let f_events_section = d.createElement("div"); // ACÁ CREATE ELEMENT--------------
        f_events_section.className = "filtered-column-div formacion";

        let f_events_section_title = d.createElement("div"); // ACÁ CREATE ELEMENT--------------
        f_events_section_title.className = "filtered-title-scope";

        let f_events_section_title_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
        f_events_section_title_span.innerHTML = "Formación";

        f_events_section_title.appendChild(f_events_section_title_span);
        f_events_section.appendChild(f_events_section_title);

        if (total_events_f_array.length === 0) {

            let no_events_container = d.createElement("div"); // ACÁ CREATE ELEMENT--------------
            no_events_container.className = "filtered-no-eventos";

            let no_events_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
            no_events_span.innerHTML = "Sin resultados";

            no_events_container.appendChild(no_events_span);
            f_events_section.appendChild(no_events_container);

        } else {

            for (const event_f of total_events_f_array) {
                let new_event_link = d.createElement("a"); // ACÁ CREATE ELEMENT--------------
                new_event_link.className = "filtered-formacion-evento";
                new_event_link.href = `/event/${event_f.id}`;

                let new_event_title_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
                new_event_title_span.className = "filtered-evento-title";
                new_event_title_span.innerHTML = event_f.title;

                let new_event_date_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
                new_event_date_span.className = "filtered-evento-fecha";
                new_event_date_span.innerHTML = `${event_f.date.split("-")[2]}/${event_f.date.split("-")[1]}/${event_f.date.split("-")[0]}`;

                let new_event_attendance_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
                new_event_attendance_span.className = "filtered-evento-asistentes";
                new_event_attendance_span.innerHTML = `${event_f.totalAttendance} asistentes`;

                new_event_link.appendChild(new_event_title_span);
                new_event_link.appendChild(new_event_date_span);
                new_event_link.appendChild(new_event_attendance_span);

                f_events_section.appendChild(new_event_link);
            }
        }

        //SECTION PARA LA COLUMNA DE INTEGRACIÓN
        let i_events_section = d.createElement("div"); // ACÁ CREATE ELEMENT--------------
        i_events_section.className = "filtered-column-div integracion";

        let i_events_section_title = d.createElement("div"); // ACÁ CREATE ELEMENT--------------
        i_events_section_title.className = "filtered-title-scope";

        let i_events_section_title_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
        i_events_section_title_span.innerHTML = "Integración social";

        i_events_section_title.appendChild(i_events_section_title_span);
        i_events_section.appendChild(i_events_section_title);

        if (total_events_i_array.length === 0) {

            let no_events_container = d.createElement("div"); // ACÁ CREATE ELEMENT--------------
            no_events_container.className = "filtered-no-eventos";

            let no_events_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
            no_events_span.innerHTML = "Sin resultados";

            no_events_container.appendChild(no_events_span);
            i_events_section.appendChild(no_events_container);

        } else {

            for (const event_i of total_events_i_array) {
                let new_event_link = d.createElement("a"); // ACÁ CREATE ELEMENT--------------
                new_event_link.className = "filtered-integracion-evento";
                new_event_link.href = `/event/${event_i.id}`;

                let new_event_title_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
                new_event_title_span.className = "filtered-evento-title";
                new_event_title_span.innerHTML = event_i.title;

                let new_event_date_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
                new_event_date_span.className = "filtered-evento-fecha";
                new_event_date_span.innerHTML = `${event_i.date.split("-")[2]}/${event_i.date.split("-")[1]}/${event_i.date.split("-")[0]}`;

                let new_event_attendance_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
                new_event_attendance_span.className = "filtered-evento-asistentes";
                new_event_attendance_span.innerHTML = `${event_i.totalAttendance} asistentes`;

                new_event_link.appendChild(new_event_title_span);
                new_event_link.appendChild(new_event_date_span);
                new_event_link.appendChild(new_event_attendance_span);

                i_events_section.appendChild(new_event_link);
            }
        }

        //SECTION PARA LA COLUMNA DE SENSIBILIZACIÓN
        let s_events_section = d.createElement("div"); // ACÁ CREATE ELEMENT--------------
        s_events_section.className = "filtered-column-div sensibilizacion";

        let s_events_section_title = d.createElement("div"); // ACÁ CREATE ELEMENT--------------
        s_events_section_title.className = "filtered-title-scope";

        let s_events_section_title_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
        s_events_section_title_span.innerHTML = "Sensibilización";

        s_events_section_title.appendChild(s_events_section_title_span);
        s_events_section.appendChild(s_events_section_title);

        if (total_events_s_array.length === 0) {

            let no_events_container = d.createElement("div"); // ACÁ CREATE ELEMENT--------------
            no_events_container.className = "filtered-no-eventos";

            let no_events_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
            no_events_span.innerHTML = "Sin resultados";

            no_events_container.appendChild(no_events_span);
            s_events_section.appendChild(no_events_container);

        } else {

            for (const event_s of total_events_s_array) {
                let new_event_link = d.createElement("a"); // ACÁ CREATE ELEMENT--------------
                new_event_link.className = "filtered-sensibilizacion-evento";
                new_event_link.href = `/event/${event_s.id}`;

                let new_event_title_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
                new_event_title_span.className = "filtered-evento-title";
                new_event_title_span.innerHTML = event_s.title;

                let new_event_date_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
                new_event_date_span.className = "filtered-evento-fecha";
                new_event_date_span.innerHTML = `${event_s.date.split("-")[2]}/${event_s.date.split("-")[1]}/${event_s.date.split("-")[0]}`;

                let new_event_attendance_span = d.createElement("span"); // ACÁ CREATE ELEMENT--------------
                new_event_attendance_span.className = "filtered-evento-asistentes";
                new_event_attendance_span.innerHTML = `${event_s.totalAttendance} asistentes`;

                new_event_link.appendChild(new_event_title_span);
                new_event_link.appendChild(new_event_date_span);
                new_event_link.appendChild(new_event_attendance_span);

                s_events_section.appendChild(new_event_link);
            }
        }

        tab_events.appendChild(total_events_section);
        tab_events.appendChild(f_events_section);
        tab_events.appendChild(i_events_section);
        tab_events.appendChild(s_events_section);
    }
}

const validateExistEvents = () => {
    let import_events_label = d.getElementById("import-events-label");
    let create_event_a = d.getElementById("create-event-a");
    let form_search_bar = d.getElementById("form-search-bar");
    let aside_filter = d.getElementById("aside-filter");
    let download_event_a = d.getElementById("download_event_a");

    import_events_label.style.display = "none";
    create_event_a.style.display = "flex";
    form_search_bar.style.display = "flex";
    aside_filter.style.display = "flex";
    download_event_a.style.display = "flex";
}

const functionToLoad = () => {

    if (events.length > 0) {
        validateExistEvents();
        if (params.toString() === "") {
            handleLoadYearsHeader();
            handleCheckedLast();
        } else {
            showBackOption();
            loadAllEventsFiltered();
            d.title = "RESULTADOS | SIGIS";
        }
    }
    if (events.length === 0) {
        if (params.toString() !== "") {
            d.title = "SIN RESULTADOS | SIGIS";
            showBackOption();
            validateExistEvents();
            const right_container = d.getElementById("right-container");
            if (right_container) {
                if (events_filters.searchBar === null || events_filters.searchBar === "") {
                    right_container.innerHTML =
                        `<div class=\"no-results\">
                        	<h3>
                        		<span class="material-symbols-outlined" style="font-size:4rem;padding-right:0.6rem">search_off</span>
                        		No se encontraron resultados
                        	</h3>
                        	<span style="font-size:1.2rem">Intentá nuevamente con otra búsqueda</span>
                        </div>`;
                }
                if (events_filters.searchBar !== null && events_filters.searchBar !== "") {
                    right_container.innerHTML =
                    `<div class=\"no-results\">
                        	<h3>
                        		<span class="material-symbols-outlined" style="font-size:4rem;padding-right:0.6rem">search_off</span>
                        		No se encontraron resultados para&nbsp;
                        		<mark>"${events_filters.searchBar}"</mark>
                        	</h3>
                        	<span style="font-size:1.2rem">Intentá nuevamente con otra búsqueda</span>
                        </div>`;
                }
            }
        }
    }
}

d.addEventListener("DOMContentLoaded", functionToLoad);

//---------------IMPORTACiÓN------------------------------
class Event {
    constructor(title, date, startTime, endingTime, instructor, organizedBy,
                status, offeredBySemillero, approach, type, origin, destination,
                modality, location, description, duration, attendance) {
        this.date = date;
        this.title = title;
        this.offeredBySemillero = offeredBySemillero;
        this.status = status;
        this.organizedBy = organizedBy;
        this.type = type;
        this.approach = approach;
        this.instructor = instructor;
        this.description = description;
        this.location = location;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.modality = modality;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.attendance = attendance;
        this.isActive = true;
    }
}

const importarEvento = d.getElementById("importarEventoArchivo");

const handleChange = async () => {

    const refactorDate = (date) => {

        let realDate = "";

        date.setUTCHours(date.getUTCHours() + 3);

        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let day = date.getDate();

        if (month < 10) month = "0" + month;
        if (day < 10) day = "0" + day;

        return realDate.concat(year + "-" + month + "-" + day);
    }

    const refactorTime = (time) => {

        let realTime = "";

        let hours = time.getHours() + 4;
        let minutes = time.getMinutes() + 17;

        if (minutes >= 60) {
            minutes -= 60;
            hours++;
        }

        if (minutes < 10) minutes = "0" + minutes;

        return realTime.concat(hours + ":" + minutes + ":00");
    }

    let content = await readXlsxFile(importarEvento.files[0]);
    let realContent = content.splice(14, content.length);
    if (realContent.length > 0) {
        if(realContent[0].length === 17){
            let eventsToImport = [];

            for (let i = 0; i < realContent.length; i++) {

                let offeredBySemillero = (realContent[i][7].toUpperCase() === "SI");

                let newEvent = new Event(realContent[i][0], refactorDate(realContent[i][1]), refactorTime(realContent[i][2]),
                    refactorTime(realContent[i][3]), realContent[i][4], realContent[i][5], realContent[i][6], offeredBySemillero,
                    realContent[i][8], realContent[i][9], realContent[i][10], realContent[i][11], realContent[i][12],
                    realContent[i][13], realContent[i][14], realContent[i][15], realContent[i][16]);
                eventsToImport.push(newEvent);
            }
            const url = new URL(window.location.href);
            url.pathname = "/event/import";
            const options = {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(eventsToImport)
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
                window.location.reload()
            }, 2000);
        }else{
            d.querySelector('.importacion-error-container').classList.add('active')
            setTimeout(() => {
                d.querySelector('.importacion-error-container').classList.remove('active')
            }, 2000);
        }
    }else{
        d.querySelector('.importacion-error-container').classList.add('active')
        setTimeout(() => {
            d.querySelector('.importacion-error-container').classList.remove('active')
        }, 2000);
    }
};

if (importarEvento) importarEvento.addEventListener("change", handleChange);