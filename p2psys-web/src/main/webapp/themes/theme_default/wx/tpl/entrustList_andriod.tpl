{{#each data.list}}
<div class="item">
	<a href="/wx/account/detail.html?id={{id}}&startTime={{addTime}}" title="{{name}}">
		<table>
			<tr>
				<td rowspan="3" class="schedule">
					<div class="schedule_andriod">
						<div class="circle_andriod">{{scales}}%</div>
					</div>
				</td>
				<td colspan="2" class="title">
					<div class="title-content">
						<a href="/wx/account/detail.html?id={{id}}&startTime={{addTime}}" style="font-weight:bold;" title="{{name}}">{{name}}</a>
					</div>
				</td>
				<td class="label"><div class="label-content">{{lowestAccount}}起投</div></td>
			</tr>
			<tr class="data">
				<td>{{ppfundAccountWait account accountYes}}元</td>
				<td>{{expectedLow}}%~{{expectedUp}}%</td>
				<td>{{{timeLimitFormat type timeLimit borrowTimeType}}}</td>
			</tr>
			<tr class="param">
				<td>金额</td>
				<td style="padding-left:15px;">利率</td>
				<td>期限</td>
			</tr>
		</table>
	</a>
</div>
{{/each}}