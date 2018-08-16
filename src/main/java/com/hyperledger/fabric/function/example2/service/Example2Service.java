package com.hyperledger.fabric.function.example2.service;

import org.springframework.http.ResponseEntity;

public interface Example2Service {

	public ResponseEntity<String> getVersion(String url);

	public ResponseEntity<String> doInvocation(String url, Object body);

	public ResponseEntity<String> doQuery(String url, Object body);
}
