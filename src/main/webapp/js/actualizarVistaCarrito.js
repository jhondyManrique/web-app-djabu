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
                    <td><button type="button" class="btn-eliminar" data-nombre="${item.nombre}">X</button></td>
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