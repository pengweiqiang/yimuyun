package com.yimuyun.lowraiseapp.util.longer;

import java.util.ArrayList;
import java.util.List;

import com.handheld.UHFLonger.UHFLongerManager;

import android.app.Application;
/**
 * use to save public variable
 * @author Administrator
 *
 */
public class MyApplication{
	public static MyApplication myapp;
	private List<String> listEpc = new ArrayList<String>(); //EPCs
	private String selectEPC = null;//
	private String password = null;//
	private UHFLongerManager manager = null; //
	
	private List<String> listInputEPC = new ArrayList<String>() ;
	
	
	public UHFLongerManager getmanager() {
		return manager;
	}



	public List<String> getListInputEPC() {
		return listInputEPC;
	}



	public void setListInputEPC(List<String> listInputEPC) {
		this.listInputEPC = listInputEPC;
	}



	public void setmanager(UHFLongerManager manager) {
		this.manager = manager;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getSelectEPC() {
		return selectEPC;
	}



	public void setSelectEPC(String selectEPC) {
		this.selectEPC = selectEPC;
	}



	public List<String> getListEpc() {
		return listEpc;
	}



	public void setListEpc(List<String> listEpc) {
		this.listEpc = listEpc;
	}

	
	public void addEPC(String epc){
		if(listEpc != null){
			listEpc.add(epc);
		}
	}
}
