package summoning;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Summoning;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.core.script.job.Task;


public class Banking extends Node{
	
	private final int shardId = 12183;
	private final int pouchId = 12155;
	private final int kyattId = 12812;
	
	public final Area bankArea = new Area(new Tile[] {
			new Tile(2448, 3079, 0), new Tile(2449, 3100, 0),
			new Tile(2436, 3100, 0), new Tile(2436, 3079, 0)
	});

	@Override
	public boolean activate() {
		//Standard checks for Pouch resources
		return bankArea.contains(Players.getLocal()) 
				&& !Inventory.contains(Main.getTask().getTertiary()) 
				|| !Inventory.contains(Main.getTask().getCharm())
				|| (Inventory.contains(shardId) && !(Inventory.getItem(shardId).getStackSize() > Main.getTask().getShardAmount()))
				|| !Summoning.isFamiliarSummoned()
				|| Summoning.getEnum() != Summoning.Familiar.SPIRIT_KYATT
				|| Summoning.getLeftClickOption() != Summoning.Option.INTERACT;
	}
	
	@Override
	public void execute() {
		//Withdraws and summons Kyatt Pouch if not summoned already
		if(!Summoning.isFamiliarSummoned() 
				|| Summoning.getEnum() != Summoning.Familiar.SPIRIT_KYATT){ 
			Summoning.dismissFamiliar();
			if(!Bank.isOpen() && !Inventory.contains(kyattId)){
				Bank.getNearest();
				Bank.open();
				Task.sleep(600,250);
			}else{
				Bank.withdraw(kyattId, 1);
				if(Bank.isOpen()){
					Bank.close();
					Task.sleep(200,200);
				}else{
					Summoning.summonFamiliar(Summoning.Familiar.SPIRIT_KYATT);
					Task.sleep(200,100);
				}
			}
			Summoning.setLeftClickOption(Summoning.Option.INTERACT);Task.sleep(200,100);
		}
		
		if(Summoning.isFamiliarSummoned()){
			if(!Bank.isOpen()){
				Bank.getNearest();
				Bank.open();
				Task.sleep(600,250);		
			}else{
				//Deposit Pouches in Inventory
				if(Inventory.contains(Main.getTask().getPouchId())){
					Bank.deposit(Main.getTask().getPouchId(), 25);
				}
				/*
				 * NOTE: The if statements below are quite messy. Is there a cleaner/better way of doing such?
				 */
				//Banks Any Un-needed Gold Charms
				if(Inventory.contains(Charm.GOLD.id()) && Charm.GOLD.id() != Main.getTask().getCharm()){
					Bank.deposit(Charm.GOLD.id(), Inventory.getItem(Charm.GOLD.id()).getStackSize());
				}
				//Banks Any Un-needed Green Charms
				if(Inventory.contains(Charm.GREEN.id()) && Charm.GREEN.id() != Main.getTask().getCharm()){
					Bank.deposit(Charm.GREEN.id(), Inventory.getItem(Charm.GREEN.id()).getStackSize());
				}
				//Banks Any Un-needed Crimson Charms
				if(Inventory.contains(Charm.CRIMSON.id()) && Charm.CRIMSON.id() != Main.getTask().getCharm()){
					Bank.deposit(Charm.CRIMSON.id(), Inventory.getItem(Charm.CRIMSON.id()).getStackSize());
				}
				//Banks Any Un-needed Blue Charms
				if(Inventory.contains(Charm.BLUE.id()) && Charm.BLUE.id() != Main.getTask().getCharm()){
					Bank.deposit(Charm.BLUE.id(), Inventory.getItem(Charm.BLUE.id()).getStackSize());
				}
				//Withdraws Charms for Task (Checks that Inventory contains enough Charms for the current task, 
				//if false it withdraws enough charms from Bank if they are present)
				if(Bank.getItem(Main.getTask().getCharm()).getStackSize() > 0){
					if(Inventory.contains(Main.getTask().getCharm())){
						if(Inventory.getItem(Main.getTask().getCharm()).getStackSize() != Main.getTask().getAmount()){
							Bank.withdraw(Main.getTask().getCharm(), Main.getTask().getAmount() - Inventory.getItem(Main.getTask().getCharm()).getStackSize());
						}
					}else{
						Bank.withdraw(Main.getTask().getCharm(), Main.getTask().getAmount());
					}
				}
				//Withdraws Pouches for Task
				if(Bank.getItem(pouchId).getStackSize() > 0){
					if(Inventory.contains(pouchId)){
						Bank.withdraw(pouchId,  Main.getTask().getAmount() - Inventory.getItem(pouchId).getStackSize());
					}else{
						Bank.withdraw(pouchId, Main.getTask().getAmount());
					}
				}
				//Withdraws any shards left in Bank
				if(Bank.getItem(shardId).getStackSize() > 0){
					Bank.withdraw(shardId, Bank.getItem(shardId).getStackSize());
				}
				//Withdraws Tertiary for Task
				if(Inventory.getCount(Main.getTask().getTertiary()) < 1 && Bank.getItem(Main.getTask().getTertiary()).getStackSize() > 0){
					Bank.withdraw(Main.getTask().getTertiary(), 25);
				}
				//Closes Bank
				if(Bank.isOpen()){
					Bank.close();
					Task.sleep(200,200);
				}
			}	
		}	
	}
}
