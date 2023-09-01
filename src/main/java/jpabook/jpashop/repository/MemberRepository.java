package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //컴포넌트 스캔으로 스프링빈으로 등록
@RequiredArgsConstructor
public class MemberRepository {
   // @Autowired//@PersistenceContext //jpa가 제공하는 표준 어노테이션
    private final EntityManager em;

    //저장로직 마들기
    public void save(Member member) {
        em.persist(member);
    }
    public Member findOne(Long id) { //단건조회
        return em.find(Member.class, id); //(타입, pk)
    }

    public List<Member> findAll() {      //JPQL                  //반환타입
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public List<Member> findByName(String name) { //특정이름에 의한 회원을 찾는거
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
