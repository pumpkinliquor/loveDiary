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
          600: {
            items: 2,
          },
        }
    });
}

//모달창
function modalClose() {
  $('.modal .close-pop').click(function() {
    $(this).parents('.modal-wrap').removeClass('is-visible');
    $('body').css("overflow", "scroll");
  });
}

function modalOpen(target) {
  $('.' + target).addClass('is-visible');
  $('body').css("overflow", "hidden");
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

$(document).ready(function() {
   $('.tab_menu').on('touchstart','.tab_m',function(event){
        $(this).addClass('active');
    });
    $('.tab_menu').on('touchend','.tab_m',function(event){
        $(this).removeClass('active');
    });
});

//타이핑 
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
   } else{ 
     if(liIndex>=liLength-1){
       liIndex=0;
     }else{
       liIndex++; 
     }
      typingIdx=0;
      typingBool = false; 
      typingTxt = $(".typing").eq(liIndex).text(); 
     
       clearInterval(tyInt);
       setTimeout(function(){
          $(".typing").html('');
         tyInt = setInterval(typing,200);
       },1000);
    } 
} 

//정렬기준 레이어
$(document).ready(function() {
  $('.btn_sort').click(function(){
    $(this).next('.layer_pop').addClass('active');
  });
});