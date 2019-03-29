package net.btt.traffic.bus.common.util.ibatis;


import java.util.List;
import java.util.Map;

public interface SelectMapper<T>{

     Integer selectCount(Class clazz,Map map);

     List<T> selectAll(Class clazz,Map map);

     List<T> selectPage(Class clazz, Map map);

}
