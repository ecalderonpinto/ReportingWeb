<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index.do">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Loads</a> <i class="icon-angle-right"></i></li>
	<li><a href="#">Load Errors</a>
</ul>
<!-- end: Breadcrumb -->
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i>
				<span class="break"></span>
				${load.loadFileName}
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-setting"><i class="halflings-icon wrench"></i></a>
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a> <a href="#"
					class="btn-close"><i class="halflings-icon remove"></i></a>
			</div>
		</div>
		<div class="box-content">
			<!-- table table-bordered table-striped table-condensed table table-striped table-bordered bootstrap-datatable datatable-->
			<form name="repform" action="Report">
				<table
					class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>Name</th>
							<th>Status</th>
							<th>Company</th>
							<th>Department</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${load.loadFileName}</td>
							<td></td>
							<td>${load.department.company.companyName}</td>
							<td>${load.department.departmentName}</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i>
				<span class="break"></span>
				Errors
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-setting"><i class="halflings-icon wrench"></i></a>
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a> <a href="#"
					class="btn-close"><i class="halflings-icon remove"></i></a>
			</div>
		</div>
		<div class="box-content">
			<!-- table table-bordered table-striped table-condensed table table-striped table-bordered bootstrap-datatable datatable-->
			<form name="repform" action="Report">
				<table
					class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>Type</th>
							<th>Text</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="error" items="${load.loadErrors}">
						<tr>
							<td>${error.loadErrorType}</td>
							<td>${error.loadErrorText}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->