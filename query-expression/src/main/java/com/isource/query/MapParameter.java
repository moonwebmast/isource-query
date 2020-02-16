package com.isource.query;

import com.isource.query.entity.PageInfoInterface;

import java.util.HashMap;


public class MapParameter extends HashMap<String,Object> implements PageInfoInterface {

    /**
     * 默认起始页码
     */
    public static final int DEFAULT_PAGE_INDEX = 0;
    /**
     * 默认查询最大记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 5000;




    /**
     * 查询数据起始记录编号
     */
    @Override
    public int getPageIndex() {
        return this.getParameter("pageIndex",DEFAULT_PAGE_INDEX);
    }

    @Override
    public void setPageIndex(int val) {
        this.setParameter("pageIndex",val);
    }

    /**
     * 查询数据的记录数
     */
    @Override
    public int getPageSize() {
        return this.getParameter("pageSize",DEFAULT_PAGE_SIZE);
    }

    @Override
    public void setPageSize(int val) {
        this.setParameter("pageSize",val);
    }

    /**
     * 获取所有记录数
     *
     * @return
     */
    @Override
    public int getAllCount() {
        return this.getParameter("allCount",0);
    }

    @Override
    public void setAllCount(int val) {
        this.setParameter("allCount",val);
    }

    /**
     * 获取当前结果集的记录数
     *
     * @return 当前结果集的记录数
     */
    @Override
    public int getCurrentCount() {
        return this.getParameter("currentCount",0);
    }

    @Override
    public void setCurrentCount(int val) {
        this.setParameter("currentCount",val);
    }

    /**
     * 获取参数值
     * @param key 关键字
     * @param defaultValue 默认值
     * @param <T> 值类型
     * @return 返回值
     */
    public <T> T getParameter(String key, T defaultValue){
        if(!super.containsKey(key)){
            return defaultValue;
        }

        try {
            return (T) super.get(key);
        }catch (Exception ex){
            return defaultValue;
        }
    }

    /**
     * 设置查询参数
     * @param key 关键字，注意避开系统保留关键字，如：pageIndex,pageSize 等
     * @param value 值
     */
    public void setParameter(String key, Object value){
        super.put(key,value);
    }
}
