package com.yzy.Service;

import com.yzy.Entity.Login;

import java.util.List;


public interface loginService {
    Login findByUsername(String username);
    List<Login> findAll();
}
