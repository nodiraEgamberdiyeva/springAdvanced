package springadvanced.pdp.uz.springadvancedlesson1task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    @NotNull(message = "street is required")
    private String street;
    @NotNull(message = "homeNumber is required")
    private String homeNumber;
}
