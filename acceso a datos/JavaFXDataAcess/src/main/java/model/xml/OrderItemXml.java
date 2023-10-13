package model.xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    }

    @Override
    public String toString() {
        return "OrderItemXml{" +
                "menuItem='" + menuItem + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
