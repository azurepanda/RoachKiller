package roachkiller;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import org.powerbot.game.bot.Context;

import sk.action.ActionBar;
import sk.action.BarNode;

public class Attacking extends Node{

	@Override
	public boolean activate() {
		
		boolean inroacharea = Variable.currentArea.contains(Players.getLocal().getLocation());
		
		return inroacharea && !Method.outOfFood();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute() {
		
		if(Players.getLocal().getHealthPercent()<60){
			Variable.status="Eating food";
			WidgetChild e = Inventory.getItem(Variable.food).getWidgetChild();
			e.interact("Eat");
			Task.sleep(600);
		}
		
		if(Variable.mage==true && Method.outOfRunes()){
			Variable.status="Out of runes, Logging out";
			Game.logout(false);
			Context.get().getScriptHandler().stop();
		}
		
		if(!Walking.isRunEnabled() && Walking.getEnergy() > 25){
			Walking.setRun(true);
		}
		
		NPC target = NPCs.getNearest(new Filter<NPC>(){
			public boolean accept(NPC npc){
				return (npc.getInteracting() == null || npc.getInteracting().equals(Players.getLocal()))
						&& npc.getId() == Variable.ROACHID
						&& Variable.currentArea.contains(npc)
						&& npc.getModel() != null
						&& npc.getHealthPercent() > 0;
			}
		});
		
		if(target==null){
			Variable.status="Looking for target";
			if(Players.getLocal().getPlane()==2){
				Walking.newTilePath(Variable.LOOKINGROACHPATHF2).traverse();
			}
		}
		
		if(Players.getLocal().getInteracting() == null
				&& target != null){
			Variable.status="Found target";
			Method.turnTo(target, 5);
			if(target.isOnScreen()){
				Variable.status="Attacking";
				target.interact("Attack", target.getName());
				Task.sleep(300, 400);
				if(Variable.range == true && Method.outOfAmmo()){
					Variable.status="Out of ammo, Logging out";
					Game.logout(false);
					Context.get().getScriptHandler().stop();
				}
			}else{
				Walking.walk(target.getLocation());
			}
		}

		if(!(Players.getLocal().getInteracting() == null)){
			Task.sleep(100);
			if(!(Players.getLocal().getAnimation() == -1)){
				if(!ActionBar.isExpanded()){
					ActionBar.expand(true);
					if(ActionBar.isFocused()){
						ActionBar.clearFocus();
					}
				}		
				while(target.getInteracting() != null && Players.getLocal().isInCombat()){
					for (int i=0; i<12; i++){
						if(target==null){
							i = 100;
						}else{
							ActionBar.getNode(new Filter<BarNode>() {
								public boolean accept(BarNode a) {
									return a.canUse();
								}
						    }).use();
						}
					}
				}
				
				if(Players.getLocal().getPlane() == 2){
					Task.sleep(3500);
				}
			}
		}
		
		if(Players.getLocal().getInteracting() == null && ActionBar.isExpanded()){
			ActionBar.expand(false);
		}
	}

}
