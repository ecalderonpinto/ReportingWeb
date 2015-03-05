<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index.do">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Data Manager</a> <i class="icon-angle-right"></i>
	</li>
	<li><a href="#">File Config detail</a></li>
</ul>
<!-- end: Breadcrumb -->

<form:form method="POST" commandName="fileconfig">
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon align-justify"></i> <span class="break">File Config</span>
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
							<th>Department name</th>
							<th>Name</th>
							<th>Type</th>
							<th>Separator</th>
							<th>Format line</th>
							<th>Cron</th>
							<th>Path</th>
							<th>Colums</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${fileConfig.department.departmentName}</td>
							<td>${fileConfig.fileConfigName}</td>
							<td>${fileConfig.fileType}</td>
							<td>${fileConfig.fileSeparator}</td>
							<td>${fileConfig.fileFormatLine}</td>
							<td>${fileConfig.fileCron}</td>
							<td>${fileConfig.filePath}</td>
							<td><a class="btn btn-small"
								href="<c:url value="fileColums.do?id=${fileConfig.id}" />">
									colums </a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->
</form:form>