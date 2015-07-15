package server;

import database.DBUtility;


public class BrokerImpt {
	private DBUtility db;
	public BrokerImpt(){
		db = new DBUtility();
	}
	
	
	public String[] checkCity(){
		String[] cities = db.selectCityFromDatabase();
		return cities;
	}
	public String[] checkHotel(){
		String [] hotels =db.selectHotelFromDatabase();
		return hotels;
	}
	public String[]checkCityHotel(String city){
		String [] hotels =db.selectCityHotelFromDatabase(city);
		return hotels;
	}
}
