package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products = new ArrayList<Product>();
    private Map<Product, Integer> productQuantity = new HashMap<Product, Integer>();

    public void addProduct(Product product) {
        products.add(product);
    }


    public void addProduct(Product product, Integer quantity) {
        if (productQuantity.containsKey(product)) {
            int newQuantity = productQuantity.get(product) + quantity;
            productQuantity.replace(product, newQuantity);
        }else{
        productQuantity.put(product, quantity);}

    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (Product product : products) {
            subtotal = subtotal.add(product.getPrice());
        }
        return subtotal;
    }

    public BigDecimal getTax() {
        return BigDecimal.ZERO;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : productQuantity.keySet()) {
            int quantity = productQuantity.get(product);
            total = product.getPrice().multiply(new BigDecimal(quantity));

        }

        return total;
    }
}
