package dao.impl;

import config.Configuration;
import config.common.Constants;
import dao.OrderItemsDao;
import io.vavr.control.Either;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.log4j.Log4j2;
import model.ErrorC;
import model.Order;
import model.xml.OrderItemXml;
import model.xml.OrderXml;
import model.xml.OrdersXml;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Log4j2
public class OrderItemsDaoImpl implements OrderItemsDao {
    Path xmlFIle = Paths.get(Configuration.getInstance().getPropertyTxt("xmlOrdersPath"));

    @Override
    public Either<ErrorC, OrdersXml> getAll() {
        OrdersXml orderList = null;
        try {
            JAXBContext context = JAXBContext.newInstance(OrdersXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Unmarshaller unmarshaller = context.createUnmarshaller();


            //read the file
            orderList = (OrdersXml) unmarshaller.unmarshal(xmlFIle.toFile());


        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (orderList == null) {
            return Either.left(new ErrorC(Constants.ERROR_READING_FILE));
        }
        return Either.right(orderList);

    }

    @Override
    public Either<ErrorC, List<OrderItemXml>> get(int id) {
        List<OrderItemXml> result;
        OrdersXml allOrdersXml = getAll().get();

        result = allOrdersXml.getOrders().stream()
                .filter(orderXml -> orderXml.getId() == id)
                .map(OrderXml::getOrderItems)
                .flatMap(List::stream)
                .toList();
        if (result.isEmpty()) {
            return Either.left(new ErrorC("No orders found"));
        } else {
            return Either.right(result);
        }
    }

    @Override
    public Either<ErrorC, Integer> save(int idOrder,OrderItemXml c) {
        try {
            JAXBContext context = JAXBContext.newInstance(OrdersXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            OrdersXml allOrders = getAll().get();

            if(allOrders.getOrders().stream().noneMatch(orderXml -> orderXml.getId() == idOrder)){
                allOrders.addOrder(new OrderXml(idOrder,List.of(c)));
            }else{
                allOrders.getOrders().stream().filter(orderXml -> orderXml.getId() == idOrder).findFirst().get().getOrderItems().add(c);
            }

            marshaller.marshal(allOrders, Files.newOutputStream(xmlFIle));

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Either.right(1);
    }

    @Override
    public Either<ErrorC, Integer> delete(int idOrder) {
        try {
            JAXBContext context = JAXBContext.newInstance(OrdersXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            OrdersXml allOrdersXml = getAll().get();

            while (allOrdersXml.getOrders().stream().anyMatch(orderXml -> orderXml.getId() == idOrder)) {
                allOrdersXml.getOrders().removeIf(orderXml -> orderXml.getId() == idOrder);
            }

            marshaller.marshal(allOrdersXml, Files.newOutputStream(xmlFIle));

            if (allOrdersXml.getOrders().isEmpty()) {
                return Either.left(new ErrorC("No orders found"));
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Either.right(1);
    }

    @Override
    public Either<ErrorC, Integer> update(Order c) {
        return null;
    }
}
