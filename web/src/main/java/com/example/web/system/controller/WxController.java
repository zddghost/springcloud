package com.example.web.system.controller;

import com.common.core.exception.MyException;
import com.common.core.basebean.ResponseBean;
import com.example.web.common.basecontroller.BaseController;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("wx")
public class WxController extends BaseController {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 认证成功后的跳转地址
     */
    @Value("${wx.auth.forwardurl}")
    private String wechatForwardUrl;
    /**
     * 获取code的认证回调接口
     */
    @Value("${wx.auth.redirecturl}")
    private String wechatRedirectUrl;

    private static final String SNS_API_USER_INFO = "snsapi_userinfo";

    @Autowired
    private WxMpService wxMpService;

    /**
     * 微信登陆跳转
     */
    @GetMapping("/jump")
    public void login(@RequestParam("url") String url, HttpServletResponse response) {
        if (!StringUtils.isEmpty(url)) {
            //获取跳转地址
            this.wechatForwardUrl = url;
        }
        String authUrl = wxMpService.oauth2buildAuthorizationUrl(wechatRedirectUrl, SNS_API_USER_INFO, URIUtil.encodeURIComponent(wechatForwardUrl));
        try {
            //重定向到wx首页
            response.sendRedirect(authUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 跳转成功获取用户授权
     *
     * @param code
     * @return
     */
    @GetMapping("accessToken")
    public void getAccessToken(@RequestParam(name = "code") String code, HttpServletResponse response) {
        if (StringUtils.isEmpty(code)) {
            throw new MyException(0, "code不存在!");
        }
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            //获取 accessToken
            String accessToken = wxMpOAuth2AccessToken.getAccessToken();
            // 获取用户微信账户信息
            WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "");

            String url = "";
            //重定向到微信页面
            url = wechatForwardUrl + "?accessToken=" + accessToken;
            response.sendRedirect(url);
        } catch (WxErrorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*微信js签名接口*/
    @GetMapping("getJsapiTicket")
    public ResponseBean getJsapiTicket(String url) {
        WxJsapiSignature jsapiSignature = null;
        try {
            jsapiSignature = wxMpService.createJsapiSignature(url);
        } catch (WxErrorException e) {
            e.printStackTrace();
            System.err.print(e.getError());
        }
        return success(jsapiSignature);
    }
}
