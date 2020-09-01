package com.ausy_technologies.demospring.Service;

import com.ausy_technologies.demospring.Model.DAO.Role;
import com.ausy_technologies.demospring.Model.DAO.User;
import com.ausy_technologies.demospring.Model.DTO.UserDto;
import com.ausy_technologies.demospring.Repository.RoleRepository;
import com.ausy_technologies.demospring.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    public Role saveRole(Role role) {


        return this.roleRepository.save(role);
    }


    public User saveUser(User user) {


        return this.userRepository.save(user);
    }

    public User saveUser2(User user ,int idRole)
    {

       Role role= this.roleRepository.findById(idRole).get();

       List<Role> roleList =new ArrayList<>();
       roleList.add(role);

       if(role!=null) {

           user.setRoleList(roleList);
           return this.userRepository.save(user);
       }
       else
       {
           throw new RuntimeException("Role not found");
       }

    }


    public User saveUser3(  User user ,List<Role> roleList)
    {
        user.setRoleList(roleList);
        return this.userRepository.save(user);

    }

    public Role findRoleById(int id)
    {
        return this.roleRepository.findById(id).get();

    }


    public UserDto findUserDtoById(int id){
        User user = findUserById(id);
        ModelMapper modelMapper = new ModelMapper();
        List<Role> roleList = user.getRoleList();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        List<String> userDtoRoleList = new ArrayList<>();
        for (Role role : roleList) {
            userDtoRoleList.add(role.getName());
        }
        userDto.setRoleList(userDtoRoleList);
        return userDto;
    }

    public List<UserDto> findAllUsersDto(){
        List<User> userList = findAllUsers();
        ModelMapper modelMapper = new ModelMapper();
        List<UserDto> userDtoList = new ArrayList<>();
        for(int i=0;i<userList.size();i++){
            List<Role> roleList = userList.get(i).getRoleList();
            List<String> userDtoRoleList = new ArrayList<>();
            UserDto userDto = modelMapper.map(userList.get(i), UserDto.class);
            for(Role role: roleList){
                userDtoRoleList.add(role.getName());
            }
            userDto.setRoleList(userDtoRoleList);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    public List<Role> findAllRoles()
    {
        return this.roleRepository.findAll();
    }


    public List<User> findAllUsers()
    {
        return this.userRepository.findAll();
    }

    public User findUserById(int id){
        return this.userRepository.findById(id);
    }


    public Role updateRoleName(int id, Role name){
        Role role = this.roleRepository.findById(id).get();
        role.setName(name.getName());
        roleRepository.save(role);
        return role;
    }

    public User updateUser(int id, List<Role> roleList , User user){
        User userModified = this.userRepository.findById(id);
        userModified.setFirstName(user.getFirstName());
        userModified.setLastName(user.getLastName());
        userModified.setBirthday(user.getBirthday());
        userModified.setEmail(user.getEmail());
        userModified.setUsername(user.getUsername());
        userModified.setRoleList(roleList);
        userRepository.save(userModified);
        return userModified;
    }


    public void deleteUserById(int id)
    {
         this.userRepository.deleteById(id);
    }

    public void deleteRoleById(int id){ roleRepository.deleteById(id);}




}
