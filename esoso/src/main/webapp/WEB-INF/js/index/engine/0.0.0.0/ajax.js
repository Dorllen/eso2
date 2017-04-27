var getAysnc = function(url, call) {
	var ajax = null;
	if(window.XMLHttpRequest) {
		ajax = new XMLHttpRequest();
	} else {
		ajax = new ActiveXObject("Microsoft.XMLHTTP");
	}
//	ajax.setRequestHeader("","");
	ajax.open("GET",url, true);
	ajax.send();
	ajax.onreadystatechange = function() {
		if(ajax.status == 200 && ajax.readyState == 4) {
			if(call) {
				call(ajax.responseText);
			}
		}
	}
}
var postAysnc = function(url, data, call) {
	var ajax = null;
	if(window.XMLHttpRequest) {
		ajax = new XMLHttpRequest();
	} else {
		ajax = new ActiveXObject("Microsoft.XMLHttp");
	}
	ajax.open("POST",url, true);
	ajax.send(data);
	ajax.onreadystatechange = function(data) {
		if(data.status == 200 && data.readyState == 4) {
			if(call) {
				call(data);
			}
		}
	}
}