<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index.do">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Data Manager</a> <i class="icon-angle-right"></i>
	</li>
	<li><a href="#">Company detail</a></li>
</ul>
<!-- end: Breadcrumb -->

<form:form method="POST" commandName="company">
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon align-justify"></i> <span class="break">Company</span>
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
							<th>Name</th>
							<th>Country</th>
							<th>Code</th>
							<th>Description</th>
							<th>Reports</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${company.companyName}</td>
							<td>${company.companyCountry}</td>
							<td>${company.companyCode}</td>
							<td>${company.companyDesc}</td>
							<td><a class="btn btn-small"
								href="<c:url value="companyReports.do?id=${company.id}" />">
									reports </a></td>
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
					<i class="halflings-icon align-justify"></i> <span class="break">Departments</span>
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
							<th>Name</th>
							<th>Code</th>
							<th>Description</th>
							<th>Country</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="department" varStatus="status"
							items="${company.departments}">
							<tr>
								<td>${department.departmentName}</td>
								<td>${department.departmentCode}</td>
								<td>${department.departmentDesc}</td>
								<td>${department.departmentCountry}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--/span-->
	</div>

	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon align-justify"></i> <span class="break">Funds</span>
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
							<th>Name</th>
							<th>Isin</th>
							<th>Code</th>
							<th>Description</th>
							<th>Class</th>
							<th>Reports</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="fund" varStatus="status" items="${company.funds}">
							<tr>
								<td>${fund.fundName}</td>
								<td>${fund.fundIsin}</td>
								<td>${fund.fundCode}</td>
								<td>${fund.fundDesc}</td>
								<td>${fund.fundClass}</td>
								<td><a class="btn btn-small"
									href="<c:url value="fundReports.do?id=${fund.id}" />">
										reports </a>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--/span-->
	</div>
</form:form>