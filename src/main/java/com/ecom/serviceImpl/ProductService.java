package com.ecom.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.ProductRepository;
import com.ecom.entity.Product;
import com.ecom.entity.ProductBrand;
import com.ecom.entity.ProductCategory;
import com.ecom.entity.ProductDiscount;
import com.ecom.entity.ProductForGender;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	EntityManagerFactory emf;
	@Autowired
	ProductBrandServiceImpl brandServiceImpl;
	@Autowired
	ProductCategoryServiceImpl categoryServiceImpl;
	@Autowired
	ProductDiscountServiceImpl discountServiceImpl;
	@Autowired
	ProductGenderServiceImpl genderServiceImpl;

		public List<Product> findAllProduct(){
			return productRepository.findAll();
		}
	public String storeProduct(Product product) {
		product.getSizeAndQuantity().forEach(quantity -> {
			quantity.setSizeQuantityAndPrice(product);
			System.out.println(quantity);
		});
		ProductDiscount discount=discountServiceImpl.findDiscountByAmount(product.getDiscount());
		ProductForGender gender=genderServiceImpl.findGenderByName(product.getGender());
		ProductBrand brand=brandServiceImpl.findBrandByName(product.getBrand());
		ProductCategory category=categoryServiceImpl.findCategoryByType(product.getCategory());
		if (discount!=null) {
			product.setDiscount(discount);
		}
		if (gender!=null) {
			product.setGender(gender);
		}
		if (brand!=null) {
			product.setBrand(brand);
		}
		if(category!=null) {
			product.setCategory(category);
		}
		System.out.println(product);
		productRepository.saveAndFlush(product);
		return "Product stored successfully";
	}
	
	public List<Product> searchProduct(String searchedItem){
		return productRepository.searchItem(searchedItem);
	}
	public String getDistinctColor() {
		return String.valueOf(productRepository.getDistinct());
	}
	public List<Product> getFilteredProduct(String item,String[] brand,String[] category,String[] color,float maxPrice,float MinPrice,String[] gender){
		return productRepository.getFilteredProduct(item, brand, category, color, maxPrice, MinPrice,gender);
	}
	public List<Product> getFilteredProductAsc(String item,String[] brand,String[] category,String[] color,float maxPrice,float MinPrice,String[] gender){
		return productRepository.getFilteredProductInAsc(item, brand, category, color, maxPrice, MinPrice,gender);
	}
	public String updateProduct(Product product) {
		Optional<Product> op=productRepository.findById(product.getId());
//		System.out.println(op.get());
		if(op.isPresent()) {
			Product p=op.get();
			p.getSizeAndQuantity().get(0).setPrice(product.getSizeAndQuantity().get(0).getPrice());
			p.getSizeAndQuantity().get(0).setQuantity(product.getSizeAndQuantity().get(0).getQuantity());
			p.getSizeAndQuantity().get(0).setSize(product.getSizeAndQuantity().get(0).getSize());
			productRepository.saveAndFlush(p);
			return "Product Updated Successfully";
		}
		return "Product not present";
	}
	public String deleteProduct(long pid) {
		Optional<Product> op=productRepository.findById(pid);
		if(op.isPresent()) {
			Product p=op.get();
			productRepository.delete(p);
			return "Product deleted successfully";
		}
		return "Product didn't exist";
		
	}
	public Product findProductById(long id) {
		Optional<Product> op=productRepository.findById(id);
		if(op.isPresent()) {
			Product p=op.get();
//			productRepository.delete(p);
			return p;
		}
		return null;
		
	}
//	public List<Product> findProductsByPrice(float price){
//		return productRepository.findProductByPrice(price);
//	}
//	public List<Product> sortByPrice() {
//		return productRepository.sortByPrice();
//	}
//	
//	public String deleteProductByName(String name) {
//		if(productRepository.deleteProductByName(name)>0)
//			return "product deleted successfully" ;
//		return "product doesn't exist";
//	}
}
