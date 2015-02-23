<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="Login">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Reports (${company.companyName})</a></li>
</ul>
<!-- end: Breadcrumb -->

<!-- Filter Box -->
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
	<!--/span-->
</div>
<!--/row-->
<!-- /Filter Box -->

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
							<th>Export</th>
							<th>Name</th>
							<th>Type</th>
							<th>Status</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="report" varStatus="status"
							items="${company.reportExecutions}">
							<tr>
								<td><input type="checkbox" id="export1" value="export1"></td>
								<td>${report.reportCatalog.reportCatalogName}</td>
								<td>${report.reportPeriodType}</td>
								<td>${report.reportStatus}</td>
								<td><a href="<c:url value="report.do?id=${report.id}" />">
										<span class="label label-important">With Errors</span>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--/span-->
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
					<button class="btn-setting btn btn-small btn-primary">Export</button>
					<button class="btn btn-small btn-danger">Delete</button>
				</form>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->
	<!-- /Actions Box -->
</form:form>