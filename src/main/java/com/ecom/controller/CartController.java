package com.ecom.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payload.CartDto;
import com.ecom.payload.CartItemRequest;
import com.ecom.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	
	 @Autowired
	    private CartService cartService;

	    //add product to cart
	    @PostMapping(value = "/")
	    public ResponseEntity<CartDto> addProductToCart(
	            @RequestBody CartItemRequest cartItemRequest,
	            Principal principal
	    ) {

	        String username = principal.getName();
	        System.out.println(username);
	        CartDto cartDto = this.cartService.addItemToCart(cartItemRequest, username);
	        System.out.println(cartDto);
	        return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
	    }


	    //get items cart
	    
	    
	    @GetMapping("/")
	    public ResponseEntity<CartDto> getCart(
	            Principal principal
	    ) {
	        String username = principal.getName();
	        CartDto cart = this.cartService.getCart(username);
	        return new ResponseEntity<>(cart, HttpStatus.OK);
	    }

	    @DeleteMapping("/{productId}")
	    public ResponseEntity<CartDto> getCart(
	            @PathVariable Integer productId
	            ,
	            Principal principal
	    ) {
	        String username = principal.getName();
	        CartDto cart = this.cartService.removeCartItem(username, productId);
	        return new ResponseEntity<>(cart, HttpStatus.OK);
	    }



	    //remove items from


}
