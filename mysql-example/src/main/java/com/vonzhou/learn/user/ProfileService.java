package com.vonzhou.learn.user;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @version 2016/11/28.
 */
@Service(value = "profileService")
public class ProfileService {

    @Resource
    private ProfileDao profileDao;

    public void add(Profile profile) {
        try {
            profileDao.insert(profile);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
