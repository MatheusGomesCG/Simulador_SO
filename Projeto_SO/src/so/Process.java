package so;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import so.memory.AddressMemory;

public class Process {
	
	private String id;
	private int sizeInMemory;
	//private int timeToExecute;
	//private Priority priority;
	private AddressMemory adressInMemory;
	
	public Process() {
		Random rand = new Random();
		this.id = UUID.randomUUID().toString();
		List<Integer> givenList = Arrays.asList(5, 10, 25, 50, 100);
		this.sizeInMemory = givenList.get(rand.nextInt(givenList.size()));
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSizeInMemory() {
		return sizeInMemory;
	}
	public void setSizeInMemory(int sizeInMemory) {
		this.sizeInMemory = sizeInMemory;
	}
	/*public int getTimeToExecute() {
		return timeToExecute;
	}
	public void setTimeToExecute(int timeToExecute) {
		this.timeToExecute = timeToExecute;
	}
	
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	*/

	public AddressMemory getAdressInMemory() {
		return adressInMemory;
	}

	public void setAdressInMemory(AddressMemory adressInMemory) {
		this.adressInMemory = adressInMemory;
	}
	
	
}
