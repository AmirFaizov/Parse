<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<t:mainLayout title="Food Detail">
    <meta charset="UTF-8">
    <div>
        <h1 style="color: #e10a22">${toy.getToyName()}</h1>
        <div class="food-field">
                <span class="field-name">Цена:</span>
                <span class="field-value">${toy.getPrice()} руб.</span>
                <span class="col"><strong>Вы добавили: </strong>0</span>
                <spaxn class="sum" style="
                    position:relative;
                    ">
                       <strong>Сумма заказа: </strong>
                       0 руб.
                   </spaxn>
                <button type="submit" class="btn-success" style="position:relative;
                    left: 30px">Сделать заказ!</button>
                <hr>
            <c:if test="${toy.getId() == 36}">
                <img src="<c:url value="/img/igr3.jpg"/>">
            </c:if>
            <c:if test="${toy.getId() == 37}">
                <img src="<c:url value="/img/igrushka.png"/>">
            </c:if>
            <c:if test="${toy.getId() == 38}">
                <img src="<c:url value="/img/igrushka2.jpg"/>">
            </c:if>
            <c:if test="${toy.getId() == 39}">
                <img src="<c:url value="/img/toy3.jpg"/>">
            </c:if>
            <c:if test="${toy.getId() == 40}">
                <img src="<c:url value="/img/TOY2.jpg"/>">
            </c:if>
            <c:if test="${toy.getId() > 41}">
                <img src="<c:url value="/img/toy4e.jpg"/>">
            </c:if>
            <button class="btn btn-outline-primary" data-type="add">+</button>
            <button class="btn btn-outline-primary" data-type="remove">-</button>
            <hr>
            <script src="<c:url value="/js/index.js"/>"></script>
            <span class="field-name">Описание:</span>
            <span class="field-value">${toy.getDiscription()}</span>
        </div>
    </div>
</t:mainLayout>