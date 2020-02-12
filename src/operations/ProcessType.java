package operations;
import accounts.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class ProcessType {
	/*
	 * Inverse Throughput (1/R): Units of time it takes the process to output one inventory item.
	 * Inventory I and FlowRate T are then determined by the rate of inputs into the process via
	 * Little's Law I * (1/R) = T
	 */
	float inverseR = 0;
	
	/*
	 * FlowRate T: Units of time it takes one inventory item to go from the beginning to the end of the
	 * process. Inventory I and Inverse Throughput (1/R) are then determined by the rate of inputs into
	 * the process via Little's Law I * (1/R) = T
	 */
	
	float flowRate = 0;
	
	ProcessClass processClass;
	
	ArrayList<String> contextParamIDs;
	
	String processID;
	
	HashMap<LedgerEntry,Function<Float,Float>> accountingMap = new HashMap<LedgerEntry,Function<Float,Float>>();
	
	/*
	 * This constructor is used when the efficiency of the process is known and constant. For example,
	 * we may know that a machine can injection mold a plastic product in 20 minutes. Thus,
	 * inverseR = 20 minutes, and so flowRate is not relevant to modeling the problem, as it is
	 * implicitly found via Little's Law and constantly changing as the rate of input changes.
	 * A queue where the lead good has a countdown based on 1/R is a good way to model this processClass
	 */
	public ProcessType(String processID, float inverseR, ArrayList<String> contextParamIDs) {
		this.inverseR = inverseR;
		this.contextParamIDs = contextParamIDs;
		this.processClass = ProcessClass.KNOWNR;
		this.processID = processID;
	}
	
	/*
	 * This constructor is used when the exact duration any given inventory unit is known and constant.
	 * For example, we may know that it takes on average 4 weeks for a customer to return a leased good.
	 * Thus, flowRate = 4 weeks, and so inverseR is not relevant to modeling the problem, as it is
	 * implicitly found via Little's Law and constantly changing as the rate of input changes. A simultaneous 
	 * countdown based on T for each good in the process is a good way to model this processClass.
	 */
	public ProcessType(String processID, ArrayList<String> contextParamIDs, float flowRate) {
		this.flowRate = flowRate;
		this.contextParamIDs = contextParamIDs;
		this.processClass = ProcessClass.KNOWNT;
		this.processID = processID;
	}
	
	/*
	 * Define the conversion of one Good to another (delta value, delta ID, split to multiple goods, etc...)
	 * and direct the output good(s) to next processType
	 */
	public void defineProcessCore(){

	}
	
	/*
	 * Add a cost of revenue driver to process. All of these costs are invoked
	 */
	public void addAccountingDriver(String debitAccountID, String creditAccountID, float amount, Function<Float,Float> multiplier) {
		LedgerEntry entry = new LedgerEntry(debitAccountID, creditAccountID, amount, 0); //Timestamp not relevant here
		this.accountingMap.put(entry, multiplier);
	}
	
	public void addAccountingDriver(String debitAccountID, String creditAccountID, float amount, float multiplier) {
		LedgerEntry entry = new LedgerEntry(debitAccountID, creditAccountID, amount, 0); //Timestamp not relevant here
		Function<Float, Float> m = (Float e)->{return e*multiplier;}; //Identity functions invoked w/ input = 1
		this.accountingMap.put(entry, m);
	}
	
}
