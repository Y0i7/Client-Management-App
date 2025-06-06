package com.Client.Managment.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/*
 * @Author: Orlando Yepes Espitia
 * @Date: 2025/26/04
 * @Description: This class represents a client entity with fields for ID, name, last name, email, and date.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "CLIENT")
public class Client {
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("NAME")
    private String clientName;

    @JsonProperty("LAST_NAME")
    private String clientLName;

    @JsonProperty("EMAIL")
    private String clientEmail;

    @JsonProperty("DATE")
    private LocalDateTime clientDate;
}
