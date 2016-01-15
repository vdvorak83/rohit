package com.programsji.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.ResponseWrapper;

import org.apache.http.HttpStatus;
import org.apache.mina.filter.reqres.Request;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONWriter;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.config.http.HttpSecurityBeanDefinitionParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.InternalResourceView;
import org.thymeleaf.context.AbstractProcessingContext;

import com.programsji.bo.Hobby;
import com.programsji.bo.Person;
import com.programsji.enums.MyEnum;
import com.programsji.exceptions.CustomNoSuchRequestHandingMethodException;
import com.programsji.security.User;
import com.programsji.security.UserDao;
import com.programsji.security.UserService;
import com.programsji.security.User_hib;
import com.programsji.service.PersonService;

@SessionAttributes("myperson")
@Controller
public class HomeController {

	@Value("${name}")
	String propertyvalue1;

	@Autowired
	PersonService service;

	@Autowired
	UserService userservice;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, Locale locale, HttpServletRequest req,
			HttpServletResponse res) {
		model.addAttribute("message", "This is a Simple Message");
		String lastUsedUserName = (String) req.getSession().getAttribute(
				"SPRING_SECURITY_LAST_USERNAME");
		model.addAttribute("message1", propertyvalue1);
		// Person p = new Person("Rohit", 10, 1000.43f, new Date());
		// Person p = service.getDefaultPerson();
		Person p = new Person();
		p.setAge(1000);
		model.addAttribute("myperson", p);

		String url = getRedirectedUrl(req, res);

		// HttpSecurityBeanDefinitionParser s;
		// HandlerExecutionChain
		System.out.println(MyEnum.values()[0].name());
		System.out.println(MyEnum.values()[0].ordinal());
		try {
			ObjectMapperExample();
		} catch (Exception ex) {
		}

		return "home";
	}

	@RequestMapping(value = "/wow", method = RequestMethod.GET)
	public String wow(Model model, Locale locale) {
		Person p = null;
		model.addAttribute("message", "This is a Simple Message From Wow");
		model.addAttribute("message1", propertyvalue1);
		// Person p = new Person("Wow Rohit", 10, 1000.43f, new Date());
		model.addAttribute("myperson", p);
		return "home";
	}

	// @Secured(value = "ROLE_ADMIN")
	// , produces = { "application/xml" }
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public InternalResourceView homePost(
			@ModelAttribute("myperson") Person person, BindingResult br) {
		if (br.hasErrors()) {
			System.out.println("BR has Errors");
		} else {
			System.out.println("BR has No Error");
		}
		return new InternalResourceView("WEB-INF/pages/showdata.jsp");
	}

	@RequestMapping(value = "/showdata", method = RequestMethod.GET)
	public String homePost_1(@ModelAttribute("myperson") Person person,
			BindingResult br) {
		if (br.hasErrors()) {
			System.out.println("BR has Errors");
		} else {
			System.out.println("BR has No Error");
		}

		return "showdata";
	}

	@RequestMapping(value = "/jsonhome", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ResponseBody
	// public ResponseEntity<Person> getJsonResponse(Model model, Locale locale)
	// {
	public Person getJsonResponse(Model model, Locale locale) {
		List<Hobby> hobbieslist = new ArrayList<Hobby>();
		hobbieslist.add(new Hobby(1, "Facebook"));
		hobbieslist.add(new Hobby(2, "Whatsapp"));

		// return new ResponseEntity<Person>(new Person("Rohit", 10, 1000.43f,
		// new Date(), hobbieslist),
		// org.springframework.http.HttpStatus.OK);
		return new Person("Rohit", 10, 1000.43f, new Date(), hobbieslist);
	}

	// @Secured(value = "ROLE_USER")
	@RequestMapping(value = "/employee/home", method = RequestMethod.GET)
	public String home_1(Model model, Locale locale, HttpServletRequest req,
			HttpServletResponse res) {
		model.addAttribute("message", "This is a Simple Message");
		model.addAttribute("message1", propertyvalue1);
		Person p = new Person("Rohit", 10, 1000.43f, new Date());
		model.addAttribute("myperson", p);
		String url = getRedirectedUrl(req, res);
		// String s = (pe.encodePassword(password, salt));

		return "home";
	}

	@RequestMapping(value = "/customer/home", method = RequestMethod.GET)
	public String home_2(Model model, Locale locale)
			throws NoSuchRequestHandlingMethodException {

		int i = 0;
		// if (i == 0) {
		// throw new NoSuchRequestHandlingMethodException("", this.getClass());
		// }

		// Object obj = SecurityContextHolder.getContext().getAuthentication()
		// .getPrincipal();
		//
		// Authentication auth = SecurityContextHolder.getContext()
		// .getAuthentication();
		// if (!(auth instanceof AnonymousAuthenticationToken)) {
		//
		// }

		model.addAttribute("message", "This is a Simple Message");
		model.addAttribute("message1", propertyvalue1);
		Person p = new Person("Rohit", 10, 1000.43f, new Date());
		model.addAttribute("myperson", p);

		String password = "malhotra";
		UserDao dao = new UserDao();
		ReflectionSaltSource saltSource = new ReflectionSaltSource();
		UserDetails ud = dao.getUserDetailByUserName("rohit");
		// Object salt = saltSource.getSalt();
		PasswordEncoder pe = new BCryptPasswordEncoder();
		String s = pe.encode("malhotra");

		// Set<String> s12 = AuthorityUtils.authorityListToSet(auth
		// .getAuthorities());
		// System.out.println("Granted Authorities Are : " + s12);
		return "home";
	}

	protected String getRedirectedUrl(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				Enumeration s = session.getAttributeNames();
				while (s.hasMoreElements()) {
					String s1 = s.nextElement().toString();
					System.out.println("S1 is " + s1);
				}
				SavedRequest savedRequest = new HttpSessionRequestCache()
						.getRequest(request, response);
				return savedRequest.getRedirectUrl();
			}
			return "";
		} catch (Exception ex) {
			return "";
		}
	}

	// @Autowired
	// @Qualifier(value = "md5passwordencoder")
	// private PasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier(value = "saltSource")
	private SaltSource saltSource;

	@RequestMapping(value = "/createuser", method = RequestMethod.GET)
	public String createUser() {
		User_hib user = new User_hib();
		user.setUserId(1);
		user.setUserName("My User Name");
		user.setPassword("MyPassword");
		user.setEnabled(true);
		userservice.saveUser(user);
		return "home";
		// u.setUsername("MyUser");
		// u.setPassword("MyPassword");
		// u.setPassword(passwordEncoder.encode("anypassword"));
		// getEntityNamager().persist(u); // optional
		// return u;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView showerrorpage(ConstraintViolationException exception,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", exception.getMessage());
		mv.setViewName("message");
		return mv;

	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public String home_3(User user) {
		return "home";
	}

	private void ObjectMapperExample() throws JsonGenerationException,
			JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		List<Hobby> hobbieslist = new ArrayList<Hobby>();
		hobbieslist.add(new Hobby(1, "Facebook"));
		hobbieslist.add(new Hobby(2, "Whatsapp"));

		String s = objectMapper.writeValueAsString(new Person("Rohit", 10,
				1000.43f, new Date(), hobbieslist));
		System.out.println(s);

	}

}
