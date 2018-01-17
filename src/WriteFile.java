import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
	String content;
	public WriteFile(String content) {//it gets the content (String variable in square class)
		this.content = content;
	}
		public  void write(String fileName){
			try {
				File file  = new File(fileName);			
				// if file doesn't exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(fileName,false);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	public String show() {
		return null;
	}

}
