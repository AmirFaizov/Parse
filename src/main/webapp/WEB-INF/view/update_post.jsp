<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:mainLayout title="UPDATE">

<div class="page">
	<h3 class="text-center mb-5">Create Toy</h3>
	<form action="" method="post" enctype="multipart/form-data" class="form-horizontal">
		<div class="form-group" id="m1">
			<label for="exampleFormControlInput1" class="control-label col-sm-3" id="f1">Title</label>
			<input required name="title" type="text" class="form-control" id="exampleFormControlInput1">
		</div>
		<div class="form-group">
			<label for="exampleFormControlTextarea2" class="control-label col-sm-3" id="f2">Content</label>
			<textarea  required name="text" class="form-control" id="exampleFormControlTextarea2" rows="3"></textarea>
		</div>
		<div class="form-group">
			<label for="exampleFormControlInput1" class="control-label col-sm-3" id="f3">Price</label>
			<input required name="price" type="number" class="form-control" id="exampleFormControlPrice">
		</div>

		<button class="btn btn-outline-primary mt-5 btn1">EDIT</button>
	</form>
</div>

</t:mainLayout>