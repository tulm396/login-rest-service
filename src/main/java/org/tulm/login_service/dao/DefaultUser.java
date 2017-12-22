/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tulm.login_service.dao;

/**
 *
 * @author tulm
 */
public class DefaultUser {
  
  public static final String USERNAME = "tulm";
  public static final String ADDRESS = "Binh Thuan";
  public static final String PASSWORD = "fa211678a7ce5d6b4852076b311e82e3";
  
  /**
   *
   * @return default username
   */
  public static String getUsername() {
    return USERNAME;
  }
  
  /**
   *
   * @param password
   * @return true if password matched
   */
  public static boolean comparePassword(String password) {
    return (PASSWORD.equals(password));
  }
  
  public static String getAddress() {
    return ADDRESS;
  }
}
