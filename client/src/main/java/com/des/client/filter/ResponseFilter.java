package com.des.client.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RefreshScope
public class ResponseFilter implements Filter {
    /**
     * 配置该过滤器
     * 服务器可以接收跨域请求
     * 可以保存session状态 注：除谷歌以外
     * <p>
     * 谷歌本地开发的 解决方案：chrome地址栏输入 chrome://flags/
     * 找到”SameSite by default cookies“，修改为disable,即可关闭浏览器限制
     */

    @Value("${custom.env}")
    private String env;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        // 指定允许其他域名访问
        response.setHeader("Access-Control-Allow-Origin", env);
        // 响应类型/**/
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,HEAD,CONNECT,TRACE,PUT");
        // 响应头设置
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,Type");
        // 响应头设置  允许携带Cookie信息 解决Session不一致的问题
        response.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
