<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index.do">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Report Data Error</a></li>
</ul>
<!-- end: Breadcrumb -->

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break">Report Data Error</span>
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
						<th>Report</th>
						<th>Data</th>
						<th>Field</th>
						<th>Error</th>
						<th>Type</th>
						<th>Detail</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="reportdataerror" items="${reportdataerrors}">
						<tr>
							<td>${reportdataerror.reportData.reportExecution.reportExecutionName}</td>
							<td>${reportdataerror.reportData.reportDataText}</td>
							<td>${reportdataerror.reportData.reportField.reportFieldName}
								(${reportdataerror.reportData.reportField.reportFieldNum})
							</td>
							<td>${reportdataerror.error.errorType}</td>
							<td>${reportdataerror.reportDataErrorType}</td>
							<td>${reportdataerror.reportDataErrorText}</td>
							<td><c:choose>
									<c:when test="${reportdataerror.versionAuditor.deleted == true}">
										<span class="label label-success">Solved</span>
									</c:when>
									<c:otherwise>
										<span class="label label-important">Pending</span>
									</c:otherwise>
								</c:choose></td>
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