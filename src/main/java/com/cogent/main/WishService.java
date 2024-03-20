package com.cogent.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class WishService 
{
	@Autowired
	private WishRepository repository;
	
	@Autowired
	private UserClient userClient;
	
	@Autowired
	private ProductClient productClient;

	public List<WishEntity> getWishlist(int userId, String token) 
	{
		if(!userClient.validUserToken(userId, token)) return null;
		return repository.findByUserId(userId);
	}

	public List<WishEntity> addWish(int userId, int productId, String token) 
	{
		if(!userClient.validUserToken(userId, token)) return null;
		
		Product p = productClient.getProduct(productId);
		WishEntity we = WishEntity.builder()
			.userId(userId)
			.productId(productId)
			.name(p.getName())
			.description(p.getDescription())
			.price(p.getPrice())
			.category(p.getCategory())
			.image(p.getImage())
			.build();
		if(repository.findByUserIdAndProductId(userId, productId).isEmpty()) repository.save(we);
		
		return repository.findByUserId(userId);
	}

	public List<WishEntity> deleteWish(int userId, int productId, String token) 
	{
		if(!userClient.validUserToken(userId, token)) return null;
		
		if(repository.findByUserIdAndProductId(userId, productId).isPresent()) 
		{
			repository.delete(repository.findByUserIdAndProductId(userId, productId).get());
		}
		return repository.findByUserId(userId);
	}

	@Transactional
	public boolean deleteByUserId(int userId, String token) 
	{
		if(!userClient.validAdminToken(token)) return false;
		
		if(repository.findByUserId(userId).size()<1) return false;
		
		repository.deleteByUserId(userId);
		
		return true;
	}
	
	
	
	

}
