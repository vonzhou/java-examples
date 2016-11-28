package com.vonzhou.learn.user;

/**
 * @version 2016/11/28.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class UserTest {

    @Autowired
    private ProfileService profileService;

    @Test
    public void testInsert() {
        Profile p = new Profile();
        p.setUsername("vonzhou");
        p.setCity("hangz");
        profileService.add(p);
    }
}
