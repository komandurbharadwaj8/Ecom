package com.ecom.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entity.Category;
import com.ecom.entity.Product;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.ProductDto;
import com.ecom.repository.CategoryRepo;
import com.ecom.repository.ProductRepo;
import com.ecom.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	
	 @Autowired
	    private ProductRepo productRepo;
	 
	    @Autowired
	    private ModelMapper modelMapper;

	    @Autowired
	    private CategoryRepo categoryRepo;

	

	@Override
	public ProductDto create(ProductDto product, Integer catId) {
		
		 Category category = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("category", "category id ", catId + ""));
	        Product product1 = this.modelMapper.map(product, Product.class);
	        product1.setCategory(category);
	        Product product2 = this.productRepo.save(product1);
	        return this.modelMapper.map(product2, ProductDto.class);
	}

	@Override
	public ProductDto update(ProductDto product, Integer productId) {
		
		Category category = this.categoryRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("category", "category id ", productId + ""));
    	Product product1 = this.modelMapper.map(product, Product.class);
    	 product1.setCategory(category);
       //Product product1 = this.productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "product id", productId + ""));
       product1.setName(product.getName());
       product1.setDescription(product.getDescription());
       product1.setPrice(product.getPrice());
       product1.setRating(product.getRating());
       product1.setImageName(product.getImageName());
       product1.setIsLive(product.getIsLive());
       product1.setInStock(product.getInStock());
       
       Product update=this.productRepo.save(product1);
       return this.modelMapper.map(update, ProductDto.class);
	}

	@Override
	public void delete(Integer productId) {
		
		Product product = this.productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "product id", productId + ""));
    	this.productRepo.delete(product);

		
	}

	@Override
	public List<ProductDto> getAllProducts() {
		
		List<Product> all = this.productRepo.findAll();
        return all.stream().map((product -> this.modelMapper.map(product, ProductDto.class))).collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> getAllProductOfCategory(Integer catId) {
		
		Category category = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("category", "category id ", catId + ""));
        List<Product> products = this.productRepo.findByCategory(category);
        return products.stream().map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> getProductNotLive() {
		
		List<Product> products = this.productRepo.findByIsLiveFalse();
        return products.stream().map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

	}

	@Override
	public List<ProductDto> getProductOutOfStock() {
		
		List<Product> products = this.productRepo.findByInStockFalse();
        return products.stream().map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

	}

	@Override
	public ProductDto getById(Integer productId) {
		
		
		 Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product", "product id ", productId + ""));

	        return this.modelMapper.map(product, ProductDto.class);

	}

}
