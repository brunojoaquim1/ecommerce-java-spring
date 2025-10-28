package com.awey.dscomerce.controllers;

import com.awey.dscomerce.dto.OrderDTO;
import com.awey.dscomerce.dto.ProductDTO;
import com.awey.dscomerce.dto.ProductMinDTO;
import com.awey.dscomerce.services.OrderService;
import com.awey.dscomerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findByID(@PathVariable Long id){
        return ResponseEntity.ok(service.findByID(id));
    }

}
