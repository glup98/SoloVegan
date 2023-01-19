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
    <h1>Select Nutricional Informaion</h1>
    <h3>${product.name}</h3>
    <!-- para poner las categorias -->
    <form:form action="/product/${product.id}/nutri/${nutritionalInformation.id}" method="post" modelAttribute="quantity">

            <h1><c:out value="${nutritionalInformation.description}"></c:out></h1>

            <div class="mb-3">
                <form:label path="volume" class="form-label">Volume</form:label>
                <form:errors path="volume"/>
                <form:input path="volume" type="text" class="form-control d-inline-block" id="form"/>
            </div>
            <div class="mb-3">
                <form:label path="portion" class="form-label">Portion</form:label>
                <form:errors path="portion"/>
                <form:input path="portion" type="text" class="form-control d-inline-block" id="form"/>
            </div>
        <button class="button">Submit</button>
    </form:form>
</body>
</html>