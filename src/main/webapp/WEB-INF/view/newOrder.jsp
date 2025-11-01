<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>new Order</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css" />
  </head>
  <body>
    <div class="container">
      <div class="container_nav">
        <div class="container_logo">
          <img src="${pageContext.request.contextPath}/assets/img/dbajulogo.jpg" alt="djabu logo" />
        </div>
        <nav>
                          <button class="menu_toggle">
                            <img src="${pageContext.request.contextPath}/assets/icons/menu.jpg" alt="" />
                          </button>
                          <div class="container_buscar">
                            <label for=""
                              ><img class="buscar" src="${pageContext.request.contextPath}/assets/icons/buscar.jpg" alt="icono buscar"
                            /></label>
                            <input type="text" />
                          </div>
                          <ul class="menu">
                            <li>
                              <a href="/dashboard"><img src="${pageContext.request.contextPath}/assets/icons/inicio.jpg" alt="" />Home</a>
                            </li>
                            <li>
                              <a href=""><img src="${pageContext.request.contextPath}/assets/icons/perfil.jpg" alt="" />Profile</a>
                            </li>
                            <li>
                              <a href=""><img src="${pageContext.request.contextPath}/assets/icons/clientes.jpg" alt="" />Clients</a>
                            </li>
                            <li>
                              <a href=""><img src="${pageContext.request.contextPath}/assets/icons/empleados.jpg" alt="" />Employees</a>
                            </li>
                            <li>
                              <a href="/products/add"
                                ><img src="${pageContext.request.contextPath}/assets/icons/agregar.jpg" alt="" />Add Product</a
                              >
                            </li>
                            <li>
                              <a href="/orders"><img src="${pageContext.request.contextPath}/assets/icons/ventas.jpg" alt="" />Orders</a>
                            </li>

                          </ul>
                        </nav>
      </div>
      <div class="container_table">
        <h1 class="inv">INVENTORY</h1>
        <table>
          <thead>
            <tr>
              <th>Product</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>+</th>

            </tr>
          </thead>
          <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td><c:out value="${product.name}" /></td>
                    <td><c:out value="${product.price}" /></td>
                    <td><input type="number" value="1" min="1" class="quantityInput" style="width: 60px;"></td>
                    <td><button type="button" class="btn-add-product"
                    data-name="${product.name}"
                    data-price="${product.price}">
                    ADD TO CART</button></td>
                </tr>
            </c:forEach>

            <c:if test="${empty products}">
                <tr>
                    <td colspan="4" style="text-align:center; padding: 20px;">
                        There are no product yet, add the first one!!
                    </td>
                </tr>
            </c:if>
          </tbody>
        </table>
        <footer><h3>&copy copyright</h3></footer>
      </div>
       <div class="carrito-compras">
                  <h2 class="carrito">Shopping cart</h2>
                  <form action="/orders/save" method="post">
                      <div class="tabla-carrito-container">
                          <table class="tabla-carrito">
                              <thead>
                                  <tr>
                                      <th>Product</th>
                                      <th>Quantity</th>
                                      <th>Price</th>
                                      <th>Subtotal</th>
                                      <th>Action</th>
                                  </tr>
                              </thead>
                              <tbody id="cart-items">

                                  <tr><td colspan="5">Your cart is empty</td></tr>
                              </tbody>
                          </table>
                      </div>

                      <div class="carrito-total">
                          <h3>Total: $<span id="cart-total">0.00</span></h3>
                      </div>

                      <%-- Este div contendrá los inputs ocultos para enviar al servlet final --%>
                      <div id="hidden-form-inputs"></div>

                      <button type="submit" class="btn-save-order">SAVE ORDER</button>
                      <div id="confirmation-order">

                        <%
                            String orderConfirmationMessage = (String) session.getAttribute("confirmation");
                            if(orderConfirmationMessage != null){
                         %>
                            <p  style="color:red; margin:0; padding:0; text-align:center;"><%= orderConfirmationMessage %></p>
                        <%
                            }
                        %>
                      </div>
                  </form>
              </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/clearOrderConfirmationMessage.js"></script>
    <script>
        const cartUrl = '${pageContext.request.contextPath}/cart';
    </script>
    <script src="${pageContext.request.contextPath}/js/updateCartView.js"></script>
    <script src="${pageContext.request.contextPath}/js/addProductToCart.js"></script>
    <script src="${pageContext.request.contextPath}/js/deleteProductFromCart.js"></script>
    <script>const cart = ${cart}</script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
    <script src="${pageContext.request.contextPath}/js/cart.js"></script>



  </body>
</html>
