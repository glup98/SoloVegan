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
    <title>Bienvenido a SoloVegan| Resultado de busqueda</title>
    <link rel="stylesheet" href="/css/bodyColor.css">
    <link rel="stylesheet" href="/css/navBar.css">
    <link rel="stylesheet" href="/css/appContainer.css">
    <link rel="stylesheet" href="/css/footer.css">
    <link rel="stylesheet" href="/css/home.css">
    <link rel="stylesheet" href="/css/createAll.css">
    <link rel="stylesheet" href="/css/productImgList.css">
    <link rel="stylesheet" href="/css/certificatesList.css">
    <link rel="stylesheet" href="/css/searchProduct.css">
    <link rel="icon" href="/images/newLogo.ico">
</head>
<body>
    
  <!-- barra de navegacion -->
  <nav class="navbar bg fixed-top">
    <div class="container-fluid">
        <!-- Hamburguesa de filtrar por categorias -->
        <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#categoriesNavBar" aria-controls="offcanvasNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="offcanvas offcanvas-start" tabindex="-1" id="categoriesNavBar" aria-labelledby="categoriesNavBarLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="categoriesNavBarLabel">Categorias</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <div class="list-group">
                    <c:forEach items="${categories}" var="category">
                        <a href="/category/${category.id}" class="list-group-item list-group-item-action">
                            <c:out value="${category.categoryName}"/>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
        <img  class="img-logo" src="/images/newLogoedit.png" alt="logo">
        <a class="navbar-brand" href="/"> <span style ="font-weight: bold;">Solo</span><span style ="font-style:italic">Vegan</span></a>
            <!-- Barra de busqueda -->
            <form action="/search" class="d-flex w-50" role="search" method="post">
                <input class="form-control me-2" type="search" placeholder="Busca tu producto" aria-label="Search" name="name">
                <c:forEach items="${storesSelected}" var="store">
                    <input type="hidden" value="${store.id}" name="storesSelected" id="checkbox1">
                </c:forEach>
                <button class="btn btn-outline-success" type="submit">Buscar</button>
            </form>
            <c:choose>
                <c:when test="${userId==null}">
                    <div class="d-inline-block">
                        <!-- Button modal register -->
                        <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#registerModal">
                            Registrate
                        </button>
                        <!-- Button modal Login -->
                        <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#loginModal">
                            Iniciar Sesion
                        </button>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="navbar-text d-inline-block">Hola, <a href="/dashboard" class="navbar-close d-inline-block"><c:out value="${userSession.firstName}"/></a></p>
                    <a href="/logout" class="navbar-close d-inline-block">| Cerrar Sesión |</a>
                </c:otherwise>
            </c:choose>
        </div>
    </nav> 
