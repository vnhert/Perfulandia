package com.perfulandia.perfulandia;

import com.perfulandia.perfulandia.Model.*;
import com.perfulandia.perfulandia.Repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.Random;
@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CuponRepository cuponRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ShipProductRepository shipProductRepository;
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();
//generando proveedores
        System.out.println("Generando Proveedores...");
        for (int i = 0; i < 10; i++) {
            Proveedor proveedor = new Proveedor();
            proveedor.setNombre(faker.company().name()); // Nombre de empresa
            proveedor.setContacto(faker.company().name()); // Nombre de empresa
            proveedor.setTelefono(faker.phoneNumber().phoneNumber());
            proveedor.setDireccion(faker.address().fullAddress());
            proveedorRepository.save(proveedor);
        }
        List<Proveedor> proveedores = proveedorRepository.findAll();
        System.out.println("Proveedores generados: " + proveedores.size());
//generando sucursales
        System.out.println("Generando Sucursales (Branch)");
        for (int i = 0; i < 5; i++) {
            Branch branch = new Branch();
            branch.setNombre(faker.address().cityName() + " Store"); // Usamos un nombre más apropiado para sucursal
            branch.setDireccion(faker.address().fullAddress());
            branchRepository.save(branch);
        }
        List<Branch> branches = branchRepository.findAll();
        System.out.println("Sucursales generadas: " + branches.size());

// Generando (User)

        System.out.println("Generando Usuarios (User)...");
        //  @JsonSubTypes de clase User
        String[] userRoles = {"ADMINISTRADOR", "EMPLEADO_VENTAS", "GERENTE_SUCURSAL", "PERSONAL_LOGISTICA","CLIENTE"};

        for (int i = 0; i < 20; i++) {
            User user;
            String assignedRole = faker.options().option(userRoles); // un rol aleatorio

            // subclase basada en el rol
            switch (assignedRole) {
                case "ADMINISTRADOR":
                    user = new AdministradorSistema();
                    break;
                case "EMPLEADO_VENTAS":
                    user = new EmpleadoVentas();
                    break;
                case "GERENTE_SUCURSAL":
                    user = new GerenteSucursal();
                    break;
                case "PERSONAL_LOGISTICA":
                    user = new PersonalLogistica();
                    break;
                case "CLIENTE":
                    user = new Client();
                default:
                    // si userRoles está bien definido
                    user = new EmpleadoVentas();
                    break;
            }



            // Setea los campos que están en la clase User abstracta
            user.setNombre(faker.name().fullName());
            user.setCorreo(faker.internet().emailAddress());
            user.setContraseña(faker.internet().password(8, 12, true, true, true));

            // Si User se relaciona con Branch (solo para los tipos de usuario que necesitan una sucursal)
            //  clase User abstracta
            if (!branches.isEmpty()) {

                // Por ejemplo, solo EmpleadoVentas, GerenteSucursal y PersonalLogistica podrían tener una sucursal
                // if (user instanceof EmpleadoVentas || user instanceof GerenteSucursal || user instanceof PersonalLogistica) {
                user.setBranch(branches.get(random.nextInt(branches.size())));
                // }
            }
            userRepository.save(user);
        }
        List<User> users = userRepository.findAll();
        System.out.println("Usuarios (Administradores, Empleados, Gerentes) generados: " + users.size());



//generando client
        System.out.println("Generando Clientes...");
        for (int i = 0; i < 150; i++) {
            Client client = new Client();
            client.setNombre(faker.name().fullName());
            client.setCorreo(faker.internet().emailAddress());
            clientRepository.save(client);
        }
        List<Client> clients = clientRepository.findAll();
        System.out.println("Clientes generados: " + clients.size());

//generando product
        System.out.println("Generando Productos...");
        if (proveedores.isEmpty()) {
            System.err.println("ERROR: No se pueden generar productos sin Proveedores. Asegúrate de generar Proveedores primero.");
        } else {
            for (int i = 0; i < 200; i++) {
                Product product = new Product();
                product.setNombre(faker.commerce().productName());
                product.setDescripcion(faker.lorem().paragraph(2));
                product.setPrecio(faker.number().randomDouble(2, 5, 500));
                product.setStock(faker.number().numberBetween(0, 500));
                product.setCategoria(faker.commerce().department());
                productRepository.save(product);
            }
        }
        List<Product> products = productRepository.findAll();
        System.out.println("Productos generados: " + products.size());

//generando cupones
        System.out.println("Generando Cupones...");
        for (int i = 0; i < 15; i++) {
            Cupon cupon = new Cupon();
            cupon.setCodigo(faker.code().asin());
            cupon.setDescuento(faker.number().randomDouble(2, 5, 30));
            cupon.setActivo(faker.bool().bool());
            cuponRepository.save(cupon);
        }
        System.out.println("Cupones generados: " + cuponRepository.count());

