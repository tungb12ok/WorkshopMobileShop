<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Mobile"%>
<%@page import="model.MobileDAO"%>
<%@page import="java.sql.SQLException"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mobile Shop</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h1>Welcome to Mobile Shop</h1>

            <c:if test="${user == null}">
                <a href="login.jsp" class="btn btn-primary">Login</a>
            </c:if>
            <c:if test="${mess != null}">
                <p class="text-success">${mess}</p>
            </c:if>

            <c:if test="${user != null}">
                <h2>Mobile List</h2>
                <c:if test="${user.role eq 1}">
                    <a href="CreateController" class="btn btn-success">Create Mobile</a>
                </c:if>
                <h1>Search Mobiles</h1>
                <form action="search" method="get">
                    <div class="row mb-3">
                        <div class="col-md-3">
                            <label for="minPrice" class="form-label">Minimum Price:</label>
                            <input type="number" class="form-control" id="minPrice" name="minPrice" value="${minPrice}" required>
                        </div>
                        <div class="col-md-3">
                            <label for="maxPrice" class="form-label">Maximum Price:</label>
                            <input type="number" class="form-control" id="maxPrice" name="maxPrice" value="${maxPrice}" required>
                        </div>
                        <div class="col-md-3">
                            <hr>
                            <button type="submit" class="btn btn-primary">Search</button>
                        </div>
                    </div>
                </form>
                <c:if test="${user.role eq 0}">
                    <a href="ViewCart" class="btn btn-info">View Cart</a>
                </c:if>
                <table class="table">
                    <!-- Table Header -->
                    <thead>
                        <tr>
                            <th>Mobile ID</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Mobile Name</th>
                            <th>Year of Production</th>
                            <th>Quantity</th>
                            <th>Not for Sale</th>
                            <!-- Add an additional column for actions based on user role -->
                            <c:if test="${user.role eq 1}">
                                <th>Actions</th>
                                </c:if>
                        </tr>
                    </thead>
                    <!-- Table Body -->
                    <tbody>
                        <c:forEach var="mobile" items="${mobileList}">
                            <tr>
                                <td>${mobile.mobileId}</td>
                                <td>${mobile.description}</td>
                                <td>${mobile.price}</td>
                                <td>${mobile.mobileName}</td>
                                <td>${mobile.yearOfProduction}</td>
                                <td>${mobile.quantity}</td>
                                <td>${mobile.notSale}</td>
                                <!-- Display actions based on user role -->
                                <c:if test="${user.role eq 1}">
                                    <td>
                                        <!-- Add action buttons for user role 1 (Staff) -->
                                        <a href="UpdateController?mobileId=${mobile.mobileId}" class="btn btn-warning">Update</a>
                                        <a href="DeleteController?mobileId=${mobile.mobileId}" class="btn btn-danger">Delete</a>
                                    </td>
                                </c:if>
                                <!-- Add action button for user role 0 (Regular User) -->
                                <c:if test="${user.role eq 0}">
                                    <td>
                                        <a href="AddToCart?id=${mobile.mobileId}" class="btn btn-success">Add to Cart</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </body>
</html>
