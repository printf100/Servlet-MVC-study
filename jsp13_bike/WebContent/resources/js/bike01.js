
// = window.onload
$(function() {
	getJsonTest();
})

function getJsonTest() {
	// bike.json 파일을 get방식으로 요청해서 요청이 성공하면 서버로부터 받은 데이터로 콜백함수 실행
	/*
	 * $.ajax({
  		dataType: "json",
  		url: url,
  		data: data,
  		success: success
	 * });
	 */
	$.getJSON("resources/json/bike.json", function(data) {
		// 가져온 데이터 각각을 iterate하겠다
		$.each(data, function(key, val) {
		
			if(key == "DESCRIPTION") {	// key가 이거라면
				
				$("table").attr("border", "1");
				$("thead").append(	// value에서 각 이름으로 값을 꺼내오고 테이블을 만든다
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
				
				var list = val;	// list에 DATA key에 있는 배열을 넣고
				
				for(var i=0; i<list.length; i++) {	// list의 길이만큼 돌려라
					var str = list[i];	// i번지의 배열을 str에 셋팅
					$("tbody").append(
							"<tr>" +	// i번지의 배열에서 각 이름으로 값을 꺼내서 테이블을 만든다
								"<td>" + str.addr_gu + "</td>" +
								"<td>" + str.content_id + "</td>" +
								"<td>" + str.content_nm + "</td>" +
								"<td>" + str.new_addr + "</td>" +
								"<td>" + str.cradle_count + "</td>" +
								"<td>" + str.longitude + "</td>" +
								"<td>" + str.latitude + "</td>" +
								"<input type='hidden' name='bike' value='" + str.addr_gu + "/" + str.content_id + "/" + str.content_nm + "/" + str.new_addr + "/" + str.cradle_count + "/" + str.longitude + "/" + str.latitude + "'>" +
							"</tr>"
					);
				}
				
			}
		})
	})
}