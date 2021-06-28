package com.atguigu.mpdemo;

import com.atguigu.mpdemo.entity.User;
import com.atguigu.mpdemo.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        user.setName("上古");
        user.setAge(30);
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

    // 分页查询
    @Test
    public void testPage() {

        //1.创建page对象
        //传入两个参数：当前页 和 每页显示的记录数
        Page<User> userPage = new Page<>(1, 3);
        // 调用mp的分页查询方法
        // 调用mp分页查询的过程中，底层封装，把分页所有数据封装到userPage对象里面
        userMapper.selectPage(userPage, null);

        // 通过userPage对象获取分页数据
        System.out.println(userPage.getCurrent());// 当前页
        System.out.println(userPage.getRecords());// 每页数据list集合
        System.out.println(userPage.getPages());// 总页数
        System.out.println(userPage.getSize());// 每页显示记录数
        System.out.println(userPage.getTotal());// 总记录数

        System.out.println(userPage.hasNext());// 下一页
        System.out.println(userPage.hasPrevious());// 上一页

    }

    // 物理删除
    @Test
    public void testDeleteById() {
        int i = userMapper.deleteById(1409343818280534017L);
        System.out.println(i);
    }

    // 批量删除
    @Test
    public void testDeleteBatchIds() {
        final int result = userMapper.deleteBatchIds(Arrays.asList(2, 3));
        System.out.println(result);
    }

}
