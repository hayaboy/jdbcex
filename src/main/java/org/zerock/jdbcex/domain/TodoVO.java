package org.zerock.jdbcex.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
//@Setter VO의 경우 getter만을 이용해서 읽기 전용으로 구성(필수는 아님)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder // 주로 테스트 용도로 임의의 데이터를 객체에 넣어 데이터를 만들어주는(builder) 어노테이션
public class TodoVO {

    private Long tno;

    private String title;

    private LocalDate dueDate;

    private boolean finished;

}
