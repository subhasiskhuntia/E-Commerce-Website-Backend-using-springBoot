package com.ecom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecom.serviceImpl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable()
		.authorizeRequests().antMatchers("/helloadmin",
										"/products/storeProduct",
										"/api/category/saveCategory",
										"api/brand/saveBrand",
										"api/banner/setBanner",
										"/api/category/updateCategory",
										"/api/brand/updateBrand",
										"/api/brand/deleteBrand/{id}",
										"/api/category/deleteCategory/{id}",
										"/api/banner/deleteBanner/{id}",
										"/products/updateProduct",
										"/api/admin/salesInCategory",
										"/api/admin/salesInBrand"
										).hasRole("ADMIN")
		.antMatchers(
				HttpMethod.OPTIONS,
				"/hellouser",
				"sayHello",
				"/api/user/showCart",
				"/api/user/addToCart",
				"/api/user/updateCart",
				"/api/user/deleteCartItem",
				"/api/user/buyProduct",
				"/api/user/saveRecord",
				"/api/user/deleteAllCartItem",
				"api/user/getOrderDetails"
				).hasAnyRole("USER","ADMIN")
		.antMatchers("/authenticate",
					"/register",
					"/refreshtoken",
					"/api/category/loadDistinctCategory",
					"/api/brand/loadDistinctBrand",
					"/api/banner/showBanner",
					"/products/storeProduct",
					"/products/getFilterdProduct",
					"/products/searchProduct",
					"/products/getDistinctColor",
					"/products/findProductById/{id}",
					"/api/category/showCategory",
					"/api/category/categoryProduct/{id}",
					"/api/brand/perticularBrand/{id}",
					"/api/brand/showBrand",
					"/api/category/getDistinctCategory",
					"/api/brand/getDistinctBrand",
					"/api/gender/products/{id}",
					"/products/getAllProducts",
					"/api/user/getUserDetails",
					"/api/user/changeUserDetails",
					"/api/user/changePassword"
					).permitAll().anyRequest().authenticated()
		.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).
		and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and().addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v3/api-docs/**",
                                   "/swagger-ui/**",
                                   "/swagger-resources/**",
                                   "/configuration/security",
                                   "/swagger-ui.html",
                                   "/webjars/**");
    }



}
