package com.jose.core.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jose.core.constantes.Pagina;

@Controller
public class LoginController {

	private static final Log LOG = LogFactory.getLog(LoginController.class);
	@GetMapping("/login")
	public String login() {
		LOG.info("URL: /login  --- METODO: login()");
		return Pagina.LOGIN;
	}
}
