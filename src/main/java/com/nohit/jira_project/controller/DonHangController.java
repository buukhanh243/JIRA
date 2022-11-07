package com.nohit.jira_project.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.nohit.jira_project.service.*;
import com.nohit.jira_project.util.*;

import static com.nohit.jira_project.common.Bean.*;
import static com.nohit.jira_project.constant.ApplicationConstant.Menu.*;
import static com.nohit.jira_project.constant.AttributeConstant.*;
import static com.nohit.jira_project.constant.TemplateConstant.*;
import static com.nohit.jira_project.constant.ViewConstant.*;

@Controller
@RequestMapping(ORDER_VIEW)
public class DonHangController {
    @Autowired
    private DonHangService donHangService;

    @Autowired
    private AuthenticationUtil authenticationUtil;

    @Autowired
    private ApplicationUtil applicationUtil;

    @GetMapping("")
    public String order() {
        _isMsgShow = true;
        _msg = "Cần chọn 1 đơn hàng để xem!";
        return REDIRECT_PREFIX + HISTORY_VIEW;
    }

    // Load order
    @GetMapping(FIND_VIEW)
    public ModelAndView orderFind(int id) {
        var client = authenticationUtil.getAccount();
        // check current account still valid
        if (client == null) {
            return new ModelAndView(REDIRECT_PREFIX + LOGOUT_VIEW);
        } else {
            var mav = new ModelAndView(ORDER_TEMP);
            mav.addObject(TITLE_PARAM, DON_HANG);
            mav.addObject(CART_PARAM, client.getGioHang());
            mav.addObject(LOGIN_PARAM, client != null);
            mav.addObject(ORDER_PARAM, donHangService.getDonHang(id));
            _isMsgShow = applicationUtil.showMessageBox(mav);
            return mav;
        }
    }
}
