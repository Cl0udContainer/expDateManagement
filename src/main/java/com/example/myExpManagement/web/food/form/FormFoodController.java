package com.example.myExpManagement.web.food.form;

import com.example.myExpManagement.domin.food.*;
import com.example.myExpManagement.domin.member.Member;
import com.example.myExpManagement.domin.session.SessionConst;
import com.example.myExpManagement.web.food.FoodValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/form/foods")
@RequiredArgsConstructor
public class FormFoodController {
    private final FoodRepository foodRepository;
    private final FoodValidator foodValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(foodValidator);
    }

    @ModelAttribute("foodTypes")
    public FoodType[] foodTypes() {
        return FoodType.values();
    }

    @ModelAttribute("storageCodes")
    public List<StorageCode> storageCodes() {
        List<StorageCode> storageCodes = new ArrayList<>();
        storageCodes.add(new StorageCode("REFR", "냉장 보관"));
        storageCodes.add(new StorageCode("FREEZING", "냉동 보관"));
        storageCodes.add(new StorageCode("ROOM", "상온 보관"));

        return storageCodes;
    }

    @GetMapping
    public String foods(Model model, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)Member loginMember) {
        List<Food> foods = foodRepository.findByMember(loginMember.getId());
        model.addAttribute("foods", foods);
        return "form/foods";
    }

    @GetMapping("/{foodId}")
    public String food(@PathVariable long foodId, Model model ) {
        Food food = foodRepository.findById(foodId);
        model.addAttribute("food", food);
        return "form/food";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute Food food, RedirectAttributes redirectAttributes, Model model) {

        model.addAttribute("food", new Food());
        return "form/addForm";
    }

    @PostMapping("/add")
    public String addFood(@Validated @ModelAttribute Food food, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                          @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)Member loginMember) {

        if (bindingResult.hasErrors()) {
            return "form/addForm";
        }

        // 검증 결과 이상 없을 때 실행하는 로직
        Food savedFood = foodRepository.save(food, loginMember.getId());
        redirectAttributes.addAttribute("foodId", savedFood.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/form/foods/{foodId}";
    }

    @GetMapping("/{foodId}/edit")
    public String editForm(@PathVariable Long foodId, Model model) {

        Food food = foodRepository.findById(foodId);
        model.addAttribute("food", food);
        return "form/editForm";
    }

    @PostMapping("/{foodId}/edit")
    public String edit(@PathVariable Long foodId, @Validated @ModelAttribute Food food, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form/editForm";
        }

        foodRepository.update(foodId, food);
        return "redirect:/form/foods/{foodId}";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null)
            session.invalidate();

        return "redirect:/";
    }
}
