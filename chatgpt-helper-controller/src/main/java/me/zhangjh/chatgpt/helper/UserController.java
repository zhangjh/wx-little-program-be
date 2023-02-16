package me.zhangjh.chatgpt.helper;

import lombok.extern.slf4j.Slf4j;
import me.zhangjh.chatgpt.helper.dto.PageDTO;
import me.zhangjh.chatgpt.helper.model.PageQuery;
import me.zhangjh.chatgpt.helper.model.TblAccount;
import me.zhangjh.chatgpt.helper.model.TblChat;
import me.zhangjh.chatgpt.helper.request.AccountRequest;
import me.zhangjh.chatgpt.helper.request.UserRequest;
import me.zhangjh.chatgpt.helper.service.TblAccountService;
import me.zhangjh.chatgpt.helper.service.TblChatService;
import me.zhangjh.chatgpt.helper.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

            PageQuery<TblChat> query = new PageQuery<>();
            TblChat chat = new TblChat();
            chat.setUserId(userId);
            query.setData(chat);
            query.setPageIndex(userRequest.getPageIndex());
            query.setPageSize(userRequest.getPageSize());

            PageDTO<TblChat> tblChatPageDTO = tblChatService.paginQuery(query);
            return Response.success(tblChatPageDTO.getData());
        } catch (Throwable t) {
            log.error("queryChatsByUserId exception, ", t);
            return Response.fail(t.getMessage());
        }
    }

    @RequestMapping("/saveUser")
    public Response<Void> saveUser(AccountRequest req) {
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
