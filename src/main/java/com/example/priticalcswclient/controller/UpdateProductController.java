package com.example.priticalcswclient.controller;

import com.example.priticalcswclient.service.ProductService;
import com.example.priticalcswclient.entity.Product;
import com.example.priticalcswclient.retrofit.RetrofitServiceGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UpdateProductController extends HttpServlet {
    private static  Product obj;

    private final ProductService productService;

    public UpdateProductController() {
        productService = RetrofitServiceGenerator.createService(ProductService.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        obj = productService.getProductDetails(id).execute().body();
        if (obj == null) {
            resp.setStatus(404);
            resp.getWriter().println("Not found");
        } else {
            req.setAttribute("product", obj);
            req.getRequestDispatcher("/product/form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        obj.setName(req.getParameter("name"));
        obj.setPrice(new Double(req.getParameter("price")));

        productService.updateProducts(id,obj).execute();
        resp.sendRedirect("/products");
    }
}
