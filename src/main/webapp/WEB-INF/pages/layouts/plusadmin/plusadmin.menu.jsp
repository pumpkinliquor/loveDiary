<%@ page import="com.plushih.common.constant.LoginSession" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="loginMenu1" value="<%=session.getAttribute(LoginSession.Menu1)%>" />
<c:set var="loginMenu2" value="<%=session.getAttribute(LoginSession.Menu2)%>" />
<c:set var="loginMenu3" value="<%=session.getAttribute(LoginSession.Menu3)%>" />

  <!-- main Sidebar Container -->
  <div class="nav lnb" id="lnb">
    <h1>
      <a href="/plusadmin/main">
        <img src="/admassets/images/logo.png" alt="Aigo" />
      </a>
    </h1>

    <ul >

        <c:if test="${not empty loginMenu1}">
        <c:forEach var="menuList1" items="${loginMenu1}">
          <c:set var="subcheck" value="0" />
          <c:if test="${not empty loginMenu2}">
          <c:forEach var="menuList2" items="${loginMenu2}">
            <c:if test="${menuList1.maCode eq menuList2.maParent}">
              <c:set var="subcheck" value="1" />
            </c:if>
          </c:forEach>
          </c:if>
          <li class="title nav-item  ${menuList1.maCode}">
            <a
                    <c:if test="${subcheck==1}">
                      href="javascript:;"
                    </c:if>
                    <c:if test="${subcheck==0}">
                      href="${menuList1.maPath}"
                    </c:if>

               class="nav-link" mucode="${menuList1.maCode}" data="${menuList1.maPath}" alt="DASHBOARD">
              <i class="fas fa-th-large"></i>
              <p class="depth1"><c:out value="${menuList1.maName}" />
                <c:if test="${subcheck==1}">
                  <i class="fas fa-angle-right right"></i>
                </c:if>
              </p>
            </a>
            <c:if test="${subcheck==1}">
              <ul style="display: none;">

                <c:forEach var="menuList2" items="${loginMenu2}">


                  <c:if test="${menuList1.maCode eq menuList2.maParent}">
                    <c:if test="${menuList2.maPath eq url}">
                      <c:set var="module" value="${menuList2.maModule}" />
                      <c:set var="moduleName" value="${menuList2.maName}" />
                    </c:if>
                    <c:set var="sub2check" value="0" />
                    <c:forEach var="menuList3" items="${loginMenu3}">
                      <c:if test="${menuList2.maCode eq menuList3.maParent}">
                        <c:set var="sub2check" value="1" />
                      </c:if>
                    </c:forEach>
                    <li class="<c:if test="${sub2check!=1}">noDepth</c:if>">
                      <a href="<c:if test="${sub2check!=1}">${menuList2.maPath}</c:if><c:if test="${sub2check==1}">#</c:if>" class="nav-link" mucode="${menuList2.maCode}" data="<c:out value="${menuList2.maPath}"/>" alt="<c:out value="${menuList2.maName}"/>" >
                          <%--                          <i class="fa fa-minus"></i> <c:out value="${menuList2.maName}" /> --%>
                        <p>ㆍ <c:out value="${menuList2.maName}" /></p>
                      </a>
                      <c:if test="${sub2check==1}">
                        <ul style="display: none;">
                          <c:forEach var="menuList3" items="${loginMenu3}">
                            <c:if test="${menuList2.maCode eq menuList3.maParent}">
                              <li>
                              <a href="${menuList3.maPath}" class="nav-link" mucode="${menuList3.maCode}" data="<c:out value="${menuList3.maPath}"/>" alt="<c:out value="${menuList3.maName}"/>" >
                                  <%--                          <i class="fa fa-minus"></i> <c:out value="${menuList2.maName}" /> --%>
                                <p>ㆍ <c:out value="${menuList3.maName}" /></p>
                              </a>
                              </li>
                            </c:if>
                          </c:forEach>
                        </ul>
                      </c:if>
                    </li>


                  </c:if>
                </c:forEach>
              </ul>
            </c:if>
          </li>
        </c:forEach>
        </c:if>

    </ul>
  </div>
  <!-- //main Sidebar Container -->
<script type="text/javascript">
  $(document).ready(function(){
    $('#lnb ul a').each(function(){
      if($(this).attr('href').indexOf(location.pathname)>-1){
        //$(this).closest('li.depth1').addClass('menu-open');
        $(this).closest('li').addClass('on');
        $(this).closest('.nav-item').addClass('on');
        $(this).closest('.nav-item').find('ul').show();
        $(this).closest('ul').show();
        $(this).hover();
      }
    });
    $('.logout').click(function(){
      if(confirm('로그아웃 하시겠습니까?')){
        $.post('/static/ajax/loginOut',{},function(r){
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