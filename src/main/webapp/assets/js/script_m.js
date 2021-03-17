//버튼 선택 시
function qSelect() {
  $('.q_choice li button').click(function() {
    $(this).parent('li').addClass('on').siblings().removeClass('on');
  });
}

function numSelect() {
  $('.number_div button').click(function() {
    $(this).addClass('on').siblings().removeClass('on');
  });
}

function filterBtnSelect() {
  $('.btn_lv').click(function() {
    $(this).toggleClass('select');
  });

  $('.sbj_btn_div>div>.btn_sbj').click(function() {
    $(this).toggleClass('select');
  });
}

function leaveBtn() {
  $('.leave_reason li button').click(function() {
    //$(this).addClass('active');
    $(this).parents().addClass('active').siblings().removeClass('active');
  });
}

//전체 선택
function chkAll() {
    if ($("#checkAll").is(':checked')) {
        $("input[type=checkbox]").prop("checked", true);
    } else {
        $("input[type=checkbox]").prop("checked", false);
    }
}

//학습홈 하단 슬라이드
function qCardSlider(){
    $(".q_card_slide").owlCarousel({
        stagePadding: 30,
        margin: 20,
        items: 1,
        loop: false,
        nav:false,
        autoplay:false,
        responsive: {
          540: {
            items: 2,
          },
          980: {
            items: 3,
          },
        }
    });
}

//종합리포트 하단 슬라이드
function unitCardSlider(){
    $(".unit_card_slide").owlCarousel({
        stagePadding: 30,
        margin: 10,
        items: 1,
        loop: false,
        nav:false,
        autoplay:false,
        responsive: {
          360: {
            items: 2,
          },
          640: {
            items: 3,
          },
        }
    });
}

//모달창
function modalOpen(target) {
  $('.' + target).addClass('is-visible');
  $('body').css("overflow", "hidden");
}

function modalClose() {
  $('.modal .close-pop').click(function() {
    $(this).parents('.modal-wrap').removeClass('is-visible');
    $('body').css("overflow", "scroll");
  });
}
function modalClose2() {
  $('.modal .ipt_radio_div>input').click(function() {
    $(this).parents('.modal-wrap').removeClass('is-visible');
    $('body').css("overflow", "scroll");
  });
}


//TAB
function levelTab(){
    var lvTab = $('#levelTab>.tab_m');
    var lvTabCont = $('#levelTabCont>.tab_cont');
    lvTab.on('click',function(){
      lvTab.removeClass('active');
      $(this).addClass('active')
      var idx = lvTab.index(this);
      lvTabCont.hide();
      lvTabCont.eq(idx).show();
    });
}

function qTabMenu(){
    var qTab = $('#qTab>.tab_m');
    var qTabCont = $('#qTabCont>.tab_cont');
    qTab.on('click',function(){
      qTab.removeClass('active');
      $(this).addClass('active')
      var idx = qTab.index(this);
      qTabCont.hide();
      qTabCont.eq(idx).show();
    });
}

function aTab(){
    var aTab = $('.tab_div>.tab_a');
    var aTabCont = $('.tab_cont_wrap .tab_cont');
    aTab.on('click',function(){
      aTab.removeClass('active');
      $(this).addClass('active')
      var idx = aTab.index(this);
      aTabCont.hide();
      aTabCont.eq(idx).show();
      return false;
    });
}


$(document).ready(function() {
   $('.tab_menu').on('touchstart','.tab_m',function(event){
        $(this).addClass('active');
    });
    $('.tab_menu').on('touchend','.tab_m',function(event){
        $(this).removeClass('active');
    });
});

//타이핑  //수정0308
var typingBool = false; 
var typingIdx=0; 
var liIndex = 0;
var liLength = $(".typing").length;

var typingTxt = $(".typing").eq(liIndex).text(); 
typingTxt=typingTxt.split(""); 
if(typingBool==false){ 
    typingBool=true; 
    var tyInt = setInterval(typing,200); 
} 
     
function typing(){ 
  if(typingIdx<typingTxt.length){
     $(".typing").append(typingTxt[typingIdx]); 
     typingIdx++; 
	 if(   typingIdx==typingTxt.length){
		return stop();
		 function stop() {
			if (tyInt) {
				clearTimeout(tyInt);
			}
		}
	}
   } else { 
     if(liIndex>=liLength-1){
      liIndex=0;
     }else{
       liIndex++; 
     }
   typingIdx=0;
      typingBool = false; 
      typingTxt = $(".typing").eq(liIndex).text(); 
      clearTimeout(tyInt);//타이핑종료

       setTimeout(function(){
          $(".typing").html('');
         tyInt = setInterval(typing,150);
       },1000);
    } 

} 

//정렬기준 레이어
$(document).ready(function() {
  $('.btn_sort').click(function(){
    $(this).next('.layer_pop').addClass('active');
  });

  $('.btn_level').click(function(){
    $(this).next('.layer_pop').toggleClass('active');
  });
});



function lavelBar(){
  $(".bar_wrap .bar_div").each(function() {
    $(this)
      .data("origWidth", $(this).width())
      .width(10)
      .animate({
        width: $(this).data("origWidth")
      }, 1000);
  });
} 

function prgressBar(){
  $(".progress_div > .bar").each(function() {
    $(this)
      .data("origWidth", $(this).width())
      .width(0)
      .animate({
        width: $(this).data("origWidth")
      }, 1200);
  });
}


$(document).ready(function(){
    $('.onoffswitch2 input[type="checkbox"]').click(function(){
        var inputId = $(this).attr("id");
        $("." + inputId).toggleClass('on');
        $(".onoff").toggleClass('expand'); //수정0224
    });

  //잘모르겠어요 버튼  //수정0308
  $('.dontknow .btn').click(function(){ 
    $(this).toggleClass('on');
  });
});


//수정0225
function qTopSection(){
 var qTopHeight; // 변수선언
 //var qSectionTop = 

  // 페이지 로드시 자동실행
  $(window).ready(function () {
      qTopHeight = document.getElementById("qTopHeight").offsetHeight+'px';
      // 페이지 로드시 id값의 div의 높이를 구함

      document.getElementById("qTopHeight").style.height = qTopHeight;
      // 높이를 id 의 height 값에 저장

      console.log("load height : " + qTopHeight);

      $(".q_section").css("top",qTopHeight);
  });


  // 화면 리사이즈시 실행
  $(window).resize(function () {
      height = document.getElementById("qTopHeight").offsetHeight+'px';
      // 화면 리사이즈 시 id값의 div의 높이를 구함

      document.getElementById("qTopHeight").style.height = height;
      // 화면 리사이즈시 id의 height값 변경 후 저장

      console.log("resize height : " + height);
  });
}

//이벤트 배너
function eventBanner(){
    var eventBannerSlide = $('.ev_ban_slide');
    eventBannerSlide.owlCarousel({
    loop:true,
    nav:false,
    dots:true,
    items:1,
    mouseDrag: true,
    autoWidth:false,
    autoplay:false,
    });
}

function faqList() {
  //$('.faq_2depth').hide();
  $('.faq_tit1').click(function() {
    $(this).toggleClass('active');
    $(this).next().toggleClass('on');
    //$(this).next().toggle();
  });
}


