package so.cpu;

import java.util.Timer;
import java.util.TimerTask;

import so.SubProcess;
import so.schedule.Schedule;

public class CpuManager {
	private Core[] cores;
	private Schedule scheduler;

	public static int INSTRUTIONS = 7;
	public static int NUM_OF_CORES = 4;
	public static int CLOCK = 500;

	public CpuManager(Schedule schedule) {
		this.cores = new Core[NUM_OF_CORES];
		for (int i = 0; i < this.cores.length; i++) {
			this.cores[i] = new Core(i, INSTRUTIONS);
		}
		this.scheduler = schedule;
		clock();
	}

	public void clock() {
		new Timer().scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				executeProcesses();

			}
		}, 0, CLOCK);
	}

	private void executeProcesses() {
		if (this.cores != null) {
			for (Core core : this.cores) {
				if (core.getCurrentSubProcess() == null) {
					if (this.scheduler != null) {
						SubProcess sp = this.scheduler.execute();
						if (sp != null) {
							core.setCurrentSubProcess(sp);
							core.run();
						}
					}
				}
				
			}
			System.out.println("**********");
		}
	}

}
