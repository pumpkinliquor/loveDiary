"use strict";
window.AjaxHandler = (function(AjaxHandler, $, undefined) {

    var success = function(result) {
        BlockUtils.blockEnd();
        var resultMessage 		= result.resultMessage;
        var resultCode			= result.resultCode;
        var resultUrl 			= result.resultUrl;
        var resultAction 		= result.resultAction;
        var resultValue		    = result.resultValue;

        if (CommonUtils.isNotEmpty(resultMessage)) {
            alert(resultMessage);
        }
        if ('success' == resultCode) {
            if (CommonUtils.isNotEmpty(resultUrl)) {
                switch (resultUrl) {
                    case 'none':
                        break;
                    case 'refresh':
                        location.reload();
                        break;
                    case 'close':
                        self.close();
                        break;
                    case 'closeWindowPopupAndOpenerRefresh':
                        window.opener.location.reload();
                        window.close();
                        break;
                    case 'closeLayerPopup':
                        LayerPopupUtils.close();
                        break;
                    case 'closeLayerPopupAndOpenerRefresh':
                        LayerPopupUtils.close();
                        location.reload();
                        break;
                    case 'tabClose' :
                        lgUI.grid.searchGrid($('#gridSearch').domJson());
                        $('#plustabs > ul li').eq($('#plustabs > ul li.ui-tabs-active').index()).find('.ui-icon-close').click();
                        break;
                    case 'notice':
                        var noticeCnt   = Number($(".notice > span").text());
                        if(noticeCnt > 0) {
                            noticeCnt   = noticeCnt - 1;
                            console.log(noticeCnt);
                            $(".notice > span").text(noticeCnt--);
                        }
                        console.log(noticeCnt);

                        break;
                    case 'setCockie':
                        setCookie('confirmNum', result.resultConfirNum, 1);
                        break;
                    default:
                        location.href = resultUrl;
                }
            }
            if (CommonUtils.isNotEmpty(resultAction)) {
                switch (resultAction) {
                    case 'openerRefresh':
                        opener.parent.location.reload();
                        break;
                    case 'call_countSeconds':
                        $(".sendSms").hide();
                        $(".clock").show();
                        countSeconds();
                        break;
                    default:
                        eval(resultAction);
                }
            }
        }
    };

    var error = function(request, status, error) {
        BlockUtils.blockEnd();
        if (request.status == 0) {
            // 서버연결 문제 or ajax 통신 완료전 새로고침, 페이지 이동 시
            return false;
        }
        // console.log("code:" + request.status + "\nmessage:" +
        // request.responseText + "\nerror:" + error);
        if (CommonUtils.isNotEmpty(request.responseText)) {
            //alert("오류가 발생하였습니다.");
            alert(request.responseText);
        }
    };

    var submitForm = function(formId, options, validator) {
        var returnFlag = true;

        var ajaxOptions = options || {
            dataType : 'json',
            type    : 'post',
            success : success,
            error : error
        };
        if (validator && validator != true) {
            ajaxOptions.beforeSubmit = validator;
        }
        // 공통 기본 유효성 검사.
        //if (validator) {
        //check class 를 갖고 있는 필드만 검사한다.

        var submitForm = typeof(formId)=='string' ? $("."+formId) :  formId;

        if(returnFlag)
            submitForm.ajaxSubmit(ajaxOptions);
    }
    //AjaxHandler.submit('form id', 'options', '유효성체크할 함수명');
    var submit = function(formId, options, validator) {
        BlockUtils.blockStart();
        var returnFlag = true;

        var ajaxOptions = options || {
            dataType : 'json',
            type    : 'post',
            success : success,
            error : error
        };
        if (validator && validator != true) {
            ajaxOptions.beforeSubmit = validator;
        }
        // 공통 기본 유효성 검사.
        //if (validator) {
        //check class 를 갖고 있는 필드만 검사한다.

        var submitForm = typeof(formId)=='string' ? $("."+formId) :  formId;

        submitForm.find(".check").each(function() {
            var _thisVal = "";
            var _tagName = $(this).get(0).tagName.toLowerCase();
            _thisVal = $(this).val();
            /*if(_tagName == "input") {
             _thisVal = $(this).val();
             } else if(_tagName === "select") {
             _thisVal = $(this).val();
             } else if(_tagName == "textarea") {
             _thisVal = $(this).val();
             }*/
            var _thisFildName = $(this).parents('td').siblings('th,td').text();
            if(!_thisVal) {
                alert(_thisFildName+"에 값을 입력해 주세요.");
                $(this).focus();
                returnFlag =  false;
                return false;
            }
        });
        //}
        if(returnFlag)
            submitForm.ajaxSubmit(ajaxOptions);
    };

    var submitAjax = function($url, $type) {
        $.ajax({
            dataType : 'json',
            //contentType : 'application/jsonp',
            type : $type || "post",
            url : $url,
            success : success,
            error : error
        });
    };
    /**
     * $url
     * $data = key=value&key=value
     */
    var submitAjaxData = function($url, $data) {
        BlockUtils.blockStart();
        $.ajax({
            dataType : 'json',
            //contentType : 'application/jsonp',
            type : "post",
            data : $data,
            url : $url,
            global: false,
            success : success,
            error : error
        });
    };

    var submitForm = function(form) {
        form = $("#".form);
        var formJSON = form.serialize();
        // console.log(formJSON);
        $.ajax({
            dataType : 'json',
            contentType : 'application/json',
            type : form.attr('method'),
            url : form.attr('action'),
            data : JSON.stringify(formJSON),
            success : success,
            error : error
        });
    };

    var submitFormHandler = function(btnSelector, validator) {
        $(btnSelector).click(function(e) {
            e.preventDefault();
            var valid = true;
            var form = $(this).closest('form');
            if ($.isFunction(validator)) {
                valid = validator();
            }
            if (valid) {
                submitForm(form);
            }
        });
    };

    var ajaxCallBackHandler = function(options, callBack) {
        var ajaxOptions = {
            type : 'post',
            dataType : 'json',
            success : function(result) {
                success(result);
                if ('success' == result.resultCode) {
                    callBack(options.data);
                }
            },
            error : error
        };
        $.ajax($.extend({}, ajaxOptions, options));
    };

    return {
        submit : submit,
        success : success,
        error : error,
        submitForm : submitForm,
        submitAjax : submitAjax,
        submitAjaxData : submitAjaxData,
        submitFormHandler : submitFormHandler,
        ajaxCallBackHandler : ajaxCallBackHandler
    }
})(window.AjaxHandler || {}, jQuery);

