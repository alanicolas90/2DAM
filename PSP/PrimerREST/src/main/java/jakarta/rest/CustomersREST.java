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

    private CustomerService su;


    @Inject
    public CustomersREST(CustomerService su) {
        this.su = su;
    }


    @GET
    public List<Customer> getAllCustomers() {
        return su.getAll();
    }

    @GET
    @Path("/{id}")
    public Customer getCustomer(@PathParam(Constantes.ID) String id) {
        return su.get(id);
    }

    @POST
    public Response create(Customer customer){
        su.add(customer);
        return Response.created(URI.create("/customers/"+ customer.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam(Constantes.ID) Integer id){
        su.delete(id);
        return Response.noContent().build();
    }

//
//    @PUT
//    public Usuario updateUsuario(Usuario usuario, @QueryParam("id") String id) {
//        Usuario aCambiar = DaoErrores.usuarios.get(0);
//        aCambiar.setName(usuario.getName());
//        aCambiar.setId(id);
//        return aCambiar;
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public Response delUsuario(@PathParam("id") String id) {
//        List<Usuario> users = DaoErrores.usuarios.stream().filter(usuario -> !usuario.getId().equals(id)).collect(Collectors.toList());
//
//        if (users.size() == DaoErrores.usuarios.size())
//            return Response.status(Response.Status.NOT_FOUND).entity(null).build();
//        else {
//            DaoErrores.usuarios = users;
//            return Response.status(Response.Status.NO_CONTENT).build();
//        }
//    }


}
