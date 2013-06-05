import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.bot.Context;


public class WalkingBank extends Node{

	@Override
	public boolean activate() {

		boolean inbankarea = Variable.Bank.contains(Players.getLocal().getLocation());
		
		return !inbankarea && Method.outoffood();
	}

	@SuppressWarnings({ "null", "deprecation" })
	@Override
	public void execute() {
		
		if(Players.getLocal().getPlane()!=0){
			Variable.status="Returning to the surface";
			if(Players.getLocal().getPlane()==3){
				SceneObject RopeUp = SceneEntities.getNearest(29729);
				if(RopeUp.isOnScreen()){
					RopeUp.click(true);
					for(int t = 0; t <= 200; t++){
						sleep(50,5);
						if(Players.getLocal().getPlane()==0){
							t = 300;
						}
						if(t==40){
							RopeUp.click(true);
						}
						if(t==60){
							RopeUp.click(true);
						}
						if(t==100){
							RopeUp.click(true);
						}
						if(t==175){
							RopeUp.click(true);
						}
						if(t==200){
							Variable.status="Something went wrong clicking the rope out";
							Game.logout(false);
							Context.get().getScriptHandler().stop();
						}
					}
				}else{
					Tile WalkTo = null;
					Method.walkToArea(Variable.ropeUp, WalkTo);
					WalkTo.clickOnMap();
					Method.turnTo(RopeUp, 5);
					for(int t = 0; t <= 200; t++){
						sleep(50,5);
						if(Variable.ropeUp.contains(Players.getLocal().getLocation())){
							t = 300;
						}
						if(t==40){
							WalkTo.clickOnMap();
						}
						if(t==60){
							WalkTo.clickOnMap();
						}
						if(t==120){
							RopeUp.click(true);
						}
						if(t==160){
							RopeUp.click(true);
						}
						if(t==200){
							Variable.status="Something went wrong walking to the rope out";
							Game.logout(false);
							Context.get().getScriptHandler().stop();
						}
					}
				}
			}
			if(Players.getLocal().getPlane()==2){
				SceneObject StairsUp = SceneEntities.getNearest(29672);
				if(StairsUp.isOnScreen()){
					StairsUp.click(true);
					for(int t = 0; t <= 200; t++){
						sleep(50,5);
						if(Players.getLocal().getPlane()==3){
							t = 300;
						}
						if(t==40){
							StairsUp.click(true);
						}
						if(t==60){
							StairsUp.click(true);
						}
						if(t==100){
							Variable.status="Something went wrong clicking the stairs up";
							Game.logout(false);
							Context.get().getScriptHandler().stop();
						}
					}
				}else{
					Tile WalkTo = null;
					Method.walkToArea(Variable.stairsUp, WalkTo);
					WalkTo.clickOnMap();
					Method.turnTo(StairsUp, 5);
					for(int t = 0; t <= 600; t++){
						sleep(50,5);
						if(Variable.stairsUp.contains(Players.getLocal().getLocation())){
							t = 700;
						}
						if(t==40){
							WalkTo.clickOnMap();
						}
						if(t==60){
							WalkTo.clickOnMap();
						}
						if(t==100){
							Variable.status="Something went wrong walking to the stairs up";
							Game.logout(false);
							Context.get().getScriptHandler().stop();
						}
					}
				}
			}
		}else{
			if(!Variable.Bank.contains(Players.getLocal().getLocation())){
				Variable.status="Walking to Bank";
				Walking.newTilePath(Variable.TopFloorPath).reverse().traverse();
				Task.sleep(100);
			}
		}
	}
}