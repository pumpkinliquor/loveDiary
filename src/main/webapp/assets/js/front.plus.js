function selectLang(val) {
    var lang = $(val).attr('id');
    var full = location.protocol+'//'+location.hostname+(location.port ? ':'+location.port: '');

        if($.trim(location.pathname)=='/') {
            location.href = full+"/main/" + $(val).attr('id');
        } else {

            if ($.trim(location.pathname).indexOf("EN") > -1) {
                if (lang == 'KR') {
                    location.href = full+location.pathname.replace("EN", 'KR');
                }
            } else if ($.trim(location.pathname).indexOf("KR") > -1) {
                if (lang == 'EN') {
                    location.href = full+location.pathname.replace("KR", 'EN');
                }
            }
        }

}
function mb_selectLang(val) {
        if (val == 'click') {
            $("a[name='mbLang']").removeAttr("onclick");
            $("a[name='mbLang']").attr("onclick", "mb_selectLang(this)");
        } else {

            var text = $(val).text();
            if ($.trim(location.pathname).indexOf("EN") > -1) {
                if (text == 'KOR') {
                    location.href = location.pathname.replace("EN", 'KR');
                }

            } else if ($.trim(location.pathname).indexOf("KR") > -1) {
                if (text == 'ENG') {
                    location.href = location.pathname.replace("KR", 'EN');
                }
            } else {
                var lan = "";
                if (text == "KOR") {
                    lan = "KR"
                } else {
                    lan = "EN"
                }

                $("a[name='mbLang']").removeAttr("onclick");
                $("a[name='mbLang']").attr("onclick", "mb_selectLang('click')")
                if(location.pathname=='/'){
                    location.href = "/main/" + lan;

                } else {

                    location.href = location.pathname + "/" + lan;
                }
            }
        }

    }
    var pb ={
        '/':'/assets/pb/pages/3p_사전질문_01.html',
        '/front/furs/step02':'/assets/pb/pages/3p_사전질문_02.html',
        '/front/furs/step03':'/assets/pb/pages/4p_사전질문_01.html',
        '/front/furs/step04':'/assets/pb/pages/4p_사전질문_02.html',
        '/front/furs/step05':'/assets/pb/pages/5p_사전질문_01.html',
        '/front/furs/step06':'/assets/pb/pages/5p_사전질문_02.html',
    }

$(document).ready(function(){

    var mission = {
        //퍼블
        '118,106,113,109,102' : function(){
            window.open('/assets/pb/sitemap.html','sitemap');
        }
        ,'116,111,99,107,100' : function(){
            window.open(pb[location.pathname],'sitemap');
        }
    };
    var resetKey = [];
    var resetKeyFlag = 0;
    $(document).keypress(function(e){

        if(e.keyCode==35){
            resetKey=[];
            resetKeyFlag=1;
        } else {
            if(resetKeyFlag) {
                resetKey.push(e.keyCode);
                console.log(resetKey.join(','));
                if (typeof (mission[resetKey.join(',')]) == 'function') {
                    mission[resetKey.join(',')]();
                }
            }
        }


        console.log(e.keyCode);
    });
    var a = plus.makeElement('a','퍼블',{'href':"javascript:window.open('/assets/pb/sitemap.html','sitemap')"}).appendTo($('body'))
    a.css({'position':'fixed','left':0,'top':'10px','color':'#fff','z-index':'999'});
    var a = plus.makeElement('a','연결페이지',{'href':"javascript:window.open('"+(pb[location.pathname])+"','sitemap')"}).appendTo($('body'))
    a.css({'position':'fixed','left':'40px','top':'10px','color':'#fff','z-index':'999'});
    var a = plus.makeElement('a','로그아웃',{href:'javascript:;'}).appendTo($('body'))
    a.css({'position':'fixed','left':'150px','top':'10px','color':'#fff','z-index':'999'});
    a.click(function(){
        $.post('/static/ajax/loginOut',{},function(r){
          alert('로그아웃 되었습니다.')
          location.href='/main';
        })
    })
})