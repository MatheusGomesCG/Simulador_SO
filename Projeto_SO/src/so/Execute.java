package so;

public class Execute {

	public static void main(String[] args) {

		Process p1 = OperacionalSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
		OperacionalSystem.systemCall(SystemCallType.WRITE_PROCESS, p1);

		Process p2 = OperacionalSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
		OperacionalSystem.systemCall(SystemCallType.WRITE_PROCESS, p2);
		
		Process p3 = OperacionalSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
		OperacionalSystem.systemCall(SystemCallType.WRITE_PROCESS, p3);
		
		Process p4 = OperacionalSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
		OperacionalSystem.systemCall(SystemCallType.WRITE_PROCESS, p4);
		
		Process p5 = OperacionalSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
		OperacionalSystem.systemCall(SystemCallType.WRITE_PROCESS, p5);
		
	}

}
