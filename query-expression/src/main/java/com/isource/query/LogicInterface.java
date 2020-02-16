package com.isource.query;

/**
 * Where 条件中的逻辑运算接口生命
 * @author pangyl
 */
public interface LogicInterface extends QueryInterface{

    /**
     * 重写排序对象
     * @return
     */
    @Override
    OrderByInterface orderBy();

    /**
     * 获取查询条件字段对象
     * @param fieldName
     * @return 查询条件字段对象
     */
    FieldElement field(String fieldName);

    /**
     * 并且
     * @return 逻辑运算节点
     */
    LogicInterface and();

    /**
     * 或者
     * @return 逻辑运算节点
     */
    LogicInterface or();

    /**
     * 获取剩余未处理的逻辑分支数量
     * @return 剩余未处理的逻辑分支数量
     */
    LogicInterface next();
}
