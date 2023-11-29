package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
//Bean 등록할 때 직접 @Qualifier를 명시 안해주고 어노테이션을 만들어서 사용 하는 이유
// -> @Qualifier("mainDiscountPolicy")를 구현체에서 직접 작성을 하게 되면,
// 문자열은 컴파일 단계에서 오류를 발생 시키지 않기 때문에 어노테이션을 직접 만들어서 사용하는 것이 좋음.
// 예를들어 "mainDiscountPolicy"에서 오타를 내도 에러를 일으키지 않음.
// 하지만 @MainDiscountPolicy 어노테이션에서 오타가 입력되면 에러가 발생함
