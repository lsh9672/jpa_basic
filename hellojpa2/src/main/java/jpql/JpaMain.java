package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //애플리케이션 로딩시점에 딱 하나만 만들어야됨.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); //쉽게 생각해서 디비 커넥션 하나 얻었다고 생각.

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);

            member.changeTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            String query = "select coalesce(m.username, '이름없는 회원') from Member m ";


//            String query =
//                    "select " +
//                            "case when m.age <= 10 then '학생요금' " +
//                            "     when m.age >= 60 then '경로요금' " +
//                            "     else '일반요금' " +
//                            "end " +
//                    "from Member m";
            List<String> result = em.createQuery(query, String.class).getResultList();

            for (String s : result) {
                System.out.println("s = " + s);
            }

//            String query = "select m from Member m inner join m.team t";
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();

//            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
//                    .getResultList();
//            List resultList = em.createQuery("select m.username, m.age from Member m")
//                    .getResultList();
//            for (Object o : resultList) {
//
//                Object[] temp = (Object[]) o;
//                for (Object o1 : temp) {
//                    System.out.println("o1 = " + o1);
//                }
//            }
//            Member findMember = result.get(0);
//            findMember.setAge(20);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
