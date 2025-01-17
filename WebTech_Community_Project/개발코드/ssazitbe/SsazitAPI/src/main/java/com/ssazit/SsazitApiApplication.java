package com.ssazit;

import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cksource.ckfinder.servlet.CKFinderServlet;

@EnableScheduling
@SpringBootApplication
public class SsazitApiApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SsazitApiApplication.class, args);
	}
 


//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		// Register the CKFinder's servlet.
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("ckfinder", new CKFinderServlet());
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/ckfinder/*");
//        dispatcher.setInitParameter("scan-path", "com.ssazit.ckfinder");
//
//        FilterRegistration.Dynamic filter = servletContext.addFilter("x-content-options", new Filter() {
//            @Override
//            public void init(FilterConfig filterConfig) {
//            }
//
//            @Override
//            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//                ((HttpServletResponse) response).setHeader("X-Content-Type-Options", "nosniff");
//                ((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "http://localhost:8080");//와일드카드 x
////                ((HttpServletResponse)response).setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
////                ((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers", "*");
////                ((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers", "content-type");
////                ((HttpServletResponse)response).setHeader("Access-Control-Allow-Credentials", "true");
////                ((HttpServletResponse)response).setHeader("Access-Control-Max-Age", "180");
//                ((HttpServletResponse)response).setHeader("X-Frame-Options", "ALLOW-FROM http://localhost:8080");
//                chain.doFilter(request, response);
//            }
//
//            @Override
//            public void destroy() {
//            }
//        });
//
//        filter.addMappingForUrlPatterns(null, false, "/userfiles/*");
//
//        String tempDirectory;
//
//        try {
//            tempDirectory = Files.createTempDirectory("ckfinder").toString();
//        } catch (IOException e) {
//            tempDirectory = null;
//        }
//
//        dispatcher.setMultipartConfig(new MultipartConfigElement(tempDirectory));
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // Configure the resource handler to serve files uploaded with CKFinder.
//        String publicFilesDir = String.format("file:%s/userfiles/", System.getProperty("user.dir"));
//
//        registry.addResourceHandler("/userfiles/**")
//                .addResourceLocations(publicFilesDir);
//    }
}
