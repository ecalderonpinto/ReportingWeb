<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index.do">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="loads.do">Loads</a> <i class="icon-angle-right"></i></li>
	<li><a href="#">Load Detail</a>
</ul>
<!-- end: Breadcrumb -->
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i>
				<span class="break"></span>
				${loadFile.loadFileName}
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
							<td>${loadFile.loadFileName}</td>
							<td></td>
							<td>${loadFile.department.company.companyName}</td>
							<td>${loadFile.department.departmentName}</td>
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
				Registers
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
							<!-- <th></th> -->
							<th>Line N�</th>
							<th>Type</th>
							<th>Status</th>
							<th>Colums</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="raw" items="${loadFile.loadRaws}">
						<tr>
							<%-- <td><a href="rawDetail.do?id=${raw.id}">
							<i class="icon-eye-open"></i></a></td> --%>
							<td>${raw.loadLineNumber + 1}</td>
							<td>${raw.loadLineType}</td>
							<td>${raw.loadError}</td>
							<td><a class="btn btn-small" href="rawDetail.do?id=${raw.id}">Detail</a>
							</td>
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

<a href="loads.do"><span
	class="btn btn-important">Back</span></a>