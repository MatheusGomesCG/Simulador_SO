package so.schedule;

import java.util.LinkedList;
import java.util.List;

import so.Process;
import so.SubProcess;
import so.SystemCallType;
import so.SystemOperation;

public class Lottery extends Schedule {
	protected LinkedList<Process> queueProcess;
	protected LinkedList<SubProcess> subProcesses;
	
	public Lottery() {
		this.queueProcess = new LinkedList<Process>();
		this.subProcesses = new LinkedList<SubProcess>();
	}

	@Override
	public void addProcessAndSubProcess(Process p) {
		this.queueProcess.add(p);
	}

	@Override
	public void finish(Process p) {
		// TODO Auto-generated method stub
	}

	@Override
	public SubProcess execute() {
		this.randomProcess();
		if (this.subProcesses != null) {
			SubProcess sp = this.subProcesses.poll();
			return sp;
		}
		return null;
	}
	
	public void randomProcess() {
		if (this.queueProcess != null) {
			int index = (int) (Math.random() * this.queueProcess.size());
			if (!this.queueProcess.isEmpty()) {
				Process process = this.queueProcess.get(index);
				if (process != null) {
					List<SubProcess> subProcesses = SystemOperation.systemCall(SystemCallType.READ_PROCESS, process);
					for (SubProcess subProcess : subProcesses) {
						this.subProcesses.add(subProcess);
					}
					this.queueProcess.removeIf((p) -> p.getId() == process.getId());
				}
			}
		}
	}
}
