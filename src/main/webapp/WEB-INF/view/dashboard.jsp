<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>dashboard</title>
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
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
  </body>
</html>
