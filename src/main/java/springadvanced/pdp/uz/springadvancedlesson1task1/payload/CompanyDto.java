package springadvanced.pdp.uz.springadvancedlesson1task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Address;


import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    @NotNull(message = "companyName is required")
    private String companyName;

    @NotNull(message = "directorName is required")
    private String directorName;

    @NotNull(message = "stir is required")
    private String stir;

    @NotNull(message = "addressId is required")
    private Long addressId;
}
