package handlermethodargumentresolvers;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.programsji.customannotation.Country;

public class MyHandlerMethodArgumentResolver implements
		HandlerMethodArgumentResolver {

	public MyHandlerMethodArgumentResolver() {
		System.out.println("Raho Hamesha Ready!!!!");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return findMethodAnnotation(Country.class, parameter) != null;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = (HttpServletRequest) webRequest
				.getNativeRequest();
		if (request.getParameter("city") != null) {
			return request.getParameter("city");
		}
		return "Ludhiana";
	}

	private <T extends Annotation> T findMethodAnnotation(
			Class<T> annotationClass, MethodParameter parameter) {
		T annotation = parameter.getParameterAnnotation(annotationClass);
		if (annotation != null) {
			return annotation;
		}
		Annotation[] annotationsToSearch = parameter.getParameterAnnotations();
		for (Annotation toSearch : annotationsToSearch) {
			annotation = AnnotationUtils.findAnnotation(
					toSearch.annotationType(), annotationClass);
			if (annotation != null) {
				return annotation;
			}
		}
		return null;
	}
}