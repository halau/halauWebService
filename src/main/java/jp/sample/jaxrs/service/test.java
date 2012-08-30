package jp.sample.jaxrs.service;

import jp.sample.jaxrs.domain.Item;

public class test {

	public static void main(String[] args){
		HalauControllerImpl hr = new HalauControllerImpl();
		
		Item[] results = hr.pullRecords();
		System.out.println("4debug");
	}
	
}
