package summoning;

public class Task {
	private Pouch pouch;
	private int amount;
	
	public Task(Pouch pouch, int amount){
		this.pouch = pouch;
		this.amount = amount;
	}
	
	public int getAmount(){
		return amount; 
	}
	
	public int getTertiary(){
		return pouch.tertiaryId();
	}
	
	public int getCharm(){
		return pouch.charmId();
	}
	
	public int getWidgetId(){
		return pouch.widgetId();
	}
	
	public int getShardAmount(){
		return pouch.shardAmount();
	}
	
	public int getPouchId(){
		return pouch.id();
	}
	
	public void minusAmount(int n){
		amount -= n;
	}
}