/**
 * 파일관련 유틸
 * @type {{download}}
 */
window.popupCodeUtils = (function(popupCodeUtils, $, undefined) {
    var element_layer = ''
    var layer   = function() {
        var layerPopup    =
            '<div id="daumLayer" style="display:block;position:fixed;overflow:hidden;z-index:100;-webkit-overflow-scrolling:touch;">\n' +
            '<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:100" onclick="window.popupCodeUtils.closeDaumPostcode()" alt="닫기 버튼">\n' +
            '</div>';
        $('body').append(layerPopup);

        window.popupCodeUtils.element_layer = document.getElementById('daumLayer');
    }
    var closeDaumPostcode   = function() {
        // iframe을 넣은 element를 안보이게 한다.
        window.popupCodeUtils.element_layer.style.display = 'none';
    }
    var daumPostcode    = function() {
        $('#daumLayer').remove();
        window.popupCodeUtils.layer();

        var objElement = arguments;
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.roadAddress; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수
                var location = data.bname1; // 읍/면/동 일 때, 읍/면 일 때에는 bname1 이고, 동일 때에는 bname 값으로 셋팅해야함
                console.log(data);
                //console.log(data.bname);
                //console.log(data.bname1);
                console.log(data);
                if(location == '')
                    location = data.bname;

                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }
                window.popupCodeUtils.element_layer.style.display = 'none';
                if(objElement.length>0){

                    try{
                        var wrap =$('#plustabs > .ui-tabs-panel').eq($('#plustabs > ul li.ui-tabs-active').index());
                        wrap.find('#addressObject').val(JSON.stringify(data));
                        $.each(objElement,function(i,vEl){
                            //console.log(vEl.attr('id'),fullAddr);
                            switch(i){
                                case 0: if(vEl) vEl.val(fullAddr);break;
                                case 1: if(vEl) vEl.val(data.jibunAddress);break;
                                case 2: if(vEl) vEl.val(data.zonecode);break;
                                case 3: if(vEl) vEl.val(data.buildingCode.substr(0,19));break;
                                case 4: if(vEl) vEl.val(data.sido);break;
                                case 5: if(vEl) vEl.val(data.sigungu);break;
                                case 6: if(vEl) vEl.val(location);break;
                            }
                        });
                    } catch(ex){
                        console.log(ex);
                    }
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                //document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
                //document.getElementById('address').value = fullAddr;
                // document.getElementById('addressEnglish').value = data.addressEnglish;

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)

            },
            width : '100%',
            height : '100%',
            maxSuggestItems : 5
        }).embed(window.popupCodeUtils.element_layer);

        // iframe을 넣은 element를 보이게 한다.
        window.popupCodeUtils.element_layer.style.display = 'block';

        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
        window.popupCodeUtils.initLayerPosition();
    }

    var initLayerPosition   = function(){
        var width = 400; //우편번호서비스가 들어갈 element의 width
        var height = 500; //우편번호서비스가 들어갈 element의 height
        var borderWidth = 5; //샘플에서 사용하는 border의 두께

        // 위에서 선언한 값들을 실제 element에 넣는다.
        window.popupCodeUtils.element_layer.style.width = width + 'px';
        window.popupCodeUtils.element_layer.style.height = height + 'px';
        window.popupCodeUtils.element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        window.popupCodeUtils.element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
        window.popupCodeUtils.element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
    };

    return {
        layer: layer,
        element_layer: element_layer,
        closeDaumPostcode: closeDaumPostcode,
        daumPostcode: daumPostcode,
        initLayerPosition: initLayerPosition
    }

})(window.popupCodeUtils || {}, jQuery);
/**
 * 파일관련 유틸
 * @type {{download}}
 */
