package so.cpu;

import so.SubProcess;

public class Core implements Runnable {

	private int id;
	private SubProcess currentSubProcess;
	private int numOfInstructionsPerClock;
	private int count;

	public Core(int id, int numOfInstructionsPerClock) {
		this.id = id;
		this.numOfInstructionsPerClock = numOfInstructionsPerClock;
	}

	@Override
	public void run() {
		count += numOfInstructionsPerClock;
		if (count >= this.currentSubProcess.getInstructions()) {
			if (this.currentSubProcess != null) {
				System.out.println(this.currentSubProcess.getId());
				this.currentSubProcess.getProcess().updateInstructionExecuted();
			}
			this.finishExecutions();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private void finishExecutions() {
		this.count = 0;
		this.currentSubProcess.getProcess().checkInstructions();
		this.currentSubProcess = null;
	}

	public SubProcess getCurrentSubProcess() {
		return currentSubProcess;
	}

	public void setCurrentSubProcess(SubProcess currentSubProcess) {
		this.currentSubProcess = currentSubProcess;
	}
}
