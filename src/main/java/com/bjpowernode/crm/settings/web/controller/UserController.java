package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.domain.returnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        //请求转发到登录界面
        return "settings/qx/user/login";
    }


    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
    public Object login(String loginAct, String loginPwd, String isRemPwd , HttpServletRequest request, HttpSession session){

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        User user = userService.queryUserByLoginActAndPwd(map);

        //登录校验
        returnObject returnObject = new returnObject();
        if(user == null){

            //帐号密码不正确
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("帐号密码不正确");

        }else{

            if(DateUtils.formateDateTime(new Date()).compareTo(user.getExpireTime()) > 0){

                //可登录权限超时
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("可登录权限超时");

            }else if(user.getLockState().equals(Contants.RETURN_OBJECT_CODE_FAIL)){

                //登录状态被锁定
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登录状态被锁定");

            }else if(!user.getAllowIps().contains(request.getRemoteAddr())){

                //ip地址登录受限
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("ip地址登录受限");

            }else{ //登录成功

                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                //把user保存到session中
                session.setAttribute(Contants.SESSION_USER,user);

            }
        }
        return returnObject;
    }
}
