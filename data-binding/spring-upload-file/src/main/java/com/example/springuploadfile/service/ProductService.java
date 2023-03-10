package com.example.springuploadfile.service;

import com.example.springuploadfile.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService{
    private List<Product> products = new ArrayList<>();
    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public Product findById(long id) {
        for(Product p : products){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    @Override
    public void update(long id, Product product) {
        for(Product p : products){
            if(p.getId() == id){
                p = product;
                break;
            }
        }
    }

    @Override
    public void remove(long id) {
        for(int i =0; i < products.size(); i++){
            if(products.get(i).getId() == id){
                products.remove(i);
            }
        }
    }
}
