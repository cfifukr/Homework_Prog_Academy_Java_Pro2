<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read file</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
</head>
<body>

<table class="table table-striped">
    <thead>
    <tr>
        <td><b>Name</b></td>
        <td><b>Surname</b></td>
        <td><b>Phone</b></td>
        <td><b>E-mail</b></td>
        <td><b>Group</b></td>
    </tr>
    </thead>
    <c:forEach items="${contacts}" var="contact">
        <tr>
            <td>${contact.name}</td>
            <td>${contact.surname}</td>
            <td>${contact.phone}</td>
            <td>${contact.email}</td>
            <c:choose>
                <c:when test="${contact.group ne null}">
                    <td>${contact.group.name}</td>
                </c:when>
                <c:otherwise>
                    <td>Default</td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>

<br>
<div class="d-grid gap-2 col-6 mx-auto">
    <a href ="save_contacts?filePath=${filePath}" class="btn btn-warning">Add students</a>
</div>


</body>
</html>