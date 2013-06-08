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
		
		return !inbankarea && Method.outOfFood();
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
					int failsafe = 0;
					do{
						RopeUp.click(true);
						Task.sleep(1000,100);
						failsafe++;
						if(failsafe==15){
							Variable.status="Something went wrong clicking the rope up";
							Game.logout(false);
							Context.get().getScriptHandler().stop();
						}
					}while(Players.getLocal().getPlane()!=0);
				}else{
					Tile WalkTo = null;
					Method.walkToArea(Variable.ropeUp, WalkTo);
					Method.turnTo(RopeUp, 5);
					int failsafe = 0;
					do{
						WalkTo.clickOnMap();
						Task.sleep(1000,100);
						failsafe++;
						if(failsafe==15){
							Variable.status="Something went wrong walking to the rope up";
							Game.logout(false);
							Context.get().getScriptHandler().stop();
						}
					}while(!Variable.ropeUp.contains(Players.getLocal().getLocation()));
				}
			}
			if(Players.getLocal().getPlane()==2){
				SceneObject StairsUp = SceneEntities.getNearest(29672);
				if(StairsUp.isOnScreen()){
					StairsUp.click(true);
					int failsafe = 0;
					do{
						StairsUp.click(true);
						Task.sleep(1000,100);
						failsafe++;
						if(failsafe==15){
							Variable.status="Something went wrong clicking the stairs up";
							Game.logout(false);
							Context.get().getScriptHandler().stop();
						}
					}while(Players.getLocal().getPlane()!=3);
				}else{
					Tile WalkTo = null;
					Method.walkToArea(Variable.stairsUp, WalkTo);
					WalkTo.clickOnMap();
					Method.turnTo(StairsUp, 5);
					int failsafe = 0;
					do{
						WalkTo.clickOnMap();
						Task.sleep(1000,100);
						failsafe++;
						if(failsafe==15){
							Variable.status="Something went wrong walking to the stairs up";
							Game.logout(false);
							Context.get().getScriptHandler().stop();
						}
					}while(!Variable.stairsUp.contains(Players.getLocal().getLocation()));
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