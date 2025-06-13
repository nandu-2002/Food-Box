package com.foodbox.repository;

import com.foodbox.model.Cart;
import com.foodbox.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {


}
