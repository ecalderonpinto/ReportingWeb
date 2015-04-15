<%@page import="com.entities.entity.reportingtool.ReportData"%>
<%@page import="com.entities.entity.reportingtool.ReportExecution"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
	<li><a href="#">${reportexecution.reportExecutionName }</a></li>
</ul>
<!-- end: Breadcrumb -->

<form:form method="POST" commandName="reportexecution">

	<!-- new part with blocks -->

	<%-- <c:forEach var="reportsection" varStatus="status"
		items="${reportsections}">

		<div class="row-fluid">
			<div class="box span12">
				<div class="box-header" data-original-title>
					<h2>
						<i class="halflings-icon edit"></i> <span class="break"></span>
						${reportsection.sectionName}
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
								test="${reportData.reportField.reportFieldSection == reportsection.sectionName }">
								<c:choose>
									<c:when test="${reportsection.hasBlock == true}">
										<!-- Block Section -->
										<c:forEach var="reportDataWithBlock" varStatus="statusBlock"
											items="${reportexecution.reportDatas}">

											<c:if
												test="${reportData.reportField.reportFieldSection == reportDataWithBlock.reportField.reportFieldSection 
											&& reportData.reportDataBlock eq reportDataWithBlock.reportDataBlock}">


												<div class="control-group">
													<label class="control-label" for="disabledInput">
														${reportDataWithBlock.reportField.reportFieldNum}.
														${reportDataWithBlock.reportField.reportFieldName} - 
														${reportDataWithBlock.reportDataText} </label>
													<div class="controls">
														<form:input
															path="reportData[${status.index}].reportDataText"
															cssClass="input-xlarge" /> 
													</div>
												</div>
											</c:if>
										</c:forEach>

										<!-- /Block Section -->


									</c:when>
									<c:otherwise>
										<!-- NO Block Section -->
										<c:choose>
											<c:when test="${reportData.hasErrors == true}">
												<!-- Field with Error -->
												<div class="control-group error">
													<label class="control-label" for="inputError">
														${reportData.reportField.reportFieldNum}.
														${reportData.reportField.reportFieldName } </label>
													<div class="controls">
														<form:input
															path="reportDatas[${status.index}].reportDataText"
															cssClass="input-xlarge" />
														<span class="help-inline"> <c:forEach
																var="reportError" varStatus="status"
																items="${reportData.reportDataErrors}">
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
														<form:input
															path="reportDatas[${status.index}].reportDataText"
															cssClass="input-xlarge" />
													</div>
												</div>
												<!-- /Normal Field -->
											</c:otherwise>
										</c:choose>
										<!-- /NO Block Section -->
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</fieldset>
				</div>
			</div>
			<!--/span-->
		</div>
	</c:forEach>
 --%>


	<!-- Old part -->

	 <c:forEach var="section" varStatus="status" items="${sections}">

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
									
									<c:when test="${reportData.hasErrors == true}">
										<!-- Field with Error -->
										<div class="control-group error">
											<label class="control-label" for="inputError">
												${reportData.reportField.reportFieldNum}.
												${reportData.reportField.reportFieldName } </label>
											<div class="controls">
												<form:input
													path="reportDatas[${status.index}].reportDataText"
													cssClass="input-xlarge" />
												<span class="help-inline"> <c:forEach
														var="reportError" varStatus="status"
														items="${reportData.reportDataErrors}">
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
												<form:input
													path="reportDatas[${status.index}].reportDataText"
													cssClass="input-xlarge" />
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
