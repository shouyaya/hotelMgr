package com.yzy.Service.Impl;

import com.yzy.Dao.LoginMapper;
import com.yzy.Entity.Login;
import com.yzy.Service.loginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("loginService")
public class loginServiceImpl implements loginService {

    @Resource(name = "loginDao")
    private LoginMapper loginMapper;
    @Override
    public Login findByUsername(String username) {
        return loginMapper.findByUsername(username);
    }

    @Override
    public List<Login> findAll() {
        return loginMapper.findAll();
    }
}
