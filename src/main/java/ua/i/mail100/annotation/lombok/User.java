package ua.i.mail100.annotation.lombok;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.math.BigDecimal;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class User {
    @Getter
    private Long id;

    @Getter
    @NonNull
    private String name;

    @Getter
    @Setter
    @NonFinal
    BigDecimal value;
}