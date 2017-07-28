package com.bw.fudepeng.repository;

import com.bw.fudepeng.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by fudepeng on 2017/7/28.
 * JPA
 */
public interface UserRep extends JpaRepository<User, Integer> {

    /**
     * 登录login
     */
    User findUserByUsernameAndPwd(String username, String pwd);

    /**
     * 根据id查询信息
     */
    User findUserById(Integer id);

}
