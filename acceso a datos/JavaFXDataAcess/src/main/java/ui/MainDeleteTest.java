package ui;


import config.Configuration;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.xml.OrdersXml;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainDeleteTest{

    public static void main(String[] args) throws Exception {
        JAXBContext context = JAXBContext.newInstance(OrdersXml.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        Path xmlFIle = Paths.get(Configuration.getInstance().getPropertyTxt("xmlOrdersPath"));

        //read XML document from the file
        OrdersXml ordersXml = (OrdersXml) unmarshaller.unmarshal(xmlFIle.toFile());


//        OrderXml newOrder = new OrderXml(1, List.of(new OrderItemXml("food",12)));
//        ordersXml.addOrder(newOrder);

        //save XML document to the file
        marshaller.marshal(ordersXml, Files.newOutputStream(xmlFIle));

        System.out.println("********* Result of reading XML document from the file***************");
        System.out.println(ordersXml);


        //marshaller.marshal(ordersXml, System.out);

    }
}
