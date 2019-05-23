package com.github.chuangkel.mybatislearn.SqlSessionFactoryLearn;

/**
 * @program: spring_learn
 * @description:
 * @author: chuangkel
 * @create: 2019-05-16 17:13
 **/
public interface BlogMapper {

    Blog selectBlog(int i);


//    @Select("SELECT * FROM blog WHERE id = #{id}")
//    Blog selectBlog(int id);

}
