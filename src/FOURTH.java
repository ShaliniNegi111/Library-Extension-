import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

public class FOURTH {

	public static void main(String[] args) throws net.minidev.json.parser.ParseException, JSONException {
		JSONObject obj = new JSONObject();
		obj.put("USERNAME", "shalini");
		obj.put("PASSWORD", "cool");
		try (FileWriter file = new FileWriter("testing.json")) {
			file.write(obj.toString());
			file.flush();
			file.close();
		} catch (IOException ex) {
			System.out.println(ex);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		JSONParser parser = new JSONParser();
		String name = "", password = "";
		try (FileReader reader = new FileReader("data.json")) {

			obj = (JSONObject) parser.parse(new FileReader("./testing.json"));
			name = (String) obj.get("USERNAME");
			password = (String) obj.get("PASSWORD");
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(name + " " + password);
	}

}
