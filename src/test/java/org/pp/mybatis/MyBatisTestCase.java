package org.pp.mybatis;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.pp.mybatis.entity.UserEntity;
import org.pp.mybatis.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTestCase {

    @Test
    public void testMybatis() throws IOException {
        // 获取配置文件输入流
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 通过SqlSessionFactoryBuilder的build()方法创建SqlSessionFactory实例
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 调用openSession()方法创建SqlSession实例
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取UserMapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 执行Mapper方法，获取执行结果
        List<UserEntity> userList = userMapper.listAllUser();

        System.out.println(JSON.toJSONString(userList));
    }
}
