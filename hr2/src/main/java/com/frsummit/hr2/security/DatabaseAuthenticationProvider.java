package com.frsummit.hr2.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("databaseAuthenticationProvider")
public class DatabaseAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
            throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(
            String s,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
            throws AuthenticationException {

        return null;
    }

//    @Override
//    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
//            throws AuthenticationException {
//
//        if (StringUtils.isEmpty(username)) {
//            setHideUserNotFoundExceptions(false);//Setting this will cause UsernameNotFoundExceptions to be thrown instead of BadCredentialsException
//            throw new UsernameNotFoundException("Enter your username.");
//        }
//
//        User user = userService.findUserByUsername(username);
//
//        String givenPassword = (String) authentication.getCredentials();
//        if (user == null || !user.getPassword().equals(givenPassword)) {
//            throw new BadCredentialsException("Incorrect username or password.");
//        }
//
//        return user;
//    }
}
