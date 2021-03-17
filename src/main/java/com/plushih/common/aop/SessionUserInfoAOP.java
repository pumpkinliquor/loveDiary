//package com.plushih.common.aop;
//
//import com.plushih.common.constant.Code;
//import com.plushih.common.constant.Default;
//import com.plushih.common.constant.LoginSession;
//import com.plushih.entities.common.CategoryEntity;
//import com.plushih.entities.user.UserInfoDetailEntity;
//import com.plushih.services.*;
//import com.plushih.common.lang.StringUtils;
//import org.aspectj.lang.JoinPoint;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//public class SessionUserInfoAOP {
//
//  private static final Logger LOGGER = LoggerFactory.getLogger(SessionUserInfoAOP.class);
//
//  @Autowired
//  private UserService userService;
//  @Autowired
//  private CategoryService categoryService;
//  @Autowired
//  private MyPageMenuService myPageMenuService;
//  @Autowired
//  private OfflineAuctionCommonService offlineAuctionCommonService;
//  @Autowired
//  private MessageService messageService;
//
//  public void sessionUserInfo(JoinPoint joinPoint) throws Throwable {
//    Model model = null;
//    HttpServletRequest request = null;
//    UserInfoDetailEntity userInfoEntity = null;
//    String menuUrl = null;
//    Boolean isUserPage = false;
//    String userId = "";
//    for (Object obj : joinPoint.getArgs()) {
//      if (obj instanceof Model) {
//        model = (Model) obj;
//      } else if (obj instanceof HttpServletRequest) {
//        request = (HttpServletRequest) obj;
//        try {
//          menuUrl = request.getRequestURI();
//          if (!menuUrl.toLowerCase().contains("admin")) {
//            isUserPage = true;
//          }
//
//          if (request.getSession().getAttribute(LoginSession.INFO) != null) {
//            userId = (String) request.getSession().getAttribute(LoginSession.ID);
//
//            Cookie[] cookies = request.getCookies();
//            // 회원정보 select
//            userInfoEntity = userService.getUserInfoFromSession(userId);
//            // 회원정보 세션 갱신
//            request.getSession().setAttribute(LoginSession.INFO, userInfoEntity);
//            request.getSession().setAttribute(LoginSession.KEY, userInfoEntity.getUserKey());
//            request.getSession().setAttribute(LoginSession.ID, userInfoEntity.getUserId());
//            request.getSession().setAttribute(LoginSession.NAME, userInfoEntity.getUserName());
//
//            if (!menuUrl.toLowerCase().contains("mypage")) {
//              menuUrl = null;
//            }
//          }
//        } catch (Exception e) {
//          LOGGER.info("오류 : ", e);
//          throw e;
//        }
//      }
//    }
//
//    if (model != null)
//
//    {
//      if (isUserPage) { // 사용자 페이지에서만
//        if (!StringUtils.isEmpty(userId)) {
//          model.addAttribute("holdEmoney", userService.getHoldEmoney(userId));
//          model.addAttribute("messageCount", messageService.unconfirmedMessageCount(userId));
//        }
//
//        if (!StringUtils.isEmpty(menuUrl)) {
//          model.addAttribute("myPageMenuDescription", myPageMenuService.getMyPageMenuDescription(menuUrl));
//        }
//
//        List<CategoryEntity> categoryEntityList = null;
//        List<String> offlineYearHeaderList = null;
//        if (request != null) {
//          if (request.getSession().getAttribute(Default.CategoryListName.ONLINE_CATEGORY_LIST) == null) {
//            request.getSession().setAttribute(Default.CategoryListName.ONLINE_CATEGORY_LIST, categoryService.getMenuCategoryList("02", "00"));
//            model.addAttribute("onlineCategoryList", categoryService.getMenuCategoryList("02", "00"));
//          } else {
//            categoryEntityList = (List<CategoryEntity>) request.getSession().getAttribute(Default.CategoryListName.ONLINE_CATEGORY_LIST);
//            model.addAttribute("onlineCategoryList", categoryEntityList);
//          }
//
//          if (request.getSession().getAttribute(Default.CategoryListName.OFFLINE_YEAR_HEADER_LIST) == null) {
//            request.getSession().setAttribute(Default.CategoryListName.OFFLINE_YEAR_HEADER_LIST, offlineAuctionCommonService.getYearListInAllRound(Code.OfflineAuction.Menu.HEADER));
//            model.addAttribute("offlineYearHeaderList", offlineAuctionCommonService.getYearListInAllRound(Code.OfflineAuction.Menu.HEADER));
//          } else {
//            offlineYearHeaderList = (List<String>) request.getSession().getAttribute(Default.CategoryListName.OFFLINE_YEAR_HEADER_LIST);
//            model.addAttribute("offlineYearHeaderList", offlineYearHeaderList);
//          }
//        }
//      }
//
//      // TODO : 현재 년도 기준으로 만든 년도 LIST(마이그레이션 후 하단의 회차기준으로 만든 년도LIST로 변경)
////      model.addAttribute("offlineYearHeaderList", offlineAuctionCommonService.getYearList(Code.OfflineAuction.Menu.HEADER));
////      model.addAttribute("offlineYearFooterList", offlineAuctionCommonService.getYearList(Code.OfflineAuction.Menu.FOOTER));
//
//    }
//  }
//}
