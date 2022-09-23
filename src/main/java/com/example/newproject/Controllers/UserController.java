package com.example.newproject.Controllers;

import com.example.newproject.Enums.Role;
import com.example.newproject.Repository.UserRepository;
import com.example.newproject.data.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
public class UserController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
        private List<User> getUsers() {
            List<User> allUsers = userRepository.findAll();
            return allUsers;
    }

    @PostMapping("")
        private void addNewUser(@RequestBody User newUser) {
           newUser.setCreatedAt(LocalDate.now());
           newUser.setRole(Role.USER);
           String pw =  newUser.getPassword();
           pw = passwordEncoder.encode(pw);
           newUser.setPassword(pw);

            userRepository.save(newUser);
        }

    @GetMapping("/{id}")
    public Optional<User> fetchUserById(@PathVariable long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + " not found");
        }
        return optionalUser;
    }

    @GetMapping("/me")
    private Optional<User> fetchMe(OAuth2Authentication auth) {
        if(auth ==  null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please login");
        }
        String userName = auth.getName();
        User user = userRepository.findByUserName(userName);
        return Optional.of(user);
    }

    @DeleteMapping("/{id}")
        private void deleteUser(@PathVariable Long id) {
            userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
        private void editAUser(@PathVariable Long id, @RequestBody User updatedUser) {
            updatedUser.setId(id);
            userRepository.save(updatedUser);
    }

    @PutMapping("/{id}/updatePassword")
        private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword) {
            Optional<User> checkIfUserExist = userRepository.findById(id);
            if (checkIfUserExist.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + "does not exit");
            }

            User user = checkIfUserExist.get();

            if (!user.getPassword().equals(oldPassword))  {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "New password cannot be the same as old password");
            }

            if (newPassword.length() < 3) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password has to be at least 3 characters");
            }

            user.setPassword(newPassword);
            userRepository.save(user);
    }

//    @GetMapping("/api/users/username")
//        private User getByUsername(@RequestParam String userName) {
//            userRepository.findOne(userName)
//    }


}
