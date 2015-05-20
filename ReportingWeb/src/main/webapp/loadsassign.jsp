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
	<li><a href="#">Loads Assignations</a></li>
</ul>
<!-- end: Breadcrumb -->

<!-- message success / fail from load file -->
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
<!-- / message success / fail from load file -->

<form:form method="POST" commandName="reportassign">
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon align-justify"></i><span class="break"></span>
					${reportassign.reportExecution.company.companyName} - Reports
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
							<th>Loads Files</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${reportassign.reportExecution.reportCatalog.reportCatalogName}</td>
							<td>${reportassign.reportExecution.reportExecutionName}</td>
							<td>${reportassign.reportExecution.reportPeriodType}</td>
							<td>${reportassign.reportExecution.reportStatus}</td>
							<td>
								<div class="control-group">
									<div class="controls">
										<form:select path="selectLoads" data-rel="chosen"
											cssClass="chzn-container chzn-container-multi"
											multiple="true" items="${selectLoads}"></form:select>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>

				<input type="submit" value="Save"
					class="btn btn-important btn-danger">
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->
</form:form>

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break">Load
					Assign files to Report</span>
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table id="myTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>Report Catalog <i class="icon-sort"></i></th>
						<th>Report Name <i class="icon-sort"></i></th>
						<th>File name <i class="icon-sort"></i></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="loadfiles" items="${loadfiles}">
						<tr>
							<td>${reportassign.reportExecution.reportCatalog.reportCatalogName}</td>
							<td>${reportassign.reportExecution.reportExecutionName}</td>
							<td>${loadfiles.loadFileName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->

<a href="companyReports.do?id=${company.id}"><span
	class="btn btn-important">Back</span></a>
