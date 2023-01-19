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

    <div class="app-container">
        <!-- <h2><c:out value="${product.name}"></c:out></h2> -->
        <form:form action="/store/product" method="post" modelAttribute="storeHasProduct">
            <p>
                <form:select path="product">
                    <c:forEach items="${lProducts}" var="lP">
                        <form:option value="${lP.id}" label="${lP.name}"/> 
                    </c:forEach>
                </form:select>
                <!-- <c:forEach items="${lProducts}" var="lP">
                    <c:out value="${lP.name}"></c:out>
                    <form:radiobutton path="product" value="${lP.id}" />
                </c:forEach> -->
            </p>
            <p>
                <c:forEach items="${stores}" var="st">
                    <c:out value="${st.name}"></c:out>
                    <form:radiobutton path="store" value="${st.id}" />
                </c:forEach>
            </p>
            <p>
                <form:label path="normalPrice">Normal Price:</form:label>
                <form:input path="normalPrice" class="form-control" type="number" />
            </p>
            <p>
                <form:label path="offerPrice">Offer Price:</form:label>
                <form:input path="offerPrice" class="form-control" type="number" />
            </p>
    
            <div class="form-group">
                <label for="imgRouteLinks">Introduce el enlace a la tienda de tu producto</label>
                <input type="text" class="form-control" id="imgRouteLinks" name="imgRouteLinks" />
            </div>
            <button class="button">Submit</button>
        </form:form>
    </div>

        <!-- footer -->
    <footer>
        <div id="contacts"><a href="contact">Contáctanos!</a></div>
        <div class="icons"><i class="fa-brands fa-facebook"></i></div>
        <div class="icons"><i class="fa-brands fa-instagram"></i></div>
        <div class="icons"><i class="fa-brands fa-twitter"></i></div>
    </footer>
</body>
</html>