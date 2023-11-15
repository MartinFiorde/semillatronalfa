const d = document;
const params = new URLSearchParams(new URL(window.location.href).search);

/* FILTROS */
d.querySelectorAll('.filtro-tipo-proyecto').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-proyecto-estado').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-proyecto-fecha-inicio').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-proyecto-fecha-fin').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-proyecto-comision').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-proyecto-marcatemporal').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});


/* TABLE PAGINATION */
if (document.getElementById("tabla-proyectos") && projects.length > 0) {
    let $table = document.getElementById("tabla-proyectos"),
        $n = 10,
        $rowCount = $table.rows.length,
        $firstRow = $table.rows[0].firstElementChild.tagName,
        $hasHead = ($firstRow === "TH"),
        $tr = [],
        $i, $ii, $j = ($hasHead) ? 1 : 0,
        $th = ($hasHead ? $table.rows[(0)].outerHTML : "");

    let $pageCount = Math.ceil($rowCount / $n);
    if ($pageCount > 1) {
        for ($i = $j, $ii = 0; $i < $rowCount; $i++, $ii++)
            $tr[$ii] = $table.rows[$i].outerHTML;
        $table.insertAdjacentHTML("afterend", "<div id='pagination'></div");
        sort(1);
    }

    function sort($p) {
        let $rows = $th, $s = (($n * $p) - $n);
        for ($i = $s; $i < ($s + $n) && $i < $tr.length; $i++)
            $rows += $tr[$i];
        $table.innerHTML = $rows;
        document.getElementById("pagination").innerHTML = pageButtons($pageCount, $p);
        document.getElementById("id" + $p).setAttribute("class", "active");
    }

    function pageButtons($pCount, $cur) {
        var $prevDis = ($cur == 1) ? "disabled" : "",
            $nextDis = ($cur == $pCount) ? "disabled" : "",
            $pagination = "<input id='prev' type='button' value='🡨 Anterior' onclick='sort(" + ($cur - 1) + ")' " + $prevDis + ">";
        for ($i = 1; $i <= $pCount; $i++)
            $pagination += "<input type='button' id='id" + $i + "'value='" + $i + "' onclick='sort(" + $i + ")'>";
        $pagination += "<input id='next' type='button' value='Siguiente 🡪' onclick='sort(" + ($cur + 1) + ")' " + $nextDis + ">";
        return $pagination;
    }
}

//-----------------------IMPORTACIÓN-----------------------------------------------------------------
class Project {
    constructor(projectName, commission, estimatedTime, initialDate, endingDate, ally, timestamp, originPlace,
                ods, responsible, responsiblePhoneNumber, responsibleEmail, address, productOwnerFullName, productOwnerTelephone,
                productOwnerEmail, productOwnerDiscordUser, purpose, projectType, projectStatus, projectStage) {
        this.projectName = projectName;
        this.commission = commission;
        this.estimatedTime = estimatedTime;
        this.initialDate = initialDate;
        this.endingDate = endingDate;
        this.ally = ally;
        this.timestamp = timestamp;
        this.originPlace = originPlace;
        this.ods = ods;
        this.responsible = responsible;
        this.responsibleEmail = responsibleEmail;
        this.responsiblePhoneNumber = responsiblePhoneNumber;
        this.address = address;
        this.productOwnerFullName = productOwnerFullName;
        this.productOwnerTelephone = productOwnerTelephone;
        this.productOwnerEmail = productOwnerEmail;
        this.productOwnerDiscordUser = productOwnerDiscordUser;
        this.purpose = purpose;
        this.projectType = projectType;
        this.projectStatus = projectStatus;
        this.projectStage = projectStage;
    }
}

const excelInput = d.getElementById("importarProyectosArchivo");

function dividirTexto(texto) {
    const index = texto.indexOf("/");

    if (index !== -1 && index !== texto.length - 1) {
        return texto.split("/");
    }
    return [texto];
}

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

