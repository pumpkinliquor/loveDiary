<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<body class="question_body">
<div class="wrapper">
    <header class="header">
        <h1 class="logo">AIGo</h1>
        <strong class="progressing">AI Progressing…</strong>
    </header>
    <div class="container">
        <div class="question_conts">
            <h2 class="q_number">01.</h2>
            <div class="q_sec">
                <img class="q_img" src="/assets/images/_tmp/img_question_1.jpg" alt="문제 이미지">
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
                    <dd><em class="grade">3</em>등급</dd>
                </dl>
            </div>
        </section>

        <section class="answer_submit_cont">
            <div class="number_div">
                <button type="button">1</button>
                <button type="button">2</button>
                <button type="button">3</button>
                <button type="button">4</button>
                <button type="button">5</button>
            </div>
            <button type="submit" class="btn btn_submit" />
            답안제출</input>
        </section>
    </div><!-- //container -->

</div>

<script type="text/javascript">
    //numSelect();
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

    function qstSet(data, num) {

        $('.q_number').html(((num + 1) > 9 ? '0' + (num + 1) : (num + 1)));
    }

    var myqst = [[], [], []];
    var qst = [

        [['O', 'O', 'O', 'O', 'O', 'O', 'O'], ['O', 'O', 'X'], ['O', 'O', 'O', 'X', 'X']],
        [['O', 'O', 'O', 'O', 'O', 'O', 'O'], ['O', 'X', 'X'], ['X', 'X']],
        [['O', 'O', 'O', 'X', 'X', 'X', 'X'], ['X', 'X']],
        [['O', 'O', 'O', 'X', 'X', 'O', 'X'], ['X', 'O', 'O'], ['X', 'X']],
    ];
    var maxCount = 13;
    $(document).ready(function() {

        var meGrade = 1;
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
        var changeLevel = meLevel;
        console.log('현재 등급은 ' + meGrade + '이며 출제그룹은 ' + meLevel + '에서 시작');
        var moJinData = moJin[meLevel];
        // $.each(qst, function(k, v) {
        //     //1단계 문제풀이
        //     var rCount = oxCount(v[0], 'O');
        //
        //     //2단계 문제풀이
        //     var o2Count = oxCount(v[1], 'O');
        //     var x2Count = oxCount(v[1], 'X');
        //
        //     //3단계 문제풀이이
        //     var o3Count = oxCount(v[2], 'O');
        //     var x3Count = oxCount(v[2], 'X');
        //
        //     //1단계에서 2단계 바라볼 키
        //     var classKey = '';
        //
        //     //
        //     $.each(moJinData['CLASS1'], function(kk, vv) {
        //         //console.log(vv, vv[0] , rCount , vv[1]);
        //         if (vv[0] <= rCount && rCount <= vv[1]) {
        //             classKey = kk;
        //         }
        //     });
        //     console.log('문제풀이 7문제중 정답카운트 ' + rCount + ' 개  중반은 정답 ' + o2Count + '개 오답 ' + x2Count + ' classKey==' + classKey + ' moJinData[classKey] = ', moJinData[classKey])
        //
        //     //만약 키값이 CLOSE면 더이상진행안하고 끝내는것
        //     if (moJinData[classKey] == 'CLOSE') {
        //
        //         changeLevel = meLevel
        //         console.log('진단 레벨은 ' + changeLevel + '입니다');
        //     } else {
        //
        //
        //         var level1MoJin = moJinData[classKey];
        //         var level1MoJinKey = '';
        //         var level2MoJin = {};
        //         //출제그룹 1에서 case
        //         if (meLevel == 1) {
        //
        //             if (x2Count >= 2) {
        //                 level1MoJinKey = 'H2';
        //                 level2MoJin = level1MoJin['H2'];
        //             } else {
        //                 level1MoJinKey = 'H3';
        //                 level2MoJin = level1MoJin['H3'];
        //             }
        //             console.log('---------meLevel', meLevel, x2Count, level1MoJinKey);
        //         }
        //         //출제그룹 5에서 case
        //         else if (meLevel == 5) {
        //
        //         } else {
        //             if ($.inArray(classKey, ['H4', 'H5']) > -1) {
        //                 console.log('H4', 'H5', level1MoJin['H4'], level1MoJin['H5']);
        //                 //2단계에서 오답이 2개이상이면 더이상진전이안됨
        //                 if (x2Count >= 2) {
        //                     level1MoJinKey = 'H4';
        //                     level2MoJin = level1MoJin['H4'];
        //                 } else {
        //                     level1MoJinKey = 'H5';
        //                     level2MoJin = level1MoJin['H5'];
        //                 }
        //             } else if ($.inArray(classKey, ['H1', 'H2']) > -1) {
        //                 //console.log('H1','H2',level1MoJin['H1'],level1MoJin['H2']);
        //                 //2단계에서 오답이 2개이상이면 더이상진전이안됨
        //                 if (x2Count >= 2) {
        //                     level1MoJinKey = 'H1';
        //                     level2MoJin = level1MoJin['H1'];
        //                 } else {
        //                     level1MoJinKey = 'H2';
        //                     level2MoJin = level1MoJin['H2'];
        //                 }
        //
        //             }
        //         }
        //
        //
        //         console.log('2단계 진행을 위한 데이터 키,값', level1MoJinKey, level1MoJin, '정답이 ', o2Count, '오답이', x2Count);
        //         //console.log('3단계 진행을 위한 데이터 키,값' , level2MoJin);
        //         if (level2MoJin == 'S2NEXT') {
        //             changeLevel++;
        //         }
        //
        //         console.log('2단계 진행을 위한 데이터 키,값', level1MoJinKey, level1MoJin, '정답이 ', o2Count, '오답이', x2Count, '2단계 진단레벨', changeLevel, '3단계=', level1MoJinKey, '3단계 단계', level2MoJin);
        //         //3단계에서는 정답수로만체크하자
        //         //console.log('3단계에서는 ',Object.keys(moJin[meLevel][level1MoJinKey]),Object.keys(moJin[meLevel][level1MoJinKey])[0],Object.keys(moJin[meLevel][level1MoJinKey])[1])
        //         var level3MoJin = moJin[level1MoJinKey];
        //         if (o3Count >= 2) {
        //             var lastLevle = Object.keys(moJin[meLevel][level1MoJinKey]);
        //             console.log('최종 진단 레벨은', lastLevle, moJinValue[meLevel][lastLevle[1]]);
        //         } else {
        //             var lastLevle = Object.keys(moJin[meLevel][level1MoJinKey]);
        //             console.log('최종 진단 레벨은', lastLevle, moJinValue[meLevel][lastLevle[0]]);
        //         }
        //     }
        //     console.log('moJinData ocount', oxCount(v[0], 'O'), '::classKeyclassKey=', classKey);
        //
        // });
        $('.btn_st1').click(function() {
            $('div.number_div').find('button').removeClass('on');
        })
        $('.number_div button').click(function() {
            $(this).closest('div').find('button').removeClass('on');
            $(this).addClass('on');
        })
        $('.btn_submit').click(function() {
            var qstValue = '';
            if ($(this).closest('div').find('button.on').length > 0) {
                qstValue = $(this).closest('div').find('button.on').html();
            } else if ($('.btn_st1.on').length > 0) {
                qstValue = $('.btn_st1.on').html();
            }
            if(qstValue=='1'){
                qstValue ='O';
            } else {
                qstValue = 'X';
            }
            var moJinData = moJin[meLevel];

            //1단계에서 2단계 바라볼 키
            var classKey = '';


            if (userI < 7) {
                console.log('qstValue==', qstValue);
                myqst[0].push(qstValue);
                //1단계 문제풀이
                var rCount = oxCount(myqst[0], 'O');

                if((userI+1)==7){

                    console.log('####',moJinData['CLASS1']);
                    $.each(moJinData['CLASS1'], function(kk, vv) {
                        //console.log(vv, vv[0] , rCount , vv[1]);
                        if (vv[0] <= rCount && rCount <= vv[1]) {
                            classKey = kk;
                        }
                    });
                    console.log('문제풀이 7문제중 정답카운트 ' + rCount + ' 개   classKey==' + classKey + ' moJinData[classKey] = ', moJinData[classKey]);

                    if(moJinData[classKey]=='CLOSE'){
                        alert('진단끝>>>>>>>('+(changeLevel)+')' );
                        console.log('다음레벨('+(changeLevel)+') 문제에서 끝나요>>>>>>>');
                        return false;
                    } else {
                        changeLevel++;
                        userLevelI=0;
                        console.log('다음레벨('+(changeLevel)+') 문제를 풀어요>>>>>>>');
                        //alert('다음레벨('+(changeLevel)+') 문제를 풀어요>>>>>>>');


                    }
                }
            } else {



                //2단계 문제풀이
                var o2Count = oxCount(myqst[1], 'O');
                var x2Count = oxCount(myqst[1], 'X');

                //정답과 오답수를 합친다 2보다클때 정답2개이상 오답이 2개가넘지않을때
                //
                if(o2Count+x2Count>=2 && o2Count>=2 && x2Count<2){
                    myqst[2].push(qstValue);
                }

                //정답과 오답수를 합친다 2보다클때 오답이2개이상 이면 종료
                else if(o2Count+x2Count>=2 && x2Count>=2 && o2Count<2){


                } else {
                    myqst[1].push(qstValue);
                }

                //2단계 문제풀이
                var o2Count = oxCount(myqst[1], 'O');
                var x2Count = oxCount(myqst[1], 'X');


                //3단계 문제풀이
                var o3Count = oxCount(myqst[2], 'O');
                var x3Count = oxCount(myqst[2], 'X');

                console.log('o3Count=='+o3Count);
                if(o2Count+x2Count>=2 && o2Count>=2 && x2Count<2){
                    if( changeLevel - meLevel ==1){
                        changeLevel++;
                        $('.grade').html(changeLevel);
                        console.log('다음레벨('+(changeLevel)+') 문제를 풀어요>>>>>>>');
                    }
                } else if(o2Count+x2Count>=2 &&  x2Count>=2){
                    alert('진단끝>>>>>>>('+(changeLevel)+')' );
                    console.log('레벨('+(changeLevel)+') 문제에서 끝나요>>>>>>>');
                    $('.grade').html(changeLevel);
                }
                if(o3Count+x3Count>=2 &&  x3Count>=2){
                    alert('진단끝>>>>>>>('+(changeLevel)+')' );
                    $('.grade').html(changeLevel);
                    console.log('레벨('+(changeLevel)+') 문제에서 끝나요>>>>>>>');
                }
                else if(o3Count+x3Count>=2 &&  o3Count>=2){
                    alert('진단끝>>>>>>>('+(changeLevel)+')' );
                    $('.grade').html(changeLevel);
                    console.log('레벨('+(changeLevel)+') 문제에서 끝나요>>>>>>>');
                }
            }

            userI++;
            userLevelI++;
            console.log(userI, myqst);
            $('.bar').width(parseInt((userI / maxCount * 100)) + '%');
            $('.text').text('진단 진행률 ' + parseInt(((userI / maxCount) * 100)) + '%');
            qstSet(realData[changeLevel], userLevelI);
            return false;
        });
        $.call('/front/ajax/aigo/firs/questionList', {}, function(r) {

            realData = r.resultList;
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
            console.log(qstData);
            qstSet(realData[meLevel], userI);


            // $.each(r.resultList,function(k,v){
            //         $('.question_conts')
            // });
        });
    })
</script>