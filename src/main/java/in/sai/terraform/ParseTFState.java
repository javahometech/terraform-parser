package in.sai.terraform;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ParseTFState {
	
	public static void createTerraformOutput(List<TerraformType> resources, String outputFilePath) {
		FileWriter file = null;
		try {
			file = new FileWriter(outputFilePath);
			JSONArray main = new JSONArray();
			
			for (TerraformType type : resources) {
				JSONObject obj = new JSONObject();
				
				obj.put("type",type.getType());
				obj.put("id",type.getId());
				main.add(obj);
				
			}
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

	        String json = gson.toJson(main);

			file.write(json);
			file.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			if(file != null) {
				try {
					file.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void readTerraformStateFile(String source, String out) {


		JSONParser parser = new JSONParser();

		try (Reader reader = new FileReader(source)) {

			JSONObject tfstate = (JSONObject) parser.parse(reader);
			JSONArray modules = (JSONArray) tfstate.get("modules");
			Iterator iteramodulesItr = modules.iterator();
			List<TerraformType> output = new ArrayList<TerraformType>();
			JSONObject outPutObj = new JSONObject();
			Map<String, String> map = new LinkedHashMap<String, String>();
			for (Object module : modules) {
				JSONObject mod = (JSONObject) module;
				JSONObject resources = (JSONObject) mod.get("resources");
				Set resourcesKeys = resources.keySet();
				for (Object resourcesKey : resourcesKeys) {
					JSONObject resource = (JSONObject) resources.get(resourcesKey);
					String resourceType = (String) resource.get("type");
					JSONObject primary = (JSONObject) resource.get("primary");
					String type = (String) resource.get("type");
					String id = (String) primary.get("id");
					output.add(new TerraformType(type, id));

//					System.out.println(output);
				}
			}
			createTerraformOutput(output,out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	
	}
	
	public static void main(String[] args) {
		readTerraformStateFile("terraform.json","output.json");
	}
}
