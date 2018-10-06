<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table border="1">
    <thead>
        <tr>
            <th>Date\Time</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="meal" items="${mealList}">
            <tr style="${meal.exceed ? 'color:red' : 'color:green'}">
                <td><javatime:format value="${meal.dateTime}" style="MS" /></td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>