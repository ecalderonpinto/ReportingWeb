<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="Login">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Admin</a></li>
</ul>
<!-- end: Breadcrumb -->

<div class="row-fluid sortable">
	<div class="box blue span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon hand-top"></i><span class="break"></span>Quick
				Actions
			</h2>
		</div>
		<div class="box-content">
			<a class="quick-button span2" onclick="loading();" href="generateDB.do"> <i class="icon-cogs"></i>
				<p>Generate Schema</p>
			</a> <a class="quick-button span2" href="installTestData.do"> <i class="icon-cogs"></i>
				<p>Install Data</p>
			</a>
			<a class="quick-button span2" href="cleanData.do"> <i class="icon-cogs"></i>
				<p>Clean Datas</p>
			</a>
			<div class="clearfix"></div>
		</div>
	</div>
	<!--/span-->

</div>
<!--/row-->

<!--
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break"></span>
				Installations
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-setting"><i class="halflings-icon wrench"></i></a>
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a> <a href="#"
					class="btn-close"><i class="halflings-icon remove"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>Name</th>
						<th>Status</th>
						<th>Company</th>
						<th>Department</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${loadRaw.id}</td>
						<td></td>
						<td>${loadRaw.id}</td>
						<td>${loadRaw.id}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!--/span -->
</div>
<!--/row-->