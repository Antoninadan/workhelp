package ua.i.mail100.annotation.lombok;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "create")
public class Account {
    @Getter
    @Setter
    Long id;

    @NonNull
    @Getter
    @Setter
    User owner;

    @Getter
    @Setter
    BigDecimal amount;
}
