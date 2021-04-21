package mk.ukim.finki.wp.project.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
    ROLE_USER, ROLE_ADMIN;
    //ROLE_SPECIAL

    @Override
    public String getAuthority() {
        return name();
    }
}