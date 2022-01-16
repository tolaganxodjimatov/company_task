package uz.teasy.apirestfull.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.teasy.apirestfull.entity.Company;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    @NotNull(message = "name - can't be empty!")
    private String name;

    @NotNull(message = "company_id - can't be empty!")
    private Integer company_id;

}
