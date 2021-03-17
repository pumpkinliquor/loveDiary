$.validator.addMethod('ip', function(value, element) {
	return this.optional(element) || /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(value);
}, 'ip 정보를 정확히 입력해 주세요. (ex. 112.123.21.123)');

$.validator.addMethod('domain', function(value, element) {

	return this.optional(element) || /^((https?|s?ftp):\/\/)?(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(value);

}, '도메인을 정확히 입력해 주십시오. (ex. naver.com)');


$.validator.addMethod('email', function(value, element) {

	return this.optional(element) || /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/.test(value);

}, '이메일형식을 확인하세요. (ex. sell@naver.com)');
	

$.validator.addMethod('kor', function(value, element) {

	return this.optional(element) || /^[가-힣]+$/.test(value);

}, '한글만 입력가능합니다.');


$.validator.addMethod('eng', function(value, element) {

	return this.optional(element) || /^[a-zA-Z]+$/.test(value);

}, '영문만 입력가능합니다.');

$.validator.addMethod('eng_space', function(value, element) {

	return this.optional(element) || /^[a-zA-Z\s]+$/.test(value);

}, '영문과 공백만 입력가능합니다.');

$.validator.addMethod('eng_num_space', function(value, element) {

	return this.optional(element) || /^[a-zA-Z0-9\s]+$/.test(value);

}, '영문과 숫자, 공백만 입력가능합니다.');

$.validator.addMethod('kor_eng', function(value, element) {

	return this.optional(element) || /^[가-힣a-zA-Z]+$/.test(value);

}, '한글과 영문만 입력가능합니다.');

$.validator.addMethod('kor_eng_num', function(value, element) {
	return this.optional(element) || /^[가-힣a-zA-Z0-9\d()]+$/.test(value);
}, '한글,영문,숫자만 입력가능합니다.');

$.validator.addMethod('kor_eng_num_space', function(value, element) {
	return this.optional(element) || /^[가-힣a-zA-Z0-9\d()\s]+$/.test(value);
}, '한글,영문,숫자,공백만 입력가능합니다.');

$.validator.addMethod('eng_num', function(value, element) {

	return this.optional(element) || /^[a-zA-Z\d]+$/.test(value);

}, '영문/숫자만 입력가능합니다.');


$.validator.addMethod('num', function(value, element) {

	return this.optional(element) || /^[0-9\d]+$/.test(value);

}, '숫자만 입력가능합니다.');


$.validator.addMethod('eng_num_dash', function(value, element) {

	return this.optional(element) || /^[a-zA-Z\d-_]+$/.test(value);

}, '영문/숫자/대시(-)/언더바(_)만 입력가능합니다.');




$.validator.addMethod('num_dash', function(value, element) {

	return this.optional(element) || /^[0-9\d-]+$/.test(value);

}, '숫자/대시(-)만 입력가능합니다.');




$.validator.addMethod('mix_eng_num', function(value, element) {

	return this.optional(element) ||  /^.*(?=.*[0-9])(?=.*[a-zA-Z]).*$/.test(value);

}, '영문/숫자를 혼용하여야 합니다.');




$.validator.addMethod('mix_eng_num_spc', function(value, element) {

	return this.optional(element) ||  /^.*(?=.*[0-9])(?=.*[!@#$%^*+=-])(?=.*[a-zA-Z]).*$/.test(value);

}, '영문/숫자/특수문자를 혼용하여야 합니다.');



$.validator.addMethod('special_eng', function(value, element) {

	return this.optional(element) || /^[a-zA-Z\d@#%^&]+$/.test(value);

}, '특수문자는 (@#%^&)만 사용가능합니다');



$.validator.addMethod('noSpace', function(value, element) {

  return value.indexOf(" ") < 0 && value != ""; 
  
}, "공백을 사용하실수 없습니다.");



$.validator.addMethod('phone', function(value, element) {

	var result = false;

	var isKeyNum = false;

	try { isKeyNum = this.isKeyNumber(el) } catch(e) {}

	var pattern = isKeyNum ? /^([0-9]{2,4})-?([0-9]{4})-?([0-9]{0,4})$/ : /^([0]{1}[0-9]{1,3})-?([0-9]{3,4})-?([0-9]{4})$/;

	var num = value;

	if (pattern.exec(num)) {

		var phones = new Array("02","031","032","033","041","042","043","051","052","053","054","055","061","062","063","064","070","080","0303","0502","0503","0504","0505","0506"); // ,"060" 제외

		if(isKeyNum || (jQuery.inArray(RegExp.$1, phones) > -1)) {

			result = true;

		}

	}

	return this.optional(element) || result;

}, '전화번호를 정확히 입력하세요.');




$.validator.addMethod('hphone', function(value, element) {

	var result = false;

	var pattern = /^([0]{1}[0-9]{1,2})-?([1-9]{1}[0-9]{2,3})-?([0-9]{4})$/;

	var num = value;

	if (pattern.exec(num)) {

		var hphones = new Array("011","016","017","018","019","010");

		if(jQuery.inArray(RegExp.$1, hphones) > -1) {

			result = true;

		}

	}

	return this.optional(element) || result;

}, ' 휴대폰번호를 정확히 입력하세요.');




$.validator.addMethod('phones', function(value, element) {

	var result = false;

	var isKeyNum = false;

	try { isKeyNum = this.isKeyNumber(el) } catch(e) {}

	var pattern = isKeyNum ? /^([0-9]{2,4})-?([0-9]{4})-?([0-9]{0,4})$/ : /^([0]{1}[0-9]{1,3})-?([0-9]{3,4})-?([0-9]{4})$/;

	var num = value ? value : el.value;

	if (pattern.exec(num)) {

		var phones = new Array("011","016","017","018","019","010","02","031","032","033","041","042","043","051","052","053","054","055","061","062","063","064","070","080","0303","0502","0503","0504","0505","0506"); // ,"060" 제외

		if(isKeyNum || (jQuery.inArray(RegExp.$1, phones) > -1)) {

			result = true;

		}

	}

	return this.optional(element) || result;

}, '전화번호를 정확히 입력하세요.');




$.validator.addMethod('bizno', function(value, element) {

	value = value.replace("-", "");

	value = value.replace("-", "");




	var sum = 0;

	var getlist =new Array(10);

	var chkvalue =new Array("1","3","7","1","3","7","1","3","5");

	for(var i=0; i<10; i++) { getlist[i] = value.substring(i, i+1); }

	for(var i=0; i<9; i++) { sum += getlist[i]*chkvalue[i]; }

	sum = sum + parseInt((getlist[8]*5)/10);

	sidliy = sum % 10;

	sidchk = 0;

	if(sidliy != 0) { sidchk = 10 - sidliy; }

	else { sidchk = 0; }

	if(sidchk != getlist[9]) {

		return false;

	}

	return true;

}, '사업자번호를 정확히 입력하세요.');
