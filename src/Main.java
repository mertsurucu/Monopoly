
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	/**
	 * That's the main class of our project. Our application starts from this class
	 * @author Mert
	 * @version 1.0
	 *
	 */
	public static void main(String[] args) throws IOException {
		Read r1=new Read(args[0]);//create an object to read command.txt
		r1.readJsonList();
		r1.readJsonProperty();
		String[] lines =r1.readFile(args[0]);//reading command.txt file
		List<String> command = new ArrayList<String>();//new array list for getting arguments
		for (String line : lines){
			String[] a=line.split("\n");
			int len=a.length;
			for(int i=0;i<len;i++){//throws in array list in string type
				command.add(a[i]);
		}	
		}
		Square s1=new Square(command);
		s1.main(command);//and send them to Square class
	}}
	
	


