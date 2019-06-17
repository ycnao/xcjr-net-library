package com.xcjr.lib.net.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 接口统一返回json对象对应的bean
 * author: Created by 闹闹 on 2018/9/13
 * version: 1.0.0
 */
public class ResponseJsonBean {
	
	private String code;
	private String description;     //描述
	private Object data;            //返回结果数据。不定类型,根据具体情况在转型处理。
	private boolean isSuccess;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public <T> T getdata(Class<T> class1) {
		if (data == null) {
			throw new NullPointerException("data == null");
		}
		if (data instanceof String) {
			String data1 = (String) data;
			return (T) data1;
		} else if (data instanceof JSONObject) {
			JSONObject data1 = (JSONObject) data;
			return (T) data1;
		} else if (data instanceof Boolean) {
			Boolean data1 = (Boolean) data;
			return (T) data1;
		} else if (data instanceof Long) {
			Long data1 = (Long) data;
			return (T) data1;
		} else if (data instanceof Double) {
			Double data1 = (Double) data;
			return (T) data1;
		} else if (data instanceof JSONArray) {
			JSONArray data1 = (JSONArray) data;
			return (T) data1;
		} else if (data instanceof Integer) {
			Integer data1 = (Integer) data;
			return (T) data1;
		} else {
			return null;
		}
	}
	
	public boolean isSuccess() {
		return isSuccess;
	}
	
	public void setSuccess(boolean success) {
		isSuccess = success;
	}
	
	@Override
	public String toString() {
		return "ResponseJsonBean{" +
				"code='" + code + '\'' +
				", description='" + description + '\'' +
				", data=" + data +
				", isSuccess=" + isSuccess +
				'}';
	}
}
