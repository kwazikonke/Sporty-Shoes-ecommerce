package com.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dto.ProductDto;
import com.model.Category;
import com.model.Product;
import com.repository.PurchaseReportRepository;
import com.service.CategoryService;
import com.service.ProductService;

@Controller
public class AdminController {

	//public static String uploadDir =System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	
	public static String uploadDir= System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	@Autowired
	PurchaseReportRepository purchaseReportRepository;
	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	
	// C A T E G O R Y
	
	@GetMapping("/admin/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}
	@GetMapping("/admin/categories/add")
	public String getCategoriesAdd(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	@PostMapping("/admin/categories/add")
	public String postcatAdd(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		categoryService.removeCategoryById(id);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable int id, Model model) {
	  Optional<Category> category = categoryService.getCategoryById(id);
	  
	  if(category.isPresent()) {
		  model.addAttribute("category", category.get());
	     return "categoriesAdd";
	  }
	  else {
		  return "404";
	  }
	  
	}
	
	// P R O D U C T
	
	@GetMapping("/admin/products")
	public String products(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "products";
	}
	@GetMapping("/admin/products/add")
	public String productAddGet(Model model) {
		model.addAttribute("productDto", new ProductDto());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productsAdd";
	}
	@PostMapping("/admin/products/add")
	public String ProductAddPost(@ModelAttribute("productDto")ProductDto productDto,
			@RequestParam("productImage") MultipartFile file, 
			@RequestParam("imgName") String imgName) throws IOException
	
	{
		Product product = new Product();
		product.setId(productDto.getId());
		product.setName(productDto.getName());
		product.setCategory(categoryService.getCategoryById(productDto.getCategoryId()).get());
		//product.setCategory(categoryService.getCategoryById(productDto.getCategoryId()));
		product.setPrice(productDto.getPrice());
		product.setWeight(productDto.getWeight());
		product.setDescription(productDto.getDescription());
		
		String imageUUID;
		if(!file.isEmpty())
		{
			imageUUID= file.getOriginalFilename();
			Path faleNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(faleNameAndPath,file.getBytes());	
		}
		else
		{
			imageUUID=imgName;
		}
		product.setImageName(imageUUID);
		productService.addProduct(product);
		
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable int id){
		productService.deleteProductById(id);
		return "redirect:/admin/products";
	}
    @GetMapping("/admin/product/update/{id}")
    public String updateProductGet(@PathVariable int id, Model model) {
    
    	Product product = productService.getProductById(id);
    	ProductDto productDto = new ProductDto();
    	productDto.setId(product.getId());
    	productDto.setName(product.getName());
    	productDto.setCategoryId(product.getCategory().getId());
    	productDto.setPrice(product.getPrice());
    	productDto.setWeight(product.getWeight());
    	productDto.setDescription(product.getDescription());
    	productDto.setImageName(product.getImageName());
    	
    	model.addAttribute("categories", categoryService.getAllCategory());
    	model.addAttribute("productDto", productDto);
    	
    	return "productsAdd";
    }
    // P U R C H A S E   R E P O R T
    
    @GetMapping ("/admin/purchaseReport")
	public String getRport(ModelMap model) {
		model.addAttribute("report",purchaseReportRepository.getReport());
		return "PurchaseReport";
		
	}
}
