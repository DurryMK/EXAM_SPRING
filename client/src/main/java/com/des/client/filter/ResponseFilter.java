package com.des.client.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ResponseFilter implements Filter {
    /**
     * 配置该过滤器
     * 服务器可以接收跨域请求
     * 可以保存session状态
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        // 指定允许其他域名访问
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:7777");
        // 响应类型/**/
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        // 响应头设置
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        // 响应头设置
        response.setHeader("Access-Control-Allow-Credentials", "true");

        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
