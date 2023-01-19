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
<title>Bienvenido a SoloVegan|Panel de Usuario</title>
<link rel="stylesheet" href="/css/bodyColor.css">
<link rel="stylesheet" href="/css/navBar.css">
<link rel="stylesheet" href="/css/appContainer.css">
<link rel="stylesheet" href="/css/footer.css">
<link rel="stylesheet" href="/css/home.css">
<link rel="stylesheet" href="/css/createAll.css">
<link rel="stylesheet" href="/css/productImgList.css">
<link rel="stylesheet" href="/css/certificatesList.css">
<link rel="stylesheet" href="/css/dashboard.css">
<link rel="icon" href="/images/newLogo.ico">
</head>
<body>
    <!-- barra de navegacion -->
    <nav class="navbar  fixed-top">
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
                <button class="btn btn-outline-success" type="submit">Buscar</button>
            </form>
            <div class="navbar-textt">
            <c:out value = "${user.firstName}"/> <c:out value = "${user.lastName}"/> 
        </div>
            <br>
            <a href="/logout" class="navbar-close">| Cerrar Sesión |</a>
            <br> 
        </div>
    </nav>

    <div class="app-container justify-content-center d-flex">
        <div class="d-inline-block">
            <div class="row">
                <!-- Tarjeta de Usuario -->
                <div class="col mx-2 my-2">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <img id="Logo-dashboard" src="/images/newLogo.png" alt="/images/newLogo.ico" class="img-fluid">
                            <h5 class="card-title text-center"><c:out value = "${user.firstName}"/> <c:out value = "${user.lastName}"/></h5>
                            <h6 class="card-subtitle mb-2 text-muted text-center">¿Estás listo para descubrir los mejores productos veganos?</h6>
                            <!-- Button Modal Edit User -->
                            <br>
                            <button type="button" class="button" data-bs-toggle="modal" data-bs-target="#registerModal">
                                Editar datos de usuario
                            </button><br>
                            
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <!-- Tarjeta de Preferencias de Usuario -->
            <div class="row d-inline-block">
                <div class="col mx-2 my-2">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                                <h5 class="card-title text-center">Tus preferencias!</h5>
                                <p class="card-text">
                                    <ul class="list-group">
                                        <c:forEach items="${userPreferences}" var="preference">
                                            <li class="list-group-item">
                                                <c:out value="${preference.typeCertificate}"/>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </p>
                                <!-- Boton que abre el modal para editar preferencias -->
                            <button type="button" class="button-edit" data-bs-toggle="modal" data-bs-target="#editModal">
                                Editar
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Gran tarjeta con productos recomendatos -->
        <div class="col mx-3 d-inline-block">
            <div class="d-flex justify-content-center row">
                <!-- Este condicional comprueba que el usuario tenga preferencias y si no las tiene muestra todos los productos de la pagina -->
                <c:choose>
                        <c:when test="${userPreferences!=null && !userPreferences.isEmpty()}">
                            <!-- Muestra productos que tengan los certificados que el usuario prefiere -->
                            <h1 class="d-flex justify-content-center">Estos productos te pueden interesar!</h1>
                            <c:forEach items="${allStoreHasProductWithPreference}" var="storeHasProduct">
                                <div class="card mx-2 my-2 col-3 shadow" style="width: 18rem;">
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
                        </c:when>
                        <c:otherwise>
                            <!-- Muestra todos los productos-->
                            <h1 class="d-flex justify-content-center">¡Escoge tus preferencias y tendras recomendaciones personalizadas!</h1>
                            <c:forEach items="${allStoreHasProdudct}" var="storeHasProduct">
                                <div class="card mx-2 my-2 col-3 shadow" style="width: 18rem;">
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
                        </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <!-- Modales -->
    <!-- Modal Edit User-->
    <div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="registerModalLabel">Edita tu usuario</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="d-flex justify-content-center">
                        <form:form class="d-inline-block mx-3" action="/editUser" method="post" modelAttribute="user">
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
                                <button type="submit" class="button">Guardar</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ... -->
    <!-- Modal  edit preferences -->
    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="editModalLabel">Crea tu cuenta</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="card col mx-2">
                        <div class="card-body">
                            <h5 class="card-title">Escoge tus preferencias!</h5>
                            <p class="card-text">
                                <div class="d-flex justify-content-center">
                                    <form class="preferencesModal" action="/preferences" method="post">
                                        <input type="hidden" name="preferences" value=""/>
                                        <c:forEach items="${allCertificates}" var="certificate" varStatus="status">
                                            <!-- La propiedad "index" de la clase "status" devuelve el índice de la iteración actual,
                                                que comienza en 0 y se incrementa en 1 en cada iteración. -->
                                                <c:if test="${selectionStatus[status.index] == true}">
                                                    <input type="checkbox" name="preferences" value="${certificate.id}" class="btn-check" id="${certificate.id}" checked autocomplete="off"/>
                                                    <label class="btn btn-outline-success mb-2 mx-1" for="${certificate.id}"><c:out value="${certificate.typeCertificate}"/></label>
                                                </c:if>
                                                <c:if test="${selectionStatus[status.index] == false}" >
                                                    <input type="checkbox" name="preferences" value="${certificate.id}" class="btn-check" id="${certificate.id}" autocomplete="off"/>
                                                <label class="btn btn-outline-success mb-2 mx-1" for="${certificate.id}"><c:out value="${certificate.typeCertificate}"/></label>
                                            </c:if>
                                        </c:forEach>
                                        <input type="submit" class="button" value="Submit"/>
                                    </form>
                                </div>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ... -->
    
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