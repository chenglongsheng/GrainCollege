package com.atguigu.mpdemo;

import com.atguigu.mpdemo.entity.User;
import com.atguigu.mpdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
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

    /*
    添加用户
     */
    @Test
    public void addUser() {

        User user = new User();
        user.setName("中古");
        user.setAge(21);
        user.setEmail("Jerry@sina.com");

        // 手动设置
//        user.setCreateTime(new Date());
//        user.setUpdateTime(new Date());

        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    /*
    修改操作
     */
    @Test
    public void updateUser() {
        User user = new User();
        user.setId(1408999156663525378L);
        user.setAge(18);

        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    // 测试乐观锁
    // 先查询再修改
    @Test
    public void testOptimisticLocker() {
        // 根据id查数据
        User user = userMapper.selectById(1409153028505853953L);
        // 修改
        user.setName("任盈盈");
        userMapper.updateById(user);
    }

    // 多个id批量查询
    @Test
    public void testSelect1() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println(users);
    }

    @Test
    public void testSelectByMap() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Jack");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);

        users.forEach(System.out::println);

    }

}
