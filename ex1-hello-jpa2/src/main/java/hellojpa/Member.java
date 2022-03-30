package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

//이 어노테이션이 있어야 JPA를 사용하는 클래스라고 인식하고 관리함.
//@Entity
//public class Member {
//
//    @Id
//    private Long id;
//
//    private String name;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}

//@Entity
//public class Member {
//    @Id
//    private Long id;
//
//    //Column의 insertable,updatable 둘다 값이 변경되거나, 값을 넣을떄, 이 컬럼을 반영할지 말지를 정하는 것,
//    @Column(name = "name",nullable=false) //데이터베이스에서 컬럼명을 지정할수도 있음, 없으면 변수명으로 만들어짐
//    private String username;

//    private Integer age; //int에 가장  맞는 타입으로 디비에 생성

    //enum의 경우, 디폴트가 ordinal인데, 이는 enum의 순서를 저장함.
    //순서를 저장하면 나중에 enum안에 값을 추가하게 되면, 기존과 순서가 달라지므로 순서를 저장하는 식으로 하면 안됨(A,B -> A,C,B 이렇게 추가되면 순서꼬임)
    //따라서 enum의 경우에는 반드시 STRING으로 할것
//    @Enumerated(EnumType.STRING)//디비에는 enum타입이 없는데, 이것을 사용하면 쓸수 있게 해줌
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    @Lob //varchar를 넘어서는 큰 값을 넣을때.
//    private String description;

    //자바8부터 들어온 타입으로, 최신의 하이버네이트는 이 타입들을 지원해준다.
    //따라서 Date의 경우에는 과거버전과의 호환성이슈가 있을때만 쓰고, 아래 두개중 하나를 쓰면 된다.
    //날짜타입의 경우, TemporalType.TIMESTAMP ,TemporalType.TIME,TemporalType.DATE 이 세개중 하나와 꼭 매핑해줘야 됨.
//    private LocalDate testLocalDate; // 년월만
//    private LocalDateTime testLocalDateTime; //년월일까지

//    public Member(){
//
//    }
//}

//sequece의 allocationSize를 너무 크게 잡으면, 다 못쓰고, 서버가 내려가면 그만큼 낭비임
//따라서 보통 50~100정도가 적절함.

/*엔티티기능들*/
//@Entity
//@SequenceGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1, allocationSize = 50) //allocationSize를 설정하면 오토로 증가시킬 시퀀스 값을 미리 많이 가져다가 메모리에 올려서 씀,
////@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
//public class Member{
//
//    //@Id는 직접 할당할때 쓰면됨(이것만 쓰면 직접 할당)
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//            generator = "MEMBER_SEQ_GENERATOR")
////    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
//    private Long id;
//
//    @Column(name="name",nullable = false)
//    private String username;
//
//    //JPA가 관리할때 생성자를 이용해서 이것저것 동작하기 때문에 만들어줘야됨.
//    public Member(){
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//}

/*연관관계 예제*/
@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
