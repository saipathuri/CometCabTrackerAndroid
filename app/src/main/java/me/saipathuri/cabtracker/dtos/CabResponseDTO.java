package me.saipathuri.cabtracker.dtos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saipathuri on 10/13/17.
 */

public class CabResponseDTO {
    @SerializedName("cabs")
    List<CabDTO> cabs;

    public CabResponseDTO() {
        cabs = new ArrayList<CabDTO>();
    }

    public static CabResponseDTO parseJSON(String response){
        Gson gson = new GsonBuilder().create();
        CabResponseDTO cabResponseDTO = gson.fromJson(response, CabResponseDTO.class);
        return cabResponseDTO;
    }

    public List<CabDTO> getCabs() {
        return cabs;
    }
}
