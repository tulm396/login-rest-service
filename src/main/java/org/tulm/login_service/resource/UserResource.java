/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tulm.login_service.resource;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import org.tulm.login_service.dao.DefaultUser;
import org.tulm.login_service.dao.UserDaoImpl;
import org.tulm.login_service.model.AuthInfoRequest;
import org.tulm.login_service.model.User;

/**
 *
 * @author tulm
 */
@Path("/users")
public class UserResource {
  
  @GET
  @Path("/test")
  @PermitAll
  @Produces(MediaType.APPLICATION_JSON)
  public Response testMessage() {
    Map<String, Object> resData = new HashMap<>();
    resData.put("message", "Hello, world!");
    return Response.status(Response.Status.OK).entity(resData).build();
  }
  
  @POST
  @Path("/authenticate")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @PermitAll
  public Response authenticate(AuthInfoRequest loginUserInfo)
          throws UnsupportedEncodingException {
    if (loginUserInfo != null &&
        loginUserInfo.getUsername().equals(DefaultUser.getUsername()) &&
        DefaultUser.comparePassword(loginUserInfo.getPassword())) {
      
      // Create jwt string
      Algorithm algorithm = Algorithm.HMAC256("1234567890");
      String token = JWT.create()
              .withClaim("username", loginUserInfo.getUsername())
          .sign(algorithm);
      
      // Create response data
      Map<String, Object> resData = new HashMap<>();
      resData.put("token", token);
      
      // Response
      return Response.status(Response.Status.OK).entity(resData).build();
    } else {
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
    
  }
  
  @GET
  @Path("/profile")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUserProfile(@Context HttpServletRequest request) {
    String username = (String) request.getAttribute("username");
    User user = UserDaoImpl.getInstance().findUserByUsername(username);
    
    if (user == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    
    return Response.status(Response.Status.OK).entity(user).build();
  }
  
}