const handleChange = async () => {
    let content = await readXlsxFile(excelInput.files[0]);
    let realContent = content.splice(14, content.length);
    if (realContent.length > 0) {
        if (realContent[0].length === 20) {
            let projectsToImport = [];

            for (let i = 0; i < realContent.length; i++) {

                let status_stage = dividirTexto(realContent[i][19])

                let newProject = new Project(realContent[i][0], realContent[i][1], realContent[i][2],
                    refactorDate(realContent[i][3]), refactorDate(realContent[i][4]), realContent[i][5],
                    realContent[i][6], realContent[i][7], realContent[i][8], realContent[i][9], realContent[i][10],
                    realContent[i][11], realContent[i][12], realContent[i][13], realContent[i][14], realContent[i][15],
                    realContent[i][16], realContent[i][17], realContent[i][18], status_stage[0].trim(), status_stage[1].trim());
                projectsToImport.push(newProject);
            }
            const url = new URL(window.location.href);
            url.pathname = "/project/import";
            const options = {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(projectsToImport)
            };

            fetch(url, options)
                .then(response => response.json())
                .then(data => {
                    console.log("Respuesta del servidor:", data);
                })
                .catch(error => {
                    console.error("Error en la solicitud:", error);
                });

            document.querySelector('.importacion-exito-container').classList.add('active')
            setTimeout(() => {
                document.querySelector('.importacion-exito-container').classList.remove('active')
                window.location.reload()
            }, 2000);
        } else {
            document.querySelector('.importacion-error-container').classList.add('active')
            setTimeout(() => {
                document.querySelector('.importacion-error-container').classList.remove('active')
            }, 2000);
        }
    } else {
        document.querySelector('.importacion-error-container').classList.add('active')
        setTimeout(() => {
            document.querySelector('.importacion-error-container').classList.remove('active')
        }, 2000);
    }
}

excelInput.addEventListener("change", handleChange);

//------------------------------------FILTROS--------------------------------------------------------
/*function toggleOpciones(num) {
    let opciones = document.getElementById('opciones-' + num);
    opciones.style.display = (opciones.style.display === 'block') ? 'none' : 'block';
}*/

function showBackOption() {
    let aBrowseBack = d.getElementById("a-browse-back");
    let h2BrowseResult = d.getElementById("h2-browse-result");
    let limpiar_filtros = d.getElementById("limpiar-filtros");

    if (aBrowseBack) aBrowseBack.style.display = "flex";
    if (h2BrowseResult) h2BrowseResult.style.display = "flex";
    if (limpiar_filtros) limpiar_filtros.style.display = "flex";
}

const functionToLoad = () => {
    let right_container = d.getElementById("right-container");
    let form_search_bar = d.getElementById("form-search-bar");
    let aside_filter = d.getElementById("aside-filter");
    let download_project_a = d.getElementById("download_project_a");

    if (params.toString() === "") {
        if (projects.length > 0) {
            if (right_container) right_container.style.display = "flex";
            if (form_search_bar) form_search_bar.style.display = "flex";
            if (aside_filter) aside_filter.style.display = "flex";
            if (download_project_a) download_project_a.style.display = "flex";
        }
    } else {
        d.title = "RESULTADOS | SIGIS";
        showBackOption();
        if (form_search_bar) form_search_bar.style.display = "flex";
        if (aside_filter) aside_filter.style.display = "flex";
        if (right_container) right_container.style.display = "flex";
        if (download_project_a) download_project_a.style.display = "flex";

        if (projects.length === 0) {
            d.title = "SIN RESULTADOS | SIGIS";
            if (right_container) {
                if (project_filters.searchBar === null || project_filters.searchBar === "") {
                    right_container.innerHTML =
                        `<div class=\"no-results\">
                        	<h3>
                        		<span class="material-symbols-outlined" style="font-size:4rem;padding-right:0.6rem">search_off</span>
                        		No se encontraron resultados
                        	</h3>
                        	<span style="font-size:1.2rem">Intentá nuevamente con otra búsqueda</span>
                        </div>`;
                }
                if (project_filters.searchBar !== null && project_filters.searchBar !== "") {
                    right_container.innerHTML =
                        right_container.innerHTML =
                            `<div class=\"no-results\">
                        	<h3>
                        		<span class="material-symbols-outlined" style="font-size:4rem;padding-right:0.6rem">search_off</span>
                        		No se encontraron resultados para&nbsp;
                        		<mark>"${project_filters.searchBar}"</mark>
                        	</h3>
                        	<span style="font-size:1.2rem">Intentá nuevamente con otra búsqueda</span>
                        </div>`;
                }
            }
        }
    }
}

d.addEventListener("DOMContentLoaded", functionToLoad);
