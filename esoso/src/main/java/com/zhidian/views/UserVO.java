package com.zhidian.views;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class UserVO {
	UserVO() {
	}

	UserVO(String name, Integer age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the address
	 */
	@Size(min=2, max=30, message="博客address长度为2-30")
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	private String name;

	@Max(10)
	private Integer age;

	private String address;

	@Override
	public String toString() {
		return "UserVO [name=" + name + ", age=" + age + ", address=" + address
				+ "]";
	}
	
}
