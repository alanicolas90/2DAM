package domain;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record Number(@NotNull @Max(10) @Min(0) int number) {

}
