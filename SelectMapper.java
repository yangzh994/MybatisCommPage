package org.apache.ibatis.test;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class SelectMapper<T>{

    private RegisterMappedStatement registerMappedStatement;
    private SqlSession sqlSession;
    public SelectMapper(Configuration configuration, SqlSession sqlSession){
        this.registerMappedStatement = RegisterMappedStatement.getRegisterMappedStatement(configuration);
        this.sqlSession = sqlSession;
    }

    public List<T> selectPage(Class clazz,Map map){
        registerMappedStatement.registerMappedStatement(clazz,SelectType.PAGE);
        return sqlSession.selectList(StatementPre.PRE_PAGE_ID + clazz.getName(),map);
    }

    public List<T> selectAll(Class clazz,Map map){
        registerMappedStatement.registerMappedStatement(clazz,SelectType.ALL);
        return sqlSession.selectList(StatementPre.PRE_ALL_ID + clazz.getName(),map);
    }

    public Integer selectCount(Class clazz,Map map){
        registerMappedStatement.registerMappedStatement(clazz,SelectType.COUNT);
        List<Object> objects = sqlSession.selectList(StatementPre.PRE_COUNT_ID + clazz.getName(), map);
        if(objects == null || objects.size() == 0) return 0;
        return (Integer)objects.get(0);
    }


}