window.FileUtils = (function(FileUtils, $, undefined) {
    /**
     * file_download
     * @param old_file  : 실제 파일명
     * @param new_file  : 저장 파일명
     * @param fold_name : 폴더명
     * 폼을 생성하여 실제 파일명, 저장 파일명, 폴더명을 인풋생성후 폼에 담아 전송한다
     *
     */
    var download = function(oldFile, newFile, foldName) {
        var url         = '/File/fileDownload';

        //dataForm ID 고정일 경우 같은 다운로드면 됨
        var formId      = Math.floor(Math.random() * 1000);

        var _formObj    = $('<form></form>').attr({'name':'dataForm' + formId, 'id':'dataForm' + formId, 'method':'POST', 'action':url});
        _formObj.append($("<input/>").attr({'type':'hidden', 'name':'type'      , 'value':'fileDownload'}));
        _formObj.append($("<input/>").attr({'type':'hidden', 'name':'oldFile'   , 'value':oldFile}));
        _formObj.append($("<input/>").attr({'type':'hidden', 'name':'newFile'   , 'value':newFile}));
        _formObj.append($("<input/>").attr({'type':'hidden', 'name':'foldName'  , 'value':foldName}));
        $('body').append(_formObj);
        $("#dataForm" + formId).submit();
    };
    return {
        download: download
    }
})(window.FileUtils || {}, jQuery);

