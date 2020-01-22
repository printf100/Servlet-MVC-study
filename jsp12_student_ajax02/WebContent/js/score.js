
function load() {
	var url = getParameterValues();
	
	httpRequest = new XMLHttpRequest();	// 서버와 통신
	httpRequest.onreadystatechange = callback;	// readystate가 바뀌는 이벤트가 실행됐을 때, callback함수를 실행하라 (call하면 요청에 맞게 실행하고 결과를 back해준다)
	httpRequest.open("GET", url, true);	// true : 비동기 / false : 동기
	httpRequest.send();	// "GET" : send() / "POST" : send(보낼 String 데이터)
}

/*
 * 
 * XMLHttpRequest : 서버와 통신을 도와주는 javascript object (http를 통한 데이터 송수신 지원)
 * 
 * 
 * readyState
 * 0 : uninitialized - 실행(load)되지 않음
 * 1 : loading - 로드중
 * 2 : loaded - 로드됨
 * 3 : interactive - 통신됨
 * 4 : complete - 통신완료
 * 
 * 
 * status
 * 200 : 통신 성공
 * 400 : bad request
 * 401 : unauthorized (권한 없음)
 * 403 : forbidden (인증 실패)
 * 404 : not found
 * 500 : internal server error
 * 
 * 
 * encodeURIComponent : 모든 문자를 인코딩
 * encodeURI	: 주소줄에 사용되는 특수문자는 제외하고 인코딩
 * 
 */

function callback() {
	alert("readyState : " + httpRequest.readyState);
	
	if(httpRequest.readyState == 4) {
		alert("status : " + httpRequest.status);
		
		if(httpRequest.status == 200) {
			var obj = JSON.parse(httpRequest.responseText);
			
			document.getElementById("result").innerHTML = decodeURIComponent(obj.name)+"님의 총점은 " + obj.sum + "이고, 평균은 " + obj.avg + "입니다.";
			
		} else {
			alert("통신 실패");
		}
	}
}

function getParameterValues() {
	var name = "name="+encodeURIComponent(document.getElementById("name").value);
	var kor = "kor="+document.getElementById("kor").value;
	var eng = "eng="+document.getElementById("eng").value;
	var math = "math="+document.getElementById("math").value;
	
	var url = "score.cal?"+name+"&"+kor+"&"+eng+"&"+math;
	console.log(url);
	
	return url;
}


