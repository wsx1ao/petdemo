package org.csu.mypetstore.controller;


import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private Order order;

    @GetMapping("login")
    public String view() {
        return "manager/signon";
    }

    @PostMapping("signon")
    public String signon(String username, String password, Model model) {

        if (username.equals("wsx") && password.equals("123")) {
            return "manager/main";
        } else {
            String msg = "Invalid username or password.  Signon failed.";
            model.addAttribute("msg", msg);
            return "manager/signon";
        }
    }

    @GetMapping("ordermanager")
    public String listOrders(Model model) {
        List<Order> orderList = orderService.getallOrders();
        model.addAttribute("orderList", orderList);
        return "manager/listOrders";
    }

    @GetMapping("modifyorder")
    public String viewOrder(int orderId, Model model) {
        order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        return "manager/modifyorder";
    }

    @GetMapping("deleteorder")
    public String deletOrder(int orderId) {
        orderService.deleteOrder(orderId);
        return "manager/success";
    }

    @PostMapping("suremodifyorder")
    public String editAccount(Order order, Model model) {
        orderService.updateorder(order);
        return "manager/listOrders";
    }
}
