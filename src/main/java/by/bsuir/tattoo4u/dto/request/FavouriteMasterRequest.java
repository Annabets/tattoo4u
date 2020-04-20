package by.bsuir.tattoo4u.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FavouriteMasterRequest {

    @NotNull
    private Long masterId;
}
