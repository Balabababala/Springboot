package com.example.demo.service;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class LotteryService {

    /** 
     * 取 539 樂透號碼，每組 5 個號碼 (1~39 不重複)
     * @param amount 購買組數
     * @return 隨機選號結果
     */
    @Tool(name = "lottery", description = "lottery 電腦選號，得到一組或多組樂透號碼（每組5個號碼）")
    public String lottery(@ToolParam(description = "購買組數") Integer amount) {
        StringBuilder sb = new StringBuilder("LotteryService 產生樂透號碼:\n");

        for (int i = 1; i <= amount; i++) {
            Set<Integer> numbers = new TreeSet<>();
            Random random = new Random();

            while (numbers.size() < 5) {
                numbers.add(1 + random.nextInt(39)); // 產生 1~39 的不重複號碼
            }

            String result = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

            sb.append("第 ").append(i).append(" 組: ").append(result).append("\n");
        }

        return sb.toString();
    }

    /** 
     * 結帳計算
     * @param amount 購買組數
     * @return 總價
     */
    @Tool(name = "checkout", description = "lottery 結帳計算")
    public String checkout(@ToolParam(description = "購買組數") Integer amount) {
        int price = 50;
        return "LotteryService 結帳總金額: $" + (amount * price);
    }
}
