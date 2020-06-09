package org.csu.mypetstore.controller;


import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private Order order;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private AccountService accountService;
    private static final List<String> LANGUAGE_LIST;
    private static final List<String> CATEGORY_LIST;

    static {
        List<String> langList = new ArrayList<String>();
        langList.add("ENGLISH");
        langList.add("CHINESE");
        LANGUAGE_LIST = Collections.unmodifiableList(langList);

        List<String> catList = new ArrayList<String>();
        catList.add("FISH");
        catList.add("DOGS");
        catList.add("REPTILES");
        catList.add("CATS");
        catList.add("BIRDS");

        CATEGORY_LIST = Collections.unmodifiableList(catList);
    }

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

    @GetMapping("accountmanager")
    public String listAccounts(Model model){
        List<Account> accountList=accountService.getallAccount();
        model.addAttribute("accountList",accountList);
        return "manager/listaccounts";
    }

    @PostMapping("editAccount")
    public String editAccount(Account account, String repeatedPassword, Model model) {
        if (account.getPassword() == null || account.getPassword().length() == 0 || repeatedPassword == null || repeatedPassword.length() == 0) {
            String msg = "密码不能为空";
            model.addAttribute("msg", msg);
            return "manager/edit_account";
        } else if (!account.getPassword().equals(repeatedPassword)) {
            String msg = "两次密码不一致";
            model.addAttribute("msg", msg);
            return "manager/edit_account";
        } else {
            System.out.print(account.getUsername());
            accountService.updateAccount(account);
            return "manager/main";
        }
    }

    @GetMapping("modifyorder")
    public String viewOrder(int orderId, Model model) {
        order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        return "manager/modifyorder";
    }
    @GetMapping("modifyaccount")
    public String viewAccount(String accountusername,Model model){
        Account account=accountService.getAccount(accountusername);
        model.addAttribute("account", account);
        model.addAttribute("LANGUAGE_LIST", LANGUAGE_LIST);
        model.addAttribute("CATEGORY_LIST", CATEGORY_LIST);
        return "manager/edit_account";
    }
    @GetMapping("deleteorder")
    public String deletOrder(int orderId) {
        System.out.print(orderId+"1");
        orderService.deleteOrder3(orderId);
        System.out.print(orderId+"2");
        orderService.deleteOrder2(orderId);
        System.out.print(orderId+"3");
        orderService.deleteOrder(orderId);
        return "manager/success";
    }

    @GetMapping("updateorder")
    public String updateOrder(int orderId,String orderStatus){
        if(orderStatus.equals("P")){
            orderService.updateStatusByOrderid(orderId,"UP");
        }
        else orderService.updateStatusByOrderid(orderId,"P");
        return "manager/success";
    }
    @PostMapping("suremodifyorder")
    public String editAccount(Order order, Model model) {
        orderService.updateorder(order);
        return "manager/listOrders";
    }
    @GetMapping("itemmanager")
    public String itemmanager(Model model) {
        List<Item> itemList = catalogService.getAllItem();
        model.addAttribute("itemList",itemList);
        return "manager/itemlist";
    }

    @PostMapping("searchItems")
    public String searchItems(String keyword, Model model, String itemId){
        if(keyword == null || keyword.length() < 1){
            String msg = "Please enter a keyword to search for, then press the search button.";
            model.addAttribute("msg",msg);
            return "manager/error";
        }else {
            Item item = catalogService.getItem(keyword);
            if(item == null){
                String msg = "This itemId don't exist";
                model.addAttribute("msg",msg);
                return "manager/error";
            }else{
                model.addAttribute("item",item);
                return "manager/items";
            }
        }
    }

    @GetMapping("ViewItems")
    public String ViewItems(Model model, String itemId){
        Item item = catalogService.getItem(itemId);
        model.addAttribute("item", item);
        return "manager/items";
    }

    @GetMapping("delete")
    public String delete(Model model, String itemId) {
        catalogService.DeleteItemById1(itemId);
        catalogService.DeleteItemById2(itemId);
        List<Item> itemList = catalogService.getAllItem();
        model.addAttribute("itemList",itemList);
        return "manager/itemlist";
    }

    @GetMapping("newItemsForm")
    public String newItemsForm(Model model) {
        model.addAttribute("newItemsForm",new Item());
        return "manager/new_item";
    }

    @PostMapping("newItem")
    public String newItem(Item item, Model model, int quantity) {
        if(item.getItemId() == null){
            String msg = "itemid不能为空";
            model.addAttribute("msg", msg);
            return "manager/new_item";
        }else if(catalogService.getItem(item.getItemId()) != null){
            String msg = "itemid已存在";
            model.addAttribute("msg", msg);
            return "manager/new_item";
        }else if(catalogService.getProduct(item.getProductId()) == null){
            String msg = "productid不存在";
            model.addAttribute("msg", msg);
            return "manager/new_item";
        } else{
            catalogService.insertItem1(item);
            catalogService.insertItem2(item.getItemId(), quantity);
            List<Item> itemList = catalogService.getAllItem();
            model.addAttribute("itemList",itemList);
            return "manager/itemlist";
        }
    }

    @GetMapping("modifyForm")
    public String modifyForm(Model model,String itemId){
        Item item = catalogService.getItem(itemId);
        model.addAttribute("item",item);
        int quantity = item.getQuantity();
        model.addAttribute("quantity", quantity);
        return "manager/modifyitem";
    }

    @PostMapping("modify")
    public String modify(Item item, Model model, int quantity){
        Product product=catalogService.getProduct(item.getProduct().getProductId());
        if(product == null){
            String msg = "productid不存在";
            model.addAttribute("msg", msg);
            return "manager/modifyitem";
        }else{
            catalogService.updateItem1(item);
            catalogService.updateItem2(item.getItemId(),quantity);
            List<Item> itemList = catalogService.getAllItem();
            model.addAttribute("itemList",itemList);
            return "manager/itemlist";
        }
    }
    //product
    @GetMapping("productmanager")
    public String productmanager(Model model){
        List<Product> productList = catalogService.getAllProduct();
        model.addAttribute("productList",productList);
        return "manager/productlist";
    }

    @PostMapping("searchProducts")
    public String searchIProducts(String keyword, Model model, String productId){
        if(keyword == null || keyword.length() < 1){
            String msg = "Please enter a keyword to search for, then press the search button.";
            model.addAttribute("msg",msg);
            return "manager/error";
        }else{
            Product product = catalogService.getProduct(keyword);
            if(product == null){
                String msg = "This product doesn't exist";
                model.addAttribute("msg",msg);
                return "manager/error";
            }else{
                model.addAttribute("product",product);
                return "manager/products";
            }
        }
    }

    @GetMapping("ViewProducts")
    public String ViewProducts(Model model, String productId){
        Product product = catalogService.getProduct(productId);
        model.addAttribute("product",product);
        return "manager/products";
    }

    @GetMapping("deleteproduct")
    public String deleteproduct(Model model, String productId){
        System.out.print(productId);
        catalogService.deleteProductById2(productId);
        catalogService.deleteProductById3(productId);
        catalogService.deleteProductById1(productId);
        List<Product> productList = catalogService.getAllProduct();
        model.addAttribute("productList",productList);
        return "manager/productlist";
    }

    @GetMapping("newProductsForm")
    public String newProductsForm(Model model) {
        model.addAttribute("newProductsForm",new Product());
        return "manager/new_product";
    }

    @PostMapping("newProduct")
    public String newProduct(Product product,Model model) {
        if(product.getProductId() == null){
            String msg = "productid不能为空";
            model.addAttribute("msg",msg);
            return "manager/new_product";
        }else if(catalogService.getProduct(product.getProductId()) != null){
            String msg = "productid已经存在";
            model.addAttribute("msg",msg);
            return "manager/new_product";
        }else {
            catalogService.insertProduct1(product);
            List<Product> productList = catalogService.getAllProduct();
            model.addAttribute("productList",productList);
            return "manager/productlist";
        }

    }

    @GetMapping("modifyFormpro")
    public String modifyFormpro(Model model,Product product){
        model.addAttribute("product",product);
        return "manager/modifyproduct";
    }

    @PostMapping("modifypro")
    public String modefypro(Product product,Model model){
        catalogService.updateProduct1(product);
        List<Product> productList = catalogService.getAllProduct();
        model.addAttribute("productList",productList);
        return "manager/productlist";
    }

    //category
    @GetMapping("categorymanager")
    public String categorymanager(Model model){
        List<Category> categoryList=catalogService.getCategoryList();
        model.addAttribute("categoryList", categoryList);
        return "manager/categorylist";
    }

    @PostMapping("searchCategory")
    public String searchICategory(String keyword, Model model, String categoryId){
        if(keyword == null || keyword.length() < 1){
            String msg = "Please enter a keyword to search for, then press the search button.";
            model.addAttribute("msg",msg);
            return "manager/error";
        }else{
            Category category = catalogService.getCategory(keyword);
            if(category == null){
                String msg = "This product doesn't exist";
                model.addAttribute("msg",msg);
                return "manager/error";
            }else{
                model.addAttribute("category",category);
                return "manager/category";
            }
        }
    }

    @GetMapping("ViewCategory")
    public String ViewCategory(Model model, String categoryId){
        Category category = catalogService.getCategory(categoryId);
        model.addAttribute("category",category);
        return "manager/category";
    }

    @GetMapping("deletecategory")
    public String deletecategory(Model model, String categoryId){
        catalogService.deleteCategorybyId4(categoryId);
        catalogService.deleteCategorybyId3(categoryId);
        catalogService.deleteCategoryById2(categoryId);
        catalogService.deleteCategoryById(categoryId);
        List<Category> categoryList = catalogService.getCategoryList();
        model.addAttribute("categorytList",categoryList);
        return "manager/categorylist";
    }

    @GetMapping("newCategoryForm")
    public String newCategoryForm(Model model) {
        model.addAttribute("newCategoryForm",new Category());
        return "manager/new_category";
    }

    @PostMapping("newCategory")
    public String newCategory(Category category,Model model) {
        if(category.getCategoryId() == null){
            String msg = "categoryid不能为空";
            model.addAttribute("msg",msg);
            return "manager/new_category";
        }else if(catalogService.getCategory(category.getCategoryId()) != null){
            String msg = "categoryid已经存在";
            model.addAttribute("msg",msg);
            return "manager/new_category";
        }else {
            catalogService.insertCategory(category);
            List<Category> categoryList = catalogService.getCategoryList();
            model.addAttribute("categoryList",categoryList);
            return "manager/categorylist";
        }

    }

    @GetMapping("modifyFormcat")
    public String modifyFormcat(Model model,Category category){
        model.addAttribute("category",category);
        return "manager/modifycategory";
    }

    @PostMapping("modifycat")
    public String modefycat(Category category,Model model){
        catalogService.updateCategory(category);
        List<Category> categoryList = catalogService.getCategoryList();
        model.addAttribute("categoryList",categoryList);
        return "manager/categorylist";
    }

}
