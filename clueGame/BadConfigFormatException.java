package clueGame;


import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class BadConfigFormatException extends Exception {
	public int checkDimension;

	BadConfigFormatException(String message){
		super(message);
	}
	BadConfigFormatException(int checkDimension){
		super("The given files are not accepted because the number of rows and columns do not match");
		this.checkDimension= checkDimension;

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
