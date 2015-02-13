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
					<p>
						You need to have <a href="http://en.wikipedia.org/wiki/JavaScript"
							target="_blank">JavaScript</a> enabled to use this site.
					</p>
				</div>
			</noscript>

			<!-- start: Content -->
			<div id="content" class="span10">
				<!-- start: Breadcrumb -->
				<ul class="breadcrumb">
					<li><i class="icon-home"></i> <a href="Login">Home</a> <i class="icon-angle-right"></i></li>
					<li><a href="#">Report</a> <i class="icon-angle-right"></i></li>
					<li><a href="#">AIFM_Sant_v10</a></li>
				</ul>
				<!-- end: Breadcrumb -->
				
				<form class="form-horizontal" action="ReportsList" method="get">
				<input type="hidden" id="r" name="r" value="aif" />
				<!-- AIFMRecordInfo -->
				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header" data-original-title>
							<h2>
								<i class="halflings-icon edit"></i><span class="break"></span>General Info
							</h2>
							<div class="box-icon">
								<!--
								<a href="#" class="btn-setting"><i
									class="halflings-icon wrench"></i></a>
								-->
								<a href="#"
									class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
								<!--
								<a href="#" class="btn-close"><i
									class="halflings-icon remove"></i></a>
								-->
							</div>
						</div>
						<div class="box-content">
								<fieldset>
									<!-- ReportingMemberState -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											1. Member State
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="ES">
											<!--<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>-->
										</div>
									</div>
									<!-- /ReportingMemberState -->
									<!-- Version -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											2. Version
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="1.2">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /Version -->
									<!-- CreationDateAndTime -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											3. Creation date and time
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="12-12-2014">
											<!--<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>-->
										</div>
									</div>
									<!-- /CreationDateAndTime -->
									<!-- FilingType -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											4. Filing type
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="INIT">
											<!--<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>-->
										</div>
									</div>
									<!-- /FilingType -->
									<!-- AIFMContentType -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											5. Content Type
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="1">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /AIFMContentType -->
									<!-- ReportingPeriodStartDate -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											6. Period Start Date
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="01-10-2014">
											<!--<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>-->
										</div>
									</div>
									<!-- /ReportingPeriodStartDate -->
									<!-- ReportingPeriodEndDate -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											7. Period End Date
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="31-12-2014">
											<!--<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>-->
										</div>
									</div>
									<!-- /ReportingPeriodEndDate -->
									<!-- ReportingPeriodType -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											8. Period Type
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="Q4">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /ReportingPeriodType -->
									<!-- ReportingPeriodYear -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											9. Period Year
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="2014">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /ReportingPeriodYear -->
									<!-- AIFMReportingObligationChangeFrequencyCode -->
									<div class="control-group error">
										<label class="control-label" for="inputError">
											10. Change Frequency Code
										</label>
										<div class="controls">
											<input type="text" id="inputError" disabled="" value="QX">
											<span class="help-inline">
												<i class="halflings-icon pencil"></i>
												No valid qualifier
											</span>
										</div>
									</div>
									<!-- /AIFMReportingObligationChangeFrequencyCode -->
									<!-- AIFMReportingObligationChangeContentsCode -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											11. Change Contents Code
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="4">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /AIFMReportingObligationChangeContentsCode -->
									<!-- AIFMReportingObligationChangeQuarter -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											12. Change Quarter
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="Q4">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /AIFMReportingObligationChangeQuarter -->
									<!-- LastReportingFlag -->
									<div class="control-group">
										<label class="control-label" for="selectLastFlag">
											13. Last Flag
										</label>
										<div class="controls">
											<select id="selectlastFlag" disabled="">
												<option></option>
												<option>True</option>
												<option selected="selected">False</option>
											</select>
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /LastReportingFlag -->
								</fieldset>
							</div>
						</div>
						<!--/span-->
					</div>
					<!-- /AIFMRecordInfo -->
									
					<!-- AIFMAssumptions -->
					<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header" data-original-title>
							<h2>
								<i class="halflings-icon edit"></i><span class="break"></span>14/15. Assumptions
							</h2>
							<div class="box-icon">
								<!--
								<a href="#" class="btn-setting"><i
									class="halflings-icon wrench"></i></a>
								-->
								<a href="#"
									class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
								<!--
								<a href="#" class="btn-close"><i
									class="halflings-icon remove"></i></a>
								-->
							</div>
						</div>
						<div class="box-content">
								<fieldset>
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											Question Number
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="25">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											Description
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="Free text">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label" for="disabledInput">
										</label>
									</div>
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											Question Number
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="30">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											Description
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="Free text">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
								</fieldset>
						</div>
					</div>
					<!--/span-->
				</div>
				<!-- /AIFMAssumptions -->
				<!-- ---------------------------------------------------------------------------------------------------------------------------------------------- -->
					<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header" data-original-title>
							<h2>
								<i class="halflings-icon edit"></i><span class="break"></span>General Info
							</h2>
							<div class="box-icon">
								<!--
								<a href="#" class="btn-setting"><i
									class="halflings-icon wrench"></i></a>
								-->
								<a href="#"
									class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
								<!--
								<a href="#" class="btn-close"><i
									class="halflings-icon remove"></i></a>
								-->
							</div>
						</div>
						<div class="box-content">
							<fieldset>
									<!-- AIFMReportingCode -->
									<div class="control-group">
										<label class="control-label" for="selectLastFlag">16. Code</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="4">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /AIFMReportingCode -->
									<!-- AIFMJurisdiction -->
									<div class="control-group">
										<label class="control-label" for="selectLastFlag">17. Jurisdiction</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="United Kingdom">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /AIFMJurisdiction -->
									<!-- AIFMNationalCode -->
									<div class="control-group">
										<label class="control-label" for="selectLastFlag">18. National Code</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="474286">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /AIFMNationalCode -->
									<!-- AIFMName -->
									<div class="control-group">
										<label class="control-label" for="selectLastFlag">19. Name</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="AIFM 1">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /AIFMName -->
									<!-- AIFMEEAFlag -->
									<div class="control-group">
										<label class="control-label" for="selectLastFlag">20. EEA Flag</label>
										<div class="controls">
											<select id="selectlastFlag" disabled="">
												<option></option>
												<option selected="selected">True</option>
												<option>False</option>
											</select>
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /AIFMEEAFlag -->
									<!-- AIFMNoReportingFlag -->
									<div class="control-group">
										<label class="control-label" for="selectLastFlag">21. Flag</label>
										<div class="controls">
											<select id="selectlastFlag" disabled="">
												<option></option>
												<option>True</option>
												<option selected="selected">False</option>
											</select>
										<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /AIFMNoReportingFlag -->
								</fieldset>
						</div>
					</div>
					<!--/span-->
				</div>
				<!-- /AIFMRecordInfo -->
				
				<!-- AIFMCompleteDescription -->
				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header" data-original-title>
							<h2>
								<i class="halflings-icon edit"></i><span class="break"></span>Complete Description
							</h2>
							<div class="box-icon">
								<!--
								<a href="#" class="btn-setting"><i
									class="halflings-icon wrench"></i></a>
								-->
								<a href="#"
									class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
								<!--
								<a href="#" class="btn-close"><i
									class="halflings-icon remove"></i></a>
								-->
							</div>
						</div>
						<div class="box-content">
								<fieldset>
									<!-- AIFMIdentifierLEI -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											22. LEI Code
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="969500AA77L4ZJXJ0T02">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /AIFMIdentifierLEI -->
									<!-- AIFMIdentifierBIC -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											23. BIC Code
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="TESTGB21XXX">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /AIFMIdentifierBIC -->
									
									<div class="box span11">
										<div class="box-header" data-original-title>
											<h2>
												<span class="break"></span>26. Principal Markets
											</h2>
											<div class="box-icon">
												<!--
												<a href="#" class="btn-setting"><i
													class="halflings-icon wrench"></i></a>
												-->
												<a href="#"
													class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
												<!--
												<a href="#" class="btn-close"><i
													class="halflings-icon remove"></i></a>
												-->
											</div>
										</div>
										<div class="box-content">
											<fieldset>
												<% for(int i=0;i<5;i++){ %>
												<div class="control-group">
												<label class="control-label" for="disabledInput">
													Ranking
												</label>
												<div class="controls">
													<input class="input-xlarge disabled" id="disabledInput"
														type="text" placeholder="Disabled input here…" disabled="" value="<%=i+1%>">
													<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
												</div>
												</div>
												<div class="control-group">
													<label class="control-label" for="disabledInput">
														Market Code
													</label>
													<div class="controls">
														<input class="input-xlarge disabled" id="disabledInput"
															type="text" placeholder="Disabled input here…" disabled="" value="XXX">
														<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" for="disabledInput">
														Aggregated Amount
													</label>
													<div class="controls">
														<input class="input-xlarge disabled" id="disabledInput"
															type="text" placeholder="Disabled input here…" disabled="" value="4520000000">
														<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" for="disabledInput">
													</label>
												</div>
												<% } %>
											</fieldset>
										</div>
									</div>
									
									<div class="box span11">
										<div class="box-header" data-original-title>
											<h2>
												<span class="break"></span>30. Principal Instruments
											</h2>
											<div class="box-icon">
												<!--
												<a href="#" class="btn-setting"><i
													class="halflings-icon wrench"></i></a>
												-->
												<a href="#"
													class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
												<!--
												<a href="#" class="btn-close"><i
													class="halflings-icon remove"></i></a>
												-->
											</div>
										</div>
										<div class="box-content">
											<fieldset>
												<% for(int i=0;i<5;i++){ %>
												<div class="control-group">
												<label class="control-label" for="disabledInput">
													Ranking
												</label>
												<div class="controls">
													<input class="input-xlarge disabled" id="disabledInput"
														type="text" placeholder="Disabled input here…" disabled="" value="<%=i+1%>">
													<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
												</div>
												</div>
												<div class="control-group">
													<label class="control-label" for="disabledInput">
														Sub Asset Type
													</label>
													<div class="controls">
														<input class="input-xlarge disabled" id="disabledInput"
															type="text" placeholder="Disabled input here…" disabled="" value="SEC_LEQ_OTHR">
														<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" for="disabledInput">
														Aggregated Amount
													</label>
													<div class="controls">
														<input class="input-xlarge disabled" id="disabledInput"
															type="text" placeholder="Disabled input here…" disabled="" value="4520000000">
														<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" for="disabledInput">
													</label>
												</div>
												<% } %>
											</fieldset>
										</div>
									</div>
								</fieldset>
						</div>
					</div>
					<!--/span-->
				</div>
				<!-- /AIFMCompleteDescription -->
				
				<!-- CancellationAIFMRecordInfo -->
				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header" data-original-title>
							<h2>
								<i class="halflings-icon edit"></i><span class="break"></span>Cancellation Info
							</h2>
							<div class="box-icon">
								<!--
								<a href="#" class="btn-setting"><i
									class="halflings-icon wrench"></i></a>
								-->
								<a href="#"
									class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
								<!--
								<a href="#" class="btn-close"><i
									class="halflings-icon remove"></i></a>
								-->
							</div>
						</div>
						<div class="box-content">
								<fieldset>
									<!-- CancelledAIFMNationalCode -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											39. Cancelled National Code
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="Id2">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /CancelledAIFMNationalCode -->
									<!-- CancelledReportingPeriodType -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											40. Cancelled Period Type
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="X1">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /CancelledReportingPeriodType -->
									<!-- CancelledReportingPeriodYear -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											41. Cancelled Period Year
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="2014">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /CancelledReportingPeriodYear -->
									<!-- CancelledRecordFlag -->
									<div class="control-group">
										<label class="control-label" for="disabledInput">
											42. Cancelled Record Flag
										</label>
										<div class="controls">
											<input class="input-xlarge disabled" id="disabledInput"
												type="text" placeholder="Disabled input here…" disabled="" value="C">
											<i class="halflings-icon tag" onmouseover="javascript:this.style.cursor='pointer'" title="Notes"></i>
										</div>
									</div>
									<!-- /CancelledRecordFlag -->
								</fieldset>
						</div>
					</div>
					<!--/span-->
				</div>
				<!-- /CancellationAIFMRecordInfo -->
				
				<!-- FormActions -->
				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header" data-original-title>
							<h2>
								<i class="halflings-icon edit"></i><span class="break"></span>Actions
							</h2>
							<div class="box-icon">
							</div>
						</div>
						<div class="box-content">
							<fieldset>
								<div class="form-actions">
									<button type="submit" class="btn btn-primary">Save
										changes</button>
									<button class="btn">Cancel</button>
								</div>
							</fieldset>

						</div>
					</div>
					<!--/span-->
				</div>
				<!-- /FormActions -->
				</form>
			</div>
			<!-- end: Content -->
		</div>
	</div>

	<div class="clearfix"></div>

	<footer>

		<p>
			<span style="text-align: left; float: left">&copy; 2013 <a
				href="http://jiji262.github.io/Bootstrap_Metro_Dashboard/"
				alt="Bootstrap_Metro_Dashboard">Bootstrap Metro Dashboard</a></span>

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
