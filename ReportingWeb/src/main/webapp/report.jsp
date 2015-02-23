<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="Login">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Report</a> <i class="icon-angle-right"></i></li>
	<li><a href="#">bla bla</a></li>
</ul>
<!-- end: Breadcrumb -->

<form:form method="POST" commandName="report">

	<c:forEach var="section" varStatus="status" items="${sections}">

		<!-- CancellationAIFMRecordInfo -->
		<div class="row-fluid sortable">
			<div class="box span12">
				<div class="box-header" data-original-title>
					<h2>
						<i class="halflings-icon edit"></i> <span class="break"></span>
						${section}
					</h2>
					<div class="box-icon">
						<!--
								<a href="#" class="btn-setting"><i
									class="halflings-icon wrench"></i></a>
								-->
						<a href="#" class="btn-minimize"><i
							class="halflings-icon chevron-up"></i></a>
						<!--
								<a href="#" class="btn-close"><i
									class="halflings-icon remove"></i></a>
								-->
					</div>
				</div>
				<div class="box-content">
					<fieldset>
						<c:forEach var="reportData" varStatus="status"
							items="${report.reportDatas}">
							<c:if
								test="${reportData.reportField.reportFieldSection == section}">
								<!-- CancelledAIFMNationalCode -->
								<div class="control-group">
									<label class="control-label" for="disabledInput"> ${reportData.reportField.reportFieldNum}.
										${reportData.reportField.reportFieldName } </label>
									<div class="controls">
										<input class="input-xlarge disabled" id="disabledInput"
											type="text" placeholder="Disabled input here…" disabled=""
											value="${reportData.reportDataText}"> <i class="halflings-icon tag"
											onmouseover="javascript:this.style.cursor='pointer'"
											title="Notes"></i>
									</div>
								</div>
								<!-- /CancelledAIFMNationalCode -->
							</c:if>
						</c:forEach>
					</fieldset>
				</div>
			</div>
			<!--/span-->
		</div>
		<!-- /CancellationAIFMRecordInfo -->

	</c:forEach>

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
						<button type="submit" class="btn btn-primary">Save
							changes</button>
						<button class="btn">Cancel</button>
					</div>
				</fieldset>

			</div>
		</div>
		<!--/span-->
	</div>
	<!-- /FormActions -->
</form:form>
