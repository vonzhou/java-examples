package com.vonzhou.learn.user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @version 2016/11/28.
 */

@Repository
public class ProfileDao {
    @Resource
    protected SqlSessionTemplate sqlSessionTemplate;

    public long insert(Profile profile) {
        sqlSessionTemplate.insert("com.vonzhou.learn.user.Profile" + "insert", profile);
        return profile.getId();
    }
}
