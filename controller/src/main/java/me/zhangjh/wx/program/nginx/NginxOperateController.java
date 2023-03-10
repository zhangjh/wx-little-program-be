package me.zhangjh.wx.program.nginx;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.zhangjh.share.response.Response;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

/**
 * @author zhangjh451@midea.com
 * @date 9:22 PM 2023/3/10
 * @Description
 */
@RestController
@RequestMapping("/wx/nginx")
@CrossOrigin
@Slf4j
public class NginxOperateController {

    private static final String NGINX_CONFIG_FILE_PATH = "/etc/nginx/conf.d/wx.conf";

    private static final String NGINX_RESTART_COMMAND = "sudo nginx -s reload";

    @SneakyThrows
    @GetMapping("/update")
    public Response<Void> updateAndReload(String imgUrl, HttpServletRequest req) {
        String secret = req.getHeader("secret");
        Assert.isTrue(Objects.equals(secret, "wired_sheep"), "secret错误");
        // 读取nginx配置文件内容
        StringBuilder configContentBuilder = new StringBuilder();
        File configFile = new File(NGINX_CONFIG_FILE_PATH);
        BufferedReader reader = new BufferedReader(new FileReader(configFile));
        String line;
        while ((line = reader.readLine()) != null) {
            if(line.contains("return 301 https://wx1.sinaimg.cn")) {
                line = "\t\t\treturn 301 " + imgUrl + ";";
            }
            configContentBuilder.append(line).append("\n");

        }
        reader.close();

        // 修改nginx配置文件内容
        String configContent = configContentBuilder.toString();
        System.out.println(configContent);

        // 将修改后的配置文件内容写入到nginx配置文件中
        FileWriter writer = new FileWriter(configFile);
        writer.write(configContent);
        writer.close();

        // 重启nginx服务
        Process process = Runtime.getRuntime().exec(NGINX_RESTART_COMMAND);
        int exitCode = process.waitFor();
        Assert.isTrue(exitCode == 0, "Nginx重启失败");
        return Response.success(null);
    }
}
