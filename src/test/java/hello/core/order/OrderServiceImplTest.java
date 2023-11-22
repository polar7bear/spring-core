package hello.core.order;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

    //생성자 주입을 사용하면 아래와 같이 순수 자바코드만으로도 테스트가 용이하다.
    @Test
    void createOrder() {
        MemberRepository memberRepository = new MemoryMemberRepository();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberRepository.save(member);
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());

        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}