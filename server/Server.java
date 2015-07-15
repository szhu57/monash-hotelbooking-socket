package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;

import constants.Constants;

public class Server {
	public static void main(String[] args) throws IOException
	{
		int port = 0;
		if(args[0].equals(Constants.Hilton))
			port = Constants.HILTONPORT;
		else if(args[0].equals(Constants.Jinjiang))
			port = Constants.JJPORT;
		else if(args[0].equals(Constants.Regent))
			port = Constants.REGENTPORT;
		else{
			System.out.println("There is no such server!\n");
			return;
			}
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println(args[0]+"Server Started");
		try{
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("\nNew client accepted.\n");
				
					new Thread(new ServerHandler(socket)).start();
				
				}
		
		}finally{
			serverSocket.close();
		}
	}
}

  class ServerHandler implements Runnable{
	  Socket s=null;
	  ServerImpt sit = new ServerImpt();
	  PrintStream writer;
	  BufferedReader reader;
	  public ServerHandler(Socket s){
		  this.s=s;
		  
	  }
	 public void run(){
		  
		  try{
				writer = new PrintStream(s.getOutputStream());
				reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

				while(true){
					String line = null;
					line = reader.readLine();
					 if (line == null) {
				          break;
				        }
					 if(line.startsWith("listroom")){
//						 System.out.println(line);
						 String[] str =line.split(",");
//						 for(int i=0; i<str.length;i++){
//							 System.out.println(str[i]);
//						 }
						String city = str[2];
						String hotel = str[3];
//						System.out.println(city+hotel);
						
						listRoom(city,hotel);	
						 
					 }
					 else if(line.startsWith("bookroom")){
						 
						 String str[] =line.split(",");
						 
						 String city =str[2];
						 String hotel = str[3];
						 String type = str[4];
						 String in = str[5];
						 String out = str[6];
						 System.out.println(city+hotel+type+in);
						 checkRoomState(in,out,hotel,city,type);
						 
						 
					 }
					 else if(line.startsWith("insertInfo")){
						 String str[] =line.split(",");
						 String id = str[2];
						 String name = str[3];
						 String cno = str[4];
						 String email= str[5];
						 insertInfo(id,name,cno,email);
						 
					 }
					 else{
						  writer.print(Constants.ERROR+Constants.CR_LF);
					  }

				//	System.out.print("Receiver Request: "+line+Constants.CR_LF);
					
				}
	
		  }catch (IOException e){
				e.printStackTrace();
		  }		
	}
	 public void  listRoom(String city,String hotel){
		 	ArrayList<String> list=sit.listRoom(city,hotel);
		 	Iterator<String> it = list.iterator();
		 	
	        while(it.hasNext()){
	        	
	        	String str = it.next();
	        	   writer.print(str+Constants.CR_LF);
	        	 //  System.out.println(str);        
	        }
	        writer.print(Constants.CR_LF);
	 }
	 
	 public void checkRoomState(String in,String out,String hotel,String city,String type){
		int s= sit.checkRoomstate(in,out,hotel,city,type);
		
		writer.print(s+Constants.CR_LF);
	 }
	 public void insertInfo(String id, String name,String cno,String email){
		if( sit.insertInfo(id,name,cno,email)){
			writer.print("booking success!"+Constants.CR_LF);
		}
	 }
	 
 }
