package main.modelo;


import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "room")
@XmlAccessorType(XmlAccessType.FIELD)
public class Room {

    @XmlAttribute(name = "id")
    private String id;
    @XmlElement(name = "door")
    private List<Door> doors;
    @XmlElement(name = "description")
    private String description;
}
