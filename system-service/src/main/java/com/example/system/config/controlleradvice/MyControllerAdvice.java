package com.example.system.config.controlleradvice;

import com.common.core.basebean.ResponseBean;
import com.common.core.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class MyControllerAdvice {
    /**
     * 全局异常处理，反正异常返回统一格式的map
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseBean exceptionHandler(Exception ex) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(500);
        responseBean.setMsg(ex.getMessage());
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return responseBean;
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     *
     * @param myex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public ResponseBean myExceptionHandler(MyException myex) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(myex.getCode());
        responseBean.setMsg(myex.getMessage());
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return responseBean;
    }
}
