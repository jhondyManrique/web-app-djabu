<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>userRegister</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css"/>
  </head>
  <body>
    <div class="container">
      <div class="container_header">
        <img src="${pageContext.request.contextPath}/assets/img/dbajulogo.jpg"  alt="logo djabu" />
        <div class="container_h1">
          <h1>REGISTER</h1>
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
        <form action="/users" method="post">
          <label for="identification">Identification</label>
          <input type="text" id="identification" name="identification" />

          <label for="firstname">Firstname</label>
          <input type="text" id="firstname" name="firstname" />

          <label for="lastname">Lastname</label>
          <input type="text" id="lastname" name="lastname" />

          <label for="phone">Phone</label>
          <input type="text" id="phone" name="phone" />

          <label for="email">Email</label>
          <input type="email" id="email" name="email" />

          <label for="password">Password</label>
          <input type="password" id="password" name="password" />

          <label for="confirm_password">Confirm Password</label>
          <input type="password" id="confirm_password" name="confirm_password" />

          <button type="submit">Register</button>
        </form>
      </div>
    </div>
    <footer><h3>&copy copyright</h3></footer>
  </body>
</html>
