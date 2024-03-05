<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Mobile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h1 class="card-title">Add Mobile</h1>
            </div>
            <div class="card-body">
                <form action="CreateController" method="post">
                    <div class="mb-3">
                        <label for="mobileId" class="form-label">Mobile ID:</label>
                        <input type="text" class="form-control" id="mobileId" name="mobileId" required>
                    </div>

                    <div class="mb-3">
                        <label for="description" class="form-label">Description:</label>
                        <input type="text" class="form-control" id="description" name="description" required>
                    </div>

                    <div class="mb-3">
                        <label for="price" class="form-label">Price:</label>
                        <input type="number" class="form-control" id="price" name="price" required>
                    </div>

                    <div class="mb-3">
                        <label for="mobileName" class="form-label">Mobile Name:</label>
                        <input type="text" class="form-control" id="mobileName" name="mobileName" required>
                    </div>

                    <div class="mb-3">
                        <label for="yearOfProduction" class="form-label">Year of Production:</label>
                        <input type="number" class="form-control" id="yearOfProduction" name="yearOfProduction" required>
                    </div>

                    <div class="mb-3">
                        <label for="quantity" class="form-label">Quantity:</label>
                        <input type="number" class="form-control" id="quantity" name="quantity" required>
                    </div>

                    <div class="form-check mb-3">
                        <input type="checkbox" class="form-check-input" id="notSale" name="notSale">
                        <label class="form-check-label" for="notSale">Not for Sale</label>
                    </div>

                    <button type="submit" class="btn btn-primary">Add Mobile</button>
                </form>
            </div>
            <div class="card-footer">
                <a href="home.jsp" class="btn btn-secondary">Back to Home</a>
            </div>
        </div>
    </div>
</body>
</html>
