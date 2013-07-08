package summoning;

public enum Charm {
	
	/*
	 * Charm Ids
	 */
	GOLD (12158),
	GREEN (12159),
	CRIMSON (12160),
	BLUE (12163);
	
	private final int id;
	Charm(int id){
		this.id = id;
	}
	public int id() { return id; }
}
