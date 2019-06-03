package com.ssm.mf.aop;

public class Transaction {

	void beginTransaction() {
		System.out.println("begin Transaction");
	}

	void commit() {
		System.out.println("commit");
	}
}