package com.cogent.main;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<WishEntity, Integer>
{

	List<WishEntity> findByUserId(int userId);

	@Query(value = "from WishEntity where userId = :userId and productId = :productId")
	Optional<WishEntity> findByUserIdAndProductId(int userId, int productId);

	void deleteByUserId(int userId);
	

}
