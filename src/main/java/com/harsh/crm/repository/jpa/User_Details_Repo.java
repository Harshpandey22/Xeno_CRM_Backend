package com.harsh.crm.repository.jpa;

import com.harsh.crm.entity.customer;

import com.harsh.crm.entity.order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User_Details_Repo extends JpaRepository<customer,Integer> {

}
