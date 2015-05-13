<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index.do">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="user.do">User Manager</a><i class="icon-angle-right"></i></li>
	<li><a href="#">User Manager Detail</a></li>
</ul>
<!-- end: Breadcrumb -->

<form:form method="POST" commandName="userdetail">
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon align-justify"></i> <span class="break">User</span>
				</h2>
				<div class="box-icon">
					<a href="#" class="btn-minimize"><i
						class="halflings-icon chevron-up"></i></a>
				</div>
			</div>
			<div class="box-content">
				<table class="table table-striped table-bordered table-condensed">
					<tbody>
						<tr>
							<td>User Name <b>(*)</b></td>
							<td><form:input path="user.userName" cssClass="input-xlarge"
									required="required" maxlength="40" /></td>
							<td>User Mail</td>
							<td><form:input path="user.userMail" cssClass="input-xlarge"
									maxlength="300" /></td>
						</tr>
						<tr>
							<td>User Password <b>(*)</b></td>
							<td><form:input path="user.userPass"
									cssClass="input-xlarge" required="required" maxlength="128" /></td>
							<td>User Rol <b>(*)</b></td>
							<td><form:select path="selectUserRol" required="required">
									<form:options items="${userRolDrop}" />
								</form:select></td>
						</tr>
						<tr>
							<td>User Enabled <b>(*)</b></td>
							<td><form:select path="selectEnabled" required="required">
									<form:options items="${userEnabledDrop}" />
								</form:select></td>
						</tr>
						<form:hidden path="userId" />
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
						<button type="submit" class="btn btn-danger">Save changes</button>
						<button class="btn">Cancel</button>
					</div>
				</fieldset>

			</div>
		</div>
		<!--/span-->
	</div>
	<!-- /FormActions -->

</form:form>

<a href="user.do"><span class="btn btn-important">Back</span></a>