package dao.retrofit.modelo;

import lombok.Data;

import java.util.List;

@Data
public class ResponseCities {
    private List<ResponseCityItem> responseCityItems;
}