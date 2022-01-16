package uz.teasy.apirestfull.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotNull(message = "street - can't be empty!")
    private String street;
    @NotNull(message = "homeNumber - can't be empty!")
    private String homeNumber;
}
