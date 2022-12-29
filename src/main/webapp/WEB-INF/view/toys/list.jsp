<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="Toy List">
    <div class="text-info"> Toys: ${toyCount} </div>
    <div class="books-list">
        <c:forEach items="${toys}" var="toy">
            <img class="perv" src="<c:url value="/img/logo1.png"/>">
            <img class="perv" src="<c:url value="/img/snowfall_.png"/>">


            <div class="book-card">
                <div class="book-header">
                    <h3 class="book-name">
                        <c:if test="${user != null}">
                        <a href="<c:url value="/toy/detail?id=${toy.id}"/>">${toy.getToyName()}</a>
                        </c:if>
                        <c:if test="${user == null}">
                        <a href="<c:url value="/signin"/>">${toy.getToyName()}</a>
                        </c:if>
                    </h3>
                    <span class="book-info">${toy.getPrice()} руб</span>
                    <div class="clearfix"></div>
                    <form action="<c:url value="/toy/delete"/>" method="post" class="d-inline-block mb-5">
                        <button name="postID" value="${toy.getId()}" class="btn btn-outline-danger btn1">Delete</button>
                    </form>
                    <form action="<c:url value="/toy/update"/>" method="get" class="d-inline-block mb-5" id="btn3">
                        <button name="postID" value="${toy.getId()}" class="btn btn-outline-info btn1" style="margin-left: 7px;">Edit</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</t:mainLayout>