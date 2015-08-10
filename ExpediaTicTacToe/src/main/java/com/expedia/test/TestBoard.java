/*
* @author  Ninus Khamis (MSc)
 * @version 1.3
 * @since   2015-06-30 
 */
package com.expedia.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.mockito.Mockito;
import com.expedia.business.helper.MessageHelper;
import com.expedia.business.manager.TicTacToeManager;
import static org.junit.Assert.assertEquals;


/**
* The following class in incharge of implementing the 
* test cases for the Tic Tac Toe application
*/ 
public class TestBoard extends Mockito {
	private HttpServletRequest request;	
	private HttpSession session;
	private char[][] tiles;
	private TicTacToeManager gm;
	
	/**
	* Initializes the board 
	*/
	public void init() {		
		this.tiles = new char[3][3];
		MessageHelper msgHelper;
		
		this.request = Mockito.mock(HttpServletRequest.class);
		this.session = Mockito.mock(HttpSession.class);		
		when(this.request.getSession()).thenReturn(this.session);	
				
		gm = new TicTacToeManager();
		
		for (int r=0; r<3; r++)
			for (int c=0; c<3; c++)
				tiles[r][c] = ' ';
		
		gm.setTiles(this.tiles);
		
		msgHelper = new MessageHelper();	
		
		when(this.request.getSession().getAttribute("bForTest")).thenReturn(true);
		when(this.request.getSession().getAttribute("newGame")).thenReturn(true);
		when(this.request.getSession().getAttribute("gameOver")).thenReturn(false);
		when(this.request.getSession().getAttribute("tiles")).thenReturn(tiles);
		when(this.request.getSession().getAttribute("msgHelper")).thenReturn(msgHelper);
	}
	
	
	/**
	* Test for 
	* |X|X|X|  
	* | | | |
	* | | | |
	*/
	@Test
	public void testRowOne()  {
		init();
		
		when(this.request.getParameter("Position00")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		when(this.request.getParameter("Position01")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		when(this.request.getParameter("Position02")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		assertEquals("Has Winner true expected", 'X', 'X');		
	}
	
	/**
	* Test for
	* | | | | 
	* |X|X|X|
	* | | | |
	*/
	@Test
	public void testRowTwo()  {
		init();
		
		when(this.request.getParameter("Position10")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		when(this.request.getParameter("Position11")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		when(this.request.getParameter("Position12")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		assertEquals("Has Winner true expected", 'X', 'X');		
	}
	
	/**
	* Test for
	* | | | |
	* | | | | 
	* |X|X|X|
	*/
	@Test
	public void testRowThree()  {
		init();
		
		when(this.request.getParameter("Position20")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		when(this.request.getParameter("Position21")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		when(this.request.getParameter("Position22")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		assertEquals("Has Winner true expected", 'X', 'X');		
	}
	
	/**
	* Test for
	* |X| | |
	* |X| | | 
	* |X| | |
	*/
	@Test
	public void testColumnOne()  {
		init();
		
		when(this.request.getParameter("Position00")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		when(this.request.getParameter("Position10")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		when(this.request.getParameter("Position20")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		assertEquals("Has Winner true expected", 'X', 'X');		
	}
	
	/**
	* Test for
	* | |X| |
	* | |X| | 
	* | |X| |
	*/
	@Test
	public void testColumnTwo()  {
		init();
		
		when(this.request.getParameter("Position01")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		when(this.request.getParameter("Position11")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		when(this.request.getParameter("Position21")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		assertEquals("Has Winner true expected", 'X', 'X');		
	}
	
	/**
	* Test for
	* |X| | |
	* | |X| | 
	* | | |X|
	*/
	@Test
	public void testDiagonalLeftToRight() {
		init();
		
		when(this.request.getParameter("Position00")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		when(this.request.getParameter("Position11")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		when(this.request.getParameter("Position22")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		assertEquals("Has Winner true expected", 'X', 'X');
	}
	
	/**
	* Test for
	* | | |X|
	* | |X| | 
	* |X| | |
	*/
	@Test
	public void testDiagonalRightToLeft() {
		init();
		
		when(this.request.getParameter("Position02")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		when(this.request.getParameter("Position11")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		when(this.request.getParameter("Position20")).thenReturn(" ");
		gm.setHTTPRequest(this.request);
		gm.updateBoard();
		
		assertEquals("Has Winner true expected", 'X', 'X');
	}	
	
}