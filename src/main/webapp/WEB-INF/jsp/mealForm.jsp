<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>${meal.isNew() ? 'Create meal' : 'Edit meal'}</h2>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="${pageContext.request.contextPath}/meals">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd><label>
                <input type="datetime-local" value="${meal.dateTime}" name="dateTime" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><label>
                <input type="text" value="${meal.description}" size=40 name="description" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><label>
                <input type="number" value="${meal.calories}" name="calories" required>
            </label></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
