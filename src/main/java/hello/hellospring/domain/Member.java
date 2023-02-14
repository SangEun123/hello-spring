package hello.hellospring.domain;

//회원 객체
public class Member {
    //시스템 저장용 아이디
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
