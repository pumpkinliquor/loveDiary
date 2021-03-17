  /* lnb accodian*/
(function($){
  
  var lnbUI = {
    click : function (target, speed) {
      var _self = this,
          $target = $(target);
      _self.speed = speed || 300;
      
      $target.each(function(){
        if(findChildren($(this))) {
          return;
        }
        $(this).addClass('noDepth');
      });
      
      function findChildren(obj) {
        return obj.find('> ul').length > 0;
      }
      
      $target.on('click','a', function(e){
          e.stopPropagation();
          var $this = $(this),
              $depthTarget = $this.next(),
              $siblings = $this.parent().siblings();
        
        $this.parent('li').find('ul li').removeClass('on');
        // $siblings.removeClass('on');
        // $siblings.find('ul').slideUp(250);
        
        if($depthTarget.css('display') == 'none') {
          _self.activeOn($this);
          $depthTarget.slideDown(_self.speed);
        } else {
          $depthTarget.slideUp(_self.speed);
          _self.activeOff($this);
        }
        
      })
      
    },
    activeOff : function($target) {
      $target.parent().removeClass('on');
    },
    activeOn : function($target) {
      $target.parent().addClass('on');
    }
  };
  
  // Call lnbUI
  $(function(){
    lnbUI.click('.lnb li', 300)
  });
  }(jQuery));
  function myFunction() {
    var input, filter, ul, li, a, i;
    //input = document.getElementById("mySearch");
    filter = input.value.toUpperCase();
    ul = document.getElementById("lnb");
    li = ul.getElementsByClassName("title");
    for (i = 0; i < li.length; i++) {
      a = li[i].getElementsByTagName("a")[0];
      if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
        li[i].style.display = "";
      } else {
        li[i].style.display = "none";
      }
    }
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
function tabMenu(){
    var memberTab = $('.tab_menu .tab');
    var memberTabCont = $('.tab_cont');
    memberTab.on('click',function(){
      memberTab.removeClass('active');
      $(this).addClass('active')
      var idx = memberTab.index(this);
      memberTabCont.hide();
      memberTabCont.eq(idx).show();
    });
}

$(document).ready(function(){
  var fileTarget = $('.filebox .upload-hidden');

  fileTarget.on('change', function(){ // 값이 변경되면
    if(window.FileReader){ // modern browser
      var filename = $(this)[0].files[0].name;
    } 
    else { // old IE
      var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출
    }
    // 추출한 파일명 삽입
    $(this).siblings('.upload-name').val(filename);
  });
});

function chkAll() {
    if ($("#checkAll").is(':checked')) {
        $("input[type=checkbox]").prop("checked", true);
    } else {
        $("input[type=checkbox]").prop("checked", false);
    }
}

function chkSet() {
  var chk1 = $(".date_set_wrap .chkbox_div .ipt_chk");
  var chk2 = $(".date_set_wrap .date_div");
    chk1.click(function(){
    chk2.toggleClass('disabled');
  });
}
