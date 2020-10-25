package com.example.restapi.service;

import com.example.restapi.model.User;
import com.example.restapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private Logger LOG = LoggerFactory.getLogger(UserService.class);

    private UserRepository productRepository;

    @Autowired
    public void setProductRepository(UserRepository productRepository) {
        this.productRepository = productRepository;
    }

    public User getProduct(String id) {
        LOG.info("Getting the product with given id:" + id);
        return productRepository.findOne(id);
    }

    public User saveProduct(User product) {
        User productToSave;
        try {
            LOG.info("Saving product...");
            productToSave = productRepository.save(product);
            return productToSave;
        } catch (Exception e) {
            LOG.error("An error occurred during product saving:" + e.getMessage());
        }
        return new User();
    }

    public User updateProduct(User productToUpdate, String id) {
        User foundProduct = productRepository.findOne(id);
        try {
            foundProduct.setName(productToUpdate.getName());
        
            return productRepository.save(foundProduct);
        } catch (Exception e) {
            LOG.error("An error pccurred during update of product" + e.getMessage());
        }
        return productToUpdate;
    }

    public void deleteProduct(String id) {
        try {
            productRepository.delete(id);
        } catch (Exception e) {
            LOG.error("An error occurred during deleting of product:" + e.getMessage());
        }
    }
}
