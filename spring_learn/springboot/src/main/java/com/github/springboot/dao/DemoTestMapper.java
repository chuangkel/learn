package com.github.springboot.dao;

import com.github.springboot.domain.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-04-15 10:28
 **/
@Mapper
public interface DemoTestMapper {
    @Transactional(propagation = Propagation.REQUIRED)
    int insert(Person person);


}
