package gameEngine.Slotgame;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import chatapp.GameEngine.common.Bet;
import chatapp.GameEngine.common.Rng;
import chatapp.GameEngine.common.SlotgameEngine;

public class Slotgame implements SlotgameEngine{
	static SlotgameConfig gameConfig;
	static int bet; 
	public JSONObject spinWin;
	public JSONObject currentConfig;
	public JSONArray finalResult;
	public int paylines;
	public JSONObject paytables;
	public JSONArray customSymbol;
	
	public boolean isFeature;
	public boolean isTest = false;
	public LinkedList eachWins; 
	public int totalWins;
	
	public Slotgame() throws Exception{
		setConfig();
	}
	
	public JSONObject getConfig() throws Exception {
		// TODO Auto-generated method stub
		return gameConfig.getConfig();
	}

	public LinkedList getEachWins() {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONArray getFinalResult() throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getTotalWins() throws JSONException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void run_feature(int arg0) throws JSONException {
		// TODO Auto-generated method stub
		
	}

	public void setBet(Bet bet) throws JSONException {
		this.bet = bet.getBet();
		this.paylines = bet.getPaylines();
	}

	public void setCustomValues(JSONArray customSymbols) throws JSONException {
		// TODO Auto-generated method stub
		this.isTest = true;
		this.customSymbol = customSymbols;
	}

	public boolean triggeredFeatureGame() {
		// TODO Auto-generated method stub
		return false;
	}
	public void setConfig() throws Exception{
		if (gameConfig == null)
		{
			gameConfig = new SlotgameConfig();
		}
		this.currentConfig = gameConfig.currentConfig;
		this.paytables = gameConfig.paytables.getJSONObject("pays");
		
	}
	
	public void run_normal() throws JSONException {
		// TODO Auto-generated method stub
		JSONArray reels_normal = this.currentConfig.getJSONArray("reels_normal");
		int rngNumber = 0;
		if(this.isTest){
			//handle test custom symbol data
		}
		else{
			for (int i = 0; i < 5; i++) {
				//handle random number.

			}
		}
		
		//win
		
		this.finalResult = genOutputResult();
	}
	
	private JSONArray genOutputResult() {
		// handle outputResult for display.
		return null;
	}

	public void genSymbol(JSONArray reels,int rngNumber) throws JSONException{
		//get number of symbol.
	}
	public void spinWin(){
		//handle result win or not.
	}

}
