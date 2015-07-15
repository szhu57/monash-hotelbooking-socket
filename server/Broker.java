package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import constants.Constants;



public class Broker{
	public static void main(String[] args) throws IOException {
		ServerSocket s = null;
		try{
			s = new ServerSocket(Constants.BROKERPORT);
		}catch (IOException e){
			e.printStackTrace();
			System.exit(1);
		}

		System.out.print("Broker server Running..."+Constants.CR_LF);
		while(true){
			Socket incoming = null;
			try{
				incoming = s.accept();
			}catch (IOException e){
				e.printStackTrace();
				continue;
			}

			new Thread(new SocketHandler(incoming)).start();
		}
		
	}
}


	class SocketHandler implements Runnable{
		
		Socket incoming;
		
		
		BrokerImpt bri = new BrokerImpt();
		//BrokerHOPP brh = new BrokerHOPP();
		BufferedReader reader;
		PrintStream writer;

		SocketHandler(Socket incoming){

			this.incoming = incoming;

		}

		public void run(){
			
			
			// to get the inputstream and outputstream
			
			try{
				writer = new PrintStream(incoming.getOutputStream());
				reader = new BufferedReader(new InputStreamReader(incoming.getInputStream()));

				while(true){
					String line = null;
					line = reader.readLine();
					 if (line == null) {
				          break;
				        }

					System.out.print("Receiver Request: "+line+Constants.CR_LF);
					  if (line.startsWith(Constants.QUIT)) break;
					  else if (line.startsWith("1")) {
				         checkCityRequest();
					  }   
					  else if (line.startsWith("2")) {
					         checkHotelRequest();
						  }
					  else if (line.startsWith("3")) {
						  String str[] = line.split(",");
					         checkCityHotelRequest(str[1]);
					  }
					  else if(line.startsWith("4")){
						  
						     BrokerHOPP brh = new BrokerHOPP("listroom");
						  checkRoomRequest(brh,line);
					  }
					  else if(line.startsWith("5")){
						  BrokerHOPP brh = new BrokerHOPP("bookroom");
						  
						  bookRoomRequest(brh,line);
					  }
					  else if(line.startsWith("insert")){
						  BrokerHOPP brh = new BrokerHOPP("insertInfo");
						  insertInfo(brh,line);
						  
					  }
					  
					  
					  	else{
							  writer.print(Constants.ERROR+Constants.CR_LF);
						  }
					  
			}incoming.close();
			}catch (IOException e){
				e.printStackTrace();
			 }
			

		}
		
		//public void cityListRequest() {
		    //String[] cityNames = {"a","b","c"};
		    public void checkCityRequest(){
		    	//DBUtility  db = new DBUtility();
		    	String[] cities =bri.checkCity();
		    	//return cities;
		    //}
		   
		    for (int n = 0; n < cities.length; n++) {
		      writer.print(cities[n]+Constants.CR_LF);
		    }
		    writer.print(Constants.CR_LF);
		  }
		    
		    
		    public void checkHotelRequest(){
		    	//DBUtility  db = new DBUtility();
		   String[]  hotels = bri.checkHotel();
		    	//return cities;
		    //}
		   
		    for (int n = 0; n < hotels.length; n++) {
		      writer.print(hotels[n]+Constants.CR_LF);
		    }
		    writer.print(Constants.CR_LF);
		  }


		    public void checkCityHotelRequest(String city){
		    	//DBUtility  db = new DBUtility();
		   String[]  hotels = bri.checkCityHotel(city);
		    	//return cities;
		    //}
		   
		    for (int n = 0; n < hotels.length; n++) {
		      writer.print(hotels[n]+Constants.CR_LF);
		    }
		    writer.print(Constants.CR_LF);
		  }
		   
		    public void checkRoomRequest(BrokerHOPP brh,String para){
		    	
		    	ArrayList<String> list = brh.listRoom(para);
			 	Iterator<String> it = list.iterator();
		        while(it.hasNext()){
		        	String str = it.next();
		        	   writer.print(str+Constants.CR_LF);
		      }
		        writer.print(Constants.CR_LF);
		 }
		    
		    public void bookRoomRequest(BrokerHOPP brh,String para){
		    	
		    	int s=brh.bookRoom(para);
		    	writer.print(s+Constants.CR_LF);
		    	
		    }
		    public void  insertInfo(BrokerHOPP brh,String para){
		    	 
		    	String msg=brh.insertInfo(para);
		    	writer.print(msg+Constants.CR_LF);
		    	//System.out.println(msg);
		    }

	}


