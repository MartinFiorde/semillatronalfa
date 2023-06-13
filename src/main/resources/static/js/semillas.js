/* TABLE PAGINATION */
const d = document;

if(d.getElementById("tabla-semillas")) {
	let $table = d.getElementById("tabla-semillas"),
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
		d.getElementById("pagination").innerHTML = pageButtons($pageCount, $p);
		d.getElementById("id" + $p).setAttribute("class", "active");
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
class SeedStatus{
	constructor(primary, secondary, projectId, projectName, digitalEmployment, companyName) {
		this.primary = primary;
		this.secondary = secondary;
		this.projectId = projectId;
		this.projectName = projectName;
		this.digitalEmployment = digitalEmployment;
		this.companyName = companyName;
	}
}
class SeedPostulationData{
	constructor(rol, turn, meetSemilleroBy, studies, hobbies, comment) {
		this.rol = rol;
		this.turn = turn;
		this.meetSemilleroBy = meetSemilleroBy;
		this.studies = studies;
		this.hobbies = hobbies;
		this.comment = comment;
	}
}
class SeedContactData{
	constructor(email, telephone, linkedin, discordUser) {
		this.email = email;
		this.telephone = telephone;
		this.linkedin = linkedin;
		this.discordUser = discordUser;
	}
}

class SeedPersonalData{
	constructor(firstName, lastName, dni, birthDate, country, city, gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.birthDate = birthDate;
		this.country = country;
		this.city = city;
		this.gender = gender;
	}
}

class SeedFollowUp{
	constructor(postulationDate, commission, status, recommendation, certification, comments) {
		this.postulationDate = postulationDate;
		this.commission = commission;
		this.status = status;
		this.recommendation = recommendation;
		this.certification = certification;
		this.comments = comments;
	}
}

class Seed{
	constructor(followUp, personalData, contactData, postulationData, isActive) {
		this.followUp = followUp;
		this.personalData = personalData;
		this.contactData = contactData;
		this.postulationData = postulationData;
		this.isActive = isActive;
	}
}

const excelInput = d.getElementById("importarSemillaArchivo");

function dividirTexto(texto) {
	const index = texto.indexOf("/");

	if (index !== -1 && index !== texto.length - 1) {
		return texto.split("/");
	}

	return [texto];
}

const handleChange = async () => {
	let content = await readXlsxFile(excelInput.files[0]);
	let realContent = content.splice(14,content.length);
	let semillasToImport = [];
	console.log(realContent);
	for (let i = 0; i < realContent.length; i++) {

		let statusDivided = dividirTexto(realContent[i][18])
		let fechaNacimiento = new Date(realContent[i][6]);
		fechaNacimiento.setUTCHours(fechaNacimiento.getUTCHours() + 3)
		let fechaPostulado = new Date(realContent[i][4]);

		//let fechaNacimientoFormateada = fechaNacimiento.getDate() + "-" + fechaNacimiento.getMonth() + "-" + fechaNacimiento.getFullYear()
		//console.log(fechaNacimientoFormateada);

		let certificadoEnviado = false;
		let semillaRecomendada = false;

		if(realContent[i][20] !== null){
			certificadoEnviado = realContent[i][20].toLowerCase() === "enviado";
		}
		if(realContent[i][21] !== null){
			semillaRecomendada = realContent[i][21].toLowerCase() === "si";
		}

		let status = new SeedStatus(statusDivided[0],statusDivided[1] || "",
			0, realContent[i][19], false, "");
		let followUp = new SeedFollowUp(fechaPostulado,realContent[i][3],
			status, semillaRecomendada, certificadoEnviado, []);
		let postulationData = new SeedPostulationData(realContent[i][2], realContent[i][5],
			realContent[i][16], realContent[i][17], "", "");
		let contactData = new SeedContactData(realContent[i][13], realContent[i][12],
			realContent[i][15], realContent[i][14]);
		let personalData = new SeedPersonalData(realContent[i][0], realContent[i][1], realContent[i][7],
			fechaNacimiento, realContent[i][11], realContent[i][10] + ", "+ realContent[i][9],
			realContent[i][8]);
		let seed = new Seed(followUp, personalData, contactData, postulationData, false);
		semillasToImport.push(seed);
	}

	console.log(semillasToImport);

	if(semillasToImport.length > 0) {
		const url = "http://localhost:8080/seed/api/create-batch";
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
	}else{
		alert("No hay datos que importar");
	}
	setTimeout(()=>{window.location.reload()},1000);
};

excelInput.addEventListener("change", handleChange);
