package com.Client.Managment.Domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/*
 * @Author: Orlando Yepes Espitia
 * @Date: 2025/26/04
 * @Description: This class is a wrapper for a list of Client objects. It uses the Jackson library to serialize and deserialize the list to and from JSON format. The @JsonRootName annotation specifies the root name of the JSON object, and the @JsonValue annotation indicates that the list of clients should be serialized as the value of the root object.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "CLIENTS")
public class ClientListWrapper {

    @JsonValue
    private List<Client> clients;
}