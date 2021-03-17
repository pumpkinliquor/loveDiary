

String.format = function() {
	// The string containing the format items (e.g. "{0}")
	// will and always has to be the first argument.
	var theString = arguments[0];

	// start with the second argument (i = 1)
	for (var i = 1; i < arguments.length; i++) {
	// "gm" = RegEx options for Global search (more than one instance)
	// and for Multiline search
	var regEx = new RegExp("\\{" + (i - 1) + "\\}", "gm");
	theString = theString.replace(regEx, arguments[i]);
	}

	return theString;
}

//숫자 타입에서 쓸 수 있도록 format() 함수 추가
Number.prototype.addComma = function(){
	
    if(this==0) return 0;
 
    var reg = /(^[+-]?\d+)(\d{3})/;
    var n = (this + '');
 
    while (reg.test(n)) n = n.replace(reg, '$1' + ',' + '$2');
 
    return n;
};
 
// 문자열 타입에서 쓸 수 있도록 format() 함수 추가
String.prototype.addComma = function(){
	
    var num = parseFloat(this);
    if( isNaN(num) ) return displayNull();
   
    return num.addComma();
};


//한국 요일 표시 0 : 월 ~ 6 : 일요일
Number.prototype.koreaWeekDay = function(){
	var koreaWeekDays = ['월', '화', '수', '목', '금', '토', '일'];
	var weekIndex = this;
	
	if(0 <= weekIndex && 6>= weekIndex){
		return koreaWeekDays[weekIndex];
	}else{
		return '';
	}	
}

// 한국 요일 표시 0 : 월 ~ 6 : 일요일
String.prototype.koreaWeekDay = function(){
	var koreaWeekDays = ['월', '화', '수', '목', '금', '토', '일'];
	var weekIndex = parseInt(this);
	
	if(0 <= weekIndex && 6>= weekIndex){
		return koreaWeekDays[weekIndex];
	}else{
		return '';
	}	
}

//문자열에서 str1을 str2치환
String.prototype.replaceAll = function (str1,str2){
	var str    = this;    
	var result   = str.replace(eval("/\\"+str1+"/g"),str2);
	return result;
	
};

//문자열에서 str1을 추가한 날짜포맷으로 변경.
String.prototype.DateFormat = function (str1){
	var str= this;
	if(str.length == 8){
		str = str.substring(0,4) + str1
		     +str.substring(4,6) + str1
		     +str.substring(6,8);
		
	}else if(str.length == 6){
		str = str.substring(0,4) + str1
	     +str.substring(4,6);;
	}
	
	return str;
};

//Date Format
Date.prototype.format = function(f) {
 if (!this.valueOf()) return " ";

 var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
 var weekSName = ["일", "월", "화", "수", "목", "금", "토"];
 var d = this;
  
 return f.replace(/(yyyy|yy|MM|dd|E|K|hh|mm|ss|a\/p)/gi, function($1) {
     switch ($1) {
         case "yyyy": return d.getFullYear();
         case "yy": return (d.getFullYear() % 1000).zf(2);
         case "MM": return (d.getMonth() + 1).zf(2);
         case "dd": return d.getDate().zf(2);
         case "E": return weekName[d.getDay()];
         case "K": return weekSName[d.getDay()];
         case "HH": return d.getHours().zf(2);
         case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
         case "mm": return d.getMinutes().zf(2);
         case "ss": return d.getSeconds().zf(2);
         case "a/p": return d.getHours() < 12 ? "오전" : "오후";
         default: return $1;
     }
 });
};

String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};
// IE 8 forEach
if ('function' !== typeof Array.prototype.forEach) {
	Array.prototype.forEach = function(fn, scope) {
		for(var i = 0, len = this.length; i < len; ++i) {
			fn.call(scope, this[i], i, this);
	    }
	};
}
//IE 8 trim 
if ('function' !== typeof String.prototype.trim) {
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g, ''); 
	}
}
//IE 8 indexOf 
if ('function' !== typeof Array.prototype.indexOf) {
	Array.prototype.indexOf = function(elt /*, from*/)
	{
	  var len = this.length >>> 0;
	
	  var from = Number(arguments[1]) || 0;
	  from = (from < 0)
	       ? Math.ceil(from)
	       : Math.floor(from);
	  if (from < 0)
	    from += len;
	
	  for (; from < len; from++)
	  {
	    if (from in this &&
	        this[from] === elt)
	      return from;
	  }
	  return -1;
	};
}
if ('function' !== typeof Array.prototype.reduce) {
	Array.prototype.reduce = function(callback, opt_initialValue){
	    'use strict';
	    if (null === this || 'undefined' === typeof this) {
	      // At the moment all modern browsers, that support strict mode, have
	      // native implementation of Array.prototype.reduce. For instance, IE8
	      // does not support strict mode, so this check is actually useless.
	      throw new TypeError(
	          'Array.prototype.reduce called on null or undefined');
	    }
	    if ('function' !== typeof callback) {
	      throw new TypeError(callback + ' is not a function');
	    }
	    var index, value,
	        length = this.length >>> 0,
	        isValueSet = false;
	    if (1 < arguments.length) {
	      value = opt_initialValue;
	      isValueSet = true;
	    }
	    for (index = 0; length > index; ++index) {
	      if (this.hasOwnProperty(index)) {
	        if (isValueSet) {
	          value = callback(value, this[index], index, this);
	        }
	        else {
	          value = this[index];
	          isValueSet = true;
	        }
	      }
	    }
	    if (!isValueSet) {
	      throw new TypeError('Reduce of empty array with no initial value');
	    }
	    return value;
  };
}