package so.schedule;

import java.util.Comparator;
import java.util.List;

import so.Process;
import so.SubProcess;
import so.SystemCallType;
import so.SystemOperation;

public class SJF extends SchedulerQueue {

	public SJF() {
		super(new Comparator<Process>() {

			@Override
			public int compare(Process sp1, Process sp2) {
				return sp1.getTimeToExecute() - sp2.getTimeToExecute();
			}
		});
	}

	public void addProcessAndSubProcess(Process p) {
		if (this.queue != null) {
			this.queue.add(p);
		}
	}

	@Override
	public void finish(Process p) {
		// TODO Auto-generated method stub
	}

	@Override
	public SubProcess execute() {
		this.orderListByTimeExecution();
		if (this.subProcesses != null) {
			return this.subProcesses.poll();
		}
		return null;
	}
	
	private void orderListByTimeExecution() {
		if (this.queue != null) {
			Process p = this.queue.poll();
			if (p != null) {
				List<SubProcess> subprocesses = SystemOperation.systemCall(SystemCallType.READ_PROCESS, p);
				for (SubProcess subProcess : subprocesses) {
					this.subProcesses.add(subProcess);
				}
			}
		}
	}
}
