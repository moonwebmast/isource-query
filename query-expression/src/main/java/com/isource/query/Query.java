package com.isource.query;

import com.isource.query.entity.PageInfoInterface;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * 查询对象
 */

public class Query extends BaseQuery implements PageInfoInterface {

    public static final String WHERE_SQL_KEY = "whereSql";

    public static final String SELECT_SQL_KEY = "selectSql";

    public static final String ORDER_BY_SQL_KEY = "orderBySql";

    public static final String LIMIT_SQL_KEY = "limitSql";

    public static final String SQL_KEY = "sql";

    /**
     * 需要查询的字段
     * 这里仅仅是设置查询字段，具体返回值需要Dao支持
     */
    @Getter
    private String[] selectFields;

    /**
     * 查询数据起始记录编号
     */
    @Getter
    private int pageIndex = -1;
    /**
     * 查询数据的记录数
     */
    @Getter
    private int pageSize = -1;

    @Getter
    @Setter
    private int allCount = 0;

    @Getter
    @Setter
    private int currentCount = 0;

    private OrderByElement orderByElement;

    private WhereElement whereElement;

    private String tableName = "${tableName}";

    public Query(){}

    public Query(String tableName){
        this.tableName = tableName;
    }



    /**
     * 查询字段
     * @param fields
     * @return
     */
    @Override
    public Query select(String ... fields){
        this.selectFields = fields;
        return this;
    }

    /**
     * 查询对象的条件
     *
     * @return 查询对象
     */
    @Override
    public LogicInterface where() {
        if(this.whereElement == null)
        {
            this.whereElement = new WhereElement(this);
        }
        return this.whereElement;
    }

    /**
     * 排序
     *
     * @return 查询对象
     */
    @Override
    public OrderByInterface orderBy() {
        if(this.orderByElement == null){
            this.orderByElement = new OrderByElement(this);
        }
        return this.orderByElement;
    }

    /**
     * 限制返回记录数
     *
     * @param pageSize
     * @return 查询对象
     */
    @Override
    public Query limit(int pageSize) {
        return this.limit(0,pageSize);
    }

    /**
     * 分页
     *
     * @param from     起始记录数
     * @param pageSize 读取记录数量
     * @return 查询对象
     */
    @Override
    public Query limit(int from, int pageSize) {
        this.pageIndex = from;
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        if(selectFields != null && selectFields.length > 0){
            for (String fieldName:this.selectFields) {
                if(result.length() > 0){
                    result.append(",");
                }
                result.append(fieldName);
            }
           result.insert(0,"SELECT ");
            result.append(" ");
        }

        if(this.whereElement != null) {
            result.append(this.whereElement.toString());
        }

        if(this.orderByElement != null) {
            result.append(this.orderByElement.toString());
        }

        if(pageIndex > -1) {
            result.append("LIMIT ");
            result.append(pageIndex);
            result.append(",");
            result.append(pageSize);
        }

        return result.toString();
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

    /**
     * 获取用于Mybatis 查询的 参数集合
     * @return Map 类型的参数
     */
    @Override
    public MapParameter getMapParameter(){

        MapParameter mapParameter = new MapParameter();
        MapParameter whereParameter = this.where().getMapParameter();
        //MapParameter orderParameter = this.orderBy().getMapParameter();

        mapParameter.setPageIndex(this.pageIndex);
        mapParameter.setPageSize(this.pageSize);
        mapParameter.putAll(whereParameter);
        // add SQL parameter

        mapParameter.put(SELECT_SQL_KEY, this.getSelectSql());
        mapParameter.put(WHERE_SQL_KEY, this.where().getSql());
        mapParameter.put(ORDER_BY_SQL_KEY,this.getOrderBySql());
        mapParameter.put(LIMIT_SQL_KEY,this.getLimitSql());
        mapParameter.put(SQL_KEY,this.getSql());

        return mapParameter;
    }



    /**
     * 获取查询对象所对应的SQL语句
     *
     * @return SQL
     */
    @Override
    public String getSql() {
        StringBuilder result = new StringBuilder();

        result.insert(0,"SELECT ");

        result.append(this.getSelectSql());

        result.append(String.format(" FROM %s ",this.tableName));

        result.append(this.getWhereSql());

        result.append(this.getOrderBySql());

        result.append(this.getLimitSql());

        return result.toString();
    }

    private String getOrderBySql(){
        if(this.orderByElement == null){
            return "";
        }

        return this.orderByElement.getSql();
    }

    /**
     * 获取查询字段SQL
     * @return
     */
    private String getSelectSql(){
        StringBuilder result = new StringBuilder();

        if(selectFields != null && selectFields.length > 0) {
            for (String fieldName : this.selectFields) {
                if (result.length() > 0) {
                    result.append(",");
                }
                result.append(fieldName);
            }
        }else{
            result.append("*");
        }

        return result.toString();
    }

    /**
     * 获取查询条件SQL
     * @return
     */
    private String getWhereSql(){
        StringBuilder result = new StringBuilder();

        if(this.whereElement != null) {
            String whereSQL = this.whereElement.getSql();

            if(!StringUtils.isEmpty(whereSQL))
            {
                result.append("WHERE " + whereSQL);
            }

        }

        return result.toString();
    }

    /**
     * 获取翻页SQL
     * @return 翻页SQL
     */
    private String getLimitSql(){
        if(pageIndex > -1) {
            return "LIMIT #{pageIndex},#{pageSize}";
        }
        return "";
    }
}
