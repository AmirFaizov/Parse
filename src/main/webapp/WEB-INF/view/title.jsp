<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Happy New Year, My Dear Friends!</title>
    <link rel="stylesheet" href="<c:url value="style/normalize1.css"/>">
    <link rel="stylesheet" href="<c:url value="style/title.css"/>">

</head>
<body>
<header class="header">
    <div class="snowfall"></div>
    <div class="ball"></div>

    <div class="text"></div>


    <div class="santa-container">
        <a href="<c:url value="/toy/list"/>">
        <span class="santa">
            <span class="cap"></span>
            <span class="head">
                <span class="eyes"></span>
            </span>
            <span class="hand"></span>
            <span class="hand left"></span>
            <span class="body">
                <span class="arrow"></span>
            </span>
            <span class="legs"></span>
        </span>
        </a>
    </div>
</header>
<script src="js/_jquery-3.2.1.min.js"></script>
<script src="js/_jquery.parallax-0.2.js"></script>
<script src="js/_main.js"></script>
</body>
</html>