package com.awey.dscomerce.services;

import com.awey.dscomerce.dto.OrderDTO;
import com.awey.dscomerce.entities.Order;
import com.awey.dscomerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;


    @Transactional(readOnly = true)
    public OrderDTO findByID(Long id) {
        Order order = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);

    }

}
