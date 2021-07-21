package com.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-07-21 23:01
 */
@Configuration
public class GeteWayConfig {

    /**
     * 配置了一个id 为route-name 的路由规则，
     * 当访问地址http://localhost:9527/guonei时会自动转发到地址：http://news.baidu.com/guonei
     * 优化方案，将所有配置存入数据库 程序启动时批量加载Bean 注入到spring容器中
     * @param routeLocatorBuilder
     * @return
     */
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
//        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
//        routes.route("path_route_atguigu", r -> r.path("/guonei")
//            .uri("http://news.baidu.com/guonei")).build();
//        return routes.build();
//    }

}
