package com.isource.query.demo.dao;

import com.isource.query.MapParameter;
import com.isource.query.demo.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    /**
     * 查询结果集
     * @param parameter 查询条件
     * @return 查询结果
     */
    List<User> findList(MapParameter parameter);
}
