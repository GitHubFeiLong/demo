package com.ssm.mf.domain;

import org.apache.ibatis.type.Alias;

@Alias("student")
public class Student {
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", username=" + username + ", age=" + age + ", subject=" + subject + ", score="
				+ score + ", evaluate=" + evaluate + "]";
	}
	private Integer id;
	private String username;
	private Integer age;
	private Integer subject;
	private Double score;
	private String evaluate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getSubject() {
		return subject;
	}
	public void setSubject(Integer subject) {
		this.subject = subject;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	
	


}
