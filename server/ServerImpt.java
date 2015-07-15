package server;

import java.util.ArrayList;

import database.DBUtility;

public class ServerImpt {
	
	private DBUtility db;
	public ServerImpt(){
		db = new DBUtility();
	}
	
	public ArrayList<String> listRoom(String city,String hotel){
		return db.selectRoomDatabase( city, hotel);
	}
	
	public int checkRoomstate(String in,String out,String hotel,String city,String type){
		return db.selectStateFromDatabase(in,out,hotel,city,type);
	}
	public boolean insertInfo(String id,String name,String cno,String email){
		return db.insertUserInfo(id,name,cno,email);
	}
}
