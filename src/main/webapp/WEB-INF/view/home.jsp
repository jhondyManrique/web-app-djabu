<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Home</title>
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
              <a href=""><img src="${pageContext.request.contextPath}/assets/icons/inicio.jpg" alt="" />Inicio</a>
            </li>
            <li>
              <a href=""><img src="${pageContext.request.contextPath}/assets/icons/perfil.jpg" alt="" />Perfil</a>
            </li>
            <li>
              <a href=""><img src="${pageContext.request.contextPath}/assets/icons/clientes.jpg" alt="" />Clientes</a>
            </li>
            <li>
              <a href=""><img src="${pageContext.request.contextPath}/assets/icons/empleados.jpg" alt="" />Empleados</a>
            </li>
            <li>
              <a href="/addProduct"
                ><img src="${pageContext.request.contextPath}/assets/icons/agregar.jpg" alt="" />Agregar Producto</a
              >
            </li>
            <li>
              <a href=""><img src="${pageContext.request.contextPath}/assets/icons/ventas.jpg" alt="" />Ventas</a>
            </li>
            <li>
              <a href=""><img src="${pageContext.request.contextPath}/assets/icons/pedidos.jpg" alt="" />Pedidos</a>
            </li>
          </ul>
        </nav>
      </div>
      <div class="container_table">
        <h1 class="inv">INVENTARIO</h1>
        <table>
          <thead>
            <tr>
              <th>Producto</th>
              <th>Precio</th>
              <th>Cantidad</th>
              <th>+</th>

            </tr>
          </thead>
          <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td><c:out value="${product.productName}" /></td>
                    <td><c:out value="${product.unitPrice}" /></td>
                    <td><input type="number" value="1" min="1" class="cantidad-input" style="width: 60px;"></td>
                    <td><button type="button" class="btn-agregar"
                    data-nombre="${product.productName}"
                    data-precio="${product.unitPrice}">
                    agregar al carrito</button></td>
                </tr>
            </c:forEach>

            <c:if test="${empty products}">
                <tr>
                    <td colspan="4" style="text-align:center; padding: 20px;">
                        No hay productos en el inventario. ¡Añade el primero!
                    </td>
                </tr>
            </c:if>
          </tbody>
        </table>
        <footer><h3>&copy copyright</h3></footer>
      </div>
       <div class="carrito-compras">
                  <h2 class="carrito">Carrito de Venta</h2>
                  <form action="/ventas" method="post">
                      <div class="tabla-carrito-container">
                          <table class="tabla-carrito">
                              <thead>
                                  <tr>
                                      <th>Producto</th>
                                      <th>Cantidad</th>
                                      <th>Precio</th>
                                      <th>Subtotal</th>
                                      <th>Acción</th>
                                  </tr>
                              </thead>
                              <tbody id="cart-items">
                                  <%-- El JavaScript llenará esta tabla dinámicamente --%>
                                  <tr><td colspan="5">El carrito está vacío</td></tr>
                              </tbody>
                          </table>
                      </div>

                      <div class="carrito-total">
                          <h3>Total: $<span id="cart-total">0.00</span></h3>
                      </div>

                      <%-- Este div contendrá los inputs ocultos para enviar al servlet final --%>
                      <div id="hidden-form-inputs"></div>

                      <button type="submit" class="btn-guardar-venta">Guardar Venta</button>
                  </form>
              </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/actualizarVistaCarrito.js"></script>
    <script src="${pageContext.request.contextPath}/js/agregarAlCarrito.js"></script>
    <script src="${pageContext.request.contextPath}/js/eliminarDelCarrito.js"></script>
    <script>const carritoInicial = ${carritoInicial};</script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
    <script src="${pageContext.request.contextPath}/js/carrito.js"></script>



  </body>
</html>
