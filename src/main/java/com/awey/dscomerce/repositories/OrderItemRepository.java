package com.awey.dscomerce.repositories;

import com.awey.dscomerce.entities.OrderItem;
import com.awey.dscomerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {


}
