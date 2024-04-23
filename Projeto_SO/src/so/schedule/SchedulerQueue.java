package so.schedule;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import so.Process;

import so.SubProcess;
import so.SystemCallType;
import so.SystemOperation;

public abstract class SchedulerQueue extends Schedule {
	protected PriorityQueue<Process> queue;
	protected LinkedList<SubProcess> subProcesses;

	public SchedulerQueue(Comparator<Process> comparator) {
		this.queue = new PriorityQueue<>(comparator);
		this.subProcesses = new LinkedList<SubProcess>();
	}

	@Override
	public void addProcessAndSubProcess(Process p) {
		if (this.queue != null) {
			this.queue.add(p);
			List<SubProcess> subProcesses = SystemOperation.systemCall(SystemCallType.READ_PROCESS, p);
			if (this.subProcesses != null) {
				for (int i = 0; i < subProcesses.size(); i++) {
					this.subProcesses.add(subProcesses.get(i));
				}
			}
		}
	}
}
