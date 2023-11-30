package dao.retrofit.modelo;

import lombok.Data;

@Data
public class ResponseCountryItem {
    private final int id;
    private final String name;
    private final String iso2;
}