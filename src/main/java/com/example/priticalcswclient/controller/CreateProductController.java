package com.example.priticalcswclient.controller;

import com.example.priticalcswclient.service.ProductService;
import com.example.priticalcswclient.entity.Product;
import com.example.priticalcswclient.retrofit.RetrofitServiceGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateProductController extends HttpServlet{
    private static Product obj;

    private final ProductService productService;

    public CreateProductController() {
        productService = RetrofitServiceGenerator.createService(ProductService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        obj = new Product("Product new",1000);
        req.setAttribute("product", obj);
        req.getRequestDispatcher("/product/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        obj.setName(req.getParameter("name"));
        obj.setPrice(new Double(req.getParameter("price")));

        productService.addProducts(obj).execute();
        resp.sendRedirect("/products");
    }
}
