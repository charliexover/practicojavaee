<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestión de Trabajadores de la Salud</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

    <h1 class="mb-4">Gestión de Trabajadores de la Salud</h1>

    <!-- LISTADO -->
    <h2>Listado</h2>
    <ul class="list-group mb-4">
        <c:forEach var="t" items="${trabajadores}">
            <li class="list-group-item">
                <strong>${t.nombre}</strong> — Matrícula: ${t.matricula} — Fecha: ${t.fechaRegistro}
            </li>
        </c:forEach>
    </ul>

    <!-- FORMULARIO DE REGISTRO -->
    <h2>Registrar nuevo trabajador</h2>
    <form action="registrar" method="post" class="mb-4">
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" id="nombre" name="nombre" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="matricula" class="form-label">Matrícula</label>
            <input type="text" id="matricula" name="matricula" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-primary">Registrar</button>
    </form>

    <!-- FORMULARIO DE BÚSQUEDA -->
    <h2>Buscar trabajador por matrícula</h2>
    <form action="buscar" method="get" class="mb-4">
        <div class="mb-3">
            <label for="matriculaBuscar" class="form-label">Ingrese matrícula</label>
            <input type="text" id="matriculaBuscar" name="matricula" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-success">Buscar</button>
    </form>

    <!-- RESULTADO DE BÚSQUEDA -->
    <c:if test="${not empty resultado}">
        <div class="alert alert-info">
            <h4>Resultado:</h4>
            <p>
                <strong>${resultado.nombre}</strong> — Matrícula: ${resultado.matricula} — Fecha: ${resultado.fechaRegistro}
            </p>
        </div>
    </c:if>

    <c:if test="${empty resultado and param.matricula != null}">
        <div class="alert alert-warning">
            No se encontró ningún trabajador con matrícula <strong>${param.matricula}</strong>.
        </div>
    </c:if>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

