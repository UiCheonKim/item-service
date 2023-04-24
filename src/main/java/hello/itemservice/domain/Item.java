package hello.itemservice.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// @Data -> 위험함 노 사용 추천
// DTO -> 단순하게 데이터를 왔다 갔다 할 때 쓰는 경우에는 써도 괜챤
@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
