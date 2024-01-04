<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>New Group</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
    <br>
    <form method = "post">
        <div class="container">
            <div class="input-group mb-3">
                <input type="file" class="form-control" id="inputGroupFile02" name = "pathFile">
            </div>
            <div class="d-grid gap-2 col-6 mx-auto">
                <button  class="btn btn-warning" type="submit" formaction="/save_to_file">Save to file</button>
                <button class="btn btn-warning" type="submit" formaction="/upload_from_file">Get contacts from file</button>
            </div>
        </div>
    </form>
    </body>
</html>