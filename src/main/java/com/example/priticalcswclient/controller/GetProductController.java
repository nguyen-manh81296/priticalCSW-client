package com.example.priticalcswclient.controller;

import com.example.priticalcswclient.entity.Product;
import com.example.priticalcswclient.retrofit.RetrofitServiceGenerator;
import com.example.priticalcswclient.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetProductController extends HttpServlet {
     ProductService productService;

    public GetProductController(){
        productService = RetrofitServiceGenerator.createService(ProductService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.getProducts().execute().body();
        req.setAttribute("products",products);
        req.getRequestDispatcher("/products/list.jsp").forward(req,resp);
    }
}
