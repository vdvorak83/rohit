package net.petrikainulainen.spring.testmvc.todo.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import net.petrikainulainen.spring.testmvc.config.TestContext;
import net.petrikainulainen.spring.testmvc.config.WebAppContext;
import net.petrikainulainen.spring.testmvc.todo.TestUtil;
import net.petrikainulainen.spring.testmvc.todo.dto.TodoDTO;
import net.petrikainulainen.spring.testmvc.todo.model.Todo;
import net.petrikainulainen.spring.testmvc.todo.model.TodoBuilder;
import net.petrikainulainen.spring.testmvc.todo.service.TodoService;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebAppContext.class })
@WebAppConfiguration
public class MyTodoControllerTest {
	private MockMvc mockMvc;
	@Autowired
	private TodoService todoServiceMock;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(todoServiceMock);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void add_EmptyToDoEntry_ShouldReturnValidationErrorForTitle()
			throws Exception {
		TodoDTO dto = new TodoDTO();

		mockMvc.perform(
				post("/api/todo").contentType(TestUtil.APPLICATION_JSON_UTF8)
						.content(TestUtil.convertObjectToJsonBytes(dto)))
				.andExpect(status().isBadRequest())
				.andExpect(
						content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.fieldErrors", hasSize(1)))
				.andExpect(jsonPath("$.fieldErrors[0].path", is("title")))
				.andExpect(
						jsonPath("$.fieldErrors[0].message",
								is("The title cannot be empty.")));

		verifyZeroInteractions(todoServiceMock);
	}

//	@Test
//	public void findAll_ShouldAddTodoEntriesToModelAndRenderTodoListView()
//			throws Exception {
//		// Todo first=new TodoBuilder().id()
//		mockMvc.perform(get("/")).andExpect(status().isOk())
//				.andExpect(view().name("todo/list"))
//				.andExpect(forwardedUrl("/WEB-INF/jsp/todo/list.jsp"))
//				.andExpect(model().attribute("todos", hasSize(2)));
//
//		verify(todoServiceMock, times(1)).findAll();
//		verifyNoMoreInteractions(todoServiceMock);
//
//	}
}
