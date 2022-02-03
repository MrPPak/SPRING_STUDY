package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {
    // ctrl + shift + n : 파일검색

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // value를 어떻게 넣을 것이냐 : Identity전략 (DB에서 자동주입)
    private Long id;
    private String name;

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
