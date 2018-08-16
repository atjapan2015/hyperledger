package com.hyperledger.fabric.common.util;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestUtil {

	private static final Logger logger = LoggerFactory.getLogger(RestUtil.class);

	@Autowired
	RestTemplate restTemplate;

	public ResponseEntity<String> get(String url) {

		HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
		return invoke(url, HttpMethod.GET, entity);
	}

	public ResponseEntity<String> put(String url) {

		HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
		return invoke(url, HttpMethod.PUT, entity);
	}

	public ResponseEntity<String> post(String url, Object body) {

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpEntity<Object> entity = new HttpEntity<>(body, new HttpHeaders());
		return invoke(url, HttpMethod.POST, entity);
	}

	public ResponseEntity<String> delete(String url, Object body) {

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpEntity<Object> entity = new HttpEntity<>(body, new HttpHeaders());
		return invoke(url, HttpMethod.DELETE, entity);
	}

	private <T> ResponseEntity<String> invoke(String url, HttpMethod method, HttpEntity<T> entity) {

		long startTime = Instant.now().toEpochMilli();
		logger.info("######Method-invokeRestTemplete start######");

		ResponseEntity<String> response = null;
		try {
			logger.info("######Rest Api Url:[" + url + "]#######");
			logger.info("######Rest Api Method:[" + method + "]#######");
			logger.info("######Invoke Rest Api Start ...#######");
			response = restTemplate.exchange(url, method, entity, String.class);
			logger.info("######Invoke Rest Api Normal End ...#######");
		} catch (HttpClientErrorException e) {
			logger.error("######Invoke Rest Api Abormal End ...#######");
			e.printStackTrace();
			return handleException(e);
		} catch (HttpServerErrorException e) {
			logger.error("######Invoke Rest Api Abormal End ...#######");
			e.printStackTrace();
			return handleException(e);
		} catch (Exception e) {
			logger.error("######Invoke Rest Api Abormal End ...#######");
			e.printStackTrace();
			return response;
		} finally {
			long endTime = Instant.now().toEpochMilli();
			logger.info("######程序运行时间： " + (endTime - startTime) + "毫秒######");
			logger.info("######Method-invokeRestTemplete end######");
		}

		return response;

	}

	private ResponseEntity<String> handleException(HttpClientErrorException e) {

		String body = e.getResponseBodyAsString();
		if (StringUtils.isEmpty(body)) {
			body = e.getStatusText();
		}

		HttpHeaders headers = e.getResponseHeaders();
		HttpStatus statusCode = e.getStatusCode();

		ResponseEntity<String> response = new ResponseEntity<>(body, headers, statusCode);
		return response;
	}

	private ResponseEntity<String> handleException(HttpServerErrorException e) {

		String body = e.getResponseBodyAsString();
		if (StringUtils.isEmpty(body)) {
			body = e.getStatusText();
		}

		HttpHeaders headers = e.getResponseHeaders();
		HttpStatus statusCode = e.getStatusCode();

		ResponseEntity<String> response = new ResponseEntity<>(body, headers, statusCode);
		return response;
	}
}
