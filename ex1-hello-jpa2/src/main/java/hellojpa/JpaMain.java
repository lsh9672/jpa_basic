package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        //애플리케이션 로딩시점에 딱 하나만 만들어야됨.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //디비에 쿼리를 날리고 종료되는 그러한 일을 할때마다 만들어서 사용해야됨. - 고객의 요청마다 계속 만들어서 씀
        EntityManager em = emf.createEntityManager(); //쉽게 생각해서 디비 커넥션 하나 얻었다고 생각.

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            /*연관관계 연습*/
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("MemberA");
            member.setTeam(team);
            em.persist(member);




            em.flush();
            em.clear();




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
        } finally{
            em.close();
        }

        emf.close();

    }
}
