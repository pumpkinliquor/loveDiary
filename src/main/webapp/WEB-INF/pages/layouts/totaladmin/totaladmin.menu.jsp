<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

  <!-- main Sidebar Container -->
  <aside class="main-sidebar sidebar-light-primary elevation-4">

    <a href="/plusadmin/main" class="brand-link">
      <img src="/admassets/images/logo.png" alt="삼일제약" class="brand-image">
      <span></span>
    </a>

    <div class="sidebar">
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">

          <!--공통관리-->
          <li class="nav-item depth1">
            <a href="#" class="nav-link">
              <i class="fas fa-th-large"></i>
              <p class="one-depth">공통관리<i class="fas fa-angle-right right"></i></p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/totaladmin/menus/menulist" class="nav-link">
                  <p>ㆍ 사용자메뉴</p>
                </a>
              </li>
                <li class="nav-item">
                <a href="/totaladmin/menus/menugrant" class="nav-link">
                  <p>ㆍ 사용자메뉴권한</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/totaladmin/menus/admmenulist" class="nav-link">
                  <p>ㆍ 관리자메뉴</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/totaladmin/menus/admmenugrant" class="nav-link">
                  <p>ㆍ 메뉴권한</p>
                </a>
              </li>
            </ul>
          </li>
          <!--//공통관리-->

          <!--R&D-->
<%--          <li class="nav-item depth1">--%>
<%--            <a href="/totaladmin/business/businessinfolist" class="nav-link">--%>
<%--              <i class="fas fa-th-large"></i>--%>
<%--              <p class="one-depth">사업자관리</p>--%>
<%--            </a>--%>
<%--          </li>--%>
            <li class="nav-item depth1">
            <a href="/totaladmin/configs/languagelist" class="nav-link">
              <i class="fas fa-th-large"></i>
              <p class="one-depth">언어설정</p>
            </a>
          </li>
          <!--//R&D-->

          <!--PRODUCTS-->
          <li class="nav-item depth1">
            <a href="#" class="nav-link">
              <i class="fas fa-th-large"></i>
              <p class="one-depth">환경설정<i class="fas fa-angle-right right"></i></p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/totaladmin/configs/codelist" class="nav-link">
                  <p>ㆍ 공통코드</p>
                </a>
              </li>
<%--              <li class="nav-item">--%>
<%--                <a href="/totaladmin/AD_PI_01" class="nav-link">--%>
<%--                  <p>ㆍ </p>--%>
<%--                </a>--%>
<%--              </li>--%>
            </ul>
          </li>
          <!--//PRODUCTS-->

          <!--RECRUIT-->
          <li class="nav-item depth1">
            <a href="#" class="nav-link">
              <i class="fas fa-th-large"></i>
              <p class="one-depth">계정관리<i class="fas fa-angle-right right"></i></p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/totaladmin/users/userlist" class="nav-link">
                  <p>ㆍ 계정관리</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/totaladmin/users/usergrouplist" class="nav-link">
                  <p>ㆍ 계정그룹관리</p>
                </a>
              </li>
            </ul>
          </li>
          <!--//RECRUIT-->

<%--          <!--IR 전자공고-->--%>
<%--          <li class="nav-item depth1">--%>
<%--            <a href="/plusadmin/AD_IR_01" class="nav-link">--%>
<%--              <i class="fas fa-th-large"></i>--%>
<%--              <p class="one-depth">IR 전자공고</p>--%>
<%--            </a>--%>
<%--          </li>--%>
<%--          <!--//IR 전자공고-->--%>

<%--          <!--SAMIL STORY-->--%>
<%--          <li class="nav-item depth1">--%>
<%--            <a href="#" class="nav-link">--%>
<%--              <i class="fas fa-th-large"></i>--%>
<%--              <p class="one-depth">SAMIL STORY<i class="fas fa-angle-right right"></i></p>--%>
<%--            </a>--%>
<%--            <ul class="nav nav-treeview">--%>
<%--              <li class="nav-item">--%>
<%--                <a href="/plusadmin/AD_PR_01" class="nav-link">--%>
<%--                  <p>ㆍ 보도자료</p>--%>
<%--                </a>--%>
<%--              </li>--%>
<%--              <li class="nav-item">--%>
<%--                <a href="/plusadmin/AD_BA_01" class="nav-link">--%>
<%--                  <p>ㆍ 방송광고</p>--%>
<%--                </a>--%>
<%--              </li>--%>
<%--              <li class="nav-item">--%>
<%--                <a href="/plusadmin/AD_PA_01" class="nav-link">--%>
<%--                  <p>ㆍ 인쇄광고</p>--%>
<%--                </a>--%>
<%--              </li>--%>
<%--            </ul>--%>
<%--          </li>--%>
<%--          <!--//SAMIL STORY-->--%>

<%--          <!--사이트 통계-->--%>
<%--          <li class="nav-item depth1">--%>
<%--            <a href="#" class="nav-link">--%>
<%--              <i class="fas fa-th-large"></i>--%>
<%--              <p class="one-depth">사이트 통계</p>--%>
<%--            </a>--%>
<%--          </li>--%>

        </ul>
      </nav>
    </div>
  </aside>
  <!-- //main Sidebar Container -->
<script type="text/javascript">
  $(document).ready(function(){
    $('.sidebar a').each(function(){
      if($(this).attr('href').indexOf(location.pathname)>-1){
        $(this).closest('li.depth1').addClass('menu-open');
        $(this).addClass('on');
      }
    });
    $('.logout').click(function(){
      if(confirm('로그아웃 하시겠습니까?')){
        $.post('static/ajax/loginOut',{},function(r){
          alert('로그아웃 되었습니다.')
          location.href='/main';
        })
      }
    })

    //
    //$('a').attr()
    //
  })
</script>