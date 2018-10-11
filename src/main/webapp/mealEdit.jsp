<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit meal</title>
    <style>
        dd {
            margin-top: 1em; /* Отступ сверху */
        }
        dt {
            margin-top: 1em; /* Отступ сверху */
        }
    </style>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h3>Edit meal</h3>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed" scope="request"/>
    <form method="post" action="meals">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>Date & Time:</dt>
            <dd><input type="datetime-local" value="${meal.dateTime}" name="datetime"></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" size="40" value="${meal.description }" name="description"></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><input type="number" value="${meal.calories}" name="calories"></dd>
        </dl>
        <button type="submit">Save</button>
    </form>
</section>
</body>
</html>