package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        //애플리케이션 로딩시점에 딱 하나만 만들어야됨.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //디비에 쿼리를 날리고 종료되는 그러한 일을 할때마다 만들어서 사용해야됨. - 고객의 요청마다 계속 만들어서 씀
        EntityManager em = emf.createEntityManager(); //쉽게 생각해서 디비 커넥션 하나 얻었다고 생각.

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //네이티브SQL
            em.createNativeQuery("select MEMBER_ID,city,street,zipcode,USERNAME from MEMBER").getResultList();


            //Criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));

            List<Member> resultList = em.createQuery(cq).getResultList();

//            List<Member> result = em.createQuery(
//                            "select m from Member m where m.username like '%kim%'",
//                            Member.class
//                    )
//                    .getResultList();
//            for (Member member : result) {
//                System.out.println("member = " + member);
//            }


//            Member member = new Member();
//            member.setUsername("member1");
//            member.setHomeAddress(new Address("city", "street", "10000"));
//
//
//            member.getFavoriteFoods().add("치킨");
//            member.getFavoriteFoods().add("족발");
//            member.getFavoriteFoods().add("피자");
//
//            member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
//            member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            System.out.println("================ start ==============");
//            Member findMember = em.find(Member.class, member.getId());

//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }
//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));
//
//            //컬렉션 안 치킨 -> 한식
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");
//
//            findMember.getAddressHistory().remove(new AddressEntity("old1", "street", "10000"));
//            findMember.getAddressHistory().add(new AddressEntity("newCity1", "street", "10000"));


//            Address address = new Address("city", "street", "10000");
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setHomeAddress(address);
//            em.persist(member);
//
//            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
//            Member member2 = new Member();
//            member2.setUsername("member2");
//            member2.setHomeAddress(copyAddress);
//            em.persist(member2);
//
//            member.getHomeAddress().setCity("newCity");


//            Child child1 = new Child();
//            Child child2 = new Child();
//
//            Parent parent = new Parent();
//
//            parent.addChild(child1);
//            parent.addChild(child2);
//
//            em.persist(parent);
////            em.persist(child1);
////            em.persist(child2);
//
//            em.flush();
//            em.clear();
//
//            Parent findParent = em.find(Parent.class, parent.getId());
////            findParent.getChildList().remove(0);
//
//            em.remove(findParent);

//            Team team = new Team();
//            team.setName("test1");
//            em.persist(team);
//
//            Team team2 = new Team();
//            team2.setName("test2");
//            em.persist(team2);
//
//            Member member = new Member();
//            member.setName("hello");
//            member.setTeam(team);
//
//            em.persist(member);
//
//            Member member2 = new Member();
//            member2.setName("hello2");
//            member2.setTeam(team2);
//
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
////            Member findMember = em.find(Member.class, member.getId());
////            System.out.println("findMember.getTeam().getClass() = " + findMember.getTeam().getClass());
////            System.out.println("=========");
////            System.out.println("findMember.getTeam().getName() = " + findMember.getTeam().getName());
//
//            List<Member> members = em.createQuery("SELECT m FROM Member m JOIN FETCH m.team", Member.class)
//                    .getResultList();

            //
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

//            Member findMember = em.getReference(Member.class, member.getId());
//            System.out.println("findMember = " + findMember.getClass()); //프록시~~~ 로 붙어서 나오는데, 이것이 JPA가 가짜로 만든 객체임
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
//            System.out.println("findMember.getName() = " + findMember.getName());
//            System.out.println("findMember.getclass = " + findMember.getClass());

//            Member member = new Member();
//            member.setCreatedBy("kim");
//            member.setName("user1");
//            member.setCreateDate(LocalDateTime.now());
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();

            /*상속관계 매핑 전략*/
//            Movie movie = new Movie();
//            movie.setDirector("aaaa");
//            movie.setActor("bbbb");
//            movie.setName("testMovie");
//            movie.setPrice(10000);
//
//            em.persist(movie);
//
//            em.flush();
//            em.clear();
//
//            Movie findMove = em.find(Movie.class, movie.getId());
//            System.out.println("findMove = " + findMove);



            /*연관관계 연습*/
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setName("MemberA");
//            member.setTeam(team);
//            em.persist(member);
//
//
//
//
//            em.flush();
//            em.clear();




//            System.out.println("findTeam = " + findTeam.getName());
            /*기본키 매핑 연습*/
//            Member member1 = new Member();
//            member1.setUsername("C");
//
//            Member member2 = new Member();
//            member2.setUsername("B");
//
//            Member member3 = new Member();
//            member3.setUsername("C");
//
//            System.out.println("==================");

            //기본키가 AUTO_INCREAMENT이면 예외적으로 insert쿼리를 커밋이 아닌 persist시점에 날림.(1차캐시에 key 값으로 기본키를 사용하기 때문에)
            //오토로 생성하면 기본키를 얻기 위해서는 디비에 조회를 해봐야됨.
//            em.persist(member1);//1, 51 => 처음에 1을 호출하는데, 이는 더미로 호출한것(50으로 설정했는데, 1이 나오니 다시 호출해서 51을 받아냄)
//            em.persist(member2);//이제 호출없이 메모리에서 가져옴
//            em.persist(member3);//메모리에서 가져옴
//
//            System.out.println("member1.getId() = " + member1.getId());
//            System.out.println("member2.getId() = " + member2.getId());
//            System.out.println("member3.getId() = " + member3.getId());
//
//            System.out.println("==================");

            
            /*저장*/
//            Member member = new Member();
//
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

            /*조회*/

//            Member findMember = em.find(Member.class, 1L);
//
//
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

            /*삭제*/
//            em.remove(findMember);

            /*수정*/
            //별도로 저장하는 로직이 없어도, JPA에서 조회한 객체는 JPA가 관리하기 때문에,변경이 되었는지 트랜잭션 시점에 체크하고, 변경이 있으면 업데이트 쿼리를 날리고 트랜잭션함.
//            findMember.setName("HelloJPA");

            /*JPQL*/
            //jpa 입장에서는 테이블을 대상으로 쿼리를 날리지 않음, 멤버 객체를 대상으로 쿼리를 날림.
            //아래에서 m은 테이블릐 Member가 아닌 Member객체임.
            //객체를 대상으로 쿼리를 날리는 건데, JPA가 dialect에 맞춰서 알아서 매핑된 정보대로 쿼리를 날림
            //즉, 이상태에서 dialect를 오라클로 바뀌면, 오라클에서 사용하는 쿼리에 맞춰서 변형되어 쿼리가 나감. - 즉, 디비가 바껴도 코드를 안바꿔도됨.
//            List<Member> resultList = em.createQuery("select m from Member as m")
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();
//
//            for (Member member : resultList) {
//                System.out.println("member.getName() = " + member.getName());
//            }
            tx.commit();

        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally{
            em.close();
        }

        emf.close();

    }
}
