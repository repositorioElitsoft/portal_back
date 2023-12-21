package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import com.elitsoft.proyectoCuestionario_backend.entities.ProductVersion;
import com.elitsoft.proyectoCuestionario_backend.repositories.ProductRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.ProductVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elitsoft.proyectoCuestionario_backend.services.ProductVersionService;

import java.util.List;

@Service
public class ProductVersionServiceImpl implements ProductVersionService {

    private final ProductRepository productRepository;
    private final ProductVersionRepository productVersionRepository;

    @Autowired
    public ProductVersionServiceImpl(ProductRepository productRepository, ProductVersionRepository productVersionRepository) {
        this.productRepository = productRepository;
        this.productVersionRepository = productVersionRepository;
    }

    @Override
    public List<ProductVersion> findByProductoId(Long prod_id) throws Exception{
        Product product = productRepository.findById(prod_id)
                .orElseThrow(() -> new IllegalArgumentException("Proudcto no encontrada con el ID: " + prod_id));

        return productVersionRepository.findByProd_Id(prod_id);
    }
}
