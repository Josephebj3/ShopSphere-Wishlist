package com.cogent.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
@RequestMapping("/wishlist")
public class WishController 
{
	@Autowired
	private WishService service;
	
	@GetMapping("/{userId}")
	public List<WishEntity> getWishlist(@PathVariable int userId, @RequestHeader("Authorization") String token) 
	{
		return service.getWishlist(userId, token);
	}
	
	@PostMapping("/{userId}")
	public List<WishEntity> addWish(@PathVariable int userId, @RequestParam int productId, @RequestHeader("Authorization") String token) 
	{
		return service.addWish(userId, productId, token);
	}
	
	@DeleteMapping("/{userId}/remove/{productId}")
	public List<WishEntity> deleteWish(@PathVariable int userId, @PathVariable int productId, @RequestHeader("Authorization") String token)
	{
		
		return service.deleteWish(userId, productId, token);
	}
	
	@DeleteMapping("/delete/{userId}")
	public boolean deleteByUserId(@PathVariable int userId, @RequestHeader("Authorization") String token)
	{
		return service.deleteByUserId(userId, token);
	}
	
}
