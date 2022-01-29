package ru.netology.repository;

import ru.netology.domain.Product;

public class ProductRepository {
    Product[] products = new Product[0];

    public void save(Product product) {
        int length = products.length + 1;
        Product[] tmp = new Product[length];
        System.arraycopy(products, 0, tmp, 0, products.length);
        int tmpLastIndex = tmp.length - 1;
        tmp[tmpLastIndex] = product;
        products = tmp;
    }

    public Product[] get() {
        return products;
    }

    public Product[] removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Вы пытаетесь удалить продукт по несуществующему идентификатору: " + id);
        } else {
            int length = products.length - 1;
                Product[] tmp = new Product[length];
                int index = 0;
                for (Product product : products) {
                    if (product.getId() != id) {
                        tmp[index] = product;
                        index++;
                    }
                }
                products = tmp;
        }
        return products;
    }

    public Product findById(int id) {
        Product neededProduct = null;
        for (Product product : products) {
            if (product.getId() == id) {
                neededProduct = product;
            }
        }
        return neededProduct;
    }
}
