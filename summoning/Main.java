package summoning;

import java.util.ArrayList;
import java.util.List;

import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;

@Manifest(authors = {"azurepanda"}, 
name = "AIO Summoning", 
version = 1.0,
description = "")

public class Main extends ActiveScript{
	
	private static ArrayList<Task> tasks = new ArrayList<Task>();
	private Tree container = null;
	private List<Node> jobs = new ArrayList<Node>();
	
	public void onStart(){
		tasks.add(new Task(Pouch.SPIRIT_WOLF, 100));
		tasks.add(new Task(Pouch.DREADFOWL, 50));
		tasks.trimToSize();
		jobs.add(new Banking());
		container = new Tree(jobs.toArray((new Node[jobs.size()])));
	}
	
	@Override
	public int loop() {
		tasks.add(new Task(Pouch.SPIRIT_WOLF, 100));
		if(tasks.get(0) != null){
			//do code
		}
		if(tasks.get(0).getAmount() == 0){
			tasks.remove(0);
			tasks.trimToSize();
		}
		if(container != null){
			final Node job = container.state();
			if(job != null){
				container.set(job);
				getContainer().submit(job);
				job.join();
			}
		}
		return 50;
	}
	
	public static Task getTask() { return tasks.get(0); }
}