package hellojpa;


import javax.persistence.*;

//TABLE_PER_CLASS 전략에서는 상위테이블을 추상 클래스로 만들어 주어야 함.
//그냥 클래스로 만들면, 이 테이블 단독으로 쓰이는 경우가 있다고 생각하고 JPA가 이를 테이블로 만듦
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn
public abstract class Item extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;


    private String name;

    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
