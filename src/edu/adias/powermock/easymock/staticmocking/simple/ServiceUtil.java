package edu.adias.powermock.easymock.staticmocking.simple;

public class ServiceUtil {
	
	public static String getFullName(){
		return ServiceUtil.getFirstName() +" "+ ServiceUtil.getLastName();
	}
	
	public static String getFullDetails(){
		return "SAIDA DHANAVATH " + ServiceUtil2.getFullAddress();
	}
	
	public static String getFirstName(){
		return "SAIDA";
	}
	
	public static String getLastName(){
		return "DHANAVATH";
	}

}
