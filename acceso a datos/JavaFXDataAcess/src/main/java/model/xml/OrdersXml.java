package model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Data
@Getter
@Setter
@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrdersXml {
    @XmlElement(name = "order")
    List<OrderXml> orders;


    public OrdersXml() {
        orders = new ArrayList<>();
    }

    public void addOrder(OrderXml order) {
        orders.add(order);
    }

    @Override
    public String toString() {
        return "OrdersXml{" +
                "orders=" + orders +
                '}';
    }
}
