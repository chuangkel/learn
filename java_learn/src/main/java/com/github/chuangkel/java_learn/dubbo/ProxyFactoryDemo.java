package com.github.chuangkel.java_learn.dubbo;

/**
 * @author xiezhang21010 [www.hundsun.com]
 * @date 2019-10-23 15:28
 */

import com.alibaba.dubbo.common.extension.ExtensionLoader;

public class ProxyFactoryDemo implements com.alibaba.dubbo.rpc.ProxyFactory {
    @Override
    public Object getProxy(com.alibaba.dubbo.rpc.Invoker arg0) {
        if (arg0 == null) {
            throw new IllegalArgumentException("com.alibaba.dubbo.rpc.Invoker argument == null");
        }
        if (arg0.getUrl() == null) {
            throw new IllegalArgumentException("com.alibaba.dubbo.rpc.Invoker argument getUrl() == null");
        }
        com.alibaba.dubbo.common.URL url = arg0.getUrl();
        String extName = url.getParameter("proxy", "javassist");
        if (extName == null) {
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.rpc.ProxyFactory) name from url(" + url.toString() + ") use keys([proxy])");
        }
        com.alibaba.dubbo.rpc.ProxyFactory extension = (com.alibaba.dubbo.rpc.ProxyFactory) ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.ProxyFactory.class).getExtension(extName);
        return extension.getProxy(arg0);
    }

    @Override
    public com.alibaba.dubbo.rpc.Invoker getInvoker(Object arg0, Class arg1, com.alibaba.dubbo.common.URL arg2) {
        if (arg2 == null) {
            throw new IllegalArgumentException("url == null");
        }
        com.alibaba.dubbo.common.URL url = arg2;
        String extName = url.getParameter("proxy", "javassist");
        if (extName == null) {
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.rpc.ProxyFactory) name from url(" + url.toString() + ") use keys([proxy])");
        }
        com.alibaba.dubbo.rpc.ProxyFactory extension = (com.alibaba.dubbo.rpc.ProxyFactory) ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.ProxyFactory.class).getExtension(extName);
        return extension.getInvoker(arg0, arg1, arg2);
    }
}
