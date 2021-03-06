package roachkiller;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.bot.Context;


public class WalkingRoaches extends Node{

	@Override
	public boolean activate() {
		
		boolean inroacharea = Variable.currentArea.contains(Players.getLocal().getLocation());
		
		return !inroacharea && Method.itemsReady();
	}

	@SuppressWarnings({ "deprecation"})
	@Override
	public void execute() {
			
		if(!Variable.ROPEDOWN.contains(Players.getLocal().getLocation())){
			Variable.status="Walking to Roaches";
			Walking.newTilePath(Variable.WALKINGBANKPATH).traverse();
			Task.sleep(100,50);
		}else{
			Variable.status="Clicking rope down";
			SceneObject RopeDown = SceneEntities.getNearest(29728);
			Method.turnTo(RopeDown, 5);
			RopeDown.click(true);
			int failsafe= 0;
			do{
				RopeDown.click(true);
				Task.sleep(1000,100);
				failsafe++;
				if(failsafe==15){
					Variable.status="Something went wrong clicking the rope down";
					Game.logout(false);
					Context.get().getScriptHandler().stop();
				}
			}while(Players.getLocal().getPlane()!=3);
		}
		if(!Variable.currentArea.contains(Players.getLocal().getLocation()) && Players.getLocal().getPlane() == 3){
			SceneObject StairsDown = SceneEntities.getNearest(29671);
			if(StairsDown != null){
				if(StairsDown.isOnScreen()){
					Variable.status="Clicking stairs";
					StairsDown.click(true);
					int failsafe = 0;
					do{
						StairsDown.click(true);
						Task.sleep(1000,100);
						failsafe++;
						if(failsafe==15){
							Variable.status="Something went wrong clicking the stairs down";
							Game.logout(false);
							Context.get().getScriptHandler().stop();
						}
					}while(Players.getLocal().getPlane() != 2);
				}else{
					Variable.status="Walking to stairs down";
					Method.turnTo(StairsDown, 5);
					int failsafe = 0;
					do{
						Tile WalkTo = null;
						WalkTo = Method.walkToArea(Variable.STAIRSDOWN, WalkTo);
						WalkTo.clickOnMap();
						Task.sleep(1000,100);
						failsafe++;
						if(failsafe==15){
							Variable.status="Something went wrong walking to the stairs down";
							Game.logout(false);
							Context.get().getScriptHandler().stop();
						}
					}while(!Variable.STAIRSDOWN.contains(Players.getLocal().getLocation()));
				}
			}
		}
	}
}
