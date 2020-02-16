package com.isource.query;

public interface OrderByInterface extends QueryInterface{

//    /**
//     * 排序
//     * @return 查询对象
//     */
//    OrderByInterface orderBy();

    /**
     * 升序
     * @param field 字段名
     * @return 查询对象
     */
    OrderByInterface asc(String field);

    /**
     * 降序
     * @param field 字段名
     * @return 查询对象
     */
    OrderByInterface desc(String field);
}
