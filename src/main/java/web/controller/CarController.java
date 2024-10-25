package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.List;

@Controller
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    public List<Car> getCars(int count) {
        if (count < 5) {
            return carService.carList().stream().limit(count).toList();
        } else {
            return carService.carList().stream().limit(5).toList();
        }
    }

    @GetMapping(value = "/cars")
    public String printCar(@RequestParam(name = "count", required = false, defaultValue = "5")
                               int count, ModelMap model) {
        model.addAttribute("count", count);
        model.addAttribute("cars", getCars(count));
        return "cars";
    }
}
