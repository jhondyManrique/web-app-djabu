
/**
 * Updates the entire shopping cart view, including the item list, total price,
 * and hidden form inputs.
 *
 * @param {object} cartData - The cart data object from the server or state.
 * @param {Array<object>} cartData.items - An array of item objects in the cart.
 * @param {string} cartData.items[].name - The name of the product.
 * @param {number} cartData.items[].quantity - The number of units for the product.
 * @param {number} cartData.items[].price - The price per unit of the product.
 * @param {number} cartData.total - The total price of all items in the cart.
 */
function updateCartView(cartData) {
    // 1. Get references to DOM elements
    const cartItemsBody = document.getElementById('cart-items');
    const cartTotalElement = document.getElementById('cart-total');
    const hiddenInputsContainer = document.getElementById('hidden-form-inputs');

    // Ensure all required elements exist before proceeding
    if (!cartItemsBody || !cartTotalElement || !hiddenInputsContainer) {
        console.error("Cart UI elements could not be found in the DOM.");
        return;
    }

    // 2. Clear previous content
    cartItemsBody.innerHTML = '';
    hiddenInputsContainer.innerHTML = '';

    // 3. Build new content
    if (cartData && cartData.items && cartData.items.length > 0) {
        let allRowsHTML = '';
        let allInputsHTML = '';

        cartData.items.forEach(item => {
            const subtotal = item.quantity * item.price;

            // Build the visible table row
            allRowsHTML += `
                <tr>
                    <td>${item.name}</td>
                    <td>${item.quantity}</td>
                    <td>$${item.price.toFixed(2)}</td>
                    <td>$${subtotal.toFixed(2)}</td>
                    <td><button type="button" class="btn-delete-product" data-name="${item.name}">X</button></td>
                </tr>
            `;

            // Build the hidden form inputs
            allInputsHTML += `<input type="hidden" name="name" value="${item.name}">`;
            allInputsHTML += `<input type="hidden" name="quantity" value="${item.quantity}">`;
        });

        // 4. Update the DOM once with the new content
        cartItemsBody.innerHTML = allRowsHTML;
        hiddenInputsContainer.innerHTML = allInputsHTML;

    } else {
        const emptyCartRow = '<tr><td colspan="5">Your cart is empty</td></tr>';
        cartItemsBody.innerHTML = emptyCartRow;
    }

    // 5. Update the total price
    cartTotalElement.textContent = cartData.total ? cartData.total.toFixed(2) : '0.00';
}