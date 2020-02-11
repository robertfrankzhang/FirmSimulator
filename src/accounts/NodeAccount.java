package accounts;

import java.util.HashMap;

/*
 NodeAccount objects make it possible to create an AccountTree, where a BaseAccount contains AccountObjects of the same
behavior for operational purposes, but the Tree makes it possible for BaseAccounts to be grouped under more general labels
 */

public class NodeAccount {
	String nodeAccountID;
	AccountType type;
	String accountDescription;
	HashMap<String,NodeAccount> accounts = new HashMap<String,NodeAccount>();
	NodeAccount parentNodeAccount = null;
	
	public NodeAccount(String nodeAccountID,AccountType type,String accountDescription,NodeAccount parentNodeAccount) {
		this.nodeAccountID = nodeAccountID;
		this.type = type;	
		this.accountDescription = accountDescription;
		this.parentNodeAccount = parentNodeAccount;
	}
	
	public NodeAccount(String nodeAccountID,AccountType type,String accountDescription) {
		this.nodeAccountID = nodeAccountID;
		this.type = type;	
		this.accountDescription = accountDescription;
	}	
	
	public float getAccountValue() {
		float totalValue = 0;
		for (NodeAccount account:this.accounts.values()) {//Recursive value summation
			totalValue += account.getAccountValue();
		}
		return totalValue;
	}
	
	public void addAccount(String nodeAccountID,String accountDescription,boolean isBaseAccount) {
		if (accounts.containsKey(nodeAccountID)) {
			System.out.println("Cannot add duplicate ID account");
			return;
		}
		if (isBaseAccount) {
			NodeAccount account = new BaseAccount(nodeAccountID,this.type,accountDescription,this);
			accounts.put(nodeAccountID, account);
		}else {
			NodeAccount account = new NodeAccount(nodeAccountID,this.type,accountDescription,this);
			accounts.put(nodeAccountID, account);
		}
	}
}
