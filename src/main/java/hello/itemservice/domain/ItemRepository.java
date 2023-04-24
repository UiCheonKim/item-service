package hello.itemservice.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository // -> @Component 가 있어 Component Scan 대상이 됨
public class ItemRepository {

    // 스프링 컨테이너 안에서 쓰면 싱글톤이기 때문에 static 을 안써도 되는데
    // 따로 new 에서 쓰거나 할 때 static 안하면 객체 생성한 만큼 따로 생성된다.

    private static final Map<Long, Item> store = new HashMap<>();     // Item 의 아이디가 Long 타입 // static
    private static long sequence = 0L; // static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
        // values 를 이용해 collenction 으로 반환해도 되는데
        // ArrayList 에 넣으면 실제 store 에는 변화, 영향이 없기 때문에 감싸준다.
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
