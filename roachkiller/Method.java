package roachkiller;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Equipment;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Locatable;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.bot.Context;

public class Method {

	public static boolean itemsReady() {
		return !outOfFood() && !outOfRunes();
	}

	public static boolean outOfFood() {
		return Inventory.getCount(Variable.food) < 1;
	}

	public static boolean outOfAmmo() {
		String ammoMessage = Variable.AMMOCHECK.getText();
		if(ammoMessage.equals("You have no ammo equipped.")){
			for(int x=0 ; x!=5; x++){
				if(!Equipment.containsOneOf(Variable.ARROWS[x])){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean outOfRunes() {
		if(Variable.mage==true){
			for(int x = 0; x < Variable.currentSpell.length; x++){
				if(Inventory.getItem(Variable.currentSpell[x]) == null && Variable.currentSpell[x]!=0){
					return true;
				}
				if(Variable.currentSpell[x]!=0){
					if(Inventory.getItem(Variable.currentSpell[x]).getStackSize() < 10){
						return true;
					}	
				}
			}
		}
		return false;
	}
	
	public static void staffEquipped() {
		for(int x = 0; x < (Variable.STAFFS.length); x++){
			boolean y = Equipment.containsOneOf(Variable.STAFFS[x]);
			if(y == true){
				int staffEquipped = Variable.STAFFS[x];
				x=100;
				switch(staffEquipped){
					case Variable.AIRSTAFF://if staff equipped removes that type of rune from rune withdraw list
						Variable.currentSpell[2] = 0;
						break;
					case Variable.WATERSTAFF:
						Variable.currentSpell[1] = 0;
						break;
					case Variable.EARTHSTAFF:
						Variable.currentSpell[3] = 0;
						break;
					case Variable.FIRESTAFF:
						Variable.currentSpell[0] = 0;
						break;
				}
			}
		}
	}
	
	public static Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}		
	
	//Credit to Manner for the middle mouse camera movements
	@SuppressWarnings("deprecation")
	public static boolean dragMouse(int x1, int y1, int x2, int y2) {
		final org.powerbot.game.client.input.Mouse MOUSE = Context.client()
				.getMouse();
		final Component TARGET = Context.get().getLoader().getComponent(0);
		Mouse.move(x1, y1);
		MOUSE.sendEvent(new MouseEvent(TARGET, MouseEvent.MOUSE_PRESSED, System
				.currentTimeMillis(), 0, Mouse.getX(), Mouse.getY(), 1, false,
				MouseEvent.BUTTON2));
		Mouse.move(x2, y2);
		MOUSE.sendEvent(new MouseEvent(TARGET, MouseEvent.MOUSE_RELEASED,
				System.currentTimeMillis(), 0, Mouse.getX(), Mouse.getY(), 1,
				false, MouseEvent.BUTTON2));
		return Mouse.getX() == x2 && Mouse.getY() == y2 && !Mouse.isPressed();
	}

	public static boolean turnTo(Locatable locatable, int degreesDeviation) {
		final double DEGREES_PER_PIXEL_X = 0.35;
		int degrees = Camera.getMobileAngle(locatable) % 360;
		int angleTo = Camera.getAngleTo(degrees);
		while (Math.abs(angleTo) > degreesDeviation) {
			angleTo = Camera.getAngleTo(degrees);
			int pixelsTo = (int) Math.abs(angleTo / DEGREES_PER_PIXEL_X)
					+ Random.nextInt(
							-(int) (degreesDeviation / DEGREES_PER_PIXEL_X) + 1,
							(int) (degreesDeviation / DEGREES_PER_PIXEL_X) - 1);
			if (pixelsTo > 450)
				pixelsTo = pixelsTo / 450 * 450;
			int startY = Mouse.getY() < 255 && Mouse.getY() > 55 ? Random
					.nextInt(-25, 25) + Mouse.getY() : Random.nextInt(70, 240);
			if (angleTo > degreesDeviation) {//right
				int startX = (500 - pixelsTo)
						- Random.nextInt(0, 500 - pixelsTo - 10);
				dragMouse(startX, startY, startX + pixelsTo,
						startY + Random.nextInt(90, 121));
			} else if (angleTo < -degreesDeviation) {// left
				int startX = (pixelsTo + 10)
						+ Random.nextInt(0, 500 - pixelsTo + 10);
				dragMouse(startX, startY, startX - pixelsTo,
						startY + Random.nextInt(90, 121));
			}
		}
		return Math.abs(Camera.getAngleTo(degrees)) <= degreesDeviation;
	}

	public static String componentTime(long seconds) {
		int hours = (int) seconds / 3600;
		int remainder = (int) (seconds - hours * 3600);
		int mins = remainder / 60;
		remainder = remainder - mins * 60;
		int secs = remainder;

		String compstring = String.valueOf(hours) + " h "
				+ String.valueOf(mins) + " m " + String.valueOf(secs) + " s ";
		return compstring;
	}
	//credit to Coma for GetPrices method
	public static Map<Integer, Integer> getPrices(final int... ids) {
	    Map<Integer, Integer> map = new HashMap<>();
	    String add = "http://scriptwith.us/api/?return=text&item=";
	    for (int i = 0; i < ids.length; i++) {
	     add += ((i == ids.length - 1) ? ids[i] : ids[i] + ",");
	    }
	    try (final BufferedReader in = new BufferedReader(
	      new InputStreamReader(new URL(add).openConnection()
	        .getInputStream()))) {
	     final String line = in.readLine();
	     for (String s : line.split("[;]")) {
	      map.put(Integer.parseInt(s.split("[:]")[0]),
	        Integer.parseInt(s.split("[:]")[1]));
	     }
	    } catch (Exception e) {
	     e.printStackTrace();
	    }
	    return map;
	  }
	
	
	public static void useAbilities() {

		
		
/*		if (Variable.OPENAB.visible()) {
			Variable.OPENAB.click(true);
		}

		if (Players.getLocal().isInCombat() == true) {
			if (Variable.ENTERPRESS.getText() != "[Press Enter to Chat]") {
				if (Variable.CLOSEAB.visible()) {
					Keyboard.sendKey((char) KeyEvent.VK_ENTER);
				} else {
					Variable.OPENAB.click(true);
					Task.sleep(500, 20);
					Keyboard.sendKey((char) KeyEvent.VK_ENTER);
				}
			}
		}
		
		if (!(Players.getLocal().getInteracting() == null)) {
			if (Players.getLocal().getAdrenalinePercent() == 100) {
				Variable.status = "Using Ability 6";
				Keyboard.sendKey('6');
				Task.sleep(1500, 400);
			}
		}

		if (!(Players.getLocal().getInteracting() == null)) {
			Variable.status = "Using Ability 1";
			Keyboard.sendKey('1');
			Task.sleep(1500, 400);
		}
		if (!(Players.getLocal().getInteracting() == null)) {
			Variable.status = "Using Ability 2";
			Keyboard.sendKey('2');
			Task.sleep(1500, 400);
		}ActionBar
		if (!(Players.getLocal().getInteracting() == null)) {
			Variable.status = "Using Ability 3";
			Keyboard.sendKey('3');
			Task.sleep(1500, 400);
		}
		if (!(Players.getLocal().getInteracting() == null)) {
			Variable.status = "Using Ability 4";
			Keyboard.sendKey('4');
			Task.sleep(1500, 400);
		}
		if (!(Players.getLocal().getInteracting() == null)) {
			Variable.status = "Using Ability 5";
			Keyboard.sendKey('5');
			Task.sleep(1500, 400);
		}

		if (!(Players.getLocal().getInteracting() == null)) {
			if (Players.getLocal().getAdrenalinePercent() == 100) {
				Variable.status = "Using Ability 6";
				Keyboard.sendKey('6');
				Task.sleep(1500, 400);
			}
		}*/
	}

	public static Tile walkToArea(Area a, Tile t) {
		if (t == null) {
			t = Method.getRandomTileInArea(a);
		}
		if (!Players.getLocal().isMoving()
				|| ((Calculations.distance(Walking.getDestination(), Players
						.getLocal().getLocation()) < (1 + Math.random() * 9)) && (Calculations
						.distance(t, Players.getLocal().getLocation()) > 10))) {
			t = Method.getRandomTileInArea(a);
			Walking.walk(t);
		}
		return t;
	}

	public static Tile getRandomTileInArea(Area a) {
		return a.getTileArray()[Random.nextInt(0, a.getTileArray().length)];

	}
}
