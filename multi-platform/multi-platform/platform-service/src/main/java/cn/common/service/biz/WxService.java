package cn.common.service.biz;

import cn.common.req.biz.app.wx.WxBaseReq;
import cn.common.req.biz.app.wx.WxPageQrCode;
import cn.common.resp.biz.app.wx.WxPhoneResp;

/**
 * @author Singer create by singer - Singer email:singer-coder@qq.com
 * @projectName multi-platform
 * @Description: 微信相关服务
 * @date 2023-02-25
 */
public interface WxService {

    /**
     * 拿到微信用户的手机号
     * @description:
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/25
     * @param getPhoneCode getPhoneNumber返回的code
     * @param weChatAccessToken  weChatAccessToken
     * @return
     */
    WxPhoneResp wxChatPhone(String getPhoneCode, String weChatAccessToken);

    /**
     * 生成微信页面二维码
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/26
     * @param wxPageQrCode 微信页面二维码请求参数
     * @return
     */
    String wxPageQrCodeBase64(WxPageQrCode wxPageQrCode);

    /**
     * 拿到微信AccessToken
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2021/2/25
     * @param wxBaseReq 拿到微信AccessToken的请求参数
     * @return String
     */
    String weChatAccessToken(WxBaseReq wxBaseReq);


}
