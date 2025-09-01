<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Add Product</title>
  </head>
  <body>
    <div class="container">
      <div class="container_header">
        <img src="${pageContext.request.contextPath}/assets/img/dbajulogo.jpg"  alt="logo djabu" />
        <div class="container_h1">
          <h1>ADD A PRODUCT</h1>
        </div>
      </div>
      <%
      String message = (String) request.getAttribute("errorMessage");
      if(message != null){
      %>
      <p style="color:red; margin:0; padding:0; text-align:center;"><%= message %></p>
      <%
      }
      %>
      <div class="container_form">
        <form action="/products" method="post">

          <label for="productName">Product Name</label>
          <input type="text" id="productName" name="productName" />

          <label for="unitPrice">Unit Price</label>
          <input type="text" id="unitPrice" name="unitPrice" />

          <button type="submit">ADD</button>
          <a href="/dashboard">
            <button type="button">Go Home</button>
          </a>
        </form>
      </div>
    </div>
    <footer><h3>&copy copyright</h3></footer>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css?v=1" />

  </body>
</html>
