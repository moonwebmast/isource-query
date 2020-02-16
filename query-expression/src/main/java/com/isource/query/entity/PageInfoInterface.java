package com.isource.query.entity;

public interface PageInfoInterface {

    /**
     * 查询数据起始记录编号
     */
    int getPageIndex();

    void setPageIndex(int val);

    /**
     * 查询数据的记录数
     */
    int getPageSize();

    void setPageSize(int val);

    /**
     * 获取所有记录数
     * @return
     */
    int getAllCount();

    void setAllCount(int val);

    /**
     * 获取当前结果集的记录数
     * @return  当前结果集的记录数
     */
    int getCurrentCount();

    void setCurrentCount(int val);

}
