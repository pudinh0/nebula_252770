<%-- 
    Document   : mis-albums
    Created on : 17 mar 2026, 7:25:48 p.m.
    Author     : Adel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Nosotros - Nébula</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/styles.css" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>

    <body>
        <%@include file="/WEB-INF/jsp/fragmentos/header.jspf" %>

        <header class="about-header">
            <img src="${pageContext.request.contextPath}/assets/img/header2.jpg" />
            <h1>Mis albums</h1>
        </header>

        <main class="about-main">
            <c:forEach var="album" items="${albums}">
                
                <div style ="border:1px solid #ccc;padding:15px;margin-bottom:15px;width:300px"
                     <img src="${album.imageUrl}" width="250">
                    <h3> ${album.titulo}</h3>
                    <p> ${album.descripcion}</p>
                    <a href="albums?action=edit&id=${album.id}">Editar </a>
                    <a href="albums?action=delete&id=${album.id}"
                       onclick ="return confirm('¿Eliminar album?')">
                        Eliminar
                    </a>
                </div>
            </c:forEach>

        </main>


        <footer>
            <div>
                Síguenos en nuestras redes.
                <div>
                    <a href="www.instagram.com" target="_blank">Instagram</a>
                    <a href="www.facebook.com" target="_blank">Facebook</a>
                    <a href="wwwtiktok.com" target="_blank">Tiktok</a>
                </div>
            </div>

            <p>Copyright Nébula 2025.</p>
            <a href="${pageContext.request.contextPath}/views/aplication/tyc.jsp" target="_blank">Términos y condiciones</a>
        </footer>
    </body>
</html>