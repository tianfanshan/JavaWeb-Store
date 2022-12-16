package org.example.store.web;

import org.example.store.domain.Customer;
import org.example.store.domain.Goods;
import org.example.store.service.CustomerService;
import org.example.store.service.GoodsService;
import org.example.store.service.OrdersService;
import org.example.store.service.ServiceException;
import org.example.store.service.imp.CustomerServiceImp;
import org.example.store.service.imp.GoodsServiceImp;
import org.example.store.service.imp.OrdersServiceImp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    private static final Properties info = new Properties();

    static {
        InputStream in = Controller.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            info.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final CustomerService customerService = new CustomerServiceImp();
    private final GoodsService goodsService = new GoodsServiceImp();
    private int totalPageNumber = 0;//总页数
    private int pageSize = Integer.parseInt(info.getProperty("pageSize"));//每页行数
    //    private int pageSize;//每页行数
    private final OrdersService ordersService = new OrdersServiceImp();

    private int currentPage = 1;//当前页数

//    @Override
//    public void init(ServletConfig config) throws ServletException{
//        super.init(config);
//        pageSize = Integer.parseInt(config.getInitParameter("pageSize"));
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("reg".equals(action)) {
            //----------客户注册-----------
            String userId = req.getParameter("userid");
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String password2 = req.getParameter("password2");
            String birthday = req.getParameter("birthday");
            String address = req.getParameter("address");
            String phone = req.getParameter("phone");

            //----------服务器验证-----------
            List<String> message = new ArrayList<>();
            if (userId == null || userId.equals("")) {
                message.add("客户账号不能为空");
            }
            if (name == null || name.equals("")) {
                message.add("客户姓名不能为空");
            }
            if (password == null || password2 == null || password.equals("") || password2.equals("")) {
                message.add("密码不能为空");
            }
            if (!Objects.equals(password, password2)) {
                message.add("两次输入的密码不一致");
            }
            String pattern = "^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))$";
            if (!Pattern.matches(pattern, birthday)) {
                message.add("出生日期格式无效");
            }
            if (message.size() > 0) {
                req.setAttribute("errors", message);
                req.getRequestDispatcher("customer_reg.jsp").forward(req, resp);
            } else {
                Customer customer = new Customer();
                customer.setId(userId);
                customer.setName(name);
                customer.setPassword(password);
                customer.setAddress(address);
                customer.setPhone(phone);
                try {
                    Date date = dateFormat.parse(birthday);
                    customer.setBirthday(date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                //注册
                try {
                    // 注册成功
                    customerService.register(customer);
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                } catch (ServiceException e) {
                    //如果捕获到异常：客户ID已经被注册
                    message.add("客户ID已经被注册");
                    req.setAttribute("errors", message);
                    req.getRequestDispatcher("customer_reg.jsp").forward(req, resp);
                }
            }
        } else if ("login".equals(action)) {
            //--------客户登录---------
            String userId = req.getParameter("userid");
            String password = req.getParameter("password");

            Customer customer = new Customer();
            customer.setId(userId);
            customer.setPassword(password);

            try {
                if (customerService.login(customer)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("customer", customer);
                    req.getRequestDispatcher("main.jsp").forward(req, resp);
                } else {
                    List<String> errors = new ArrayList<>();
                    errors.add("您输入的客户账号或密码不正确");
                    req.setAttribute("errors", errors);
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        } else if ("list".equals(action)) {
            //-------商品列表-------
            List<Goods> goodsList = goodsService.queryAll();
            if (goodsList.size() % pageSize == 0) {
                totalPageNumber = goodsList.size() / pageSize;
            } else {
                totalPageNumber = goodsList.size() / pageSize + 1;
            }
            req.setAttribute("totalPageNumber", totalPageNumber);
            req.setAttribute("currentPage", currentPage);
            int start = (currentPage - 1) * pageSize;
            int end = currentPage * pageSize;
            if (currentPage == totalPageNumber) { //最后一页
                end = goodsList.size();
            }
            req.setAttribute("goodsList", goodsList.subList(start, end));
            req.getRequestDispatcher("goods_list.jsp").forward(req, resp);
        } else if ("paging".equals(action)) {
            //-------商品列表分页--------
            String page = req.getParameter("page");

            if (page.equals("prev")) { //上一页
                currentPage--;
                if (currentPage < 1) {
                    currentPage = 1;
                }
            } else if (page.equals("next")) {//下一页
                currentPage++;
                if (currentPage > totalPageNumber) {
                    currentPage = totalPageNumber;
                }
            } else {
                currentPage = Integer.parseInt(page);
            }
            int start = (currentPage - 1) * pageSize;
            int end = currentPage * pageSize;
            List<Goods> goodsList = goodsService.queryGoodsByPage(start, end);
            req.setAttribute("totalPageNumber", totalPageNumber);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("goodsList", goodsList);
            req.getRequestDispatcher("goods_list.jsp").forward(req, resp);
        } else if ("detail".equals(action)) {
            //-------显示商品详情---------
            String goodsId = req.getParameter("productId");
            Goods goods = goodsService.queryDetail(Long.parseLong(goodsId));
            req.setAttribute("goods", goods);
            req.getRequestDispatcher("goods_detail.jsp").forward(req, resp);
        } else if ("add".equals(action)) {
            //------添加到购物车------
            Long goodsId = new Long(req.getParameter("productId"));
            String goodsName = req.getParameter("productName");
            double goodsPrice = Double.parseDouble(req.getParameter("productPrice"));

            //购物车结构是List中包含Map，每一个Map是一个商品
            //从session中取出购物车
            List<Map<String, Object>> cart = (List<Map<String, Object>>) req.getSession().getAttribute("cart");

            if (cart == null) { //第一次取出是null
                cart = new ArrayList<Map<String, Object>>();
                req.getSession().setAttribute("cart", cart);
            }


            // 购物车中有选择的商品
            int flag = 0;
            for (Map<String, Object> item : cart) {
                Long cartGoodsId = (Long) item.get("goodsId");
                if (cartGoodsId.equals(goodsId)) {
                    int quantity = (Integer) item.get("quantity");
                    quantity++;
                    item.put("quantity", quantity);
                    flag++;
                }
            }

            //如果flag有变化则不进入一下if
            if (flag == 0) {
                //购物车中没有选择的商品
                Map<String, Object> item = new HashMap<>();
                //item结构是Map[商品，商品名称，价格，数量]
                item.put("goodsId", goodsId);
                item.put("goodsName", goodsName);
                item.put("quantity", 1);
                item.put("goodsPrice", goodsPrice);
                cart.add(item);
            }

            System.out.println(cart);

            String pageName = req.getParameter("pageName");
            if (pageName.equals("list")) {
                int start = (currentPage - 1) * pageSize;
                int end = currentPage * pageSize;
                List<Goods> goodsList = goodsService.queryGoodsByPage(start, end);
                req.setAttribute("totalPageNumber", totalPageNumber);
                req.setAttribute("currentPage", currentPage);
                req.setAttribute("goodsList", goodsList);

                req.getRequestDispatcher("goods_list.jsp").forward(req, resp);
            } else if (pageName.equals("detail")) {
                Goods goods = goodsService.queryDetail(goodsId);
                req.setAttribute("goods", goods);
                req.getRequestDispatcher("goods_detail.jsp").forward(req, resp);
            }
        } else if ("cart".equals(action)) {
            //------查看购物车------
            // 从session中取出购物车
            List<Map<String, Object>> cart = (List<Map<String, Object>>) req.getSession().getAttribute("cart");

            double total = 0.0;
            if (cart != null) {
                for (Map<String, Object> item : cart) {
                    int quantity = (Integer) item.get("quantity");
                    double price = (Double) item.get("goodsPrice");
                    double subtotal = quantity * price;
                    total += subtotal;
                }
            }
            req.setAttribute("total", total);
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
        } else if ("sub_ord".equals(action)) {
            //------提交订单------
            // 从session中取出购物车
            List<Map<String, Object>> cart = (List<Map<String, Object>>) req.getSession().getAttribute("cart");
            for (Map<String, Object> item : cart) {
                Long goodsId = (Long) item.get("goodsId");
                String strQuantity = req.getParameter("quantity_" + goodsId);
                int quantity = 0;
                try {
                    quantity = new Integer(strQuantity);
                } catch (Exception e) {
                }
                System.out.println(quantity);
                item.put("quantity", quantity);
            }
            //提交订单
            String orderId = ordersService.submitOrders(cart);
            req.setAttribute("orderId", orderId);
            req.getRequestDispatcher("order_finish.jsp").forward(req, resp);
            // 清空购物车
            req.getSession().removeAttribute("cart");
        } else if ("main".equals(action)) {
            //-------回到主页面---------
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        } else if ("logout".equals(action)){
            //-------注销---------
            // 清空购物车
            req.getSession().removeAttribute("cart");
            // 清除用户登录信息
            req.getSession().removeAttribute("customer");
            // 回到登录页面
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        } else if ("reg_init".equals(action)) {
            //-------客户注册页面进入--------
            req.getRequestDispatcher("customer_reg.jsp").forward(req,resp);
        }
    }


}
