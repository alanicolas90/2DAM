package jakarta.rest;


import dao.model.Order;
import domain.servicios.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrdersREST {

    private final OrderService orderService;



    @Inject
    public OrdersREST(OrderService orderService) {
        this.orderService = orderService;
    }



    @GET
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GET
    @Path("/{id}")
    public Order get(@PathParam(ConstantesJakarta.ID) int id) {
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
    public Response delete(@PathParam(ConstantesJakarta.ID) Integer id){

        if(orderService.delete(id)) return Response.ok().build();
        else return Response.status(Response.Status.NOT_FOUND).build();

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam(ConstantesJakarta.ID)int id, Order order){

        order.setId(id);
        orderService.update(order);
        return Response.ok(order).build();

    }

}
