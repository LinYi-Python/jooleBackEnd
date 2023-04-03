package com.itlizeSession.joole.Controller;

import com.itlizeSession.joole.Entity.*;
import com.itlizeSession.joole.Service.*;

import com.itlizeSession.joole.Util.JwtUtil;
import com.itlizeSession.joole.Vo.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ProductController
 * @Description TODO
 * @Author Yi Lin
 * @Date 5/17/22 13:34
 * @Version 1.0
 **/
@RestController
@RequestMapping("/userController")
public class UserController {

     @Autowired
     private AuthenticationManager myauthenticaitonManager;

     @Autowired
     private UserService userService;

     @Autowired
     private JwtUtil jwtTokenUtil;

     @Autowired
     private MyUserDetailsService userDetailsService;

//     @PostMapping("/createUser")
//     public ResponseEntity<?> register(@RequestParam("username") String username,
//               @RequestParam("password") String password) {
//          if (userService.findByUsername(username) != null) {
//               return new ResponseEntity<>(HttpStatus.CONFLICT);
//          }
//          User user = new User(username, password);
//          user.setRole(Role.ROLE_USER);
//          return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
//     }
     //change
     @PostMapping("/createUser")
     @ResponseBody
     public ResponseEntity<?> register(@RequestBody User authData
                                       ) {
          String username = authData.getUsername();
          String password = authData.getPassword();
          if (userService.findByUsername(username) != null) {
               return new ResponseEntity<>(HttpStatus.CONFLICT);
          }
          User user = new User(username, password);
          user.setRole(Role.ROLE_USER);
          return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
     }

     @PostMapping("/createAdmin")
     public ResponseEntity<?> adminRegiter(@RequestParam("username") String username,
                                           @RequestParam("password") String password) {
          if (userService.findByUsername(username) != null) {
               return new ResponseEntity<>(HttpStatus.CONFLICT);
          }
          User user = new User(username, password);
          user.setRole(Role.ROLE_ADMIN);
          return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
     }

     @PostMapping("/createManufacturer")
     public ResponseEntity<?> manufacturerRegiter(@RequestParam("username") String username,
                                           @RequestParam("password") String password) {
          if (userService.findByUsername(username) != null) {
               return new ResponseEntity<>(HttpStatus.CONFLICT);
          }
          User user = new User(username, password);
          user.setRole(Role.ROLE_MANUFACTURER);
          return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
     }




//     @PostMapping("/usersCreate")
//     public ResponseEntity<?> createUser(@RequestParam("username") String username,
//               @RequestParam("password") String password) {
//
//          boolean createUser = userService.createUser(username, password);
//
//          return new ResponseEntity<>(createUser, HttpStatus.OK);
//     }

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     @PostMapping("/admin/usersDelete")
     public ResponseEntity<?> delete(@RequestParam("password") String password,
               @RequestParam("id") int id) {

          boolean delete = userService.delete(password, id);

          return new ResponseEntity<>(delete, HttpStatus.OK);
     }

     @PreAuthorize("hasRole('ROLE_USER')")
     @GetMapping("/user/usersFindUserById")
     public ResponseEntity<?> userFindUserById(@RequestParam("id") int id) {

          User user = userService.findUserById(id);

          return new ResponseEntity<>(user, HttpStatus.OK);
     }

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     @GetMapping("/admin/usersFindUserById")
     public ResponseEntity<?> adminFindUserById(@RequestParam("id") int id) {

          User user = userService.findUserById(id);

          return new ResponseEntity<>(user, HttpStatus.OK);
     }

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     @GetMapping("/admin/FindAllUser")
     public ResponseEntity<?> findAllUser(){
          List<User> users = userService.findAllUsers();
          return new ResponseEntity<>(users, HttpStatus.OK);
     }

//     @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
//     public ResponseEntity<?> createAuthenticationToken(@RequestParam(name="username") String username,
//                                                        @RequestParam(name="password") String password)
//     //@RequestBody User User)
//             throws Exception {
//
//          try {
//               myauthenticaitonManager.authenticate(
//                       new UsernamePasswordAuthenticationToken(username,password)//User.getUsername(), User.getPassword())
//               );
//          }
//          catch (BadCredentialsException e) {
//               throw new Exception("Incorrect username or password", e);
//          }
//
//
//          final UserDetails userDetails = userDetailsService
//                  .loadUserByUsername(username);
//
//          final String jwt = jwtTokenUtil.generateToken(userDetails);
//
//          return  new ResponseEntity<>(jwt, HttpStatus.OK);
////
//     }

