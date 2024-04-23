package so.schedule;

import so.cpu.CpuManager;
import so.Process;
import so.SubProcess;

public abstract class Schedule {
	public CpuManager cm;

	public Schedule() {
		cm = new CpuManager(this);
	}

	public abstract void addProcessAndSubProcess(Process p);
	
	public abstract void finish(Process p);

	public CpuManager getCm() {
		return cm;
	}

	public abstract SubProcess execute();
}
