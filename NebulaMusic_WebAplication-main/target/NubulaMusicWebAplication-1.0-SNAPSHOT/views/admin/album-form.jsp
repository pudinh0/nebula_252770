<%-- 
    Document   : album-form
    Created on : 17 mar 2026, 7:13:16 p.m.
    Author     : Adel
--%>

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
            <c:if test ="${album == null}">
                Nuevo album

            </c:if>

            <c:if test ="${album != null}">
                Editar album

            </c:if>    

            <c:if test ="${error != null}">
                <p style="color:red">  ${error}</p>
            </c:if>    

            <form action="albums" method="post" enctype="multipart/form-data"> 
                <input type="hidden" name="id" value="${album.id}">
                <label> Titulo</label>
                <br><!-- comment -->
                <input type="text" name ="titulo" value="${album.titulo}" required>

                <label> Titulo</label>
                <br><!-- comment -->
                <textarea  name ="descripcion"required> ${album.descripcion}   </textarea> 

                <br> <br><!-- comment -->
                <input type ="file" name ="imagen" accept ="image/png" required><!-- comment -->
                <br><br><!-- comment -->
                <button type ="submit"> guardar </button>
            </form>

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
