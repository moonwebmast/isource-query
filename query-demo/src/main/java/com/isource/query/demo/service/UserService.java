package com.isource.query.demo.service;

import com.isource.query.Query;
import com.isource.query.demo.dao.UserDao;
import com.isource.query.demo.entity.User;
import com.isource.query.entity.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    /**
     * 列表查询DEMO
     * @param query
     * @return
     */
    public DataSet<User> findList(Query query){
        if(query == null){
            query = new Query();
        }
        // 条件合并
        query.where().field("status").equal("1");
        // 查询结果
        List<User> list = userDao.findList(query.getMapParameter());
        // 返回分页信息，目前分页拦截器还未实现，实现后将分页信息通过 Query 返回；
        return new DataSet<User>(list, query);
    }

}
