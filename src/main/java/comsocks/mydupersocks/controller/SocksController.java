package comsocks.mydupersocks.controller;

import comsocks.mydupersocks.exceptions.QuantitySocksError;
import comsocks.mydupersocks.service.SocksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/socks")
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }



    @PostMapping("/income")
    public String socksIncome(
            @RequestParam String color,
            @RequestParam Integer cottonPart,
            @RequestParam Integer quantity
    ) {
        socksService.addSocks(color,cottonPart,quantity);
        return "accepted 0_o " + quantity + " " +color +" socks with " + cottonPart + " % cotton content";
        }

    @PostMapping("/outcome")
    public String socksOutcome(
            @RequestParam String color,
            @RequestParam Integer cottonPart,
            @RequestParam Integer quantity
    ) {
try {
    socksService.deleteSocks(color,cottonPart,quantity);
} catch (QuantitySocksError error) {return error.getLocalizedMessage();}
        socksService.deleteSocks(color,cottonPart,quantity);
        return "sent " + quantity + " " +color +" socks with " + cottonPart + " % cotton content";
    }

    @GetMapping
    public Integer getAllSocks(@RequestParam String color, @RequestParam String operation, @RequestParam Integer cottonPart){
        return socksService.getCountSocksType(color,operation,cottonPart);
    }
}
