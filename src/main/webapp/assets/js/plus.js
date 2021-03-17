//https://sweetalert2.github.io/
var oEditors = [];


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
var xss = {
    encodeXss : function(xss){
        if(xss){
            if(typeof(xss)=='string') {
                xss = xss.replaceAll("'", "&#39;");
                xss = xss.replaceAll("'","&#39;");
                xss = xss.replaceAll("<","&lt;");
                xss = xss.replaceAll(">","&gt;");
                xss = xss.replaceAll("&","&amp;");
            }
        }
        return xss;
    },
    decodeXss : function(xss){
        if(xss){
            if(typeof(xss)=='string'){
                xss = xss.replaceAll("&#39;","'");
                xss = xss.replaceAll("&lt;","<");
                xss = xss.replaceAll("&gt;",">");
                xss = xss.replaceAll("&amp;","&");
            }
        }
        return xss;
    }
}

/**
 * 한글초성 분석
 * placeholder 생성할때 사용
 * ex) 메뉴경로 마지막글자 로에 받침이 있는지 찾는용도
 * @returns {Array}
 */
String.prototype.toKorChars = function() {
    var cCho = ['ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ']
        ,
        cJung = ['ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ']
        ,
        cJong = ['', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ']
        , cho, jung, jong;
    var str = this, cnt = str.length, chars = [], cCode;
    for (var i = 0; i < cnt; i++) {
        cCode = str.charCodeAt(i);
        if (cCode == 32) {
            continue;
        }
        // 한글이 아닌 경우
        if (cCode < 0xAC00 || cCode > 0xD7A3) {
            chars.push(str.charAt(i));
            continue;
        }
        cCode = str.charCodeAt(i) - 0xAC00;
        jong = cCode % 28; // 종성
        jung = ((cCode - jong) / 28) % 21 // 중성
        cho = (((cCode - jong) / 28) - jung) / 21 // 초성
        chars.push(cCho[cho], cJung[jung]);
        if (cJong[jong] !== '') {
            chars.push(cJong[jong]);
        }
    }
    return chars;
}

$.extend({
    /**
     * ajax지만 async: 동기방식으로
     * $.post와 성격은 비슷하지만 동기방식
     * @param url
     * @param data
     * @param callback
     * @param isAsync
     */
    call: function (url, data, callback, jsonExtend) {
        var isAsync = false;
        if(!jsonExtend) jsonExtend = {error:function(r){
              alert('시스템에 문제가 생겼습니다.\n다시 시도하여 주시길 바랍니다.');
        }};
        $.ajax($.extend({
            url: url,
            type: 'post',
            data: data,
            dataType:'json',
            async: isAsync,
            success: callback
        },jsonExtend));

    },
    callJS: function (url, data, callback, isAsync) {
        isAsync = false;
        $.ajax({
            url: url,
            type: 'post',
            data: data,
            async: isAsync,
            success: callback
        })
    }

});

$.fn.extend({
    /**
     * Validation rules에필수값있는경우
     * @param addClass
     * @returns {jQuery|HTMLElement}
     */
    required:function(addClass){
        if(!addClass){
            addClass ='';
        }
        try{
            if($(this).first().get(0)=='TH'){
                $(this).first().append('<span class="colorBox colorrequired '+addClass+'">*</span>')
            }
            if($(this).get(0).nodeName=='TH'){
                $(this).first().append('<span class="colorBox colorrequired '+addClass+'">*</span>')
            }
            if($(this).get(0).nodeName=='INPUT' || $(this).get(0).nodeName=='SELECT' ){
                $(this).parent().prev().append('<span class="colorBox colorrequired '+addClass+'">*</span>')
            }

        } catch (ex) {

        }
        return $(this);


    },
    /**
     * colspan 먹임 마지막td에
     * @param num
     * @returns {jQuery|HTMLElement}
     */
    colspan:function(num){
      $(this).last().attr('colspan',num);
      return $(this);

    },
    /**
     * 아이콘 추가
     * @param fa
     * @returns {jQuery|HTMLElement}
     */
    addIcon:function(fa){
      $(this).prepend($('<i></i>').attr('class','fa fa-'+fa));
      return $(this);
    },
    /**
     * 그리드를 초기화한다
     */
    resetGrid:function(){
        if($(this).length){
            try {
                $(this).dataTable().fnDestroy();
            }catch(e){console.log(e);}
            $(this).find('thead').remove();
            $(this).find('tbody').remove();
        }
    },

    /**
     * 폼필드에 값을 세팅한다.
     * @param v
     */

    setValue:function(val){
        //console.log("value",val);
        var element = $(this);

        if(element.length>0){

            if(element.is('.html-editor')){

                // var editorId = $('.html-editor').attr('id');
                // if(!$('.html-editor').is('.editoron')){

                    // $('.html-editor').summernote({
                    //   height:400,
                    //     code:'',
                    //   width:'100%',
                    //   toolbar: [
                    //     // [groupName, [list of button]]
                    //     ['style', ['bold', 'italic', 'underline', 'clear']],
                    //     // ['font', ['strikethrough', 'superscript', 'subscript']],
                    //     ['fontsize', ['fontsize']],
                    //     ['color', ['color']],
                    //     ['para', ['ul', 'ol', 'paragraph']],
                    //
                    //     ['height', ['height']]
                    //   ]
                    // });
                    $(element).each(function(){
                        var editorId = $(this).attr('id');
                        if(!$(this).is('.editoron')){
                            nhn.husky.EZCreator.createInIFrame({
                            oAppRef : oEditors,
                               width:'100%',
                               height:'300px',
                            elPlaceHolder : editorId,
                            sSkinURI : "/assets/neditor/SmartEditor2Skin.html",
                            //sSkinURI : "/main/w_mode/SmartEditor2Skin.html",
                            htParams : {
                                bUseToolbar : true,
                                fOnBeforeUnload : function() {

                                }
                            }, //boolean
                            fOnAppLoad : function() {
                                oEditors.getById[editorId].exec("SET_IR", [val])
                            },
                            fCreator : "editor"
                        });
                        $(this).addClass('editoron');
                        } else {
                            oEditors.getById[editorId].exec("SET_IR", [val]);
                        }

                    })


                // }
            } else {


                element.val(xss.decodeXss(val));
            }
            if(element.prop('nodeName')=='SELECT'){
                element.change();
            }
        } else {
            // if(typeof(val)=='object' && val !=null){
            //     var tabContent  = null;
            //     if($('.nav-tabs .active').length>0){
            //         tabContent = $('.nav-tabs .active').attr('href');
            //     } else {
            //         tabContent = $('#tabs .ui-tabs-active a').attr('href');
            //     }
            //     if($(tabContent) !=null)
            //     $.each(val,function(fieldKey,value){
            //         console.log(fieldKey,$(tabContent).find('#'+fieldKey))
            //         $(tabContent).find('#'+fieldKey).val(value);
            //     });;
            //
            // }
        }

    },

    setHtml : function(val){
        var element = $(this);
        //console.log(val,element.attr('id'),element.prop('nodeName'),element.is('.inp'),element.is(':hidden'));
        if(element.is(':hidden') && !element.is('.inp')) {
            element.val(val);
        } else if(element.is('.inp')){
            var iname = element.attr('name');

            if(element.prop('nodeName')=='SELECT'){
                element.val(val);
                var parentElement =element.parent();
                element.parent().text(element.find(':selected').text());
                parentElement.append(plus.makeInput('hidden',iname,{value:val}))
            } else if(element.is(':hidden')) {
                element.val(val);
            } else {
                var parentElement = element.parent();
                parentElement.html(val);
                parentElement.append(plus.makeInput('hidden',iname,{id:iname,value:val}))
            }

        } else {
            if(typeof(val)=='object' && val !=null){

                var tabContent  = null;
                if($('.nav-tabs .active').length>0){
                    tabContent = $('.nav-tabs .active').attr('href');
                } else {
                    tabContent = $('#tabs .ui-tabs-active a').attr('href');
                }
                if($(tabContent) !=null)
                $.each(val,function(fieldKey,value){
                    console.log(fieldKey,$(tabContent).find('#'+fieldKey))
                    $(tabContent).find('#'+fieldKey).setHtml(value);
                });;

            }
        }

        return $(this);
    },

    /**
     * 타입이 selectBox,radio,checkbox등에 사용한다
     * @param jArray
     * @param empty
     * @param desc
     * @returns {jQuery|HTMLElement}
     */
    addItem:function(jArray,empty,desc){
        var label = null;
        if($(this).prop('nodeName')=='TH') {
            label = $(this).find('label');
        }else if($(this).prop('nodeName')=='TD'){
            label = $(this).prev().find('label');
        } else {
            label =$(this).parent().prev().find('label');
        }
        var keysSorted = plus.jsonSort(jArray,desc);
        if(label.attr('type')=='select'){

            var target =$(this).prop('nodeName')=='SELECT'?$(this):$(this).find('select');
            target.empty();
            if(empty){
                target.append(plus.makeElement('option','선택하세요',{value:''}));
            }

            $.each(keysSorted,function(kk,vv){
                if(jArray[vv])
                target.append(plus.makeElement('option',jArray[vv],{value:(vv)}));
            });
        }  else if(label.attr('type')=='checkbox'||label.attr('type')=='radio'){
            var target =label.parent().next();
            target.empty();;
            if(jArray){
                $.each(keysSorted,function(kk,vv){
                    var isbool = false;
                    var id3 = plus.getId(parseInt(Math.random()*881),label.attr('for'));
                    target.append(plus.makeInput(label.attr('type'),[label.attr('for'),'[',vv,']'].join(''),{'class':label.attr('for'),'value':1,'id':id3,'checked':isbool}));
                    target.append(plus.makeElement('label',jArray[vv],{'for':id3,'class':'inp_func'}));
                });
            }

        }

        return $(this);
    },
    choiceItem:function(jArray,empty,desc){
        var target = $(this);
        target.empty();
        var keysSorted = plus.jsonSort(jArray,desc);
        $.each(keysSorted,function(kk,vv){
            plus.makeElement('li',plus.makeElement('button',jArray[vv],{data:vv}),{data:vv}).appendTo(target);
        });
        //qSelect();
        $('.q_choice li button').click(function() {
            $(this).parent('li').addClass('on').siblings().removeClass('on');
            $('.next').removeClass('disabled');
        });

    },
    addCodeItem:function(jArray,empty,desc){
        var target = $(this);

        var keysSorted = plus.jsonSort(jArray,desc);
        if(target.attr('type')=='select'){

            var target2 =$(this).prop('nodeName')=='SELECT'?$(this):$(this).find('select');
            console.log(target2.attr('data'));
            if(target2.length==0){
                target2  = plus.makeElement('select','',{'name':target.attr('data'),'id':target.attr('data')}).appendTo(target);
            }
            target2.empty();
            if(empty){
                target2.append(plus.makeElement('option','선택하세요',{value:''}));
            }

            $.each(keysSorted,function(kk,vv){
                if(jArray[vv])
                target2.append(plus.makeElement('option',jArray[vv],{value:(vv)}));
            });
        }  else if(target.attr('type')=='checkbox'||target.attr('type')=='radio'){
            target.empty();;
            if(jArray){

                $.each(keysSorted,function(kk,vv){

                    if(typeof(vv)=='string'){
                        var isbool = false;
                        var div  = plus.makeElement('div','',{'class':'ipt_radio_div'}).appendTo(target);
                        var id3 = plus.getId(parseInt(Math.random()*881),target.attr('data'));
                        div.append(plus.makeInput(target.attr('type'),target.attr('data'),{'class':'custom-control-input '+target.attr('data'),'value':vv,'id':id3,'checked':isbool}));
                        div.append(plus.makeElement('label',jArray[vv],{'for':id3,'class':'custom-control-label'}));
                    }
                });
            }

        }

        return $(this);
    },
    /**
     * 여러를 추가할때
     * td.adds([엘리먼트1,엘리먼트2]) 등 응용
     * @returns {jQuery|HTMLElement}
     */
    adds: function () {
        var obj = $('<div></div>');
        if (arguments.length > 0) {
            $.each(arguments, function (i, v) {
                obj.append(v);
            });
        }
        $(this).append($(obj.html()));
        return $(this);
    },
    /**
     * 폼하위 엘레먼트를 Json으로 리턴
     * @returns {*}
     */
    formJson: function () {
        var obj = null;
        try {
            if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
                var arr = this.serializeArray();
                if (arr) {
                    obj = {};
                    jQuery.each(arr, function () {
                        obj[this.name] = this.value;
                    });
                }//if ( arr ) {
            }
        } catch (e) {
            alert(e.message);
        } finally {
        }

        return obj;
    },
    /**
     * 해당돔 하위 인풋들을 Json으로 리턴
     */
    domJson: function () {
        var el = $(this).find('input,textarea,select');
        var obj = {};
        $.each(el, function (i) {
            if($(this).attr('type')=='checkbox' && !$(this).is(':checked')) return true;
            if($(this).attr('type')=='radio' && !$(this).is(':checked')) return true;
            var objName = $(this).attr('name');
            if (objName) {
                obj[objName] = $(this).val();
                if ($(this).get(0).nodeName == 'SELECT' && (obj[objName] == '' || obj[objName] == null)) {
                    obj[objName] = $(this).find(':selected').attr('value');
                }
            }

        });
        return obj;
    }
})
    var plus = {
        lpad:function (str, padLen, padStr) {
                if (padStr.length > padLen) {
                    console.log("오류 : 채우고자 하는 문자열이 요청 길이보다 큽니다");
                    return str;
                }
                str += ""; // 문자로
                padStr += ""; // 문자로
                while (str.length < padLen)
                    str = padStr + str;
                str = str.length >= padLen ? str.substring(0, padLen) : str;
                return str;
        },
        strip_tags: function  (input, allowed) {
            // making sure the allowed arg is a string containing only tags in lowercase (<a><b><c>)
            allowed = (((allowed || "") + "").toLowerCase().match(/<[a-z][a-z0-9]*>/g) || []).join('');
            var tags = /<\/?([a-z][a-z0-9]*)\b[^>]*>/gi ,
                commentsAndPhpTags = /<!--[\s\S]*?-->|<\?(?:php)?[\s\S]*?\?>/gi;
            return input.replace(commentsAndPhpTags, '')
                        .replace(tags, function ($0, $1) {return allowed.indexOf('<' + $1.toLowerCase() + '>') > -1 ? $0 : '';});
        },
        formSubmit:function (action, paramMap, option) {
            var $form = $('<form>').attr($.extend({
                action : action,
                enctype : 'multipart/form-data',
                method : 'post'
            }, option));

            for ( var key in paramMap) {
                try {
                    $.each($.makeArray(paramMap[key]), function(index, element) {
                        $form.append($('<input>').attr({
                            name : key,
                            type : 'hidden'
                        }).val(typeof element === 'object' ? JSON.stringify(element) : element));
                    });
                } catch (e) {
                    console.log(e);
                }
            }

            $form.appendTo(document.body).submit().remove();
        },
        setEditorValue:function(val){

            if(oEditors.length>0){
                var editorId = $('.html-editor').attr('id');
                console.log(editorId,"plus.setEditorValue(val)","run");
                if(oEditors!=null){
                    try {
                        oEditors.getById[editorId].setIR(val);
                    } catch(ex){
                        plus.setEditorValue(val);
                    }

                }
            } else {
                setTimeout(function(){
                    console.log("plus.setEditorValue(val)");
                    plus.setEditorValue(val);
                },200);
            }
        },
        /**
         * jqueryValidation 사용지 룰선언하도록
         */
        rules:{},
        /**
         * 코드 extend 사용 구조체
         * 호출 plus.codes.코드명
         */
        codes:{
            use:{y:'사용',n:'미사용'},
            del:{y:'삭제',n:'미삭제'}
        },
        address:{
            element:null,
            close:function(){
                plus.address.element.hide()
            },
            setPosition:function(){
                var element_layer = plus.address.element;
                var width = 300; //우편번호서비스가 들어갈 element의 width
                var height = 460; //우편번호서비스가 들어갈 element의 height
                var borderWidth = 5; //샘플에서 사용하는 border의 두께

                // 위에서 선언한 값들을 실제 element에 넣는다.
                element_layer.style.width = width + 'px';
                element_layer.style.height = height + 'px';
                element_layer.style.border = borderWidth + 'px solid';
                // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
                element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
                element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
            },
            open:function(){
                if(plus.address.element==null){
                    plus.address.element =document.getElementById('postcodeLayer');
                }
                new daum.Postcode({
                    oncomplete: function(data) {
                        // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                        // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                        // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                        var fullAddr = data.address; // 최종 주소 변수
                        var extraAddr = ''; // 조합형 주소 변수

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
                        // 우편번호와 주소 정보를 해당 필드에 넣는다.
                        var wrap = plus.tab.getActiveTab();
                        console.log(data);
                        wrap.find('#aiPostcode').val(data.postcode||data.zonecode);
                        wrap.find('#addrObject').val(JSON.stringify(data));
                        wrap.find('#aiRoadaddress').val(data.roadAddress);

                        // iframe을 넣은 element를 안보이게 한다.
                        plus.address.element.style.display = 'none';
                    },
                    width : '100%',
                    height : '100%'
                }).embed(plus.address.element);

                // iframe을 넣은 element를 보이게 한다.
                plus.address.element.style.display = 'block';
                plus.address.setPosition();
            }
        },
        /**
         * 함수를 개별로 생성하지말고
         * event객체를 선언하여 생성
         */
        event:{

            checkAll:function(rowData,tableElement,mode){
                var res = '<div class="custom-control custom-checkbox">'+
                    '<input type="checkbox" id="jb-checkbox" class="custom-control-input">'+
                    '<label class="custom-control-label" for="jb-checkbox"></label>'+
                  '</div>';
                return res;
            },
            addAddress:function(rowData,tableElement,mode){
                if(rowData==null){
                    rowData = {};
                    rowData['addressInfo'] = {aiSeq:'0',aiPostcode:'',aiRoadaddress:'',aiAddressdetail:''};
                }
                if(rowData['addressInfo'] ==null){
                    rowData['addressInfo'] = {aiSeq:'0',aiPostcode:'',aiRoadaddress:'',aiAddressdetail:''};
                }
                if(rowData['addressInfo']!=null){
                    $('#tplAddress').tmpl($.extend(rowData,{mode:mode})).appendTo(tableElement);
                }
            },
            sitefile:function(rowData){
                $(':file').each(function(k,v){
                  $(this).removeData();
                  var fileWrap =$(this).closest('td');
                  fileWrap.find('.file-bg').unbind('click').addClass('hidden');
                  fileWrap.find('.fa-times').unbind('click').addClass('hidden');
                  fileWrap.find('.upload-name').val('파일 선택').prop('readonly',true);
                });

                if(rowData['filemap']){
                  $.each(rowData['filemap'],function(k,v){
                    console.log(k,v);
                    var fileTarget =$(':file[name='+k+']');
                    fileTarget.data(v);
                    var fileWrap =fileTarget.closest('td');
                    fileWrap.find('.file-upload').unbind('click').click(function(){
                      if(!fileWrap.find('.fa-times2').is('.hidden')){
                          //alert('파일 삭제후 이용해주세요');
                          Swal.fire({
                            icon: 'error',
                            title: '파일 삭제후 이용해주세요',
                          });
                          return false;
                      }
                    });
                    fileWrap.find('.file-img2 img').attr('src','/common/siteImgView?safSeq='+v['safSeq']);
                    fileWrap.find('.upload-name').val(v['safOrFile']);
                    fileWrap.find('.file-bg').removeClass('hidden');
                    fileWrap.find('.fa-times').removeClass('hidden');
                    //fileWrap.find('.file-upload').addClass('hidden');
                    fileWrap.find('.file-bg').unbind('click').click(function(){
                      window.open('/common/siteFileDown?safCode='+v['safCode']+'&safSeq='+v['safSeq'])
                    });
                    fileWrap.find('.fa-times').unbind('click').click(function(){
                      fileWrap.find('.file-img2 img').attr('src','');
                      fileTarget.data({'delSafSeq':fileTarget.data('safSeq')});
                      fileWrap.find('.file-bg').addClass('hidden');
                      fileWrap.find('.fa-times').addClass('hidden');
                      fileWrap.find('.upload-name').val('파일 선택');
                    })
                  })
                }
            },
            bbsfile2:function(rowData){
                $(':file').each(function(k,v){
                  $(this).removeData();
                  var fileWrap =$(this).closest('td');
                  fileWrap.find('.file-bg').unbind('click').addClass('hidden');
                  fileWrap.find('.fa-times').unbind('click').addClass('hidden');
                  fileWrap.find('.upload-name').val('파일 선택').prop('readonly',true);
                });
                if(rowData['filemap']){
                  $.each(rowData['filemap'],function(k,v){
                    console.log(k,v);
                    var fileTarget =$(':file[name='+k+']');
                    fileTarget.data(v);
                    var fileWrap =fileTarget.closest('td');
                    fileWrap.find('.file-upload').unbind('click').click(function(){
                      var uploadName = fileWrap.find('.upload-name').val();
                      if(!fileWrap.find('.fa-times2').is('.hidden')){
                          //alert('파일 삭제후 이용해주세요');
                          Swal.fire({
                            icon: 'error',
                            title: '파일 삭제후 이용해주세요',
                          });
                          return false;
                      }
                    });
                    fileWrap.find('.file-img2 img').attr('src','/common/imgView?bafSeq='+v['bafSeq']);
                    fileWrap.find('.upload-name').val(v['bafOrFile']);
                    fileWrap.find('.file-bg').removeClass('hidden');
                    fileWrap.find('.fa-times').removeClass('hidden');
                    //fileWrap.find('.file-upload').addClass('hidden');
                    fileWrap.find('.file-bg').unbind('click').click(function(){
                      window.open('/common/fileDown?bafCode='+v['bafCode']+'&bafSeq='+v['bafSeq'])
                    });
                    if(fileWrap.find('.player').length){
                        //fileWrap.find('.player').data({'poster',''})

                        fileWrap.find('.player').attr('src','/common/playerView?bafSeq='+v['bafSeq']);
                        var playerId = fileWrap.find('.player').attr('id');
                        const player = new Plyr('#'+playerId);
                    }
                    fileWrap.find('.fa-times').unbind('click').click(function(){
                      fileWrap.find('.file-img2 img').attr('src','');
                      fileTarget.data({'delBafSeq':fileTarget.data('bafSeq')});
                      fileWrap.find('.file-bg').addClass('hidden');
                      fileWrap.find('.fa-times').addClass('hidden');
                      fileWrap.find('.upload-name').val('파일 선택');
                    })
                  })
                }
            },
            fileElement :function(name,v){
                var add_file_div = plus.makeElement('div','',{'class':'add_file_div'});
                var filebox = plus.makeElement('div','',{'class':'filebox'}).appendTo(add_file_div);
                var getId = plus.getId();
                var lab = plus.makeElement('label','파일찾기',{'for':getId,'class':'btn btn-st1'}).appendTo(filebox);
                var inp = plus.makeElement('input','',{'type':'text','class':'upload-name','disabled':'disabled','value':v['safOrFile']}).appendTo(filebox);
                // console.log('safOrFile',v);
                // inp.attr('value',v['safOrFile']);
                // inp.val(v['safOrFile']);
                var inp = plus.makeElement('input','',{'type':'file','class':'upload-hidden',name:name,id:getId}).appendTo(filebox);
                inp.data(v);
                inp.change(function(){
                    var fileOne = $(this).get(0).files[0];
                      if(fileOne){
                        $(this).closest('div').find('.upload-name').val(fileOne.name);
                        $(this).closest('div').find('.del_file').css('display','inline-block');
                        //$(this).closest('div').find('img').attr('src',URL.createObjectURL(fileOne));
                      }
                })
                var a = plus.makeElement('a','다운로드',{'href':'javascript:;','class':'btn down_file' + (v['safSeq']?'':' hidden')}).appendTo(add_file_div);
                a.click(function(){
                    window.open('/common/siteFileDown?safCode='+v['safCode']+'&safSeq='+v['safSeq'])
                })
                var a = plus.makeElement('a','삭제',{'href':'javascript:;','class':'btn del_file'}).appendTo(add_file_div);
                a.unbind('click').click(function(){
                    console.log($(this).closest('td').find('.add_file_div:not(.hidden)').length);
                    if(confirm('해당 파일을 삭제하시겠습니까?')){
                        if($(this).closest('td').find('.add_file_div:not(.hidden)').length==1){
                            var tabGb = $(this).closest('td').attr('data');
                            var getId = plus.getId();
                            plus.event.fileElement(tabGb+'.'+getId, {}).appendTo($(this).closest('td'));
                        }
                        var fileTarget= $(this).closest('div').find(':file');
                        if(fileTarget.data('safSeq')){
                            $(this).closest('div').addClass('hidden');
                            fileTarget.data({'delSafSeq':fileTarget.data('safSeq')});
                        }else {
                            $(this).closest('div').remove();
                        }
                    }


                });
                var a = plus.makeElement('a','추가',{'href':'javascript:;','class':'btn add_file'}).appendTo(add_file_div);
                a.unbind('click').click(function() {

                    var pElement = $(this).closest('td');

                    var getId = plus.getId();
                    var fileGb = pElement.attr('data');
                    var fileGbName = [fileGb, getId].join('.');
                    plus.event.fileElement(fileGbName, {}).appendTo(pElement);
                });
                return add_file_div;
            },
            bbsfileElement :function(name,v){

                var add_file_div = plus.makeElement('div','',{'class':'add_file_div'});
                var filebox = plus.makeElement('div','',{'class':'filebox'}).appendTo(add_file_div);
                var getId = plus.getId();
                var lab = plus.makeElement('label','파일찾기',{'for':getId,'class':'btn btn-st1'}).appendTo(filebox);
                var inp = plus.makeElement('input','',{'type':'text','class':'upload-name','disabled':'disabled','value':v['bafOrFile']}).appendTo(filebox);
                // console.log('bafOrFile',v);
                // inp.attr('value',v['bafOrFile']);
                // inp.val(v['bafOrFile']);
                var inp = plus.makeElement('input','',{'type':'file','class':'upload-hidden',name:name,id:getId}).appendTo(filebox);
                inp.data(v);
                inp.change(function(){
                    var fileOne = $(this).get(0).files[0];
                      if(fileOne){
                        $(this).closest('div').find('.upload-name').val(fileOne.name);
                        $(this).closest('div').find('.del_file').css('display','inline-block');
                        //$(this).closest('div').find('img').attr('src',URL.createObjectURL(fileOne));
                      }
                })
                var a = plus.makeElement('a','다운로드',{'href':'javascript:;','class':'btn down_file' + (v['bafSeq']?'':' hidden')}).appendTo(add_file_div);
                a.click(function(){
                    window.open('/common/fileDown?bafCode='+v['bafCode']+'&bafSeq='+v['bafSeq'])
                })
                var a = plus.makeElement('a','삭제',{'href':'javascript:;','class':'btn del_file'}).appendTo(add_file_div);
                a.unbind('click').click(function(){
                    console.log($(this).closest('td').find('.add_file_div:not(.hidden)').length);
                    if(confirm('해당 파일을 삭제하시겠습니까?')){
                        if($(this).closest('td').find('.add_file_div:not(.hidden)').length==1){
                            var tabGb = $(this).closest('td').attr('data');
                            var getId = plus.getId();
                            plus.event.fileElement(tabGb+'.'+getId, {}).appendTo($(this).closest('td'));
                        }
                        var fileTarget= $(this).closest('div').find(':file');
                        if(fileTarget.data('bafSeq')){
                            $(this).closest('div').addClass('hidden');
                            fileTarget.data({'delBafSeq':fileTarget.data('bafSeq')});
                        }else {
                            $(this).closest('div').remove();
                        }
                    }


                });
                var a = plus.makeElement('a','추가',{'href':'javascript:;','class':'btn add_file'}).appendTo(add_file_div);
                a.unbind('click').click(function() {

                    var pElement = $(this).closest('td');

                    var getId = plus.getId();
                    var fileGb = pElement.attr('data');
                    var fileGbName = [fileGb, getId].join('.');
                    plus.event.fileElement(fileGbName, {}).appendTo(pElement);
                });
                return add_file_div;
            },
            bbsfilechange:function(rowData){
                $(':file').change(function(){
                  var fileOne = $(this).get(0).files[0];
                  if(fileOne){
                    $(this).closest('div').find('.upload-name').val(fileOne.name);
                    $(this).closest('div').find('.del_file').css('display','inline-block');
                    //$(this).closest('div').find('img').attr('src',URL.createObjectURL(fileOne));
                  }
                });
            },
            bbsfile:function(rowData){

                // $('.del_file').hide();
                // $('.del_file').each(function(i){
                //     var pElement = $(this).closest('div');
                //     var fileData = pElement.find(':file').data();
                //
                //     $(this).click(function(){
                //         pElement.find(':file').removeData();
                //         $(this).hide();
                //     })
                // });

                if(rowData['filemap'])
                {
                  var fileMapCount= 0;
                  $.each(rowData['filemap'],function(k,v){
                    console.log('@@@',k,v);
                    if(k.indexOf('.')>-1){
                        return true;
                    }
                    fileMapCount++;
                    console.log('e---------------e--');
                    var fileTarget =$('.fileMap :file[name='+k+']');

                    if(fileTarget.length){
                        fileTarget.data(v);
                        var fileWrap =fileTarget.closest('td');
                        fileWrap.find('.file-upload').unbind('click').click(function(){
                            var uploadName = fileWrap.find('.upload-name').val();
                            if(!fileWrap.find('.fa-times2').is('.hidden')){
                                //alert('파일 삭제후 이용해주세요');
                                Swal.fire({
                                    icon: 'error',
                                    title: '파일 삭제후 이용해주세요',
                                });
                                return false;
                            }
                        });
                        fileWrap.find('.thumb_img img').attr('src','/common/siteImgView?safSeq='+v['safSeq']);
                        fileWrap.find('.upload-name').val(v['safOrFile']);
                        fileWrap.find('.file-bg').removeClass('hidden');
                        fileWrap.find('.fa-times').removeClass('hidden');
                        //fileWrap.find('.file-upload').addClass('hidden');
                        fileWrap.find('.file-bg').unbind('click').click(function(){
                            window.open('/common/siteFileDown?safCode='+v['safCode']+'&safSeq='+v['safSeq'])
                        });

                        //삭제
                        fileWrap.find('.fa-times').unbind('click').click(function(){
                            fileWrap.find('.thumb_img img').attr('src','');
                            fileTarget.data({'delSafSeq':fileTarget.data('safSeq')});
                            fileWrap.find('.file-bg').addClass('hidden');
                            fileWrap.find('.fa-times').addClass('hidden');
                            fileWrap.find('.upload-name').val('파일 선택');
                        })
                    }

                  });
                  if(fileMapCount==0){

                        var fileTarget =$('.fileMap :file');
                        var fileWrap =fileTarget.closest('td');
                        fileWrap.find('.thumb_img img').attr('src','/admassets/images/sample.JPG');
                        fileWrap.find('.file-bg').addClass('hidden');
                        fileWrap.find('.fa-times').addClass('hidden');
                        fileWrap.find('.upload-name').val('파일 선택');
                        $('.fileMap :file').removeData();
                  }
                } else {
                    var fileTarget =$('.fileMap :file');
                    var fileWrap =fileTarget.closest('td');
                    fileWrap.find('.thumb_img img').attr('src','/admassets/images/sample.JPG');
                    fileWrap.find('.file-bg').addClass('hidden');
                    fileWrap.find('.fa-times').addClass('hidden');
                    fileWrap.find('.upload-name').val('파일 선택');
                    $('.fileMap :file').removeData();
                }
                if(rowData['filelist']){
                    console.log("fileList",rowData['filelist']);
                    $.each(rowData['filelist'],function(k,v){
                        if(v['safCode'].indexOf('.')>-1){
                            var tabGb = v['safCode'].split('.')[0];
                            $('.'+tabGb+'.I .file').empty();

                        }
                    });
                    $.each(rowData['filelist'],function(k,v){
                        if(v['safCode'].indexOf('.')>-1){
                            var tabGb = v['safCode'].split('.')[0];

                            //plus.evnet.fileElement(v['safCode'],v).appendTo($('.'+tabGb+'.file'));
                            console.log('.'+tabGb+'.file');
                            var fileElement = plus.event.fileElement(v['safCode'],v).appendTo($('.'+tabGb+'.I .file'));
                            fileElement.find('.upload-name').val(v['safOrFile']);

                        }
                        console.log(k,v);
                    });
                }
                $('td.file').each(function(){
                    if($(this).find('.add_file_div').length==0){
                        var getId = plus.getId();
                        var fileGb = $(this).attr('data');
                        var fileGbName = [fileGb,getId].join('.');
                        plus.event.fileElement(fileGbName, {}).appendTo($(this));
                    }
                })
                $('.add_file').unbind('click').click(function(){

                   var pElement = $(this).closest('td');

                    var getId = plus.getId();
                    var fileGb = pElement.attr('data');
                    var fileGbName = [fileGb,getId].join('.');
                    plus.event.fileElement(fileGbName, {}).appendTo(pElement);

                   //var fileWrap = $(this).closest('div').clone().appendTo(pElement);

                   // var getId = plus.getId();
                   // fileWrap.find('.btn-st1').attr('for',getId);
                   // var del_file = fileWrap.find('.del_file');
                   //  del_file.removeClass('hidden').click(function(){
                   //      $(this).closest('div').hide();
                   //  })
                   // fileWrap.find('.upload-name').val('');
                   // var name=  fileWrap.find('.upload-hidden').attr('name');
                   // if(name.indexOf('.')>-1){
                   //     var fileGbName = [name.split('.')[0],getId].join('.');
                   //     fileWrap.find('.upload-hidden').attr('name',fileGbName).attr('id',getId);
                   // } else {
                   //     fileWrap.find('.upload-hidden').attr('name',getId).attr('id',getId);
                   // }



                });



            },
            bbsfilebbs:function(rowData){
                var filexCheck = false;
                if(rowData['filelist']){
                    if(rowData['filelist'].length){
                        filexCheck=true;
                        $('.file').empty();

                        $.each(rowData['filelist'],function(k,v){
                                var fileElement = plus.event.bbsfileElement(v['bafCode'],v).appendTo($('.file'));
                                fileElement.find('.upload-name').val(v['bafOrFile']);
                        });

                    } else {

                    }

                }
                if(filexCheck==false){
                    $('.file').empty();
                    var fileElement = plus.event.bbsfileElement('file', {}).appendTo($('.file'));
                            fileElement.find('.upload-name').val('');
                }
                $('td.file').each(function(){
                    if($(this).find('.add_file_div').length==0){
                        var getId = plus.getId();
                        var fileGb = $(this).attr('data');
                        var fileGbName = [fileGb,getId].join('.');
                        plus.event.fileElement(fileGbName, {}).appendTo($(this));
                    }
                })
                $('.add_file').unbind('click').click(function(){

                   var pElement = $(this).closest('td');

                    var getId = plus.getId();
                    var fileGb = pElement.attr('data');
                    var fileGbName = [fileGb,getId].join('.');
                    plus.event.fileElement(fileGbName, {}).appendTo(pElement);

                   //var fileWrap = $(this).closest('div').clone().appendTo(pElement);

                   // var getId = plus.getId();
                   // fileWrap.find('.btn-st1').attr('for',getId);
                   // var del_file = fileWrap.find('.del_file');
                   //  del_file.removeClass('hidden').click(function(){
                   //      $(this).closest('div').hide();
                   //  })
                   // fileWrap.find('.upload-name').val('');
                   // var name=  fileWrap.find('.upload-hidden').attr('name');
                   // if(name.indexOf('.')>-1){
                   //     var fileGbName = [name.split('.')[0],getId].join('.');
                   //     fileWrap.find('.upload-hidden').attr('name',fileGbName).attr('id',getId);
                   // } else {
                   //     fileWrap.find('.upload-hidden').attr('name',getId).attr('id',getId);
                   // }



                });



            },
            formAfter:function(pageContentLast,rowData,mode){
                $('.error').html('');
                $(".select2_group",pageContentLast).select2({width:'100%'});
                // $('.btnEditElement',pageContentLast).unbind('click')
                // $('.btnEditElement',pageContentLast).click(function(){
                //     $(this).closest('form').attr('action',$(this).attr('data'));
                //     plus.event.tplSubmit($(this).closest('form'))
                // });
                $('.btnEditElementBefore',pageContentLast).unbind('click').click(plus.event.tplEdit);
                $('.btnCancelElement',pageContentLast).unbind('click').click(plus.event.tplCancel);

                $('.btnDeleteElement',pageContentLast).unbind('click').click(plus.event.tplDelete);
                $('.btnAddressElement',pageContentLast).unbind('click').click(plus.address.open);
                // $('.inp.datepicker',pageContentLast).datetimepicker({
                //     format: 'YYYY-MM-DD'
                // });

                try {
                    pageContentLast.find('tr.EDIT,tr.NEW').addClass('hidden');
                    pageContentLast.find('tr.'+mode).removeClass('hidden');
                } catch (e){
                    console.log(e);
                }




                if(pageContentLast.find('form').length>0){
                    pageContentLast.find('form').get(0).reset();
                    plus.event.tplSubmit(pageContentLast.find('form'));
                }
                if(pageContentLast.prop('nodeName')=='FORM'){
                    plus.event.tplSubmit(pageContentLast);
                }

                if(Object.keys(rowData).length>0){

                    var forcheck=0;
                    $.each(rowData,function(i,v){
                       //console.log("each",mode,i,v);
                       var el = $('#'+i,pageContentLast);

                       if(el.length!=0){
                           if(mode=='VIEW'){
                               el.setHtml(v);
                           } else {

                               el.setValue(v=='NULL'?'':v);
                               $('.'+mode).find('.'+i).html(v)
                           }
                       } else{
                           el = $('input[name='+i+']',pageContentLast);
                           $('.'+mode).find('.'+i).html(rowData[i])
                           if(el.length!=0){
                               if(el.eq(0).attr('type')=='radio'||el.eq(0).attr('type')=='checkbox'){
                                   $.each(el,function(k,v){
                                       if($(this).attr('value')==rowData[i]){
                                           $(this).prop('checked',true);
                                       }
                                   });

                               }
                           }
                       }
                        forcheck++;
                    });
                    if(forcheck==0){

                        $.each(Object.keys(rowData),function(ik,i){

                            var el = $('#'+i,pageContentLast);

                            if(el.length!=0){
                                if(mode=='VIEW'){
                                   el.setHtml(rowData[i]);
                                } else {
                                   el.setValue(rowData[i]);
                                   $('.'+mode).find('.'+i).html(rowData[i]);
                               }
                            } else {

                                el = $('input[name='+i+']',pageContentLast);
                                $('.'+mode).find('.'+i).html(rowData[i])
                                if(el.eq(0).attr('type')=='radio'||el.eq(0).attr('type')=='checkbox'){
                                    $.each(el,function(k,v){
                                       if($(this).attr('value')==rowData[i]){
                                           $(this).prop('checked',true);
                                       }
                                    });

                                }
                            }

                        });
                    }
                }
                try{
                    $.each(plus.rules,function(i, v){
                        if(v['required']){
                            $('#'+i,pageContentLast).parent().prev().required();
                        }
                    });
                } catch(e){

                }

                setTimeout(function(){
                    pageContentLast.find(':radio:checked').each(function(){
                        $(this).click();
                    });
                    pageContentLast.find(':checkbox:checked').each(function(){
                        $(this).click();
                    })
                },500);
            },

            tplCancel:function(){
                if(confirm("작업을 중지하고 탭을 닫으시겠습니까?")){
                    $('.nav-tabs a.active').find('.xi-close').click();
                }
            },
            tplEdit:function(){
                var pageContent = $(this).closest('.tab-pane');
                var tabId = $(this).closest('.tab-pane').attr('id');
                var rowData = plus.frontTab.db[tabId];
                //plus.event.tabAfter($('#'+tabId).find('.pageContent'),rowData,'EDIT');

                var className = rowData['className']||'';
                plus.frontTab.stack[className]($('#'+tabId).find('.pageContent'),rowData,'EDIT');

                $(this).remove();
                $('.btnEditElement',pageContent).removeClass('hide');
            },
            tplDelete:function(thisObj,url,data){
                //var url=$(this).attr('data');
                //var textFalg = $(this).html();
                var tplViewForm = $(this).closest('form');
                //thisObj.
                var textFalg = '삭제';
                if(!data){
                    data = {};
                }
                if(confirm(String.format("데이터를 {0} 하시겠습니까?",textFalg))) {
                    $.call($(this).attr('data'),tplViewForm.domJson(),function(r){
                            var toastColor = '';
                            var colors = {
                                SUCCESS: 'success',
                                FAIL: 'warning',
                                DUPLICATION: 'error',
                                EXCEPTION: 'error',
                                EMPTY_PARAM: 'info',
                            };
                            try {
                                toastColor = colors[r.resultCode];
                            } catch (e) {
                                toastColor = 'info';
                            }
                            $('#' + r['pk']).val('');
                            try {
                                if (r['pk']) {
                                    plus.tab.getActiveTab().find('#' + r['pk']).val(r[r['pk']]);
                                }
                            } catch (e) {
                                console.log(e);
                            }
                            //$('#alertModal .msg').html(r.resultMsg);

                            Swal.fire(
                              r.resultMsg,
                              '',
                              'success'
                            )
                            //$('#alertModal').modal()
                            // $.toast({
                            //     heading: '데이터 전송 결과',
                            //     text: r.resultMsg,
                            //     position: 'top-right',
                            //     loaderBg: '#ff6849',
                            //     icon: toastColor,
                            //     hideAfter: 2500,
                            //     stack: 6
                            // });
                            if (typeof(plus.evnet.submitAfter) == 'function') {
                                plus.evnet.submitAfter();
                            }
                    })
                    // tplViewForm.attr('action', url);
                    // tplViewForm.ajaxSubmit({
                    //     data:data,
                    //     success: function (r) {
                    //         var toastColor = '';
                    //         var colors = {
                    //             SUCCESS: 'success',
                    //             FAIL: 'warning',
                    //             DUPLICATION: 'error',
                    //             EXCEPTION: 'error',
                    //             EMPTY_PARAM: 'info',
                    //         };
                    //         try {
                    //             toastColor = colors[r.resultCode];
                    //         } catch (e) {
                    //             toastColor = 'info';
                    //         }
                    //         $('#' + r['pk']).val('');
                    //         try {
                    //             if (r['pk']) {
                    //                 plus.tab.getActiveTab().find('#' + r['pk']).val(r[r['pk']]);
                    //             }
                    //         } catch (e) {
                    //             console.log(e);
                    //         }
                    //         //$('#alertModal .msg').html(r.resultMsg);
                    //
                    //         Swal.fire(
                    //           r.resultMsg,
                    //           '',
                    //           'success'
                    //         )
                    //         //$('#alertModal').modal()
                    //         // $.toast({
                    //         //     heading: '데이터 전송 결과',
                    //         //     text: r.resultMsg,
                    //         //     position: 'top-right',
                    //         //     loaderBg: '#ff6849',
                    //         //     icon: toastColor,
                    //         //     hideAfter: 2500,
                    //         //     stack: 6
                    //         // });
                    //         if (typeof(plus.evnet.submitAfter) == 'function') {
                    //             plus.evnet.submitAfter();
                    //         }
                    //
                    //     },
                    //     error: function (err) {
                    //         if (err.status == 404) {
                    //             alert('서버오류발생!\r관리자에게 문의해주시기바랍니다.');
                    //             //console.log('err',err);
                    //         }
                    //
                    //     }
                    // });
                    //
                    // tplViewForm.submit();
                }
                return false;
            },

            tplCallback:function(r){
                var toastColor = '';
                var colors = {
                    SUCCESS: 'success',
                    FAIL: 'warning',
                    DUPLICATION: 'error',
                    EXCEPTION: 'error',
                    EMPTY_PARAM: 'info',
                };
                try {
                    toastColor = colors[r.resultCode];
                } catch (e) {
                    toastColor = 'info';
                }
                $('#' + r['pk']).val('');
                try {
                    if (r['pk']) {
                        plus.tab.getActiveTab().find('#' + r['pk']).val(r[r['pk']]);
                    }
                } catch (e) {
                    console.log(e);
                }
                Swal.fire(
                  r.resultMsg,
                  '',
                  'success'
                )
                // $('#alertModal .msg').html(r.resultMsg);
                // $('#alertModal').modal()
                // $.toast({
                //     heading: '데이터 전송 결과',
                //     text: r.resultMsg,
                //     position: 'top-right',
                //     loaderBg: '#ff6849',
                //     icon: toastColor,
                //     hideAfter: 2500,
                //     stack: 6
                // });
            },
            tplSubmit:function(thisObj){

                console.log("tplSubmit !!!!");
                if($('#lnb a.currMenu').attr('mggrant')!='A'){
                    Swal.fire(
                        '해당 메뉴는 수정 권한이 없습니다.',
                        '',
                        'error'
                    );
                    return false;
                }
                var formCheck=false;
                var tplViewForm = null;
                try {
                  if(thisObj.prop('nodeName')=='FORM'){
                        tplViewForm = thisObj
                    } else {

                        tplViewForm = $(thisObj).closest('form');
                    }
                  // formCheck=true;
                } catch(e){
                    alert('처리도중 에러가 발생했습니다. 폼전송 하지못합니다.');
                    return false;
                    // formCheck=false;
                    // tplViewForm = $(this);
                    // if(tplViewForm.prop('nodeName')!='FORM'){
                    //     tplViewForm = $(thisObj).closest('form');
                    //
                    // }
                }
                //console.log(tplViewForm,$(this));


                var rules = {};
                try {
                    var formData = tplViewForm.data();


                    if(formData['rules']){
                        rules =formData['rules'];
                    }
                } catch(ex){
                    console.log(ex);
                }

                tplViewForm.validate({
                    rules:rules,
                    debug       : false,
                    onfocusout  : false,
                    focusCleanup: true,
                    focusInvalid: false,

                    showErrors:function(errorMap, errorList) {
                        console.log(errorMap, errorList);
                        if(errorList.length){
                            var exp = [];
                            var reqCount= 0;
                            $.each(errorMap,function(k,v){
                                if(v['method']=='required'){
                                    reqCount++
                                    if($('#'+k).length){
                                        var th = $.trim($('#'+k).closest('tr').find('th').html().replace('*',''));
                                        exp.push('['+th+'] 항목은'+v);
                                    } else if($('input[name='+k+']').length){
                                        var th = $.trim($('input[name='+k+']').closest('tr').find('th').html().replace('*',''));
                                        exp.push('['+th+'] 항목은'+v);
                                    }
                                }

                            });
                            if(reqCount){
                                Swal.fire(
                                    exp.join('\r\n'),
                                    '',
                                    'error'
                                )
                            }
                        }

                        this.defaultShowErrors();
                    },
                    invalidHandler:function(event, validator){
                        var errors = validator.numberOfInvalids();
                        console.log(errors);
                        // if(errors>0){
                        //     plus.frontTab.getActiveTab().find('.btnEditElement').hide();
                        // } else {
                        //     plus.frontTab.getActiveTab().find('.btnEditElement').show();
                        // }
                    },
                    submitHandler:function(form){
                        var textFalg = '저장';
                        if(confirm(String.format("데이터를 {0} 하시겠습니까?",textFalg))) {

                            //tplViewForm.attr('action', url);
                            tplViewForm.ajaxSubmit({
                                data:{rules:JSON.stringify(rules)},
                                success: function (r) {
                                    var toastColor = '';
                                    var colors = {
                                        SUCCESS: 'success',
                                        FAIL: 'warning',
                                        DUPLICATION: 'error',
                                        EXCEPTION: 'error',
                                        EMPTY_PARAM: 'info',
                                    };
                                    try {
                                        toastColor = colors[r.resultCode];
                                    } catch (e) {
                                        toastColor = 'info';
                                    }
                                    $('#' + r['pk']).val('');
                                    try {
                                        if (r['pk']) {
                                            plus.tab.getActiveTab().find('#' + r['pk']).val(r[r['pk']]);
                                        }
                                    } catch (e) {
                                        console.log(e);
                                    }
                                    Swal.fire(
                                      r.resultMsg,
                                      '',
                                      'success'
                                    )
                                    //$('#alertModal .msg').html(r.resultMsg);
                                    //$('#alertModal').modal()
                                    // $.toast({
                                    //     heading: '데이터 전송 결과',
                                    //     text: r.resultMsg,
                                    //     position: 'top-right',
                                    //     loaderBg: '#ff6849',
                                    //     icon: toastColor,
                                    //     hideAfter: 2500,
                                    //     stack: 6
                                    // });

                                    if (typeof(plus.evnet.submitAfter) == 'function') {

                                        plus.evnet.submitAfter();
                                    }

                                },
                                error: function (err) {
                                    if (err.status == 404) {
                                        alert('서버오류발생!\r관리자에게 문의해주시기바랍니다.');
                                        //console.log('err',err);
                                    }

                                }
                            });
                        }

                    }
                });
                //tplViewForm.submit();



            }
        },
        /**
         * Json소트 {} object객체를 조건에의거
         * val , asc,desc -벨류를 asc
         * key, asc,desc
         * @param jArray
         * @param desc
         * @returns {string[]}
         */
        jsonSort:function(jArray,desc){
            var itemArray  = [];
            if(!desc) desc='';
            var keysSorted = [];
            if(!jArray) jArray = {};

            if(desc=='val.asc'||desc =='asc')
            {
                keysSorted = Object.keys(jArray).sort(function(a,b){
                    if (a==''||b=='') return -1;
                    return jArray[a]<jArray[b] ? -1 : jArray[a]>jArray[b] ? 1 : 0;
                });
            }
            else if(desc=='val.desc'||desc =='desc')
            {

                keysSorted = Object.keys(jArray).sort(function(a,b){
                    if (a==''||b=='') return -1;
                    return jArray[a]>jArray[b] ? -1 : jArray[a]<jArray[b] ? 1 : 0
                });
            }
            else if(desc=='key.asc')
            {

                keysSorted = Object.keys(jArray).sort(function(a,b){
                    if (a==''||b=='') return -1;
                    return a<b ? -1 : a>b ? 1 : 0;
                });
            }
            else if(desc=='key.desc')
            {
                keysSorted = Object.keys(jArray).sort(function(a,b){
                    if (a==''||b=='') return -1;
                    return a>b ? -1 : a<b ? 1 : 0
                });
            }
            else {
                keysSorted = Object.keys(jArray);
            }
            if(keysSorted.length){
                var found = keysSorted.indexOf('');
                if(found>-1) {
                    delete keysSorted[found];
                    keysSorted.unshift("");
                }
            }
            return keysSorted;
        },
        tab:{
            thisTab:null,
            tabCounter:2,
            tabTemplate : "<li><a href='#{href}'>#{label}</a> <span class='fa fa-times ui-icon-close' role='presentation'><i class=''></i></span></li>",
            getActiveTab:function(){
                var wrap =$('#tabs > .ui-tabs-panel').eq($('#tabs > ul li.ui-tabs-active').index());
                return wrap;
            },
            addTab:function (title,rowData,tabContent) {
                var label = title||'',
                id = "tabs-" + (plus.tab.tabCounter),
                li = $( (plus.tab.tabTemplate).replace( /#\{href\}/g, "#" + id ).replace( /#\{label\}/g, label ) ),
                tabContentHtml =  tabContent?tabContent:'<div class="pageContent"></div>';

                plus.tab.thisTab.find( ".ui-tabs-nav" ).append( li );
                plus.tab.thisTab.append( plus.makeElement('div',tabContentHtml,{id:id}) );
                plus.tab.thisTab.tabs( "refresh" );
                plus.tab.tabCounter++;

                plus.tab.thisTab.on( "click", "span.ui-icon-close", function() {
                    var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
                    $( "#" + panelId ).remove();
                    plus.tab.thisTab.tabs( "refresh" );
                });



                //plus.tab.thisTab( "option", "active", id );
                var activeNum= plus.tab.thisTab.find('ul > li').length-1;
                plus.tab.thisTab.tabs({active:activeNum});

                if(typeof(plus.event.tabAfter)=='function'){
                    console.log(rowData);
                    $('.file-img2 img').attr('src','/admassets/images/sample.JPG');
                    plus.event.tabAfter(rowData);
                }

            }
        },
        frontPage:{
            show:function(pageContentLast,rowData,mode){
                if($('#lnb a.currMenu').attr('mggrant')!='A'&&mode=='NEW'){
                    Swal.fire(
                        '해당 메뉴는 쓰기 권한이 없습니다.',
                        '',
                        'error'
                    );
                    return false;
                }
                pageContentLast.show();
                $('.file-img2 img').attr('src','/admassets/images/sample.JPG');
                plus.event.tabAfter(pageContentLast,rowData,mode)
            },
            popup:function(pageContentLast,rowData,mode){

                if($('#lnb a.currMenu').attr('mggrant')!='A'&&mode=='NEW'){
                    Swal.fire(
                        '해당 메뉴는 쓰기 권한이 없습니다.',
                        '',
                        'error'
                    );
                    return false;
                }
                $('.hidden').hide();
                pageContentLast.show();
                pageContentLast.find('.'+mode).show();
                pageContentLast.closest('.modal-wrap').addClass('is-visible');
                $('body').css("overflow", "hidden");
                $('.file-img2 img').attr('src','/admassets/images/sample.JPG');
                plus.event.tabAfter(pageContentLast,rowData,mode)
            }
        },
        frontTab:{
            db:{},
            stack:{},
            headerClass:'.nav-tabs:eq(0)',
            bodyClass:'#tabContents .contBox',
            /**
             * 엑티브된 tab컨텐츠
             * @returns {jQuery.fn.init|jQuery|HTMLElement}
             */
            getActiveTab:function(){
                var wrap = $('.nav-tabs .active').attr('href');;
                return $(wrap);
            },
            /**
             * 아이콘 클로즈
             */
            iconClick:function(){
                /* tab 탭클로즈*/
                $('.xi-close').click(function(){
                    var ax = $(this).closest('a').prev();
                    ax.click();
                    $(ax.attr('href')).addClass('active show');

                    var id = $(this).closest('a').attr('href');
                    $(this).closest('a').remove();
                    $(id).remove();
                    //setTimeout(function(){ax.click();},100);
                });
            },
            /**
             * 신규 멀티탭(다중멀티)
             * @param thisObject
             * @returns {boolean}
             */
            addMultiTab:function(thisObject){
                var tabExistsCheck = $(this.headerClass).find('a.'+thisObject.attr('mucode'));

                //기존에 탭이있는경우 기존탭을 선택되도록
                if(tabExistsCheck.length>0){
                    $(tabExistsCheck).click();
                    return false;
                }
                var tabId = plus.getId();
                var tabMax = 8
                if($(this.headerClass).find('a').length>=tabMax){
                    alert('더이상 탭을 열수 없습니다.');
                    return false
                }
                var lastPage ={'url':thisObject.attr('data'),'title':$.trim(thisObject.text()),'mucode':thisObject.attr('mucode')};

                var tabContent=  '';
                var rowData = {};
                var tabHeader = $(this.headerClass).append(plus.makeElement('a',thisObject.text()+'<i class="xi xi-close"></i>',{href:'#'+tabId,'class':thisObject.attr('mucode'),'data-toggle':"tab"}));
                var tabBody = $(this.bodyClass).append(plus.makeElement('div',tabContent,{id:tabId,role:'tabpanel','class':'tab-pane fade active show'}));
                tabHeader.find('a:last').click();
                plus.frontTab.iconClick();

                plus.frontTab.tabAppend(lastPage);
                //plus.frontTab.db[tabId]= rowData;
                //plus.event.tabAfter($("#"+tabId).find('.pageContent'),rowData,'VIEW');
            },
            tabAppend:function(lastPage){

                //lastPage['title']
                //lastPage['class']
                if(lastPage['url']){
                    $.callJS('/front/binPage',lastPage,function(r){
                        eval(r);
                    });
                }
            },
            addTab:function(title,rowData,tabContent){
                this._addTab(title,rowData,tabContent,'VIEW');
            },
            newTab:function(title,rowData,tabContent){
                tabContent.find('.btnEditElement').removeClass('hide');
                tabContent.find('.btnEditElementBefore').addClass('hide');
                tabContent.find('.btnDeleteElement').addClass('hide');
                this._addTab(title,rowData,tabContent,'EDIT');
            },
            /**
             * 일반탭할때 사용
             * @param title
             * @param rowData
             * @param tabContent
             * @returns {boolean}
             */
            _addTab:function(title,rowData,tabContent,mode){
                var tabId = plus.getId();
                if($(this.headerClass).find('a').length>8){
                    alert('더이상 탭을 열수 없습니다.');
                    return false
                }
                var tabHeader = $(this.headerClass).append(plus.makeElement('a',title+'<i class="xi xi-close"></i>',{href:'#'+tabId,'data-toggle':"tab"}));
                var tabBody = $(this.bodyClass).append(plus.makeElement('div',tabContent,{id:tabId,role:'tabpanel','class':'tab-pane fade active show'}));
                tabHeader.find('a:last').click();
                plus.frontTab.iconClick();
                plus.frontTab.db[tabId]= rowData;

                var className = rowData['className']||'';
                plus.frontTab.stack[className]($("#"+tabId).find('.pageContent'),rowData,mode);

            }
        },

//
//         tab:{
//             tabItem:[],
//             makeTab:function(tabTitle,tabContent,fn){
//                 plus.tab.tabItem = [];
//                 var tabId = plus.getId();
//                 var tabs = [];
//                 tabs.push({title:tabTitle,paneId:tabId,active: true,content:tabContent.prop('outerHTML')});
//                 console.log($('#gridElement').length)
//                 $('.tabs-inside-here').scrollingTabs({tabs:tabs,readyCallback:function(){alert()}}).on('ready.scrtabs', function() {
//                     if(typeof(fn)=="function"){
//                         fn();
//                     }
//                 })
//                 console.log($('#gridElement').length)
//                 console.log(typeof(fn))
//
//             },
//             addTab:function(rowData,titleItem,tabContent){
//                 var tabId = plus.getId();
//                 var newTab = {paneId: 'tab' + tabId,title: rowData['titleItem'],content: tabContent.prop('outerHTML'),active: true};
// console.log(newTab);
//                 plus.tab.tabItem.some(function (tab) {
//                   if (tab.active) {
//                     tab.active = false;
//                     return true; // exit loop
//                   }
//                 });
//
//                 plus.tab.tabItem.push(newTab);
//
//                 $('.tabs-inside-here').scrollingTabs('refresh', {
//                   forceActiveTab: true // make our new tab active
//                 });
//             },
//             removeTab:function(){
//
//             }
//         },
        /**
         * th,td를 반환한다.
         * @param type
         * @param name
         * @param label
         * @param attr
         * @param val
         * @returns {*}
         */
        makeTd:function(type,name,label,attr,val){

			var addClass='';
			var oriType = type;
			if(type.indexOf('.')>-1){
				addClass = type.substring(type.indexOf('.')+1);
				type =  type.substring(0,type.indexOf('.'));
			}
			if(type=='hidden') {
                return this.makeInput('hidden', name, attr, val);
            }
			if(!val) val='';
			if(!attr) attr={cell:{},label:{},field:{}};

            var resObj = $('<tr><th class="bdnoneL"></th><td class="bdnoneR"></td></tr>');
            resObj.find('th').html(plus.makeElement('label',plus.makeElement('span',label,{'class':'labelx '+addClass,title:label}),
            $.extend({},{'for':name,'type':type},attr['label'])));
            resObj.find('td').append(plus.makeType(oriType,name,label,val,attr));
            return resObj.find('th,td');


		},
        /**
         * makeTD 에서 참조
         * 어떤 항목이 왔을때 만드는것들
         * @param type
         * @param name
         * @param label
         * @param val
         * @param attr
         * @returns {*}
         */
        makeType:function(type,name,label,val,attr){
            var tempElement = null;
            var addClass='';
            if(type.indexOf('.')>-1){
                addClass = type.substring(type.indexOf('.')+1);
                type =type.substring(0,type.indexOf('.'));
                if(addClass=='display' || addClass=='img'){
                    $.extend(attr['field'],{disabled:'disabled'});
                }
            }

            var placeholder = label;
            if(label){
                var lastChar = label.substring(label.length-1);
                var lastCharLength = lastChar.toKorChars();
                if(lastCharLength.length==2){
                    placeholder += '를 입력해주세요';
                } else {
                    placeholder += '을 입력해주세요';
                }
                console.log()
            }

            var defaultAttr = {'placeholder':placeholder,'class':'inp '+addClass+' '+type,name:name,id:name};

            switch(type){

                case 'select':
                    tempElement = plus.makeSelect(name,{},val,$.extend({},defaultAttr)).addClass('select2_group inp form-control custom-select select2-hidden-accessible');
                    break;
                case 'checkbox':
                    tempElement = plus.makeCheckbox(name,{},val,$.extend({},defaultAttr));
                    break;
                case 'radio':
                    tempElement = plus.makeRadio(name,{},val,$.extend({},defaultAttr));
                    break;
                case 'calendar':
                case 'date':
                    type = 'text';
                    tempElement = plus.makeInput(type,name,$.extend({},defaultAttr)).addClass('datepicker');
                    break;
                case 'textarea':
                    tempElement = plus.makeElement('textarea',name,val,$.extend({},defaultAttr));
                    break;
                case 'display':
                case 'disable':
                case 'disabled':
                    tempElement = plus.makeInput(type,name,val,$.extend({},defaultAttr,{disabled:'disabled'})).addClass('disabled');
                    break;
                 case 'html' :
                    tempElement = name;
                    break;
                case 'none' :
                    tempElement = "";
                    break;
                case 'text':
                case 'file':
                case 'hidden':
                case 'password':
                default:
                    tempElement = plus.makeInput(type?type:'text',name,$.extend({},defaultAttr));
                    break;
            }
            return tempElement;
        },
        /**
         * 난수생성
         * @param i
         * @param d
         * @returns {string}
         */
        getId:function(i,d){
            var time = parseInt(new Date().getTime());

            if(typeof(i)!='undefined'){
                time = time+(i*i);
            }
            var add = '';
            if(typeof(d)!='undefined'){
                add=d;
            }
            //dashboard.log(typeof(i) +"::"+ time);
            return add+'id'+time;
        },
        /**
         * 인풋박스생성
         * @param type
         * @param name
         * @param attr
         * @param val
         * @returns {*}
         */
        makeInput : function(type,name,attr,val){
            var el = null;
            var addClass='';
            var nodeName = 'input';
            if(nodeName.indexOf('.')>-1){
                addClass = type.substring(type.indexOf('.')+1);
                type =type.substring(0,type.indexOf('.'));
            }

            if(typeof(type)=='undefined'){
                type = 'text';
            }
            var attr = $.extend({},{'type':type,'name':name,'id':name},attr);
            if(nodeName=='input'){
                el = $('<'+nodeName+' />');
            }
            if(val){
                el.val(val);
            }
            if(attr){
                el.attr(attr);
            }
            if(addClass){
				el.addClass(addClass);
			}
            return el;
        },
        /**
         * 엘먼트생성
         * @param nodeName
         * @param val
         * @param attr
         * @returns {jQuery|HTMLElement|*}
         */
        makeElement:function(nodeName,val,attr){
			var el = null;
            var addClass='';
			if(nodeName.indexOf('.')>-1){
                addClass = nodeName.substring(nodeName.indexOf('.')+1);
                nodeName =nodeName.substring(0,nodeName.indexOf('.'));
            }

			if(nodeName=='input' || nodeName=='img'){
				el = $('<'+nodeName+' />');
			} else {
				el = $('<'+nodeName+'></'+nodeName+'>');
			}
			if(val){
				if(nodeName=='input'||nodeName=='textarea') el.val(val);
				else el.html(val);
			}
			if(attr){
				el.attr(attr);
			}
			if(addClass){
				el.addClass(addClass);
			}
			return el;
		},
        /**
         * 셀렉트박스생성
         * @param name
         * @param dft
         * @param val
         * @param attr
         * @returns {*|jQuery|HTMLElement}
         */
        makeSelect : function(name,dft,val,attr){
            if(!attr) attr = {};
            var addClass='';
            if(name.indexOf('.')>-1){
                addClass = name.substring(name.indexOf('.')+1);
                name =name.substring(0,name.indexOf('.'));
            }
            if(name){
                $.extend(attr,{name:name,class:addClass});
            }
            var select = plus.makeElement('select','',attr);
            if(dft){
                $.each(dft,function(kk,vv){
                    select.append(plus.makeElement('option',vv,$.extend({value:(kk)},(kk+"")==(val+"")?{'selected':'selected'}:{})));
                });
            }
            //select.val((typeof(val)!='undefined'?val[name]:val));
            select.val(val);


            return select;
        },
        /**
         * 라디오박스 생성
         * @param name
         * @param dft
         * @param val
         * @returns {jQuery|HTMLElement}
         */
        makeRadio : function(name,dft,val){
            var id = plus.getId(parseInt(Math.random()*26),'mr');
            //var div = $('<span class="data"></span>');
            var div  = plus.makeElement('div','',{'class':'d-flex'});
            var onlyone = '';
            if(dft){
                $.each(dft,function(k,text){
                    var isbool = false;
                    var id3 = plus.getId(parseInt(Math.random()*881),name);
                    if(typeof(val[name])!='undefined') {
                        isbool = val[name]==k;
                    } else {
                        if(val){
                            isbool = k==val;
                        }
                    }
                    var divsuv  = plus.makeElement('div','',{'class':'custom-control custom-radio'}).appendTo(div);
                    divsuv.append(plus.makeInput('radio',k,{'class':'custom-control-input '+name,'name':name,id:id3,'checked':isbool}));
                    divsuv.append(plus.makeElement('label',text,{title:text,'for':id3,'class':'custom-control-label inp_func'}));
                });
            }
            return div;
        },
        /**
         * 버튼 생성
         * @param html
         * @param attr
         * @param fn
         * @returns {*}
         */
        makeBtn : function(html,attr,fn){
            if(!attr) attr = {};
            var fa = attr['fa'];
            if(!fn) fn = null;
            return plus.makeElement('button',(fa?'<i class="'+fa+'" style="margin:-0.5em 7px -0.23em -18px;"></i>':'')+html,$.extend({},{type:'button','class':'btn_Sm btn_write'},attr)).click(fn);
        },
        /**
         * 체크박스 생성
         * @param name
         * @param dft
         * @param val
         * @param gahang
         * @returns {jQuery|HTMLElement}
         */
        makeCheckbox : function(name,dft,val,gahang){
            var div = $('<span class="data"></span>');
            var id = plus.getId(parseInt(Math.random()*37),'mc');
            var id2 = plus.getId(parseInt(Math.random()*46),'cr');
            if(dft){
                $.each(dft,function(k,text){

                    var isbool = false;
                    var isbool2 = false;
                    var id3 = plus.getId(parseInt(Math.random()*881),name);
                    if(val){
                        isbool = k==val;
                    }
                    div.append(plus.makeInput('checkbox',k,{'class':name,'name':name,id:id3,'checked':isbool}));
                    div.append(plus.makeElement('label',text,{'for':id3,'class':'inp_func'}));

                });
            }
            return div;
        },
        /**
         * 페이지 네비게이션 바꿀것
         * @param wrapElement
         */
        gridPageNavi:function(wrapElement){
            //wrapElement.find('.dataTables_paginate').addClass('pagination pagination-sm');

            //wrapElement.find('.dataTables_paginate a').wrap('<ul class="pagination"></ul>');
            ///setTimeout(function(){
            if($('.paging_simple_numbers').length>2){
                wrapElement.find('.pagination').unwrap();
            }

            wrapElement.find('.pagination').wrap('<div class="dataTables_paginate paging_simple_numbers"></div>');
            //alert(wrapElement.find('.paginate').length);
            wrapElement.find('.pagination a').wrap('<li class="paginate_button page-item"></li>');
            wrapElement.find('.pagination a.active').closest('li').addClass('active')

            //},100);
            //.paginate

        },
        /**
         * 그리드 렌더러 정의해서 사용
         */
        renderer:{
            rownum:function(val,f,row ,set){

                return set['settings']['_iDisplayStart']+(set['row']+1);
            },
            rrownum:function(val,f,row ,set){

                return set['settings']['_iRecordsTotal'] - ( set['settings']['_iDisplayStart'] + set['row'])
            },
            iddate:function(val,f,row){
                var strValue = [];
                if(row['udtDate']){
                    strValue.push(row['udtUmName']);
                    strValue.push('(');
                    strValue.push(row['udtUmId']);
                    strValue.push(') ');
                    var a = new Date(row['udtDate']);
                    strValue.push(a.format('yy.MM.dd HH:mm'))
                } else {
                    strValue.push(row['regUmName']);
                    strValue.push('(');
                    strValue.push(row['regUmId']);
                    strValue.push(') ');
                    var a = new Date(row['regDate']);
                    strValue.push(a.format('yy.MM.dd HH:mm'))

                }
                row['show_iddate'] = strValue.join('');
                return '<span class="clickkbox">'+strValue.join('')+'</span>';
            },
            img:function(d, t, r){
                if(!d){
                    d = {};
                }
                var src= '';
                if(d.hasOwnProperty('bafSeq')){
                    src='/common/imgView?bafSeq='+d['bafSeq'];
                } else {
                    src='/admassets/images/sample.JPG'
                }
                // console.log('!t',t);
                // console.log('!r',r);
                var div = plus.makeElement('img','',{'src':src,'class':'gridimg'});
                return div.prop('outerHTML')
            },
            banner:function(d, t, r){
                if(!d){
                    d = {};
                }
                var src= '';
                if(d.hasOwnProperty('safSeq')){
                    src='/common/siteImgView?safSeq='+d['safSeq'];
                } else {
                    src='/admassets/images/sample.JPG'
                }
                // console.log('!t',t);
                // console.log('!r',r);
                var div = plus.makeElement('img','',{'src':src,'class':'gridimg','onerror':"this.src='/admassets/images/sample.JPG'"});
                return div.prop('outerHTML')
            },
            file:function(d, t, r){
                if(!d){
                    d = {};
                }
                var src= '';
                var fileexists ="-";
                if(d.hasOwnProperty('bafSeq')){
                    src='javascript:window.open(\'/common/fileDown?bafSeq='+d['bafSeq']+'\')';
                    fileexists="O";
                } else {
                    src='javascript:;'
                }
                // console.log('!t',t);
                // console.log('!r',r);
                var div = plus.makeElement('a',fileexists,{'href':src,'class':'gridimg'});
                return div.prop('outerHTML')
            },
            sitefile:function(d, t, r){
                if(!d){
                    d = {};
                }
                var src= '';
                var fileexists ="-";
                if(d.hasOwnProperty('safSeq')){
                    src='javascript:window.open(\'/common/siteFileDown?safSeq='+d['safSeq']+'\')';
                    fileexists="O";
                } else {
                    src='javascript:;'
                }
                // console.log('!t',t);
                // console.log('!r',r);
                var div = plus.makeElement('a',fileexists,{'href':src,'class':'gridimg'});
                return div.prop('outerHTML')
            },
            num: function ( data, type, row ) {
                if(!data) return 0;
                return plus.event.addComma(Number(data).toLocaleString());
            },
            input:function( data, type, row ,set) {
                //console.log(set['settings']['aoColumns'][set['col']]['data'],set);
                var fieldName = set['settings']['aoColumns'][set['col']]['data'];
                var fieldClass = '';
                try {
                    fieldClass = set['settings']['aoColumns'][set['col']]['inputClass'];
                }catch(e){

                }
                return plus.makeInput('text',fieldName,{value:data,class:"form-input "+fieldName +" "+fieldClass,id:null}).width(80).prop('outerHTML');
            },
            code:function(val,f,row,s){
              if(typeof(s['settings']['aoColumns'][s['col']]['code'])!=undefined){
                  return s['settings']['aoColumns'][s['col']]['code'][val];
              }
              return val;
            },
            clickbox:function(d, t, r){
                d = $.trim(d);
                if(d=='NULL'){
                    d = '';
                }
                if(d=='null'){
                    d = '';
                }

                console.log('ddddddddd',d);
                var div = plus.makeElement('a',$.trim(d),{'href':'javascript:;','class':'clickkbox'});
                return div.prop('outerHTML')
            },
            clickboxview:function(d, t, r){
                if(d=='NULL'){
                    d = '';
                }
                var div = plus.makeElement('a','보기',{'href':'javascript:;','class':'clickkbox'});
                return div.prop('outerHTML')
            },
            select:function( data, type, row ,set){
                //var gb = $.extend({},{'0':'그룹선택'},ibms.codes.GB);
                var fieldName  =set['settings']['aoColumns'][set['col']]['data'];
                var code = {};
                if(set['settings']['aoColumns'][set['col']]['code']){
                    code = set['settings']['aoColumns'][set['col']]['code'];
                }

                return plus.makeSelect(fieldName,code,data).prop("outerHTML");
            },
            dashtocomma:function(r,f,row,s){
                if(!r){r = '';}
                try{

                    return r.replace(/-/ig,'.');
                } catch(e){
                    console.log(e);
                }
                return r;
            },
            comma:function(r,f,row,s){
                try{

                    return r.addComma();
                } catch(e){
                    console.log(e);
                }
                return r;
            },
            time:function(r,f,row,s) {
                try {
                    var a = new Date(r);
                    return a.format('yy-MM-dd');
                } catch (e) {
                    console.log(e);
                }
                return r;
            },
            date:function(r,f,row,s) {
                try {
                    var a = new Date(r);

                    console.log(r,f,row,s);
                    return a.format('yyyy-MM-dd');
                } catch (e) {
                    console.log(e);
                }
                return r;
            },
            os:function(r,f,row,s){
                var AgentUserOs=r;





                    if(AgentUserOs.indexOf("WindowsCE") != -1) OSName="Windows CE";

                    else if(AgentUserOs.indexOf("Windows95") != -1) OSName="Windows 95";

                    else if(AgentUserOs.indexOf("Windows98") != -1) {

                        if (AgentUserOs.indexOf("Win9x4.90") != -1) OSName="Windows Millennium Edition (Windows Me)"

                            else OSName="Windows 98";

                    }

                    else if(AgentUserOs.indexOf("WindowsNT4.0") != -1) OSName="Microsoft Windows NT 4.0";

                    else if(AgentUserOs.indexOf("WindowsNT5.0") != -1) OSName="Windows 2000";

                    else if(AgentUserOs.indexOf("WindowsNT5.01") != -1) OSName="Windows 2000, Service Pack 1 (SP1)";

                    else if(AgentUserOs.indexOf("WindowsNT5.1") != -1) OSName="Windows XP";

                    else if(AgentUserOs.indexOf("WindowsNT5.2") != -1) OSName="Windows 2003";

                    else if(AgentUserOs.indexOf("WindowsNT6.0") != -1) OSName="Windows Vista/Server 2008";

                    else if(AgentUserOs.indexOf("WindowsNT6.1") != -1) OSName="Windows 7";

                    else if(AgentUserOs.indexOf("WindowsNT6.2") != -1) OSName="Windows 8";

                    else if(AgentUserOs.indexOf("WindowsNT6.3") != -1) OSName="Windows 8.1";

                    else if(AgentUserOs.indexOf("WindowsNT6.4") != -1) OSName="Windows 10";

                    else if(AgentUserOs.indexOf("WindowsPhone8.0") != -1) OSName="Windows Phone 8.0";

                    else if(AgentUserOs.indexOf("WindowsPhoneOS7.5") != -1) OSName="Windows Phone OS 7.5";

                    else if(AgentUserOs.indexOf("Xbox") != -1) OSName="Xbox 360";

                    else if(AgentUserOs.indexOf("XboxOne") != -1) OSName="Xbox One";

                    else if(AgentUserOs.indexOf("Win16") != -1) OSName="Windows 3.x";

                    else if(AgentUserOs.indexOf("ARM") != -1) OSName="Windows RT";

                    else OSName="Windows (Unknown)";



                    if(AgentUserOs.indexOf("WOW64") != -1) OsVers=", WOW64";

                    else if(AgentUserOs.indexOf("Win64;x64;") != -1) OsVers=", Win64 on x64";

                    else if(AgentUserOs.indexOf("Win16") != -1) OsVers=" 16-bit";

                    else OsVers=" on x86";


                    if(AgentUserOs.indexOf("BlackBerry9000") != -1) OSName="BlackBerry9000";

                    else if(AgentUserOs.indexOf("BlackBerry9300") != -1) OSName="BlackBerry9300";

                    else if(AgentUserOs.indexOf("BlackBerry9700") != -1) OSName="BlackBerry9700";

                    else if(AgentUserOs.indexOf("BlackBerry9780") != -1) OSName="BlackBerry9780";

                    else if(AgentUserOs.indexOf("BlackBerry9900") != -1) OSName="BlackBerry9900";

                    else if(AgentUserOs.indexOf("BlackBerry;Opera Mini") != -1) OSName="Opera/9.80";

                    else if(AgentUserOs.indexOf("Symbian/3") != -1) OSName="Symbian OS3";

                    else if(AgentUserOs.indexOf("SymbianOS/6") != -1) OSName="Symbian OS6";

                    else if(AgentUserOs.indexOf("SymbianOS/9") != -1) OSName="Symbian OS9";

                    else if(AgentUserOs.indexOf("Ubuntu") != -1) OSName="Ubuntu";

                    else if(AgentUserOs.indexOf("PDA") != -1) OSName="PDA";

                    else if(AgentUserOs.indexOf("NintendoWii") != -1) OSName="Nintendo Wii";

                    else if(AgentUserOs.indexOf("PSP") != -1) OSName="PlayStation Portable";

                    else if(AgentUserOs.indexOf("PS2;") != -1) OSName="PlayStation 2";

                    else if(AgentUserOs.indexOf("PLAYSTATION3") != -1) OSName="PlayStation 3";

                    else OSName="Linux (Unknown)";



                    if(AgentUserOs.indexOf("x86_64") != -1) OsVers=", x86_64";

                    else if(AgentUserOs.indexOf("i686") != -1) OsVers=", i686";

                    else if(AgentUserOs.indexOf("i686 on x86_64") != -1) OsVers=", i686 running on x86_64";

                    else if(AgentUserOs.indexOf("armv7l") != -1) OsVers=" Nokia N900 Linux mobile, on the Fennec browser";

                    else if(AgentUserOs.indexOf("IA-32") != -1) OsVers=" 32-bit";

                    else OsVers="";



                    if(AgentUserOs.indexOf("iPhoneOS3") != -1) OSName="iPhone OS 3";

                    else if(AgentUserOs.indexOf("iPhoneOS4") != -1) OSName="iPhone OS 4";

                    else if(AgentUserOs.indexOf("iPhoneOS5") != -1) OSName="iPhone OS 5";

                    else if(AgentUserOs.indexOf("iPhoneOS6") != -1) OSName="iPhone OS 6";

                    else if(AgentUserOs.indexOf("iPhoneOS7") != -1) OSName="iPhone OS 7";

                    else if(AgentUserOs.indexOf("iPhoneOS8") != -1) OSName="iPhone OS 8";

                    else if(AgentUserOs.indexOf("iPad") != -1) OSName="iPad";

                    else if((AgentUserOs.indexOf("MacOSX10_1")||AgentUserOs.indexOf("MacOSX10.1")) != -1) OSName="Mac OS X Puma";

                    else if((AgentUserOs.indexOf("MacOSX10_2")||AgentUserOs.indexOf("MacOSX10.2")) != -1) OSName="Mac OS X Jaguar";

                    else if((AgentUserOs.indexOf("MacOSX10_3")||AgentUserOs.indexOf("MacOSX10.3")) != -1) OSName="Mac OS X Panther";

                    else if((AgentUserOs.indexOf("MacOSX10_4")||AgentUserOs.indexOf("MacOSX10.4")) != -1) OSName="Mac OS X Tiger";

                    else if((AgentUserOs.indexOf("MacOSX10_5")||AgentUserOs.indexOf("MacOSX10.5")) != -1) OSName="Mac OS X Leopard";

                    else if((AgentUserOs.indexOf("MacOSX10_6")||AgentUserOs.indexOf("MacOSX10.6")) != -1) OSName="Mac OS X Snow Leopard";

                    else if((AgentUserOs.indexOf("MacOSX10_7")||AgentUserOs.indexOf("MacOSX10.7")) != -1) OSName="Mac OS X Lion";

                    else if((AgentUserOs.indexOf("MacOSX10_8")||AgentUserOs.indexOf("MacOSX10.8")) != -1) OSName="Mac OS X Mountain Lion";

                    else if((AgentUserOs.indexOf("MacOSX10_9")||AgentUserOs.indexOf("MacOSX10.9")) != -1) OSName="Mac OS X Mavericks";

                    else if((AgentUserOs.indexOf("MacOSX10_10")||AgentUserOs.indexOf("MacOSX10.10")) != -1) OSName="Mac OS X Yosemite";

                    else OSName="MacOS (Unknown)";



                    if(AgentUserOs.indexOf("Intel") != -1) OsVers=" on Intel x86 or x86_64";

                    else if(AgentUserOs.indexOf("PPC") != -1) OsVers=" on PowerPC";


              var OSDev = OSName + OsVers;

                return OSDev;
            },datetime:function(r,f,row,s){
                try{
                    var a = new Date(r);
                    return a.format('yy.MM.dd HH:mm');
                } catch(e){
                    console.log(e);
                }
                return r;
            },datetimefull:function(r,f,row,s){
                try{
                    var a = new Date(r);
                    var b = new Date(row['rcEdate']);
                    return a.format('yy.MM.dd HH:mm')+'~'+b.format('yy.MM.dd HH:mm');
                } catch(e){
                    console.log(e);
                }
                return r;
            }
        },
        /**
         * 그리드 객체 Ready상태일때 호출하기위함
         */
        ready:{

        },
        /**
         * 펑션 객체를 function aa() 이렇게 사용하지 않도록
         */
        evnet:{
            gridComplet:function(){

            }
        },
        log:function(gubun){
            //가끔 콘솔로그가 없는 브라우저가있음
            if(typeof (console.log)=='function'){
                console.log(gubun,arguments);
            }

        },
        /**
         * 사이트에서 사용하는
         * 그리드 기본구조체
         */
        grid:{
            "language": {
                "emptyTable"		: "데이터가 존재하지 않습니다",
                "zeroRecords"		: "데이터가 존재하지 않습니다",
                "search"			: "검색어:<span style='display:inline-block;width:10px;'></span> ",
                "searchPlaceholder"	: "검색어를 입력하세요",
                "paginate":{
                'previous':'<i class="fa fa-angle-left"></i>',
                        'next':'<i class="fa fa-angle-right"></i>',
                        'last':'<i class="fa fa-angle-double-right"></i>',
                        'first':'<i class="fa fa-angle-double-left"></i>'
                },
            },
            //dom <lf<t>ip>
            // <div>
            //     { length }
            //     { filter }
            //     <div>
            //         { table }
            //     </div>
            //     { info }
            //     { paging }
            // </div>
            //"dom": 'iptfl'
            //fnDrawCallback
            //테이블 데이터 합계 정보 - 페이징 - 테이블 - 필터링(검색) - 표시 건수 설정
            "dom":'<<"container-fluid"<"form-inline justify-content-end"<"input-group input-group-sm searchx"lf>>><t>i<"table-footer table-footer_fix"p>>',
            "bPaginate": true,
            "bLengthChange": false,
            destroy: true,
             aDataSort:false,
             pageLength: 10,
             bAutoWidth: false,
            processing: false,
            ordering: false,
            serverSide: true,
            scrollCollapse:true,
            searching: false,
            sAjaxSource : "",
            sServerMethod: "POST",
            rownum:false,
            "columns" : [],
            "drawCallback":function( settings, json){
              //console.log('"drawCallback":function( settings, json){');
              var dataTableEl = $('#'+settings.sInstance).closest(".dataTables_wrapper");
              plus.gridPageNavi(dataTableEl);
            },
            "initComplete":function( settings, json){
                //console.log('initComplete');

                //var dataTable = $('#'+settings.sInstance).DataTable();
                var dataTableEl = $('#'+settings.sInstance).closest(".dataTables_wrapper");
                dataTableEl.find('th').each(function(){
                   $(this).attr('title',$(this).html());
                });
                //plus.gridPageNavi(dataTableEl);

                $("#jb-checkbox").off('click').on('click',function(e) {

                    //만약 전체 선택 체크박스가 체크된상태일경우
                    if ($("#jb-checkbox").prop("checked")) {
                        //해당화면에 전체 checkbox들을 체크해준다
                        $(":checkbox",dataTableEl).prop("checked", true).change();
                        // 전체선택 체크박스가 해제된 경우
                    } else {
                        //해당화면에 모든 checkbox들의 체크를해제시킨다.
                        $(":checkbox",dataTableEl).prop("checked", false).change();
                    }
                })
                try{
                    if(typeof(plus.ready[settings.sInstance])=='function'){
                        console.log(plus.ready[settings.sInstance]());
                    }
                } catch(e){console.log(e)}
                try{
                    if(typeof(plus.event.gridComplet)=='function'){
                        plus.event.gridComplet(settings.sInstance);
                    }
                } catch(e){console.log(e)}

                // $('#'+settings.sInstance+' tbody ').on( 'click', 'tr', function () {
                //     console.log( dataTable.row( this ).data() );
                // });
            },
            columnDefs: [
                {
                    className: 'dt-body-left'
                }
              ],
            "responsive"	: false,				//#헤더고정
            "bInfo"			: false,			//#페이지 정보
            "pagingType"	: "full_numbers",	//#Full Paging
            "lengthMenu"	: [[15, 25, 50, 100, -1], [15, 25, 50, 100, "All"]], //#show entries
            "order"			: [[0, 'asc']]		//Sort 순서
        },

        /**
         * Grid에서 사용할 Ajax객체 생성
         * @param url
         * @param data
         * @param dataSrc
         * @returns {{url: *, data: *, type: string, dataSrc: *, deferRender: boolean}}
         */
        makeAjax:function(url,data,dataSrc){
            if(!dataSrc) dataSrc = 'data';
            var resAjax = {
                'url':url,
                'data':data,
                'type':'POST',
                'dataSrc':dataSrc,
                'deferRender': true,
            }
            return resAjax;
        },
        /**
         * 그리드를 생성한다.
         * @param id
         * @param columns
         * @param ajax
         * @param attr
         * @returns {jQuery}
         */
        makeGrid:function(id,columns,ajax,attr){
            if(!attr) attr = {};
            //히든및 가공한번 필요할때 사용
            $.fn.dataTable.ext.errMode = 'none'
            $.fn.dataTableExt.sErrMode = 'none'
            if(columns.length){
                $.each(columns,function(i,v){
                    if(columns[i].hidden){
                       columns[i]['visible'] = false;
                       columns[i]['searchable'] = false;
                    }
                });

            }
            //console.log(id,url,columns);
            var dataGrid = $(id).DataTable($.extend({},$.extend({},plus.grid,attr),{'ajax':ajax,columns:columns}));


            return dataGrid;
        },
        /**
         * 리사이즈 함수
         * 오버라이딩 가능
         */
        resize:function(){
            var h= $(window).height()-70;
            $('#dev').height(h);
        }
    }

    /*
    * $.toast({
            heading: 'Welcome to admin',
            text: 'Use the predefined ones, or specify a custom position object.',
            position: 'top-right',
            loaderBg:'#ff6849',
            icon: 'info',
            hideAfter: 2500,
            stack: 6
        });
    * */
$(document).ready(function(){
    $('.modal .close-pop').click(function() {
        $(this).parents('.modal-wrap').removeClass('is-visible');
        $('body').css("overflow", "scroll");
    });
    var li =plus.makeElement('li');
    // var pburl = document.location.pathname.split('/')[2]
    // var a = plus.makeElement('a','퍼블',{href:'javascript:window.open(\'/admassets/pages/'+pburl+'.html\',\'pb\')'})
    // a.appendTo(li);
    var a = plus.makeElement('a','퍼블사이트맵',{href:'javascript:window.open(\'http://hsk3807nas.synology.me/aigo/admin/sitemap.html\',\'sitemap\')'})
    a.appendTo(li);
    li.appendTo($('.content-header .fR'))
});
