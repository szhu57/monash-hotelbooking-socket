package client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import constants.Constants;

public class ClientHOOP{
	private Socket mySocket;
	private BufferedReader reader;
	private PrintStream writer;
	

	public ClientHOOP(String server) {
		mySocket = null;
		InputStream inStream = null;
	    OutputStream outStream = null;
		try{
			InetAddress address = InetAddress.getByName(server);
		
			//e.printStackTrace();
			//System.exit(1);
		
			mySocket = new Socket(address, Constants.BROKERPORT);

			inStream = mySocket.getInputStream();
    		outStream = mySocket.getOutputStream();

    		reader = new BufferedReader(new InputStreamReader(inStream));
    		writer = new PrintStream(outStream);
		}catch(UnknownHostException e){
      		e.printStackTrace();
      	}catch (IOException e) {
      		e.printStackTrace();
      	}

      	

	}
	public void quit(){
		try {
     		 writer.print(Constants.QUIT+Constants.CR_LF);
      		 reader.close();
      		 writer.close();
      		 mySocket.close();
    	} catch (Exception e) {
      // ignore
    		}
	}

	public String[] checkCity() {
	  
	    writer.print("1"+Constants.CR_LF); 
	    ArrayList<String> tempList = new ArrayList<String>();
	    tempList = getResponds();
//	    String line = null;
//	    while (true) {
//	      try {
//	        line = reader.readLine();
//	      } catch (IOException e) {
//	        break;
//	      }
//	      if (line.equals("")) {
//	        break;
//	      }
//	      tempList.add(line);
//	    }
	    String[] cityList = new String[tempList.size()];
	    tempList.toArray(cityList);
	    return cityList;
	  }

	public String[] checkHotel(){
		 writer.print("2"+Constants.CR_LF);
		 ArrayList<String> tempList = new ArrayList<String>();
		 tempList = getResponds();
//		    String line = null;
//		    while (true) {
//		      try {
//		        line = reader.readLine();
//		      } catch (IOException e) {
//		        break;
//		      }
//		      if (line.equals("")) {
//		        break;
//		      }
//		      tempList.add(line);
//		    }
		    String[] hotels = new String[tempList.size()];
		    tempList.toArray(hotels);
		    return hotels;
		  }
		 		
	public String[] checkCityHotel(String city){
		 writer.print("3"+","+city+Constants.CR_LF);
		 ArrayList<String> tempList = new ArrayList<String>();
		 tempList = getResponds();
//		    String line = null;
//		    while (true) {
//		      try {
//		        line = reader.readLine();
//		      } catch (IOException e) {
//		        break;
//		      }
//		      if (line.equals("")) {
//		        break;
//		      }
//		      if (line.equals("ERROR")) {
//			        break;
//			      }
//		      tempList.add(line);
//		    }
		    String[] hotels = new String[tempList.size()];
		    tempList.toArray(hotels);//for (int n = 0; n < hotels.length; n++) {
		        //System.out.print(hotels[n]+Constants.CR_LF);
		      //}
		    return hotels;
		  }
	public ArrayList<String>listRoom(String city,String hotel){
		writer.print("4"+","+city+","+hotel+Constants.CR_LF);
		
		 ArrayList<String> tempList = new ArrayList<String>();
	//	    String line = null;
		    tempList = getResponds();
//		    while (true) {
//		      try {
//		        line = reader.readLine();
//		      } catch (IOException e) {
//		        break;
//		      }
//		      if (line.equals("")) {
//		        break;
//		      }
//		      if (line.equals("ERROR")) {
//			        break;
//			      }
//		      	tempList.add(line);
//		    }
		    
		    return tempList;
}
	public int bookRoom(String city,String hotel, String type,String in,String out){
		
		writer.print("5"+","+city+","+hotel+","+type+","+in+","+out+Constants.CR_LF);
		
		String line = null;
		try{
			line = reader.readLine();
		}catch (IOException e){
			e.printStackTrace();
		}
		return Integer.parseInt(line);
		
	
		
		
	}
	
	
public String insertInfo(int id,String name,String cno,String email,String hotel){
		
		writer.print("insert"+","+id+","+name+","+cno+","+email+","+hotel+Constants.CR_LF);
		
		
		String line = null;
		try{
			line = reader.readLine();
		}catch (IOException e){
			e.printStackTrace();
		}
		return line;
		
	}
	public ArrayList<String> getResponds(){
		ArrayList<String> tempList = new ArrayList<String>();
	    String line = null;
	    while (true) {
	      try {
	        line = reader.readLine();
	      } catch (IOException e) {
	        break;
	      }
	      if (line.equals("")) {
	        break;
	      }
	      if (line.equals("ERROR")) {
		        break;
		      }
	      	tempList.add(line);
	    }
	    return tempList;
		
	}
	
	
	}
	