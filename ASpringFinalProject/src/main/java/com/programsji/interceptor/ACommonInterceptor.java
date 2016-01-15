package com.programsji.interceptor;

import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

@Component
public class ACommonInterceptor implements HandlerInterceptor {
	public final static String HANDLER_METHOD = "handlerMethod";
	public final static String SERVLET_NAME = "XXXXX";

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("After Completion");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("Post Handle");
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj2) throws Exception {
		System.out.println("Pre Handle");
//		ServletContext servletContext = request.getSession()
//				.getServletContext();
//		String attrName = FrameworkServlet.SERVLET_CONTEXT_PREFIX
//				+ SERVLET_NAME;
//		WebApplicationContext context = WebApplicationContextUtils
//				.getWebApplicationContext(servletContext, attrName);
//		AnnotationMethodHandlerAdapter adapter = context
//				.getBean(AnnotationMethodHandlerAdapter.class);
//
//		Method getMethodResolverMethod = adapter.getClass().getDeclaredMethod(
//				"getMethodResolver", Object.class);
//		getMethodResolverMethod.setAccessible(true);
//		Object servletHandlerMethodResolver = getMethodResolverMethod.invoke(
//				adapter, obj2);
//
//		Method resolveHandlerMethod = servletHandlerMethodResolver.getClass()
//				.getMethod("resolveHandlerMethod", HttpServletRequest.class);
//		resolveHandlerMethod.setAccessible(true);
//		Method handlerMethod = (Method) resolveHandlerMethod.invoke(
//				servletHandlerMethodResolver, request);
//		request.setAttribute(HANDLER_METHOD, handlerMethod);
//
//		System.out.println("preHandle>>>" + handlerMethod);

		return true;
	}

}
