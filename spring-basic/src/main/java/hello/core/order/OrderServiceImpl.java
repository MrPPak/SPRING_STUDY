package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;

    /*
        문제점 발견
        - 우리는 역할과 구현을 충실하게 분리했다. OK
        - 다형성도 활요하고, 인터페이스와 구현 객체를 분리했다. OK
        - OCP, DIP 같은 객체지향 설계 원칙을 충실히 준수했다 -> 그렇게 보이지만 사실은 아니다.

        DIP 관점
        지금까지 단순히 DiscountPolicy 인터페이스에만 의존한다고 생가했다.
        그러나 잘보면 클라이언트인 OrderServiceImpl이 DiscountPolicy 인터페이스 뿐만 아니라
        FixDiscountPolicy인 구체 클래스도 함께 의존하고 있다. -> DIP 위반!!
        ※ DIP : 의존성 역전 -> 구체화된 것에 의존하지말고 추상화된 것에 의존해라!

        OCP 관점
        FixDiscountPolicy 라는 구체화된 클래스에 의존하고 있으므로
        할인 정책을 바꿨을 때 즉 FixDiscountPolicy -> RateDiscountPolicy로 변경하는 순간
        OrderServiceImpl의 소스코드(클라이언트 코드)도 함께 변경해야 한다 -> OCP 위반!!
        ※ OCP : 개방 폐쇄의 법칙 -> 확장(변경)을 할 때 소스코드가 변경되면 안된다
     */
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        /*
            단일 책임의 원칙을 잘 지킨 사례
            할인에 관련된 것은 discountPolicy에서 처리한다.
            할인에 관련된 기능이 바뀌면 discountPolicy만 변경하면 됨 -> orderService에서 변경할 것은 없음
        */
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


}
