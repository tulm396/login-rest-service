/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tulm.login_service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author tulm
 */
//@Provider
public class AuthFilter implements ContainerRequestFilter {

  @Context
  ResourceInfo resInfo;
  
  @Context
  private HttpServletRequest servletRequest;

  /**
   *
   * @param crc
   * @throws IOException
   */
  @Override
  public void filter(ContainerRequestContext crc) throws IOException {
    Method method = resInfo.getResourceMethod();

    if (!method.isAnnotationPresent(PermitAll.class)) {
      try {
        String token = crc.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        // Null meaning client not transfered token to header
        if (token == null) {
          throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        Algorithm algorithm = Algorithm.HMAC256("1234567890");
        
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        
        // Put username to request for using in resource
        this.servletRequest.setAttribute("username", jwt.getClaim("username").asString());
      } catch (JWTVerificationException exception) {
        // Verify failed
        throw new WebApplicationException(Response.Status.BAD_REQUEST);
      }

    }
  }

}
