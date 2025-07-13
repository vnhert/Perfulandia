package com.perfulandia.perfulandia.Assembler;

import com.perfulandia.perfulandia.Controller.OrderController;
import com.perfulandia.perfulandia.Model.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler; // Make sure this is correctly imported
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class OrderAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

    @Override // Ensure this annotation is present
    public EntityModel<Order> toModel(Order order) {
        return EntityModel.of(order,
                linkTo(methodOn(OrderController.class).getOrder(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).getOrders()).withRel("orders"));
    }
}