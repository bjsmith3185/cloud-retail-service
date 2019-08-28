package com.company.adminservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource ds;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
        PasswordEncoder enc = new BCryptPasswordEncoder();

        authBuilder.jdbcAuthentication()
                .dataSource(ds)
                .usersByUsernameQuery(
                        "select username,password,enabled from users where username = ?")
                .authoritiesByUsernameQuery(
                        "select username,authority from authorities where username = ?")
                .passwordEncoder(enc);
    }

    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()
                .mvcMatchers("/loggedin").authenticated()
                //        Any staff member can update a product and CRUD on invoice
                //        CRUD on invoice
                .mvcMatchers(HttpMethod.GET,"/loggedin", "/administration/customers", "/administration/customers", "/administration/inventory", "/administration/inventory/{id}", "/administration/invoices", "/administration/invoices/{id}", "/administration/levelup", "/administration/levelup/{id}", "/administration/products", "/administration/products/{id}").hasAuthority("ROLE_USER")
                .mvcMatchers(HttpMethod.PUT,"/administration/inventory/{id}" ).hasAuthority("ROLE_USER")
                .mvcMatchers(HttpMethod.PUT, "/administration/customers/{id}", "/administration/invoices/{id}", "/administration/levelup/{id}", "/administration/products/{id}").hasAuthority("ROLE_TEAMLEAD")
                .mvcMatchers(HttpMethod.POST,"/administration/customers" ).hasAuthority("ROLE_TEAMLEAD")
                //        Only Managers and above can create new products and delete an invoice
                .mvcMatchers(HttpMethod.POST,"/administration/inventory", "/administration/invoices", "/administration/levelup", "/administration/products").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.DELETE,"/administration/customers/{id}", "/administration/inventory/{id}", "/administration/invoices/{id}", "/administration/levelup/{id}", "/administration/products/{id}").hasAuthority("ROLE_ADMIN");
                //        Only Admin users can delete a product.

        httpSecurity
                .logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/admin")
                .deleteCookies("JSESSIONID")
                .deleteCookies("XSRF-TOKEN")
                .invalidateHttpSession(true);

        httpSecurity
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }
}
