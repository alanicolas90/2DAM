package jakarta.rest;



import dao.model.Customer;
import domain.servicios.CustomerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;


@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomersREST {

    private final CustomerService customerService;


    @Inject
    public CustomersREST(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GET
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }

    @GET
    @Path("/{id}")
    public Customer getCustomer(@PathParam(Constantes.ID) int id) {
        return customerService.get(id);
    }

    @POST
    public Response create(Customer customer){
        customerService.add(customer);
        return Response.status(Response.Status.CREATED)
                .entity(customer).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam(Constantes.ID) Integer id){
        customerService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam(Constantes.ID)int id, Customer customer){
        customer.setId(id);
        customerService.update(customer);
        return Response.ok(customer).build();
    }
}
