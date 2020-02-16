package com.isource.query;

import java.util.ArrayList;
import java.util.List;

public class FieldElement {
    /**
     * 上级条件节点的引用
     */
    private LogicInterface parent;

    private List<String> parameterKeys = new ArrayList<>();

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 运算符元数，一元、二元、三元
     */
    private int valueCount = 1;

    /**
     * 值1
     */
    private String value1;
    /**
     * 值2
     */
    private String value2;

    /**
     * 值3
     */
    private String value3;

    /**
     * 条件表达式格式化
     */
    private String format;

    public FieldElement(LogicInterface parent, String fieldName){
        this.parent = parent;
        this.fieldName = fieldName;
    }

    /**
     * 等于运算
     * @param val 等于的值
     * @return 下一个逻辑运算对象
     */
    public LogicInterface equal(String val){
        this.format = "%s = %s";
        this.value1 = val;
        return this.parent.next();
    }

    /**
     * 包含
     * @param val 值
     * @return 下一个逻辑运算对象
     */
    public LogicInterface like(String val){
        this.format = "%s like %s";
        this.value1 = val;
        return this.parent.next();
    }



    /**
     * 获取SQL字符串
     * @return
     */
    @Override
    public String toString(){
        if(valueCount == 1) {
            return String.format(this.format, fieldName, value1);
        }else if(valueCount == 2){
            return String.format(this.format, fieldName, value1, value2);
        }else{
            return String.format(this.format, fieldName, value1, value2, value3);
        }
    }

    /**
     * 获取SQL魔板
     * @return
     */
    public String getSql(){
        InitParameterIndex();

        if(valueCount == 1) {
            return String.format(this.format, fieldName,
                    String.format("#{%s}",getParameterKey(0)));
        }else if(valueCount == 2){
            return String.format(this.format, fieldName,
                    String.format("#{%s}",getParameterKey(0)),
                    String.format("#{%s}",getParameterKey(1)));
        }else{
            return String.format(this.format, fieldName,
                    String.format("#{%s}",getParameterKey(0)),
                    String.format("#{%s}",getParameterKey(1)),
                    String.format("#{%s}",getParameterKey(2)));
        }
    }

    public MapParameter getMapParameter(){

        InitParameterIndex();

        MapParameter mapParameter = new MapParameter();

        if(valueCount > 0) {
            mapParameter.setParameter(this.getParameterKey(0), this.value1);
        }
        if(valueCount > 1) {
            mapParameter.setParameter(this.getParameterKey(1), this.value2);
        }
        if(valueCount > 2) {
            mapParameter.setParameter(this.getParameterKey(2), this.value3);
        }

        return mapParameter;
    }

    /**
     * 初始化参数
     */
    private void InitParameterIndex()
    {
        if(parameterKeys.size() == 0) {
            parameterKeys = this.parent.generatorParameterIndex(valueCount);
        }
    }

    /**
     * 获取SQL 参数Key
     * @param index 序号
     * @return 参数Key
     */
    private String getParameterKey(int index){
        InitParameterIndex();
        return parameterKeys.get(index);
    }


}
