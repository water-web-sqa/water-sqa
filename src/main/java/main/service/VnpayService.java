package main.service;

import main.entity.Bill;

public interface VnpayService {
    public String paymentWater(String codeHouse,String vnpOrderInfo, String bankcode, String mountBill, String return_url);
    public void savePayMent(Bill bill);
}
