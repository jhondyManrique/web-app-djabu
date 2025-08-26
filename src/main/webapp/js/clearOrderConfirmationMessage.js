
/**
 * @file Manages UI updates related to order confirmation messages.
 * @author [Jhondy Manrique]
 */

/**
 * Clears the order confirmation message from the DOM.
 *
 * This function finds the specific div element responsible for showing the
 * outcome of an order (whether it was successful or not) and resets its
 * content, effectively making it disappear from the view until a new
 * message is set.
 */

function clearOrderConfirmationMessage(){
    const orderConfirmationDiv = document.getElementById("order-confirmation");

        // Check if the element exists before trying to modify it
        if (orderConfirmationDiv) {
            orderConfirmationDiv.innerHTML = '';
        }
}