package domain.servicios;

import dao.CustomerDao;
import dao.model.Customer;
import domain.modelo.errores.OtraException;
import io.vavr.control.Either;
import jakarta.errores.ApiError;
import jakarta.inject.Inject;
import jakarta.validation.Validator;

import java.util.List;


public class ServiciosUsuarios {


    private Validator validator;


    private CustomerDao dao;


    @Inject
    public ServiciosUsuarios(Validator validator, CustomerDao dao) {
        this.validator = validator;
        this.dao = dao;
    }

//    public Either<ApiError, Usuario> dameUno(String id)
//    {
//        return dao.dameUno(id);
//    }

    public List<Customer> dameTodos()
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
