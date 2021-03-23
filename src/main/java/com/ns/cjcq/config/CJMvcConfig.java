package com.ns.cjcq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
@EnableScheduling
public class CJMvcConfig implements WebMvcConfigurer {

	//不通过controller,直接跳转
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/").setViewName("index");
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/demo/ckeditor401").setViewName("demo/ckeditor4Demo01");
		registry.addViewController("/demo/dataTablesDemo").setViewName("demo/dataTableDemo_ServerSide");

	}

	/*静态资源设置*/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//addResourceHandler     是指你想在url请求的路径
		//addResourceLocations   是图片存放的真实路径,可以是classpath:,也可以是file:
		registry.addResourceHandler("/cjThirdStatic/**").addResourceLocations("classpath:/static/vendor/");
		registry.addResourceHandler("/node_modules/**").addResourceLocations("classpath:/static/node_modules/");
		registry.addResourceHandler("/sb2Static/**").addResourceLocations("classpath:/static/sb2/");
		registry.addResourceHandler("/icon/**").addResourceLocations("classpath:/static/icon/");

	}


	/*@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}*/

	/*@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");// 3

	}*/

//Intepretor config
	/*@Bean
	// 1
	public DemoInterceptor demoInterceptor() {
		return new DemoInterceptor();
	}*/

	/*@Override
	public void addInterceptors(InterceptorRegistry registry) {// 2
		registry.addInterceptor(demoInterceptor());
	}*/
//



//	不忽略pathVariable中"."后面的参数，例如...../pathvar/xx.yy
//    默认情况下，只会获取到xx
	 /*@Override
	 public void configurePathMatch(PathMatchConfigurer configurer) {
	 configurer.setUseSuffixPatternMatch(false);
	 }*/
//file upload
	/*@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000);
		return multipartResolver;
	}*/
//

	/*@Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }*/

	/*@Bean
	public MyMessageConverter converter(){
		return new MyMessageConverter();
	}*/


}
