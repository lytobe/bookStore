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
//            JdbcUtils.commitAndClose();//���û���쳣�����ύ����
//        }catch (Exception e){
//            JdbcUtils.rollBackAndClose();//������쳣��ع�����
//            e.printStackTrace();
//            throw  new RuntimeException();
//        }
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
