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
						<a href="index.do">Home</a> 
						<i class="icon-angle-right"></i>
					</li>
					<li><a href="#">Reports</a></li>
				</ul>
				<!-- end: Breadcrumb -->
				<!-- Filter Box -->
				<div class="row-fluid sortable">		
					<div class="box span12">
						<div class="box-header">
							<h2><i class="halflings-icon filter"></i><span class="break"></span>Filters</h2>
							<div class="box-icon">
								<a href="#" class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
								<a href="#" class="btn-close"><i class="halflings-icon remove"></i></a>
							</div>
						</div>
						<div class="box-content">
						<form name="repform" action="Filter">
						<table class="table table-bordered">
							<tr>
								<td>
							<% if(request.getParameter("r") != null && request.getParameter("r").equals("aifm")){ %>
								<label class="control-label" for="selectError">AIFM Group</label>
								<div class="controls">
								  <select id="selectError" data-rel="chosen">
									<option>AIFM Group 1</option>
									<option>AIFM Group 2</option>
									<option>AIFM Group 3</option>
									<option>AIFM Group 4</option>
									<option>AIFM Group 5</option>
								  </select>
								</div>
							<% } else { %>
								<label class="control-label" for="selectError">AIF Group</label>
								<div class="controls">
								  <select id="selectError" data-rel="chosen">
									<option>AIF Group 1</option>
									<option>AIF Group 2</option>
									<option>AIF Group 3</option>
									<option>AIF Group 4</option>
									<option>AIF Group 5</option>
								  </select>
								</div>
							<% } %>
								</td>
								<td>
									<label class="control-label" for="inlineCheckbox1">Frequency:</label>
									<div class="controls">
								  <label class="checkbox inline">
									<input type="checkbox" id="inlineCheckbox1" value="option1"> Yearly
								  </label>
								  <label class="checkbox inline">
									<input type="checkbox" id="inlineCheckbox2" value="option2"> Half-Yearly
								  </label>
								  <label class="checkbox inline">
									<input type="checkbox" id="inlineCheckbox3" value="option3"> Quarterly
								  </label>
								</div>
								</td>
								<td style="text-align: center;"><button class="btn btn-small btn-primary">Search</button></td>
							</tr>
						</table>
						</form>
						</div>
					</div><!--/span-->
				</div><!--/row-->
				<!-- /Filter Box -->
