package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Catergory;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
//상속관계 전략잡기
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //한테이블에 넣는것
@DiscriminatorColumn(name = "dtype")//상속관계 매핑
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items" )
    private List<Catergory> catergories = new ArrayList<>();

    //---비즈니스 로직---//객체지향적
    //stck증가
    public void addSock(int quantity){
        this.stockQuantity += quantity;
    }
    //stock감소
    public void removeStock(int quantity) {
        int  restStock = this.stockQuantity - quantity;
        if(restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
