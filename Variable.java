import java.awt.Font;
import java.awt.Image;
import java.util.Map;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.widget.WidgetChild;


public class Variable {

	public static int returns = 15;
	
	public static boolean GUIopen;
	
	public static String status = "";
	public static final String version = "0.1";
	
	public static long startTime;
	public static long runTime;
	public static String runString;
	
	public static final int startXpAttack = Skills.getExperience(Skills.ATTACK);
	public static final int startXpStrength = Skills.getExperience(Skills.STRENGTH);
	public static final int startXpDefense = Skills.getExperience(Skills.DEFENSE);
	public static final int startXpMage = Skills.getExperience(Skills.MAGIC);
	public static final int startXpRange = Skills.getExperience(Skills.RANGE);
	public static final int startXpHitpoints = Skills.getExperience(Skills.CONSTITUTION);
	public static final int startXpPrayer = Skills.getExperience(Skills.PRAYER);
	
	public static int gainedXpAttack;
	public static int gainedXpStrength;
	public static int gainedXpDefense;
	public static int gainedXpMage;
	public static int gainedXpRange;
	public static int gainedXpHitpoints;
	public static int gainedXpPrayer;
	
	public static int gainedExp;	
	public static String gainedExpString;
	
	public static int expPHour;
	public static String expPHourString;
	
	public static int totalGained = 0;
	public static String totalGainedString;
	public static int totalGainedPerHour;
	public static String totalGainedPerHourString;
	
	public static final int lobster = 379;
	public static final int trout = 333;
	public static final int salmon = 329;
	public static final int tuna = 361;
	public static final int swordfish = 373;
	
	public static boolean meele = false;
	public static boolean range = false;
	public static boolean mage = false;
	
	public static int foodAmount = 20;
	public static int food = lobster;
	
	public static final int roachId = 7160;
	
	public static final int[]arrows = {882, 884, 886, 888, 890, 892};
	public static final int[]lootTierHigh = {1185, 24154, 560, 563, 554, 447, 448, 449, 450, 1617, 1333};
	public static final int[]lootTierLow =  {1621, 1623, 1619, 24154, 1621, 1185, 560, 563, 554, 447, 448, 449, 450, 1617, 1333};
	public static int[] lootTier;
	public static int[] withdrawList = {556, 554, 555};
	public static int[] withdrawAmounts = {20, 25, 30};
	
	public static final int airStaff =  1381;
	public static final int waterStaff =  1383;
	public static final int earthStaff =  1385;
	public static final int fireStaff = 1387;
	public static final int[] staffs =  {1381, 1383, 1385, 1387};
	
	public static final int[] airSpell = { 0, 0, 556, 0}; //fire water air earth low -> high
	public static final int[] fireSpell = { 554, 0, 556, 0};
	public static final int[] waterSpell = { 0, 555, 556, 0};
	public static final int[] earthSpell = { 0, 0, 556, 557};
	public static final int[] nullSpell = { 0, 0, 0, 0};
	public static int[] currentSpell;
	
	public static Map<Integer, Integer> lootPrices;
	
	public static final Area roaches1 = new Area(new Tile(3161,4274,3), new Tile(3145,4282,3));//3.3
	public static final Area roaches2 = new Area(new Tile(3167,4274, 2), new Tile(3196,4227, 2));//2.2
	
	public static Area currentArea = roaches2;
	
	public static final Area stairsDown = new Area(new Tile(3167,4273,3), new Tile(3173,4268,3));//3.3
	public static final Area stairsUp = new Area(new Tile(3171,4269,2), new Tile(3177,4273,2));//2.2
	public static final Area ropeDown = new Area(new Tile(3074,3466,0), new Tile(3079,3461,0));
	public static final Area ropeUp = new Area(new Tile(3160,4277,3), new Tile(3156,4281,3));//3.3
	public static final Area Bank = new Area(new Tile(3091,3498,0), new Tile(3095,3489,0));

	public static final Area Bank1 = new Area(new Tile(3072,3474,0), new Tile(3077,3470,0));
	public static final Area Bank2 = new Area(new Tile(3081,3488,0), new Tile(3084,3483,0));
	
	public static final Tile StairsD = new Tile(3170, 4270, 3);
	public static final Tile StairsU = new Tile(3174, 4271, 2);
	public static final Tile RopeU = new Tile(3158, 4279, 3);

	public static final Tile[] TopFloorPath = new Tile[] { new Tile(3092, 3491, 0), new Tile(3088, 3490, 0), new Tile(3084, 3488, 0), new Tile(3078, 3487, 0), new Tile(3074, 3487, 0), 
														   new Tile(3074, 3482, 0), new Tile(3074, 3477, 0), new Tile(3074, 3472, 0), new Tile(3074, 3467, 0),
														   new Tile(3074, 3464, 0)};
	public static final Tile[] Floor2LookingPath = new Tile[] { new Tile(3177, 4259, 2), new Tile(3181, 4261, 2), new Tile(3186, 4260, 2), new Tile(3189, 4257, 2),
																new Tile(3189, 4252, 2), new Tile(3192, 4249, 2), new Tile(3193, 4244, 2), new Tile(3191, 4239, 2),
																new Tile(3187, 4237, 2), new Tile(3182, 4237, 2), new Tile(3177, 4239, 2), new Tile(3176, 4244, 2),
																new Tile(3176, 4249, 2), new Tile(3176, 4254, 2)};
	
	public static final WidgetChild close = Widgets.get(640, 30);
	public static final WidgetChild open = Widgets.get(640, 3);
	public static final WidgetChild EnterPress = Widgets.get(137, 56);
	public static final WidgetChild AmmoCheck = Widgets.get(137, 58).getChild(0);
	
	public static final Image background = Method.getImage("http://i.imgur.com/UYXHy6q.png");
	public static final Font font1 = new Font("Arial", 0, 13);
}
