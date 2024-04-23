package so.schedule;

import java.util.Comparator;

import so.Process;
import so.SubProcess;

public class FCFS extends SchedulerQueue {

	public FCFS() {
		super((Comparator<Process>) new Comparator<Process>() {
			@Override
			public int compare(Process process1, Process process2) {
				return -1;
			}
		});
	}

	@Override
	public void finish(Process p) {
		// TODO Auto-generated method stub
	}

	@Override
	public SubProcess execute() {
		if (this.queue != null) {
			this.queue.poll();
			if (this.subProcesses != null) {
				return this.subProcesses.poll();
			}
		}
		return null;
	}
}
