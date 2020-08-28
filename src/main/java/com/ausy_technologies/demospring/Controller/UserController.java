package com.ausy_technologies.demospring.Controller;

import com.ausy_technologies.demospring.Model.DAO.Role;
import com.ausy_technologies.demospring.Model.DAO.User;
import com.ausy_technologies.demospring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addRole")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {


        Role roleAdded = this.userService.saveRole(role);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "addedNewRole");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(roleAdded);
    }


    @PostMapping("/addUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User userAdded = this.userService.saveUser(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "addedNewUser");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(userAdded);
    }

    @PostMapping("/addUser2/{idRole}")
    public ResponseEntity<User> saveUser2(@RequestBody User user, @PathVariable int idRole)
    {
        User userAdded =  this.userService.saveUser2(user,idRole);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "addedNewUser");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(userAdded);

    }

    @PostMapping("/addUser3/{roleList}")
    public ResponseEntity<User> saveUser3(@RequestBody User user , @PathVariable List<Role> roleList)
    {
        User userAdded = this.userService.saveUser3(user,roleList);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "addedNewUser");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(userAdded);
    }

    @GetMapping("/findRoleBy/{id}")
    public ResponseEntity<Role> findRoleById(@PathVariable int id)
    {
        Role role = this.userService.findRoleById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "action-completed");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(role);

    }


    @GetMapping("/findAllRoles")
    public ResponseEntity<List<Role>> findAllRoles()
    {
        List<Role> roles =  userService.findAllRoles();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "action-completed");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(roles);
    }


    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> findAllUsers()
    {
        List<User> users = this.userService.findAllUsers();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "action-completed");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(users);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUser(@PathVariable int id)
    {
        this.userService.deleteUserById(id);

    }

    @DeleteMapping("/deleteRoleById/{id}")
    public void deleteRoleById(@PathVariable int id){
        this.userService.deleteRoleById(id);
    }

    @PutMapping("/updateRoleName/{id}")
    public ResponseEntity<Role> updateRoleName(@PathVariable int id, @RequestBody Role name){
        Role role = this.userService.updateRoleName(id, name);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "role-updated");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(role);
    }

    @PutMapping("/updateRoleName2/{id}")
    public Role updateRoleName2(@PathVariable int id, @RequestBody Role name){
        return this.userService.updateRoleName(id, name);
    }

    @PutMapping("/updateUser/{id}/{roleList}")
    public ResponseEntity<User> updateUser(@PathVariable int id,@PathVariable List<Role> roleList, @RequestBody User user){
        User userUpdated = this.userService.updateUser(id, roleList, user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "user-updated");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(userUpdated);

    }


}
