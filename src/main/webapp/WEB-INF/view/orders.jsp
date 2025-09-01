<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Orders</title>
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
        <h1 class="inv">ORDERS</h1>
        <form action="/orders" method="GET">
            <label for="startDate">From:</label>
            <input type="date" id="startDate" name="startDate">

            <label for="endDate">To:</label>
            <input type="date" id="endDate" name="endDate">

            <button type="submit">Filter</button>
        </form>
        <table>
          <thead>
            <tr>
              <th>Id</th>
              <th>Date</th>
              <th>Total</th>
              <th>Details</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td><c:out value="${order.id}" /></td>
                    <td><fmt:formatDate value="${order.date}" pattern="dd/MM/yyyy" /></td>
                    <td><c:out value="${order.total}" /></td>
                    <td><a href="${pageContext.request.contextPath}/orders/details?id=${order.id}">
                        <button type="button" class="btn-order-details">VIEW DETAILS</button>
                    </a></td>
                </tr>
            </c:forEach>

            <c:if test="${empty orders}">
                <tr>
                    <td colspan="4" style="text-align:center; padding: 20px;">
                        There are no orders yet, add the first one!!
                    </td>
                </tr>
            </c:if>
          </tbody>
        </table>
         <a href="/orders/new-order"><button type="button" class="btn-new-order">NEW ORDER</button></a>

        <footer><h3>&copy copyright</h3></footer>
      </div>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>

  </body>
</html>
