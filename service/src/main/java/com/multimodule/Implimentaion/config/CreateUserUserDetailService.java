package com.multimodule.Implimentaion.config;

import com.multimodule.domain.model.CreateUser;
import com.multimodule.domain.repository.CreateUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateUserUserDetailService implements UserDetailsService {
    @Autowired
    private CreateUserRepository createUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CreateUser> createUser = createUserRepository.findByName(username);
        return createUser.map(CreateUserUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user name not found " + username));
    }
}
