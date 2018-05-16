package be.qnh.hobby.cds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableGlobalMethodSecurity
@EnableWebSecurity
// see
// http://docs.spring.io/spring-security/site/docs/3.2.x/reference/htmlsingle/#multiple-httpsecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String APIROLE = "APIROLE";

    private UserAuthenticationProvider uap;

    @Autowired
    public SecurityConfig(UserAuthenticationProvider uap) {
        this.uap = uap;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                @Value("${api.username}") String apiUsername,
                                @Value("${api.password}") String apiPassword) throws Exception{

        auth.inMemoryAuthentication().withUser(apiUsername).password(apiPassword).roles(APIROLE);
        auth.authenticationProvider(uap);
    }

    @Configuration
    @Order(1)
    public static class AuWebSecurityAdapterRest extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.authorizeRequests().antMatchers("/api/**").hasRole(APIROLE).and().httpBasic();

            http.authorizeRequests().anyRequest().permitAll();

            // the rest is implicit denied
        }
    }
}