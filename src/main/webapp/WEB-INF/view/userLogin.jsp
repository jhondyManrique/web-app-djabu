<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta
            name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
    />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
    <title>userLogin</title>
</head>
<body>

     <div class="container">
          <div class="container_logo">
            <img src="${pageContext.request.contextPath}/assets/img/dbajulogo.jpg" alt="logo djabu" />
          </div>
          <div class="container_form">
            <div class="container_h1">
              <h1>
                Welcome <br />
                Back
              </h1>
              <%
              String message = (String) request.getAttribute("Message");
              if(message != null){
              %>
                <p style="color:red; margin:0; padding:0; text-align:center;"><%= message %></p>
              <%
              }
              %>
            </div>
            <form action="/login" method="post">
              <label for="username">Username</label>
              <input type="text" id="username" name="email" />

              <label for="password">Password</label>
              <input type="password" id="password" name="password" />

              <div class="checkbox-container">
                <input type="checkbox" id="remember" name="remember" />
                <label for="remember">Remember me</label>
              </div>

              <button type="submit">Sign In</button>
              <a href="/register">
                <button type="button">Sign Up</button>
              </a>
            </form>
          </div>
        </div>
        <footer><h3>&copy copyright</h3></footer>
</body>
</html>