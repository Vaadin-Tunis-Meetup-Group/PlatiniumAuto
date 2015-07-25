package org.mendole.platiniumauto.controllers;

import org.mendole.platiniumauto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller to handle accounts activation
 * 
 * @author HaithemMosbahi
 *
 */
@Controller
public class RegistrationController {
	
	@Autowired
    private UserService userService;
  
    

    /**
     * GET  /account/activate -> activate the registered user.
     */
    @RequestMapping(value = "/account/activate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String activateAccount(@RequestParam(value = "key") String key) {
        //userService.activateRegistration(key);
        return "redirect:/";
    }

}
