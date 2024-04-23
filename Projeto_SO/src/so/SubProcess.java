package so;

public class SubProcess {
	private String id;
	private int instructions;
	private Process process;

	public SubProcess(String id, Process process) {
		this.instructions = 7;
		this.id = id;
		this.process = process;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getInstructions() {
		return instructions;
	}

	public void setInstructions(int instructions) {
		this.instructions = instructions;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}
}
