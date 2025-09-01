function addProductToCart(product) {

    const params = new URLSearchParams();
    params.append('action', 'addProduct');
    params.append('name', product.name);
    params.append('price', product.price);
    params.append('quantity', product.quantity);

    fetch("/cart", {
        method: 'POST',
        body: params
    })
    .then(response => response.json())
    .then(cartData => {
        updateCartView(cartData);
    });
}