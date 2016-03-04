package gameEngine.Slotgame;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import chatapp.GameEngine.common.Paytable;
import chatapp.GameEngine.common.commonConfig;

public class SlotgameConfig extends commonConfig{
	public static String gameName = "Slotgame";
	public static JSONObject currentConfig;  
	public static JSONObject getConfig() throws JSONException, Exception{
		InputStream input = Class.class.getClassLoader().getSystemResourceAsStream(gameName.toLowerCase()+"/"+gameName+"Config.json");
		String responseString = readInputStream(input);
		JSONArray jObj = new JSONArray(responseString);
		return jObj.getJSONObject(0);
	}
	public SlotgameConfig() throws Exception {
		if(screen_row != 0){
			return;
		}
		InputStream config = Class.class.getClassLoader().getSystemResourceAsStream(gameName.toLowerCase()+"/"+gameName+"Config.json");
		InputStream paylineConfig = Class.class.getClassLoader().getSystemResourceAsStream(gameName.toLowerCase()+"/Paylines.json");
		try{
			objs = new JSONArray(readInputStream(config));
			paylines = new JSONArray(readInputStream(paylineConfig));
			currentConfig = (JSONObject) objs.get(0);
			screen_row = currentConfig.getInt("screen_row");
			screen_column = currentConfig.getInt("screen_column");
			feature_game_turn = currentConfig.getInt("feature_game_turn");
			rng_min = currentConfig.getInt("rng_min");
			rng_max = currentConfig.getInt("rng_max");
			rngSymbols = currentConfig.getJSONArray("rngSymbols");
			reels_normal = currentConfig.getJSONArray("reels_normal");
			reels_feature = currentConfig.getJSONArray("reels_feature");
			paytables = new Paytable(gameName).getObjects();
			
		}
		catch (Exception e) {
            e.printStackTrace();
        }
	}
}