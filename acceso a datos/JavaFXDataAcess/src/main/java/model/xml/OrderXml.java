package model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderXml {
    @XmlElement
    private int id;
    @XmlElement(name = "orderItem")
    private List<OrderItemXml> orderItems;

    @Override
    public String toString() {
        return "OrderXml{" +
                "id=" + id +
                ", orderItems=" + orderItems +
                '}';
    }
}