     @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<?> createAuthenticationToken(@RequestBody User authData)
          //@RequestBody User User)
             throws Exception {
          String username = authData.getUsername();
          String password = authData.getPassword();

          try {
               myauthenticaitonManager.authenticate(
                       new UsernamePasswordAuthenticationToken(username,password)//User.getUsername(), User.getPassword())
               );
          }
          catch (BadCredentialsException e) {
               throw new Exception("Incorrect username or password", e);
          }


          final UserDetails userDetails = userDetailsService
                  .loadUserByUsername(username);

          final String jwt = jwtTokenUtil.generateToken(userDetails);
          Map<String, Object> map = new HashMap<>();
          map.put("token", jwt);
//          return  new ResponseEntity<>(jwt, HttpStatus.OK);
//          JWT jwtObject = new JWT(jwt);
          return  new ResponseEntity<>(map, HttpStatus.OK);
//          return  new ResponseEntity<>(jwtObject, HttpStatus.OK);


     }

//     @GetMapping("/usersLogin")
//     public ResponseEntity<?> userLogin(@RequestParam("username") String username,
//               @RequestParam("password") String password) {
//
//          boolean userLogin = userService.userLogin(username, password);
//
//          return new ResponseEntity<>(userLogin, HttpStatus.OK);
//     }
     @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANUFACTURER', 'ROLE_USER')")
     @PostMapping("/passwordUpdate")
     public ResponseEntity<?> passwordUpdate (@RequestParam("id") Integer id,
                                    @RequestParam("oldPassword") String oldPassword ,
                                         @RequestParam("newPassword") String newPassword) {
          boolean result = userService.savePassword(id, oldPassword, newPassword);
          return new ResponseEntity<>(result, HttpStatus.OK);
     }

     @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANUFACTURER', 'ROLE_USER')")
     @PostMapping("/user/usersUpdate")
     public ResponseEntity<?> updateUser(@RequestParam("id") Integer id,
                                         @RequestParam("username") String username ,@RequestParam("name") String name,
               @RequestParam("profilePic") String profilePictureUrl) {

          Timestamp updateTime = new Timestamp(System.currentTimeMillis());
//          User user = userService.findUserById(id);
          User user = new User();
          user.setId(id);
          user.setUsername(username);
          user.setName(name);
          user.setProfilePictureUrl(profilePictureUrl);
          user.setUpdateTime(updateTime);
//          userService.updateUser(user);
//          User user = new User(userName, password, name, profilePictureUrl, time, time);

          boolean updateUser = userService.updateUser(user);

          return new ResponseEntity<>(updateUser, HttpStatus.OK);
     }


     @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANUFACTURER', 'ROLE_USER')")
     @GetMapping("/usersLogout")
     public ResponseEntity<?> userLogout() {

          boolean userLogout = userService.userLogout();

          return new ResponseEntity<>(userLogout, HttpStatus.OK);
     }

//     @PreAuthorize("hasRole('ROLE_ADMIN')")
//     @GetMapping("/admin/usersLogout")
//     public ResponseEntity<?> adminLogout() {
//
//          boolean userLogout = userService.userLogout();
//
//          return new ResponseEntity<>(userLogout, HttpStatus.OK);
//     }

}
