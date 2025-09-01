<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>details</title>
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
                <label for="">
                    <img class="buscar" src="${pageContext.request.contextPath}/assets/icons/buscar.jpg" alt="icono buscar"/>
                </label>
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
                    <a href="/products/add"><img src="${pageContext.request.contextPath}/assets/icons/agregar.jpg" alt="" />Add Product</a>
                </li>
                <li>
                    <a href="/orders"><img src="${pageContext.request.contextPath}/assets/icons/ventas.jpg" alt="" />Orders</a>
                </li>
            </ul>
        </nav>
      </div>
      <div class="container_table">
        <h1 class="inv">DETAILS</h1>
        <table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Quantity</th>
              <th>Price</th>
              <th>subtotal</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="detail" items="${details}">
                <tr>
                    <td><c:out value="${detail.name}" /></td>
                    <td><c:out value="${detail.quantity}" /></td>
                    <td><c:out value="${detail.price}" /></td>
                    <td><c:out value="${detail.quantity * detail.price}" /></td>
                </tr>
            </c:forEach>

            <c:if test="${empty details}">
                <tr>
                    <td colspan="4" style="text-align:center; padding: 20px;">
                        this order is empty!!
                    </td>
                </tr>
            </c:if>
          </tbody>
        </table>
        <div class="carrito-total">
            <h3>Total: $<span id="cart-total">${total}</span></h3>
        </div>
        <footer><h3>&copy copyright</h3></footer>
      </div>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
  </body>
</html>
