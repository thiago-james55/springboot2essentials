package academy.devdojo.springboot2.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnimePostRequestBody {

    @NotBlank(message = "The name of anime can't be empty!")
    private String name;
}
