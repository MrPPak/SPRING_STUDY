package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/*
    객체의 생성과 연결은 AppConfig가 담당
    DIP의 완성 : MemberServiceImpl은 MemberRepository 즉 추상에만 의존하면 된다. 이제 구체 클래스를 몰라도 된다.
    관심사의 분리 : 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.
 */
public class AppConfig {

    /*
        MemberServiceImpl 입장에서는 생성자를 통해 어떤 구현 객체가 들어올지(주입될지) 알 수 없다.
        MemberServiceImpl 생성자를 통해 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정된다
        MemberServiceImpl은 이제부터 **의존관계에 대한 고민은 외부(AppConfig)에 맡기고 실행에만 집중하면 된다.**
    */

    /*
        클라이언트인 memberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서
        DI(Dependency Injection) 우리말로 의존관계 주입 또는 의존성 주입이라 한다.
     */
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
