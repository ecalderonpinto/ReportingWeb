<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index.do">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="admin.do">General Admin</a></li>
</ul>
<!-- end: Breadcrumb -->

<div class="row-fluid sortable">
	<div class="box blue span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon hand-top"></i><span class="break"></span>Quick
				Actions
			</h2>
		</div>
		<div class="box-content">
			<a class="quick-button span2" onclick="loading();"
				href="generateDB.do"> <i class="icon-cogs"></i> <br>Generate
				Schema
			</a> <a class="quick-button span2" href="installTestData.do"> <i
				class="icon-cogs"></i> <br>Install Data
			</a> <a class="quick-button span2" href="cleanData.do"> <i
				class="icon-cogs"></i> <br>Clean Datas
			</a>
			<div class="clearfix"></div>
		</div>
	</div>
	<!--/span-->

</div>
<!--/row-->

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break">Report
					Catalogs</span>
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
						<th>Report Name</th>
						<th>Level</th>
						<th>Version</th>
						<th>Description</th>
						<th>Details</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="reportcatalog" items="${reportcatalogs}">
						<tr>
							<td>${reportcatalog.reportCatalogName}</td>
							<td>${reportcatalog.reportLevel}</td>
							<td>${reportcatalog.reportVersion}</td>
							<td>${reportcatalog.reportCatalogDesc}</td>
							<td><a class="btn btn-small"
								href="<c:url value="reportCatalogDetail.do?id=${reportcatalog.id}" />">
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
