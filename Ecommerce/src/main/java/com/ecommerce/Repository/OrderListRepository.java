package com.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.OrderList;


@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Integer>{

}
