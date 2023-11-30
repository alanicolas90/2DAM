package dao.retrofit.modelo;

import lombok.Data;

import java.util.List;

@Data
public class ResponseStates {
    private List<ResponseStateItem> responseStateItemList;
}