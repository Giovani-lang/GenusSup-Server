package com.genus.GENUS_SUP.Security;


import com.genus.GENUS_SUP.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepo.findUserByUsername(username)
                .map(com.genus.GENUS_SUP.Security.UserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("Could not find ! Try again."));
    }
}
