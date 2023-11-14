package service;


import dao.CustomerDao;
import dao.OrdersDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import model.Customer;
import model.ErrorC;
import model.Order;
import service.model.CustomerXml;
import service.model.OrderXml;
import service.model.OrdersXml;

import java.io.File;
import java.util.List;

public class CustomerService {

    private final CustomerDao customerDao;
    private final OrdersDao ordersDao;

    @Inject
    public CustomerService(CustomerDao customerDao, OrdersDao ordersDao) {
        this.customerDao = customerDao;
        this.ordersDao = ordersDao;
    }

    public Either<ErrorC, List<Customer>> getAll() {
        return customerDao.getAll();
    }

    public Either<ErrorC, Integer> saveAutoIncrementalID(Customer customer) {
        return customerDao.save(customer);
    }

    public Either<ErrorC, Integer> delete(int customerId, boolean confirm) {
        if (confirm) {
            saveOrdersXML(customerId);
        }
        return customerDao.delete(customerId, confirm);
    }


    public Either<ErrorC, Integer> update(Customer customerUpdated) {
        return customerDao.update(customerUpdated);
    }

    public Either<ErrorC, Customer> getCustomerById(int id) {
        return customerDao.get(id);
    }

    public List<Integer> getAllIdsCustomer() {
        List<Customer> customers = customerDao.getAll().get();
        return customers.stream().map(Customer::getId).toList();

    }


    private void saveOrdersXML(int customerId) {
        try {
            JAXBContext context = JAXBContext.newInstance(CustomerXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


            List<Order> orders = ordersDao.get(customerId).get();

            List<OrdersXml> ordersXml = orders.stream().map(order ->
                    new OrdersXml(
                            order.getOrderItems().stream().map(orderItem ->
                                    new OrderXml(orderItem.getOrderId(), orderItem.getMenuItemId(), orderItem.getQuantity()
                            )).toList())).toList();


            CustomerXml customerXml = new CustomerXml(customerId, ordersXml);

            marshaller.marshal(customerXml, new File("data/customer" + customerId + ".xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
