package so.memory;

import so.Process;

public class MemoryManager {

	private String[] physicMemory;
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

	private void writeUsingWorstFit(Process p) {

	}

	private void writeUsingBestFit(Process p) {
		spaceInPhysicMemory();
		boolean condition = false;
		int lowerNumber = Integer.MAX_VALUE;
		int position = 0;
		int sizeArray = sizePhysicMemoryEmpty();
		if (p.getSizeInMemory() <= sizeArray) {
			for (int i = 0; i < physicMemory.length; i++) {
				if (i == (physicMemory.length - 1)) {
					if (emptySpaces.length > 0) {
						for (int j = 0; j < physicMemory.length; j++) {
							if (emptySpaces[j][0] >= p.getSizeInMemory()) {
								int start = (emptySpaces[j][1]+1) - emptySpaces[j][0];
								int count = 0;
								do {
									physicMemory[(start + count)] = p.getId();
									count++;
								} while (count < p.getSizeInMemory());
								condition = false;
								break;
							}
						}
						break;
					}
				}
				if (p.getSizeInMemory() == emptySpaces[i][0]) {
					int start = emptySpaces[i][1] - emptySpaces[i][0];
					int count = 0;
					do {
						physicMemory[(start + count)] = p.getId();
						count++;
					} while (count < p.getSizeInMemory());
					condition = false;
					break;
				} else {
					condition = true;
				}
			}
			if (condition) {
				for (int i = 0; i < physicMemory.length; i++) {
					if (emptySpaces[i][0] > p.getSizeInMemory()) {
						int differentNumber	= Math.abs(emptySpaces[i][0] - p.getSizeInMemory());
						if (differentNumber < lowerNumber) {
							lowerNumber = emptySpaces[i][0];
							position = emptySpaces[i][1];
						}
						
					} 
				}
				int start = position - lowerNumber;
				int count = 0;
				do {
					physicMemory[(start + count)] = p.getId();
					count++;
				} while (count < p.getSizeInMemory());
				condition = false;
			} 
			printStatusMemory();
		} else {
			memoryFull(p);
		}
	}

	private void writeUsingFiristFit(Process p) {
		int actualSize = 0;
		if (p.getSizeInMemory() <= sizePhysicMemoryEmpty()) {
			for (int i = 0; i < physicMemory.length; i++) {
				if (i == (physicMemory.length - 1)) {
					if (actualSize > 0) {
						if (p.getSizeInMemory() <= actualSize) {
							int start = i - actualSize;
							int count = 0;
							do {
								physicMemory[(start + count)] = p.getId();
								count++;
							} while (count < p.getSizeInMemory());
							break;
						}
					}
				} else if (physicMemory[i] == null) {
					actualSize++;
				} else {
					if (actualSize > 0) {
						if (p.getSizeInMemory() <= actualSize) {
							int start = i - actualSize;
							int count = 0;
							do {
								physicMemory[(start + count)] = p.getId();
								count++;
							} while (count < p.getSizeInMemory());
							break;
						}
					}
					actualSize = 0;
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

	}

	public void memoryFull(so.Process p) {
		System.out.printf("THE PROCESS (%s) DID NOT FOUND, THE MEMORY IS NO SPACE!%n", p.getId());
	}
}
