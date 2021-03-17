var front = {
    /**
     * 기본퍼블리싱온거에 수정
     */
    publish:function(){

        /* hsk3807 top menu click 왼쪽에 메뉴가 펄쳐지도록*/
        $('.nav > li > a').click(function(){

            $('.leftMenu > li').removeClass('active');
            var depth1= $('.leftMenu > li.'+$(this).attr('class'));
            ;
            if($.trim(depth1.find('>a').next().html())!=''){
                depth1.addClass('active');
            } else {
                depth1.find('a').click();
            }
        });
        /* hsk3807 대시보드 처럼 하위가없으면 화살표 삭제*/
        $('.leftMenu > li > a').each(function(){
            if($.trim($(this).next().html())=='') $(this).find('span').remove();
        });

        /* leftMenu */
        $('.leftMenu > li > a').on('click',function(){
            if($.trim($(this).next().html())=='') return false;
            if($(this).parent().hasClass('active') ) {
                $(this).parent().removeClass('active');
            }else{
                $('.leftMenu > li').removeClass('active');
                $(this).parent().addClass('active');
            }
            return false;
        });
        /* hsk3807 메뉴클릭시 탭설정*/
        $('.leftMenu > li a').click(function(){
            var dataUrl = $(this).attr('data');
            if(dataUrl.indexOf('/front')>-1){
                console.log('탭으로 간주하자',dataUrl);
                $.cookie('lastPage',JSON.stringify({url:dataUrl,'mucode':$(this).attr('mucode'),title:$.trim($(this).text())}));
                plus.frontTab.addMultiTab($(this));
            }
            return false;
        });
        /*hsk3807 3depth가 있는경우 아이콘 변경 */
        $('.depth3').each(function(){
           if($.trim($(this).html())!=''){
               var el = $(this).prev();
               el.attr('data','javascript:;');
               el.find('.fa').removeClass('fa-minus').addClass('fa-angle-up');
               el.click(function(){
                   if($(this).is('.on')){
                       $(this).removeClass('on');
                       //$(this).find('.fa')
                       el.find('.fa').removeClass('fa-angle-down').addClass('fa-angle-up');
                       $(this).next().hide();
                   } else {
                       $(this).addClass('on');
                       //$(this).find('.fa').removeClass('')
                       el.find('.fa').removeClass('fa-angle-up').addClass('fa-angle-down');
                       $(this).next().show();
                   }
               })
           }
        });

          // DATE PICKER
        $('.date input').datetimepicker({
            // format: 'YYYYMMDD'
            format: 'DD/MM/YYYY'
        });
        $('.dateTYPE2 input').datetimepicker({
            format: 'YYYY-MM-DD'
        });
        $('.date .btn').click(function(){
            $(this).prev().focus();
        });
        $('.dateTYPE2 .btn').click(function(){
            $(this).prev().focus();
        });

        /* 파일보내기 파일업로드 스타일변경*/
        var uploadFile = $('.fileBox .uploadBtn');
        uploadFile.on('change', function(){
            if(window.FileReader){
                var filename = $(this)[0].files[0].name;
            } else {
                var filename = $(this).val().split('/').pop().split('\\').pop();
            }
            $(this).siblings('.fileName').val(filename);
        })

        /* leftMenu Show Hide */
        $(".menu_link").click(function(){
          if($('body').hasClass("left_show")){
            $('body').removeClass("left_show");
          }else{
            $('body').addClass("left_show");
          }
        });

    },
    /**
     * 초기 탭세팅
     */
    init:function(){
      var lastPage = $.cookie('lastPage');
      if(lastPage!=null){
          try{
              lastPage =  $.parseJSON(lastPage);

              $('.nav-tabs a:eq(0)').text($.trim(lastPage['title'])).addClass(lastPage['mucode']);
          } catch(ex){
            lastPage = {'url':'/front/dashboard','title':'Dashboard','mucode':'DAS_10'}
          }
      } else {
          lastPage = {'url':'/front/dashboard','title':'Dashboard','mucode':'DAS_10'}
      }
      plus.frontTab.tabAppend(lastPage);
    },
    /**
     * 리사이즈
     */
    resize:function(){

        var w = $(window).width();
        if(w>1200){
            $('body').addClass('left_show');
        } else  if(w<800){
            $('body').removeClass('left_show');
        }
    },
    logOut:function(){
      $('.btn_basic').click(function(){
            $.call('/static/ajax/loginOut',{},function(r){
                if(r.resultCode=='OK'){
                    location.href = '/';
                }

            });
        })
    },

}
$(function() {
    "use strict";
    for (var prop in front) {
        if (front.hasOwnProperty(prop)) {
            front[prop]();
        }
    }






});
$(window).resize(front.resize);