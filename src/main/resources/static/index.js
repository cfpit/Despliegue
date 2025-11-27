// Variables globales
const tablaPersonasHTML = document.getElementById('tablaPersonas');
const nombre = document.getElementById('nombre');
const edad = document.getElementById('edad');
const btnGuardar = document.getElementById('btnGuardar');
let tituloModal = document.getElementById('tituloModal');
let idPersonaModificar = 0;


// Event listener para el botón de crear
btnGuardar.addEventListener('click', () => {
    if (tituloModal.innerText === "Modificar Persona") {
        crearPersona(idPersonaModificar);
    } else
        if (tituloModal.innerText === "Crear Persona") {
            crearPersona(0);
        }
});


//Eliminar Persona
async function eliminarPersona(id) {
    await fetch('http://localhost:8080/api/persona/borrar/' + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(() => {
            alert('Persona eliminada correctamente');
            obtenerPersonas();
        })
        .catch(err => {
            console.error('Error al eliminar persona:', err);
            alert('Error al eliminar la persona');
        });
}

    
//Modificar Persona
async function modificarPersona(id) {
    tituloModal.innerText = "Modificar Persona";
    modal.style.display = "block";//muestra el modal

    //Esta variable se usa para enviar el ID correcto al servidor luego de los 
    //cambios
    idPersonaModificar = id;

    resetModal();

    //Filtra la lista de personas (listaPersonas) buscando la persona cuya 
    //propiedad idpersona coincida con el id pasado como parámetro. Devuelve 
    //un array con la persona encontrada o un array vacío si no se encuentra 
    //la persona.
    let persona = listaPersonas.find(p => p.idPersona === idPersonaModificar);

    if (persona) {
        nombre.value = persona.nombre;
        edad.value = persona.edad;

        // Envía una solicitud PUT para actualizar la persona
        await fetch('http://localhost:8080/api/persona/actualizar/' + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                idPersona: id,
                nombre: nombre.value,
                edad: parseInt(edad.value)
            })
        })
            .then(response => response.json())
            .then(data => {
                console.log('Persona actualizada:', data);
                alert('Persona modificada correctamente');
                obtenerPersonas();
            })
            .catch(err => {
                console.error('Error al modificar persona:', err);
            });
    }
}


//Crear Persona
async function crearPersona(id) {
    let personaGuardar = {
        idPersona: id,
        nombre: nombre.value,
        edad: parseInt(edad.value),
    }
    console.log(personaGuardar);

    await fetch('http://localhost:8080/api/persona/crear', {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(personaGuardar)
    })
        .then((persona) => {
            console.log(persona);
            alert("Persona guardada")
            modal.style.display = "none";
            obtenerPersonas()
            resetModal()
        })
        .catch((err) => {
            console.log(err);
            alert("Error en el alta de la persona")
        })

}


// Traer todas las personas
let listaPersonas = [];
async function obtenerPersonas() {
    tablaPersonasHTML.innerHTML = '';
    await fetch('http://localhost:8080/api/persona/traer')
        .then(response => response.json())
        .then(personas => {
            listaPersonas = personas
            personas.forEach(persona => {
                tablaPersonasHTML.innerHTML += `
                    <tr>
                        <td>${persona.idPersona}</td>
                        <td>${persona.nombre}</td>
                        <td>${persona.edad}</td>
                        <td>
                            
                            <button class="btn btn-danger btn-sm" onclick=eliminarPersona(${persona.idPersona})>Eliminar</button>
                            <button class="btn btn-success btn-sm" onclick=modificarPersona(${persona.idPersona})>Modificar</button>
                        </td>
                    </tr>
                `;



            });
        })
        .catch(err => {
            console.error('Error al obtener personas:', err);
            alert('Error al cargar las personas');
        });
}
obtenerPersonas()

async function buscarPersonas() {
    const busqueda = document.getElementById('busqueda').value;
    tablaPersonasHTML.innerHTML = '';

    await fetch('http://localhost:8080/api/persona/buscarSimilar/' + busqueda)
        .then(response => response.json())
        .then(personas => {
            listaPersonas = personas;
            personas.forEach(persona => {
                tablaPersonasHTML.innerHTML += `
                    <tr>
                        <td>${persona.idPersona}</td>
                        <td>${persona.nombre}</td>
                        <td>${persona.edad}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick=eliminarPersona(${persona.idPersona})>Eliminar</button>
                            <button class="btn btn-success btn-sm" onclick=modificarPersona(${persona.idPersona})>Modificar</button>
                        </td>
                    </tr>
                `;
            });
        })
        .catch(err => {
            console.error('Error al obtener personas:', err);
            alert('Error al cargar las personas');
        });
}


//FUNCION CONSUMO API DOLAR
function cargarDatos() {
    fetch('http://localhost:8080/api/dolar') 
        .then(response => {
            if (!response.ok) {
                throw new Error('No se pudo obtener el tipo de cambio del dólar');
            }
            return response.json();
        })
        .then(data => {
            mostrarDatos(data);
            console.log(data);
        })
        .catch(error => {
            console.error('Error al cargar los datos:', error);
            document.getElementById('dolarInfo').innerHTML = '<div class="alert alert-danger" role="alert">Error al obtener el tipo de cambio del dólar.</div>';
        });
}

function mostrarDatos(data) {
    document.getElementById('compra').textContent = '$' + data.compra.toFixed(2);
    document.getElementById('venta').textContent = '$' + data.venta.toFixed(2);
    document.getElementById('fechaActualizacion').textContent = new Date(data.fechaActualizacion).toLocaleString();
}

window.onload = cargarDatos;


//MODAL
// Función para resetear el formulario del modal
function resetModal() {
    nombre.value = '';
    edad.value = '';
}

//Modal 
var modal = document.getElementById("myModal");
var btn = document.getElementById("myBtn");

//Busca el primer elemento de clase "close" en el documento.
//Esto suele ser un icono de cierre para cerrar el modal.
var span = document.getElementsByClassName("close")[0];

//Asigna una función al evento de clic del botón "myBtn".
//Cuando se presiona este botón, cambia el texto del título del modal a "Crear Persona" y muestra el modal.
btn.onclick = function () {
    tituloModal.innerText = "Crear Persona"
    modal.style.display = "block";
}

//Cuando se presiona este elemento (generalmente un icono de cierre), oculta el modal y llama a resetModal().
span.onclick = function () {
    modal.style.display = "none";
    resetModal()
}

//Si el objetivo del clic es el modal mismo, ocultará el modal y llamará a resetModal().
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
        resetModal()
    }
}



