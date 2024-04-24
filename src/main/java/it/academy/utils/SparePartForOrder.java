package it.academy.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SparePartForOrder implements Serializable {

    @SerializedName(value = "id")
    @Expose
    private Long id;

    @SerializedName(value = "quantity")
    @Expose
    private Integer quantity;

}
