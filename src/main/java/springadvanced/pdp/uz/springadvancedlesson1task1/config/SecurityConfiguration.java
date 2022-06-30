package springadvanced.pdp.uz.springadvancedlesson1task1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("director").password(passwordEncoder().encode("director")).roles("DIRECTOR")
                .authorities("READ_ALL_ADDRESS", "READ_ONE_ADDRESS", "ADD_ADDRESS", "EDIT_ADDRESS", "DELETE_ADDRESS")
                .and()
                .withUser("accountant").password(passwordEncoder().encode("accountant")).roles("ACCOUNTANT")
                .authorities("DELETE_ADDRESS", "READ_ALL_ADDRESS", "READ_ONE_ADDRESS")
                .and()
                .withUser("others").password(passwordEncoder().encode("others")).roles("OTHERS")
                .authorities("READ_ALL_ADDRESS", "READ_ONE_ADDRESS");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/adddress/*").hasRole( "OTHERS")
//                .antMatchers(HttpMethod.POST, "/api/adddress/**").hasAnyRole("DIRECTOR", "ACCOUNTANT")
//                .antMatchers( "/api/adddress/*").hasRole("DIRECTOR")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        UserDetails director = User.withDefaultPasswordEncoder()
//                .username("director")
//                .password("director")
//                .roles("DIRECTOR")
//                .build();
//        return new InMemoryUserDetailsManager(user, director);
//    }
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authz) -> authz
//                        .anyRequest().authenticated()
//                )
//                .csrf().disable()
//                .httpBasic(withDefaults());
//        return http.build();
//    }

}
