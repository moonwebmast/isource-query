package com.isource.query;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询排序处理对象
 * @author pangyl
 */
public class OrderByElement extends BaseQuery implements OrderByInterface{

    private Map<String,String> dict = new LinkedHashMap<String, String>();

    /**
     * 构造函数
      * @param parent 上层节点
     */
    public OrderByElement(QueryInterface parent){
        super(parent);

    }

    /**
     * 重写Order by 节点
     * @return
     */
    @Override
    public OrderByInterface orderBy() {
        return this;
    }

    /**
     * 获取用于Mybatis 查询的 参数集合
     *
     * @return Map 类型的参数
     */
    @Override
    public MapParameter getMapParameter() {
        return null;
    }

    /**
     * 获取查询对象所对应的SQL语句
     *
     * @return SQL
     */
    @Override
    public String getSql() {
        return this.toString();
    }

    /**
     * 升序
     * @param field 字段名
     * @return 查询对象
     */
    @Override
    public OrderByInterface asc(String field){
        this.dict.put(field,"ASC");
        return this;
    }

    /**
     * 降序
     * @param field 字段名
     * @return 查询对象
     */
    public OrderByInterface desc(String field){
        this.dict.put(field,"DESC");
        return this;
    }

    @Override
    public String toString(){
        if(dict.isEmpty()){
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (String key: dict.keySet() ) {
            if(result.length() > 0){
                result.append(",");
            }
            result.append(key + " " + dict.get(key) + " ");
        }

        return "ORDER BY " + result.toString();
    }

}
