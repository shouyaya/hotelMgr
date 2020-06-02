package com.yzy.Dao;

import com.yzy.Entity.Login;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * LoginMapper 数据访问类
 * @author xlei @qq 251425887 @tel 13352818008
 * @Email dlei0009@163.com
 * @date 2020-05-21 22:51:24
 * @version 1.0
 */

@Repository("loginDao")
public interface LoginMapper {

    Login findByUsername(String username);

    List<Login> findAll();






}