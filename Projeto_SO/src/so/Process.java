package so;

import java.util.LinkedList;
import java.util.List;

public class Process {
	
	private String id;
	private int sizeInMemory;
	private List<String> processes;
	private int timeToExecute;
	private int priority;
	private int instructions;
	private int instructionExecuted;
	
	public static int count;

	public Process(int sizeMemory, int timeToExecute, int prioriry) {
		count++;
		this.timeToExecute = timeToExecute;
		this.priority = prioriry;
		this.id = "P" + count;
		this.sizeInMemory = sizeMemory;
		this.processes = this.getProcesses();
		this.instructions = this.getProcesses().size() * 7;
	}

	public List<String> getProcesses() {
		if (this.processes == null) {
			this.processes = new LinkedList<>();
			for (int i = 1; i <= this.sizeInMemory; i++) {
				String spId = this.getId() + "-" + i;
				this.processes.add(spId);
			}
		}
		return this.processes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSizeInMemory() {
		return sizeInMemory;
	}

	public void setSizeInMemory(int sizeInMemory) {
		this.sizeInMemory = sizeInMemory;
	}

	public int getTimeToExecute() {
		return timeToExecute;
	}

	public void setTimeToExecute(int timeToExecute) {
		this.timeToExecute = timeToExecute;
	}

	public int getPriorityType() {
		return priority;
	}

	public void setPriorityType(int priority) {
		this.priority = priority;
	}
	
	public void updateInstructionExecuted() {
		this.instructionExecuted += 7;
	}
	
	public void checkInstructions() {
		if (this.instructions == this.instructionExecuted) {
			System.out.println("\n**********");
			System.out.println(this.getId() + "- SUCESS!");
			System.out.println("**********\n");
		}
	}

}
