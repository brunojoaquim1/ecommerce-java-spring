package com.awey.dscomerce.repositories;

import com.awey.dscomerce.entities.Order;
import com.awey.dscomerce.entities.User;
import com.awey.dscomerce.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
