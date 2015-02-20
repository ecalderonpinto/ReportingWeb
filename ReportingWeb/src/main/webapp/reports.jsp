<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="Login">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Reports</a></li>
</ul>
<!-- end: Breadcrumb -->

<!-- Filter Box -->
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon filter"></i><span class="break"></span>Filters
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a> <a href="#"
					class="btn-close"><i class="halflings-icon remove"></i></a>
			</div>
		</div>
		<div class="box-content">
			<form name="repform" action="Filter">
				<table class="table table-bordered">
					<tr>
						<td><label class="control-label" for="selectError">AIFM
								Group</label>
							<div class="controls">
								<select id="selectError" data-rel="chosen">
									<option>AIFM Group 1</option>
									<option>AIFM Group 2</option>
									<option>AIFM Group 3</option>
									<option>AIFM Group 4</option>
									<option>AIFM Group 5</option>
								</select>
							</div></td>
						<td><label class="control-label" for="inlineCheckbox1">Frequency:</label>
							<div class="controls">
								<label class="checkbox inline"> <input type="checkbox"
									id="inlineCheckbox1" value="option1"> Yearly
								</label> <label class="checkbox inline"> <input type="checkbox"
									id="inlineCheckbox2" value="option2"> Half-Yearly
								</label> <label class="checkbox inline"> <input type="checkbox"
									id="inlineCheckbox3" value="option3"> Quarterly
								</label>
							</div></td>
						<td style="text-align: center;"><button
								class="btn btn-small btn-primary">Search</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<!-- /Filter Box -->

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i><span class="break"></span>Fund
				Reports
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content">
			<form name="repAIFMform" action="Report">
				<table
					class="table table-striped table-bordered table-condensed datatable">
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
							<td onmouseover="javascript:this.style.cursor='pointer'"
								onclick="repAIFMform.submit();">AIFMD_F120_2014</td>
							<td class="center">2012/01/01</td>
							<td class="center">2012/01/01</td>
							<td class="center"><span class="label label-success">Dispatched</span>
							</td>
							<td>
								<div class="taskProgress progressSlim green">47</div>
							</td>
						</tr>
						<tr>
							<td><input type="checkbox" id="export2" value="export2"></td>
							<td onmouseover="javascript:this.style.cursor='pointer'"
								onclick="repAIFMform.submit();">AIFMD_v3_2013</td>
							<td class="center">2012/03/01</td>
							<td class="center">2012/01/01</td>
							<td class="center"><span class="label label-warning">Pending</span>
							</td>
							<td>
								<div class="taskProgress progressSlim yellow">60</div>
							</td>
						</tr>
						<tr>
							<td><input type="checkbox" id="export3" value="export3"></td>
							<td onmouseover="javascript:this.style.cursor='pointer'"
								onclick="repAIFMform.submit();">AIFMD_h10_v5_2014</td>
							<td class="center">2012/03/01</td>
							<td class="center">2012/01/01</td>
							<td class="center"><span class="label label-important">Rejected</span>
							</td>
							<td>
								<div class="taskProgress progressSlim red">25</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->

<!-- Actions Box -->
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon filter"></i><span class="break"></span>Actions
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content">
			<form name="repform" action="Filter">
				<button class="btn-setting btn btn-small btn-primary">Export</button>
				<button class="btn btn-small btn-danger">Delete</button>
			</form>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<!-- /Actions Box -->