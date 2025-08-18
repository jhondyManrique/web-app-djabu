// Función para enviar la solicitud de "eliminar" al servlet
function eliminarDelCarrito(nombre) {
    const params = new URLSearchParams();
    params.append('action', 'eliminar');
    params.append('nombre', nombre);

    fetch('carrito', {
        method: 'POST',
        body: params
    })
    .then(response => response.json())
    .then(data => {
        actualizarVistaCarrito(data);
    });
}