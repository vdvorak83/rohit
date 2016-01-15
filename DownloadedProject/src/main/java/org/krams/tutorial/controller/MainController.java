package org.krams.tutorial.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles and retrieves the common or admin page depending on the URI template.
 * A user must be log-in first he can access these pages.  Only the admin can see
 * the adminpage, however.
 */
@Controller
@RequestMapping("/main")
public class MainController {

	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="sessionRegistry")
	private SessionRegistryImpl sessionRegistry;
	
	/**
	 * Handles and retrieves the common JSP page that everyone can see
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/common", method = RequestMethod.GET)
    public String getCommonPage() {
    	logger.debug("Received request to show common page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "commonpage";
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage() {
    	logger.debug("Received request to show admin page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/adminpage.jsp
    	return "adminpage";
	}
    
    /**
	 * Handles and retrieves list of logged-in users as JSP view
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsersPage(Model model) {
    	logger.debug("Received request to show users page");
    
    	logger.debug("Total logged-in users: " + sessionRegistry.getAllPrincipals().size());
    	logger.debug("List of logged-in users: ");
    	for (Object username: sessionRegistry.getAllPrincipals()) {
    		logger.debug(username);
    	}
    	logger.debug("Total sessions including expired ones: " + sessionRegistry.getAllSessions(sessionRegistry.getAllPrincipals().get(0), true).size());
    	logger.debug("Total sessions: " + sessionRegistry.getAllSessions(sessionRegistry.getAllPrincipals().get(0), false).size());

    	// Attach to model list of users and granted authorities
    	model.addAttribute("users", sessionRegistry.getAllPrincipals());
    	model.addAttribute("total", sessionRegistry.getAllPrincipals().size());
    	
    	// This will resolve to /WEB-INF/jsp/userspage.jsp
    	return "userspage";
	}
}
