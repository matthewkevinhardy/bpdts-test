package bpdts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	private long id;
    
	@JsonProperty("first_name")
	private String firstName;
    
    @JsonProperty("last_name")
    private String lastName; //"last_name": "Boam",
    
    private String email;//": "mboam3q@thetimes.co.uk",
    
    @JsonProperty("ip_address")
    private String ipAddress;///"ip_address": "113.71.242.187",
    
    private double latitude;//"latitude": -6.5115909,
    
    private double longitude;//"longitude": 105.652983
	
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
