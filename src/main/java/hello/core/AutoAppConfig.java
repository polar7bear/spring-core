package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //basePackages = "hello.core.member",  <- basePackages를 지정 해주면 입력된 패키지를 포함한 하위 패키지들을 모두 스캔 해준다.
        //basePackages = {"hello.core.member", "hello.core.order"}, <- 이와 같이 여러 패키지를 지정해 줄 수도 있다.
        //basePackageClasses = AutoAppConfig.class, <- 이 속성은 해당 클래스가 위치한 패키지를 포함한 그 하위 패키지들까지 스캔 해주는 것이다.
        //위의 패키지 경로나 클래스를 지정해주지 않은 default값은 해당 클래스(AutoAppConfig)가 위치한 패키지를 포함한 그 하위 패키지들을 모두 스캔한다.
        //실무에서 권장하는 방법은 이런 설정 정보 클래스의 위치를 프로젝트 최상단에 위치하게 하고 default값으로 두는 것이다.
        //스프링 부트도 이 방법을 기본으로 제공한다고 한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //위와 같은 filter 속성으로 스캔 할 클래스를 제외 시킬수도 있다.
)
public class AutoAppConfig {
}
