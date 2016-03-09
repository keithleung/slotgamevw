package gameEngine.Slotgame;
import org.json.JSONArray;
import org.json.JSONObject;
import chatapp.GameEngine.common.Bet;
import chatapp.GameEngine.common.SlotgameEngine;

public class main {
	public static void main(String[] args) throws Exception{
		try {
			JSONObject customObject = new JSONObject();
			JSONArray customValues = new JSONArray();
			JSONArray customSymbol = new JSONArray();

			customSymbol.put(33);
			customSymbol.put(7);
			customSymbol.put(19);
			customSymbol.put(35);
			customSymbol.put(1);

			
			Bet bet = new Bet(20,1);
			SlotgameEngine obj = new Slotgame();
			
			obj.setBet(bet);
			obj.setCustomValues(customSymbol);
			obj.run_normal();

			System.out.println("finalResult : "+obj.getFinalResult());
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
