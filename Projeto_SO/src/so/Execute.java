package so;

public class Execute {
	public static void main(String[] args) {
		
		System.out.println("PROCESS P1");
		Process p1 = SystemOperation.SystemCall(SystemCallType.CREATE_PROCESS, 130, 10, -1);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p1);
		
		System.out.println("\nPROCESS P2");
		
		Process p2 = SystemOperation.SystemCall(SystemCallType.CREATE_PROCESS, 90, 5, 0);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p2);
		
		System.out.println("\nPROCESS P3");
		
		Process p3 = SystemOperation.SystemCall(SystemCallType.CREATE_PROCESS, 30, 15, 100);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p3);
		
	}
}
