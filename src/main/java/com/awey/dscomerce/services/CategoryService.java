package com.awey.dscomerce.services;

import com.awey.dscomerce.dto.CategoryDTO;
import com.awey.dscomerce.dto.ProductDTO;
import com.awey.dscomerce.dto.ProductMinDTO;
import com.awey.dscomerce.entities.Category;
import com.awey.dscomerce.entities.Product;
import com.awey.dscomerce.repositories.CategoryRepository;
import com.awey.dscomerce.repositories.ProductRepository;
import com.awey.dscomerce.services.exception.DatabaseException;
import com.awey.dscomerce.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> result = repository.findAll();
        return result.stream().map(x -> new CategoryDTO(x)).toList();
    }

}