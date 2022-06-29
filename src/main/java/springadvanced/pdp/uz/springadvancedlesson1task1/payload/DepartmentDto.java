package springadvanced.pdp.uz.springadvancedlesson1task1.payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "companyId is required")
    private Long companyId;
}
