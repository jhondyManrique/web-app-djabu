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