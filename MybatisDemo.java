package org.apache.ibatis.test;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.pojo.User;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.UnknownTypeHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisDemo {

    public static void main(String[] args) throws IOException {
        String resource = "org/apache/ibatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

     //   SqlSession session = sqlSessionFactory.openSession();
        SelectMapper<User> mapper = new SelectMapper(sqlSessionFactory.getConfiguration(),sqlSessionFactory.openSession());
      //  System.out.println(mapper.selectAll(User.class));
        Map<String,Object> map = new HashMap<String,Object> ();
        User user = new User();
        map.put("param",user);
        map.put("offset",0);
        map.put("limit",5);
        List<User> users1 = mapper.selectPage(User.class, map);
        List<User> users2 = mapper.selectPage(User.class, map);
        List<User> users3 = mapper.selectPage(User.class, map);
        System.out.println(users1);

//        List<Object> list = session.selectList("UserMapper.selectUser", "456");
//        System.out.println(list);
    }
}


class AsynSelect implements Runnable {

    private SqlSession session;

    AsynSelect(SqlSession session) {
        this.session = session;
    }

    @Override
    public void run() {
        User user1 = (User) session.selectOne("UserMapper.selectUser", 1);
        System.out.println(user1);
    }

    //            User user = new User();
//            user.setId(1);
//            user.setName("fuxiangqin");
//            session.update("UserMapper.updateUser", user);
//            //System.out.println(user);
//

//            //session.commit();
//        session.commit();
//        Configuration configuration = sqlSessionFactory.getConfiguration();
//        String id = "UserMapper.selectUser";
//        List<ResultMap> resultMaps = new ArrayList<>();
//        ResultMap inlineResultMap = new ResultMap.Builder(configuration, id + "-Inline", User.class, new ArrayList<ResultMapping>(), null).build();
//        resultMaps.add(inlineResultMap);
//        List<ParameterMapping> parameterMappingList = new ArrayList<ParameterMapping>();
//        UnknownTypeHandler handler = new UnknownTypeHandler(configuration.getTypeHandlerRegistry());
//        ParameterMapping.Builder parame = new ParameterMapping.Builder(configuration,"id",handler);
//        parameterMappingList.add(parame.build());
//        SqlSource sqlSource = new StaticSqlSource(configuration, "select * from User where id = ?",parameterMappingList);
//        MappedStatement.Builder builder = new MappedStatement.Builder(configuration, id, sqlSource, SqlCommandType.SELECT).resultMaps(resultMaps);
//        MappedStatement mappedStatement = builder.build();
//        configuration.addMappedStatement(mappedStatement);
}