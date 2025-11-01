// Función para enviar la solicitud de "eliminar" al servlet
function deleteProductFromCart(productName) {
    const params = new URLSearchParams();
    params.append('action', 'deleteProduct');
    params.append('name', productName);

    fetch("/cart", {
        method: 'POST',
        body: params
    })
    .then(response => response.json())
    .then(cartData => {
        updateCartView(cartData);
    });
}