package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {

@Id @GeneratedValue
@Column(name="order_id")
 private Long id;

@ManyToOne(fetch = FetchType.LAZY)//order와member는 다대일관계 //연관관계의 주인 = 외래키가 있는 곳
@JoinColumn(name="member_id")
 private Member member;

@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
private List<OrderItem> orderItems = new ArrayList<>();

@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JoinColumn(name="delivery_id")
private Delivery delivery;

private LocalDateTime orderDate; //주문시간

 @Enumerated(EnumType.STRING)
private OrderStatus status; //주문 상태(order, cancel)

//---연관관계 편의 메서드 - 양방향에서
 public void setMember(Member member) {
  this.member = member;
  member.getOrders().add(this);
 }

 public void addOrderItem(OrderItem orderItem) {
  orderItems.add(orderItem);
  orderItem.setOrder(this);
 }

 public void setDelivery(Delivery delivery) {
  this.delivery = delivery;
  delivery.setOrder(this);
 }

 //==생성메서드==// 생성하는 지점 되면 이것만 바꾸면 ㄷ됌...//주문 생성
 public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
   Order order = new Order();
   order.setMember(member);
   order.setDelivery(delivery);
   for(OrderItem orderItem:orderItems ) {
    order.addOrderItem(orderItem);
   }
   order.setStatus(OrderStatus.ORDER);//오더스테이터스를 오더상태로 처음 강제해놓음
   order.setOrderDate(LocalDateTime.now());
   return order;
 }

 //--비즈니스 로직--/
//주문취소
 public void cancel
}
