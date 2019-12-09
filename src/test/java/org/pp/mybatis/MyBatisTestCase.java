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

        // SqlSessionFactory build from config document and initialize all
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // open session and new Executor
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // get MapperProxyFactory instance from cache for create MapperProxy
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<UserEntity> userList = userMapper.listAllUser();

        System.out.println(JSON.toJSONString(userList));
    }
}
