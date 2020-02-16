package com.isource.query;

import java.util.List;

public interface QueryInterface {

    /**
     * 查询字段
     * @param fields 查询的字段列表
     * @return 查询对象
     */
    QueryInterface select(String ... fields);

    /**
     * 查询对象的条件
     * @return 查询对象
     */
    QueryInterface where();

    /**
     * 排序
     * @return 查询对象
     */
    QueryInterface orderBy();

    /**
     * 限制返回记录数
     * @param pageSize 读取记录数量
     * @return 查询对象
     */
    QueryInterface limit(int pageSize);

    /**
     * 分页
     * @param from 起始记录数
     * @param pageSize 读取记录数量
     * @return 查询对象
     */
    QueryInterface limit(int from, int pageSize);

    /**
     * 获取用于Mybatis 查询的 参数集合
     * @return Map 类型的参数
     */
    MapParameter getMapParameter();

    /**
     * 生成当前查询参数序号
     * @return 参数序号
     */
    String generatorParameterIndex();

    /**
     * 生成指定数量的参数序号
     * @param count 数量
     * @return 第一个参数序号
     */
    List<String> generatorParameterIndex(int count);

    /**
     * 获取查询对象所对应的SQL语句
     * @return SQL
     */
    String getSql();

//    /**
//////     * 获取用与缓存结果集使用的Key()
//////     * @return
//////     */
//////    String getCacheKey();

}
