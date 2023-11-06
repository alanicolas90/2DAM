package main.modelo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Door {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "dest")
    private String destination;
}
