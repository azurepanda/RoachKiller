package summoning;

public enum Pouch {
	
	/*
	 * Gold Charm Pouches in Level Order
	 */
	SPIRIT_WOLF (2859, Charm.GOLD.id(), 0, 7, 12047),
	DREADFOWL (2859, Charm.GOLD.id(), 4, 7, 12047),
	SPIRIT_SPIDER (2859, Charm.GOLD.id(), 8, 7, 12047),
	THORNY_SNAIL (2859,Charm.GOLD.id(), 12, 7, 12047),
	GRANITE_CRAB (2859, Charm.GOLD.id(), 16, 7, 12047),
	SPIRIT_MOSQUITO (2859, Charm.GOLD.id(), 20, 7, 12047),
	
	/*
	 * Green Charm Pouches in Level Order
	 */
	DESERT_WRYM (2859, Charm.GREEN.id(), 0, 7, 12047),
	
	/*
	 * Crimson Charm Pouches in Level Order
	 */
	SPIRIT_SCORPION (2859, Charm.CRIMSON.id(), 0, 7, 12047),
	SPIRIT_TZ_KIH (2859, Charm.CRIMSON.id(), 4, 7, 12047),
	 
	/*
	 * Blue Charm Pouches in Level Order
	 */
	ALBINO_RAT (2859, Charm.BLUE.id(), 0, 7, 12047),
	SPIRIT_KALHPITE (2859, Charm.BLUE.id(), 4, 7, 12047);
	
	private final int tertiaryId;
	private final int charmId;
	private final int widgetId;
	private final int shardAmount;
	private final int pouchId;
	
	Pouch(int tertiaryId, int charmId, int widgetId, int shardAmount, int pouchId){
		this.tertiaryId = tertiaryId;
		this.charmId = charmId;
		this.widgetId = widgetId;
		this.shardAmount = shardAmount;
		this.pouchId = pouchId;
	}
	
	public int tertiaryId() { return tertiaryId; }
	public int charmId() { return charmId; }
	public int widgetId() { return widgetId; }
	public int shardAmount() { return shardAmount; }
	public int id() { return pouchId; }
}

//1371, 50, 0 - current charm page
//387, 33 - ring slot Equipment tab
//28705 scene object obelisk
//28741 scene object trapdoor
//2738 bank chest