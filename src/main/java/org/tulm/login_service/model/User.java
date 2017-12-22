/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tulm.login_service.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tulm
 */
@XmlRootElement
public class User {
  private String username;
  private String address;

  public User(String username, String address) {
    this.username = username;
    this.address = address;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
  
}
