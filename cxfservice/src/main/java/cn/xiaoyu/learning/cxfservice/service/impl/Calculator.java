package cn.xiaoyu.learning.cxfservice.service.impl;

import cn.xiaoyu.learning.cxfservice.service.ICalculator;

import javax.jws.WebService;

@WebService
public class Calculator implements ICalculator {
    @Override
    public double add(double a, double b) {
        return a + b;
    }

    @Override
    public double sub(double a, double b) {
        return a - b;
    }
}
