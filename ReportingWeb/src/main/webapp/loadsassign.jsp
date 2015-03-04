<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="Login">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Loads Assignations</a></li>
</ul>
<!-- end: Breadcrumb -->

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
							<th>Name</th>
							<th>Type</th>
							<th>Status</th>
							<th>Loads Files</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${reportassign.reportExecution.reportCatalog.reportCatalogName}</td>
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
				
				<input type="submit" value="Save" class="btn btn-important">
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->
</form:form>