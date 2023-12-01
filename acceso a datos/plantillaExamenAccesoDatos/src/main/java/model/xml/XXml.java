package model.xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import model.X;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class XXml {
    @XmlElement(name = "order")
    List<X> xs;

    public XXml(List<X> xs) {
        this.xs = new ArrayList<>();
    }

    public void addX(X x) {
        xs.add(x);
    }

    @Override
    public String toString() {
        return "XXml{" +
                "xs=" + xs +
                '}';
    }
}
