package com.example.homework10;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {


    @GetMapping("/")
    public String toMain(){
        return "main";
    }


    @PostMapping("/result")
    public String toResult(Model model, @RequestParam String currency){

        Currency[] currencies = CurrencyNbuAPI.getCurrencyNBU(currency.toLowerCase());

        if(currencies.length == 0){
            return "fail";
        }
        model.addAttribute("code",currencies[0].getCode());
        model.addAttribute("codeUkr",currencies[0].getCodeUkr());
        model.addAttribute("rate",currencies[0].getRate());


        return "result";
    }


}
