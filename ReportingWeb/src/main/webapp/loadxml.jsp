<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index.do">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="dataManager.do">Data Manager</a><i
		class="icon-angle-right"></i></li>
	<li><a href="companyDetail.do?id=${company.id}">Company detail</a><i
		class="icon-angle-right"></i></li>
	<li><a href="companyReports.do?id=${company.id}">Reports
			(${company.companyName})</a> <i class="icon-angle-right"></i></li>
	<li><a href="#">Load XML</a></li>
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

<form:form enctype="multipart/form-data" method="POST" commandName="loadXML">
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon align-justify"></i><span class="break"></span>
					${loadXML.reportExecution.company.companyName} - Reports
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
							<th>Report Catalog</th>
							<th>Report Name</th>
							<th>Type</th>
							<th>Status</th>
							<th>Load XML file</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${loadXML.reportExecution.reportCatalog.reportCatalogName}</td>
							<td>${loadXML.reportExecution.reportExecutionName}</td>
							<td>${loadXML.reportExecution.reportPeriodType}</td>
							<td>${loadXML.reportExecution.reportStatus}</td>
							<td>
								<div class="controls">
									<form:input path="inputFile" cssClass="input-file uniform_on"
										type="file" />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<input type="submit" class="btn btn-primary" value="Load">
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->
</form:form>

<a href="companyReports.do?id=${company.id}"><span
	class="btn btn-important">Back</span></a>