package com.Application.FilRouge.Controllers;

import com.Application.FilRouge.DTO.PlatsDto;
import com.Application.FilRouge.Model.Category;
import com.Application.FilRouge.Model.Plats;
import com.Application.FilRouge.Mappers.PlatMapper;
import com.Application.FilRouge.Repository.PlatRepository;
import com.Application.FilRouge.Services.PlatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simple-plats")
@CrossOrigin(origins = "http://localhost:4200")
public class SimplePlatController {

    private final PlatService platService;
    private final PlatRepository platRepository;
    private final PlatMapper platMapper;

    public SimplePlatController(PlatService platService, PlatRepository platRepository, PlatMapper platMapper) {
        this.platService = platService;
        this.platRepository = platRepository;
        this.platMapper = platMapper;
    }

    // Get all dishes - Simple version
    @GetMapping("/all")
    public List<PlatsDto> getAllDishes() {
        return platService.listePlats();
    }

    // Add new dish - Simple version with manual save (bypasses authentication)
    @PostMapping("/add")
    public ResponseEntity<String> addDish(@RequestBody PlatsDto plat) {
        try {
            System.out.println("Received dish data: " + plat.getName() + ", Category: " + plat.getCategory());
            System.out.println("Full DTO: Name=" + plat.getName() + ", Description=" + plat.getDescription() + ", Prix=" + plat.getPrix());
            
            // Validate required fields
            if (plat.getName() == null || plat.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Dish name is required");
            }
            if (plat.getDescription() == null || plat.getDescription().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Description is required");
            }
            if (plat.getPrix() <= 0) {
                return ResponseEntity.badRequest().body("Price must be greater than 0");
            }
            if (plat.getCategory() == null) {
                return ResponseEntity.badRequest().body("Category is required");
            }
            
            // Convert DTO to entity manually (bypassing security requirements)
            Plats plats = new Plats();
            plats.setName(plat.getName());
            plats.setDescription(plat.getDescription());
            plats.setPrix(plat.getPrix());
            plats.setAvailable(plat.isAvailable());
            plats.setCategory(plat.getCategory());
            plats.setPhoto(plat.getPhoto());
            // Leave restaurant and commande as null for simplicity
            
            Plats savedPlat = platRepository.save(plats);
            System.out.println("Dish saved with ID: " + savedPlat.getId());
            return ResponseEntity.ok("Dish added successfully!");
        } catch (Exception e) {
            System.err.println("Error adding dish: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Update dish - Simple version
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDish(@PathVariable Long id, @RequestBody PlatsDto plat) {
        try {
            Plats existingPlat = platRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
            

            existingPlat.setName(plat.getName());
            existingPlat.setDescription(plat.getDescription());
            existingPlat.setPrix(plat.getPrix());
            existingPlat.setAvailable(plat.isAvailable());
            existingPlat.setCategory(plat.getCategory());
            existingPlat.setPhoto(plat.getPhoto());

            platRepository.save(existingPlat);
            return ResponseEntity.ok("Dish updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Delete dish - Simple version
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable Long id) {
        try {
            if (!platRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            platRepository.deleteById(id);
            return ResponseEntity.ok("Dish deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Get one dish by ID - Simple version
    @GetMapping("/{id}")
    public ResponseEntity<PlatsDto> getDish(@PathVariable Long id) {
        try {
            Plats plat = platRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
            PlatsDto platDto = platMapper.platsToDto(plat);
            return ResponseEntity.ok(platDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get available categories
    @GetMapping("/categories")
    public ResponseEntity<Category[]> getCategories() {
        return ResponseEntity.ok(Category.values());
    }
}