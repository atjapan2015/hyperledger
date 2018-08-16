package com.hyperledger.fabric.function.example2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hyperledger.fabric.common.util.RestUtil;
import com.hyperledger.fabric.function.example2.service.Example2Service;

@Service
public class Example2ServiceImpl implements Example2Service {

	@Autowired
	RestUtil restUtil;

	@Override
	public ResponseEntity<String> getVersion(String url) {

		ResponseEntity<String> result = restUtil.get(url);
		return result;
	}

	@Override
	public ResponseEntity<String> doInvocation(String url, Object body) {

		ResponseEntity<String> result = restUtil.post(url, body);
		return result;
	}

	@Override
	public ResponseEntity<String> doQuery(String url, Object body) {

		ResponseEntity<String> result = restUtil.post(url, body);
		return result;
	}
}
