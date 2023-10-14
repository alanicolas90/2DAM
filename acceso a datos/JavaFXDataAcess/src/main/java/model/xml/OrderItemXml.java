package model.xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItemXml {
    String menuItem;
    int quantity;

    public OrderItemXml() {
        this.menuItem = null;
    }

    @Override
    public String toString() {
        return "OrderItemXml{" +
                "menuItem='" + menuItem + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
