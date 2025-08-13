function agregarAlCarrito(producto) {

    const params = new URLSearchParams();
    params.append('action', 'agregar');
    params.append('nombre', producto.nombre);
    params.append('precio', producto.precio);
    params.append('cantidad', producto.cantidad);

    fetch('carrito', {
        method: 'POST',
        body: params
    })
    .then(response => response.json())
    .then(data => {
        actualizarVistaCarrito(data);
    });
}

// Función para redibujar la tabla del carrito y el total
function actualizarVistaCarrito(data) {
    const cartItemsTbody = document.getElementById('cart-items');
    const cartTotalSpan = document.getElementById('cart-total');
    const hiddenInputsDiv = document.getElementById('hidden-form-inputs');

    // Limpiamos la tabla y los inputs ocultos
    cartItemsTbody.innerHTML = '';
    hiddenInputsDiv.innerHTML = '';

    if (data.items && data.items.length > 0) {
        data.items.forEach(item => {
            // Creamos la fila para la tabla visible
            const subtotal = item.cantidad * item.precio;
            const row = `
                <tr>
                    <td>${item.nombre}</td>
                    <td>${item.cantidad}</td>
                    <td>$${item.precio.toFixed(2)}</td>
                    <td>$${subtotal.toFixed(2)}</td>
                    <td><button type="button" class="btn-eliminar-item" data-id="${item.id}">X</button></td>
                </tr>
            `;
            cartItemsTbody.innerHTML += row;

            // Creamos los inputs ocultos para el formulario final
            const hiddenId = `<input type="hidden" name="productoId" value="${item.id}">`;
            const hiddenCantidad = `<input type="hidden" name="cantidad" value="${item.cantidad}">`;
            hiddenInputsDiv.innerHTML += hiddenId + hiddenCantidad;
        });
    } else {
        cartItemsTbody.innerHTML = '<tr><td colspan="5">El carrito está vacío</td></tr>';
    }

    // Actualizamos el total
    cartTotalSpan.textContent = data.total.toFixed(2);
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
            const nombre = e.target.dataset.id;
            eliminarDelCarrito(id);
        }
    });
});




// Función para enviar la solicitud de "eliminar" al servlet
function eliminarDelCarrito(id) {
    const params = new URLSearchParams();
    params.append('action', 'eliminar');
    params.append('id', id);

    fetch('carrito', {
        method: 'POST',
        body: params
    })
    .then(response => response.json())
    .then(data => {
        actualizarVistaCarrito(data);
    });
}



