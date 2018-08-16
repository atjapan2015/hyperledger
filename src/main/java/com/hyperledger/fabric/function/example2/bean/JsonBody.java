package com.hyperledger.fabric.function.example2.bean;

public class JsonBody {

	private String channel;
	private String chaincode;
	private String chaincodeVer;
	private String method;
	private String[] args;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChaincode() {
		return chaincode;
	}

	public void setChaincode(String chaincode) {
		this.chaincode = chaincode;
	}

	public String getChaincodeVer() {
		return chaincodeVer;
	}

	public void setChaincodeVer(String chaincodeVer) {
		this.chaincodeVer = chaincodeVer;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

}
