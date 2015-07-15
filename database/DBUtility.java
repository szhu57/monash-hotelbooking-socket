package database;
import java.sql.*;
import java.util.ArrayList;

import constants.Constants;





public class DBUtility  {
	
		private Connection conn = null;
		public DBUtility(){
			//user=root&password=password";
			conn = GetDBCon();
		
}
	
	public Connection GetDBCon() {
        try {
           
            Class.forName("com.mysql.jdbc.Driver").newInstance();
           String url = "jdbc:mysql://127.0.0.1:3306/id26346915";
            String user = "fit5183a1"; 
          String password = "";

            Connection conn = DriverManager.getConnection(url,user,password);
            System.out.println("DataBase connecting sucess£¡");
            return conn;
        } catch (ClassNotFoundException ex1) {
            System.out.println("Can't find driver");
            ex1.printStackTrace();
            return null;
        } catch (SQLException ex2) {
            System.out.println("DataBase connection failed");
            ex2.printStackTrace();
            return null;
        } catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
	public void freeDB(){
		 try {
			conn.close();
			System.out.println("Database has been freed!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn = null;
		}
	 }
	
	 public String [] selectCityFromDatabase(){
		 try{
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("select distinct city from hotel");
			 if(rs == null){
				 System.out.println("Empty Set!\n");
				 return null;
			 }
			 String[] cities = new String[3];
			 int i = 0; 
			 while(rs.next()){
				 
				cities[i++] = rs.getString(1);
			 }
			 return cities;
		 }catch(SQLException e){
			 e.printStackTrace();
			 return null;
		 }
		 
	 }
	 public String[] selectHotelFromDatabase(){
		 
		 try{
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("select distinct name from hotel");
			 if(rs == null){
				 System.out.println("Empty Set!\n");
				 return null;
			 }
			 String[] hotels = new String[3];
			 int i = 0; 
	
			 while(rs.next()){
				 hotels[i++] = rs.getString(1);
			
			 }
			 return hotels;
		 }catch(SQLException e){
			 e.printStackTrace();
			 return null;
		 }
	
}
	 public String[] selectCityHotelFromDatabase(String city){
	 
	  try{
		 Statement stmt = conn.createStatement();
		 ResultSet rs = stmt.executeQuery("select  name from hotel where city= '"+city+"'");
		 if(rs == null){
			 System.out.println("Empty Set!\n");
			 return null;
		 }
		 ArrayList<String> tempList = new ArrayList<String>();
		 //String[] hotels = new String[2];

		 while(rs.next()){
			 tempList.add(rs.getString(1));
		
		 }
		 String[] hotels = new String[tempList.size()];
		    tempList.toArray(hotels);
		 return hotels;
	 }catch(SQLException e){
		 e.printStackTrace();
		 return null;
	 }

}
	 
	  public ArrayList<String> selectRoomDatabase(String city,String hotel){
		  
		  try{
				 Statement stmt = conn.createStatement();
				 ResultSet rs = stmt.executeQuery("select  city,Hotel_name,price,rate,style from hotelroom where city= '"+city+"'and Hotel_name = '"+hotel+"'");
				 if(rs == null){
					 System.out.println("Empty Set!\n");
					 return null;
				 }
				 ArrayList<String> tempList = new ArrayList<String>();
				 //String[] hotels = new String[2];

				 while(rs.next()){
					 tempList.add(rs.getString(1));
					 tempList.add(rs.getString(2));
					 tempList.add(rs.getString(3));
					 tempList.add(rs.getString(4));
					 tempList.add(rs.getString(5));
				
				 }
				 return tempList;
				 
		  }catch(SQLException e){
			  e.printStackTrace();
			  return null;
		  }
		}
	  
	  public  int selectStateFromDatabase(String in,String out,String hotel,String city,String type){
			 try{
				 Statement stmt = conn.createStatement();
				 ResultSet rs = stmt.executeQuery("select state from hotelroom where city= '"+city+"'and Hotel_name = '"+hotel+"'and style='"+type+"'" );
				 if(rs ==null){
					 System.out.println(-1);
					 return -1;
				 }
				 int result = 0;
				 
				 while(rs.next()){
					 result = (Integer) rs.getObject(1);
				 //System.out.println(rs.getObject(1));
				 }		
				 if (result>0){
					int id =(int)((Math.random()*9+1)*100000);//产生一个随机的六位用户id
					//String user_id= new String(id);
					if((insertBooking(id+"",in,out,hotel,city,type))&&(updataState(city,hotel,type)))//insert booking table information
					
				
						return id;
													//if success then update the available room number
						
				 }
				 return -1;
				
			 }
			 catch(SQLException ex){
				 ex.printStackTrace();
				 return -1;
			 }
			
	 }
	  
	  public boolean insertBooking(String id,String in,String out,String hotel,String city,String type ){
		    // String values = id+Constants.comma+in+Constants.comma+out+Constants.comma+hotel+Constants.comma+city+Constants.comma+type;
			 String table = "booking";
			 boolean res = insertIntoDatabase(table,id,in,out,hotel,city,type);
			 return res;
	  }
	  public boolean insertUserInfo(String id, String name,String cno,String email){
		 
			// String values = id + Constants.comma+name+Constants.comma+cno+Constants.comma+email;
			 String table = "user";
			 boolean res = insertIntoDatabase(table,id,name,cno,email);
			 return res;
		
	  }
	  public boolean insertIntoDatabase(String table,String id,String in,String out,String hotel,String city,String type){
			 try{
				 Statement stmt = conn.createStatement();
				 int rs = stmt.executeUpdate("insert into "+ table +Constants.space +"values('"+id+"','"+in+"','"+out+"','"+hotel+"','"+city+"','"+type+"')" );
				 //System.out.println(rs);
				 if(rs==1)
				 return true;
				 return false;
			 }
			 catch(SQLException ex){
				 ex.printStackTrace();
				 return false;
			 }
		 }
	  public boolean insertIntoDatabase(String table,String id,String name,String cno,String email){
			 try{
				 Statement stmt = conn.createStatement();
				 int rs = stmt.executeUpdate("insert into "+ table +Constants.space +"values('"+id+"','"+name+"','"+cno+"','"+email+"')");
				 //System.out.println(rs);
				 if(rs==1)
				 return true;
				 return false;
			 }
			 catch(SQLException ex){
				 ex.printStackTrace();
				 return false;
			 }
		 }
	  
	  
	  public boolean updataState(String city,String hotel,String type){
		  try{
				 Statement stmt = conn.createStatement();
				 stmt.execute("LOCK TABLES "+"hotelroom"+" WRITE;");
				 int rs = stmt.executeUpdate("update hotelroom set state=state-1 where city='"+city+"'and Hotel_name= '"+hotel+"'and style='"+type+"'" );
				 stmt.execute("UNLOCK TABLES");	 //System.out.println(rs);
				 if(rs==1)
				 return true;
				 return false;
			 }
			 catch(SQLException ex){
				 ex.printStackTrace();
				 return false;
			 }
	  }
}	          


