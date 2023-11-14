package service.model;

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
    @XmlElement
    private int menuItemId;
    @XmlElement
    private int quantity;

    @Override
    public String toString() {
        return "OrderXml{" +
                "id=" + id +
//                ", orderItems=" + orderItems +
                '}';
    }
}
