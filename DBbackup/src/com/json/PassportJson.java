package com.json;

import java.util.ArrayList;

public class PassportJson {
    
	private boolean isSuccess;
	private String Msg;
	private ArrayList<Passport> Data;
	
	public PassportJson(){}
	
	public PassportJson(boolean isSuccess, String msg, ArrayList<Passport> data) {
		super();
		this.isSuccess = isSuccess;
		Msg = msg;
		Data = data;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	public ArrayList<Passport> getData() {
		return Data;
	}
	public void setData(ArrayList<Passport> data) {
		Data = data;
	}
	
	
}
