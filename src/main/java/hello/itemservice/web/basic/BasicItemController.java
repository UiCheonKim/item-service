package hello.itemservice.web.basic;

import hello.itemservice.domain.Item;
import hello.itemservice.domain.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String itmes(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String Item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

  //  @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {
        /*
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        */

        // @ModelAttribute 은 Item 객체를 생성하고, 요청 파라미터 값을 프로퍼티 접근법(SetXxx)으로 입력해준다.

        itemRepository.save(item);

        // model.addAttribute("item", item); // 주석 처리 해도 됨
        // 자동으로 view 에 넣어주는 역할까지 함
        // ex) @ModelAttribute("hello") Item item => model.addAttribute("hello", item);

        return "basic/item";
    }

 //   @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        /*
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        */

        // @ModelAttribute 은 Item 객체를 생성하고, 요청 파라미터 값을 프로퍼티 접근법(SetXxx)으로 입력해준다.


        itemRepository.save(item);

        // Item -> item, HelloData -> helloData 소문자로 바뀜
        // model.addAttribute("item", item); // 주석 처리 해도 됨
        // 자동으로 view 에 넣어주는 역할까지 함
        // ex) @ModelAttribute("hello") Item item => model.addAttribute("hello", item);

        return "basic/item";
    }

 //   @PostMapping("/add")
    public String addItemV4(Item item) {
        /*
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        */

        // @ModelAttribute 은 Item 객체를 생성하고, 요청 파라미터 값을 프로퍼티 접근법(SetXxx)으로 입력해준다.
        // String, int 단순 타입은 @RequestParam 자동 적용, 내가 만든 임의의 객체는 @ModelAttribute 적용

        itemRepository.save(item);

        // Item -> item, HelloData -> helloData 소문자로 바뀜
        // model.addAttribute("item", item); // 주석 처리 해도 됨
        // 자동으로 view 에 넣어주는 역할까지 함
        // ex) @ModelAttribute("hello") Item item => model.addAttribute("hello", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV5(Item item) {
        /*
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        */

        // @ModelAttribute 은 Item 객체를 생성하고, 요청 파라미터 값을 프로퍼티 접근법(SetXxx)으로 입력해준다.
        // String, int 단순 타입은 @RequestParam 자동 적용, 내가 만든 임의의 객체는 @ModelAttribute 적용

        itemRepository.save(item);

        // Item -> item, HelloData -> helloData 소문자로 바뀜
        // model.addAttribute("item", item); // 주석 처리 해도 됨
        // 자동으로 view 에 넣어주는 역할까지 함
        // ex) @ModelAttribute("hello") Item item => model.addAttribute("hello", item);

        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        /*
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        */

        // @ModelAttribute 은 Item 객체를 생성하고, 요청 파라미터 값을 프로퍼티 접근법(SetXxx)으로 입력해준다.
        // String, int 단순 타입은 @RequestParam 자동 적용, 내가 만든 임의의 객체는 @ModelAttribute 적용

        Item saved = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saved.getId());
        redirectAttributes.addAttribute("status", true);


        // Item -> item, HelloData -> helloData 소문자로 바뀜
        // model.addAttribute("item", item); // 주석 처리 해도 됨
        // 자동으로 view 에 넣어주는 역할까지 함
        // ex) @ModelAttribute("hello") Item item => model.addAttribute("hello", item);

        // redirectAttributes 에 넣은 itemId 값이 치환된다.
        // redirectAttributes 를 사용하면 URL 인코딩도 해주고, pathVarible 쿼리 파라미터까지 처리해준다.
        return "redirect:/basic/items/{itemId}"; // 남는 애들 (status 같은 경우) 쿼리파라미터 형식으로 넘어감 (?status=true)
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }



    // 테스트용 데이터 추가
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
