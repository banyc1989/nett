package com.nett.work.interceptor;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.nett.work.common.DBContextHolder;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.nett.work.common.Master) " +
            "&& (execution(* com.nett.work.service..*.select*(..)) " +
            "|| execution(* com.nett.work.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.nett.work.common.Master) " +
            "|| execution(* com.nett.work.service..*.insert*(..)) " +
            "|| execution(* com.nett.work.service..*.add*(..)) " +
            "|| execution(* com.nett.work.service..*.update*(..)) " +
            "|| execution(* com.nett.work.service..*.edit*(..)) " +
            "|| execution(* com.nett.work.service..*.delete*(..)) " +
            "|| execution(* com.nett.work.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* com.nett.work.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }
}