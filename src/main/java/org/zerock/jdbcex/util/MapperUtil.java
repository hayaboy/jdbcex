package org.zerock.jdbcex.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;


public enum MapperUtil {

    INSTANCE;


    //ModelMapper - 객체 매핑을 수행하고 구성을 유지하며 TypeMap을 저장합니다.
    //객체 매핑을 수행하려면 map을 사용하세요.
    //한 유형을 다른 유형으로 매핑하도록 구성하려면 createTypeMap을 사용하세요.
    //특정 속성에 대한 매핑을 추가하려면 PropertyMap을 제공하는 addMappings를 사용하세요.
    //ModelMapper를 구성하려면 getConfiguration()을 사용하세요.
    //매핑의 유효성을 검사하려면 verify()를 사용하세요.

    private ModelMapper modelMapper;


    MapperUtil(){
      this.modelMapper  =new ModelMapper();

      // private으로 선언된 필드도 접근 가능하도록 설정,
      this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);

    }


    public ModelMapper get() {
        return modelMapper;
    }
}
