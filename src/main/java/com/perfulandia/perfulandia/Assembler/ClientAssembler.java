package com.perfulandia.perfulandia.Assembler;
import com.perfulandia.perfulandia.Model.Client;
import com.perfulandia.perfulandia.Controller.ClientController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClientAssembler implements RepresentationModelAssembler<Client, EntityModel<Client>> {
    @Override
    public EntityModel<Client> toModel(Client client) {
        return EntityModel.of(client,
                linkTo(methodOn(ClientController.class).getClient(client.getId())).withSelfRel(),
                linkTo(methodOn(ClientController.class).getClients()).withRel("clientes")
        );
    }
}
