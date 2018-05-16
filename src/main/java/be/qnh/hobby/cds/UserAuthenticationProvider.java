package be.qnh.hobby.cds;

import be.qnh.hobby.cds.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthenticationProvider.class);

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        if (this.userService.authenticate(username, password)) {

            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new AuthenticationTokenGrantedAuthority());

            LOGGER.debug("Validation succeeded for [{}]!", username);

            return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
        }
        else {
            LOGGER.error("Validation failed for [{}]", username);
            throw new AuthenticationTokenAuthenticationException(String.format("Validation failed for %s", username));
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}

    class AuthenticationTokenAuthenticationException extends AuthenticationException {

        private static final long serialVersionUID = -9139561336028106640L;

        public AuthenticationTokenAuthenticationException(String msg) {
            super(msg);
        }
    }

    class AuthenticationTokenGrantedAuthority implements GrantedAuthority {

        private static final long serialVersionUID = -4893914488018936401L;

        @Override
        public String getAuthority() {
            return "ROLE_"+"APIROLE";
        }
    }
