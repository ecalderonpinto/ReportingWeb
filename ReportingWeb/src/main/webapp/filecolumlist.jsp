<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index.do">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">File Colum List</a></li>
</ul>
<!-- end: Breadcrumb -->

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break">File Colum List</span>
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
						<th>Colum Name</th>
						<th>Colum Number</th>
						<th>Type</th>
						<th>Origin</th>
						<th>Destination</th>
						<!-- <th>Detail</th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach var="filecolumlist" items="${filecolumlist}">
						<tr>
							<td>${filecolumlist.fileColum.columName}</td>
							<td>${filecolumlist.fileColum.columNumber}</td>
							<td>${filecolumlist.fileColumListType}</td>
							<td>${filecolumlist.fileColumListOrig}</td>
							<td>${filecolumlist.fileColumListDest}</td>
							<%-- <td>
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