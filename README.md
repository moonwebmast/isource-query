# isource-query

本项目是一个基于Spring Boot MyBatis 的项目。
本项目提供一个查询条件的参数，使查询列表的参数能够动态化。

1.支持查询条件动态构建  
2.支持字段动态选择（DEMO 还不完整）  
3.支持排序  
4.支持翻页（已经完成成参数传入，还未整合Mybatis 过滤器）  

## 查询参数构建DEMO
~~~
Query query = new Query();

query
    .select("f1","f2","f3")
    .where()
    .field("f1").equal("1")
    .and()
    .field("f2").like("%2%")
    .orderBy().asc("f1").desc("f2")
    .limit(1,10);
~~~
## Controller DEMO
~~~
@RequestMapping("findUser")
public DataSet<User> findUser(String key){
    Query query = new Query();
    // 拼装查询条件
    query.where().field("account").like(key);
    return userService.findList(query);
}
~~~
## 查询条件合并DEMO  

~~~
public DataSet<User> findList(Query query){
   if(query == null){
       query = new Query();
   }
   // 如果传入的Query已经存在条件，则进行条件合并
   query.where().field("status").equal("1");
   // 查询结果
   List<User> list = userDao.findList(query.getMapParameter());
   // 返回分页信息，目前分页拦截器还未实现，实现后将分页信息通过 Query 返回；
   return new DataSet<User>(list, query);
}
~~~  
* DataSet 是自定义的一个返回数据集，支持翻页信息的返回

## Dao 接口定义
~~~
List<User> findList(MapParameter parameter);
~~~

## Mapping 定义 DEMO
~~~
<select id="findList" parameterType="Map" resultType="com.isource.query.demo.entity.User">
    SELECT
    `id`, `account`, `name`, `password`, `email`,`status`
    FROM `rc_users`
    <where>
        ${whereSql}
        ${orderBySql}
        ${limitSql}
    </where>
</select>
~~~