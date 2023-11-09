package domain.servicios;

import dao.CustomerDao;
import dao.OrdersDao;
import dao.model.Customer;
import dao.model.Order;
import jakarta.inject.Inject;
import jakarta.validation.Validator;

import java.util.List;


public class OrderService {


    private OrdersDao dao;


    @Inject
    public OrderService(OrdersDao dao) {
        this.dao = dao;
    }

//    public Either<ApiError, Usuario> dameUno(String id)
//    {
//        return dao.dameUno(id);
//    }

    public List<Order> dameTodos()
    {
        return dao.getAll().get();
    }

//    public boolean borrar(String id )
//    {
//        return dao.borrar(id);
//    }
//
//    public boolean login(String user,String pass){
//
//        boolean loginOk=false;
//        if (dao.dameUsuarioPorNombre(user).isRight())
//        {
//            loginOk = pass.equals(dao.dameUsuarioPorNombre(user).get().getPassword());
//        }
//        return loginOk;
//
//    }
//
//    public  Usuario addUser(Usuario u)
//    {
//        final StringBuilder error = new StringBuilder();
//        validator.validate(u).stream().forEach(
//                testDtoConstraintViolation ->
//                        error.append(testDtoConstraintViolation.getMessage()));
//        if (!error.toString().isEmpty())
//            throw new OtraException(error.toString());
//        return dao.addUser(u);
//    }
//
//    public List<UsuarioEntity> dameTodosHibernate() {
//        return dao.dameTodosHibernate();
//    }
}
