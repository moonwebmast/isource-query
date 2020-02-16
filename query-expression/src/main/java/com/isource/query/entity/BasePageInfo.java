package com.isource.query.entity;


/**
 * 分页数据定义基础类
 */
public class BasePageInfo implements PageInfoInterface {

    /**
     * 查询数据起始记录编号
     */
    private int pageIndex = -1;

    /**
     * 查询数据的记录数
     */
    private int pageSize = 0;

    /**
     * 获取所有记录数
     * @return
     */
    private int allCount = 0;

    /**
     * 获取当前结果集的记录数
     * @return  当前结果集的记录数
     */
    private int currentCount;

    /**
     * 构造函数
     */
    public BasePageInfo (){

    }

    /**
     * 构造函数
     * @param pageIndex     当前页码
     * @param pageSize      查询数据的记录数
     * @param allCount      所有记录数
     * @param currentCount  当前结果集的记录数
     */
    public BasePageInfo(int pageIndex, int pageSize, int allCount, int currentCount){
        this.currentCount = currentCount;
        this.allCount = allCount;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    /**
     * 查询数据起始记录编号
     */
    @Override
    public int getPageIndex() {
        return this.pageIndex;
    }

    @Override
    public void setPageIndex(int val) {
        this.pageIndex = val;
    }

    /**
     * 查询数据的记录数
     */
    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    @Override
    public void setPageSize(int val) {
        this.pageSize = val;
    }

    /**
     * 获取所有记录数
     *
     * @return
     */
    @Override
    public int getAllCount() {
        return this.allCount;
    }

    @Override
    public void setAllCount(int val) {
        this.allCount = val;
    }

    /**
     * 获取当前结果集的记录数
     *
     * @return 当前结果集的记录数
     */
    @Override
    public int getCurrentCount() {
        return this.currentCount;
    }

    @Override
    public void setCurrentCount(int val) {
        this.currentCount = val;
    }
}
