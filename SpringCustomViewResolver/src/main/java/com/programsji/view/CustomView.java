package com.programsji.view;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.programsji.model.Person;

public class CustomView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BufferedWriter writer = new BufferedWriter(response.getWriter());
		try {
			response.setHeader("Content-Disposition",
					"attachment; filename=\"mytextfile.txt\"");
			List<Person> personList = (List<Person>) model.get("personlist");
			for (Person person : personList) {
				writer.write(person.getId() + "," + person.getFname() + ","
						+ person.getLname());
				writer.newLine();
			}
		} catch (IOException ex) {

		} finally {
			writer.flush();
			writer.close();
		}
	}
}
