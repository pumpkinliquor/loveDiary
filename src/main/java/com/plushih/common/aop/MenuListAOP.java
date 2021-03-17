package com.plushih.common.aop;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@Aspect
public class MenuListAOP {
//    private static final Logger LOGGER = LoggerFactory.getLogger(MenuListAOP.class);



    public static void main(String[] args)  throws Throwable {
        Model model = null;
        HttpServletRequest request = null;
//        UserInfoDetailEntity userInfoEntity = null;
        String menuUrl = null;
        Boolean isUserPage = false;
        String userId = "";

        //    List<MenuEntity> menuList   = menuService.getMenuList();
        model.addAttribute("menuList", "test");
    }



    public void menuList() {
        Model model = null;
        HttpServletRequest request = null;
//        UserInfoDetailEntity userInfoEntity = null;
        String menuUrl = null;
        Boolean isUserPage = false;
        String userId = "";
        Object menuEntity = "test";
//        List<MenuEntity> menuList   = menuService.getMenuList();
       // model.addAttribute("menuList", "asdf");
        model.addAttribute("menuList", menuEntity);
        if (model != null)
        {
            if (isUserPage) { // 사용자 페이지에서만
                if (!StringUtils.isEmpty(userId)) {
//                    model.addAttribute("holdEmoney", userService.getHoldEmoney(userId));
//                    model.addAttribute("messageCount", messageService.unconfirmedMessageCount(userId));
                }

                if (!StringUtils.isEmpty(menuUrl)) {
//                    model.addAttribute("myPageMenuDescription", myPageMenuService.getMyPageMenuDescription(menuUrl));
                }

            }
//      model.addAttribute("offlineYearHeaderList", offlineAuctionCommonService.getYearList(Code.OfflineAuction.Menu.HEADER));
//      model.addAttribute("offlineYearFooterList", offlineAuctionCommonService.getYearList(Code.OfflineAuction.Menu.FOOTER));

        }
    }
}
