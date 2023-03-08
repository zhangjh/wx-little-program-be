package me.zhangjh.wx.program.chatgpt;

import lombok.extern.slf4j.Slf4j;
import me.zhangjh.share.response.Response;
import me.zhangjh.wx.program.chatgpt.request.AccountRequest;
import me.zhangjh.wx.program.chatgpt.request.UserRequest;
import me.zhangjh.wx.program.dto.PageDTO;
import me.zhangjh.wx.program.model.chatgpt.PageChatQuery;
import me.zhangjh.wx.program.model.chatgpt.TblAccount;
import me.zhangjh.wx.program.model.chatgpt.TblChat;
import me.zhangjh.wx.program.service.chatgpt.TblAccountService;
import me.zhangjh.wx.program.service.chatgpt.TblChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 3:14 PM 2023/2/6
 * @Description 用户相关
 */
@RestController
@RequestMapping("/wx/chatHelper")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private TblAccountService tblAccountService;

    @Autowired
    private TblChatService tblChatService;

    @RequestMapping("/queryUserById")
    public Response<TblAccount> queryUserById(UserRequest userRequest, HttpServletRequest req) {
        try {
            String userId = req.getHeader("userId");
            userRequest.headerCheck(req);
            userRequest.check();

            TblAccount tblAccount = tblAccountService.queryById(userId);
            return Response.success(tblAccount);
        } catch (Throwable t) {
            log.error("queryUserById exception, ", t);
            return Response.fail(t.getMessage());
        }
    }

    @RequestMapping("/queryChatsByUserId")
    public Response<List<TblChat>> queryChatsByUserId(UserRequest userRequest, HttpServletRequest req) {
        try {
            String userId = req.getHeader("userId");
            userRequest.headerCheck(req);
            userRequest.check();

            PageChatQuery query = new PageChatQuery();
            query.setUserId(userId);
            query.setPageIndex(userRequest.getPageIndex());
            query.setPageSize(userRequest.getPageSize());

            PageDTO<TblChat> tblChatPageDTO = tblChatService.paginQuery(query);
            return Response.success(tblChatPageDTO.getData());
        } catch (Throwable t) {
            log.error("queryChatsByUserId exception, ", t);
            return Response.fail(t.getMessage());
        }
    }

    @PostMapping("/saveUser")
    public Response<Void> saveUser(@RequestBody AccountRequest req) {
        try {
            String userId = req.getUserId();
            String avatar = req.getAvatarUrl();
            String nickName = req.getNickName();
            TblAccount tblAccount = new TblAccount();
            tblAccount.setAvatar(avatar);
            tblAccount.setNickName(nickName);
            // 小程序用户
            tblAccount.setExtType(1);
            tblAccount.setExtId(userId);
            tblAccountService.insert(tblAccount);
            return Response.success(null);
        } catch (Throwable t) {
            log.error("saveUser exception, ", t);
            return Response.fail(t.getMessage());
        }
    }
}
