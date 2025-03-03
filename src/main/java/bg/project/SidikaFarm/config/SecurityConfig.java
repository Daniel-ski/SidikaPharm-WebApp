package bg.project.SidikaFarm.config;

import bg.project.SidikaFarm.model.entity.enums.RoleType;
import bg.project.SidikaFarm.repository.UserRepository;
import bg.project.SidikaFarm.service.impl.AppUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

       return httpSecurity.authorizeHttpRequests(authorizeRequest ->
                authorizeRequest
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/","/login","/register","/admin/**").permitAll()
                        .requestMatchers("/user-profile/**").authenticated()
//                        .requestMatchers("/admin/**").hasRole(RoleType.ADMIN.name())
                        .anyRequest().authenticated()
        ).formLogin(formLogin ->
                formLogin.loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/user-profile/management",true)
                        .failureForwardUrl("/login"))

                .logout(logout ->
                        logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                ).build();


    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new AppUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
