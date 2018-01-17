import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Read {

	public String path;
	public Read(String path) {
		this.path=path;
	}
	
	public Read(){
	}
	Map<Integer,String> propertyId = new TreeMap<Integer,String>();
	Map<String,Integer> propertyCost = new TreeMap<String,Integer>();

	List<String> chanceList = new ArrayList<String>();
	List<String> communityChestList = new ArrayList<String>();
	public void readJsonProperty(){//its read properties
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("property.json"));
			JSONObject jsonObject =  (JSONObject) obj;

			for (int i = 1; i <= 3; i++) {
				JSONArray arrays = (JSONArray) jsonObject.get(String.valueOf(i));
				for (Object object : arrays) {
					JSONObject jsonObjects =  (JSONObject) object;
					String id = (String) jsonObjects.get("id");
					String name = (String) jsonObjects.get("name");
					String cost = (String) jsonObjects.get("cost");
					Integer newId=Integer.parseInt(id);
					Integer newCost=Integer.parseInt(cost);
					propertyId.put(newId, name);
					propertyCost.put(name, newCost);
				}
				
			} 	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public void readJsonList(){//its put the cards into tree map 
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("list.json"));
			JSONObject jsonObject =  (JSONObject) obj;
			JSONArray arraysChance = (JSONArray) jsonObject.get("chanceList");	    	
			JSONArray arraysCommunity = (JSONArray) jsonObject.get("communityChestList");
			for (Object object : arraysChance) {
				JSONObject jsonObjects =  (JSONObject) object;
				String item = (String) jsonObjects.get("item");
				chanceList.add(item);
				
			}
			for (Object object : arraysCommunity) {
				JSONObject jsonObjects =  (JSONObject) object;
				String item = (String) jsonObjects.get("item");
				communityChestList.add(item);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
	}
	public String[] readFile(String path){//to read command.txt
		try {
			int i=0;
			int length=Files.readAllLines(Paths.get(path)).size();
			String[] results=new String[length];
			for (String line : Files.readAllLines(Paths.get(path))){
				results[i++]=line;
			}
			return results;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}	
	}
	
	
	
}