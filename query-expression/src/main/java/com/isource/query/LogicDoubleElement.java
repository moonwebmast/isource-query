package com.isource.query;

import java.util.List;
import java.util.Stack;

/**
 * 双节点逻辑运算符基础类
 * @author pangyl
 */
public abstract class LogicDoubleElement extends BaseLogicElement {

    LogicInterface l1;

    LogicInterface l2;

    Stack<LogicInterface> nodes = new Stack<LogicInterface>();

    /**
     * 默认构造函数
     * @param parent 上级节点
     */
    public LogicDoubleElement(LogicInterface parent){
        super(parent);

        this.l1 = new LogicSingleElement(this);
        this.l2 = new LogicSingleElement(this);

        nodes.push(l2);
        nodes.push(l1);


    }

    /**
     * 带有已知条件的构造函数
     * @param parent 上级节点
     * @param current 当前的条件
     */
    public LogicDoubleElement(LogicInterface parent, LogicInterface current){
        super(parent);
        this.l1 = current;
        this.l2 = new LogicSingleElement(this);
        nodes.push(this.l2);
    }

    /**
     * 获取查询条件字段对象
     *
     * @param fieldName
     * @return 查询条件字段对象
     */
    @Override
    public FieldElement field(String fieldName) {
        if(this.nodes.empty()){
            //throw new Exception("没有可用逻辑分支");
        }
        return nodes.pop().field(fieldName);
    }

    /**
     * 并且
     *
     * @return
     */
    @Override
    public LogicInterface and() {
        if(this.nodes.empty()){
            // 目前没有处理异常所以注释掉，后续一定要抛出可以识别出的异常
            //throw new Exception("没有可用逻辑分支");
        }
        LogicInterface temp = nodes.pop();
        return temp;
    }

    /**
     * 或者
     *
     * @return
     */
    @Override
    public LogicInterface or() {
        if(this.nodes.empty()){
            // 目前没有处理异常所以注释掉，后续一定要抛出可以识别出的异常
            //throw new Exception("没有可用逻辑分支");
        }
        LogicInterface temp = nodes.pop();
        return temp;
    }

    /**
     * 获取下个逻辑分支
     * 注意：内部使用不对外
     *
     * @return 下个可用的逻辑分支
     */
    @Override
    public LogicInterface next() {
        if(this.nodes.empty()){
            return this.parent.next();
        }
        return nodes.pop();
    }

    /**
     * 获取用于Mybatis 查询的 参数集合
     *
     * @return Map 类型的参数
     */
    @Override
    public MapParameter getMapParameter() {
        MapParameter mapParameter = new MapParameter();
        mapParameter.putAll(this.l1.getMapParameter());
        mapParameter.putAll(this.l2.getMapParameter());
        return mapParameter;
    }

    /**
     * 生成指定数量的参数序号
     *
     * @param count 数量
     * @return 第一个参数序号
     */
    @Override
    public List<String> generatorParameterIndex(int count) {
        return parent.generatorParameterIndex(count);
    }


}
