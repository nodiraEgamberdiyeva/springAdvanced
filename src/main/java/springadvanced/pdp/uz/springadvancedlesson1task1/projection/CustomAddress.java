package springadvanced.pdp.uz.springadvancedlesson1task1.projection;

import org.springframework.data.rest.core.config.Projection;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Address;

@Projection(types = Address.class)
public interface CustomAddress {
    String getHomeNumber();
}
