package com.isource.query;

public class LogicOrElement extends LogicDoubleElement {

    /**
     * 默认构造函数
     * @param parent 上级节点
     */
    public LogicOrElement(LogicInterface parent){
        super(parent);
    }

    /**
     * 带有已知条件的构造函数
     * @param parent 上级节点
     * @param current 当前的条件
     */
    public LogicOrElement(LogicInterface parent, LogicInterface current){
        super(parent,current);
    }

    /**
     * 并且
     *
     * @return 逻辑运算节点
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
     * @return 逻辑运算节点
     */
    @Override
    public LogicInterface or() {
        LogicInterface temp = super.or();
        temp = new LogicOrElement(this);
        return temp;
    }

    /**
     * 将对象输出文本
     * @return
     */
    @Override
    public String toString() {
        return String.format(" (%s or %s) ",this.l1,this.l2);
    }

    /**
     * 获取查询对象所对应的SQL语句
     *
     * @return SQL
     */
    @Override
    public String getSql() {
        return String.format(" (%s or %s) ",this.l1.getSql(),this.l2.getSql());
    }
}
