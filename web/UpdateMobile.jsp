<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Update Mobile</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <c:if test="${mess != null}">
                <p class="text-warning">${mess}</p>
            </c:if>
            <h1>Update Mobile</h1>
            <form action="UpdateController" method="post">
                <input type="hidden" name="mobileId" value="${mobile.mobileId}">

                <div class="mb-3">
                    <label for="description" class="form-label">Description:</label>
                    <input type="text" class="form-control" id="description" name="description" value="${mobile.description}" required>
                </div>

                <div class="mb-3">
                    <label for="price" class="form-label">Price:</label>
                    <input type="number" class="form-control" id="price" name="price" value="${mobile.price}" required>
                </div>

                <div class="mb-3">
                    <label for="mobileName" class="form-label">Mobile Name:</label>
                    <input type="text" class="form-control" id="mobileName" name="mobileName" value="${mobile.mobileName}" required>
                </div>

                <div class="mb-3">
                    <label for="yearOfProduction" class="form-label">Year of Production:</label>
                    <input type="number" class="form-control" id="yearOfProduction" name="yearOfProduction" value="${mobile.yearOfProduction}" required>
                </div>

                <div class="mb-3">
                    <label for="quantity" class="form-label">Quantity:</label>
                    <input type="number" class="form-control" id="quantity" name="quantity" value="${mobile.quantity}" required>
                </div>

                <div class="form-check mb-3">
                    <input type="checkbox" class="form-check-input" id="notSale" name="notSale" ${mobile.notSale ? 'checked' : ''}>
                    <label class="form-check-label" for="notSale">Not for Sale</label>
                </div>

                <button type="submit" class="btn btn-primary">Update Mobile</button>
            </form>
            <a href="home.jsp" class="btn btn-secondary mt-3">Back to Home</a>
        </div>
    </body>
</html>
