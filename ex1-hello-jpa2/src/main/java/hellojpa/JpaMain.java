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
            List<Member> resultList = em.createQuery("select m from Member as m")
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : resultList) {
                System.out.println("member.getName() = " + member.getName());
            }
            tx.commit();

        }catch (Exception e){
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();

    }
}
