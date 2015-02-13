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
						<i class="icon-angle-right"></i></li>
					<li><a href="#">Load Detail</a>
				</ul>
				<!-- end: Breadcrumb -->
				<div class="row-fluid sortable">		
					<div class="box span12">
						<div class="box-header">
							<h2><i class="halflings-icon align-justify"></i><span class="break"></span>Load 1</h2>
							<div class="box-icon">
								<a href="#" class="btn-setting"><i class="halflings-icon wrench"></i></a>
								<a href="#" class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
								<a href="#" class="btn-close"><i class="halflings-icon remove"></i></a>
							</div>
						</div>
						<div class="box-content">
						<!-- table table-bordered table-striped table-condensed table table-striped table-bordered bootstrap-datatable datatable-->
						<form name="repform" action="Report">
							<table class="table table-striped table-bordered table-condensed datatable">
								<thead>
									<tr>
										<th width="5%">Raw Msg</th>
										<th>Name</th>
										<th>Status</th>
										<th>Completed</th>
								  	</tr>
							  	</thead>   
							  	<tbody>
									<tr>
										<td><a href="#" class="btn-setting" title="bal bla"><i class="icon-eye-open"></i></a></td>
										<td>Reg 1</td>
										<td>PROCESSED</td>
										<td><span class="label label-success">Success</span></td>
									</tr>
									<tr>
										<td><a href="#" class="btn-setting" title="bal bla"><i class="icon-eye-open"></i></a></td>
										<td>Reg 2</td>
										<td>PROCESSED</td>
										<td><span class="label label-important">Failed</span></td>
									</tr>
									<tr>
										<td><a href="#" class="btn-setting" title="bal bla"><i class="icon-eye-open"></i></a></td>
										<td>Reg 3</td>
										<td>PROCESSED</td>
										<td><span class="label label-success">Success</span></td>
									</tr>
									<tr>
										<td><a href="#" class="btn-setting" title="bal bla"><i class="icon-eye-open"></i></a></td>
										<td>Reg 4</td>
										<td>PROCESSED</td>
										<td><span class="label label-success">Success</span></td>
									</tr>
								</tbody>
							</table>
						</form>
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
			<h3>Raw message</h3>
		</div>
		<div class="modal-body">
			<p>ASSETMNG1|2014-10-01|GB|INIT|2|2014-10-01|2014-12-31|Q4|2014|QH|Q4|false|4|GB|474286|AIFM 1|true|false|969500AA77L4ZJXJ0T02|TESTGB21XXX|
1|XXX||452000000|2|MIC|XEUR|42800000|3|MIC|XNAS|22782456|4|MIC|XBRU|11478685|5|NOT|||
1|SEC_LEQ_OTHR|248478000|2|DER_FID_FIXI|145786532|3|PHY_TIM_PTIM|14473914|4|CIU_OAM_OTHR|7214939|5|NTA_NTA_NOTA||
5500000|USD|7253950|ECB|12345678901234.7535|
Id2|X1|2014|C|</p>
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
	<script type="text/javascript">
		function mostrar(){
			$('#prueba').show('slow');
		}
	</script>
	<!-- end: JavaScript-->
	
</body>
</html>
