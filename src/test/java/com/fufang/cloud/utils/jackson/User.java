package com.fufang.cloud.utils.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	private Integer id;
	private String name;
	private String teliphone;

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

	public String getTeliphone() {
		return teliphone;
	}

	public void setTeliphone(String teliphone) {
		this.teliphone = teliphone;
	}
}
