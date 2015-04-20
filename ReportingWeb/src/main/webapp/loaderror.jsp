<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index.do">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Load Error</a></li>
</ul>
<!-- end: Breadcrumb -->

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break">Load Error</span>
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
						<th>Load File</th>
						<th>Error</th>
						<th>Type</th>
						<th>Text</th>
						<!-- <th>Detail</th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach var="loaderror" items="${loaderrors}">
						<tr>
							<td>${loaderror.loadFile.loadFileName}</td>
							<td>${loaderror.error.errorType}</td>
							<td>${loaderror.loadErrorType}</td>
							<td>${loaderror.loadErrorText}</td>
						<%-- 	<td>
								<a class="btn btn-small" href="<c:url value="fileConfigDetail.do?id=${fileconfig.id}" />" >
									detail
								</a>
							</td> --%>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->

<a href="index.do"><span
	class="btn btn-important">Back</span></a>