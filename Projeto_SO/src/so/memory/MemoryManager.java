package so.memory;

import java.util.Hashtable;
import java.util.List;

import so.Process;

public class MemoryManager {

	private String[] physicMemory;
	private Hashtable<String, List<FrameMemory>> logicalMemory;
	private Strategy strategy;
	private int[][] emptySpaces;

	public MemoryManager(Strategy strategy) {
		this.strategy = strategy;
		this.physicMemory = new String[128];
		this.emptySpaces = new int[physicMemory.length][2];
	}

	public int sizePhysicMemoryEmpty() {
		int emptySpace = 0;
		for (int i = 0; i < physicMemory.length; i++) {
			if (physicMemory[i] == null) {
				emptySpace++;
			}
		}
		return emptySpace;
	}

	public void spaceInPhysicMemory() {
		int actualSize = 0;
		int count = 0;
		for (int i = 0; i < physicMemory.length; i++) {
			if (i == (physicMemory.length - 1)) {
				if (actualSize > 0) {
					if (physicMemory[i] == null) {
						actualSize++;
						emptySpaces[count][0] = actualSize;
						emptySpaces[count][1] = i;
						break;
					}
				}
			} else if (physicMemory[i] == null) {
				actualSize++;
			} else {
				if (actualSize > 0) {
					emptySpaces[count][0] = actualSize;
					emptySpaces[count][1] = i;
					actualSize = 0;
					count++;
				}
			}
		}
	}

	public void writeProcess(so.Process p) {
		if (this.strategy.equals(Strategy.FIRIST_FIT)) {
			this.writeUsingFiristFit(p);
		} else if (this.strategy.equals(Strategy.BEST_FIT)) {
			this.writeUsingBestFit(p);
		} else if (this.strategy.equals(Strategy.WORST_FIT)) {
			this.writeUsingWorstFit(p);
		} else if (this.strategy.equals(Strategy.PAGING)) {
			this.writeUsingPaging(p);
		} else {
			System.out.println("INCORRECT STRATEGY");
		}

	}

	private void writeUsingPaging(Process p) {

	}

	private int[][] biggerSpacePhysicMemory() {
		int[][] bigSpace = new int[1][2];
		spaceInPhysicMemory();
		for (int i = 0; i < physicMemory.length; i++) {
			if (emptySpaces[i][0] > bigSpace[0][0]) {
				bigSpace[0][0] = emptySpaces[i][0];
				bigSpace[0][1] = emptySpaces[i][1];
			}
		}
		return bigSpace;
	}

	private void writeUsingWorstFit(Process p) {
		int[][] bigSpace = biggerSpacePhysicMemory();
		int sizeArray = sizePhysicMemoryEmpty();
		if (p.getSizeInMemory() <= sizeArray) {
			if (p.getSizeInMemory() <= bigSpace[0][0]) {
				int start = 0;
				if (bigSpace[0][1] < 127) {
					start = bigSpace[0][1] - bigSpace[0][0];
				} else {
					start = (bigSpace[0][1] + 1) - bigSpace[0][0];
				}
				int count = 0;
				do {
					physicMemory[(start + count)] = p.getId();
					count++;
				} while (count < p.getSizeInMemory());
				printStatusMemory();
			} else {
				memoryFull(p);
			}
		} else {
			memoryFull(p);
		}

	}

	private void writeUsingBestFit(Process p) {
		spaceInPhysicMemory();
		int lowerNumber = Integer.MAX_VALUE;
		int position = 0;
		int[][] bigSpace = biggerSpacePhysicMemory();
		if (p.getSizeInMemory() <= bigSpace[0][0]) {
			for (int i = 0; i < physicMemory.length; i++) {
				if (emptySpaces[i][0] > p.getSizeInMemory()) {
					int differentNumber = Math.abs(emptySpaces[i][0] - p.getSizeInMemory());
					if (differentNumber < lowerNumber) {
						lowerNumber = emptySpaces[i][0];
						position = emptySpaces[i][1];
					}

				}
			}
			int start = 0;
			if (position < 127) {
				start = position - lowerNumber;
			} else {
				start = (position + 1) - lowerNumber;
			}
			int count = 0;
			do {
				physicMemory[(start + count)] = p.getId();
				count++;
			} while (count < p.getSizeInMemory());
			printStatusMemory();
		} else {
			memoryFull(p);
		}
	}

	private void writeUsingFiristFit(Process p) {
		int[][] bigSpace = biggerSpacePhysicMemory();
		spaceInPhysicMemory();
		if (p.getSizeInMemory() <= bigSpace[0][0]) {
			for (int i = 0; i < physicMemory.length; i++) {
				if (p.getSizeInMemory() <= emptySpaces[i][0]) {
					int start = 0;
					if (emptySpaces[i][1] < 127) {
						start = Math.abs((emptySpaces[i][0]) - emptySpaces[i][1]);
					} else {
						start = Math.abs((emptySpaces[i][0] - 1) - emptySpaces[i][1]);
					}
					int count = 0;
					do {
						physicMemory[(start + count)] = p.getId();
						count++;
					} while (count < p.getSizeInMemory());
				} 
			}
			printStatusMemory();
		} else {
			memoryFull(p);
		}

	}

	private void printStatusMemory() {
		for (int i = 0; i < physicMemory.length; i++) {
			System.out.print(physicMemory[i] + " | ");
		}
		System.out.println();
	}

	public void deleteProcess(so.Process p) {
		for (int i = 0; i < physicMemory.length; i++) {
			if (p.getId() == physicMemory[i]) {
				physicMemory[i] = null;
			}
		}
		printStatusMemory();
	}

	public void memoryFull(so.Process p) {
		System.out.printf("THE PROCESS (%s) DID NOT FOUND, THE MEMORY IS NO SPACE!%n", p.getId());
	}
}
