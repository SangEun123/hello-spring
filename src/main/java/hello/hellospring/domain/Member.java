package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//회원 객체
@Entity
public class Member {

//    IDENTITY: DB에서 아이디를 자동 생성해주는 것
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //시스템 저장용 아이디; PK
    private Long id;
    private String name;

    //getter and setter: alt+Insert
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
