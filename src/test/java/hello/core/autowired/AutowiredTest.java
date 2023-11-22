package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    }


    //주입 할 Bean이 존재하지 않아도 의존성 주입을 수행 하도록 할 때
    static class TestConfig {

        @Autowired(required = false)
        public void autowiredTest(Member noBean1) { //메서드 호출 자체가 안됨
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void autowiredTest2(@Nullable Member noBean2) { //null 반환
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void autowiredTest3(Optional<Member> noBean3) { //Optional.empty 반환
            System.out.println("noBean3 = " + noBean3);
        }
    }

}
