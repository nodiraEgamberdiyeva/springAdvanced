package springadvanced.pdp.uz.springadvancedlesson1task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {
    @NotNull(message = "phoneNumber is required")
    private String phoneNumber;
    @NotNull(message = "addressId is required")
    private Long addressId;
    @NotNull(message = "departmentId is required")
    private Long departmentId;
}
