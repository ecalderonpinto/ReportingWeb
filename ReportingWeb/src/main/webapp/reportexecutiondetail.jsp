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
	<li><a href="companyReports.do?id=${company.id}">Reports
			(${company.companyName})</a><i class="icon-angle-right"></i></li>
	<li><a href="#">New AIFM Report</a></li>
</ul>
<!-- end: Breadcrumb -->

${resultMessage}

<form:form method="POST" commandName="reportexecutiondetail">
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon align-justify"></i> <span class="break">New
						AIFM Report</span>
				</h2>
				<div class="box-icon">
					<a href="#" class="btn-minimize"><i
						class="halflings-icon chevron-up"></i></a>
				</div>
			</div>
			<div class="box-content">
				<table class="table table-striped table-bordered table-condensed">
					<tbody>
						<tr>
							<td>Report Name <b>(*)</b></td>
							<td><form:input path="reportExecutionName"
									cssClass="input-xlarge" required="required" /></td>
							<td>Report Description</td>
							<td><form:input path="reportExecutionDesc"
									cssClass="input-xlarge" /></td>
						</tr>
						<tr>
							<td>Period Type <b>(*)</b></td>
							<td><form:input path="reportPeriodType"
									cssClass="input-xlarge" required="required" /></td>
							<td>Period Year <b>(*)</b></td>
							<td><form:input path="reportPeriodYear"
									cssClass="input-xlarge" required="required" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->

	<!-- FormActions -->
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header" data-original-title>
				<h2>
					<i class="halflings-icon edit"></i><span class="break"></span>Actions
				</h2>
				<div class="box-icon"></div>
			</div>
			<div class="box-content">
				<fieldset>
					<div class="form-actions">
						<button type="submit" class="btn btn-danger">Save changes</button>
						<button class="btn">Cancel</button>
					</div>
				</fieldset>

			</div>
		</div>
		<!--/span-->
	</div>
	<!-- /FormActions -->

</form:form>

<a href="companyReports.do?id=${company.id}"><span
	class="btn btn-important">Back</span></a>
