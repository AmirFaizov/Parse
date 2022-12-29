<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:mainLayout title="Registration">
    <div class="intro">
        <form id="loginForm" class="form-horizontal" action="<c:url value="/create"/>" method="POST">
            <div class="form-group">
                <label class="control-label col-sm-3" for="toyName">Name</label>
                <div class="controls col-sm-9">
                    <input id="toyName" name="toyName" class="form-control" type="text" value=""/>
                </div>
            </div>
                <div class="form-group">
            <label class="control-label col-sm-3" for="price">Price</label>
            <div class="controls col-sm-9">
                <input id="price" name="price" class="form-control" type="price" value=""/>
            </div>
            </div>
                <div class="form-group">
                </div><button type="submit" class="btn btn-success">Создать</button>
        </div>
        </form>
    </div>


</t:mainLayout>