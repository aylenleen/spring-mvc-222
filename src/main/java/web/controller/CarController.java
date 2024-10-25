package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
        if (count > 5) {
            return carService.carList().stream().limit(count).toList();
        } else if (count >= 5) {
            return carService.carList();
        }
        return null;
    }

    @GetMapping(value = "/cars")
    public String printCar(ModelMap model) {
        getCars(3);
        model.addAttribute("cars", getCars(3));
        return "cars";
    }
}
