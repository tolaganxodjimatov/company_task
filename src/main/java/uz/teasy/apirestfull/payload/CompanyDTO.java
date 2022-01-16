package uz.teasy.apirestfull.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.teasy.apirestfull.entity.Address;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    @NotNull(message = "corpName - can't be empty!")
    private String corpName;

    @NotNull(message = "directorName - can't be empty!")
    private String directorName;

    @NotNull(message = "address_id - can't be empty!")
    private Integer address_id;

}
