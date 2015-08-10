/*
* @author  Ninus Khamis (MSc)
 * @version 1.3
 * @since   2015-06-30 
 */
package com.expedia.tictactoe.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.expedia.business.manager.TicTacToeManager;

/** 
* This class acts as a "Meta Front Controller" capable of linking many view to
* many related models respectively. Does so using Reflections.
*/ 
@Controller
public class TicTacToeController {
	@RequestMapping(value = "/tictactoe", method = RequestMethod.GET)
	public String test() {
		return "tictactoe";
	}
	
	@RequestMapping(value = "/frontController", method = RequestMethod.POST)	
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		TicTacToeManager tm = new TicTacToeManager();
		tm.setHTTPRequest(request);
		tm.setHTTPResponse(response);
		tm.updateBoard();
	}	
}