package io.snailuu.boot.config;

import io.snailuu.boot.auth.interceptor.*;
import io.snailuu.boot.config.properties.*;
import io.snailuu.boot.framework.filter.JsonRequestBodyFilter;
import io.snailuu.boot.framework.filter.TraceIdLogFilter;
import io.snailuu.boot.framework.interceptor.PageHelperClearInterceptor;
import io.snailuu.boot.framework.xss.XssFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author snailuu
 * @date 2022/3/15
 **/
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginProperties loginProperties;

    @Autowired
    private LoginAdminProperties loginAdminProperties;

    @Autowired
    private LoginAppProperties loginAppProperties;

    @Autowired
    private LoginCommonProperties loginCommonProperties;

    @Autowired
    private LocalFileProperties localFileProperties;

    @Autowired
    private XssProperties xssProperties;

    @Autowired
    private NotAuthProperties notAuthProperties;

    @Bean
    public ExcludePathInterceptor excludePathInterceptor() {
        return new ExcludePathInterceptor();
    }

    @Bean
    public NotAuthInterceptor notAuthInterceptor() {
        return new NotAuthInterceptor();
    }

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public AppLoginInterceptor loginAppInterceptor() {
        return new AppLoginInterceptor();
    }

    @Bean
    public CommonLoginInterceptor loginCommonInterceptor() {
        return new CommonLoginInterceptor();
    }

    @Bean
    public RefreshTokenInterceptor refreshTokenInterceptor() {
        return new RefreshTokenInterceptor();
    }

    @Bean
    public PageHelperClearInterceptor pageHelperClearInterceptor() {
        return new PageHelperClearInterceptor();
    }


    @Bean
    public FilterRegistrationBean traceIdLogFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TraceIdLogFilter traceIdLogFilter = new TraceIdLogFilter();
        filterRegistrationBean.setFilter(traceIdLogFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean jsonRequestBodyFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        JsonRequestBodyFilter jsonRequestBodyFilter = new JsonRequestBodyFilter();
        filterRegistrationBean.setFilter(jsonRequestBodyFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }

    /**
     * XssFilter配置
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean xssFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setEnabled(xssProperties.isEnable());
        filterRegistrationBean.addUrlPatterns(xssProperties.getUrlPatterns());
        filterRegistrationBean.setOrder(xssProperties.getOrder());
        filterRegistrationBean.setAsyncSupported(xssProperties.isAsync());
        return filterRegistrationBean;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 上传文件访问路径
        String accessPath = localFileProperties.getAccessPath();
        // 上传文件保存路径
        String uploadPath = localFileProperties.getUploadPath();
        if (StringUtils.isNotBlank(accessPath) && StringUtils.isNotBlank(uploadPath)) {
            // 虚拟目录文件映射
            registry.addResourceHandler(accessPath)
                    .addResourceLocations("file:" + uploadPath);
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 加入的顺序就是拦截器执行的顺序
        registry.addInterceptor(excludePathInterceptor());
        // 没有权限的拦截器，直接返回无权限
        boolean enableNotAuth = notAuthProperties.isEnable();
        if (enableNotAuth) {
            List<String> includePaths = notAuthProperties.getIncludePaths();
            registry.addInterceptor(notAuthInterceptor()).addPathPatterns(includePaths);
        }

        // token拦截器
        registry.addInterceptor(tokenInterceptor()).excludePathPatterns("/admin/login", "/app/login","/admin/register");
        // 管理后台登录拦截器配置
        boolean enableAdminInterceptor = loginAdminProperties.isEnable();
        if (enableAdminInterceptor) {
            List<String> excludePaths = loginProperties.getExcludePaths();
            List<String> adminExcludePaths = loginAdminProperties.getExcludePaths();
            adminExcludePaths.addAll(excludePaths);
            registry.addInterceptor(loginInterceptor())
                    .addPathPatterns(loginAdminProperties.getIncludePaths())
                    .excludePathPatterns(adminExcludePaths);
        }
        // 移动端端登录拦截器配置
        boolean enableAppInterceptor = loginAppProperties.isEnable();
        if (enableAppInterceptor) {
            List<String> appIncludePaths = loginAppProperties.getIncludePaths();
            List<String> excludePaths = loginAppProperties.getExcludePaths();
            registry.addInterceptor(loginAppInterceptor()).addPathPatterns(appIncludePaths).excludePathPatterns(excludePaths);
        }
        // 刷新token拦截器
        registry.addInterceptor(refreshTokenInterceptor());

        // 系统公共请求拦截器，子拦截/common/开头的请求
        boolean enableCommonInterceptor = loginCommonProperties.isEnable();
        if (enableCommonInterceptor) {
            registry.addInterceptor(loginCommonInterceptor()).addPathPatterns(loginCommonProperties.getIncludePaths());
        }

        // 分页缓存清除拦截器
        registry.addInterceptor(pageHelperClearInterceptor());
    }
}
