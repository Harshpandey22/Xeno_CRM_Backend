package com.harsh.crm.repository.jpa;

import com.harsh.crm.dto.CustomerSegments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Customer_Segments_Repo extends JpaRepository<CustomerSegments,Long> {
}
