package com.dallotech.arcard.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaveImageDto {

    private String fileName;
    private boolean saveStatus;
}
