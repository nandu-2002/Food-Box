package com.foodbox.controller;

import com.foodbox.Service.OrderService;
import com.foodbox.Service.PaymentService;
import com.foodbox.Service.UserService;
import com.foodbox.model.CartItem;
import com.foodbox.model.Order;
import com.foodbox.model.User;
import com.foodbox.request.AddCartItemRequest;
import com.foodbox.request.OrderRequest;
import com.foodbox.response.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<PaymentResponse> createController(@RequestBody OrderRequest req,
                                                            @RequestHeader("Authorization")String jwt)throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Order order=orderService.createOrder(req,user);
        PaymentResponse res=paymentService.createPaymentLink(order);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestParam Long userId,
                                                  @RequestHeader("Authorization")String jwt)throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        List<Order> order=orderService.getUsersOrder(user.getId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}