package com.practikum.naumen.configs;


import com.practikum.naumen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    /* Шифрование пароля при создании учетной записи */
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    /* Пароль при создании учетной записи НЕ шифруется */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                //     .antMatchers("/css/**","/js/**").permitAll()
          //      .antMatchers("/").permitAll()
                .antMatchers("/staff-request/","/staff-request/**","/staff-status","/staff-filter-request").hasRole("STAFF")
                .antMatchers("/admin","/admin/**","/admin-request","/admin-status","/admin-account","/filter-staff","/filter-request","/filter-request-from-whom").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");
    }

    /* Шифрование пароля при создании учетной записи */

//    @Autowired
//    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
//    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }


    /* Разрешаем Spring Security допускать следующие директории */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**");

    }
}


