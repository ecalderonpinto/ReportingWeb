<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index.do">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="dataManager.do">Data Manager</a></li>
</ul>
<!-- end: Breadcrumb -->

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break">Companies</span>
			</h2>
			<div class="box-icon">
				<a href="#"
					onclick="$('#myTable').tableExport({type:'excel',escape:'false'});">
					<img src="img/xls.png" width="20px">
				</a>
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table id="myTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>Name <i class="icon-sort"></i></th>
						<th>Country <i class="icon-sort"></i></th>
						<th>Code <i class="icon-sort"></i></th>
						<th>Description <i class="icon-sort"></i></th>
						<th>Details</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="company" items="${companies}">
						<tr>
							<td>${company.companyName}</td>
							<td>${company.companyCountry}</td>
							<td>${company.companyCode}</td>
							<td>${company.companyDesc}</td>
							<td><a class="btn btn-small btn-primary"
								href="<c:url value="companyDetail.do?id=${company.id}" />">
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

<a href="index.do"><span class="btn btn-important">Back</span></a>