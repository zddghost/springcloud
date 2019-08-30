package com.common.core.basecontroller;

import com.common.core.basebean.ResponseBean;
import com.common.core.enumset.EnumSet;



public class BaseController {

    public ResponseBean success() {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(EnumSet.SUCCESS.getCode());
        responseBean.setMsg(EnumSet.SUCCESS.getMessage());
        return responseBean;
    }
    public ResponseBean success(Object data) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(EnumSet.SUCCESS.getCode());
        responseBean.setMsg(EnumSet.SUCCESS.getMessage());
        responseBean.setData(data);
        return responseBean;
    }

    public ResponseBean failure() {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(EnumSet.FAILURE.getCode());
        responseBean.setMsg(EnumSet.FAILURE.getMessage());
        return responseBean;
    }
    public ResponseBean failure(String msg) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(EnumSet.FAILURE.getCode());
        responseBean.setMsg(msg);
        return responseBean;
    }
    public ResponseBean failure(Integer code,String msg) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(code);
        responseBean.setMsg(msg);
        return responseBean;
    }

}