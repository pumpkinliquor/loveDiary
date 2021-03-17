var isloginAlert = false;
(function($) {
    $.ajaxSetup({
        beforeSend: function(xhr) {
            xhr.setRequestHeader("AJAX", true);
        },
        error: function(xhr, status, err) {

            if (xhr.status == 401) {
                alert("401");
            } else if (xhr.status == 403) {

                if(!isloginAlert){
                    isloginAlert = true;
                    alert("사용자 세션이 만료되었습니다.\n 다시 로그인해주세요.");

                    var url = window.location.href;
                    lgUI.log(url);
                }

            } else {
                lgUI.log("예외가 발생했습니다. 관리자에게 문의하세요.");
            }
        }
    });
})(jQuery);
$.extend({
    call:function(url,data,callback,isAsync){
        isAsync=false;
        $.ajax({
            url:url,
            type:'post',
            data:data,
            async:isAsync,
            success:callback
        })
    },
    viewData:function(fieldKey,wrapElement,value){
        value=$.trim(value);
        $('#'+fieldKey,wrapElement).val(value);
        var tdElement = $('td').has($('#'+fieldKey,wrapElement).not(':hidden input'));
        console.log(tdElement.index());
        if(tdElement.length){

            $('td').has($('#'+fieldKey,wrapElement).not(':hidden input')).html(value);
            $('td').has($('[id^='+fieldKey+']',wrapElement).not(':hidden input')).html(value);
            tdElement.addClass('added')
            $('#'+fieldKey,wrapElement).not(':hidden input').remove();
            $('[id^='+fieldKey+']',wrapElement).not(':hidden input').remove();
        }

    }
});
$.fn.extend({
    sap:function(addClass){
        //try{}
        //if($(this).get(0).nodeName=='SELECT')
        $(this).each(function(i){
            var nodeName = $(this).get(0).nodeName;
           if($.trim(nodeName)=='SELECT'){
               $(this).parent().find('.select2-container .select2-selection--single').addClass('sap '+addClass);
               $(this).addClass('sap '+addClass).attr('readonly','readonly').css('cursor','not-allowed');
           } else {
                $(this).addClass('sap '+addClass).attr('readonly','readonly').css('cursor','not-allowed');
           }
        });

        return $(this);
    },
    rsap:function(addClass){
        //try{}
        //if($(this).get(0).nodeName=='SELECT')
        $(this).each(function(i){
            var nodeName = $(this).get(0).nodeName;
            if($.trim(nodeName)=='SELECT'){
                $(this).parent().find('.select2-container .select2-selection--single').addClass('sap '+addClass);
                $(this).addClass('rsap '+addClass);
            } else {
                $(this).addClass('rsap '+addClass);
            }
        });

        return $(this);
    },
    readonly:function(addClass){
        $(this).addClass('readonly').attr('readonly','readonly').css('cursor','not-allowed');
        return $(this);
    },
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
    adds:function(){
        var obj = $('<div></div>');
        if(arguments.length>0){
            $.each(arguments,function(i,v){
                obj.append(v);
            });
        }
        $(this).append($(obj.html()));
        return $(this);
    },
    addTo : function(target){
        $(this).find('.cell').removeAttr('label');
        $(this).find('.cell').removeAttr('field');
        var cellSize = $(this).find('.cell').size();

        $(this).addClass('cellCnt'+cellSize).appendTo(target);
        return $(this);
    },
    setValue : function(val){
        if($(this).is('.inp')){
            $(this).val(val);
        } else if($(this).find('.inp').length>0){
            $(this).find('.inp').val(val);
        } else {
            if($(this).is(":checkbox")){
                $(this).prop('checked',val==$(this).attr('value'));
            } else {
                $(this).val(val);
            }
        }
        return $(this);
    },
    setHtml : function(val){
        if($(this).is('.inp') && $(this).is(':hidden')) {
            $(this).val(val);
        } else if($(this).is('.inp')){
            $(this).parent().html(val);
        }


        return $(this);
    },

    //{} desc
    addComma:function(){
        $(this).focus(function(){
            $(this).val($.trim($(this).val()).replace(/\,/ig,'')) ;
        }).keyup(function(e){
            if (e.which && (e.which < 48 || e.which > 57)) {   //숫자만 받기
                e.preventDefault();
            }
            var val = String($.trim($(this).val().replace(/[^0-9]/g, "")));
            $(this).val(val);
        }).blur(function(){
            $(this).val(lgUI.util.addComma($.trim($(this).val()))) ;
        });
    },
    addItem:function(jArray,empty,desc,defaultVal){
        if(!defaultVal) defaultVal = '';
        var label = null;
        if($(this).get(0).nodeName=='TH') {
            label = $(this).find('label');
        }else if($(this).get(0).nodeName=='TD'){
            label = $(this).prev().find('label');
        } else {
            label =$(this).parent().prev().find('label');
        }

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
        if(label!="undefined"){
            if(label.attr('type')=='select'){

                var target =$(this).find('.inp');
                target.empty();
                if(empty){
                    target.append(lgUI.ui.makeElement('option','선택하세요',{value:''}));
                }

                $.each(keysSorted,function(kk,vv){
                    if(jArray[vv])
                    target.append(lgUI.ui.makeElement('option',jArray[vv],{value:(vv)}));
                });
                if(defaultVal) target.val(defaultVal);
            } else if(label.attr('type')=='checkbox'||label.attr('type')=='radio'){
                target =$(this).find('.data');
                target.empty();;
                if(jArray){
                    $.each(keysSorted,function(kk,vv){
                        var isbool = false;
                        var id3 = lgUI.ui.getId(parseInt(Math.random()*881),label.attr('for'));
                        target.append(lgUI.ui.makeInput(label.attr('type'),vv,{'class':label.attr('for'),'name':label.attr('for'),id:id3,'checked':isbool}));
                        target.append(lgUI.ui.makeElement('label',jArray[vv],{'for':id3,'class':'inp_func'}));
                    });
                }
                if(defaultVal) target.val(defaultVal);
            }
        }
        return $(this);
    },
    formJson : function() {
        var obj = null;
        try {
            if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
                var arr = this.serializeArray();
                if (arr) {
                    obj = {};
                    jQuery.each(arr, function() {
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
    domJson : function() {
        var el = $(this).find('input,textarea,select');
        var obj = {};
        $.each(el,function(i){
            var objName =$(this).attr('name');
            if(objName) {
                obj[objName] = $(this).val();
                if($(this).get(0).nodeName=='SELECT' && (obj[objName]==''|| obj[objName]==null)){
                    obj[objName] =$(this).find(':selected').attr('value');
                }
            }

        });
        return obj;
    }
});


var lgUI = {
    STATE:{
      EDIT : 'EDIT:수정폼',
      NEW : 'NEW:신규등록폼',
      INFO : 'INFO:상세보기'
    },
    codes:{},
    log:function(){
        if(typeof(window.console)!='undifined'){

            $.each(arguments,function(i,v){
                if(typeof(v)=='object'){
                    console.log('arguments '+i+'번째'+typeof(v)+')',v,JSON.stringify(v));
                } else {
                    console.log('arguments '+i+'번째('+typeof(v)+')',v);
                }
            })

        }
    },
    tab:{
        thisTab:null,
        thisSubTab:null,
        tabCounter:2,
        tabTemplate : "<li><a href='#{href}'><div class=\"icon \">#{label}</div><span class='ui-icon-close' role='presentation'><img src='/asset/img/icon_tab_colse.png' alt='닫기' /></span></a></li>",
        addTab:function (title,rowId) {
            var label = title||'상세보기',
                id = "tabs-" + (lgUI.tab.tabCounter),
                li = $( (lgUI.tab.tabTemplate).replace( /#\{href\}/g, "#" + id ).replace( /#\{label\}/g, label ) ),
                tabContentHtml =  '<form class="pageContent" enctype="multipart/form-data" data-rowid="'+rowId+'" ></form>';

            lgUI.tab.thisTab.find( '.addTab' ).append( li );
            lgUI.tab.thisTab.find( '.addTab li:last a' ).attr('id','ul-id-'+id);
            lgUI.tab.thisTab.append( '<div id="' + id + '" class="tabContent"><p>' + tabContentHtml + '</p></div>' );
            lgUI.tab.thisTab.tabs( 'refresh' );
            lgUI.tab.tabCounter++;


            lgUI.tab.thisTab.on( "click", "span.ui-icon-close", function() {
                var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
                $( "#" + panelId ).remove();
                lgUI.tab.thisTab.tabs( "refresh" );
                lgUI.event.buildingAfter(); //빌딩쪽에서 사용
            });

            var activeNum= lgUI.tab.thisTab.find('ul.addTab > li').size()-1;
            lgUI.tab.thisTab.tabs({active:activeNum});

        },addSubTab:function (tab,title,rowId) {
            var subId = UI.getId();
            var label = title||'상세보기',
                id = "tabs-" + lgUI.tab.tabCounter,
                li = $( (lgUI.tab.tabTemplate).replace( /#\{href\}/g, "#" + id ).replace( /#\{label\}/g, label ) ),
                tabContentHtml =  '<div class="pageSubContent" data-rowid="'+rowId+'" ></div>';

            //tab.find( '.addSubTab' ).append( li );
            tab.append( '<div id="' + id + '" class="tabContent"><p>' + tabContentHtml + '</p></div>' );
            tab.tabs( 'refresh' );
            lgUI.tab.tabCounter++;


            tab.on( "click", "span.ui-icon-close", function() {
                var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
                $( "#" + panelId ).remove();
                tab.tabs( "refresh" );
            });

            var activeNum= tab.find('ul.addSubTab > li').size()-1;
            tab.tabs({active:activeNum});

        },activeClose:function(){
            $('#plustabs > ul.addTab li').eq($('#plustabs > ul.addTab li.ui-tabs-active').index()).find('.ui-icon-close').click();
        }
    },

    event:{
        popupSearchForm : function(){

        },
        buildingAfter:function () {
        },
        cancleBtnClick:function(existsformJson){
            if(confirm('작성을 취소하시겠습니까?\r확인또는 Yes를 누르면 탭이 닫힙니다.')){
                var pageContent = $('.pageContent').has($(this));
                $('#plustabs > ul li').eq(pageContent.index()).find('.ui-icon-close').click();
            }
        },
        slide:function(el){

            var $slide = $('.uislide',el),
                $slideGroup = $('.uislide-mask-group',el),
                $bullet = $('.uibullet',el);

            var slidesTotal = ($slide.length - 1),
                current = 0,
                autoSlide=0,
                isAutoSliding = true;

            $slide.first().addClass('current');
            $bullet.first().addClass('current');

            var clickSlide = function() {
                //stop auto sliding
                window.clearInterval(autoSlide);
                isAutoSliding = false;

                var slideIndex = $bullet.index($(this));

                updateIndex(slideIndex);
            };

            var updateIndex = function(currentSlide) {
                if(isAutoSliding) {
                    if(current === slidesTotal) {
                        current = 0;
                    } else {
                        current++;
                    }
                } else {
                    current = currentSlide;
                }

                $bullet.removeClass('current');
                $bullet.eq(current).addClass('current');

                transition(current);
            };

            var transition = function(slidePosition) {
                $slideGroup.animate({
                    'top': '-' + slidePosition + '00%'
                });
            };

            $bullet.on( 'click', clickSlide);
        },
        datepicker:function(wrapElement,rolusItem){
            "use strict";
            if(!rolusItem)rolusItem=false;
            $('input',wrapElement).attr('autocomplete', 'off');
            $( ".datepicker",wrapElement).datepicker({
                 showOn: "both",
                 language: 'ko',
                 buttonImage: "/asset/images/calendar.gif",
                 buttonImageOnly: true,
                 buttonText: "날짜를 선택하세요",
                showMonthAfterYear: true, // 월, 년순의 셀렉트 박스를 년,월 순으로 바꿔준다.
                monthNames: ['1월(January)','2월(February)','3월(March)','4월(April)','5월(May)','6월(June)',
                    '7월(July)','8월(August)','9월(September)','10월(October)','11월(November)','12월(December)'],
                dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
                monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                prevText: '이전 달',
                nextText: '다음 달'
            });

            var d = new Date();
            var options = {
                i18n: {
                    year: '년도',
                    months: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                    prevYear: '전년',
                    nextYear: '후년',
                },
                Button: false,
                MaxMonth: 0,
                MonthFormat: 'yy-mm', // Default is 'mm/yyyy' and separator char is not mandatory
                ShowIcon: false,
            };
            $('.monthpicker',wrapElement).MonthPicker(options);
            $('.month-picker-year-table',wrapElement).find('.ui-button').removeClass('ui-state-disabled');
            $( ".datepicker",wrapElement ).datepicker( "option", "dateFormat", 'yy-mm-dd' );//.datepicker('setDate', (new Date()).format('yyyy-MM-dd'));

            //이건 누가만들어논거지.. 주석처리합니다.(hsk3807)
            // $.each($(".datepicker"),function(i,v){
            //     if($.trim($(this).val())=='') {
            //         $(this).datepicker();
            //     }
            // });
            try{
                if(!rolusItem){
                    var rule = rules();
                    $.each(rule,function(i,v){
                        if(v['required']){
                            $('#'+i,wrapElement).parent().prev().required();
                        }
                    });
                }
            } catch(e){

            }
            var h =0;
            wrapElement.find('.g_column').each(function(){
                h = $(this).height()>h ?$(this).height():h;
            });
            if(h){
                wrapElement.append('<div class="cb"></div>');
                wrapElement.height(h);
            }
            $(".select2_group",wrapElement).select2({width:'100%'});


        },

        gridDelete:function(){
        },
        gridDblClickAfter:function(){

        },
        subGridDblClickAfter:function(){

        },
        submitAction:function(){

        },
        gridDblClick:function(rowId){
            var rowData = jQuery(this).getRowData(rowId);



            lgUI.tab.addTab();
        }
    },
    grid:{
        thisGrid:null,
        statementLink:function(cellvalue, options, rowObject){
            var button = $('<button></button>');
            if(!rowObject.itmSeq) return "";
            if(rowObject.itmStatementNumber){
                button = button.attr({'class':'viewLineItem','data':rowObject.itmStatementNumber,id:"row"+(options.rowid)}).html('전표취소').prop('outerHTML');
            } else {
                button = button.attr({'class':'viewLineItem','data':'',id:"row"+(options.rowid)}).html('전표생성').prop('outerHTML');
            }


            return button;
        },
        viewLink:function(cellvalue, options, rowObject){
            console.log(cellvalue, options, rowObject);

            var button = $('<button></button>').attr({'class':'viewLineItem',id:"row"+(options.rowid)}).html('전표생성').prop('outerHTML');

            return button;
        },
        codeFormat:function(cellvalue, options, rowObject) {
            //console.log(cellvalue, options, rowObject);

            //console.log(rowObject.colModel);
            if(!options.colModel.code){
                return cellvalue;
            }
            if(typeof(options.colModel.code[cellvalue])!='undefined'){
                cellvalue = options.colModel.code[cellvalue];
            }

            return cellvalue;
        },
        selectFormat:function(cellvalue, options, rowObject) {
            /*if(typeof(options.colModel.code[cellvalue])!='undefined'){
                cellvalue = options.colModel.code[cellvalue];
            }*/
            var resHtml  = UI.getHtml(UI.makeSelect(options['name'],options.colModel.code,options.cellvalue,{value:cellvalue}).val(cellvalue));
            //resHtml.find('select')
            //console.log(resHtml);
            return resHtml;
        },
        commaFormat:function(cellvalue, options, rowObject) {

            return cellvalue?lgUI.util.addComma(cellvalue):0;
        },
        dateFormatter : function  ( cellvalue, options, rowObject )
        {
            return (new Date(cellvalue)).format('yyyy-MM-dd');
        },
        trimFormatter : function  ( cellvalue, options, rowObject )
        {
            return $.trim(cellvalue);
        },
        trimFormatter324 : function  ( cellvalue, options, rowObject )
        {
            var tempCellvalue = $.trim(cellvalue);
            if(cellvalue && cellvalue.indexOf('-')==-1){
                tempCellvalue='';
               tempCellvalue+= cellvalue.substring(0,3);
               tempCellvalue+='-';
               tempCellvalue+= cellvalue.substring(3,5);
               tempCellvalue+='-';
               tempCellvalue+= cellvalue.substring(5);
            }
            return $.trim(tempCellvalue);
        },
        trimFormatter426 : function  ( cellvalue, options, rowObject )
        {
            var tempCellvalue = $.trim(cellvalue);
            if(cellvalue && cellvalue.indexOf('-')==-1){
               tempCellvalue='';
               tempCellvalue+= cellvalue.substring(0,4);
               tempCellvalue+='-';
               tempCellvalue+= cellvalue.substring(4,6);
               tempCellvalue+='-';
               tempCellvalue+= cellvalue.substring(6);
            }
            return $.trim(tempCellvalue);
        },
        clearGrid:function(){
            lgUI.grid.thisGrid.jqGrid("clearGridData", true);
        },
        cto :0,
        decodeName : function(encodeName){
            var resName = '';
            for(var ixx=0;ixx<encodeName.length;ixx++){
                try{
                    if(encodeName.charAt(ixx)==(encodeName.charAt(ixx)).toUpperCase()){
                        resName+='_'+encodeName.charAt(ixx);
                    } else {
                        resName+=encodeName.charAt(ixx);
                    }
                } catch(ex){
                 console.log(ex);
                }

            }
            if(!resName) resName = encodeName;

            return resName.toUpperCase();
        },
        searchForm:function(){
            clearTimeout(lgUI.grid.cto);
            lgUI.grid.cto = setTimeout(function(){
                var wrapElement = $('#gridSearch');
                var searchJson = wrapElement.domJson();
                lgUI.grid.searchGrid(searchJson);
                /*
                $.each(searchJson, function(key, value){
                    if ($.trim(value) === '' || $.trim(value) === null || $.trim(wrapElement.find('#'+key).val())==''){
                        //delete searchJson[key];
                    }
                });
                //lgUI.grid.searchGrid(Object.keys(searchJson).length==0?{page:1,rows:15}:searchJson);
                */

            },200);
        },
        searchGrid:function(postData){

            lgUI.grid.thisGrid.setGridParam({
                datatype	: "json",
                postData	: postData,
                loadComplete	: function(data) {
                    try{
                            initPage(lgUI.grid.thisGrid.attr('id'), lgUI.grid.thisGrid.attr('id')+'Paging', true, "TOT");
                            try{
                                lgUI.grid.loadComplete()
                            } catch(ex){

                            }
                        } catch(ex){

                        }
                }
            }).trigger("reloadGrid");
        },
        searchGridForID:function(id,postData){
            /*$("#list").setGridParam({postData: ""});
                 $("#list").setGridParam({url: 'blabla' });
                 var paramObj = $("#list").jqGrid("getGridParam", 'postData');
                 var frmObj = parseQuery($('#frmSearch').serialize().replace(/\+/g,'%20'));
                 paramObj = appendProps(paramObj,frmObj); // 폼의 데이터를 jqGrid 파라메터에 추가한다.
                 $("#list").jqGrid().setGridParam({postData: frmObj });
                 $("#list").trigger("reloadGrid",[{page:1}]);
            * */
            console.log('postData',id,(postData));
            $('#'+id).jqGrid('setGridParam',{
                postData	: postData
            }).trigger("reloadGrid");
        },


        makeGrid:function(id, columns, url, isAutoLoad, postData, attr){
            if(!isAutoLoad) isAutoLoad='';
            if(!attr) attr = {};
            var gridEl =$('#'+id);
            if(gridEl){
                gridEl.jqGrid('GridUnload');
                UI.makeElement('div','',{id:id+'Paging'}).insertAfter(gridEl);
            }
            if(columns.length>0){
                try{

                }catch(e){
                    console.log('grid',e);
                }
                var JSONdata = [];
                if(!JSONdata) JSONdata=[];
                var listOfName= [];
                var dftJson= {};
                var contextItem ={};

                $.each(columns,function(i,v){
                    //console.log(i,columns[i],v['name']);
                    columns[i]['index'] = lgUI.grid.decodeName(v['name']);
                    //columns[i]['index'] = decodeName(v['name']);
                    //listOfName.push(v.label);
                    //dftJson[v.name] = v.label;
                });

				//JSONdata.push(dftJson);
				//console.log(JSONdata);

                var grid = $("#"+id);
                var objGrid =grid.jqGrid($.extend({},{
                    url:url,
                    postData:postData,
                    datatype: "json",
                    jsonReader : {  // 이부분 추가 하셔야 json 쓰시기 편리 합니다.
                        page: "page",
                        total: "total",
                        root: "resultList",
                        records: "record", //function(obj){return obj.length;},
                        repeatitems: false,
                        id: "id"
                    }, // 여기 까지

                    //datatype: "local",
                    //data: JSONdata,

                    mtype: "POST",

                    colNames: listOfName,
                    colModel: columns,

                    rowNum: 25, //한화면로딩
                    loadtext : '로딩중..',
                    loadui: 'disable',
                    viewrecords: true,
                    autowidth: true,
                    shrinkToFit: true,
                    loadonce: false,
                    gridview: true,
                    //sortable: true,
                    height: $(window).height()-360,
                    scroll:0,
                    // pager: '#'+id+'Paging',
                    rowList: [15, 30, 100, 300],
                    rownumbers: true,
                    rownumWidth:40,
                    caption: '',
                    //ondblClickRow: lgUI.event.gridDblClick,
                    onSelectRow: lgUI.event.gridDblClick,
                    loadBeforeSend: function (data) {
                        if(isAutoLoad!=''){
                            this.p.loadBeforeSend = null; //remove event handler
                            return false; // dont send load data request
                        }

                    },

                    //onRightClickRow: function(rowid, iRow, iCol, e){
                    //},
                    loadComplete : function(data){

                        //if(isAutoLoad=='')
                        //$.contextMenu({
                            //selector: '#tabs-grid tr.jqgrow',
                            //items: {

                                //'삭제': {
                                    //name: "삭제",
                                    //icon: '',
                                    //callback: function(itemKey, opt, rootMenu, originalEvent) {
                                        ////this==tr
                                        //var rowId=  (jQuery(this).index());
                                        //var data = lgUI.grid.thisGrid.getRowData(rowId);
                                        ////alert(JSON.stringify(data));
                                        //lgUI.event.delBtnClick(data);
                                        ////if(confirm('해당 문서를 삭제하시겠습니까?')){
                                        ////}
                                    //}
                                //},
                                //'상세보기': {
                                    //name: "상세보기",
                                    //icon: '',
                                    //callback: function(itemKey, opt, rootMenu, originalEvent) {
                                        //var rowId=  (jQuery(this).index());
                                        //lgUI.event.gridDblClick(rowId);
                                    //}
                                //}
                            //}
                        //});
                        try{
                            initPage(id, id+'Paging', true, "TOT");
                        } catch(ex){
                            console.log(ex)
                        }
                        try{
                            lgUI.grid.loadComplete()
                        } catch(ex){
                            
                        }
                    }
                },attr));

				$('.ui-jqgrid .ui-jqgrid-htable th div').each(function(){
					$(this).attr('title',$(this).text());
				});
				lgUI.grid.resizeJqGridWidth(id,'plustabs',true);

                /*
                var width = grid.width();
                grid.jqGrid ('navGrid', '#gridPaging',
                    {edit:false, add:false, del:false, refresh:true, view:false},
                    {},{},{},{multipleSearch:true,overlay:true});

                //grid.jqGrid("columnChooser", {
                //done: function() {
                ////grid.trigger("resize");
                ////setGridWidth
                //grid.jqGrid('setGridWidth', width);
                //}
                //});

                grid.jqGrid('navButtonAdd', '#gridPaging',{
                    caption: "",
                    buttonicon: "ui-icon-calculator",
                    title: "choose columns",
                    onClickButton: function() {
                        lgUI.grid.thisGrid.jqGrid('columnChooser',{done:function(perm){
                                //grid.trigger("resize");
                                grid.jqGrid('setGridWidth', width+15);
                                if (perm)
                                {
                                    this.jqGrid("remapColumns", perm, true);
                                    //this.setGridWidth(w - 30, true);
                                }
                            }});
                    },
                    done:function(){
                        alert();
                    }
                });
                */
            }
            return objGrid;
        },
		resizeJqGridWidth:function(grid_id, div_id,  tf){

			// window에 resize 이벤트를 바인딩 한다.
			$(window).bind('resize', function() {

				// 그리드의 width 초기화
                if($('#'+div_id).find('#' + grid_id).length){
                    var resizeWidth = 0;
                    if($('.boxArea').has($('#'+grid_id)).length>0){
                        resizeWidth = $('.boxArea').has($('#'+grid_id)).width()-2; //jQuery-ui의 padding 설정 및 border-width값때문에 넘치는 걸 빼줌.
                    }else {
                        resizeWidth = $('#'+div_id).width()-2; //jQuery-ui의 padding 설정 및 border-width값때문에 넘치는 걸 빼줌.
                    }

                    $('#'+div_id).find('#' + grid_id).setGridWidth( resizeWidth, tf);

                    // 그리드의 width를 div 에 맞춰서 적용
                    $('#'+div_id).find('#' + grid_id).setGridWidth( resizeWidth , tf); //Resized to new width as per window.

                } else {

                    var resizeWidth = $('.boxArea').has($('#'+grid_id))-2;

                    $('#' + grid_id).setGridWidth( resizeWidth, tf);

                    // 그리드의 width를 div 에 맞춰서 적용
                    $('#' + grid_id).setGridWidth( resizeWidth , tf); //Resized to new width as per window.
                }

			}).trigger('resize');
		},
    },
    ui:{
        cellAttr:function(attr){
            if(!attr) attr= {};
            return $.extend({},{cell:{'class':'cell',label:{'class':'label'},field:{'class':'inp'}}},attr);
        },
        concatHtml : function(){
            var obj = $('<div></div>');
            $.each(arguments,function(i,v){
                if(obj==null){
                    obj.append(v);
                }else {
                    obj.append(v);
                }
            });
            return $(obj.html());
        },
        concat : function(){
            var args = Array.prototype.slice.call(arguments);
            return args.join('');
        },
        concatJoin : function(spacer){
            var args = Array.prototype.slice.call(arguments,1);
            return args.join(spacer);
        },



        makeJson : function(string){
            var resJson = {};
            if(typeof(string)=='string'&& string){
                resJson = $.parseJSON(string);
            } else {
                resJson = string;
            }
            return resJson;
        },


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
        makeBtn : function(html,fn,fa,attr){
            if(!fa) fa = '';
            if(!attr) attr = {};
            if(!fn) fn = null;
            return lgUI.ui.makeElement('button',(fa?'<i class="'+fa+'" style="margin:-0.5em 7px -0.23em -18px;"></i>':'')+html,$.extend({},{type:'button','class':'btn_Sm btn_write'},attr)).click(fn);
        },
        makeSelect : function(name,dft,val,attr){
            if(!attr) attr = {};
            var select = lgUI.ui.makeElement('select','',attr);
            if(dft){
                $.each(dft,function(kk,vv){
                    select.append(lgUI.ui.makeElement('option',vv,{value:(kk)}));
                });
            }
            //console.log(typeof(val[name])!=undefined);
            //console.log(typeof(val),dft,select);
            select.val((typeof(val)!='undefined'?val[name]:val));

            return select;
        },
        makeRadio : function(name,dft,val){
            var id = lgUI.ui.getId(parseInt(Math.random()*26),'mr');
            var div = $('<span class="data"></span>');
            var onlyone = '';
            if(dft){
                $.each(dft,function(k,text){
                    var isbool = false;
                    var id3 = lgUI.ui.getId(parseInt(Math.random()*881),name);
                    if(typeof(val[name])!='undefined') {
                        isbool = val[name]==k;
                    } else {
                        if(val){
                            isbool = k==val;
                        }
                    }
                    div.append(lgUI.ui.makeInput('radio',k,{'class':name,'name':name,id:id3,'checked':isbool}));
                    div.append(lgUI.ui.makeElement('label',text,{title:text,'for':id3,'class':'inp_func'}));
                });
            }
            return div;
        },

        makeCheckbox : function(name,dft,val,gahang){
            var div = $('<span class="data"></span>');
            var id = lgUI.ui.getId(parseInt(Math.random()*37),'mc');
            var id2 = lgUI.ui.getId(parseInt(Math.random()*46),'cr');
            if(dft){
                $.each(dft,function(k,text){

                    var isbool = false;
                    var isbool2 = false;
                    var id3 = lgUI.ui.getId(parseInt(Math.random()*881),name);
                    if(typeof(val[name])!='undefined') {
                        isbool = $.inArray(k,val[name].checkbox)>-1;
                        isbool2 = val[name].radio==k;
                    } else {
                        if(val){
                            isbool = k==val;
                        }
                    }
                    div.append(lgUI.ui.makeInput('checkbox',k,{'class':name,'name':name,id:id3,'checked':isbool}));
                    div.append(lgUI.ui.makeElement('label',text,{'for':id3,'class':'inp_func'}));

                });
            }
            return div;
        },
        makeInput : function(type,val,attr){
            var el = null;
            var nodeName = 'input';

            if(typeof(type)=='undefined'){
                type = 'text';
            }
            var attr = $.extend({},attr,{'type':type});
            if(nodeName=='input'){
                el = $('<'+nodeName+' />');
            }
            if(val){
                el.val(val);
            }
            if(attr){
                el.attr(attr);
            }
            return el;
        },
        makeTextarea : function(name,val,attr){
            var el = null;
            var nodeName = 'textarea';

            var attr = $.extend({},attr,{});
            el = $('<'+nodeName+'></'+nodeName+'>');
            if(val){
                el.val(val);
            }
            if(attr){
                el.attr(attr);
            }
            return el;
        },
        makeType:function(type,name,val,attr,jArray){
            var tempElement = null;
            var addClass='';
            if(type.indexOf('.')>-1){
                addClass = type.substring(type.indexOf('.')+1);
                type =type.substring(0,type.indexOf('.'));
                if(addClass=='display' || addClass=='img'){
                    $.extend(attr['field'],{disabled:'disabled'});
                }
            }
            switch(type){

                case 'select':
                    tempElement = lgUI.ui.makeSelect(name,jArray,val,$.extend({},{'class':'inp select2_group '+addClass,name:name,id:name},attr['field']));
                    break;
                case 'checkbox':
                    tempElement = lgUI.ui.makeCheckbox(name,jArray,val,$.extend({},{'class':'inp '+addClass,name:name,id:name},attr['field']));
                    break;
                case 'radio':
                    tempElement = lgUI.ui.makeRadio(name,jArray,val,$.extend({},{'class':'inp '+addClass,name:name,id:name},attr['field']));
                    break;
                case 'calendar':
                case 'date':
                    type = 'text';
                    tempElement = lgUI.ui.makeInput(type,val,$.extend({},{'class':'inp datepicker '+addClass,name:name,id:name},attr['field']));
                    break;
                case 'month':
                    type = 'text';
                    tempElement = lgUI.ui.makeInput(type,val,$.extend({},{'class':'inp monthpicker '+addClass,name:name,id:name},attr['field']));
                    break;

                case 'textarea':
                    tempElement = lgUI.ui.makeTextarea(name,val,$.extend({},{'class':'inp '+addClass,name:name,id:name},attr['field']));
                    break;
                case 'display':
                case 'disable':
                case 'disabled':
                    tempElement = lgUI.ui.makeInput(type,val,$.extend({},{'class':'inp display',name:name,id:name,disabled:'disabled'},attr['field']));
                    break;
                case 'html':
                case 'element':
                    tempElement = lgUI.ui.makeElement('div',(val?val:''),$.extend({},{'class':'inp '+addClass,name:name,id:name},attr['field']));
                    break;
                case 'none' :
                    tempElement = "";
                    break;
                case 'text':
                case 'file':
                case 'hidden':
                case 'password':
                default:
                    tempElement = lgUI.ui.makeInput(type?type:'text',val,$.extend({},{'class':'inp '+addClass,name:name,id:name},attr['field']));
                    break;
            }
            return tempElement;
        },
        makeTr:function(type,name,label,attr,jArray,val){
			if(!val) val='';
			if(!attr) attr={cell:{},label:{},field:{}};
			var addClass='';
			var oriType = type;
			if(type.indexOf('.')>-1){
				addClass = type.substring(type.indexOf('.')+1);
				type =  type.substring(0,type.indexOf('.'));
			}
			var resObj = $('<tr><th class="bdnoneL"></th><td class="bdnoneR"></td></tr>');
			resObj.find('th').html(lgUI.ui.makeElement('label',lgUI.ui.makeElement('span',label,{'class':'labelx '+addClass,title:label}),
				$.extend({},{'for':name,'type':type},attr['label'])));
			resObj.find('td').append(lgUI.ui.makeType(oriType,name,val,attr,jArray));
			return resObj;

		},
		makeTd:function(type,name,label,attr,jArray,val){
			if(!val) val='';
			if(!attr) attr={cell:{},label:{},field:{}};
			var addClass='';
			var oriType = type;
			if(type.indexOf('.')>-1){
				addClass = type.substring(type.indexOf('.')+1);
				type =  type.substring(0,type.indexOf('.'));
			}
			var resObj = $('<tr><th class="bdnoneL"></th><td class="bdnoneR"></td></tr>');
			resObj.find('th').html(lgUI.ui.makeElement('label',lgUI.ui.makeElement('span',label,{'class':'labelx '+addClass,title:label}),
				$.extend({},{'for':name,'type':type},attr['label'])));
			resObj.find('td').append(lgUI.ui.makeType(oriType,name,val,attr,jArray));
			return resObj.find('th,td');

		},
        makeCell:function(type,name,label,attr,val,jArray){
            if(!val) val='';
            if(!attr) attr={cell:{},label:{},field:{}};
            var oriType = type;

            var addClass='';
            if(type.indexOf('.')>-1){
                addClass = type.substring(type.indexOf('.')+1);
                type =  type.substring(0,type.indexOf('.'));
            }
            return lgUI.ui.makeElement('div',lgUI.ui.makeElement('label',$('<span></span>').html(label),$.extend({},{'title':label,'for':name,'class':addClass,'type':type},attr['label'])),$.extend({},{'class':'cell'},attr['cell'])).append(lgUI.ui.makeType(oriType,name,val,attr,jArray));

        },
        makeCellBtn:function(html,fn,fa,attr){
            if(!attr) attr=  {};
            var el = lgUI.ui.makeElement('div',lgUI.ui.makeBtn(html,$.extend({},{'class':'btn_normal btn_save'},attr),fa),$.extend({},{'class':'cell'},attr['cell']));
            if(!fn) fn=  el.click(fn);

            return el
        },
        makeElement:function(nodeName,val,attr){
            var el = null;
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

            return el;
        },
        getHtml:function(el){
            return el.clone().wrapAll("<div/>").parent().html();
        }
    },
    DB:{

    },
    fileUpload:{
        el:null,

        ui_multi_add_file:function (id, file)
        {
          var template = $('#files-template').text();
          template = template.replace('%%filename%%', file['itafOrFile']?file['itafOrFile']:file['ibafOrFile']);

          template = $(template);

          template.find('i').click(function() {
            if(confirm('해당 파일을 삭제하시겠습니까')){
              $('.media').has($(this)).remove();
            }
          });
          template.prop('id', 'uploaderFile' + id);
          template.data('file-id', id);

          template.append(UI.makeInput('hidden',JSON.stringify(file),{'class':'uploadData'}));

          template.find('.text-muted').html('업로드 완료');

          $('#files').find('li.empty').fadeOut(); // remove the 'no files yet'
          $('#files').prepend(template);
        },
        ToJson:function(){
            var fileList = $('#dialog ul#files');
            var arr = [];
            var fileData = '';
            if(lgUI.fileUpload.el) {
                lgUI.fileUpload.el.find('.uploadFileNames').empty();
            }
            fileList.find('.uploadData').each(function(){
                var arrData = $.parseJSON($(this).val());

                arr.push(arrData);
                fileData = UI.makeElement('span',arrData['itafOrFile']?arrData['itafOrFile']:arrData['ibafOrFile']).data(arrData);
                if(lgUI.fileUpload.el) {
                    lgUI.fileUpload.el.find('.uploadFileNames').append(fileData);
                }
            });
            if(lgUI.fileUpload.el){
                lgUI.fileUpload.el.find('#itafFile').val(JSON.stringify(arr));
            }

        },
        open:function(){
            if($(this).length>0){

                lgUI.fileUpload.el = $(this).parent();
                var fileList = $('#dialog ul#files');
                var itafFile = lgUI.fileUpload.el.find('#itafFile').val();
                if(itafFile){
                    fileList.empty();
                    var jArray = $.parseJSON(itafFile);
                    $.each(jArray,function(i,v){
                        lgUI.fileUpload.ui_multi_add_file(UI.getId(),v);
                    });
                } else {
                    fileList.empty();
                }
                // var divWrap = UI.makeElement('div','<div class="g_column">' +
                //     '<div id="drag-and-drop-zone" class="dm-uploader p-5 text-center">' +
                //     '<h3 class="mb-5 mt-5 text-muted">Drag &amp; drop Images here</h3>' +
                //     '</div><div class="btn btn-primary btn-block mb-5">' +
                //     '<span>Open the file Browser</span>' +
                //     '<input type="file" title="Click to add Files" />' +
                //     '</div>' +
                //     '</div>' +
                //     '<div class="g_column">' +
                //     '<div class="card h-100"><div class="card-header">File List</div></div>' +
                //     '<ul class="list-unstyled p-2 d-flex flex-column col" id="files"><li class="text-muted text-center empty">No files uploaded.</li></ul>' +
                //     '</div>',{'class':'row'});
                // //$('#dialog').html(divWrap);

                $.blockUI({
                    message : $('#dialog'),
                    css : {
                        width : 800,
                        height : 300,
                        //top : top,
                        //left : left,
                        // position : 'fixed'
                        position : 'absolute',
                        close : true
                        // ,backgroundColor:false TODO-lms0123: 레이어 팝업 드래그???
                    }
                })
            }
        }
    },
    Popup:{
        action:function(){
            $.blockUI({
                message : divWrap,
                css : {
                    width : width,
                    height : height,
                    //top : top,
                    //left : left,
                    // position : 'fixed'
                    position : 'absolute',
                    close : true
                    // ,backgroundColor:false TODO-lms0123: 레이어 팝업 드래그???
                }
            })
        },
        open:function(message,title,btns,width,height,top,left){
            $('.popupUniq').remove();
            if(!width) width=  600;
            if(!height) height=  300;
            var divWrap = UI.makeElement('div','',{'class':'popupUniq'});
            divWrap.adds(

                UI.makeElement('div','<i class="fa fa-window-close" aria-hidden="true" onclick="javascript:LayerPopupUtils.close()"></i><div style="clear:both"></div>',{'class':'closeBtn',style:'float:right; padding:5px;z-index:100'})
                ,UI.makeElement('div','',{style:'clear:both'})
                ,UI.makeElement('div',message,{'class':'popupContents pdt20 detail_tb '}).prepend(UI.makeElement('h1',title))
                ,UI.makeElement('div','',{'class':'btns pdt10 tc'})
            );
            if(btns){
                $.each(btns,function(i,btn){
                    divWrap.find('.btns').append(btn);
                });
            }



            $.blockUI({
                message : divWrap,
                css : {
                    width : width,
                    height : height,
                    //top : top,
                    //left : left,
                    // position : 'fixed'
                    position : 'absolute',
                    close : true
                    // ,backgroundColor:false TODO-lms0123: 레이어 팝업 드래그???
                }
            });
            lgUI.event.datepicker(divWrap);
        },close:function(){

        }
    },
    Layout:{
        init:function(_detailName, _insertUrl, _updateUrl, _delUrl, _callBack) {//170915
            var detailName  = _detailName   ? _detailName   : "";
            var insertUrl      = _insertUrl       ? _insertUrl       : "";
            var updateUrl      = _updateUrl       ? _updateUrl       : "";
            var delUrl      = _delUrl       ? _delUrl       : "";
            lgUI.tab.thisTab= $( '#plustabs' ).tabs();

            lgUI.event.editBtnClick=function(){
                
                if($('.addTab div:contains("신규등록")').length>0){
                    alert('신규등록 탭이 있습니다.\r신규등록 끝난후 데이터 수정을 해주세요.');
                    return false;
                }
                if($('ul.addTab > li.EDIT').length>0){
                    alert('이미수정중인 탭이 있습니다.\r수정작업이 끝난 후 신규등록을 해주세요.');
                    return false;
                }
                var pageContent = $('.pageContent').has($(this));
                pageContent.empty();
                var rowData = lgUI.DB[pageContent.data('rowid')];
                $('ul.addTab > li.ui-tabs-active').addClass('EDIT');
                lgUI.event.gridDblClickAfter(pageContent,rowData,lgUI.STATE.EDIT);

                $("#mode",pageContent).val("MOD");
                /** 셀렉트박스 */
                $(".select2_group",pageContent).select2({width:'100%'});
            }
            lgUI.event.cancleBtnClick=function(existsformJson){
                if(confirm('작성을 취소하시겠습니까?\r확인또는 Yes를 누르면 탭이 닫힙니다.')){
                    var pageContent = $('.pageContent').has($(this));
                    $('#plustabs > ul li').eq(pageContent.index()).find('.ui-icon-close').click();
                }
            }
            lgUI.event.gridDblClick=function(rowid){
                console.log(lgUI.grid.thisGrid.getRowData(rowid));
                var rowData = lgUI.grid.thisGrid.getRowData(rowid);
                var tabTitle  =  String.format('[{0}] {1}',rowData[detailName],'상세');
                var rowDataId = lgUI.ui.getId();
                lgUI.DB[rowDataId] = rowData;
                lgUI.tab.addTab(tabTitle,rowDataId);;

                var pageContent = $('.pageContent:last');
                lgUI.event.gridDblClickAfter(pageContent,rowData,lgUI.STATE.INFO);

            }
            lgUI.event.subGridDblClick=function(grid,tab,subdetailName,rowid){

                var rowData = grid.getRowData(rowid);
                var tabTitle  =  String.format('[{0}] {1}',rowData[subdetailName],'상세');
                var rowDataId = lgUI.ui.getId();
                lgUI.tab.addSubTab(tab,tabTitle,rowDataId);;
                var pageSubContent = $('.pageSubContent:last');
                console.log(rowData);
                lgUI.event.subGridDblClickAfter(pageSubContent,rowData,lgUI.STATE.INFO);

            }

            lgUI.event.gridNewForm=function(rowId){
                var tabTitle  =  String.format('[{0}]','신규등록');
                if($('.addTab div:contains("신규등록")').length>0){
                    alert('이미신규등록 탭이 있습니다.');
                    return false;
                }
                if($('ul.addTab > li.EDIT').length>0){
                    alert('이미수정중인 탭이 있습니다.\r수정작업이 끝난 후 신규등록을 해주세요.');
                    return false;
                }
                lgUI.tab.addTab(tabTitle);

                var pageContent = $('.pageContent:last');
                lgUI.event.gridDblClickAfter(pageContent,{},lgUI.STATE.NEW);

                $("#mode",pageContent).val("NEW");
                /** 셀렉트박스 */
                $(".select2_group",pageContent).select2({width:'100%'});
            }

            lgUI.event.delBtnClick=function(existsformJson){
                var pageContent = $('.pageContent').has($(this));

                if(confirm('해당 정보를 삭제하시겠습니까?')){
                    var pageContent = $('.pageContent').has($(this));

                    if(existsformJson['type']=='click'){
                        existsformJson = null;
                    }
                    var formJson = existsformJson?existsformJson:pageContent.domJson();
                    var url = delUrl
                    pageContent.attr('action',url);
                    window.AjaxHandler.submit(pageContent);
                }
            }

            lgUI.event.submitAction = function(form) {

                var form = $(form);
                var formJson = form.domJson();
                var url = insertUrl;
                if(confirm('해당 정보를 저장하시겠습니까?')) {
                    if (formJson['mode'] == 'MOD') {
                        url = updateUrl;
                    }
                    if (typeof rules == 'function') {
                        if (form.find('#rules').length == 0) {
                            form.append(UI.makeInput('hidden', JSON.stringify(rules()), {id: 'rules', name: 'rules'}));
                        }
                    }
                    form.attr('action', url);
                    console.log(form);

                    window.AjaxHandler.submit(form);
                }
            }

            lgUI.event.saveBtnClick=function(){
                var pageContent = $('.pageContent').has($(this));
                //pageContent.validate().reset();
                $.validator.addMethod("regex", function(value, element, regexpr) {
                    return regexpr.test(value);
                });

                if(pageContent.find('textarea').length>0){
                    try{
                        pageContent.find('textarea').each(function(){
                                oEditors.getById[$(this).attr('idstr')].exec('UPDATE_CONTENTS_FIELD', []);
                        })
                    } catch(ex) {

                    }
                }
                pageContent.validate({
                    debug       : false,
                    onfocusout  : false,
                    rules       : typeof rules      == 'function' ? rules(pageContent) : {},
                    messages    : typeof messages   == 'function' ? messages() : {},
                    errorPlacement: function(error, element) {
                        // do nothing
                        console.log('errorPlacement',error, element);
                    },
                    invalidHandler: function(form, validator) {
                        console.log('invalidHandler',form, validator);
                        var errors = validator.numberOfInvalids();
                        if (errors) {
                            var errMsgs = [];

                            if(validator.errorList.length>0){
                                $.each(validator.errorList,function(i,v){
                                    var msg = $(validator.errorList[i].element,pageContent).parent().prev().html();
                                    if(msg){
                                        msg = msg.replace('*','');
                                    }
                                    errMsgs.push(msg+" 은(는) " +validator.errorList[i].message);
                                    $(validator.errorList[i].element,pageContent).css('background','#E2A593');
                                    setTimeout(function(){
                                        $(validator.errorList[i].element,pageContent).css('background','');
                                    },1700);
                                })
                                $.toast.config.align = 'right';
                                $.toast(errMsgs.join('<br/>'), {
                                    type: 'danger',
                                    textColor:'#f00',
                                    duration: 1700
                                });
                                //alert(validator.errorList[0].message);
                                validator.errorList[0].element.focus();
                            }
                        }
                    },submitHandler: lgUI.event.submitAction
                });
                pageContent.submit();
            }


            $(window)
                .ajaxStart(function(){
                    BlockUtils.blockStart();
                })
                .ajaxStop(function() {
                    BlockUtils.blockEnd();
                });


        },
        lnbAction:function() {//170915
            var _btn = $('.menuBtn'),
                _lnb = _btn.parents('.lnb');
            _btn.on('click',function() {

                if(_lnb.hasClass('spread')) {
                    _lnb.removeClass('spread');
                    _lnb.find('li').removeClass('open');
                    _btn.find('i').attr('class','fa fa-arrow-right');
                    lgUI.Layout.sameHeight();
                } else {
                    _lnb.addClass('spread');
                    _lnb.find('.active').addClass('open');
                    _btn.find('i').attr('class','fa fa-arrow-left');
                    lgUI.Layout.sameHeight();
                };
            });

            var _list = _lnb.find('.depth > li'); //170920
            _list.each(function() {

                $(this).mouseenter(function() {
                    $(this).addClass('on');
                    if($(this).parents('.lnb').hasClass('spread')) {
                        _lnb.find('.depth > li').removeClass('open');
                        _lnb.find('.depthArrow').removeClass('ambicon-002_arrow_left');
                        $(this).find('.depthArrow').addClass('ambicon-002_arrow_left');
                        $(this).addClass('open');
                    };
                }).mouseleave(function() {
                    $(this).removeClass('on');
                    _lnb.find('.depth > li').removeClass('open');
                    _lnb.find('.depthArrow').removeClass('ambicon-002_arrow_left');
                });

                if($(this).children('.depth_two').length > 0) {
                    $(this).children('a').append('<i class="depthArrow fa fa-angle-right"></i>');
                };
            });

            var _icon = _lnb.find('.fa20');//170929
            _icon.on('click', function() {
                if(_lnb.hasClass('spread')) {
                    _lnb.removeClass('spread');
                    _lnb.find('li').removeClass('open');
                    _btn.find('i').removeClass('ambicon-002_arrow_left');
                    lgUI.Layout.sameHeight();
                } else {
                    _lnb.addClass('spread');
                    _btn.find('i').addClass('ambicon-002_arrow_left');
                    lgUI.Layout.sameHeight();
                    $(this).parents('li').addClass('open');
                };
            });

            $('.header, .content').on('click', function() {
                _lnb.removeClass('spread');
                _lnb.find('li').removeClass('open');
                _btn.find('i').removeClass('ambicon-002_arrow_left');
                lgUI.Layout.sameHeight();
            }).focusin(function() {
                _lnb.removeClass('spread');
                _lnb.find('li').removeClass('open');
                _btn.find('i').removeClass('ambicon-002_arrow_left');
                lgUI.Layout.sameHeight();
            });
        },
        sameHeight:function() {
            var _wrap = $('.contentWrap'),
                _lnb = _wrap.find('.lnb'),
                _content = _wrap.find('.content');
            _lnb.outerHeight('');
            _content.outerHeight('');
            if(_lnb.outerHeight() > _content.outerHeight()) {
                _content.outerHeight(_lnb.outerHeight());
            } else {
                _lnb.outerHeight(_content.outerHeight());
            };
            _content.height('100%');
        },
        containerH:function() {
            var win = $(window),
                header = $('.header'),
                container = $('#contentWrap'),
                contH;
            contH = win.outerHeight() - header.outerHeight();
            container.css('min-height', contH + 'px');
        },
        finder:function(){
            $('.style-finder .toggler').on('click', function(event){
				event.preventDefault();
				$(this).closest('.style-finder').toggleClass('opened');
			});
        }
    },
    finder : {
        buildingO :0,
        clientO :0,
        clientFn:function(iclGubun,thisObj){
            var sFinder = $('.style-finder').has(thisObj);
            clearTimeout(lgUI.finder.clientO);
            lgUI.finder.clientO = setTimeout(function(){
                if(sFinder.find('#iclCompanyName').length)
                {
                    var iclCompanyName = $.trim(sFinder.find('#iclCompanyName').val());
                    var wrap =$('#plustabs > .ui-tabs-panel').eq($('#plustabs > ul li.ui-tabs-active').index());
                    var icmCompanyCode = wrap.find('#icmCompanyCode').val();
                    if(icmCompanyCode && iclCompanyName){
                        $.post('/rest/client/clientList',{iclGubun:iclGubun,icmCompanyCode:icmCompanyCode,iclCompanyName:iclCompanyName},function(jsonResult){
                            codes.iclCompanyCode = lgUI.util.listToObject(jsonResult.resultList,'iclCompanyCode','iclCompanyName',true);
                            wrap.find('#iclCompanyCode').parent().addItem(codes.iclCompanyCode);
                        });
                    } else {
                        if(iclGubun=='C'){

                            alert('임대인코드 선택과 고객명을 입력해주세요');
                        } else {
                            alert('임차인코드 선택과 구매처명을 입력해주세요');
                        }
                    }
                }
            },350);
        },
        clientvFn:function(){
            lgUI.finder.clientFn('V',$(this))
        },
        clientcFn:function(){
            lgUI.finder.clientFn('C',$(this))
        },
        buildingFn:function(){
            var sFinder = $('.style-finder').has($(this));
            clearTimeout(lgUI.finder.buildingO);
            lgUI.finder.buildingO = setTimeout(function(){
                if(sFinder.find('#ibmBuildingName').length)
                {
                    var ibmBuildingName = $.trim(sFinder.find('#ibmBuildingName').val());
                    var ibmLocation = $.trim(sFinder.find('#ibmLocation').val());
                    var wrap =$('#plustabs > .ui-tabs-panel').eq($('#plustabs > ul li.ui-tabs-active').index());
                    var param = {ibmBuildingName:ibmBuildingName};
                    if(ibmLocation){
                        $.extend(param,{ibmLocation:ibmLocation});
                    }
                    $.post('/rest/building/buildingList',param,function(jsonResult){
                        codes.ibmBuildingCode = lgUI.util.listToObject(jsonResult.resultList,'ibmBuildingCode','ibmBuildingName',true);
                        wrap.find('#ibmBuildingCode').parent().addItem(codes.ibmBuildingCode);
                    });
                }
            },350);

        },
        run:function(){
            this.el.show();
            var fel = this.el.find('.finder');
            $.each(this.item,function(i,v){
                fel.append(v);
            });
        }
    },
    util:{
        addComma:function addCommas(nStr) {
            nStr += '';
            x = nStr.split('.');
            x1 = x[0];
            x2 = x.length > 1 ? '.' + x[1] : '';
            var rgx = /(\d+)(\d{3})/;
            while (rgx.test(x1)) {
                x1 = x1.replace(rgx, '$1' + ',' + '$2');
            }
            return x1 + x2;
        },
        listToObject:function(list,key,val,isBlank,defineKey){
            if(!defineKey) defineKey='';
            if(!list)  list= [];
            if(!isBlank) isBlank = false;
            if(typeof(list)=='string'){
                list = JSON.parse(list);
            }
            var res = {};
            var resOrigi = {};
            if(isBlank){
                res[""] = '::선택하세요::';
            }
            if(list.length>0){

                $.each(list,function(i,v){
                    res[v[key]] = v[val];
                    resOrigi[v[key]] = v;
                });
            }
            lgUI.codes[(defineKey?defineKey:(key+'Orig'))] = resOrigi;
            return res;
        },
        array:{
            hasOwn : Object.prototype.hasOwnProperty,

            getKeys:function(obj) {
                var keys = [], name;
                for (name in obj) {
                    if (this.hasOwn.call(obj, name)) {
                        keys.push(name);
                    }
                }
                return keys;
            },


            //합집합
            union:function (a, b) {
                var tmp={}, res=[];
                for(var i=0;i<a.length;i++) tmp[a[i]]=1;
                for(var i=0;i<b.length;i++) tmp[b[i]]=1;
                for(var k in tmp) res.push(k);
                return res;
            },
            //교집합
            intersect:function(a, b) {
                var tmp={}, res=[];
                for(var i=0;i<a.length;i++) tmp[a[i]]=1;
                for(var i=0;i<b.length;i++) if(tmp[b[i]]) res.push(b[i]);
                return res;
            },
            //차집합
            array_diff:function(a, b) {
                var tmp={}, res=[];
                for(var i=0;i<a.length;i++) tmp[a[i]]=1;
                for(var i=0;i<b.length;i++) { if(tmp[b[i]]) delete tmp[b[i]]; }
                for(var k in tmp) res.push(k);
                return res;
            },
            //대칭차
            sym_diff:function(a, b) {
                var tmp={}, res=[];
                for(var i=0;i<a.length;i++) tmp[a[i]]=1;
                for(var i=0;i<b.length;i++) { if(tmp[b[i]]) delete tmp[b[i]]; else tmp[b[i]]=1; }
                for(var k in tmp) res.push(k);
                return res;
            }
        },
        number:{
            // 숫자만 입력
            isNum:function(obj){
                var word = obj.value;
                var str = "1234567890";
                for(i=0; i<word.length; i++){
                    if(str.indexOf(word.charAt(i))< 0){
                        alert("숫자만 입력 가능합니다.");
                        obj.value="";
                        obj.focus();
                        return false;

                    }

                }

            }
        },
        date:{
            //날짜 계산
            fnGetDate:function(s, i){
                var dateSplit = s.split('-');
                var newDt = new Date(dateSplit[0], dateSplit[1]-1, dateSplit[2]);
                newDt.setDate( newDt.getDate() + i );
                return this.converDateString(newDt);
            },
            //날짜 계산
            converDateString:function(dt){
                return dt.getFullYear() + "-" + this.addZero(dt.getMonth()+1) + "-" + this.addZero(dt.getDate());
            },
            //날짜 계산
            addZero:function(i){
                var rtn = i + 100;
                return rtn.toString().substring(1,3);
            },
            //날짜 계산
            fnGetDateM:function(s, i){
                var newDt = new Date(Date.UTC(s.split('-')[0],s.split('-')[1],s.split('-')[2]));

                newDt.setMonth( newDt.getMonth() + i );

                return this.converDateStringM(newDt);
            },


            //날짜 계산
            converDateStringM:function(dt){
                return dt.getFullYear() + "-" + this.addZero(eval(dt.getMonth()+1));
            },
            //날짜 계산
            addZero:function(i){
                var rtn = i + 100;
                return rtn.toString().substring(1,3);
            },
            //null 일때 표시하는 문자
            displayNull:function (){
                return "-";
            },
            //null 일때 표시하는 문자
            displayNullValue:function(obj){
                return (obj  == null ? this.displayNull() : obj);

            },
            getDate:function(date){

                var t = null
                if(date){
                    t = lgUI.util.date.strDate(date);
                } else {
                    t = (new Date(Date.now()))
                }
                return t;

            },
            //Ymd를 date로
            strDate:function(dt){
                if(dt.length == 8){
                    var dateString  = dt;
                    var year        = dateString.substring(0,4);
                    var month       = dateString.substring(4,6);
                    var day         = dateString.substring(6,8);

                    var date        = new Date(year, month-1, day);
                    return date;
                }
            }
        }
    }

}
var UI = lgUI.ui;
var initLayout = function(flag){
    lgUI.Layout.lnbAction();
    lgUI.Layout.containerH();
    lgUI.Layout.sameHeight();
    lgUI.Layout.finder();
    var wh =  $(window).height();
    $('input').attr('autocomplete', 'off');
    $('#tabs-grid .jqgirdTable').each(function(){
        $(this).setGridHeight(wh-300);
    })
}
$(document).ready(function(){

     initLayout();
});
var cto = 0;
var jqGridResize= function(){
	var grid = $('.ui-jqgrid');
	grid.each(function() {
		if($(this).length > 0) {
			var _table = $(this).find('.jqgirdTable');
			console.log("$(this).parent().width()");
			console.log($(this).parent().width());
			_table.jqGrid().setGridWidth($(this).parent().width());
		}
	});
}
$(window).scroll(function() {
    clearTimeout(cto);
    cto = setTimeout(function(){
        lgUI.Layout.containerH();
        lgUI.Layout.sameHeight();
        $('input').attr('autocomplete', 'off');
        $('.jqgirdTable').each(function(){
            //$(this).setGridWidth($('.ui-tabs').has($(this)).width());
        });
    },200);
});
$(window).resize(function() {
    clearTimeout(cto);
    var wh =  $(window).height();
    //cto = setTimeout(function(){
        lgUI.Layout.containerH();
        lgUI.Layout.sameHeight()
        $('#tabs-grid .jqgirdTable').each(function(){
            var gridSearchHeight = $('#gridSearch').height();
            $(this).setGridHeight(wh-gridSearchHeight-300);
        })
        //jqGridResize();
        // $('.jqgirdTable').each(function(){
        //     $(this).setGridWidth($('.g_column').has($(this)).length?$('.g_column').has($(this)).width():$('.ui-tabs-panel').has($(this)).width());
        // });
    //},200);
});

//var isBlock = true;
var popupWidth = '';
var popupHeight = '';

var BlockUtils = {
    isBlock : true,
    blockOption     : {
        message: '<img src="/asset/images/loading.gif"/>',
        css    : { position: 'fixed', padding: '10px'}
       /* onBlock: function () {
            $(window).trigger('resize');
            $('.blockUI.blockMsg.blockElement').css({
                'top'       : '50%',
                'left'      : '50%',
                'marginTop' : '-35px',
                'marginLeft': '-35px'
            });
        }*/
    },
    innerBlockOption: {
        message: '<img src="/asset/images/loading.gif" />',
        css    : { position: 'absolute', padding: '20px'}
    },
    blockStart      : function () {
        if ( typeof BlockUtils.isBlock == 'undefined' || BlockUtils.isBlock == true ) {
            popupWidth = '';
            popupHeight = '';

            if ( $('.blockUI.blockMsg.blockPage').length > 0 ) {
                $('.blockUI.blockMsg.blockPage').block(this.innerBlockOption);
            }
            else {
                $('body').block(this.blockOption);
            }
        }
    },
    blockEnd        : function () {
        BlockUtils.isBlock = true;
        if ( $('.blockUI.blockMsg.blockPage').length > 0 ) {
            $('.blockUI.blockMsg.blockPage').unblock();
        }
        else {
            $('body').unblock();
        }
    }
};

