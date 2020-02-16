package com.isource.query;

/**
 * 查询条件处理对象
 */
public class WhereElement extends BaseQuery implements LogicInterface {



    /**
     * 当前逻辑运算节点，默认只有一个
     */
    private LogicInterface rootElement;

    /**
     * 构造函数
     * @param parent 上层节点
     */
    public WhereElement(QueryInterface parent){
        super(parent);

        //this.currentElement = this;
    }

    @Override
    public OrderByInterface orderBy() {
        return (OrderByInterface)this.parent.orderBy();
    }

    /**
     * 获取用于Mybatis 查询的 参数集合
     *
     * @return Map 类型的参数
     */
    @Override
    public MapParameter getMapParameter() {
        if(this.rootElement == null){
            return new MapParameter();
        }

        return rootElement.getMapParameter();
    }

    /**
     * 获取查询对象所对应的SQL语句
     *
     * @return SQL
     */
    @Override
    public String getSql() {
        if(this.rootElement == null){
            return "";
        }
        return this.rootElement.getSql() + " " ;
    }

    /**
     * 获取查询条件字段对象
     *
     * @param fieldName 查询的字段列表
     * @return 查询条件字段对象
     */
    @Override
    public FieldElement field(String fieldName) {
        if(this.rootElement == null){
            //第一个节点创建单节点逻辑对象
            this.rootElement = new LogicSingleElement(this);
        }else{
            //后续默认 And 逻辑
            this.rootElement = new LogicAndElement(this,this.rootElement);
        }
        return this.rootElement.field(fieldName);
    }

    /**
     * 并且
     *
     * @return
     */
    @Override
    public LogicInterface and() {
        if(this.rootElement != null)
        {
            this.rootElement = new LogicAndElement(this,this.rootElement);
        }else{
            this.rootElement = new LogicAndElement(this);
        }
        return this.rootElement;
    }

    /**
     * 或者
     *
     * @return
     */
    @Override
    public LogicInterface or() {

        if(this.rootElement != null)
        {
            this.rootElement = new LogicOrElement(this,this.rootElement);
        }else{
            this.rootElement = new LogicOrElement(this);
        }
        return this.rootElement;
    }

    /**
     * 获取下个逻辑分支
     * 注意：内部使用不对外
     *
     * @return 下个可用的逻辑分支
     */
    @Override
    public LogicInterface next() {
        return this;
    }

    @Override
    public String toString() {
        if(this.rootElement == null){
            return "";
        }
        return " WHERE " + this.rootElement.toString() + " " ;
    }


}
