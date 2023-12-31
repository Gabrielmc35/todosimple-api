package com.gabrielmagalhaes.todosimple.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gabrielmagalhaes.todosimple.models.enums.ProfileEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSpringSecurity implements UserDetails{
    
    
    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;



    public UserSpringSecurity(Long id, String username, String password, Set<ProfileEnum> ProfileEnums) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = ProfileEnums.stream().map(x->new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
    }

    

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    


    public boolean hasRole(ProfileEnum profileEnum)
    {
        return getAuthorities().contains(new SimpleGrantedAuthority(profileEnum.getDescription()));

    }


}
