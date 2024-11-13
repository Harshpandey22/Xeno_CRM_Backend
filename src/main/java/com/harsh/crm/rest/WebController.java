package com.harsh.crm.rest;

import com.harsh.crm.entity.customer;
import com.harsh.crm.repository.mongo.Order_Details_Repo;
import com.harsh.crm.repository.jpa.User_Details_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.harsh.crm.entity.order;


@RestController
@CrossOrigin("*")
public class WebController {

    private final User_Details_Repo userDetailsRepo;
    private final Order_Details_Repo orderDetailsRepo;

    @Autowired
    public WebController(User_Details_Repo userDetailsRepo, Order_Details_Repo orderDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
        this.orderDetailsRepo = orderDetailsRepo;
    }

    @PostMapping("/store_data")
    public ResponseEntity<String> storeData(@RequestBody customer data){
        if (data == null) {
            return new ResponseEntity<>("Invalid customer data", HttpStatus.BAD_REQUEST);
        }
        userDetailsRepo.save(data);
        return new ResponseEntity<>("Customer Data stored", HttpStatus.CREATED);
    }

    @GetMapping("/get_data")
    public ResponseEntity<List<customer>> getData(){
        List<customer> customers = userDetailsRepo.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @DeleteMapping("/delete_data/{id}")
    public ResponseEntity<String> deleteData(@PathVariable int id){
        Optional<customer> existingCustomer = userDetailsRepo.findById(id);
        if (existingCustomer.isEmpty()) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
        userDetailsRepo.deleteById(id);
        return new ResponseEntity<>("User Deleted", HttpStatus.OK);
    }

    @PutMapping("/put_data/{id}")
    public ResponseEntity<String> putData(@RequestBody customer data, @PathVariable int id){
        Optional<customer> existingCustomer = userDetailsRepo.findById(id);
        if (existingCustomer.isEmpty()) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }

        customer cust = existingCustomer.get();

        cust.setPhoneNumber(data.getPhoneNumber());
        cust.setEmailId(data.getEmailId());
        cust.setFirstName(data.getFirstName());
        cust.setLastName(data.getLastName());

        userDetailsRepo.save(cust);
        return new ResponseEntity<>("Details Successfully Updated", HttpStatus.OK);
    }

    @PostMapping("/store_order")
    public ResponseEntity<String> storeOrder(@RequestBody order data){
        if (data == null) {
            return new ResponseEntity<>("Invalid customer data", HttpStatus.BAD_REQUEST);
        }
        orderDetailsRepo.save(data);
        return new ResponseEntity<>("Customer Data stored", HttpStatus.CREATED);
    }

    @GetMapping("/get_orders")
    public ResponseEntity<List<order>> getOrders(){
        List<order> orders = orderDetailsRepo.findAll();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

//    @GetMapping("/get_single_order/{id}")
//    public ResponseEntity <order> getSingleOrder(@PathVariable int id){
//        List<order> orders = orderDetailsRepo.findAll();
//        for(order temp:orders){
//            if(temp.getOrderId().equals(Integer.toString(id))){
//                return new ResponseEntity<>(temp,HttpStatus.OK);
//            }
//        }
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
    @GetMapping("/get_single_order/{orderId}")
    public ResponseEntity<order> getSingleOrder(@PathVariable String orderId){
        Optional<order> orderOptional = orderDetailsRepo.findById(orderId);
        if (orderOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderOptional.get(), HttpStatus.OK);
    }



    @DeleteMapping("/delete_order_data/{id}")
    public ResponseEntity<String> deleteOrderData(@PathVariable String id){
        Optional<order> existingorder = orderDetailsRepo.findById(id);
        if (existingorder.isEmpty()) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
        userDetailsRepo.deleteById(Integer.valueOf(id));
        return new ResponseEntity<>("Order Deleted", HttpStatus.OK);
    }


    @PutMapping("/put_order_data/{id}")
    public ResponseEntity<String> putOrderData(@RequestBody order data, @PathVariable int id){
        Optional<order> existingorder  = orderDetailsRepo.findById(Integer.toString(id));
        if (existingorder.isEmpty()) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }

        order or = existingorder.get();
        // Ensure orderId is not overwritten
        or.setOrderDate(data.getOrderDate());
        or.setOrderPrice(data.getOrderPrice());

        orderDetailsRepo.save(or);
        return new ResponseEntity<>("Details Successfully Updated", HttpStatus.OK);
    }



}