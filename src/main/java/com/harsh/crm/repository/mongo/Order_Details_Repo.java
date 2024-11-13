package com.harsh.crm.repository.mongo;

import com.harsh.crm.entity.order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface Order_Details_Repo extends MongoRepository<order,String> {
    @Query("{ 'orderId' : ?0 }")
    Optional<Order> findByOrderId(String orderId);
}
