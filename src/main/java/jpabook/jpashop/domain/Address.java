package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;


@Embeddable
@Getter //값타임은 게터만 만듦. 처음부터 정해진 값이긴 때문에
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() { //접근제한자 정리
    }
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
