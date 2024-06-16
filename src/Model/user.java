/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class user {
    protected String userId;
    protected String name;
    protected String email;
    protected String type;
    protected String password;

    public user(String userId, String name, String email,String type,String password) {	
	this.userId = userId;
	this.name = name;
	this.email = email;
        this.type = type;
	this.password = password;
    }
   
    public String getUserId() {
	return userId;
    }
    public String getName() {
	return name;
    }
    public String getEmail() {
	return email;
    }
    
    public String getPassword() {
	return password;
    }
    public String gettype() {
	return type;
    }
}
