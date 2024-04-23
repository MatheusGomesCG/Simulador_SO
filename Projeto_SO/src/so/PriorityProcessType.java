package so;

public enum PriorityProcessType {
	BAIXA(-1),
	MEDIA(0),
	ALTA(1),
	CRITICA(100);

	private int level;

	PriorityProcessType(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}
	
}
