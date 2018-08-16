package com.hyperledger.fabric.function.example2.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hyperledger.fabric.function.example2.facade.Example2Facade;
import com.hyperledger.fabric.function.example2.service.Example2Service;

@Service
public class Example2FacadeImpl implements Example2Facade {

	@Autowired
	Example2Service example2Service;

	@Override
	public ResponseEntity<String> getVersion(String url) {

		ResponseEntity<String> result = example2Service.getVersion(url);
		return result;
	}

	@Override
	public ResponseEntity<String> doInvocation(String url, Object body) {

		ResponseEntity<String> result = example2Service.doInvocation(url, body);
		return result;
	}

	@Override
	public ResponseEntity<String> doQuery(String url, Object body) {

		ResponseEntity<String> result = example2Service.doQuery(url, body);
		return result;
	}
}
