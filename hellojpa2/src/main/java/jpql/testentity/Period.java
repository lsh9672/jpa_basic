package jpql.testentity;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class Period {


    private String test_time;

    private String test_day;
}
