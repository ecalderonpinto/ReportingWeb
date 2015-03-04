<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="Login">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Loads</a> <i class="icon-angle-right"></i></li>
	<li><a href="#">Load Detail</a>
</ul>
<!-- end: Breadcrumb -->
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break"></span>
				${loadRaw.loadFile.loadFileName} - Line ${loadRaw.loadLineNumber}
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
			<table class="table table-striped table-bordered table-condensed">
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
						<td>${loadRaw.id}</td>
						<td></td>
						<td>${loadRaw.id}</td>
						<td>${loadRaw.id}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break"></span>
				Columns - Datas
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
			<table class="table table-striped table-bordered table-condensed bootstrap-datatable datable">
				<thead>
					<tr>
						<th>File Column</th>
						<th>Column Num</th>
						<th>Data</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="rawData" items="${loadRaw.loadRawDatas}">
						<tr>
							<td>${rawData.fileColum.columName}</td>
							<td>${rawData.fileColum.columNumber}</td>
							<td>${rawData.loadRawDataText}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->