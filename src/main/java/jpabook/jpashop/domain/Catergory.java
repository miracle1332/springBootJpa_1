package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
public class Catergory {
    @Id @GeneratedValue
    @Column(name="category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="category_item",
            joinColumns = @JoinColumn(name="category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")) //다대다매핑을 하려면 일대다, 다대일로 풀어내는 중간테이블이 필요함!
    private List<Item> items = new ArrayList<>();

        //카테고리 안에서도 계층형 - 셀프로 양방향연관관계를 만듦
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private  Catergory parent;

    @OneToMany(mappedBy ="parent") //자식은 카테고리를 여러개 가질 수 있으므로
    private List<Catergory> child = new ArrayList<>();

    //--연관관계 편의 메서드
    public void addChildCategory(Catergory child) {
        this.child.add(child);
        child.setParent(this);
    }
}
