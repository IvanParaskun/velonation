package org.velonation.core.service.implementation.product;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.velonation.core.datasource.entity.product.Product;
import org.velonation.core.service.ImageService;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public Product save(Product product) throws IOException {
        String name = generatePublicId(product);
        File file = File.createTempFile("temporary", "png");

        product.getFile().transferTo(file);
        product.setImageUrl(upload(file, name));

        return product;
    }

    private String generatePublicId(Product product) {
        return product.getName().replace(" ", "_");
    }

    private String upload(File file, String name) throws IOException {
        HttpPost post = new HttpPost("https://api.imgbb.com/1/upload?key=acd03ab81e9279d6a920be2297881767");
        post.setEntity(MultipartEntityBuilder.create()
                .addBinaryBody("image", new FileInputStream(file), ContentType.APPLICATION_OCTET_STREAM, name)
                .build()
        );

        CloseableHttpResponse response = HttpClients.createDefault().execute(post);

        Response resp = new Gson()
                .fromJson(
                        IOUtils.readLines(response.getEntity().getContent()).get(0),
                        Response.class
                );

        return resp.data.url;
    }

    private static class Response {
        public Data data;

        public static class Data {
            public String url;
        }
    }
}
