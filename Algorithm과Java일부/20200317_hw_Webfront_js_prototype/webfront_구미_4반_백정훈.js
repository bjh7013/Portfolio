/**
 * MyMap 생성자로 사용될 함수를 구현
 */

var data = [ 2, "ssafy", "싸피", a=[3,4,5]];

function map(data) {
	for (var i = 0; i < data.length; i++) {
		if(data[i] instanceof Array){
			console.dir(data[i][3]);
			continue;
		}
		console.dir(data[i]);
	}
}

map.prototype.insa = function() {
	console.log("0");
}
var m1 = new map(data);
m1.insa();