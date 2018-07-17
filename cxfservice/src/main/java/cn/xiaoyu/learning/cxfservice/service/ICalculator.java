package cn.xiaoyu.learning.cxfservice.service;

import javax.jws.WebService;

@WebService
public interface ICalculator {
    double add(double a, double b);

    double sub(double a, double b);
}
