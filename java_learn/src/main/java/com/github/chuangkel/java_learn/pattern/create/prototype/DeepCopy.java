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
package com.github.chuangkel.java_learn.pattern.create.prototype;


//import com.pattern.create.prototype.prototypeAbs.BaseMessage;
//
//import java.util.ArrayList;

import com.github.chuangkel.java_learn.pattern.create.prototype.prototypeAbs.BaseMessage;

import java.util.ArrayList;

/**
 * @Package：com.pattern.create.prototype
 * @ClassName：DeepCopy
 * @Description：   <p> 原型模式  ---  原型模式深拷贝</p>
 * @Author： -
 * @CreatTime：2017年10月26日 下午5:43:23
 * @Modify By：
 * @ModifyTime：
 * @Modify marker：
 * @version V1.0
 */
public class DeepCopy extends BaseMessage implements Cloneable {

    @SuppressWarnings("unchecked")
    @Override
    protected Object clone() throws CloneNotSupportedException {
        DeepCopy deepCopy = null;
        try {
            deepCopy = (DeepCopy) super.clone();
            this.setImgList((ArrayList<String>) this.getImgList().clone());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deepCopy;
    }
}
