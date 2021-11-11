package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

// test를통해 직접 main함수를 안돌려보고도 체크가능하다.

//순서에 의존하게 하면안됨.
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach(){
        repository.clearStore(); // 테스트가 끝날때마다 clear한다.
    }
    // MemberRepository -> interface
    // MemoryMemberRepository -> interface를 구현한 클래스
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        Member result = repository.findById(member.getId()).get(); //.get()해준이유가 optional<T> 클래스의객체값 얻기위함.
        assertThat(member).isEqualTo(result); // member의 객체가 result객체와 같은가?
        //Assertions.assertEquals(member, null);
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift + f6
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
