package com.programsji.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.ExceptionTranslationFilter;

public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {

	private String accessdeniederrorpage;

	public CustomAccessDeniedHandler(String accessdeniederrorpage) {
		this.accessdeniederrorpage = accessdeniederrorpage;
	}

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {

		if (!response.isCommitted()) {

			if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				response.sendError(403, "Access Denied for resource");
			} else {
				request.setAttribute(WebAttributes.ACCESS_DENIED_403,
						accessDeniedException);
				response.setStatus(HttpServletResponse.SC_FOUND);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(accessdeniederrorpage);
				dispatcher.forward(request, response);
			}
		}
	}
}