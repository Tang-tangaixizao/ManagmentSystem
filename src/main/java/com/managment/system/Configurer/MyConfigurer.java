package com.managment.system.Configurer;

import com.managment.system.MyInterceptor.MyInterceptor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@Configuration
public class MyConfigurer implements WebMvcConfigurer {
    /**添加拦截器函数
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**需要拦截的路径，是一个字符串数组
         * */
        String[] addPathPatterns={"/mainPage.html**"};

        /**不需要拦截的路径
         * */
        String[] excludePathterns={"/index.html","/asserts/**",
        "/css/**","/js/**","/layuiadmin/**","/modules/**","/static/**",
        "/templates/**","/treeTable/**"};

        /**给对应的拦截器进行配置
         * */
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns(excludePathterns);
    }


    @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        System.out.println("应用已经准备就绪 ... 启动浏览器");
        String url = "http://localhost:8080/MS";
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
