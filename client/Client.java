package client;
import constants.Constants;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Client{

	private BufferedReader console;
	private ClientHOOP clientHOPP;
	private Scanner s;


	public static void main(String[] args) {
		 if (args.length != 1) {
		      System.err.println("Usage: Client address");
		      System.exit(1);
		    }
		
	 	Client c = new Client(args[0]);
	 		c.loop();
	 }
	
	
	 public  Client(String server){
	 	clientHOPP = null;
	 	try{
	 		clientHOPP = new ClientHOOP(server);//to get socket from ClientHOPP
	 	}catch (Exception e){
	 		e.printStackTrace();
	 		System.exit(1);
	 	}

	 	
	 	console = new BufferedReader(new InputStreamReader(System.in)); 

	 }
	 public void loop(){
		 System.out.print("Welcome to Hotel Reservation Client\r\n");
		 System.out.println("Enter Requeset"+Constants.CR_LF +"0:quit");
			System.out.print("1:listcity"+Constants.CR_LF);
			System.out.print("2:listhotel"+Constants.CR_LF);
			System.out.print("3:listcityhotel"+Constants.CR_LF);
			System.out.print("4:listroom"+Constants.CR_LF);
			System.out.print("5:book"+Constants.CR_LF);
	 	
			while(true){
				
	 		String line = null;
	 		try{
	 			 line = console.readLine();
	 		}catch (IOException e){
	 			clientHOPP.quit();
	 			e.printStackTrace();
	 			System.exit(1);
	 		};
	 		
	 		switch(line){
				case "1": listCity();break;
				case "2":listHotel();break;
				case "3":listCityHotel();break;
				case "4":listRoom();break;
				case "5":bookRoom();break;
				case "0": clientHOPP.quit(); System.exit(0);break;
				default:  System.out.print("plesea enter the correct command:"+Constants.CR_LF);break;
		    }
	 	}

	 }
	  public void listCity() {
	
		    String[] cityList = clientHOPP.checkCity();
		    if (cityList.length == 0) {
		      System.out.print("No city list available"+Constants.CR_LF);
		    } else {
		      System.out.println("city is:");
		      for (int n = 0; n < cityList.length; n++) {
		        System.out.print(cityList[n]+Constants.CR_LF);
		      }
		    }
		  }
	 

	  public void listHotel(){

		    String[] hotels = clientHOPP.checkHotel();
		    if (hotels.length == 0) {
		      System.out.print("No hotel list available"+Constants.CR_LF);
		    } else {
		      System.out.println("hotel is:");
		      for (int n = 0; n < hotels.length; n++) {
		        System.out.print(hotels[n]+Constants.CR_LF);
		      }
		    }
		  }
	
	  public void listCityHotel(){
		  s = new Scanner(System.in);
		  System.out.print("please enter the city"+Constants.CR_LF);
		  String city = s.next();
		    String[] hotels = clientHOPP.checkCityHotel(city);
		    if (hotels.length == 0) {
		      System.out.print("No hotel list available"+Constants.CR_LF);
		    } else {
		      System.out.println("hotel is:");
		      for (int n = 0; n < hotels.length; n++) {
		        System.out.print(hotels[n]+Constants.CR_LF);
		      }
		    }
		  }
	public void listRoom(){
		boolean flag =false;
		while(!flag)
		{
		 s = new Scanner(System.in);
		 System.out.print("please enter the city"+Constants.CR_LF);
		 String city =s.next();
		 System.out.print("please enter the hotel"+Constants.CR_LF);
		 String hotel =s.next();
	
		 if((hotel.equalsIgnoreCase(Constants.Hilton)||hotel.equalsIgnoreCase(Constants.Jinjiang)||
				 hotel.equalsIgnoreCase(Constants.Regent))&&(city.equalsIgnoreCase(Constants.Beijing)||
						 city.equalsIgnoreCase(Constants.Nanjing) ||city.equalsIgnoreCase(Constants.Shanghai))){
			 
			 ArrayList<String> list = new ArrayList<String>();
			 list = clientHOPP.listRoom(city,hotel);
			 if(list.isEmpty()){
				 System.out.println(" no hotel in this city, please check before enter "); continue;
			 }
			// System.out.print("city"+"\t hote"+"      "+"price"+"      "+"rate"+"      "+"style"+Constants.CR_LF);
			 Iterator<String> it = list.iterator();
			 int count =0;
		        while(it.hasNext()){
		        	String str = it.next();
		        		count++;
		        	   System.out.print(str+"\t");
		        	   if (count%5==0){
		        		   System.out.print(Constants.CR_LF);
		        	   }
		        	  
		        }
		      flag =true;
		 }
		 
		 else
			 System.out.print("your city or hotel is not exist,please enter the correct city or hotel!"+Constants.CR_LF);
	
		
		}
		
	 }
	
	public void bookRoom(){
		
		boolean flag =false;
		
		
		while(!flag){
			s = new Scanner(System.in);
			System.out.print("please enter the city"+Constants.CR_LF);
			String city =s.next();
			System.out.print("Please enter the hotel name"+Constants.CR_LF);
			String hotel =s.next();
			//ArrayList<String> list = new ArrayList<String>();
			 if(!((hotel.equalsIgnoreCase(Constants.Hilton)||hotel.equalsIgnoreCase(Constants.Jinjiang)||
					 hotel.equalsIgnoreCase(Constants.Regent))&&(city.equalsIgnoreCase(Constants.Beijing)||
							 city.equalsIgnoreCase(Constants.Nanjing) ||city.equalsIgnoreCase(Constants.Shanghai)))){
				 	System.out.print("no this hotel or city"+Constants.CR_LF);
				 continue;
			 }
			if((clientHOPP.listRoom(city, hotel)).isEmpty()){
				System.out.print(" hotel or city is not exist,please check before select"+Constants.CR_LF);
				continue;
			}
				System.out.print("please enter the room type you want book<single or double>"+Constants.CR_LF);
				String type = s.next();
				String in=null;
				String out=null;
				while(true){
					System.out.print("please enter the check in date (formate:yyyy-mm-dd)"+Constants.CR_LF);
					 in = s.next(); 
					 if(!(isDataFor(in))) 
					 { 
						 System.out.println("wrong date formate");
						 continue;
					 }
						 
					System.out.print("please enter the check out date (formate: yyyy-mm-dd)"+Constants.CR_LF);
					 out = s.next(); 
					 if(!(isDataFor(out)))
					 {
						 System.out.println("wrong date formate");
						 continue; 
					 }
					 if((out.compareTo(in))<=0)
					 {
						 System.out.println("  check out date is illeage");
						 continue;
					 }
					 break;
				}
				
				flag = true;
			int id =clientHOPP.bookRoom(city,hotel,type,in,out);
			if(id<=0){//if id >0 indicate id is the user id 
				System.out.println(id);
				System.out.print("room is not available"+Constants.CR_LF);break;
			}
			else{
				System.out.print("the room is availabe and your ID is"+id +Constants.CR_LF);
				userInfo(id,hotel);
				
			}
		}
		
	}
	
	
	public void userInfo(int id,String hotel){
		
		s = new Scanner(System.in);
		System.out.print("please enter your information"+Constants.CR_LF);
		
		System.out.print("please enter your name"+Constants.CR_LF);
		String name = s.next();
		System.out.print("please enter your credit number"+Constants.CR_LF);
		String cno = s.next();
		while(true){
		System.out.print("please enter your email"+Constants.CR_LF);
		String email = s.next(); 
		if(isEmail(email)){
			
			
			String s=clientHOPP.insertInfo(id,name,cno,email,hotel);
			System.out.print(s+Constants.CR_LF);break;
		}
		else{
			System.out.print("wrong email formate"+Constants.CR_LF);
			continue;
		}
		
		
		
	}	
	
		
	}
	 public boolean isEmail(String email){  
         if (null==email || "".equals(email)) return false;    
         Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//∏¥‘”∆•≈‰  
         Matcher m = p.matcher(email);  
         return m.matches();  
        }  
	 public boolean isDataFor(String data){  
         if (null==data || "".equals(data)) return false;    
         Pattern p =  Pattern.compile("^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$");//∏¥‘”∆•≈‰  
         Matcher m = p.matcher(data);  
         return m.matches();  
        }  

}

