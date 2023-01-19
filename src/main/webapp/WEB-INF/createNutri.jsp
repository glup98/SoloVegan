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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
        crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous">
    </script>
    <title>Añade información nutricional</title>
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
    <h1>Añade la información nutricional:</h1>
    <form:form action="/create/nutriInfo" method="post" modelAttribute="nutri">
		<p>
			<form:label path="description">Nombre de información nutricional:</form:label>
            <br>
			<form:input path="description" class="" id="form"/>
			<form:errors path="description"/>
		</p>
        <button class="button">Enviar</button>
    </form:form>
    <!-- tabla con todos los tipos de informaciones nutricionales -->

    <br>
    <table class="table">
        <thead>
            <tr>
                <th scope="col">Información Nutricional</th>
                <th scope="col"> Agrega volumen y porción</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${allNutritionalInformation}" var="nutritionalInformation">
            <tr>
                <td>
                    <c:out value="${nutritionalInformation.description}"></c:out>
                </td>
                <td>
                    <a href="/product/${productId}/nutri/${nutritionalInformation.id}">Introduce el volumen y la porción</a>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Añade arriba y veras porciones y volumenes abajo</h2>
    <!-- tabla con todas las informaciones nutricionales y sus cantidades -->
    <table class="table">
        <thead>
            <tr>
                <th scope="col">Informacion Nutricional</th>
                <th scope="col">Volumen</th>
                <th scope="col">Porción</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${quantities}" var="quantity">
            <tr>
                <td>
                    <c:out value="${quantity.nutritionalInformation.description}"></c:out>
                </td>
                <td>
                    <c:out value="${quantity.volume}"></c:out>
                </td>
                <td>
                    <c:out value="${quantity.portion}"></c:out>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href="/store/product" class="button">Enviar</a>
</div>
</body>
</html>