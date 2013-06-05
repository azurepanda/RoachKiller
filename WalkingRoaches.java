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
		
		return !inroacharea && Method.itemsready();
	}

	@SuppressWarnings({ "deprecation", "null" })
	@Override
	public void execute() {
			
		if(!Variable.ropeDown.contains(Players.getLocal().getLocation())){
			Variable.status="Walking to Roaches";
			Walking.newTilePath(Variable.TopFloorPath).traverse();
			Task.sleep(100);
		}else{
			Variable.status="Clicking rope down";
			SceneObject RopeDown = SceneEntities.getNearest(29728);
			RopeDown.click(true);
			for(int t = 0; t <= 200; t++){
				sleep(50,5);
				if(Players.getLocal().getPlane()==3){
					t = 300;
				}
				if(t == 25){
					RopeDown.click(true);
				}
				if(t == 50){
					RopeDown.click(true);
				}
				if(t == 75){
					RopeDown.click(true);
				}
				if(t == 150){
					RopeDown.click(true);
				}
				if(t == 200){
					Variable.status="Something went wrong walking to Roaches";
					Game.logout(false);
					Context.get().getScriptHandler().stop();
				}
			}
		}
		if(!Variable.currentArea.contains(Players.getLocal().getLocation())){
			SceneObject StairsDown = SceneEntities.getNearest(29671);
			if(StairsDown != null){
				if(StairsDown.isOnScreen()){
					StairsDown.click(true);
					for(int t = 0; t <= 300; t++){
						sleep(50,5);
						if(Players.getLocal().getPlane()==2){
							t = 400;
						}
						if(t==50){
							StairsDown.click(true);
						}
						if(t==100){
							StairsDown.click(true);
						}
						if(t==200){
							StairsDown.click(true);
						}
						if(t==250){
							StairsDown.click(true);
						}
						if(t==300){
							Variable.status="Something went wrong clicking the stairs down";
							Game.logout(false);
							Context.get().getScriptHandler().stop();
						}
					}
				}else{
					Tile WalkTo = null;
					Method.walkToArea(Variable.stairsDown, WalkTo);
					WalkTo.clickOnMap();
					Method.turnTo(StairsDown, 5);
					for(int t = 0; t <= 400; t++){
						sleep(50,5);
						if(Players.getLocal().getPlane()==2){
							t = 500;
						}
						if(t==100){
							WalkTo.clickOnMap();
						}
						if(t==250){
							WalkTo.clickOnMap();
						}
						if(t==300){
							WalkTo.clickOnMap();
						}
						if(t==400){
							Variable.status="Something went wrong walking to the stairs down";
							Game.logout(false);
							Context.get().getScriptHandler().stop();
						}
					}
				}
			}
		}
	}
}

//SceneObject StairsDown = SceneEntities.getNearest(29671);
//SceneObject StairsUp = SceneEntities.getNearest(29672);
//SceneObject RopeDown = SceneEntities.getNearest(29728);
//SceneObject RopeUp = SceneEntities.getNearest(29729);
