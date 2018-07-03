<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<html>
<head>
    <title>Meals</title>
    <style type="text/css">
        tr.col1 { color: coral; }
        tr.col2 { color: green; }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table>
    <thead>
        <tr>
            <th>Время</th>
            <th>Описание</th>
            <th>Калории</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${meals}" var="current">
            <tr class="${current.exceed ? 'col1':'col2'}">
                <td><javatime:format value="${current.dateTime}" style="MS"/></td>
                <td><c:out value="${current.description}"/></td>
                <td><c:out value="${current.calories}" /></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>