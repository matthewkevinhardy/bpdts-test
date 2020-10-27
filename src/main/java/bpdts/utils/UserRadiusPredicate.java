package bpdts.utils;

import java.util.function.Predicate;

import bpdts.model.User;

public class UserRadiusPredicate {
	
	public static final double DEGREES_TO_MILES = 69.2;
	
	public static boolean isWithinRadius(User user,double lat, double lng, double maxRadiusMiles) {
		double x = user.getLatitude()-lat;
		double y = user.getLongitude()-lng;
		double radiusMiles = Math.sqrt(x*x+y*y)*DEGREES_TO_MILES;
		
		if(radiusMiles<=maxRadiusMiles) {
			return true;
		}
		return false;
	}
	
	public static Predicate<User> isWithinRadius(double lat, double lng, double radMiles) {
		return u -> isWithinRadius(u, lat, lng, radMiles);
	}
}
