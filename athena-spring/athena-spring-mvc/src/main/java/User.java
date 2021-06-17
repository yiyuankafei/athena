import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class User {

    @NotEmpty
    private String name;

    private Integer age = 18;

}
