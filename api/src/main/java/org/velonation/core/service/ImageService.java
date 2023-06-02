package org.velonation.core.service;

import org.velonation.core.datasource.entity.product.Product;
import java.io.IOException;

public interface ImageService {
    Product save(Product product) throws IOException;
}
