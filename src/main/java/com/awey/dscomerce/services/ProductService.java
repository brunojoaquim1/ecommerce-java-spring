package com.awey.dscomerce.services;

import com.awey.dscomerce.dto.CategoryDTO;
import com.awey.dscomerce.dto.ProductDTO;
import com.awey.dscomerce.dto.ProductMinDTO;
import com.awey.dscomerce.entities.Category;
import com.awey.dscomerce.entities.Product;
import com.awey.dscomerce.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public ProductDTO findByID(Long id) {
        Product product = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProductDTO(product);

    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
        Page<Product> result = repository.serachByName(name,pageable);
        return result.map(product -> new ProductMinDTO(product));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);

        entityManager.flush();
        entityManager.clear();

        entity = repository.searchProductWithCategories(entity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

        return new ProductDTO(entity);
    }

    @Transactional()
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);

            entityManager.flush();
            entityManager.clear();

            entity = repository.searchProductWithCategories(entity.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

        entity.getCategories().clear();
        for (CategoryDTO catDto : dto.getCategories()) {
            Category category = new Category();
            category.setId(catDto.getId());
            entity.getCategories().add(category);
        }
    }


}