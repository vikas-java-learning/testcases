package Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Users {

    private Integer id;

    private String first_name;

    private String last_name;

    private String avatar;

    private String email;
}
