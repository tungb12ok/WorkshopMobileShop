<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%@page import="model.Cart"%>
<%@page import="model.Mobile"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User's Cart</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h1>User's Cart</h1>

            <c:if test="${userCart != null}">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Mobile ID</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="userEntry" items="${userCart.items}">
                            <c:if test="${userEntry.key eq user.getUserId()}">
                                <c:forEach var="entry" items="${userEntry.value}">
                                    <tr>
                                        <td>${entry.key}</td>
                                        <td>${entry.value}</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${empty userCart}">
                <p>The cart is empty for this user.</p>
            </c:if>
        </div>
    </body>
</html>
