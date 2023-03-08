package me.zhangjh.wx.program.chatgpt;

import lombok.extern.slf4j.Slf4j;
import me.zhangjh.share.response.Response;
import me.zhangjh.wx.program.chatgpt.request.ItemRequest;
import me.zhangjh.wx.program.model.chatgpt.TblShare;
import me.zhangjh.wx.program.service.chatgpt.TblShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author njhxzhangjihong@126.com
 * @date 3:13 PM 2023/2/6
 * @Description
 */
@RestController
@RequestMapping("/wx/chatHelper")
@CrossOrigin
@Slf4j
public class ShareController {

    @Autowired
    private TblShareService tblShareService;

    public Response<Boolean> shareItem(ItemRequest itemRequest, HttpServletRequest req) {
        try {
            String userId = req.getHeader("userId");
            itemRequest.headerCheck(req);
            itemRequest.check();

            TblShare tblShare = new TblShare();
            tblShare.setUserId(userId);
            tblShare.setItemId(itemRequest.getItemId());
            // 已经分享过的不再重复分享
            TblShare sharedItem = tblShareService.queryByQuery(tblShare);
            if(sharedItem != null) {
                log.info("重复发起分享，itemId: {}", itemRequest.getItemId());
                return Response.success(true);
            }
            tblShare.setTag(itemRequest.getTag());
            tblShare.setShareType(itemRequest.getShareType());
            tblShare.setTarget(itemRequest.getTarget());

            tblShareService.insert(tblShare);
            return Response.success(true);
        } catch (Throwable t) {
            log.error("shareItem exception,", t);
            return Response.fail(t.getMessage());
        }
    }

}
