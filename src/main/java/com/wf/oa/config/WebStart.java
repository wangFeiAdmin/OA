package com.wf.oa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebStart implements WebMvcConfigurer {

    /**
     * 配置一个视图控制
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //当访问路径为 / 或者为 /login 时，跳转到index.html界面
        registry.addViewController("/").setViewName("/loginUI");
        registry.addViewController("/login").setViewName("/loginUI");
    }

    /**
     * 解决上传图片需要重启服务器才生效
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置一个静态资源映射
        registry.addResourceHandler("/upload/**").addResourceLocations("file:D:\\IDEXiangMu\\OA\\src\\main\\resources\\static\\upload\\");
    }
}
