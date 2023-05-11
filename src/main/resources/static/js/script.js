

// ejercicio 4.1 fetch GET >>>

// peticion incompleta inicial
// fetch("https://rickandmortyapi.com/api/character");

// peticion directa a traves de fetch (queda solo en un console.log)
// fetch("https://rickandmortyapi.com/api/character").
// then(response=>response.json()).
// then(data => console.log(data));

// peticion directa a travea de fetch, guardando el resultado de la peticion en una variable
async function peticion() {
    let response = await fetch("https://rickandmortyapi.com/api/character");
    let data = await response.json();
    return data;
}

let r = peticion();
console.log(r);


// ejercicio 4.2 fetch POST >>> imposible ejecutar el codigo que muestran en el video....


// ejercicio 4.2 destructuring >>> 
// ejemplo con objeto
let obj = { a: 123, b: 456, c: 789 };
let { a: alias1, b: alias2, c: alias3 = 999 } = obj;
console.log(`${alias1} - ${alias2} - ${alias3}`);

// ejemplo con array
let array = [1, 2, 3, 4, 5];
let [alias4, a2, a3, a4, a5, alias5, alias6 = 888] = array;
console.log(`${alias4} - ${alias5} - ${alias6}`);

let [alias7, , ...otros] = array;
console.log(`${alias7} - ${otros}`);

// ejemplo con objeto constante "persona"
const persona = {
    nombre: "Chiquito",
    apellido: "Gordote"
}

function soloNombre({ nombre: alias8 = "nn" }) {
    console.log(alias8);
}

soloNombre(persona);

// ejemplo con solicitudes de una API
fetch("https://rickandmortyapi.com/api/character").
    then(response => response.json()).
    then(data => {
        const { results } = data;
        console.log(results);
    });


// ejercicio 4.4 local storage, sesion storage y cookies

// COMANDOS LOCAL STORAGE
// localStorage.setItem(`mascota`,`Chiquito`);
// let miMascota1 = localStorage.getItem("mascota");
// localStorage.removeItem("mascota");
// localStorage.length;
// localStorage.clear();

// COMANDOS SESION STORAGE
// sesionStorage.setItem(`mascota`,`Filomena`);
// let miMascota2 = sesionStorage.getItem("mascota");
// sesionStorage.removeItem("mascota");
// sesionStorage.length;
// sesionStorage.clear();

// COMANDOS COOKIES (conviene probarlas en una pagina web normal, desde un html suelto suele generar inconvenientes)
// document.cookie = "mascota=Malva"; // agrega el item mascota
// let cookies = document.cookie; // consulta todas las cookies, entre las cuales esta la mascota
// document.cookie = "mascota= "; // borra el item mascota 