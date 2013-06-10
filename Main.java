
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.bot.Context;

@Manifest(authors = {"azurepanda"}, 
name = "RoachKiller F2P", 
version = 1.1,
description = "Make bank, gain levels - RoachKiller F2P.")

public class Main extends ActiveScript implements PaintListener{

	private Tree container = null;
	private List<Node> jobs = new ArrayList<Node>();
	
	@SuppressWarnings("deprecation")
	public void onStart(){
		new GUI();
		
		Variable.startTime = System.currentTimeMillis()+1;
		
	    while (Variable.GUIopen == true) {
	    	Task.sleep(10);
	    }
		
	    if(Variable.mage==true && Variable.currentSpell==Variable.NULLSPELL){
	    	Variable.status="Please select a spell.";
	    	Game.logout(false);
			Context.get().getScriptHandler().stop();
	    }
	   
	    Variable.lootPrices = Method.getPrices(Variable.lootTier);
		
		Widgets.get(548, 160).click(true);
		Camera.setPitch(360);
		
		if(Variable.mage==true){
				Method.staffEquipped();
		}
		
		Mouse.setSpeed(Mouse.Speed.FAST);
		jobs.add(new Looting());	
		jobs.add(new Attacking());
		jobs.add(new WalkingBank());
		jobs.add(new Banking());
		jobs.add(new WalkingRoaches());
			
		container = new Tree(jobs.toArray((new Node[jobs.size()])));
	}
	
	@Override
	public int loop() {
		if(container != null){
			final Node job = container.state();
			if(job != null){
				container.set(job);
				getContainer().submit(job);
				job.join();
			}
		}
		return Variable.returns;
	}

	@Override
	public void onRepaint(Graphics g) {
		if(Variable.GUIopen == false){
			Variable.runTime = (((System.currentTimeMillis() - Variable.startTime)/1000)+1);
			Variable.runString = Method.componentTime(Variable.runTime);
			
			Variable.gainedXpAttack = Skills.getExperience(Skills.ATTACK) - Variable.STARTXPATTACK;
			Variable.gainedXpStrength = Skills.getExperience(Skills.STRENGTH) - Variable.STARTXPSTRENGTH;
			Variable.gainedXpDefense = Skills.getExperience(Skills.DEFENSE) - Variable.STARTXPDEFENSE;
			Variable.gainedXpMage = Skills.getExperience(Skills.MAGIC) - Variable.STARTXPMAGE;
			Variable.gainedXpRange = Skills.getExperience(Skills.RANGE) - Variable.STARTXPRANGE;
			Variable.gainedXpHitpoints = Skills.getExperience(Skills.CONSTITUTION) - Variable.STARTXPHITPOINTS;
			Variable.gainedXpPrayer = Skills.getExperience(Skills.PRAYER) - Variable.STARTXPPRAYER;
			
			Variable.gainedExp = Variable.gainedXpAttack + Variable.gainedXpStrength + Variable.gainedXpDefense + Variable.gainedXpMage
					+ Variable.gainedXpRange + Variable.gainedXpHitpoints + Variable.gainedXpPrayer;
			Variable.gainedExpString = String.valueOf(Variable.gainedExp);
			Variable.expPHour = (int) (Variable.gainedExp*3600/Variable.runTime);
			Variable.expPHourString = String.valueOf(Variable.expPHour);	
			
			Variable.totalGainedString = String.valueOf(Variable.totalGained);
			Variable.totalGainedPerHour = (int) (Variable.totalGained*3600/Variable.runTime);
			Variable.totalGainedPerHourString = String.valueOf(Variable.totalGainedPerHour);
			//Variable.totalGainedConcat = Variable.totalGainedString+"("+Variable.totalGainedPerHourString+")";
			
			g.setColor(Color.BLUE);
			g.drawLine(Mouse.getX() - 4, Mouse.getY() - 4, Mouse.getX() + 4, Mouse.getY() + 4);
			g.drawLine(Mouse.getX() - 4, Mouse.getY() + 4, Mouse.getX() + 4, Mouse.getY() - 4);
			
			g.drawImage(Variable.BACKGROUND, 0, 388, null);
			
			g.setColor(Color.BLACK);
			
			g.drawString(Variable.runString, 284, 514);//time ran 99 470 128 491
			g.drawString(Variable.gainedExpString, 104, 513);//exp gained
			g.drawString(Variable.expPHourString, 89, 480);//exp per hour
			//g.drawString(Variable.totalGainedString, , );//profit gained
			g.drawString(Variable.totalGainedPerHourString, 112, 445);//profit gained per hour
			//g.drawString(Variable.totalGainedConcat, 112, 445);//combined profit string
			g.drawString(Variable.VERSION, 457, 410);//version
			g.drawString(Variable.status, 229, 423);//status
		}
	}
}