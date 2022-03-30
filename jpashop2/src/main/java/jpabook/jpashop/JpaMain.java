package jpabook.jpashop;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        //애플리케이션 로딩시점에 딱 하나만 만들어야됨.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //디비에 쿼리를 날리고 종료되는 그러한 일을 할때마다 만들어서 사용해야됨. - 고객의 요청마다 계속 만들어서 씀
        EntityManager em = emf.createEntityManager(); //쉽게 생각해서 디비 커넥션 하나 얻었다고 생각.

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Order order = new Order();
            order.addOrderItem(new OrderItem());

             tx.commit();

        }catch (Exception e){
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();

    }
}

