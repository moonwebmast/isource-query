package com.isource.query;

import java.util.ArrayList;
import java.util.List;

public abstract   class BaseQuery implements QueryInterface {

    QueryInterface parent = null;

    private int parameterIndex = 0;

    public BaseQuery(){

    }

    public BaseQuery(QueryInterface parent){
        this.parent = parent;
    }

    /**
     * 查询字段
     *
     * @param fields
     * @return 查询对象
     */
    @Override
    public QueryInterface select(String... fields) {
        if(this.parent != null){
            return this.parent.select(fields);
        }
        return this;
    }

    /**
     * 查询对象的条件
     *
     * @return 查询对象
     */
    @Override
    public QueryInterface where() {
        if(this.parent != null){
            return this.parent.where();
        }
        return this;
    }

    /**
     * 排序
     *
     * @return 查询对象
     */
    @Override
    public QueryInterface orderBy() {
        if(this.parent != null){
            return this.parent.orderBy();
        }
        return this;
    }

    /**
     * 限制返回记录数
     *
     * @param pageSize
     * @return 查询对象
     */
    @Override
    public QueryInterface limit(int pageSize) {
        if(this.parent == null){
            return this.limit(pageSize);
        }
        return this.parent.limit(pageSize);
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
        if(this.parent == null){
            return this.limit(from,pageSize);
        }
        return this.parent.limit(from, pageSize);
    }

    /**
     * 生成当前查询参数序号
     *
     * @return
     */
    @Override
    public String generatorParameterIndex() {
        if(this.parent == null){
            return String.format("key_%s", this.parameterIndex ++) ;
        }
        return this.parent.generatorParameterIndex();
    }

    /**
     * 生成当前查询参数序号
     *
     * @return
     */
    @Override
    public List<String> generatorParameterIndex(int count) {
        if(this.parent == null){
            List<String> list = new ArrayList<>();
            for(int i=0;i < count; i ++) {
                list.add(String.format("key_%s", this.parameterIndex++));
            }
            return list;
        }
        return this.parent.generatorParameterIndex(count);
    }





}
