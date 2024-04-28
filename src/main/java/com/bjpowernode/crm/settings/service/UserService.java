package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.domain.User;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface UserService {
    User queryUserByLoginActAndPwd(Map<String,Object> map);
}
