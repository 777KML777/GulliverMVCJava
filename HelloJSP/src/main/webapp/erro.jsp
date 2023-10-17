<!doctype html>
<jsp:useBean id="Usuario"
     type="br.com.masters3.beans.Usuario"
     scope="session"/> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" 
          prefix="c"%>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<div class="container my-5">
    <div class="text-center mb-5">
<h2 class="my-1">
Ola <c:out value="${Usuario.nome}"/>
    </h2>
    <p><c:out value="${Usuario.email}"/></p>
   <p>Nivel de Usuario: 
    <c:if test="${Usuario.nivel==1}">Usuario</c:if>
    <c:if test="${Usuario.nivel==2}">Administrador</c:if>
   </p>        
          

   <form method="post" id="dados-conta" action="">
    <div class="row">
      <div class="col-12 col-sm-12 col-md-6">
         <div class="form-group">
           <label for="nome" class="titulos">Nome</label>
             <input type="text" class="form-control" 
              id="nome" name="nome" 
              value="<c:out value="${Usuario.nome}"/>"/>
         </div>
       </div>
    </div>    
</body>
</html>
