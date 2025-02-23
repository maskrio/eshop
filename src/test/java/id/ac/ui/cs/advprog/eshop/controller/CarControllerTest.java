package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private CarService carService;
    
    @TestConfiguration
    static class CarControllerTestConfig {
        @Bean
        public CarService carService() {
            return org.mockito.Mockito.mock(CarService.class);
        }
    }
    
    @Test
    void testCreateCarPage() throws Exception {
        mockMvc.perform(get("/car/createCar"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateCar"))
                .andExpect(model().attributeExists("car"));
    }
    
    @Test
    void testCreateCarPost() throws Exception {
        Car dummyCar = new Car("1", "TestCar", "Red", 1);
        when(carService.create(any(Car.class))).thenReturn(dummyCar);
        
        mockMvc.perform(post("/car/createCar")
                        .param("carId", "1")
                        .param("carName", "TestCar")
                        .param("carColor", "Red")
                        .param("carQuantity", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:listCar"));
        
        verify(carService).create(any(Car.class));
    }
    
    @Test
    void testCarListPage() throws Exception {
        List<Car> dummyCars = Arrays.asList(
                new Car("1", "Car1", "Blue", 2),
                new Car("2", "Car2", "Green", 3)
        );
        when(carService.findAll()).thenReturn(dummyCars);
        
        mockMvc.perform(get("/car/listCar"))
                .andExpect(status().isOk())
                .andExpect(view().name("CarList"))
                .andExpect(model().attributeExists("cars"));
    }
    
    @Test
    void testEditCarPage() throws Exception {
        Car dummyCar = new Car("1", "Car1", "Blue", 2);
        when(carService.findById("1")).thenReturn(dummyCar);
        
        mockMvc.perform(get("/car/editCar/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditCar"))
                .andExpect(model().attributeExists("car"));
    }
    
    @Test
    void testEditCarPost() throws Exception {
        Car updatedCar = new Car("1", "UpdatedCar", "Green", 3);
        when(carService.update(eq("1"), any(Car.class))).thenReturn(updatedCar);
        
        mockMvc.perform(post("/car/editCar")
                        .param("carId", "1")
                        .param("carName", "UpdatedCar")
                        .param("carColor", "Green")
                        .param("carQuantity", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:listCar"));
        
        verify(carService).update(eq("1"), any(Car.class));
    }
    
    @Test
    void testDeleteCar() throws Exception {
        Car dummyCar = new Car("1", "Car1", "Blue", 2);
        when(carService.deleteCarById("1")).thenReturn(dummyCar);
        
        mockMvc.perform(post("/car/deleteCar")
                        .param("carId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:listCar"));
        
        verify(carService).deleteCarById("1");
    }
}