package com.awey.dscomerce.services;

import com.awey.dscomerce.dto.OrderDTO;
import com.awey.dscomerce.dto.OrderItemDTO;
import com.awey.dscomerce.entities.*;
import com.awey.dscomerce.repositories.OrderItemRepository;
import com.awey.dscomerce.repositories.OrderRepository;
import com.awey.dscomerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public OrderDTO findByID(Long id) {
        Order order = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert (OrderDTO dto){

        System.out.println("Chamou o service Order");


        Order order = new Order();
        order.setMoment(Instant.now());
        order.setstatus(OrderStatus.WAITING_PAYMENT);

        System.out.println("Criou o Order");

        User user = userService.authenticated();
        order.setClient(user);

        System.out.println("Criou o User");


        for (OrderItemDTO itemDTO : dto.getItems()){

            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(order,product,itemDTO.getQuantity(),product.getPrice());
            order.getItems().add(item);
        }

        repository.save(order);

        orderItemRepository.saveAll(order.getItems());
        
        return new OrderDTO(order);

    }

}
