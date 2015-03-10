<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="Login">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Reports</a> <i class="icon-angle-right"></i></li>
	<li><a href="#">${reportexecution.reportExecutionName }</a></li>
</ul>
<!-- end: Breadcrumb -->

<form:form method="POST" commandName="reportexecution">

	<c:forEach var="section" varStatus="status" items="${sections}">

		<!-- CancellationAIFMRecordInfo -->
		<div class="row-fluid">
			<div class="box span12">
				<div class="box-header" data-original-title>
					<h2>
						<i class="halflings-icon edit"></i> <span class="break"></span>
						${section}
					</h2>
					<div class="box-icon">
						<a href="#" class="btn-minimize"><i
							class="halflings-icon chevron-down"></i></a>
					</div>
				</div>
				<div class="box-content" style="display: none;">
					<fieldset>
						<c:forEach var="reportData" varStatus="status"
							items="${reportexecution.reportDatas}">
							<c:if
								test="${reportData.reportField.reportFieldSection == section}">
								<c:choose>
									<c:when test="${fn:length(reportData.reportDataErrors) gt 0}">
										<!-- Field with Error -->
										<div class="control-group error">
											<label class="control-label" for="inputError">
												${reportData.reportField.reportFieldNum}.
												${reportData.reportField.reportFieldName } </label>
											<div class="controls">	
													<form:input path="reportDatas[${status.index}].reportDataText" cssClass="input-xlarge"/>
													 <span
													class="help-inline">
													<c:forEach var="reportError" varStatus="status"
													items="${reportData.reportDataErrors}" >
														${reportError.reportDataErrorText}
													</c:forEach>
												</span>
											</div>
										</div>
										<!-- /Field with Error -->
									</c:when>
									<c:otherwise>
										<!-- Normal Field -->
										<div class="control-group">
											<label class="control-label" for="disabledInput">
												${reportData.reportField.reportFieldNum}.
												${reportData.reportField.reportFieldName } </label>
											<div class="controls">
												<form:input path="reportDatas[${status.index}].reportDataText" cssClass="input-xlarge"/>
											</div>
										</div>
										<!-- /Normal Field -->
									</c:otherwise>
								</c:choose>
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
