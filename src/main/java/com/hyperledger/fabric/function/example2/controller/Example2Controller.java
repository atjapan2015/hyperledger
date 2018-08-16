package com.hyperledger.fabric.function.example2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyperledger.fabric.function.example2.bean.JsonBody;
import com.hyperledger.fabric.function.example2.facade.Example2Facade;

@Controller
public class Example2Controller {

	private final static String GATEWAY_HOSTNAME_PORT = "http://10.0.2.15:7100";
	private final static String VERSION = "/bcsgw/rest/version";
	private final static String QUERY = "/bcsgw/rest/v1/transaction/query";
	private final static String INVOCATION = "/bcsgw/rest/v1/transaction/invocation";
	private final static String ASYNCINVOCATION = "/bcsgw/rest/v1/transaction/asyncInvocation";
	private final static String GETSTATUS = "/bcsgw/rest/v1/transaction?channel=<name>&txid=<id> ";
	private final static String WAITSTATUS = "/bcsgw/rest/v1/transaction/waitStatus";

	@Autowired
	Example2Facade example2Facade;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {
		return "Hello World";
	}

	@RequestMapping(value = "/version", method = RequestMethod.GET)
	@ResponseBody
	public String getVersion() {

		String url = GATEWAY_HOSTNAME_PORT + VERSION;
		ResponseEntity<String> result = example2Facade.getVersion(url);
		return result.getBody();
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public String doQuery() {

		String url = GATEWAY_HOSTNAME_PORT + QUERY;

		JsonBody jsonBody = new JsonBody();
		jsonBody.setChannel("oracledemoorderer");
		jsonBody.setChaincode("obcs-marbles");
		jsonBody.setChaincodeVer("v0");
		jsonBody.setMethod("readMarble");

		String[] args = { "marble01" };
		jsonBody.setArgs(args);

		ResponseEntity<String> result = example2Facade.doQuery(url, jsonBody);
		return result.getBody();
	}

	@RequestMapping(value = "/invocation", method = RequestMethod.GET)
	@ResponseBody
	public String doInvocation() {

		String url = GATEWAY_HOSTNAME_PORT + INVOCATION;

		JsonBody jsonBody = new JsonBody();
		jsonBody.setChannel("oracledemoorderer");
		jsonBody.setChaincode("obcs-marbles");
		jsonBody.setChaincodeVer("v0");
		jsonBody.setMethod("initMarble");

		String[] args = { "marble02", "red", "50", "jerry" };
		jsonBody.setArgs(args);

		ResponseEntity<String> result = example2Facade.doInvocation(url, jsonBody);
		return result.getBody();
	}
}
