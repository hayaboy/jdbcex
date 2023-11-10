package org.zerock.jdbcex.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.zerock.jdbcex.domain.TodoVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class TodoDAO {


//    public String getTime(){
//        String now = null;
//        //try with resource
//
//        try(Connection connection  = ConnectionUtil.INSTANCE.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("select now()");
//            ResultSet resultSet =preparedStatement.executeQuery();
//        ){
//
//            resultSet.next();
//
//            now = resultSet.getString(1);
//            System.out.println(now);
//        }catch (Exception e){
//            System.out.println("시간 조회시 예외 발생");
//        }
//
//        return now;
//    }



    public String getTime() throws Exception{
        String now = null;
        //try with resource 대체 @Cleanup
        //무슨 일이 일어나든 상관없이 close 메서드를 호출하여 주석을 추가한 변수 선언이 정리되도록 합니다.
        // 범위 끝까지 지역 변수 선언 다음에 오는 모든 명령문을 finally 작업으로 리소스를 닫는 try 블록으로 래핑하여 구현됩니다.

        @Cleanup Connection connection  = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select now()");
        @Cleanup ResultSet resultSet =preparedStatement.executeQuery();

            resultSet.next();

            now = resultSet.getString(1);
            System.out.println(now);
        return now;
    }


    //글 전체 조회
    public List<TodoVO> selectAll()throws Exception  {

        String sql = "select * from tbl_todo";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<TodoVO> list = new ArrayList<>();

        while(resultSet.next()){

          TodoVO vo=  TodoVO.builder().tno(resultSet.getLong("tno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished")).build();

          list.add(vo);

        }


        return list;
    }


    //글 추가
    public void insert(TodoVO vo) throws Exception {

        String sql = "insert into tbl_todo (title, dueDate, finished) values (?, ?, ?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, vo.getTitle());
        preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
        preparedStatement.setBoolean(3, vo.isFinished());
        preparedStatement.executeUpdate();
    }



    //글 하나 조회(글번호를 매개변수)
    public TodoVO selectOne(Long tno)throws Exception {

        String sql = "select * from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        TodoVO vo = TodoVO.builder()
                .tno(resultSet.getLong("tno"))
                .title(resultSet.getString("title"))
                .dueDate( resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();
        return vo;
    }




    //글 하나 삭제 (글번호를 매개변수)

    public void deleteOne(Long tno) throws Exception {

        String sql = "delete from tbl_todo where tno = ?";

        log.info("삭제 전");
        @Cleanup Connection    connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);

        preparedStatement.executeUpdate();
        log.info("삭제   후 ");

    }



    //글 하나 수정(글 객체를 매개 변수)
    public void updateOne(TodoVO todoVO)throws Exception{

        String sql = "update tbl_todo set title =?, dueDate = ?, finished = ? where tno =?";

        log.info("업데이트(해야할 일 수정) 에서의  sql문 "  + sql);

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, todoVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
        preparedStatement.setBoolean(3, todoVO.isFinished());
        preparedStatement.setLong(4, todoVO.getTno());

        preparedStatement.executeUpdate();

        log.info("업데이트 함 ");
    }


}
