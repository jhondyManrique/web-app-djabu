if (typeof cart !== 'undefined') {
        // Si la nota existe, usa la herramienta para dibujar el carrito inmediatamente.
        updateCartView(cart);
        clearOrderConfirmationMessage();
    }

document.addEventListener('DOMContentLoaded', function() {

    document.addEventListener('click', function(e) {
        if (e.target.classList.contains('btn-add-product')) {
            const button = e.target;
            const row = button.closest('tr');
            const quantityInput = row.querySelector(".quantityInput");
            const product = {
                name: button.dataset.name,
                price: button.dataset.price,
                quantity: quantityInput.value
            };
            addProductToCart(product);
            clearOrderConfirmationMessage();
        }

        if (e.target.classList.contains('btn-delete-product')) {
            const name = e.target.dataset.name;
            deleteProductFromCart(name);
            clearOrderConfirmationMessage();
        }
    });
});








