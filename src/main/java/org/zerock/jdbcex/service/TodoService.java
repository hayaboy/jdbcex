package org.zerock.jdbcex.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVO;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {

    INSTANCE;


    // TodoService는 ModelMapper와 TodoDAO를 이용할 수 있도록 구성
    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService(){
        dao = new TodoDAO();
        modelMapper=MapperUtil.INSTANCE.get();
    }

    //새로운 TodoDTO를 등록하는 기능
    public void register(TodoDTO todoDTO) throws Exception{

        // DTO -> VO로 변환
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info("DTO에서 변환된 VO" + todoVO);

        dao.insert(todoVO);

    }

    public List<TodoDTO> listAll() throws Exception {

        List<TodoVO> voList = dao.selectAll();
        log.info("voList.................");
        log.info("서비스층에서의 voList 객체 :" + voList);

        List<TodoDTO> dtoList = voList.stream(). map(vo -> modelMapper.map(vo,TodoDTO.class)).collect(Collectors.toList());


        return dtoList;
    }


    public TodoDTO get(Long tno)throws Exception {
        log.info("tno: " + tno);
        TodoVO todoVO =dao.selectOne(tno);
        TodoDTO todoDTO =  modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }


    public void modify(TodoDTO todoDTO)throws Exception {


        log.info(" 서비스에서의 수정할 todoDTO: " + todoDTO );

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);


        dao.updateOne(todoVO);

    }


    public void remove(Long tno)throws Exception {

        log.info("삭제하고자 할 tno: " + tno);
        dao.deleteOne(tno);
    }
}
