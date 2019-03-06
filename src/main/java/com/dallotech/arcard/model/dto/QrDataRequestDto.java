package com.dallotech.arcard.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QrDataRequestDto {


    @JsonProperty("qr_data")
    private String qrData;


}
