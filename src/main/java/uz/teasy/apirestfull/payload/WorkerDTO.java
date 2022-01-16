package uz.teasy.apirestfull.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.teasy.apirestfull.entity.Address;
import uz.teasy.apirestfull.entity.Department;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDTO {
    @NotNull(message = "name - cen't be empty!")
    private String name;

    @NotNull(message = "phoneNumber - cen't be empty!")
    private String phoneNumber;

    @NotNull(message = "address_id - cen't be empty!")
    private Integer address_id;

    @NotNull(message = "department_id - cen't be empty!")
    private Integer department_id;
}
