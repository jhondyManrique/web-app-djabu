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
              <a href="/home"><img src="${pageContext.request.contextPath}/assets/icons/inicio.jpg" alt="" />Inicio</a>
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
              <a href="/ventas/nueva"><img src="${pageContext.request.contextPath}/assets/icons/ventas.jpg" alt="" />Ventas</a>
            </li>
            <li>
              <a href=""><img src="${pageContext.request.contextPath}/assets/icons/pedidos.jpg" alt="" />Pedidos</a>
            </li>
          </ul>
        </nav>
      </div>
      <div class="container_table">
        <h1 class="inv">HISTORIAL</h1>
        <table>
          <thead>
            <tr>
              <th>Id</th>
              <th>Fecha</th>
              <th>Total</th>
              <th>Detalles</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="venta" items="${ventas}">
                <tr>
                    <td><c:out value="${venta.id}" /></td>
                    <td><c:out value="${venta.fecha}" /></td>
                    <td><c:out value="${venta.total}" /></td>
                    <td><a href="${pageContext.request.contextPath}/ventas/detalles?id=${venta.id}">
                        <button type="button" class="btn-detalles">Ver Detalles</button>
                    </a></td>
                </tr>
            </c:forEach>

            <c:if test="${empty ventas}">
                <tr>
                    <td colspan="4" style="text-align:center; padding: 20px;">
                        No hay ventas en el historial. ¡Añade la primera!
                    </td>
                </tr>
            </c:if>
          </tbody>
        </table>
        <footer><h3>&copy copyright</h3></footer>
      </div>


    <script src="${pageContext.request.contextPath}/js/actualizarVistaCarrito.js"></script>
    <script src="${pageContext.request.contextPath}/js/agregarAlCarrito.js"></script>
    <script src="${pageContext.request.contextPath}/js/eliminarDelCarrito.js"></script>
    <script>const carritoInicial = ${carritoInicial};</script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
    <script src="${pageContext.request.contextPath}/js/carrito.js"></script>



  </body>
</html>
