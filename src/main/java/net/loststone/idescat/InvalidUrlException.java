package net.loststone.idescat; 

public class InvalidUrlException extends Exception {

  private static final long serialVersionUID = 1L;

  public InvalidUrlException(String motiu) {
 	super(motiu); 
  }
}

