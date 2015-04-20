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

<form:form method="POST" commandName="reportXML">
	<div class="box-content">
		<input type="hidden" name="reportid" value="${report.id}" />
		<input type="submit" value="Download XML" class="btn btn-important btn-success">
	</div>
</form:form>

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
			<c:out value="${outputXML}"></c:out>
		</div>
	</div>
	<!--/span-->
</div>


<a href="companyReports.do?id=${company.id}"><span
	class="btn btn-important">Back</span></a>