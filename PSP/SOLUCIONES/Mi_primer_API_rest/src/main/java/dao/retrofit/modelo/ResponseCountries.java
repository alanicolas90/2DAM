package dao.retrofit.modelo;


import lombok.Data;

import java.util.List;


@Data
public class ResponseCountries {
    private final List<ResponseCountryItem> responseCountryItems;

    public ResponseCountries(List<ResponseCountryItem> responseCountryItems) {
        this.responseCountryItems = responseCountryItems;
    }
}