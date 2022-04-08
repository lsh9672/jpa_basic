package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //애플리케이션 로딩시점에 딱 하나만 만들어야됨.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); //쉽게 생각해서 디비 커넥션 하나 얻었다고 생각.

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamB);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();

            System.out.println("resultCount = " + resultCount);

//            /*NamedQuery*/
//            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
//                    .setParameter("username", "회원1")
//                    .getResultList();
//
//            for (Member member : resultList) {
//                System.out.println("member = " + member);
//            }

//            String query = "select m From Member m join fetch m.team";
//            String query = "select distinct t From Team t join fetch t.members";
//            String query = "select m from Member m where m = :member";
//
//            List<Team> result = em.createQuery(query, Team.class)
//                    .getResultList();
//            Member result = em.createQuery(query, Member.class)
//                    .setParameter("member", member1)
//                    .getSingleResult();

//            for (Team team : result) {
//                System.out.println("team.getName() = " + team.getName() + "|members=" + team.getMembers().size());
//                for (Member member : team.getMembers()) {
//                    System.out.println("-> member = " + member);
//                }
//            }


//            Member member1 = new Member();
//            member1.setUsername("관리자1");
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("관리자2");
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            String query = "select m.team From Member m";
//
//            List<Team> result = em.createQuery(query, Team.class)
//                    .getResultList();
//
//            for (Team s : result) {
//                System.out.println("s = " + s);
//            }

//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//
//            member.changeTeam(team);
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();

//            String query = "select coalesce(m.username, '이름없는 회원') from Member m ";


//            String query =
//                    "select " +
//                            "case when m.age <= 10 then '학생요금' " +
//                            "     when m.age >= 60 then '경로요금' " +
//                            "     else '일반요금' " +
//                            "end " +
//                    "from Member m";
//            List<String> result = em.createQuery(query, String.class).getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

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
