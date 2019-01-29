package com.sane.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageUserDetailsService implements UserDetailsService {

    private List<UserDetails> userList = new ArrayList<>();
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private void initUserList() {
        GrantedAuthorityImpl userAdmin = new GrantedAuthorityImpl("admin", passwordEncoder.encode("admin"));
        userAdmin.setGrantedAuthorities(new ArrayList<GrantedAuthority>(){{
            add(() ->  "ROLE_ADMIN");
        }});

        GrantedAuthorityImpl userNormal = new GrantedAuthorityImpl("guest", passwordEncoder.encode("guest"));
        userAdmin.setGrantedAuthorities(new ArrayList<GrantedAuthority>(){{
            add(() ->  "ROLE_USER");
        }});

        userList.add(userAdmin);
        userList.add(userNormal);
    }

    public MessageUserDetailsService() {
        initUserList();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        if(!userList.stream().anyMatch(userDetails -> userDetails.getUsername().equals(userName))) {
            throw new UsernameNotFoundException("没有找到用户[" + userName + "]");
        }

        List<UserDetails> loginUserList = userList.stream().filter(userDetails -> userDetails.getUsername().equals(userName))
                .collect(Collectors.toList());

        if(loginUserList.isEmpty())
            throw new UsernameNotFoundException("没有找到用户[" + userName + "]");

        return loginUserList.get(0);
    }
}
