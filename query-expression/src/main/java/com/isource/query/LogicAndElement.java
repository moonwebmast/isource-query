package com.isource.query;

/**
 * And 逻辑操作元素定义
 */
public class LogicAndElement extends LogicDoubleElement {



    /**
     * 默认构造函数
     * @param parent 上级节点
     */
    public LogicAndElement(LogicInterface parent){
        super(parent);
    }

    /**
     * 带有已知条件的构造函数
     * @param parent 上级节点
     * @param current 当前的条件
     */
    public LogicAndElement(LogicInterface parent, LogicInterface current){
        super(parent,current);
    }

    /**
     * 并且
     *
     * @return
     */
    @Override
    public LogicInterface and() {
        LogicInterface temp = super.and();
        temp = new LogicAndElement(this);
        return temp;
    }

    /**
     * 或者
     *
     * @return
     */
    @Override
    public LogicInterface or() {
        LogicInterface temp = super.or();
        temp = new LogicOrElement(this);
        return temp;
    }

    /**
     * 将对象转换成成文本
     * @return
     */
    @Override
    public String toString() {
        return String.format(" (%s and %s) ",this.l1,this.l2);
    }

    /**
     * 获取查询对象所对应的SQL语句
     *
     * @return SQL
     */
    @Override
    public String getSql() {
        return String.format(" (%s and %s) ",this.l1.getSql(),this.l2.getSql());
    }
}
