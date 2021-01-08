package com.platzi.market.persistence;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.Producto;
import com.platzi.market.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRespository implements ProductRepository {

    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper productMapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productList = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productList);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productsByCategory = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productsByCategory));
    }

    @Override
    public Optional<List<Product>> getScarceProducts(int quantity) {
        Optional<List<Producto>> scarceProducts = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return scarceProducts.map(products -> productMapper.toProducts(products));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository
                .findById(productId)
                .map(product -> productMapper.toProduct(product));
    }

    @Override
    public Product save(Product product) {
        Producto producto = productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }

}
