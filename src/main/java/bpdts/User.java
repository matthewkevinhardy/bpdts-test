package bpdts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	@JsonProperty("id")
	private long id;
    
	@JsonProperty("first_name")
	private String firstName;
    
    @JsonProperty("last_name")
    private String lastName; 
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("ip_address")
    private String ipAddress;
    
    @JsonProperty("latitude")
    private double latitude;
    
    @JsonProperty("longitude")
    private double longitude;
	
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	private static final double DEGREES_TO_MILES = 69.2;
	
	public boolean isWithinRadius(double lat, double lng, double maxRadiusMiles) {
		double x = this.latitude-lat;
		double y = this.longitude-lng;
		double radiusMiles = Math.sqrt(x*x+y*y)*DEGREES_TO_MILES;
		
		if(radiusMiles<=maxRadiusMiles) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", ipAddress=" + ipAddress + ", latittude=" + latitude + ", longitude=" + longitude + "]";
	}
	@Override
	public int hashCode() {
		return Long.hashCode(this.id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
    
}
