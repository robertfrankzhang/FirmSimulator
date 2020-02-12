package operations;

import java.util.ArrayList;
import java.util.HashMap;

public class ProcessDictionary {
	
	HashMap<String,ProcessType> processes = new HashMap<String,ProcessType>();
	
	public ProcessDictionary() {
		
	}
	
	public void addProcessType(String processID, float timer, ArrayList<String> contextParamID, ProcessClass processClass) {
		ProcessType process;
		if (processClass.equals(ProcessClass.KNOWNR)){
			process = new ProcessType(processID, timer, contextParamID);
		}else {
			process = new ProcessType(processID, contextParamID, timer);
		}
		
		
		processes.put(processID, process);
		
	}
}
