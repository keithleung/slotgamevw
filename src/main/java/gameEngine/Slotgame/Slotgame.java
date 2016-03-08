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
	
	public boolean isFeature = false;
	public boolean isTest = false;
	public LinkedList eachWins; 
	public int totalWins =0;
	
	public Slotgame() throws Exception{
		setConfig();
	}
	
	public JSONObject getConfig() throws Exception {
		// TODO Auto-generated method stub
		return gameConfig.getConfig();
	}

	public LinkedList getEachWins() {
		// TODO Auto-generated method stub
		return this.eachWins;
	}

	public JSONArray getFinalResult() throws JSONException {
		// TODO Auto-generated method stub
		return genOutputResult();
	}

	public int getTotalWins() throws JSONException {
		// TODO Auto-generated method stub
		return this.totalWins;
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
		return this.isFeature;
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
		JSONArray temp = new JSONArray();
		this.finalResult = temp;
		if(this.isTest){
			//handle test custom symbol data
			for(int i = 0; i < 5; i++)
			{
				JSONArray symbols_array = reels_normal.getJSONArray(i);	
				genSymbol(symbols_array, this.customSymbol.getInt(i));
			}			
		}
		else{
			JSONArray ran_sym = new JSONArray();
			this.customSymbol = ran_sym;
			for (int i = 0; i < 5; i++) {
				//handle random number.
				double ran_num;
				ran_num = Rng.rng(1,100);
				rngNumber = (int) ran_num;
				try	{
					JSONArray symbols_array = reels_normal.getJSONArray(i);
					this.customSymbol.put(rngNumber);
					genSymbol(symbols_array, rngNumber);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		spinWin();
		//win
		this.finalResult = genOutputResult();
	}
	
	private JSONArray genOutputResult() {
			// handle outputResult for display.
			try {
				JSONObject nothing = new JSONObject();
				JSONObject Start = new JSONObject();
				JSONObject End = new JSONObject();
				JSONObject playedSpin = new JSONObject();
				JSONArray outputresult = new JSONArray();
				Start.put("event","gameStart");
				Start.put("context",nothing);
				playedSpin.put("event","playedSpin");
				playedSpin.put("context",this.customSymbol);
				End.put("event","gameEnd");
				End.put("context",nothing);
				outputresult.put(Start);
				outputresult.put(playedSpin);
				outputresult.put(this.spinWin);
				outputresult.put(End);
				return outputresult;
			}
			catch (Exception e)	{
				e.printStackTrace();
				return null;
			}
	}

	public void genSymbol(JSONArray reels,int rngNumber) throws JSONException{
		//get number of symbol.
		int symbol_index;
		String symbol_temp;
		try	{
			symbol_index = rngNumber % reels.length();
			symbol_temp = reels.getString(symbol_index);
			this.finalResult.put(symbol_temp);
		}
		catch(Exception e)	{
			e.printStackTrace();
		}
		
	}
	
	public void spinWin(){
		//handle result win or not.
		int equalCount = 0;
		int pay = 0;
		LinkedList eachWin_temp = new LinkedList();
		if(this.eachWins == null)
		{
			this.eachWins = eachWin_temp;
		}
		JSONObject spinWinOut = new JSONObject();
		this.spinWin = spinWinOut;
		for(int i=1; i < this.finalResult.length(); i++)
		{
			String s1= "",s2="";
			try	{
				s1 = this.finalResult.getString(i-1);
				s2 = this.finalResult.getString(i);
			}
			catch(Exception e)	{
				e.printStackTrace();
			}
			
			if(s1.equals(s2))
			{
				equalCount++;
			}
		}
		
		if(equalCount == 4)
		{
			System.out.println("Win");
			try{
				JSONArray Payline = new JSONArray();
				JSONObject context1 = new JSONObject();
				JSONObject context2 = new JSONObject();
				JSONObject paytable = new JSONObject();
				for(int i=0; i < 5; i++)
				{
					Payline.put(1);
				}
				context2.put("Payline", Payline);
				context1.put("symbol","CUP");
				context1.put("num_of_sys",5);
				context1.put("context",context2);
				paytable = this.paytables.getJSONObject("CUP");
				pay = paytable.getInt("5");
				pay = this.bet * pay;
				context1.put("pay",pay);
				this.spinWin.put("context",context1);
				this.spinWin.put("event","spinWin");
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else
		{
			System.out.println("Lost");
			try
			{
				JSONObject nothing = new JSONObject();
				this.spinWin.put("event", "spinWin");
				this.spinWin.put("context", nothing);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		this.eachWins.add(pay);				
		this.totalWins =  this.totalWins + pay;
	}

}
