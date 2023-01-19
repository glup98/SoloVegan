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
    <title>Bienvenido a SoloVegan|Crear certificados</title>
    <link rel="icon" href="/images/newLogo.ico">
    <link rel="icon" href="/images/SoloVegan_Icon.ico">
    <link rel="stylesheet" href="/css/bodyColor.css">
    <link rel="stylesheet" href="/css/navBar.css">
    <link rel="stylesheet" href="/css/appContainer.css">
    <link rel="stylesheet" href="/css/footer.css">
    <link rel="stylesheet" href="/css/home.css">
    <link rel="stylesheet" href="/css/createAll.css">
</head>
<body>
        <!-- barra de navegacion -->
        <nav class="navbar fixed-top">
            <div class="container-fluid">
            </div>
                <img  class="img-logo" src="/images/newLogoedit.png" alt="logo">
                <a class="navbar-brand" href="/"> <span style ="font-weight: bold;">Solo</span><span style ="font-style:italic">Vegan</span></a>
        </nav> 
        <div class="app-container">
    <h1 >Create a Certificate: </h1>
    <form:form action="/create/certificate" method="post" modelAttribute="certificate">
		<p>
			<form:label path="typeCertificate">Name:</form:label>
			<form:input path="typeCertificate" class="" id="form" />
			<form:errors path="typeCertificate" />
		</p>
        <button class="button">Submit</button>
    </form:form>
</div>
</body>
</html>