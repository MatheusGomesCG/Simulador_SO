package so.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import so.Process;
import so.SubProcess;

public class MemoryManager {
	public List<SubProcess[]> physicMemory;
	private Map<String, FrameMemory> logicMemory;

	public static final int PAGE_SIZE = 4;
	public static final int MEMORY_SIZE = 256;

	public MemoryManager() {
		int quantityPages = MEMORY_SIZE / PAGE_SIZE;
		physicMemory = new ArrayList<>(quantityPages);
		for (int frame = 0; frame < quantityPages; frame++) {
			physicMemory.add(new SubProcess[PAGE_SIZE]);
		}
		logicMemory = new HashMap<>();
	}

	public List<SubProcess> read(Process process) {
		List<SubProcess> subProcesses = new ArrayList<>();
		for (String subProcessIdSelected : process.getProcesses()) {
			FrameMemory addressSubProcess = logicMemory.get(subProcessIdSelected);
			if (addressSubProcess != null && physicMemory.get(addressSubProcess.getPageNumber())[addressSubProcess
					.getDisplacement()] != null) {
				subProcesses.add(physicMemory.get(addressSubProcess.getPageNumber())[addressSubProcess.getDisplacement()]);
			}
		}
		return subProcesses;
	}

	public void write(Process process) {
		allocateProcessWithPaging(process);
	}

	public boolean checkWrite(Process process) {
		List<Integer> emptyFrames = findEmptyPages();
		return emptyFrames.size() >= (double) process.getSizeInMemory() / PAGE_SIZE;
	}

	private List<Integer> findEmptyPages() {
		List<Integer> framesIndex = new ArrayList<>();
		for (int frame = 0; frame < physicMemory.size(); frame++) {
			SubProcess[] element = physicMemory.get(frame);
			if (element[0] == null) {
				framesIndex.add(frame);
			}
		}
		return framesIndex;
	}

	private void allocateProcessWithPaging(Process process) {
		List<Integer> emptyFrames = findEmptyPages();
		int countSize = 0;
		for (Integer indexFrame : emptyFrames) {
			SubProcess[] page = physicMemory.get(indexFrame);
			int indexPage = 0;
			while (indexPage < page.length && countSize < process.getSizeInMemory()) {
				String subProcessId = process.getProcesses().get(countSize);
				physicMemory.get(indexFrame)[indexPage] = new SubProcess(subProcessId, process);
				logicMemory.put(subProcessId, new FrameMemory(indexFrame, indexPage));
				countSize++;
				indexPage++;
			}
		}
		printMemory();
	}

	public Process delete(Process process) {
		List<String> subProcess = process.getProcesses();
		for (SubProcess[] page : physicMemory) {
			if (page[0] != null && page[0].getProcess().getId().equals(process.getId())) {
				for (int i = 0; i < page.length; i++) {
					page[i] = null;
				}
			}
		}
		for (String sb : subProcess) {
			logicMemory.remove(sb);
		}
		printMemory();
		return process;
	}

	private void printMemory() {
		System.out.println("----------");
		for (SubProcess[] page : physicMemory) {
			for (SubProcess subProcess : page) {
				System.out.print("[PROCESS:"+ (subProcess != null ? subProcess.getId() : null) +"]");
			}
			System.out.println();
		}
	}
}