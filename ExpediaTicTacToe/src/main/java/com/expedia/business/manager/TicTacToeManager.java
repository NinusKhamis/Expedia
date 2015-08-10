package com.expedia.business.manager;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.expedia.business.helper.MessageHelper;

/**
* The following Manager specialization class is the Business Layer
* Logic or "Model" for the Tic Tack Toe Game 
*/
public class TicTacToeManager {
	private char[][] tiles;		
	private TicTacToeManager gameManager;
	private MessageHelper msgHelper;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	/**
	* Initializes a new board game 
	*/	
	public void initBoard() {		
		this.gameManager = new TicTacToeManager();
		this.msgHelper = new MessageHelper();
		setSessionAttributes();		 
	}	
	
	/**
	* Manages the current board game 
	*/
	public void updateBoard(){		
		boolean bForTest = Boolean.valueOf(this.request.getSession().getAttribute("bForTest").toString());
		
		if(Boolean.valueOf(this.request.getSession().getAttribute("newGame").toString())) initBoard();
		
		this.tiles = (char[][]) this.request.getSession().getAttribute("tiles");		
		
		if(!Boolean.parseBoolean(this.request.getSession().getAttribute("gameOver").toString())) {
			for (int r=0; r<3; r++) {
				for (int c=0; c<3; c++) {
					if (this.request.getParameter("Position" + r + c) != null && tiles[r][c] == ' ')
						makeMove(r, c);
				}
			 }		
		}
		else {
			initBoard();
			deleteSessionAttributes();
		}				
		
		try {
			if(!bForTest)
				this.request.getRequestDispatcher("/WEB-INF/jsp/tictactoe.jsp").forward(this.request, this.response);
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Manages the round robin tick 
	*/
	public void makeMove(int row, int column) {		
	    char token = getToken();
	    int count = 0;
	    
	    this.tiles[row][column] = token;
	    
	    if(Boolean.valueOf(this.request.getSession().getAttribute("newGame").toString()))
	    	this.request.getSession().setAttribute("count",count);
	    else
	    	count = Integer.valueOf(this.request.getSession().getAttribute("count").toString());
	    
	    String message = "User :" + token + " makes move to Row:" + row + " and Column: " + column;
	    setMessage(message);
	    
	    count++;	    
	    this.request.getSession().setAttribute("count",count);	    
	    
	    if((hasWinner(token))) {	    	 
	    	 this.request.getSession().setAttribute("gameOver", true);
	    	 return;
	    }
	    
	    if((count == 9)) {
	    	message = "Tie Game";
			this.setMessage(message);
			this.request.getSession().setAttribute("hasTie", true);
			this.request.getSession().setAttribute("gameOver", true);						
			return;
	    }
	}
	
	/**
	* Iterates through the multi-dimensional array looking
	* for a winner.  
	*/
	public boolean hasWinner(char token) {		
		for (int r=0; r<3; r++) {
			if (tiles[r][0]==token && tiles[r][1]==token && tiles[r][2] ==token) {
				String message = "Winner " + token;
				this.setMessage(message);				
				return true;
			}
		}		
		
		for (int c=0; c<3; c++) {
			if (tiles[0][c]==token && tiles[1][c]==token && tiles[2][c]==token) {
				String message = "Winner " + token;
				this.setMessage(message);				
				return true;
			}
	    }
		
	    if (tiles[0][0]==token && tiles[1][1]==token && tiles[2][2]==token) {
	    	String message = "Winner " + token;
			this.setMessage(message);			
			return true;
	    }
	    
	    if (tiles[0][2]==token && tiles[1][1]==token && tiles[2][0]==token) {
	    	String message = "Winner " + token;
			this.setMessage(message);			
	    	return true;
	    }	    
	    
	    return false;
	}
	
	public char[][] getTiles() {
		return this.tiles;
	}
	
	public void setTiles(char[][] tiles) {
		this.tiles = tiles;		
	}	
	
	public char getToken() {
		char token = ' ';
		
		if(Boolean.valueOf(this.request.getSession().getAttribute("newGame").toString())) token = UserToken.XUser.getValue();
		else {
			char t = Character.valueOf((char) this.request.getSession().getAttribute("token"));
			if(t=='X')
				token = UserToken.OUser.getValue();
			else
				token = UserToken.XUser.getValue();			
		}
		
		this.request.getSession().setAttribute("token", token);		
		return token;
	}
	
	public String getMessage() {
		return this.request.getSession().getAttribute("message").toString();
	}
	
	public void setMessage(String message) {
		this.msgHelper = (MessageHelper) this.request.getSession().getAttribute("msgHelper");
		this.msgHelper.appendMessage(message);		
		this.request.getSession().setAttribute("msgHelper",msgHelper);
	}
	
	public boolean isGameOver() {
		return Boolean.valueOf(this.request.getSession().getAttribute("gameOver").toString());		
	}
	
	public void setSessionAttributes() {
		this.request.getSession().setAttribute("gameOver", false);
		this.request.getSession().setAttribute("msgHelper", msgHelper);		
		this.request.getSession().setAttribute("gameManager", gameManager);
	}
	
	public void deleteSessionAttributes() {
		this.gameManager = null;		
		this.setSessionAttributes();
	}
	
	public HttpServletRequest getHTTPRequest() {
		return this.request;
	}
	
	public void setHTTPRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public HttpServletResponse getHTTPResponse() {
		return this.response;
	}
	
	public void setHTTPResponse(HttpServletResponse response) {
		this.response = response;
	}	
}

enum UserToken {		
	XUser('X'),
	OUser('O');
	
	private final char value;
	private static final Map<Character, UserToken> stringToTypeMap = new TreeMap<Character, UserToken>();
	
    static {
        for (UserToken type : UserToken.values()) {
            stringToTypeMap.put(type.value, type);
        }
    }
    
    private UserToken(char value) {
        this.value = value;
    }
   
    public char getValue() {
        return value;
    }
}