/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tulm.login_service.dao;

import org.tulm.login_service.model.User;

/**
 *
 * @author tulm
 */
public interface UserDao {
  User findUserByUsername(String username);
}
