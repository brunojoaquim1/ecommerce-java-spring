package com.awey.dscomerce.dto;

import com.awey.dscomerce.entities.Category;
import com.awey.dscomerce.entities.Product;
import jakarta.validation.constraints.*;
import org.apache.logging.log4j.message.Message;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {

    private Long id;
    @Size(min = 3, max = 80 , message = "O Nome precisa ter entre 3 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;

    @Size(min = 10, message = "A descrição deve ter no minimo 10 caracteres")
    @NotBlank
    private String description;

    @Positive(message = "O preço deve ser positivo")
    @NotNull(message = "Campo requerido")
    private Double price;
    private String imgUrl;

    @NotEmpty(message = "Deve ter pelo menos uma categoria")
    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(){}

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }


    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();


        categories = product.getCategories()
                .stream()
                .map(CategoryDTO::new) // cria um CategoryDTO a partir da entidade Category
                .toList();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }
}
