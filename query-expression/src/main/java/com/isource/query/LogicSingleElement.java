package com.isource.query;

import java.util.List;

/**
 * 单节点逻辑条件节点
 */
public class LogicSingleElement extends BaseLogicElement {

    FieldElement fieldElement;

    /**
     * 默认构造函数
     * @param parent 上级节点
     */
    public LogicSingleElement(LogicInterface parent){
        super(parent);
    }
    /**
     * 获取查询条件字段对象
     *
     * @param fieldName
     * @return 查询条件字段对象
     */
    @Override
    public FieldElement field(String fieldName) {
        this.fieldElement = new FieldElement(this,fieldName);
        return this.fieldElement;
    }

    /**
     * 并且
     *
     * @return 逻辑运算节点
     */
    @Override
    public LogicInterface and() {
        return this.parent.and();
    }

    /**
     * 或者
     *
     * @return 逻辑运算节点
     */
    @Override
    public LogicInterface or() {
        return this.parent.or();
    }

    /**
     * 获取下个逻辑分支
     * 注意：内部使用不对外
     *
     * @return 下个可用的逻辑分支
     */
    @Override
    public LogicInterface next() {
        return this.parent.next();
    }

    @Override
    public String toString() {
        return this.fieldElement.toString();
    }

    /**
     * 获取用于Mybatis 查询的 参数集合
     *
     * @return Map 类型的参数
     */
    @Override
    public MapParameter getMapParameter() {
        return this.fieldElement.getMapParameter();
    }

    /**
     * 生成指定数量的参数序号
     *
     * @param count 数量
     * @return 第一个参数序号
     */
    @Override
    public List<String> generatorParameterIndex(int count) {
        return this.parent.generatorParameterIndex(count);
    }

    /**
     * 获取查询对象所对应的SQL语句
     *
     * @return SQL
     */
    @Override
    public String getSql() {
        return this.fieldElement.getSql();
    }

}