<div>
    <div class="app-container d-flex row">
        <div class="filtrar  col-md-3">
            <div id="filtrar">
            <div class="card d-inline-block row col mx-3 my-2 p-3">
                <!-- Filtros de Busqueda -->
                <form method="post" action="/search">
                    <input class="form-control me-2" type="hidden" placeholder="Busca tu producto" aria-label="Search" name="name" value="${name}">
                    <div class="form-group">
                        <h4><label for="checkbox1">Tienda</label></h4>
                        <br>
                        <c:forEach items="${allStores}" var="store">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="${store.id}" name="storesSelected" id="checkbox1"
                                <c:if test="${not empty storesSelected and storesSelected.contains(store)}">
                                    checked
                                </c:if>
                                
                                <label class="form-check-label" for="checkbox1">
                                    <p><c:out value="${store.name}"/></p>
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                    <button type="submit" class="button">Enviar</button>
                </form>
            </div>
        </div>
        </div>
        <!-- Resultados de busqueda -->
        <div class="app-container col mx-2 d-inline-block">
            <div class="searchResults d-flex row">
            <h2 class="tittleSearch">Resultados de tu búsqueda</h2>
            <br>
            <div id="searchResults"></div>
                <c:forEach items="${allStoreHasProductSearch}" var="storeHasProduct">
                    <div class="card mx-2 my-2 col-3 shadow" style="width:18rem">
                        <div class="card-bodys">
                            <a href="/product/${storeHasProduct.product.id}">
                                <div id="img-container">
                                    <img class="img-product" src="${storeHasProduct.product.imgRoute}" alt="${storeHasProduct.product.imgRoute}">
                                </div>
                            </a>
                            <h1 class="font-text"><c:out value="${storeHasProduct.product.name}"/></h1>
                            <c:forEach items="${storeHasProduct.product.certificates}" var="certificate">
                            </c:forEach>
                            <p class="normalprice">Precio Normal: $<c:out value="${storeHasProduct.normalPrice}"/></p>
                            <p class="offerprice">Precio Oferta: $<c:out value="${storeHasProduct.offerPrice}"/></p>
                            <!-- Certificados -->
                            <c:set var="count" value="0" />
                            <c:forEach items="${storeHasProduct.product.certificates}" var="certificate">
                                <c:if test="${count < 3}">
                                    <p id="certificateList"><c:out value="${certificate.typeCertificate}"/></p>
                                    <c:set var="count" value="${count + 1}" />
                                </c:if>
                            </c:forEach>
                            <!-- ... -->
                            <p><c:out value="${storeHasProduct.store.name}"/></p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- ... -->

        <!-- Modal Register -->
    <div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <img  class="img-logo" src="/images/newLogoedit.png" alt="logo">
                    <h1 class="modal-title fs-5" id="registerModalLabel">Crea tu cuenta</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="d-flex justify-content-center">
                        <form:form class="d-inline-block mx-3" action="/register" method="post" modelAttribute="user">
                            <div class="mb-3">
                                <form:label path="firstName" class="form-label">Nombre</form:label>
                                <form:errors path="firstName"/>
                                <form:input path="firstName" type="text" class="form-control d-inline-block" id="form"/>
                            </div>
                            
                            <div class="mb-3">
                                <form:label path="lastName" class="form-label">Apellido</form:label>
                                <form:errors path="lastName"/>
                                <form:input path="lastName" type="text" class="form-control d-inline-block" id="form"/>
                            </div>
                            
                            <div class="mb-3">
                                <form:label path="email" class="form-label">Email</form:label>
                                <p><c:out value = "${emailError}"/></p>
                                <form:errors path="email"/>
                                <form:input path="email" type="email" class="form-control d-inline-block" id="form"/>
                            </div>
    
                            <div class="mb-3">
                                <form:label path="password" class="form-label">Contraseña</form:label>
                                <form:errors path="password"/>
                                <form:password path="password" class="form-control d-inline-block" id="form"/>
                            </div>
    
                            <div class="mb-3">
                                <form:label path="passwordConfirmation" class="form-label">Confirmar contraseña</form:label>
                                <form:errors path="passwordConfirmation"/>
                                <form:password  path="passwordConfirmation" class="form-control d-inline-block" id="form"/>
                            </div>
                                <button type="submit" class="button">Registrarse</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ... -->

    <!-- Modal Login -->
    <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <img  class="img-logo" src="/images/newLogoedit.png" alt="logo">
                    <h1 class="modal-title fs-5" id="loginModalLabel">Iniciar sesión</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="d-flex justify-content-center">
                        <form action="/login" method="post" class="d-inline-block align-top mx-3">
                            <p><c:out value = "${errorLog}"/></p>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input name ="email" type="email" class="form-control d-inline-block" id="form">
                            </div>
                            <div class="mb-3">
                                <label for = "password" class="form-label">Contraseña</label>
                                <input name="password" type="password" class="form-control d-inline-block" id="form">
                            </div>
                            
                            <button type="submit" class="button">Iniciar Sesion</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ... -->
    </div>
    <!-- footer -->
    <footer>
        <div id="contacts"><a href="contact">Contáctanos!</a></div>
        <div class="icons"><i class="fa-brands fa-facebook"></i></div>
        <div class="icons"><i class="fa-brands fa-instagram"></i></div>
        <div class="icons"><i class="fa-brands fa-twitter"></i></div>
        <p><a href="/admin">¿Quieres anunciarte con nosotros?</a></p>
    </footer>

</body>
</html>