package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {

        //싱글톤 Bean에서 Prototype Bean 사용 시 새로운 빈을 계속 생성 해주기 위해서는
        //ObjectProvider나 ObjectFactory라는 것을 사용하면 됨
//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        //ObjectFactory와 ObjectProvider 둘다 스프링에 의존적이다
        //ObjectProvider는 상속, 옵션 처리나 스트림 처리 등 편의기능이 많음.


        //자바에서 제공하는 JSR-330 Provider
        //자바에서 제공하기 때문에 스프링 컨테이너에서만 사용할 수 있는 것이 아닌 다른 컨테이너에서도 사용 가능함.
        //하지만 라이브러리 추가 해야함.
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            //PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); -> ObjectFactory나 ObjectProvider 사용할때 메서드
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); //자바에서 제공하는 JSR-330 Provider를 사용 할 때는 get()
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