window.CommonUtils = (function(CommonUtils, $, undefined) {

    var isArray = function(array) {
        if (Object.prototype.toString.call(array) === '[object Array]') {
            return true;
        } else if (typeof array.length === 'number') {
            return true;
        }
        return false;
    };

    var moneyUnitFloor = function(value, unit) {
        unit = unit * 10;
        if (value > unit) {
            value = Math.floor(value / unit) * unit;
        }
        return value;
    };

    var moneyUnitCheck = function(value, unit) {
        unit = unit * 10;
        return value % unit == 0 ? false : true;
    };

    var htmlTagRemove = function(str) {
        return str.replace(/(<([^>]+)>)/ig, '');
    };

    var checkLimitLength = function(str, limitLength) {
        return isNotEmpty(str) ? str.length > limitLength : true;
    };

    var isUndef = function(obj) {
        return (typeof obj == 'undefined');
    };

    var initNumber = function(obj) {
        obj = Number(obj);
        if (isNaN(obj)) {
            obj = 0;
        }
        return obj;
    };

    var existArray = function(array, obj) {
        return $.inArray(obj, array) != -1;
    };

    var removeArray = function(array, obj) {
        var idx = $.inArray(obj, array);
        if (idx != -1) {
            array.splice(idx, 1);
            removeArray(array, obj);
        }
        return false;
    };

    var getCookieList = function(cookieName) {
        var cookieList = $.cookie(cookieName);
        if (isUndef(cookieList) || 'null' == cookieList || '' == cookieList) {
            cookieList = [];
        } else {
            cookieList = cookieList.split(',');
        }
        return cookieList;
    };

    var getSessionList = function(sessionKey) {
        var sessionList = $.session.get(sessionKey);
        if (isUndef(sessionList) || 'null' == sessionList || '' == sessionList) {
            sessionList = [];
        } else {
            sessionList = sessionList.split(',');
        }
        return sessionList;
    };

    var isEmpty = function(str) {
        return (typeof str == 'undefined' || str == null || $.trim(str) == '');
    };
    var isNotEmpty = function(str) {
        return !(typeof str == 'undefined' || str == null || $.trim(str) == '');
    };

    var checkNull = function(message) {
        var newMessage;

        if (message == null || message == undefined || message == 'null'
            || message == 'undefined') {
            newMessage = '';
        } else {
            newMessage = message;
        }

        return newMessage;
    };


    var printWon = function(numeric) {
        var textWon = '0원';
        if ($.isNumeric(numeric)) {
            textWon = numeral(numeric).format('0,0') + '원'
        }
        return textWon;
    };


    /**
     * IE 판단 및 버젼 조회
     *
     * @returns Number IE 버젼 아니면 0
     */
    var getVerIE = function() {
        var ua = window.navigator.userAgent;
        var version = 0;
        var msie = ua.indexOf('MSIE ');
        var trident = ua.indexOf('Trident/');
        var edge = ua.indexOf('Edge/');

        if (msie > 0) {
            // IE 10 or older => return version number
            version = parseInt(ua.substring(msie + 5, ua.indexOf('.', msie)),
                10);
        } else if (trident > 0) {
            // IE 11 => return version number
            var rv = ua.indexOf('rv:');
            version = parseInt(ua.substring(rv + 3, ua.indexOf('.', rv)), 10);
        } else if (edge > 0) {
            // IE 12 => return version number
            version = parseInt(ua.substring(edge + 5, ua.indexOf('.', edge)),
                10);
        }
        return version;
    };

    /**
     * Browser 종류 조회
     *
     * @returns {string}
     */
    var getBrowser = function() {
        var agt = navigator.userAgent.toLowerCase();
        if (agt.indexOf("firefox") != -1)
            return 'Firefox';
        if (agt.indexOf("chrome") != -1)
            return 'Chrome';
        if (agt.indexOf("safri") != -1)
            return 'Safari';
        if (agt.indexOf("opera") != -1)
            return 'Opera';
        if (getVerIE() > 0)
            return 'IE';
        else
            return navigator.userAgent;
    };


    /** \n >> <br/> */
    var nToBr = function(value) {
        return value.replace(/[\n|\r]/g, '<br/>');
    };

    /** <br/> >> \n */
    var brToN = function(value) {
        return value.replace(/<br\s*\/?>/mg, '\n');
    };

    /**
     * 자리수 채우기
     *
     * @param n
     * @param digits
     * @returns {string}
     */
    function leadingZeros(n, digits) {
        var zero = '';
        n = n.toString();

        if (n.length < digits) {
            for (var i = 0; i < digits - n.length; i++)
                zero += '0';
        }
        return zero + n;
    }

    /**
     * return query string
     *
     * @param queryData
     * @returns {string}
     */
    var getQueryString = function(queryData) {
        var queryString = "";
        var idx = 0;
        for ( var key in queryData) {
            if (queryData.hasOwnProperty(key)) {
                var data = queryData[key];
                if (!isEmpty(data)) {
                    queryString += (idx == 0 ? '?' : '&') + key + '='
                        + queryData[key];
                    idx++;
                }
            }
        }
        return queryString;
    };

    /**
     * 문자열 바이트 수
     *
     * @param string
     * @returns {number}
     */
    var getByteLength = function(string) {
        var str = string;
        var length = 0;
        for(var i = 0; i < str.length; i++)
        {
            if(escape(str.charAt(i)).length >= 4)
                length += 2;
            else if(escape(str.charAt(i)) == "%A7")
                length += 2;
            else
            if(escape(str.charAt(i)) != "%0D")
                length++;
        }
        return length;
    };

    /**
     * 글자를 앞에서부터 원하는 바이트만큼 잘라 리턴합니다.
     * 한글의 경우 2바이트로 계산하며, 글자 중간에서 잘리지 않습니다.
     */
    var cutByte = function(str, len) {
        var count = 0;

        for(var i = 0; i < str.length; i++) {
            if(escape(str.charAt(i)).length >= 4)
                count += 2;
            else
            if(escape(str.charAt(i)) != "%0D")
                count++;


            if(count >  len) {
                if(escape(str.charAt(i)) == "%0A")
                    i--;
                break;
            }
        }
        return str.substring(0, i);
    }
    /**
     * 문자열 랜덤 생성
     *
     * @param length
     *            {number} (1~10)
     * @returns {*}
     */
    function getRandomString(length) {
        var randomString;
        if (length < 1 || length > 10) {
            // console.log('parameter index (1 ~ 10) out of range.');
            return false;
        } else {
            randomString = Math.random().toString(36).substr(2, length);
        }
        return randomString.toUpperCase();
    }

    /**
     * console.log를 지원하지 않는 브라우저에서 동작하도록해줌. alertFallback 변수로 얼럿으로 대채할것인지 설정
     * 2014/3/25 wsw
     */
    var consoleChk = function() {
        var alertFallback = false;
        if (typeof console === "undefined"
            || typeof console.log === "undefined") {
            console = {};
            if (alertFallback) {
                console.log = function(msg) {
                    alert(msg);
                };
            } else {
                console.log = function() {
                };
            }
        }
    };

    var openerCreateCookie = function(cookieName, value) {
        $.cookie(cookieName, value);
    };
    /**
     * 체크박스 전체 체크 스크립트. EX) <input type="checkbox" class="allCheck"
     * id="osimgChk"/>, <input type="checkbox" data-parent="osimgChk"/> class는
     * 'allCheck'로 통일한다. 전체 체크 대상의 data-parent 태그에 parent의 아이디를 입력한다.
     */
    var allCheckBox = function() {
        $("input[type=checkbox][class=allCheck]").click(function() {
            var parentId = $(this).attr("id"); // parent ID
            var isChecked = $(this).is(":checked"); // Check 상태
            alert(parentId);
            $("input[type=checkbox][data-parent=" + parentId + "]")
                .each(function() {
                    $(this).prop("checked", isChecked);
                });
        });
    };

    /**
     * 전화번호 형식으로 바꾸기
     * 예) 0212341234 -> 02-1234-1234
     *    03112341234 -> 031-1234-1234
     *    01012341234 -> 010-1234-1234
     * @param num
     * @returns
     */
    var phoneFormat = function() {
        $(document).on("change", ".tel", function() {
            var thisNum     = $(this).val();
            $(this).val(thisNum.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3"));
        });
    };

    /**
     * 인풋 값 양식 확인
     *
     * @param object
     * @param regex
     * @param message
     * @returns {boolean}
     */
    var matchInput = function(object, regex, message) {
        var objectVar = object.val();
        if (objectVar.match(regex)) {
            return true;
        } else {
            alert(message);
            object.focus().val('');
            return false;
        }
    };
    var inputFile   = function(_target, _text, _btnclass) {
        _target     = _target   || '.file';
        _text       = _text     || 'File';
        _btnclass   = _btnclass || 'btn-primary';

        $(_target).filestyle({
            badge: false,
            input : true,
            text : _text,
            btnClass : _btnclass,
            htmlIcon : '<span class="fa fa-file"></span> '
        });
    };
    return {
        isUndef : isUndef,
        checkLimitLength : checkLimitLength,
        initNumber : initNumber,
        existArray : existArray,
        removeArray : removeArray,
        getCookieList : getCookieList,
        getSessionList : getSessionList,
        isEmpty : isEmpty,
        isNotEmpty : isNotEmpty,
        checkNull : checkNull,
        printWon : printWon,
        getVerIE : getVerIE,
        getBrowser : getBrowser,
        nToBr : nToBr,
        brToN : brToN,
        getQueryString : getQueryString,
        getByteLength : getByteLength,
        leadingZeros : leadingZeros,
        getRandomString : getRandomString,
        htmlTagRemove : htmlTagRemove,
        moneyUnitFloor : moneyUnitFloor,
        moneyUnitCheck : moneyUnitCheck,
        isArray : isArray,
        allCheckBox : allCheckBox,
        phoneFormat : phoneFormat,
        matchInput : matchInput,
        cutByte : cutByte,
        inputFile : inputFile
    };
})(window.CommonUtils || {}, jQuery);


var CONST = (function($) {

    var Regex = {
        'text' : /^[0-9a-zA-Z\u3131-\u314e|\u314f-\u3163|\uac00-\ud7a3]*$/,
        'email' : /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/,
        'phone' : /^\d{3}-\d{3,4}-\d{4}$/,
        'enNum' : /^[0-9a-zA-Z+]*$/,
        'firEnNum' : /^[a-zA-Z][0-9a-zA-Z+]*$/,
        'firEn' : /^[a-zA-Z]/,
        'num' : /^[0-9]*$/,
        //'ip'	: /^(1|2)?\d?\d([.](1|2)?\d?\d){3}$/,
        'ip' : /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/,
        'id' : /^(?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$/,
        'password' :  /^[0-9a-zA-Z+\@\#\%\^\&]*$/,
        'password2' : /^.*(?=.*[a-zA-Z])((?=.*[0-9])|(?=.*[!@#$%^*+=-])).*$/,
        'domain' : /^(((http(s?))\:\/\/)?)([가-힣0-9a-zA-Z\-]+\.)+[a-zA-Z]{2,6}(\:[0-9]+)?(\/\S*)?$/
    };

    return {
        Regex : Regex
    }

})(jQuery);

/**
 * 숫자 타입 핸들
 */
window.NumberFormatHandler = (function(NumberFormatHandler, $, undefined) {
    //지정자리 반올림 (값, 자릿수)
    var Round = function(n, pos) {
        var digits = Math.pow(10, pos);

        var sign = 1;
        if (n < 0) {
            sign = -1;
        }

        // 음수이면 양수처리후 반올림 한 후 다시 음수처리
        n = n * sign;
        var num = Math.round(n * digits) / digits;
        num = num * sign;

        return num.toFixed(pos);
    };
    // 지정자리 버림 (값, 자릿수)
    var Floor = function(num, loc) {
        if(!loc) loc = 0;

        num		= parseFloat(num);
        var pow		= Math.pow(10, loc);

        var numPow = num / pow;

        var arrayVal	= Math.floor(numPow);

        return arrayVal * pow;
    };
    // 지정자리 올림 (값, 자릿수)
    var Ceiling = function(n, pos) {
        var digits = Math.pow(10, pos);
        var num = Math.ceil(n / digits) * digits;
        return num;
        //return num.toFixed(pos,0);
    };
    //엑셀 스타일의 반올림 함수 정의
    var RoundXL = function(n, digits) {
        if (digits >= 0) return parseFloat(n.toFixed(digits)); // 소수부 반올림

        digits = Math.pow(10, digits); // 정수부 반올림
        var t = Math.round(n * digits) / digits;

        return parseFloat(t.toFixed(0));
    };
    //숫자 콤마 찍기
    var AddCommas = function(nStr){
        nStr += '';
        var x = nStr.split('.');
        var x1 = x[0];
        var x2 = x.length > 1 ? '.' + x[1] : '';
        var rgx = /(\d+)(\d{3})/;
        while (rgx.test(x1)) {
            x1 = x1.replace(rgx, '$1' + ',' + '$2');
        }
        return x1 + x2;
    };

    return {
        Round : Round
        , Floor : Floor
        , Ceiling : Ceiling
        , RoundXL : RoundXL
        , AddCommas : AddCommas
    }
})(window.NumberFormatHandler || {}, jQuery);

window.DatePickerUtils = (function(DatePickerUtils, $, undefined) {
    "use strict";

    var toFormPicker = function() {
        $( ".from" ).datepicker({
            defaultDate: "+0w",
            changeMonth: false,
            numberOfMonths: 1,
            dateFormat : 'yy-mm-dd',
            onClose: function( selectedDate ) {
                $(this).siblings(".to" ).datepicker( "option", "minDate", selectedDate);
                $(this).siblings(".to" ).datepicker( "option", "maxDate", selectedDate.split('-')[0]+'-12-31');
            }
        });
        $( ".to" ).datepicker({
            defaultDate: "+0w",
            changeMonth: false,
            numberOfMonths: 1,
            dateFormat : 'yy-mm-dd',
            onClose: function( selectedDate ) {
                $(this).siblings(".from" ).datepicker( "option", "maxDate", selectedDate );
                $(this).siblings(".from" ).datepicker( "option", "minDate", selectedDate.split('-')[0]+'-01-01' );
            }
        });
    };
    var datePicker = function() {
        /* DatePicker 설정*/
        $(".datepicker").datepicker ({
            dateFormat: "yy-mm-dd",
            showOn : "both",
            showAnim: "slide",
            buttonImage : "/resources/img/icon-calendar.gif",
            showMonthAfterYear: true, // 월, 년순의 셀렉트 박스를 년,월 순으로 바꿔준다.
            monthNames: ['1월(January)','2월(February)','3월(March)','4월(April)','5월(May)','6월(June)',
                '7월(July)','8월(August)','9월(September)','10월(October)','11월(November)','12월(December)'],
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            prevText: '이전 달',
            nextText: '다음 달'
        });
    };
    var monthPicker = function() {
        var d = new Date();
        var options = {
            pattern: 'yyyymm', // Default is 'mm/yyyy' and separator char is not mandatory
            selectedYear: d.getFullYear(),
            startYear: d.getFullYear() - 5,
            finalYear: d.getFullYear() + 5,
            monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
        };
        $('.monthPicker').monthpicker(options);
    };
    var dateTimePicker = function() {
        $('.datetimepicker').datetimepicker({
            format:'Y-m-d H:i',
            defaultTime:'10:00',
            step : 10,
            timepickerScrollbar:false
        });
    };
    var datePickerSet = function() {
        $(".setDate").click(function() {
            var type = $(this).data("type");
            var d 				= new Date();
            var beforeYear 	= d.getFullYear() - 1;
            var affterYear 	= d.getFullYear();
            var beforeMonth  = CommonUtils.leadingZeros(d.getMonth(), 2);
            var afterMonth  	= CommonUtils.leadingZeros(d.getMonth()+1, 2);
            var date    			= CommonUtils.leadingZeros(d.getDate(), 2);
            var beforeDate 	= '';
            var afterDate 		= '';
            if(type == 'month') {
                beforeDate 		= affterYear + "-" + beforeMonth + "-" + date;
                afterDate 		= affterYear + "-" + afterMonth + "-" + date;
            } else {
                beforeDate 		= beforeYear + "-" + afterMonth + "-" + date;
                afterDate 		= affterYear + "-" + afterMonth + "-" + date;
            }
            $(this).parent().find('.from').val(beforeDate);
            $(this).parent().find('.to').val(afterDate);
        });
    };
    return {
        toFormPicker : toFormPicker
        , datePicker : datePicker
        , monthPicker : monthPicker
        , dateTimePicker : dateTimePicker
        , datePickerSet : datePickerSet
    };
})(window.DatePickerUtils || {}, jQuery);

var LayerPopupUtils = (function($) {

    /**
     * @param type
     *            {'create', 'reload'}
     * @param popupWidth
     *            (팝업높이)
     * @param popupHeight
     *            (팝업너비)
     * @param message
     *            (팝업내용-html)
     */
    var createPopup = function(type, popupWidth, popupHeight, message) {
        var width, height, top, left = '';
        var winWidth = $(window).width();
        var winHeight = $(window).height();
        var marginTop = -(popupHeight / 2);
        var marginLeft = -(popupWidth / 2);

        if (winWidth <= popupWidth) {
            width = '100%';
            top = '0';
            left = '0';
            marginTop = '0';
            marginLeft = '0';

            $('div.wrap').hide();
        } else {
            width = popupWidth;
            height = popupHeight;
            top = $(window).scrollTop() + (winHeight / 2);
            // top = '50%';
            left = '50%';

            $('div.wrap').show();
        }

        if (winHeight <= popupHeight) {
            marginTop = 20;
            top = $(window).scrollTop();
            // marginTop = marginTop/10;
            // top = '20px';
        }

        var margin = marginTop + 'px 0 0 ' + marginLeft + 'px';
        if (type == 'create') {
            $.blockUI({
                message : message,
                css : {
                    width : width,
                    height : height,
                    top : top,
                    left : left,
                    margin : margin,
                    // position : 'fixed'
                    position : 'absolute',
                    close : true,
                    overflow : 'auto',
                    padding : '0 20px 20px 20px'
                    // ,backgroundColor:false TODO-lms0123: 레이어 팝업 드래그???
                }
            });
        } else {
            $('.blockUI.blockMsg').eq(0).css({
                'top' : top,
                'left' : left,
                'margin' : margin,
                'width' : width,
                'height' : height,
                'overflow' : 'auto',
                'padding' : '0 20px 20px 20px'
            });
        }
        //$('.blockMsg').draggable();
        closeHandler();
    };

    var closeHandler = function() {
        $('.pop-header .icon-close, .btnClosePopup').on('click', function() {
            var that = $(this);
            var menu = that.data('menu');
            close();
            if (menu == 'message') {
                location.reload();
            }
            return false;
        });
    };

    var close = function() {
        $.unblockUI();
        $('div.wrap').show();
    };

    var closeMessage = function(message) {
        alert(message);
        close();
    };

    var callPopup = function (width, height, url, dataParam, type) {
        BlockUtils.isBlock = false;
        $.ajax({
            url: '/popup/popupTemp',
            type: 'post',
            dataType: 'html',
            data: dataParam,
            success: function (resultHtml) {
                if(type == 'message') {
                    resultHtml = $(resultHtml).clone().wrapAll("<div/>").parent().find(".popupContents").html(dataParam).end();
                    createPopup('create', width, height, resultHtml);
                } else {
                    setPopupContents(width, height, url, dataParam, resultHtml, type);
                }



                $('.blockUI').draggable();  //레이어 팝업 드래그
            },
            error: AjaxHandler.error
        });
    };

    var setPopupContents = function(width, height, url, dataParam, tempHtml, type) {
        if(!tempHtml) tempHtml = '<div class="closeBtn" style="float:right; padding:5px"><i class="fa fa-window-close" aria-hidden="true" onclick="javascript:LayerPopupUtils.close()"></i></div><div style="clear:both"></div><div id="popupWrap"><div class="popupContents"></div></div>';
        $.ajax({
            url :  url,
            type : 'post',
            dataType : 'html',
            data : dataParam,
            success : function(resultHtml) {
                if(type == 'db') {
                    if(resultHtml) {
                        resultHtml = $.parseJSON(resultHtml);

                        $.each(resultHtml, function(index, value)
                        {
                            width = resultHtml[index].ipmWidth;
                            height = resultHtml[index].ipmHeight;
                            var content = resultHtml[index].ipmContents;
                            var contents = $(tempHtml).clone().wrapAll("<div/>").parent().find(".popupContents").html(content).end();
                            createPopup('create', width, height, contents);
                        });
                    }
                } else {
                    resultHtml = $(tempHtml).clone().wrapAll("<div/>").parent().find(".popupContents").html(resultHtml).end();
                    createPopup('create', width, height, resultHtml);
                }
                $('.blockUI').draggable();  //레이어 팝업 드래그
            },
            error : AjaxHandler.error
        });
    }

    return {
        create : createPopup,
        fast : setPopupContents,
        close : close,
        closeMessage : closeMessage,
        call : callPopup
    }
})(jQuery);


/**
 * 팝업
 * @param $width
 * @param $height
 * @param $url
 * @param $param - Object
 * ex) text 팝업   - openPopup(width, height, url, msg, 팝업타입('message'))
 * ex) Layer 팝업 - openPopup(width, height, url, param(key=value&key=value))
 */
function openPopup($width, $height, $url, $param, $type){
    var popupUrl = $url;
    var popupWidth = $width;
    var popupHeight = $height;
    var param = $param;
    var type = $type;
    LayerPopupUtils.call(popupWidth, popupHeight, popupUrl, param, type);
}

/**
 * 팝업
 * @param $width
 * @param $height
 * @param $url
 * @param $param - Object
 * ex) text 팝업   - openPopup(width, height, url, msg, 팝업타입('message'))
 * ex) Layer 팝업 - openPopup(width, height, url, param(key=value&key=value))
 */
function fastPopup($width, $height, $url, $param, $type){
    var popupUrl = $url;
    var popupWidth = $width;
    var popupHeight = $height;
    var param = $param;
    var type = $type;
    LayerPopupUtils.fast(popupWidth, popupHeight, popupUrl, param, type);
}
/**
 * 테이블 동일 값 열/행 병합 처리를 위한 함수
 *
 * 같은 값이 있는 열을 병합함
 *
 * 사용법 : $('#테이블 ID').rowspan(0);
 *
 */
$.fn.rowspan = function(colIdx, isStats) {
    return this.each(function(){
        var that;
        var rowspan;
        $('tr', this).each(function(row) {
            $('td:eq('+colIdx+')', this).each(function(col) {
                if ($(this).html() == $(that).html()
                    && (!isStats
                        || isStats && $(this).prev().html() == $(that).prev().html()
                    )
                ) {
                    rowspan = $(that).attr("rowspan") || 1;
                    rowspan = Number(rowspan)+1;

                    $(that).attr("rowspan",rowspan);

                    // do your action for the colspan cell here
                    $(this).hide();

                    //$(this).remove();
                    // do your action for the old cell here

                } else {
                    that = this;
                }

                // set the that if not already set
                that = (that == null) ? this : that;
            });
        });
    });
};
/**
 * 같은 값이 있는 행을 병합함
 *
 * 사용법 : $('#테이블 ID').colspan (0);
 *
 */
$.fn.colspan = function(rowIdx) {
    return this.each(function(){
        $('tr', this).filter(":eq("+rowIdx+")").each(function(row) {
            $(this).find('th').filter(':visible').each(function(col) {
                if ($(this).html() == $(that).html()) {
                    var colspan = '';
                    colspan = $(that).attr("colSpan") || 1;
                    colspan = Number(colspan)+1;

                    $(that).attr("colSpan",colspan);
                    $(this).hide(); // .remove();
                } else {
                    that = this;
                }

                // set the that if not already set
                that = (that == null) ? this : that;

            });
        });
    });
}
function rn2br(str) {
    return str.replace(/\r\n/g, "<br />");
}
// 쿠키 생성
function setCookie(cName, cValue, cDay){
    var expire = new Date();
    expire.setDate(expire.getDate() + cDay);
    var cookies = cName + '=' + escape(cValue) + '; path=/ ';
    if(typeof cDay != 'undefined') cookies += ';expires=' + expire.toGMTString() + ';';
    document.cookie = cookies;
}

// 쿠키 가져오기
function getCookie(cName) {
    cName = cName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cName);
    var cValue = '';
    if(start != -1){
        start += cName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cValue = cookieData.substring(start, end);
    }
    return unescape(cValue);
}

//오늘하루 그만보기
function closePopupNotToday(id){
    setCookie(id,'Y', 1);
    $("#"+id).hide('fade');
}

