/**
 * <html>
 * <body>
 * <P> Copyright 1994 JsonInternational</p>
 * <p> All rights reserved.</p>
 * <p> Created on 19941115</p>
 * <p> Created by Jason</p>
 * </body>
 * </html>
 */
package com.github.chuangkel.java_learn.pattern.create.factory.human;

//import com.pattern.create.factory.Human;

import com.github.chuangkel.java_learn.pattern.create.factory.Human;

/**
 * @Package：com.pattern.create.factory.human
 * @ClassName：WhiteHuman
 * @Description：   <p> 工厂模式  --- 白人</p>
 * @Author： -
 * @CreatTime：2017年10月26日 下午5:49:13
 * @Modify By：
 * @ModifyTime：
 * @Modify marker：
 * @version V1.0
 */
public class WhiteHuman implements Human {

    @Override
    public void getColor() {
        System.out.println("白色人种");
    }
}
