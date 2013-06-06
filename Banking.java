import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.bot.Context;


public class Banking extends Node{

	@Override
	public boolean activate() {
		
		boolean inbankarea = Variable.Bank.contains(Players.getLocal().getLocation());
		
		return inbankarea && !Method.itemsready();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute() {	
		System.out.println("Banking");
		Variable.status="Banking";
		Bank.open();
		for(int x = 0; x<20; x++){
			if(Bank.isOpen()){
				x = 30;
			}
			Task.sleep(20);
		}
		Bank.depositInventory();
		Task.sleep(200,100);
		
		System.out.println("We have "+Bank.getItemCount(Variable.food) + " food left in the bank");
		Bank.withdraw(Variable.food, Variable.foodAmount);
		Bank.close();
		Task.sleep(200);
		if(!Method.itemsready()){
			if(Bank.isOpen()){
				Bank.close();
			}
			Variable.status="Something went wrong banking";
			Game.logout(false);
			Context.get().getScriptHandler().stop();
		}
	}
}

/*
for(int x = 0; x < v.staffs[].length; x++){
	boolean y = Equipment.getOneOf(staffs[x]);
	if(y == true){
		v.staffEqipped = v.staffs[x];
		switch(v.staffEquipped){
			case fireStaffId:
				v.currentSpell[0] = null;
				break;
		}
		x=100;
	}
}

int runes = Bank.getItemCount()-1;
if(runes>=0 && v.currentSpell != null){
	
}
*/
