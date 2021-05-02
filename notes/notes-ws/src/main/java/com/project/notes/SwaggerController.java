/*
 * Licensed Materials - Property of IBM 6949-80Q Copyright IBM Corp. 2020 All Rights Reserved
 * US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */
package com.project.notes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Home redirection to swagger API documentation.
 */
@Controller
@ApiIgnore
public class SwaggerController {

	/**
	 * Index.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/")
	public String index() {
		return "redirect:swagger-ui.html";
	}
}