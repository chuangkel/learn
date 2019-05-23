package com.github.springboot.service;

import com.github.springboot.dao.DemoTestDao;
import org.junit.Test;
import org.springframework.stereotype.Service;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-04-15 10:34
 **/

@Service
public class DemoTestService {
    private DemoTestDao demoTestDao;

    public DemoTestService(DemoTestDao demoTestDao) {
        this.demoTestDao = demoTestDao;
    }

    public void testDemo(){
        demoTestDao.print();
    }


}
