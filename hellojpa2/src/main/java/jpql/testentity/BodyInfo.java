package jpql.testentity;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class BodyInfo {

    private int weight;

    private int age;
}
