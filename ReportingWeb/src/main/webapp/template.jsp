<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<!-- end: Meta -->
	
	<title>
		<tiles:insertAttribute name="headerTitle" />
	</title>
	
	<!-- start: CSS -->
	<link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
	<link id="base-style" href="css/style.css" rel="stylesheet">
	<link id="base-style-responsive" href="css/style-responsive.css" rel="stylesheet">
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext' rel='stylesheet' type='text/css'>
	<!-- end: CSS -->
	
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
		function loading(){
			$('#loading').show();
			
		}
	</script>
	<!-- end: JavaScript -->

	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<link id="ie-style" href="css/ie.css" rel="stylesheet">
	<![endif]-->
	
	<!--[if IE 9]>
		<link id="ie9style" href="css/ie9.css" rel="stylesheet">
	<![endif]-->
		
	<!-- start: Favicon -->
	<link rel="shortcut icon" href="img/favicon.ico">
	<!-- end: Favicon -->
	
</head>

<body>
	<!-- start: Header -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
						<span class="icon-bar"><img src="img/logo.png" width="5%"></span>
				</a> <a class="brand"><span>ReportingTool v1.0</span></a>
			
				<!-- start: Header Menu -->
				<div class="nav-no-collapse header-nav">
					<ul class="nav pull-right">
						<!-- start: User Dropdown -->
						<li class="dropdown"><a class="btn dropdown-toggle"
							data-toggle="dropdown" href="#"> <i
								class="halflings-icon white user"></i> Admin <span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li class="dropdown-menu-title"><span>Account Settings</span></li>
								<!--
								<li><a href="#"><i class="halflings-icon user"></i> Profile</a></li>
								-->
								<li><a href="Logout"><i class="halflings-icon off"></i>
										Logout</a></li>
							</ul></li>
						<!-- end: User Dropdown -->
					</ul>
				</div>
				<!-- end: Header Menu -->
			</div>
		</div>
	</div>
	<!-- end: Header -->
	<!-- start: Main Container -->
	<div class="container-fluid-full">
		<div class="row-fluid">
			<!-- start: Main Menu -->
			<div id="sidebar-left" class="span2">
				<div class="nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li><a href="index.do"><i class="icon-bar-chart"></i><span class="hidden-tablet"> Dashboard</span></a></li>
						<li><a href="dataManager.do"><i class="icon-envelope"></i><span class="hidden-tablet"> Data Manager</span></a></li>
						<li>
							<a class="dropmenu" href="#"><i class="icon-bar-chart"></i><span class="hidden-tablet"> Admin</span></a>
							<ul>
								<li><a class="submenu" href="admin.do"><i class="icon-file-alt"></i><span class="hidden-tablet"> General Admin</span></a></li>
								<li><a class="submenu" href="fileConfig.do"><i class="icon-file-alt"></i><span class="hidden-tablet"> File Configs</span></a></li>
								<li><a class="submenu" href="fileColumList.do"><i class="icon-file-alt"></i><span class="hidden-tablet"> File Colum List</span></a></li>
								<li><a class="submenu" href="reportFieldList.do"><i class="icon-file-alt"></i><span class="hidden-tablet"> Report Field List</span></a></li>
								<li><a class="submenu" href="loadError.do"><i class="icon-file-alt"></i><span class="hidden-tablet"> Load Errors</span></a></li>
								<li><a class="submenu" href="reportError.do"><i class="icon-file-alt"></i><span class="hidden-tablet"> Report Errors</span></a></li>
								<li><a class="submenu" href="reportDataError.do"><i class="icon-file-alt"></i><span class="hidden-tablet"> Report Data Errors</span></a></li>
							</ul>	
						</li>
						<li><a href="ReportsList?r=aifm"><i class="icon-list-alt"></i><span class="hidden-tablet"> AIFM Reports</span></a></li>
						<li><a href="ReportsList?r=aif"><i class="icon-list-alt"></i><span class="hidden-tablet"> AIF Reports</span></a></li>
						<li><a href="loads.do"><i class="icon-file-alt"></i><span class="hidden-tablet"> Loads</span></a></li>
					</ul>
				</div>
			</div>
			<!-- end: Main Menu -->
				
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<!-- start: Content -->
			<div id="content" class="span10">
			
			<tiles:insertAttribute name="mainContent" />
			
			</div>
			<!-- end: Content -->
		</div>
	</div>
	<!--end: Main Container -->
		
	<tiles:insertAttribute name="modalWindow" />
	
	<div id="loading" style="position: fixed;top: 50%;right: 50%;left: 50%;width: auto;margin: 0;">
		<img alt="blabla" src="img/progress.gif" ></img>
	</div>
	
	<div class="clearfix"></div>
	
	<footer>
		<p>
			<span style="text-align:left;float:left">&copy; 2014 - Lynx Iberica (Madrid)</span>
		</p>
	</footer>
	
</body>
</html>
