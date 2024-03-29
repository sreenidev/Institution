package com.multimodule.application.controller;

import com.multimodule.Implimentaion.userException.UserException;
import com.multimodule.domain.model.User;
import com.multimodule.domain.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    //create form
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public User createForm(@RequestBody User user) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
        String formattedDate = dateFormat.format(new Date());
        user.setCreatedDate(formattedDate);
        user.setStatus("Pending");
        user.setStatusMessage("Application is submitted by user");
        return userRepository.save(user);
    }

    //fetch all the details
    @GetMapping("/fetchAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> fetchAllDetails() {
        return userRepository.findAll();
    }

    //fetch by name
    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<User>> fetchDetailsByName(@PathVariable String name) {
        List<User> schoolDTO = userRepository.findByName(name);
        if (schoolDTO.isEmpty()) {
            throw new UserException(name + "not found");
        }
        return ResponseEntity.ok(schoolDTO);
    }

    //fetch by phone Number
    @GetMapping("/phone/{phone}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<User> fetchDetailsByPhone(@PathVariable Long phone) {
        User schoolDTO = userRepository.findByPhone(phone).orElseThrow(() -> new UserException(phone + " not found"));
        return ResponseEntity.ok(schoolDTO);
    }

    //fetch by percentage
    @GetMapping("/percent/{percentage}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<User> fetchDetailsByPercent(@PathVariable Double percentage) {
        User schoolDTO = userRepository.findByPercentage(percentage).orElseThrow(() -> new UserException(percentage + " not found"));
        return ResponseEntity.ok(schoolDTO);
    }

    //update details by phone
    @PutMapping("/update/{phone}/{updateMessgage}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<User> updateDetailsByPhone(@PathVariable Long phone, @PathVariable String updateMessgage, @RequestBody User userDetails) {
        User user = userRepository.findByPhone(phone).orElseThrow(() -> new UserException(phone + " not found"));
        String existingCreatedDate = user.getCreatedDate();
        String existingStatus = user.getStatus();
        int existingUpdateCount = user.getUpdateCount();
        if (existingUpdateCount == 5) {
            throw new UserException(" You can't update more than 5 times ");
        }
        modelMapper.map(userDetails, user);
        user.setUpdateCount(existingUpdateCount + 1);
        user.setCreatedDate(existingCreatedDate);
        user.setStatus(existingStatus);
        user.setUpdateMessage(updateMessgage);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    //delete form by phone
    @DeleteMapping("/delete/{phone}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<User> deleteEmployee(@PathVariable Long phone) {
        User schoolDTO = userRepository.findByPhone(phone).orElseThrow(() -> new UserException(phone + "not found"));
        userRepository.delete(schoolDTO);
        return ResponseEntity.noContent().build();
    }
    //update details by phone
    @PutMapping("/admin/updateStatus/{phone}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<User> updateStatusByPhone(@PathVariable Long phone, @RequestBody User userDetails) {
        User user = userRepository.findByPhone(phone).orElseThrow(() -> new UserException(phone + " not found"));
        String existingName = user.getName();
        int existingAge = user.getAge();
        String existingAddress = user.getAddress();
        String existingEmail = user.getEmail();
        String existingEducation = user.getEducation();
        String existingSchool = user.getSchool();
        String existingParentsName = user.getParentsName();
        Double existingPercentage = user.getPercentage();
        String existingCreatedDate = user.getCreatedDate();
        String existingUpdateMessage = user.getUpdateMessage();
        int existingUpdateCount = user.getUpdateCount();

        modelMapper.map(userDetails, user);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
        String formattedDate = dateFormat.format(new Date());

        user.setName(existingName);
        user.setAge(existingAge);
        user.setAddress(existingAddress);
        user.setEmail(existingEmail);
        user.setEducation(existingEducation);
        user.setSchool(existingSchool);
        user.setParentsName(existingParentsName);
        user.setPercentage(existingPercentage);
        user.setCreatedDate(existingCreatedDate);
        user.setApprovalDate(formattedDate);
        user.setUpdateMessage(existingUpdateMessage);
        user.setUpdateCount(existingUpdateCount);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}
