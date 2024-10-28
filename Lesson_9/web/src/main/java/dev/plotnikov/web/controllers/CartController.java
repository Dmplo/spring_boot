package dev.plotnikov.web.controllers;

import dev.plotnikov.web.models.CartDTO;
import dev.plotnikov.web.models.Order;
import dev.plotnikov.web.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    @PostMapping("/add")
    public String addToCart(Order order, RedirectAttributes redirectAttributes) {
        boolean isCreated = service.addToCart(order);
        if (isCreated) {
            redirectAttributes.addFlashAttribute("confirm", "Добавлено в корзину!");
            Long count = service.getCount();
            redirectAttributes.addFlashAttribute("count", count);
        } else {
            redirectAttributes.addFlashAttribute("message", "Ошибка добавления!");
        }
        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        String url = "/";
        boolean isSuccessful = service.buyProduct(id);
        if (isSuccessful) {
            redirectAttributes.addFlashAttribute("confirm", "Покупка совершена!");
            Long count = service.getCount();
            if (count == 0) {
                redirectAttributes.addFlashAttribute("count", count);
            } else {
                List<CartDTO> cart = service.getCart();
                redirectAttributes.addFlashAttribute("cart", cart);
                url = "/cart";
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Не удалось совершить покупку!");
        }
        return "redirect:" + url;
    }


    @GetMapping
    public String getCart(Model model) {
        List<CartDTO> cart = service.getCart();
        Long count = service.getCount();
        model.addAttribute("count", count);
        model.addAttribute("cart", cart);
        return "cart.html";
    }
}
