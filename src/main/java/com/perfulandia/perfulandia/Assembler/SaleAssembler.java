package com.perfulandia.perfulandia.Assembler;
import com.perfulandia.perfulandia.Model.Sale;
import com.perfulandia.perfulandia.Controller.SaleController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SaleAssembler  implements RepresentationModelAssembler<Sale, EntityModel<Sale>> {
    @Override
    public EntityModel<Sale> toModel(Sale sale) {
        return EntityModel.of(sale,
                linkTo(methodOn(SaleController.class).getSale(sale.getId())).withSelfRel(),
                linkTo(methodOn(SaleController.class).getSales()).withRel("ventas")
        );
    }
}
