package com.ssm.mf.domain;

import org.apache.ibatis.type.Alias;

@Alias("studentSqlserver")
public class StudentSqlserver {
	private Integer id;
	private String name;
	private Integer age;
	private String address;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "StudentSqlserver [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + "]";
	}
	
	

}
