package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)//데이터변경은 트랜잭션이 꼭 있어야한다.
//조회만 하는 경우에는 리드온리로 읽기만 하게끔.
@RequiredArgsConstructor //final
public class MemberService {

    private final MemberRepository memberRepository;

   /* @Autowired //@RequiredArgsConstructor -어노테이션을 쓰면 생성자 자동 생성.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplidateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //검증메서드 - 중복회원검증
    private void validateDuplidateMember(Member member) {
        //exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) { //이름으로 조회되면 이미 가입된회원이 있다는 뜻
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Member findOne(Long memberId) { //단건조회
        return memberRepository.findOne(memberId);
    }
}
