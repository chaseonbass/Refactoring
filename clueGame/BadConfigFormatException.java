package clueGame;


import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class BadConfigFormatException extends Exception {

	BadConfigFormatException(String message){
		super(message);
	}

	@Override
	public String toString() {
		return "The file you are reading has an unsupported format";
	}
	
	
	public BadConfigFormatException() throws FileNotFoundException {
		PrintWriter out= new PrintWriter("logfile.txt");
		out.println(toString());
		
		out.close();
	}
	
}
