package com.example.exchanger.rest.orders.repository;


import com.example.exchanger.rest.orders.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<OrderEntity, Long> {

}
