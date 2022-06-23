package com.ecom;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ecom.dao.ProductBrandRepo;
import com.ecom.dao.ProductCategoryRepo;
import com.ecom.dao.ProductRepository;
import com.ecom.entity.Product;
import com.ecom.entity.ProductBrand;
import com.ecom.entity.ProductCategory;
import com.ecom.entity.ProductDiscount;
import com.ecom.serviceImpl.ProductBrandServiceImpl;
import com.ecom.serviceImpl.ProductCategoryServiceImpl;
import com.ecom.serviceImpl.ProductDiscountServiceImpl;
import com.ecom.serviceImpl.ProductService;

@SpringBootApplication
public class ECommerceWebsiteApplication implements CommandLineRunner{
	@Autowired
	ProductCategoryServiceImpl categoryServiceImpl;
	@Autowired
	ProductBrandServiceImpl brandServiceImpl;
	@Autowired
	ProductDiscountServiceImpl discountServiceImpl;
	@Autowired
	ProductRepository productrepo;
	@Autowired
	ProductCategoryRepo catrepo;
	@Autowired
	ProductBrandRepo brandrepo;
	@Autowired
	ProductService productService;
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(ECommerceWebsiteApplication.class, args);
		System.out.println("running ");
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		ProductCategory category=ProductCategory.builder().id(29l).build();
//		boolean result =categoryServiceImpl.categoryExistOrNot(category);
//		System.out.println(result);
//		ProductBrand brand=ProductBrand.builder().name("Being Human").build();
//		ProductBrand brand2=brandServiceImpl.findById(29);
//		System.out.println(brandServiceImpl.brandExistOrNot(brand));
//		ProductDiscount discount =ProductDiscount.builder().discount_percent(30).build();
//		ProductDiscount discount2=discountServiceImpl.findDiscountByAmount(discount);
//		discount2.getProducts().forEach((product)->System.out.println(product.getName()));;
//		System.out.println(String.valueOf(brand2.getProducts().get(0).getImages()));
//		System.out.println(brand2);
//		System.out.println(productrepo.findAll().size());
//		List<Product> products= productrepo.getProduct("%%");
//		System.out.println(products.size());
//		System.out.println(productrepo.getDistinct().length);
//		System.out.println(productrepo.getProduct(new String[] {"H&M"}));
//		String[] brands= {"%%"};
//		System.out.println(productrepo.getProduct(brands).size());
//		System.out.println(String.valueOf(catrepo.getDistinctCategoryType()));
//		System.out.println(String.valueOf(brandrepo.distinctBrand()));
//		System.out.println(productrepo.getDistinct());
//		productrepo.getFilteredProduct("%%",new String[]{"H&M"},new String[]{"Shirt","T-Shirt"}, new String[] {"RED","WHITE"},2000,500,new String[] {"man","woman","baby","kid"},"desc").forEach(a->System.out.println(a));
//		productrepo.pricedProduct().forEach(a->System.out.println(a));
	}
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("*").allowedHeaders("*").allowedOrigins("*").allowedMethods("*").allowCredentials(true);
//			}
//		};
//	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurerAdapter() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**").allowedOrigins("http://localhost:4200");
	        }
	    };
	}

}
