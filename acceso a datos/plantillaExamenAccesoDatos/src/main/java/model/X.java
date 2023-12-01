package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class X {

    private int id;
    private String name;

    @Override
    public String toString() {
        return "X{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
