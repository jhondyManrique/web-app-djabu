if (typeof carritoInicial !== 'undefined') {
        // Si la nota existe, usa la herramienta para dibujar el carrito inmediatamente.
        actualizarVistaCarrito(carritoInicial);
    }

document.addEventListener('DOMContentLoaded', function() {

    document.addEventListener('click', function(e) {
        if (e.target.classList.contains('btn-agregar')) {
            const button = e.target;
            const fila = button.closest('tr');
            const inputCantidad = fila.querySelector(".cantidad-input");
            const product = {
                nombre: button.dataset.nombre,
                precio: button.dataset.precio,
                cantidad: inputCantidad.value
            };
            agregarAlCarrito(product);
        }

        if (e.target.classList.contains('btn-eliminar')) {
            const nombre = e.target.dataset.nombre;
            eliminarDelCarrito(nombre);
        }
    });
});








