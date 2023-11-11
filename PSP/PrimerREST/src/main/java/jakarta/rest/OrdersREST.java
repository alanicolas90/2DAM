package jakarta.rest;


import dao.model.Order;
import domain.servicios.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrdersREST {

    private OrderService orderService;



    @Inject
    public OrdersREST(OrderService orderService) {
        this.orderService = orderService;
    }



    @GET
    public List<Order> getAllOrders() {
        return orderService.getAll();
    }

    @GET
    @Path("/{id}")
    public Order getUsuario(@PathParam("id") int id) {
        return orderService.get(id);
    }

    @POST
    public Response create(Order order){
        orderService.add(order);
        return Response.status(Response.Status.CREATED)
                .entity(order).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id){
        orderService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam(Constantes.ID)int id, Order order){
        order.setId(id);
        orderService.update(order);
        return Response.ok(order).build();
    }

}
