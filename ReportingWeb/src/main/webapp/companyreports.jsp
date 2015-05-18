<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index.do">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="dataManager.do">Data Manager</a><i
		class="icon-angle-right"></i>
	<li><a href="companyDetail.do?id=${company.id}">Company detail</a>
		<i class="icon-angle-right"></i></li>
	<li><a href="#">Reports (${company.companyName})</a></li>
</ul>
<!-- end: Breadcrumb -->

<%-- <!-- Filter Box -->
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon filter"></i><span class="break"></span>Filters
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a> <a href="#"
					class="btn-close"><i class="halflings-icon remove"></i></a>
			</div>
		</div>
		<div class="box-content">
			<form name="repform" action="Filter">
				<table class="table table-bordered">
					<tr>
						<td><label class="control-label" for="selectError">AIFM
								Group</label>
							<div class="controls">
								<select id="selectError" data-rel="chosen">
									<option>AIFM Group 1</option>
									<option>AIFM Group 2</option>
									<option>AIFM Group 3</option>
									<option>AIFM Group 4</option>
									<option>AIFM Group 5</option>
								</select>
							</div></td>
						<td><label class="control-label" for="inlineCheckbox1">Frequency:</label>
							<div class="controls">
								<label class="checkbox inline"> <input type="checkbox"
									id="inlineCheckbox1" value="option1"> Yearly
								</label> <label class="checkbox inline"> <input type="checkbox"
									id="inlineCheckbox2" value="option2"> Half-Yearly
								</label> <label class="checkbox inline"> <input type="checkbox"
									id="inlineCheckbox3" value="option3"> Quarterly
								</label>
							</div></td>
						<td style="text-align: center;"><button
								class="btn btn-small btn-primary">Search</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<!--/row-->
<!-- /Filter Box --> --%>

<form:form method="POST" commandName="company">
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon align-justify"></i><span class="break"></span>${company.companyName}
					- Reports
				</h2>
				<div class="box-icon">
					<a href="#" class="btn-minimize"><i
						class="halflings-icon chevron-up"></i></a>
				</div>
			</div>

			<div class="box-content">
				<table
					class="table table-striped table-bordered table-condensed datatable">
					<thead>
						<tr>
							<!-- <th>Export</th> -->
							<th>Name</th>
							<th>Type</th>
							<th>Year</th>
							<th>Status</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="reportexecution" varStatus="status"
							items="${company.reportExecutions}">
							<c:if
								test="${reportexecution.reportCatalog.reportLevel == 'COMPANY'}">
								<tr>
									<!-- <td><input type="checkbox" id="export1" value="export1"></td> -->
									<td>${reportexecution.reportExecutionName}</td>
									<td>${reportexecution.reportPeriodType}</td>
									<td>${reportexecution.reportPeriodYear}</td>
									<td>${reportexecution.reportStatus}</td>
									<td><a class="btn btn-small btn-primary"
										href="<c:url value="reportExecution.do?id=${reportexecution.id}" />">
											Detail </a> 
										<a class="btn btn-small btn-warning"
										href="<c:url value="loadsAssignToReport.do?id=${reportexecution.id}" />">
											Add file</a>
										<a class="btn btn-small btn-warning"
										href="<c:url value="loadXMLToReport.do?id=${reportexecution.id}" />">
											Add XML</a>
										<a class="btn btn-small btn-success"
										href="<c:url value="viewXML.do?id=${reportexecution.id}" />">
											View XML </a> 
										<c:choose>
											<c:when test="${reportexecution.hasErrors == true}">
												<a class="btn btn-small btn-danger"
													href="<c:url value="reportError.do?id=${reportexecution.id}" />">
													Errors </a>
											</c:when>
											<c:otherwise>
												<span class="label label-success">No errors</span>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!--/row-->

	<!-- Actions Box -->
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon filter"></i><span class="break"></span>Actions
				</h2>
				<div class="box-icon">
					<a href="#" class="btn-minimize"><i
						class="halflings-icon chevron-up"></i></a>
				</div>
			</div>
			<div class="box-content">
				<form name="repform" action="Filter">
					<!-- <button class="btn-setting btn btn-small btn-primary">Export</button> -->
					<!-- <button class="btn btn-small btn-danger">Delete</button> -->
				</form>
				<a href="reportExecutionDetail.do?id=${company.id}"><span
					class="btn btn-important btn-danger">Create Report</span></a>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->
	<!-- /Actions Box -->
</form:form>

<a href="dataManager.do"><span class="btn btn-important">Back</span></a>