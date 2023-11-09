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
@XmlRootElement(name = "dungeon")
@XmlAccessorType(XmlAccessType.FIELD)
public class Mazmorra {

    @XmlElement(name = "room")
    private List<Room> rooms;

}
