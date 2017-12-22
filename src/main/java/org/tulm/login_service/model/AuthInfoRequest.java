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
public class AuthInfoRequest {
  private String username;
  private String password;
  
  public AuthInfoRequest() {
    // do nothing...
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public AuthInfoRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }
  
}