<% if(request.getParameter("r") != null && request.getParameter("r").equals("aifm")){ %>
				<div class="row-fluid sortable">		
					<div class="box span12">
						<div class="box-header">
							<h2><i class="halflings-icon align-justify"></i><span class="break"></span>AIFM Reports</h2>
							<div class="box-icon">
								<a href="#" class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
							</div>
						</div>
						<div class="box-content">
							<form name="repAIFMform" action="Report">
							<table class="table table-striped table-bordered table-condensed datatable">
								<thead>
									<tr>
										<th>Export</th>
										<th>Name</th>
										<th>Last update</th>
										<th>Due date</th>
										<th>Status</th>
										<th width="20%">Completed</th>
								  	</tr>
							  	</thead>   
							  	<tbody>
									<tr>
										<td><input type="checkbox" id="export1" value="export1"></td>
										<td onmouseover="javascript:this.style.cursor='pointer'" onclick="repAIFMform.submit();">AIFMD_F120_2014</td>
										<td class="center">2012/01/01</td>
										<td class="center">2012/01/01</td>
										<td class="center">
											<span class="label label-success">Dispatched</span>
										</td>
										<td>
                                        	<div class="taskProgress progressSlim green">47</div>
										</td>
									</tr>
									<tr>
										<td><input type="checkbox" id="export2" value="export2"></td>
										<td onmouseover="javascript:this.style.cursor='pointer'" onclick="repAIFMform.submit();">AIFMD_v3_2013</td>
										<td class="center">2012/03/01</td>
										<td class="center">2012/01/01</td>
										<td class="center">
											<span class="label label-warning">Pending</span>
										</td>
										<td>
                                        	<div class="taskProgress progressSlim yellow">60</div>
										</td>
									</tr>
									<tr>
										<td><input type="checkbox" id="export3" value="export3"></td>
										<td onmouseover="javascript:this.style.cursor='pointer'" onclick="repAIFMform.submit();">AIFMD_h10_v5_2014</td>
										<td class="center">2012/03/01</td>
										<td class="center">2012/01/01</td>
										<td class="center">
											<span class="label label-important">Rejected</span>
										</td>
										<td>
                                        	<div class="taskProgress progressSlim red">25</div>
										</td>
									</tr>
								</tbody>
							</table>
						</form>
						</div>
					</div><!--/span-->
				</div><!--/row-->
<%} else if(request.getParameter("r") != null && request.getParameter("r").equals("aif")){ %>
				<div class="row-fluid sortable">		
					<div class="box span12">
						<div class="box-header">
							<h2><i class="halflings-icon align-justify"></i><span class="break"></span>AIF Reports</h2>
							<div class="box-icon">
								<a href="#" class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
							</div>
						</div>
						<div class="box-content">
							<form name="repform" action="Report">
							<table class="table table-striped table-bordered table-condensed datatable">
								<thead>
									<tr>
										<th>Export</th>
										<th>Name</th>
										<th>Last update</th>
										<th>Due date</th>
										<th>Status</th>
										<th width="20%">Completed</th>
								  	</tr>
							  	</thead>   
							  	<tbody>
							  	<%
							  	//for(int i=0;i<20;i++){
							  	%>
							  		<tr onmouseover="" onclick="">
							  			<td><input type="checkbox" id="export1" value="export1"></td>
										<td>AIF_2014</td>
										<td class="center">2012/01/01</td>
										<td class="center">2012/01/01</td>
										<td class="center">
											<span class="label label-success">Dispatched</span>
										</td>
										<td>
                                        	<div class="taskProgress progressSlim green">100</div>
										</td>
									</tr>
									<tr onmouseover="" onclick="">
										<td><input type="checkbox" id="export2" value="export2"></td>
										<td>AIF_v3</td>
										<td class="center">2012/03/01</td>
										<td class="center">2012/01/01</td>
										<td class="center">
											<span class="label label-warning">Pending</span>
										</td>
										<td>
                                        	<div class="taskProgress progressSlim yellow">60</div>
										</td>
									</tr>
									<tr onmouseover="" onclick="">
										<td><input type="checkbox" id="export3" value="export3"></td>
										<td>AIF_v5_2014</td>
										<td class="center">2012/03/01</td>
										<td class="center">2012/01/01</td>
										<td class="center">
											<span class="label label-important">Rejected</span>
										</td>
										<td>
                                        	<div class="taskProgress progressSlim red">25</div>
										</td>
									</tr>
								<%
							  	//}
								%>
								</tbody>
							</table> 
							</form> 
						</div>
					</div><!--/span-->
				</div><!--/row-->
<%} %>
				<!-- Actions Box -->
				<div class="row-fluid sortable">		
					<div class="box span12">
						<div class="box-header">
							<h2><i class="halflings-icon filter"></i><span class="break"></span>Actions</h2>
							<div class="box-icon">
								<a href="#" class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
							</div>
						</div>
						<div class="box-content">
						<form name="repform" action="Filter">
							<button class="btn-setting btn btn-small btn-primary">Export</button>
							<button class="btn btn-small btn-danger">Delete</button>
						</form>
						</div>
					</div><!--/span-->
				</div><!--/row-->
				<!-- /Actions Box -->
			</div>
			<!-- end: Content -->
		</div>
	</div>
	<!-- end: Main Container -->
		
	<div class="modal hide fade" id="myModal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">x</button>
			<h3>Export</h3>
		</div>
		<div class="modal-body">
			<p>Choose a option to export...</p>
			<p>
				<button class="btn btn-small btn-primary">PDF</button>
				<button class="btn btn-small btn-primary">XML</button>
				<button class="btn btn-small btn-primary">CSV</button>
			</p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">Close</a>
		</div>
	</div>
	
	<div class="clearfix"></div>
	
	<footer>

		<p>
			<span style="text-align:left;float:left">&copy; 2013 <a href="http://jiji262.github.io/Bootstrap_Metro_Dashboard/" alt="Bootstrap_Metro_Dashboard">Bootstrap Metro Dashboard</a></span>
			
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
	<!-- end: JavaScript-->
	
</body>
</html>
