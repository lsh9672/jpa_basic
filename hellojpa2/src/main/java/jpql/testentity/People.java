package jpql.testentity;
/*임베디드 타입 확인*/

import javax.persistence.*;

@Entity
public class People {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Period period;

    @Embedded
    private BodyInfo bodyInfo;
}
