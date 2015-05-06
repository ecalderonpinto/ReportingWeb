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
	<li><a href="#">Report in XML</a></li>
</ul>
<!-- end: Breadcrumb -->

<!-- Download button -->
<form:form method="POST" commandName="generateXML">
	<div class="box-content">
		<input type="submit" value="Download XML"
			class="btn btn-important btn-success">
	</div>
</form:form>

<!-- errors link -->
<div class="box-content">
	<c:choose>
		<c:when test="${generateXML.reportExecution.hasErrors == true}">
			<a class="btn btn-small btn-danger"
				href="<c:url value="reportError.do?id=${generateXML.reportExecution.id}" />">
				Errors </a>
		</c:when>
		<c:otherwise>
			<span class="label label-success">No errors</span>
		</c:otherwise>
	</c:choose>
</div>

<!-- XML is Valid -->
<div class="box-content alerts">
	<c:choose>
		<c:when test="${isValid != true}">
			<div class="alert alert-error">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<strong>Error:</strong> XML has failed XSD validation. Check errors
				before sent XML file.
			</div>
		</c:when>
		<c:otherwise>
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<strong>Success:</strong> XML has passed XSD validation.
			</div>
		</c:otherwise>
	</c:choose>
</div>



<!-- XML output -->
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break">XML</span>
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content">
			<c:out value="${generateXML.outputXML}"></c:out>
		</div>
	</div>
	<!--/span-->
</div>


<a href="companyReports.do?id=${company.id}"><span
	class="btn btn-important">Back</span></a>