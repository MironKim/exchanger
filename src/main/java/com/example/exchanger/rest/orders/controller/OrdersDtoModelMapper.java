package com.example.exchanger.rest.orders.controller;


import com.example.exchanger.rest.orders.controller.dto.Order;
import com.example.exchanger.rest.orders.controller.dto.OrderConfirmParameters;
import com.example.exchanger.rest.orders.controller.dto.OrderCreationParameters;
import com.example.exchanger.rest.orders.service.model.OrderConfirmParametersModel;
import com.example.exchanger.rest.orders.service.model.OrderCreationParametersModel;
import com.example.exchanger.rest.orders.service.model.OrderModel;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdersDtoModelMapper {

    List<Order> map(List<OrderModel> source);

    OrderCreationParametersModel map(OrderCreationParameters source);

    OrderConfirmParametersModel map(Long orderId, OrderConfirmParameters parameters);
}
