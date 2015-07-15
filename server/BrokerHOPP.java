package server;
import java.io.*;

import java.net.*;
import java.util.ArrayList;
//import java.util.Iterator;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.json.JSONTokener;


import constants.Constants;


public class BrokerHOPP{
	private Socket hiltonSocket ;
	private Socket jjSocket;
	private Socket regentSocket;
	private BufferedReader hiltonIn;
    private PrintWriter hiltonOut;
    private BufferedReader jjIn;
    private PrintWriter jjOut;
    private BufferedReader regentIn;
    private PrintWriter regentOut;
	private String com = null;
	//private JSONObject js =null;
	public BrokerHOPP(String com){
		this.com=com;
		try{
			
			hiltonSocket = new Socket(InetAddress.getLocalHost(),Constants.HILTONPORT);
			hiltonIn=new BufferedReader(new InputStreamReader(hiltonSocket.getInputStream()));
			hiltonOut=new PrintWriter(new BufferedWriter(new OutputStreamWriter(hiltonSocket.getOutputStream())),true);
			
			jjSocket = new Socket(InetAddress.getLocalHost(),Constants.JJPORT);
			jjIn=new BufferedReader(new InputStreamReader(jjSocket.getInputStream()));
			jjOut=new PrintWriter(new BufferedWriter(new OutputStreamWriter(jjSocket.getOutputStream())),true);
			
			regentSocket = new Socket(InetAddress.getLocalHost(),Constants.REGENTPORT);
			regentIn=new BufferedReader(new InputStreamReader(regentSocket.getInputStream()));
			regentOut=new PrintWriter(new BufferedWriter(new OutputStreamWriter(regentSocket.getOutputStream())),true);
		}catch(UnknownHostException e){
      		e.printStackTrace();
      	}catch (IOException e) {
      		e.printStackTrace();
      	}

	}
	public ArrayList<String> listRoom(String para){
		if(para.contains(Constants.Hilton)){
			
			sendRequest(com,para,hiltonOut);
			
			return getListRequest(hiltonIn);
		}
		
		else if(para.contains(Constants.Jinjiang)){							
			sendRequest(com,para,jjOut);
			return getListRequest(jjIn);
			
		}
		else if(para.contains(Constants.Regent)){
			sendRequest(com,para,regentOut);
			return getListRequest(regentIn);
			
			//return jArray;
		}
		else return null;
		
	
//    	
//    	 
//    	
//    	
//    	JSONArray jArray = new JSONArray();
//		..
//		return jArray;
		
	}
	
public int bookRoom(String para){
		
		if(para.contains(Constants.Hilton)){
			
			sendRequest(com,para,hiltonOut);
			
			return getBookRequest(hiltonIn);
		}
		
		else if(para.contains(Constants.Jinjiang)){							
			sendRequest(com,para,jjOut);
			return getBookRequest(jjIn);
			
		}
		else if(para.contains(Constants.Regent)){
			sendRequest(com,para,regentOut);
			return getBookRequest(regentIn);
			
			//return jArray;
		}
		else return -1;
			
	}


 	public String insertInfo(String para){

 		if(para.contains(Constants.Hilton)){
			
			sendRequest(com,para,hiltonOut);
			
			return getInsertRequest(hiltonIn);
		}
		
		else if(para.contains(Constants.Jinjiang)){							
			sendRequest(com,para,jjOut);
			return getInsertRequest(jjIn);
			
		}
		else if(para.contains(Constants.Regent)){
			sendRequest(com,para,regentOut);
			return getInsertRequest(regentIn);
			
			//return jArray;
		}
		else return null;
			
 		
 		
 		
 	}
	public boolean sendRequest(String com, String para,PrintWriter out){
		
		out.println(com+","+para+Constants.CR_LF);
		return true;
	}
	public ArrayList<String> getListRequest(BufferedReader in){
		
			 ArrayList<String> tempList = new ArrayList<String>();
			    String line = null;
			    while (true) {
			      try {
			        line = in.readLine();
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
	public int getBookRequest(BufferedReader in){
		String line =null;
		  try{
			  line = in.readLine();
		  }catch(IOException e){
			  e.printStackTrace();
		  }
		return   Integer.parseInt(line);
		   
		     
		
	}
	public String getInsertRequest(BufferedReader in){
		String line =null;
		  try{
			  line = in.readLine();
		  }catch(IOException e){
			  e.printStackTrace();
		  }
		  return line;
	}



}