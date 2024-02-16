package com.sql.jdbc.service.test;

import com.sql.jdbc.entity.Order;
import com.sql.jdbc.service.OrderService;
import com.sql.jdbc.service.ProductService;
import com.sql.jdbc.service.ShoppingCartService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;


class OrderServiceTest {
    private OrderService orderService;
    private ShoppingCartService shoppingCartService;
    private ProductService productService;

    @BeforeEach
    public void createService() {
        orderService = new OrderService();
        shoppingCartService = new ShoppingCartService();
        productService = new ProductService();
    }

    @Test
    void add() {
        int userId = 6;
        String list = "";
        String length2 = "";
        boolean flag = false;
        List<Integer> productsByUserBefore = shoppingCartService.getAllProductsByUser(userId);
        int currentSize = orderService.getAllById(userId).size();
        orderService.add(userId);
        int newSize = orderService.getAllById(userId).size();
        List<Integer> productsByUserAfter = shoppingCartService.getAllProductsByUser(userId);

        for (Integer p : productsByUserBefore) {
            list += productService.getById(p).getName() + ",";
        }
        if(list.length()!=0 && list!=null){
            if (list.length()!=0){length2 = list.substring(0, list.length() - 1);}

            List<Order> allProductByUser = orderService.getAllById(userId);
            String[] listProductsBefore = length2.split(",");

            for (Order products : allProductByUser) {
                String[] currentList = products.getList().split(",");
                for (int i = 0; i < currentList.length; i++) {
                    if (currentList[i].equals(listProductsBefore[i])) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                }
            }
        }


        Assert.assertTrue("список замовлень не збільшився користувача, після добавлення",currentSize<newSize);
        Assert.assertTrue("Продукти не видалились з корзини, після добавлення списку замовлень",productsByUserAfter.size()==0);
        Assert.assertEquals("Список з відповідними продуктами не з'явився в базі даних",flag,true);
    }

    @Test
    void getAllById() {
        int userId = 6;
        List<Order> ordersByUser = orderService.getAllById(userId);

        Assert.assertNotNull(ordersByUser);
        Assert.assertTrue("Кількість списків повинна бути рівна 0 або більше",ordersByUser.size()>=0);
    }

    @Test
    void getAll() {
        List<Order> ordersAll = orderService.getAll();

        Assert.assertNotNull(ordersAll);
        Assert.assertTrue("Кількість списків повинна бути рівна 0 або більше",ordersAll.size()>=0);
    }
}