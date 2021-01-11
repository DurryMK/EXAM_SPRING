package com.des.client.core.exceptionHandler;

import com.des.client.core.exception.RunningException;
import com.des.client.entity.system.Emap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/8 14:31
 *
 * 统一异常处理
 */

@ControllerAdvice
public class ExceptionController {

    private Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(RunningException.class)
    @ResponseBody
    public Map appException(HttpServletResponse response, HttpServletRequest request, RunningException ex) {
        Emap em = new Emap();
        String URL = request.getRequestURL().toString();
        try {
            URL = URL.split("/exam_du")[1];
        } catch (Exception e) {
            URL = request.getRequestURL().toString();
        }
        log.debug("[SOME ERROR] : "+URL);
        log.debug("Error Code：[" + response.getStatus()+"]");
        log.error("Error Info ======> ",ex);
        return em.exception();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map globalException(HttpServletResponse response, HttpServletRequest request, Exception ex) {
        Emap em = new Emap();
        String URL = request.getRequestURL().toString();
        try {
            URL = URL.split("/exam_du")[1];
        } catch (Exception e) {
            URL = request.getRequestURL().toString();
        }
        log.debug("[SOME ERROR] : "+URL);
        log.debug("Error Code：[" + response.getStatus()+"]");
        log.error("Error Info ======> ",ex);
        return em.exception();
    }
}
