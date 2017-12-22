/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tulm.login_service.dao;

import org.tulm.login_service.model.User;

/**
 * Singleton
 * @author tulm
 */
public class UserDaoImpl implements UserDao {
  
  private static final UserDaoImpl INSTANCE = new UserDaoImpl();
  
  private UserDaoImpl() {
    // singleton
  }
  
  /**
   *
   * @return
   */
  public static UserDaoImpl getInstance() {
    return INSTANCE;
  }

  /**
   *
   * @param username
   * @return user if it have in database (fake)
   */
  @Override
  public User findUserByUsername(String username) {
    if(DefaultUser.getUsername().equals(username)) {
      return new User(DefaultUser.getUsername(), DefaultUser.getAddress());
    }
    
    return null;
  }
  
}
