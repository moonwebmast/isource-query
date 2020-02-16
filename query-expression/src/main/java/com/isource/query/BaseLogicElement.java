package com.isource.query;

/**
 * 逻辑运算的基类
 * @author pangyl
 */
public  abstract class BaseLogicElement implements LogicInterface{

    /**
     * 上级节点
     */
    LogicInterface parent;

    public BaseLogicElement(LogicInterface parent){
        this.parent = parent;
    }
    /**
     * 获取查询条件字段对象
     * @param fieldName
     * @return 查询条件字段对象
     */
    @Override
    public abstract FieldElement field(String fieldName) ;

    /**
     * 并且
     * @return 逻辑运算节点
     */
    @Override
    public abstract LogicInterface and() ;

    /**
     * 或者
     * @return 逻辑运算节点
     */
    @Override
    public abstract LogicInterface or() ;

    /**
     * 获取下个逻辑分支
     * 注意：内部使用不对外
     *
     * @return 下个可用的逻辑分支
     */
    @Override
    public abstract LogicInterface next() ;

    /**
     * 查询字段
     * @param fields
     * @return 查询对象
     */
    @Override
    public QueryInterface select(String... fields) {
        return parent.select(fields);
    }

    /**
     * 查询对象的条件
     *
     * @return 查询对象
     */
    @Override
    public QueryInterface where() {
        return parent.where();
    }

    /**
     * 排序
     *
     * @return 查询对象
     */
    @Override
    public OrderByInterface orderBy() {
        return parent.orderBy();
    }

    /**
     * 限制返回记录数
     *
     * @param pageSize
     * @return 查询对象
     */
    @Override
    public QueryInterface limit(int pageSize) {
        return parent.limit(pageSize);
    }

    /**
     * 分页
     *
     * @param from     起始记录数
     * @param pageSize 读取记录数量
     * @return 查询对象
     */
    @Override
    public QueryInterface limit(int from, int pageSize) {
        return parent.limit(from,pageSize);
    }

    /**
     * 生成当前查询参数序号
     *
     * @return
     */
    @Override
    public String generatorParameterIndex() {
        return parent.generatorParameterIndex();
    }
}
