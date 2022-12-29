<%@tag description="Default Layout Tag" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@attribute name="title" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <script src = "<c:url value="/js/_bootstrap.min.js"/>"></script>

    <link rel="stylesheet" href="<c:url value="/style/title.css"/>">
    <link rel="stylesheet" href="<c:url value="/style/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/style/main.css"/>">
    <link rel="stylesheet" href="<c:url value="/style/add_story.css"/>">


</head>
<body>
<header class="header">
    <%@include file="/WEB-INF/view/parts/_nav.jsp"%>
    <div class="snow2"></div>
    <div class="santa2"></div>
    <div class="snow1"></div>
    <div class="container"
    <jsp:doBody/>
    </div>
</header>
<script href="<c:url value="/js/_jquery-3.2.1.min.js"/>"></script>
<script href="<c:url value="/js/_jquery.parallax-0.2.js"/>"></script>
<script href="<c:url value="/js/_main.js"/>"></script>
</body>
</html>