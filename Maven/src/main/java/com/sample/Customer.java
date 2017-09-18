package com.sample;
import java.io.Serializable;
 
public class Customer implements Serializable{
 
    private static final long serialVersionUID = 1L;
    /*private final String username= "user1";
	private final String password= "helloworld";*/
    
	private String userid;
    private String name;
    private String address;
    private Long mobile;
    private String emailid;
    
	public Customer(String userid, String name, String address, Long mobile,
			String emailid) {
		this.userid = userid;
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.emailid = emailid;
	}
    public Customer() {
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Long getMobile() {
        return mobile;
    }
    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }
    public String getEmailid() {
        return emailid;
    }
    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
     
}