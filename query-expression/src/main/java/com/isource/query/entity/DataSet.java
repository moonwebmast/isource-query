package com.isource.query.entity;

import lombok.Data;

import java.util.List;

/**
 * 数据集
 * @author pangyl
 * @param <T> 数据集中的数据类型
 */
@Data
public class DataSet<T> extends BasePageInfo{

    /**
     * 记录集
     */
    private List<T> rows;

    public DataSet(){}

    public DataSet(List<T> rows){
        this(rows,0,-1,rows.size());

    }

    public DataSet(List<T> rows, PageInfoInterface pageInfo){
        this(rows,pageInfo.getPageIndex(),pageInfo.getPageSize(),rows.size());

    }

    public DataSet(List<T> rows, int pageIndex,int pageSize, int allCount){
        super(pageIndex,pageSize,allCount,rows.size());
        this.rows = rows;
    }

}
