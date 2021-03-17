<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<style>
/*     .tbxxx {font-size:10px !important;} */
/*     .tbxxx * {font-size:10px !important;} */
/*     .btn_st1.on {background:#ff0} */
</style>
<body class="question_body">
<div class="wrapper">
    <header class="header">
        <h1 class="logo">AIGo</h1>
<!--         <strong class="progressing typing ai_text">AI Progressing…</strong> -->
		<div class="typewriter">
			<h1 class="progressing">AI Progressing...</h1>
		</div>
    </header>
    <div class="container btm0">
        <div class="question_conts question_common1">
            <h2 class="q_number">01.</h2>
            <div class="q_sec">
                <img class="q_img" src="" alt="문제 이미지">
<%--                <textarea class="dlog" style="position: absolute;right:10px;top:0px;z-index:10;height:99%;width:50%;font-size:10px;opacity:0.5">--%>
<%--                </textarea>--%>
            </div>
            <div class="dontknow">
                <a href="#" class="fR btn btn_st1">잘 모르겠어요</a>
            </div>
        </div>
        <section class="progress_cont">
            <div class="progress_div"><span class="bar" style="width:0%"></span><span class="text">진단 진행률 0%</span>
            </div>
            <div class="expect_div">
                <dl class="fc">
                    <dt>예상 백분위</dt>
                    <dd>산출중</dd>
                </dl>
                <dl>
                    <dt>예상 등급</dt>
                    <dd><em class="grade">3등급</em></dd>
                </dl>
            </div>
        </section>

        <section class="answer_submit_cont">
            <div class="number_div qstType S">
                <button type="button">1</button>
                <button type="button">2</button>
                <button type="button">3</button>
                <button type="button">4</button>
                <button type="button">5</button>
            </div>
            <div class="text_div qstType A">
                <input type="text" name="" class="ipt_answer" placeholder="정답을 입력하세요">
            </div>
            <button type="submit" class="btn btn_submit">답안제출</button>
        </section>
    </div><!-- //container -->

</div>


