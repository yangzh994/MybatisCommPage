package net.btt.traffic.bus.common.util.ibatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;

public class SelectMapperImpl<T> implements SelectMapper {

    public SqlSessionTemplate template;

    private RegisterMappedStatement registerMappedStatement;

    private SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.template = new SqlSessionTemplate(sqlSessionFactory);
        this.registerMappedStatement = RegisterMappedStatement.getRegisterMappedStatement(sqlSessionFactory.getConfiguration());
    }

    public List<T> selectPage(Class clazz, Map map){
        registerMappedStatement.registerMappedStatement(clazz,SelectType.PAGE);
        return template.selectList(StatementPre.PRE_PAGE_ID + clazz.getName(),map);
    }

    public List<T> selectAll(Class clazz,Map map){
        registerMappedStatement.registerMappedStatement(clazz,SelectType.ALL);
        return template.selectList(StatementPre.PRE_ALL_ID + clazz.getName(),map);
    }

    public Integer selectCount(Class clazz,Map map){
        registerMappedStatement.registerMappedStatement(clazz,SelectType.COUNT);
        List<Object> objects = template.selectList(StatementPre.PRE_COUNT_ID + clazz.getName(), map);
        if(objects == null || objects.size() == 0) return 0;
        return (Integer)objects.get(0);
    }
}
