package com.github.springboot.service;

import com.github.springboot.dao.DemoTestMapper;
import com.github.springboot.domain.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-04-15 10:34
 **/

@Service
public class DemoTestService {
    private DemoTestMapper demoTestMapper;

    public DemoTestService(DemoTestMapper demoTestMapper) {
        this.demoTestMapper = demoTestMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void testDemo(){
        Person person = new Person(1,"john",0);
        demoTestMapper.insert(person);
    }


}
