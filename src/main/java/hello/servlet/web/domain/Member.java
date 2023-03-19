package hello.servlet.web.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
    private Long id;
    private String username;
    private int age;

}
