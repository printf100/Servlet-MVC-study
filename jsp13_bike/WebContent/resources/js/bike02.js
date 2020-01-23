$(function() {
	parseJsonTest();
})

function parseJsonTest() {
	
	$.getJSON("resources/json/bike.json", function(data) {
		
		$.ajax({
			url: "bike.do?command=seconddb",
			method: "post",
			data: {"obj":JSON.stringify(data)},
			success: function(msg) {	// text로 넘어올 것임
				
				if(msg == 1163) {
					
					$.each(data, function(key, val) {
						
						if(key == "DESCRIPTION") {
							
							$("table").attr("border", "1");
							$("thead").append(
									"<tr>" +
										"<th>" + val.ADDR_GU + "</th>" +
										"<th>" + val.CONTENT_ID + "</th>" +
										"<th>" + val.CONTENT_NM + "</th>" +
										"<th>" + val.NEW_ADDR + "</th>" +
										"<th>" + val.CRADLE_COUNT + "</th>" +
										"<th>" + val.LONGITUDE + "</th>" +
										"<th>" + val.LATITUDE + "</th>" +
									"</tr>"
							);
							
						} else if(key == "DATA") {
							
							for(var i=0; i<val.length; i++) {
								
								var v = val[i];
								
								$("tbody").append(
										"<tr>" +
											"<td>" + v.addr_gu + "</td>" +
											"<td>" + v.content_id + "</td>" +
											"<td>" + v.content_name + "</td>" +
											"<td>" + v.new_addr + "</td>" +
											"<td>" + v.cradle_count + "</td>" +
											"<td>" + v.longitude + "</td>" +
											"<td>" + v.latitude + "</td>" +
											"<input type='hidden' name='bike' value='" + v.addr_gu + "/" + v.content_id + "/" + v.content_name + "/" + v.new_addr + "/" + v.cradle_count + "/" + v.longitude + "/" + v.latitude + "'>" +
										"</tr>"
								);
							}
						}
					});
					
				} else {
					alert("저장 실패");
				}
			},
			error: function() {
				alert("통신 실패");
			}
		});
	});
}