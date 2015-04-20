<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="index.do">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Loads</a></li>
</ul>
<!-- end: Breadcrumb -->
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i><span class="break"></span>Manual
				Load
			</h2>
			<div class="box-icon">
				<!-- <a href="#" class="btn-setting"><i class="halflings-icon wrench"></i></a> -->
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a>
			</div>
		</div>
		<c:if test="${alerts}">
			<div class="box-content alerts">
				<c:choose>
					<c:when test="${alert.error}">
						<div class="alert alert-error">
							<button type="button" class="close" data-dismiss="alert">×</button>
							<strong>Error:</strong>
							<c:out value="${alert.message}" />
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">×</button>
							<strong>Success:</strong>
							<c:out value="${alert.message}" />
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</c:if>
		<div class="box-content">
			<form:form enctype="multipart/form-data" method="post"
				commandName="loadFile">
				<div class="control-group">
					<label class="control-label" for="inputFile">Load a file:</label>
					<div class="controls">
						<form:input path="inputFile" cssClass="input-file uniform_on"
							type="file" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="selectFileConfigs"> File
						Config: </label>
					<div class="controls">
						<form:select path="selectFileConfigs" items="${selectFileConfigs}"></form:select>
					</div>
				</div>
				<input type="submit" value="Load">
			</form:form>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i><span class="break"></span>Loads
			</h2>
			<div class="box-icon">
				<!-- <a href="#" class="btn-setting"><i class="halflings-icon wrench"></i></a> -->
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content">
			<!-- table table-bordered table-striped table-condensed table table-striped table-bordered bootstrap-datatable datatable-->
			<table
				class="table table-striped table-bordered table-condensed datatable">
				<thead>
					<tr>
						<!-- <th></th> -->
						<th>Name</th>
						<th>Config</th>
						<th>Date</th>
						<th>Registers</th>
						<th>Status</th>
						<th>Registers</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="load" items="${loads}">
						<tr>
							<%-- <td><a href="loadDetail.do?id=${load.id}"> <i
									class="icon-eye-open"></i>
							</a></td> --%>
							<td>${load.loadFileName}</td>
							<td>${load.fileConfig.fileConfigName}</td>
							<td>${load.loadFileDate}</td>
							<td>${fn:length(load.loadRaws)}</td>
							<td><c:choose>
									<c:when test="${fn:length(load.loadErrors) gt 0}">
										<a href="<c:url value="viewErrors.do?id=${load.id}" />"> <span
											class="label label-important">With Errors </span>
										</a>
									</c:when>
									<c:otherwise>
										<span
											class="label label-success">Loaded</span>
									</c:otherwise>
								</c:choose>
							</td>
							<td><a class="btn btn-small" href="loadDetail.do?id=${load.id}">Detail</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--/span-->
</div>

<a href="index.do"><span class="btn btn-important">Back</span></a>