//generando orders
        System.out.println("Generando Órdenes (Order)...");
        if (products.isEmpty() || branches.isEmpty()) {
            System.err.println("ERROR: No se pueden generar Órdenes sin Productos o Sucursales.");
        } else {
            for (int i = 0; i < 100; i++) {
                Order order = new Order();
                order.setEstado(faker.options().option("PENDIENTE", "CONFIRMADA", "RECHAZADA"));
                order.setFechaEntrega(new Date()); // Fecha de la orden
                order.setCantidad(String.valueOf(faker.number().numberBetween(1, 5))); // Cantidad de este producto específico
                order.setAutorizado(faker.bool().bool());
                order.setBranch(branches.get(random.nextInt(branches.size())));
                order.setProduct(products.get(random.nextInt(products.size()))); // ManyToOne a un solo Product
                orderRepository.save(order);
            }
        }
        List<Order> orders = orderRepository.findAll();
        System.out.println("Órdenes (Order) generadas: " + orders.size());

//generando ship
        System.out.println("Generando Envíos (Ship)...");
        if (clients.isEmpty()) {
            System.err.println("ERROR: No se pueden generar Envíos sin Clientes.");
        } else {
            for (int i = 0; i < 80; i++) { // Generar algunos Ships, no necesariamente uno por cada Order
                Ship ship = new Ship();
                ship.setEstado(faker.options().option("PREPARACION", "EN_TRANSITO", "ENTREGADO", "CANCELADO_ENVIO"));
                ship.setFechaEntrega(new Date()); // <<-- CORRECCIÓN: Usando 'fechaEntrega'
                ship.setCliente(clients.get(random.nextInt(clients.size()))); // Un Ship tiene un Cliente
                ship.setCupon(faker.options().option("CUPON_NAVIDAD", "CUPON_VERANO", "SIN_CUPON")); // O faker.code().asin()

                // La lista 'productos' se manejará a través de ShipProduct
                shipRepository.save(ship);
            }
        }
        List<Ship> ships = shipRepository.findAll();
        System.out.println("Envíos (Ship) generados: " + ships.size());


//generando shipProduct
        System.out.println("Generando Detalles de Productos en el Envío (ShipProduct)...");
        if (ships.isEmpty() || products.isEmpty()) {
            System.err.println("ERROR: No se pueden generar ShipProducts sin Envíos o Productos.");
        } else {
            for (Ship ship : ships) { // Para cada envío existente
                int numProductsInShip = random.nextInt(3) + 1; // Cada envío tendrá entre 1 y 3 tipos de productos
                for (int j = 0; j < numProductsInShip; j++) {
                    ShipProduct shipProduct = new ShipProduct();
                    shipProduct.setShip(ship); // Asocia al envío actual
                    shipProduct.setProduct(products.get(random.nextInt(products.size()))); // Asocia un producto aleatorio
                    shipProduct.setCantidad(faker.number().numberBetween(1, 5)); // Cantidad de ese producto en este envío

                    shipProductRepository.save(shipProduct);
                }
            }
        }
        System.out.println("Detalles de Productos en el Envío (ShipProduct) generados: " + shipProductRepository.count());
//generando ventas
        System.out.println("Generando Ventas (Sale)...");
        if (clients.isEmpty() || products.isEmpty() || branches.isEmpty()) {
            System.err.println("ERROR: No se pueden generar ventas sin Clientes, Productos o Sucursales.");
        } else {

            for (int i = 0; i < 60; i++) {
                Sale sale = new Sale();
                sale.setId(i + 1);
                sale.setProduct(products.get(random.nextInt(products.size())));
                sale.setClient(clients.get(random.nextInt(clients.size())));
                sale.setCantidad(faker.number().numberBetween(1, 10));
                sale.setFecha(new Date());
                sale.setBranch(branches.get(random.nextInt(branches.size())));
                saleRepository.save(sale);
            }
        }
        System.out.println("Ventas generadas: " + saleRepository.count());
//generando review
        System.out.println("Generando Reseñas (Review)...");
        if (clients.isEmpty() || products.isEmpty()) {
            System.err.println("ERROR: No se pueden generar reseñas sin Clientes o Productos.");
        } else {
            for (int i = 0; i < 100; i++) {
                Review review = new Review();
                review.setCliente(clients.get(random.nextInt(clients.size()))); // Usando 'setCliente'
                review.setProducto(products.get(random.nextInt(products.size()))); // Usando 'setProducto'
                review.setCalificacion(faker.number().numberBetween(1, 5)); // Usando 'setCalificacion'
                review.setComentario(faker.lorem().paragraph(1)); // Usando 'setComentario'
                // review.setReviewDate(new Date()); // Campo 'reviewDate' eliminado del modelo
                reviewRepository.save(review);
            }
        }
        System.out.println("Reseñas generadas: " + reviewRepository.count());

        System.out.println("--- Generación de Datos de Prueba para Perfulandia Completada ---");
    }
}




