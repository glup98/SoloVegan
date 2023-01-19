<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/e88ea14770.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
        crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous">
    </script>
    <title>Bienvenido a SoloVegan</title>
    <link rel="stylesheet" href="/css/bodyColor.css">
    <link rel="stylesheet" href="/css/navBar.css">
    <link rel="stylesheet" href="/css/appContainer.css">
    <link rel="stylesheet" href="/css/footer.css">
    <link rel="stylesheet" href="/css/home.css">
    <link rel="stylesheet" href="/css/createAll.css">
    <link rel="icon" href="/images/newLogo.ico">
</head>
<body>
    <nav class="navbar fixed-top">
        <div class="container-fluid">
            </div>
            <img  class="img-logo" src="/images/newLogoedit.png" alt="logo">
            <a class="navbar-brand" href="/"> <span style ="font-weight: bold;">Solo</span><span style ="font-style:italic">Vegan</span></a>
    </nav> 
    <div class="app-container"> 
    <h1>Select properties</h1>
    <h3>${product.name}</h3> 
    <form:form action="/product/${id}/all" method="post" modelAttribute="product">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Categorias</th>
                    <th scope="col">Certificados</th>
                    <th scope="col">Ingredientes</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <c:forEach items="${categoryList}" var="cl">
                            <c:out value="${cl.categoryName}"></c:out>
                            <form:checkbox path="categories" value="${cl}" />
                        </c:forEach>
                        <a href="/create/category">Crea tus categorias</a>
                    </td>
                    <td>
                        <c:forEach items="${certificatesList}" var="cl">
                            <c:out value="${cl.typeCertificate}"></c:out>
                            <form:checkbox path="certificates" value="${cl}" />
                        </c:forEach>
                        <a href="/create/certificate">Crea tus certificados</a>
                    </td>
                    <td>
                        <c:forEach items="${ingredientsList}" var="il">
                            <c:out value="${il.ingredientName}"></c:out>
                        </c:forEach>
                        <a href="/create/ingredient">Introduce ingredientes de tu producto</a>
                    </td>
                </tr>
            </tbody>
        </table>
        <button class="button">Enviar</button>
    </form:form>
    
    <!-- footer -->
    <footer>
        <div id="contacts"><a href="contact">Contáctanos!</a></div>
        <div class="icons"><i class="fa-brands fa-facebook"></i></div>
        <div class="icons"><i class="fa-brands fa-instagram"></i></div>
        <div class="icons"><i class="fa-brands fa-twitter"></i></div>
    </footer>
</body>
</html>