<script type="text/javascript">
    //${temp_id};
    function getRandomInt(min, max) { return Math.floor(Math.random() * (max - min + 1)) + min; }
    var meGrade = parseInt('${tempGrade}'||'5'); //getRandomInt(1,5);// getRandomInt(1,5);
    if($.trim('${temp_id}')!='0'&& '${temp_id}'!=''){
        $.call('/front/ajax/aigo/furs/checkState',{},function(r){
            if(r.resultData){

                if($.trim(r.resultData['aiComplit'])==''){
                    meGrade = r.resultData['tempGrade'];
                    alert('사전질문을 완료 하지 않으셨습니다\n사전질문을 완료후 이용해주시기 바랍니다.');
                    location.herf='/front/furs/step03';
                    return false;
                } else {
                    location.herf='/front/main';
                }
            }
        })

    }

    function clog(msg){
        console.log(msg);
        $('.dlog').val(msg+'\r\n'+$('.dlog').val());
    }

    //numSelect();
    var currQst = {};
    var userI = 0;
    var userLevelI = 0;
    var userData = [];
    var realData = [];
    var qstData = {};
    //정책
    //
    var jc = {
        'X2': 2 //오답2개면끝
        , 'S2': '2' //정답이2개맞춰도 끝
        , 'NEXT': 'NEXT' //다음클라스
        , 'S2NEXT': 'S2NEXT' //다음클라스
        , 'CLOSE': 'CLOSE' //다음클라스
    }
    var jc = {X: 2};   //두개틀리면 체크
    var jc = {X: 2};   //두개틀리면 체크
    var jcUp = {O: 2, X: 2}; //두개최소2개이상

    //모의진단    초반 7개를 무조건 풀고 클라스에 해당되는것을 기준으로 다음스탭을 바라본다.

    var moJin = {
        //출제그룹 :  CLASS1 [초반,중반,후반]
        '1': {
            CLASS1: {H1: [0, 5], H2: [6, 7]}
            , H1: 'CLOSE'
            , H2: {H2: 'X2', H3: 'S2NEXT'}
            , H3: {H2: 'X2', H3: 'S2'}
        },
        '2': {
            CLASS1: {H1: [0, 3], H2: [4, 5], H3: [6, 7]}
            , H1: {H1: 'X2', H2: 'S2'}
            , H2: 'CLOSE'
            , H3: {H4: 'X2', H5: 'S2NEXT'}
        },
        '3': {
            CLASS1: {H1: [0, 3], H2: [4, 5], H3: [6, 7]}
            , H1: {H1: 'X2', H2: 'S2'}
            , H2: 'CLOSE'
            , H3: {H4: 'X2', H5: 'S2NEXT'}
            //,S2NEXT:{}
        },
        '4': {
            CLASS1: {H1: [0, 3], H2: [4, 5], H3: [6, 7]}
            , H1: {H1: 'X2', H2: 'S2'}
            , H2: 'CLOSE'
            , H3: {H4: 'X2', H5: 'S2'}
        },
        '5': {
            CLASS1: {H1: [0, 1], H2: [2, 3], H3: [4, 7]}
            , H1: {H1: 'X2', H2: 'S2'}
            , H2: {H2: 'X2', H3: 'S2'}
            , H3: 'CLOSE'
        },
    }
    var moJinValue = {
        //출제그룹 :  CLASS1 [초반,중반,후반]
        '1': {X: 2, H1: 1, H2: 2, H3: 3},
        '2': {X: 2, H1: 1, H2: 2, H3: 3, H4: 4, H5: 5},
        '3': {X: 2, H1: 2, H2: 3, H3: 4, H4: 5},
        '4': {X: 2, H1: 3, H2: 4, H3: 5},
        '5': {X: 2, H1: 3, H2: 4, H3: 5},
    }

    function oxCount(data, typeOX) {
        if (!typeOX) typeOX = 'O'
        var rCount = 0;
        $.each(data, function(k, v) {
            if (v == typeOX) {
                rCount++;
            }
        });
        return rCount;
    }

    function qstSet(data, num,eq) {
        currQst = data[eq];
        console.log(currQst);
        if(currQst.hasOwnProperty("filelist")){
            console.log(currQst.filelist);
            $(".q_img").attr("src", "/common/siteImgView?safSeq=" + currQst.filelist.safSeq);
        }
        $('.qstType').hide();
        if(currQst['qstType']=='S'){
            if(currQst['qstTypeNum']=='5'){
                $('.answer_submit_cont .number_div button').css('width','20%');
                $('.answer_submit_cont .number_div button:last').show();
            } else if(currQst['qstTypeNum']=='4'){
                $('.answer_submit_cont .number_div button:last').hide();
                $('.answer_submit_cont .number_div button').css('width','25%');
            }
        }
        $('.qstType.'+currQst['qstType']).show();
        $('.q_number').html(plus.lpad((eq + 1),2,'0') +'. '+(currQst['qstName']=='NULL'?'모의진단문제':currQst['qstName']) +":<span style=\"font-size:10px !important;\">"+currQst['qstKey']+':'+currQst.filelist.safOrFile+'</span>' );
        if(!$('.eieiei').length){
            $('.q_number').append(plus.makeElement('span',' level:'+currQst['levValue'],{'class':'eieiei','style':"font-size:10px !important;"}))
        } else {
            $('.eieiei').html('level:'+currQst['levValue'])
        }
        //현재문제업데이트

        if(changeLevel == "5") { $('.grade').html("1등급"); }
        else if(changeLevel == "4") { $('.grade').html("2등급"); }
        else if(changeLevel == "3") { $('.grade').html("3등급");  }
        else if(changeLevel == "2") { $('.grade').html("4등급");  }
        else if(changeLevel == "1") { $('.grade').html("6등급 이하");  }

        //$('.grade').html(currQst['levValue']);
        console.log('currQst==',eq,currQst['qstType'],currQst);
        $('.ipt_answer').val('');

    }

    var myqst = [[], [], []];
    var myqstlist = [];
    var lastTime = (new Date()).format('yyyy-MM-dd HH:mm:ss');

    var maxCount = 13;
    var changeLevel =0;
    $(document).ready(function() {	
		console.log('${temp_id}');
		
		// 주관식 답안 입력 시 잘 모르겠어요 버튼 클릭 해제
		$('.ipt_answer').on('keyup', function(){
			$('.btn_st1').removeClass('on');
		});
		
	    if($.trim('${temp_id}')=='0' || '${temp_id}'==''){
	        alert('로그인 또는 사전질문후 이용부탁드립니다.');
	        location.href='/front/main';
	    }
    
        $('.tbxxx tr').each(function(){
            console.log($(this).text());
            if(!$.trim($(this).text())){
                $(this).remove();
            }
        })


        var meLevel = 0;
        if (meGrade >= 6 && meGrade <= 9) {
            meLevel = 1;
        } else if (meGrade >= 4 && meGrade <= 5) {
            meLevel = 2;
        } else if (meGrade >= 3 && meGrade <= 3) {
            meLevel = 3;
        } else if (meGrade >= 2 && meGrade <= 2) {
            meLevel = 4;
        } else if (meGrade >= 1 && meGrade <= 1) {
            meLevel = 5;
        }
        changeLevel = meLevel;
        console.log('현재 등급은 ' + meGrade + '이며 출제그룹은 ' + meLevel + '에서 시작');
        clog('현재 등급은 ' + meGrade + '이며 출제그룹은 ' + meLevel + '에서 시작');
        var moJinData = moJin[meLevel];

        $('.btn_st1').click(function() {
//             if($(this).is('.on')){
//                 $(this).removeClass('on');
//             } else {
//                 $(this).addClass('on');
//             }
            $('div.number_div').find('button').removeClass('on');

        })
        $('.number_div button').click(function() {
            $('.btn_st1').removeClass('on');
            $(this).closest('div').find('button').removeClass('on');
            $(this).addClass('on');
        })
        //1단계에서 2단계 바라볼 키
        var classKey = '';
        $('.btn_submit').click(function() {
            var qstValue = '';
            if ($('.btn_st1.on').length > 0) {
                qstValue = '-';
            }
            else {

                if(currQst['qstType']=='S'){
                    if ($(this).closest('div').find('button.on').length > 0) {
                        qstValue = $(this).closest('div').find('button.on').html();
                    }
                } else {
                    qstValue = $.trim($('.ipt_answer').val());
                }
                if($.trim(qstValue)==''){
                    alert('답안을 선택 또는 입력하지 않으셨습니다.');
                    return false;
                }
            }
            var oxQustValue = '';
            if($.trim(currQst['qstValue'])==qstValue){
                oxQustValue ='O';
            } else {
                oxQustValue = 'X';
            }

            var moJinData = moJin[meLevel];



            clog('정답 :  '+oxQustValue);

            $(this).closest('div').find('button.on').removeClass("on");
            $('.btn_st1.on').removeClass("on");

            console.log(currQst);
            var currQst2 = {};
            currQst2['qstId'] = currQst['qstId']+'';
            currQst2['qstKey'] = currQst['qstKey']+'';

            myqstlist.push($.extend({},currQst2,{'oxQustValue':oxQustValue,'qstValue':qstValue,inTime:lastTime,outTime:(new Date()).format('yyyy-MM-dd HH:mm:ss')}));
            lastTime = (new Date()).format('yyyy-MM-dd HH:mm:ss');;
            //## 7문제까지는 1단계
            if (userI < 7) {

                myqst[0].push(oxQustValue);

                //1단계 문제풀이
                var rCount = oxCount(myqst[0], 'O');
                var xCount = oxCount(myqst[0], 'X');

                if((userI+1)==7){

                    console.log('####',moJinData['CLASS1']);
                    $.each(moJinData['CLASS1'], function(kk, vv) {
                        //console.log(vv, vv[0] , rCount , vv[1]);
                        if (vv[0] <= rCount && rCount <= vv[1]) {
                            classKey = kk;
                        }
                    });
                    //console.log();
                    clog('문제풀이 7문제중 정답카운트 ' + rCount + ' 개   classKey==' + classKey + ' moJinData[classKey] = '+  moJinData[classKey]);
                    if(moJinData[classKey]=='CLOSE'){
                        alert('진단끝>>>>>>>('+(changeLevel)+')' );
                        plus.formSubmit('/front/furs/complete', {grade:changeLevel,rCount:(rCount),xCount:(xCount),myqstlist:JSON.stringify(myqstlist)});
                        //location.href = '/front/furs/complete?grade='+changeLevel+'&rCount='+(rCount)+'&xCount='+xCount;
                        clog('레벨('+(changeLevel)+') 진단이 끝나요>>>>>>>');
                        //console.log('다음레벨('+(changeLevel)+') 문제에서 끝나요>>>>>>>');
                        return false;
                    } else {
                        //출제그룹1은 ++되는걸로
                        if(changeLevel==1){
                           changeLevel++;
                        //출제그룹5은 --되는걸로
                        } else if(changeLevel==5){
                            changeLevel--;
                        //출제그룹2~4은 --되는걸로
                        } else {
                           if(classKey=='H1'){
                             changeLevel--;
                           } else {
                             //changeLevel++;
                           }
                        }
                        userLevelI=0;
                        //console.log('다음레벨('+(changeLevel)+') 문제를 풀어요>>>>>>>');
                        clog('다음레벨('+(changeLevel)+') 문제를 풀어요>>>>>>>');
                        //alert('다음레벨('+(changeLevel)+') 문제를 풀어요>>>>>>>');


                    }
                }
            } else {


                //2단계 문제풀이
                var oCount = rCount= oxCount(myqst[0], 'O');
                var xCount = oxCount(myqst[0], 'X');
                //2단계 문제풀이
                var o2Count = oxCount(myqst[1], 'O');
                var x2Count = oxCount(myqst[1], 'X');

                //정답과 오답수를 합친다 2보다클때 정답2개이상 오답이 2개가넘지않을때
                if(o2Count+x2Count>=2 && o2Count+x2Count>=3){
                    myqst[2].push(oxQustValue);
                } else {
                    myqst[1].push(oxQustValue);
                }

                //2단계 문제풀이
                var o2Count = oxCount(myqst[1], 'O');
                var x2Count = oxCount(myqst[1], 'X');


                //3단계 문제풀이
                var o3Count = oxCount(myqst[2], 'O');
                var x3Count = oxCount(myqst[2], 'X');


                if(o2Count+x2Count>=2 && o2Count>=2 && x2Count<2  && (o3Count+x3Count)==0 ){
                    if( changeLevel - meLevel ==1){
                        changeLevel++;
                        $('.grade').html(changeLevel);
                        clog('다음레벨('+(changeLevel)+') 문제를 풀어요>>>>>>>');

                    } else {
                      //레벨 1은 무시
                      if(meLevel>1 ){

                            classKey = 'H'+(parseInt(classKey.replace('H',''))+1);
                            changeLevel++;

                        clog('문제풀이[중반] ('+(o2Count+x2Count)+')문제중 정답카운트 ' + o2Count + ' 개  changeLevel = '+(changeLevel)+' classKey==' + classKey );
                        var moJinData = moJin[meLevel];
                        if(moJinData[classKey]=='CLOSE'){
                            //alert('끝나브렀어요1');

                            clog('최종레벨=='+ moJinValue[meLevel][classKey] );
                            if(changeLevel>5){
                                changeLevel =5;
                            }
                            plus.formSubmit('/front/furs/complete', {grade:changeLevel,rCount:(rCount+o2Count),xCount:(xCount+x2Count+rCount+o2Count),myqstlist:JSON.stringify(myqstlist)});
                            //location.href = '/front/furs/complete?grade='+changeLevel+'&rCount='+(rCount+o2Count)+'&xCount='+(xCount+x2Count+rCount+o2Count);
                        }
                      }
                    }
                } else if(o2Count+x2Count>=2 &&  x2Count>=2 && (o3Count+x3Count)==0 ){
                    //alert('진단끝>>01>>>>>('+(changeLevel)+')' );
                    clog('레벨('+(changeLevel)+') 문제에서 끝나요>>>>>>>');
                    $('.grade').html(changeLevel);
                    if(changeLevel>5){
                        changeLevel =5;
                    }
                    //location.href = '/front/furs/complete?grade='+changeLevel+'&rCount='+(rCount+o2Count)+'&xCount='+(xCount+x2Count+rCount+o2Count);
                    plus.formSubmit('/front/furs/complete', {grade:changeLevel,rCount:(rCount+o2Count),xCount:(xCount+x2Count+rCount+o2Count),myqstlist:JSON.stringify(myqstlist)});
                    var moJinData = moJin[meLevel];
                    if(moJinData[classKey]=='CLOSE'){
                        //alert('끝나브렀어요2');

                        clog('최종레벨=='+ moJinValue[changeLevel][classKey] )
                    }
                }

                clog('o2Count=='+o2Count+',o3Count=='+o3Count);
                if(o3Count+x3Count>=2 &&  o3Count>=2 && x3Count<2){

                    changeLevel++
                    //alert('진단끝>>02>>>>>('+(changeLevel)+')' );
                    if(changeLevel>5){
                        changeLevel =5;
                    }
                    plus.formSubmit('/front/furs/complete', {grade:changeLevel,rCount:(rCount+o2Count+o3Count),xCount:(xCount+x2Count+x3Count+rCount+o2Count+o3Count),myqstlist:JSON.stringify(myqstlist)});

                    //location.href = '/front/furs/complete?grade='+changeLevel+'&rCount='+(rCount+o2Count+o3Count)+'&xCount='+(xCount+x2Count+x3Count+rCount+o2Count+o3Count);
                    $('.grade').html(changeLevel);
                    clog('레벨('+(changeLevel)+') 문제에서 끝나요>>>>>>>');
                }
                else if(o3Count+x3Count>=2 &&  x3Count>=2 && o3Count<2){
                    //alert('진단끝>>03>>>>>('+(changeLevel)+')' );
                    if(changeLevel>5){
                        changeLevel =5;
                    }
                    plus.formSubmit('/front/furs/complete', {grade:changeLevel,rCount:(rCount+o2Count+o3Count),xCount:(xCount+x2Count+x3Count+rCount+o2Count+o3Count),myqstlist:JSON.stringify(myqstlist)});
                    //location.href = '/front/furs/complete?grade='+changeLevel+'&rCount='+(rCount+o2Count+o3Count)+'&xCount='+(xCount+x2Count+x3Count+rCount+o2Count+o3Count);
                    $('.grade').html(changeLevel);
                    clog('레벨('+(changeLevel)+') 문제에서 끝나요>>>>>>>');
                }
            }

            userI++;
            userLevelI++;
            console.log(userI, myqst);
            $('.bar').width(parseInt((userI / maxCount * 100)) + '%');
            $('.text').text('진단 진행률 ' + parseInt(((userI / maxCount) * 100)) + '%');

            console.log('qstData[changeLevel]',changeLevel);
            qstSet(qstData[changeLevel], userLevelI,userI);
            return false;
        });
        $.call('/front/ajax/aigo/furs/questionList', {}, function(r) {

            realData = r.resultList;
            ///console.log('qstData=',qstData);
            $.each(realData, function(k, v) {

                var cjGroup = $.trim(v['subAcaName']).replace('출제그룹', '');

                if ($.inArray(cjGroup, ['1', '2', '3', '4', '5']) == -1) {
                    return true;
                }
                if (typeof (qstData[cjGroup]) == "undefined") {
                    qstData[cjGroup] = [];
                }
                qstData[cjGroup].push(v);

            })
            //console.log('qstData=',qstData);
            qstSet(qstData[meLevel], userLevelI,userI);


            // $.each(r.resultList,function(k,v){
            //         $('.question_conts')
            // });
        });
    })
</script>