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
    <title>Crea un producto</title>
    <link rel="stylesheet" href="/css/appContainer.css">
    <link rel="stylesheet" href="/css/bodyColor.css">
    <link rel="stylesheet" href="/css/navBar.css">
    <link rel="stylesheet" href="/css/footer.css">
    <link rel="stylesheet" href="/css/createAll.css">
    <link rel="icon" href="/images/SoloVegan_Icon.ico">
</head>
<body>
    <nav class="navbar fixed-top">
        <div class="container-fluid">
            </div>
            <img  class="img-logo" src="/images/newLogoedit.png" alt="logo">
            <a class="navbar-brand" href="/"> <span style ="font-weight: bold;">Solo</span><span style ="font-style:italic">Vegan</span></a>
    </nav> 
    <div class="app-container">    
        <h2>Sube un producto:</h2>
        <form:form action="/create/product" method="post" modelAttribute="product" enctype="multipart/form-data">
            <div id="formBox">
            <p>
                <form:label path="name">Nombre del producto:</form:label>
                <form:errors path="name"/>
                <form:input path="name" class="form-control" id="form" />
            </p>
            </div>
            <div id="formBox">
            <p>
                <form:label path="description">Descripción del producto:</form:label>
                <form:errors path="description"/>
                <form:textarea path="description" class="form-control" id="form" />
            </p>
        </div>
        <div id="formBox">
            <p>
                <form:label path="portionValue">Tamaño en (gr/ml):</form:label>
                <form:errors path="portionValue"/>
                <form:input path="portionValue" class="form-control" id="form" />
            </p>
        </div>
        <div id="formBox">
            <div class="mb-3">
                <label for="formFile" class="form-label">Selecciona una imagen:</label>
                <input name="productfile" class="form-control" type="file" id="formFile">
            </div>
        </div>
        <button class="button">Enviar</button>
        </form:form>
    </div>
</body>
</html>