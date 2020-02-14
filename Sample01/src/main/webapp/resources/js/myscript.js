/**
 * 
 */

// 타임스탬프(밀리세컨드) 값을 받아서 문자열 형태로 반환
function dateString(mSec) {
	var d = new Date(mSec);
	var year = d.getFullYear();		// 년
	var month = d.getMonth() + 1;	// 월
	var date = d.getDate();			// 일
	var hour = d.getHours();		// 시
	var minute = d.getMinutes();	// 분
	var sec = d.getSeconds();		// 초
	
//	dd
	return 	year + "/" + 
			make2digits(month) 	+ "/" + 
			make2digits(date)	+ " " + 
			make2digits(hour) 	+ ":" + 
			make2digits(minute) + ":" + 
			make2digits(sec);
	
}

// 자릿수를 2자리로 맞추기
function make2digits(num) {
	return (num < 10) ? "0" + num : num;
	
//	if (num < 10) {
//		return "0" + num;
//	} else {
//		return num;
//	}
	
}


// 이미지인지 아닌지 체크
function checkImage(fileName) {
	var dotIndex = fileName.lastIndexOf(".");
	var formatName = fileName.substring(dotIndex + 1).toUpperCase();
	var arrFormat = ["JPG", "PNG", "GIF"];

	for (var v = 0; v < arrFormat.length; v++)	{
		if (formatName == arrFormat[v])	{
			return true;
		}
	}
	
//	arrFormat.forEach(function (aFormat) {
//		if(formatName == aFormat)	{
//			return true;
//		}
//	});
	
	return false;
}


// 썸네일 이미지의 이름 얻기
// 2020/1/20/8f120ba2-67c0-4e18-b71c-12a8c787b281_peng02.jpg
function getThumbnailName(full_name){
	var slashIndex = full_name.lastIndexOf("/");			// 뒤에서 부터 찾기
	var front = full_name.substring(0, slashIndex + 1);	// 첫번째부터 slashIndex 까지
														// 2020/1/20
	var rear = full_name.substring(slashIndex + 1);		// slashIndex 부터 끝까지
														// 8f120ba2-67c0-4e18-b71c-12a8c787b281_peng02.jpg
	var thumbnailName = front + "s_" + rear;
	
	return thumbnailName;
}



