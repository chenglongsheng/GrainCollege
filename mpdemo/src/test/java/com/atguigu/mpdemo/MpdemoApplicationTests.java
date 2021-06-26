package com.atguigu.mpdemo;

import com.atguigu.mpdemo.entity.User;
import com.atguigu.mpdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MpdemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /*
    查询所有数据
     */
    @Test
    void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

}
