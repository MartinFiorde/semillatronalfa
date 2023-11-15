const d = document;
const params = new URLSearchParams(new URL(window.location.href).search);

//------------------------------------------------FILTROS-----------------------------------------------
d.querySelectorAll('.filtro-rol').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

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

d.querySelectorAll('.filtro-proyectos').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-genero').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-turno').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-comision').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-pais').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-certificado').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-marcatemporal').forEach(dropdown => {
    const select = dropdown.querySelector('.select');
    const caret = dropdown.querySelector('.caret');
    const menu = dropdown.querySelector('.menu');

    select.addEventListener('click', () => {
        select.classList.toggle('select-clicked');
        caret.classList.toggle('caret-rotate');
        menu.classList.toggle('menu-open');
    });
});

d.querySelectorAll('.filtro-recomendada').forEach(dropdown => {
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
if (document.getElementById("tabla-semillas") && seeds.length > 0) {
    let $table = document.getElementById("tabla-semillas"),
        $n = 40,
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
class SeedStatus {
    constructor(primary, secondary, digitalEmployment, companyName, projectNames) {
        this.primary = primary;
        this.secondary = secondary;
        this.digitalEmployment = digitalEmployment;
        this.companyName = companyName;
        this.projectNames = projectNames;
    }
}

class SeedPostulationData {
    constructor(rol, turn, meetSemilleroBy, studies, hobbies, comment) {
        this.rol = rol;
        this.turn = turn;
        this.meetSemilleroBy = meetSemilleroBy;
        this.studies = studies;
        this.hobbies = hobbies;
        this.comment = comment;
    }
}

class SeedContactData {
    constructor(email, telephone, linkedin, discordUser) {
        this.email = email;
        this.telephone = telephone;
        this.linkedin = linkedin;
        this.discordUser = discordUser;
    }
}

class SeedPersonalData {
    constructor(firstName, dni, birthDate, country, city, gender) {
        this.firstName = firstName;
        this.dni = dni;
        this.birthDate = birthDate;
        this.country = country;
        this.city = city;
        this.gender = gender;
    }
}

class SeedFollowUp {
    constructor(postulationDate, commission, status, certificationString) {
        this.postulationDate = postulationDate;
        this.commission = commission;
        this.status = status;
        this.certificationString = certificationString;
    }
}

class Seed {
    constructor(followUp, personalData, contactData, postulationData, isActive) {
        this.followUp = followUp;
        this.personalData = personalData;
        this.contactData = contactData;
        this.postulationData = postulationData;
        this.isActive = isActive;
    }
}

const excelInput = document.getElementById("importarSemillaArchivo");

function dividirStatus(texto) {
    const index = texto.indexOf("/");

    if (index !== -1 && index !== texto.length - 1) {
        return texto.split("/");
    }
    return [texto];
}

const handleChange = async () => {
    let content = await readXlsxFile(excelInput.files[0]);
    let realContent = content.splice(14, content.length);
    if (realContent.length > 0) {
        if(realContent[0].length === 19){
            let semillasToImport = [];

            for (let i = 0; i < realContent.length; i++) {
                let statusDivided = dividirStatus(realContent[i][17])
                let fechaNacimiento = new Date(realContent[i][5]);
                fechaNacimiento.setUTCHours(fechaNacimiento.getUTCHours() + 3)
                let fechaPostulado = new Date(realContent[i][3]);

                let status = new SeedStatus(statusDivided[0], statusDivided[1] ? statusDivided[1] + (statusDivided[2] ? " - " + statusDivided[2] : "") : "",
                    false, "", realContent[i][18].split(","));
                let followUp = new SeedFollowUp(fechaPostulado, realContent[i][2],
                    status, "No enviado");
                let postulationData = new SeedPostulationData(realContent[i][1], realContent[i][4],
                    realContent[i][15], realContent[i][16], "", "");
                let contactData = new SeedContactData(realContent[i][12], realContent[i][11],
                    realContent[i][14], realContent[i][13]);
                let personalData = new SeedPersonalData(realContent[i][0], realContent[i][6],
                    fechaNacimiento, realContent[i][10], realContent[i][9] + ", " + realContent[i][8],
                    realContent[i][7]);
                let seed = new Seed(followUp, personalData, contactData, postulationData, false);
                semillasToImport.push(seed);
            }

            const url = new URL(window.location.href);
            url.pathname = "/seed/api/create-batch";
            const options = {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(semillasToImport)
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
        }else{
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
};

excelInput.addEventListener("change", handleChange);

//-------------testeo alerta--------------------------------------------------
function alertTest() {
    const url = new URL(window.location.href);
    const params = new URLSearchParams(url.search);

    params.forEach((value, key) => {
        console.log(`${key}: ${value}`);
    });
}

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
    let download_seed_a = d.getElementById("download_seed_a");

    if (params.toString() === "") {
        if (seeds.length > 0) {
            if (right_container) right_container.style.display = "flex";
            if (form_search_bar) form_search_bar.style.display = "flex";
            if (aside_filter) aside_filter.style.display = "flex";
            if (download_seed_a) download_seed_a.style.display = "flex";
        }
    } else {
        d.title = "RESULTADOS | SIGIS";
        showBackOption();
        if (form_search_bar) form_search_bar.style.display = "flex";
        if (aside_filter) aside_filter.style.display = "flex";
        if (right_container) right_container.style.display = "flex";
        if (download_seed_a) download_seed_a.style.display = "flex";

        if (seeds.length === 0) {
            d.title = "SIN RESULTADOS | SIGIS";
            if (right_container) {
                if (seed_filter.searchBar === null || seed_filter.searchBar === "") {
                    right_container.innerHTML =
                        `<div class=\"no-results\">
                        	<h3>
                        		<span class="material-symbols-outlined" style="font-size:4rem;padding-right:0.6rem">search_off</span>
                        		No se encontraron resultados
                        	</h3>
                        	<span style="font-size:1.2rem">Intentá nuevamente con otra búsqueda</span>
                        </div>`;
                }
                if (seed_filter.searchBar !== null && seed_filter.searchBar !== "") {
                    right_container.innerHTML =
                        `<div class=\"no-results\">
                        	<h3>
                        		<span class="material-symbols-outlined" style="font-size:4rem;padding-right:0.6rem">search_off</span>
                        		No se encontraron resultados para&nbsp;
                        		<mark>"${seed_filter.searchBar}"</mark>
                        	</h3>
                        	<span style="font-size:1.2rem">Intentá nuevamente con otra búsqueda</span>
                        </div>`;
                }
            }
        }
    }
}

d.addEventListener("DOMContentLoaded", functionToLoad);
