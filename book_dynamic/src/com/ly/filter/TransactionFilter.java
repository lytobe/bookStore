//package com.ly.filter;
//
//import com.ly.utils.JdbcUtils;
//
//import javax.servlet.*;
//import java.io.IOException;
//
//public class TransactionFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        try{
//            filterChain.doFilter(servletRequest,servletResponse);
//            JdbcUtils.commitAndClose();//如果没有异常，则提交事务
//        }catch (Exception e){
//            JdbcUtils.rollBackAndClose();//如果有异常则回滚事务
//            e.printStackTrace();
//            throw  new RuntimeException();
//        }
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
