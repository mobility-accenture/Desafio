package accenture.com.coffeetek.Interface;

import java.util.List;

import accenture.com.coffeetek.Model.Product;

public interface JsonAsyncResponse {
    void processFinish(List<Product> productList);
}
