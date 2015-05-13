<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index.do">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="user.do">User Manager</a></li>
</ul>
<!-- end: Breadcrumb -->

<c:if test="${alerts}">
	<div class="box-content alerts">
		<c:choose>
			<c:when test="${alert.error}">
				<div class="alert alert-error">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<strong>Error:</strong>
					<c:out value="${alert.message}" />
				</div>
			</c:when>
			<c:otherwise>
				<div class="alert alert-success">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<strong>Success:</strong>
					<c:out value="${alert.message}" />
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</c:if>

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break">Users</span>
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>User Name</th>
						<th>User Rol</th>
						<th>User Mail</th>
						<th>Last login</th>
						<th>Enabled</th>
						<th>Detail</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${users}">
						<tr>
							<td>${user.userName}</td>
							<td>${user.userRol.rolName}</td>
							<td>${user.userMail}</td>
							<td>${user.lastLoginTms}</td>
							<td>${user.enabled}</td>
							<td><a class="btn btn-small"
								href="<c:url value="userDetail.do?id=${user.id}" />">
									detail </a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->

<!-- FormActions -->
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header" data-original-title>
			<h2>
				<i class="halflings-icon edit"></i><span class="break"></span>Actions
			</h2>
			<div class="box-icon"></div>
		</div>
		<div class="box-content">
			<fieldset>
				<div class="form-actions">
					<a href="userDetail.do?id=0"> <span class="btn btn-danger">Create
							User</span></a>
				</div>
			</fieldset>

		</div>
	</div>
	<!--/span-->
</div>
<!-- /FormActions -->

<a href="index.do"><span class="btn btn-important">Back</span></a>