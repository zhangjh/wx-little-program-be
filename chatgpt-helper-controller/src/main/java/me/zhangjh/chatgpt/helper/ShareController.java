package me.zhangjh.chatgpt.helper;

import lombok.extern.slf4j.Slf4j;
import me.zhangjh.chatgpt.helper.dto.PageDTO;
import me.zhangjh.chatgpt.helper.model.PageQuery;
import me.zhangjh.chatgpt.helper.model.TblShare;
import me.zhangjh.chatgpt.helper.request.ItemRequest;
import me.zhangjh.chatgpt.helper.service.TblShareService;
import me.zhangjh.chatgpt.helper.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 3:13 PM 2023/2/6
 * @Description
 */
@RestController
@RequestMapping("/chatHelper")
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

    public Response<List<TblShare>> getShareItems(ItemRequest itemRequest, HttpServletRequest req) {
        try {
            String userId = req.getHeader("userId");
            itemRequest.headerCheck(req);
            itemRequest.check();

            PageQuery<TblShare> pageQuery = new PageQuery<>();
            TblShare tblShare = new TblShare();
            // 获取所有的分享结果
            if(!itemRequest.getGetAllFlag()) {
                tblShare.setUserId(userId);
            }
            tblShare.setType(itemRequest.getType());
            tblShare.setTarget(itemRequest.getTag());
            tblShare.setShareType(itemRequest.getShareType());
            pageQuery.setData(tblShare);
            pageQuery.setPageIndex(itemRequest.getPageIndex());
            pageQuery.setPageSize(itemRequest.getPageSize());
            PageDTO<TblShare> pageDTO = tblShareService.paginQuery(pageQuery);
            return Response.success(pageDTO.getData());
        } catch (Throwable t) {
            log.error("getShareItems exception, ", t);
            return Response.fail(t.getMessage());
        }
    }
}
