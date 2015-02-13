<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<body>
	<!-- start: Header -->
	<div class="navbar">
		<div class="navbar-inner">
			<jsp:include page="headerMenu.jsp" />
		</div>
	</div>
	<!-- end: Header -->
	<!-- start: Main Container -->
	<div class="container-fluid-full">
		<div class="row-fluid">
			<jsp:include page="leftMenu.jsp" />
			
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<!-- start: Content -->
			<div id="content" class="span10">
				<!-- start: Breadcrumb -->
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>
						<a href="Login">Home</a> 
						<i class="icon-angle-right"></i>
					</li>
					<li><a href="#">Loads</a>
						<i class="icon-angle-right"></i>
					</li>
					<li><a href="#">Dashboard</a></li>
				</ul>
				<!-- end: Breadcrumb -->
				<div class="row-fluid sortable">		
					<div class="box span12">
						<div class="box-header">
							<h2><i class="halflings-icon align-justify"></i><span class="break"></span>Loads</h2>
							<div class="box-icon">
								<a href="#" class="btn-setting"><i class="halflings-icon wrench"></i></a>
								<a href="#" class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
							</div>
						</div>
						<div class="box-content">
							<form action="LoadManual" enctype="multipart/form-data" method="post">
								<div class="control-group">
									<label class="control-label" for="inputFile">Load a file:</label>
									<div class="controls">
										<input class="input-file uniform_on" id="inputFile" name="inputFile" type="file">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputDesc">Desc:</label>
									<div class="controls">
										<input class="input-xlarge focused" id="inputDesc" name="inputDesc" type="text" value="This is focused">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="selectLastFlag">
									File Config:
									</label>
									<div class="controls">
										<select name="fileConfigId">
											<option selected="selected"></option>
											<%
											for(int i=0;i<5;i++){
											%>
												<option value="">
												</option>
											<%
											}
											%>
										</select>
									</div>
								</div>
								<input type="submit" value="Load">
							</form>
						<!-- table table-bordered table-striped table-condensed table table-striped table-bordered bootstrap-datatable datatable-->
						<!-- 
						<table class="table table-striped table-bordered table-condensed datatable">
								<thead>
									<tr>
										<th></th>
										<th>Name</th>
										<th>Status</th>
										<th>Type</th>
										<th>Start</th>
										<th>End</th>
										<th>Total</th>
										<th>Completed</th>
										<th>Success</th>
										<th>Failed</th>
								  	</tr>
							  	</thead>   
							  	<tbody>
									<tr>
										<td onmouseover="javascript:this.style.cursor='pointer'"><a href="LoadDetail"><i class="icon-eye-open"></i></a></td>
										<td>Load 1</td>
										<td>CLOSE</td>
										<td>AIF</td>
										<td>21-12-2014 14:40:12</td>
										<td>21-12-2014 14:42:10</td>
										<td>100</td>
										<td>100</td>
										<td>95</td>
										<td>5</td>
									</tr>
									<tr>
										<td onmouseover="javascript:this.style.cursor='pointer'"><a href="LoadDetail"><i class="icon-eye-open"></i></a></td>
										<td>Load 2</td>
										<td>CLOSE</td>
										<td>AIF</td>
										<td>21-12-2014 14:40:12</td>
										<td>21-12-2014 14:42:10</td>
										<td>100</td>
										<td>100</td>
										<td>95</td>
										<td>5</td>
									</tr>
									<tr>
										<td onmouseover="javascript:this.style.cursor='pointer'"><a href="LoadDetail"><i class="icon-eye-open"></i></a></td>
										<td>Load 3</td>
										<td>OPEN</td>
										<td>AIFM</td>
										<td>21-12-2014 14:40:12</td>
										<td>21-12-2014 14:42:10</td>
										<td>105</td>
										<td>90</td>
										<td>80</td>
										<td>10</td>
									</tr>
								</tbody>
							</table>
							-->
						</div>
					</div><!--/span-->
				</div><!--/row-->
			</div>
			<!-- end: Content -->
		</div>
	</div>
	<!-- end: Main Container -->
		
	<div class="modal hide fade" id="myModal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">x</button>
			<h3>Settings</h3>
		</div>
		<div class="modal-body">
			añsdñlasdñfñkasdf a´sdfadsf
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">Close</a>
			<a href="#" class="btn btn-primary">Load</a>
		</div>
	</div>
	
	<div class="clearfix"></div>
	
	<footer>
		<p>
			<span style="text-align:left;float:left">&copy; 2015 <a href="http://jiji262.github.io/Bootstrap_Metro_Dashboard/" alt="Bootstrap_Metro_Dashboard">Bootstrap Metro Dashboard</a></span>
		</p>
	</footer>
	
	<!-- start: JavaScript-->
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/jquery-migrate-1.0.0.min.js"></script>
	<script src="js/jquery-ui-1.10.0.custom.min.js"></script>
	<script src="js/jquery.ui.touch-punch.js"></script>
	<script src="js/modernizr.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.cookie.js"></script>
	<script src='js/fullcalendar.min.js'></script>
	<script src='js/jquery.dataTables.min.js'></script>
	<script src="js/excanvas.js"></script>
	<script src="js/jquery.flot.js"></script>
	<script src="js/jquery.flot.pie.js"></script>
	<script src="js/jquery.flot.stack.js"></script>
	<script src="js/jquery.flot.resize.min.js"></script>
	<script src="js/jquery.chosen.min.js"></script>
	<script src="js/jquery.uniform.min.js"></script>
	<script src="js/jquery.cleditor.min.js"></script>
	<script src="js/jquery.noty.js"></script>
	<script src="js/jquery.elfinder.min.js"></script>
	<script src="js/jquery.raty.min.js"></script>
	<script src="js/jquery.iphone.toggle.js"></script>
	<script src="js/jquery.uploadify-3.1.min.js"></script>
	<script src="js/jquery.gritter.min.js"></script>
	<script src="js/jquery.imagesloaded.js"></script>
	<script src="js/jquery.masonry.min.js"></script>
	<script src="js/jquery.knob.modified.js"></script>
	<script src="js/jquery.sparkline.min.js"></script>
	<script src="js/counter.js"></script>
	<script src="js/retina.js"></script>
	<script src="js/custom.js"></script>
	<script type="text/javascript">
		function mostrar(){
			$('#prueba').show('slow');
		}
	</script>
	<!-- end: JavaScript-->
	
</body>
</html>
