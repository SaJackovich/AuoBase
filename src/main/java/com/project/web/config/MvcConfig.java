package com.project.web.config;

import com.project.web.converter.AutoFormDtoToAutoConverter;
import com.project.web.converter.RequestFormDtoToRequestConverter;
import com.project.web.converter.RequestToAutoSearchDtoConverter;
import com.project.web.converter.StringToJourneyStatusConverter;
import com.project.web.converter.StringToUserRoleConverter;
import com.project.web.converter.UserFormDtoToUserConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToJourneyStatusConverter());
        registry.addConverter(new StringToUserRoleConverter());
        registry.addConverter(new UserFormDtoToUserConverter());
        registry.addConverter(new RequestFormDtoToRequestConverter());
        registry.addConverter(new RequestToAutoSearchDtoConverter());
        registry.addConverter(new AutoFormDtoToAutoConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

//        registry.addResourceHandler("/media/**")
//                .addResourceLocations("file:D:\\WorkingSpringProjects\\WorkingOldprojectToString\\OldProjectToSpring\\src\\main\\resources\\static\\media\\")
//                .setCachePeriod(0);
//        registry.addResourceHandler("/js/**")
//                .addResourceLocations("file:D:\\WorkingSpringProjects\\WorkingOldprojectToString\\OldProjectToSpring\\src\\main\\resources\\static\\js\\");
//        registry.addResourceHandler("/css/**")
//                .addResourceLocations("file:D:\\WorkingSpringProjects\\WorkingOldprojectToString\\OldProjectToSpring\\src\\main\\resources\\static\\css\\");
//        registry.addResourceHandler("/bootstrap/**")
//                .addResourceLocations("file:D:\\WorkingSpringProjects\\WorkingOldprojectToString\\OldProjectToSpring\\src\\main\\resources\\static\\bootstrap\\");
//        registry.addResourceHandler("/font/**")
//                .addResourceLocations("file:D:\\WorkingSpringProjects\\WorkingOldprojectToString\\OldProjectToSpring\\src\\main\\resources\\static\\font\\");
//        registry.addResourceHandler("/scss/**")
//                .addResourceLocations("file:D:\\WorkingSpringProjects\\WorkingOldprojectToString\\OldProjectToSpring\\src\\main\\resources\\static\\scss\\");
//        registry.addResourceHandler("/vendor/**")
//                .addResourceLocations("file:D:\\WorkingSpringProjects\\WorkingOldprojectToString\\OldProjectToSpring\\src\\main\\resources\\static\\vendor\\");


        registry.addResourceHandler("/media/**")
                .addResourceLocations("file:F:\\Spring Java\\AuoBase\\src\\main\\resources\\static\\media\\")
                .setCachePeriod(0);
        registry.addResourceHandler("/js/**")
                .addResourceLocations("file:F:\\Spring Java\\AuoBase\\src\\main\\resources\\static\\js\\");
        registry.addResourceHandler("/css/**")
                .addResourceLocations("file:F:\\Spring Java\\AuoBase\\src\\main\\resources\\static\\css\\");
        registry.addResourceHandler("/bootstrap/**")
                .addResourceLocations("file:F:\\Spring Java\\AuoBase\\src\\main\\resources\\static\\bootstrap\\");
        registry.addResourceHandler("/font/**")
                .addResourceLocations("file:F:\\Spring Java\\AuoBase\\src\\main\\resources\\static\\font\\");
        registry.addResourceHandler("/scss/**")
                .addResourceLocations("file:F:\\Spring Java\\AuoBase\\src\\main\\resources\\static\\scss\\");
        registry.addResourceHandler("/vendor/**")
                .addResourceLocations("file:F:\\Spring Java\\AuoBase\\src\\main\\resources\\static\\vendor\\");
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }

}
