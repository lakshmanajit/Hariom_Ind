package com.traffsys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traffsys.Authservices.ProductService;
import com.traffsys.DTO.ProductDto;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController
{
	    @Autowired
	    private ProductService service;
	    
	    //Add Product
	    @PostMapping("/addproduct")
	   public ResponseEntity<?>addProduct(@RequestBody ProductDto pdto )
	   {
	    	System.out.println("print Controller");
		return ResponseEntity.ok(service.addProduct(pdto));
		   
	   }
	    
	    @GetMapping("/all")
	    public ResponseEntity<?> getAllProduct(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size) {

	        Page<ProductDto> products =
	                service.getAllProduct(page, size);

	        Map<String, Object> response = new HashMap<>();

	        response.put("status", 200);
	        response.put("message", "Success");
	        response.put("myObjectList", products.getContent());
	        response.put("currentPage", products.getNumber());
	        response.put("pageSize", products.getSize());
	        response.put("totalRecords", products.getTotalElements());
	        response.put("totalPages", products.getTotalPages());

	        return ResponseEntity.ok(response);
	    }
	    
	    @PutMapping("/update/{id}")
	    public ResponseEntity<?> updated(@PathVariable Long id ,@RequestBody ProductDto dto)
	    {
			return ResponseEntity.ok(service.updateProduct(id,dto));
	    	
	    }
	    
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?>deleteMethod(@PathVariable Long id)
	    {
	    	System.out.println("delelte api hit");
	    	return ResponseEntity.ok(service.deleteapi(id));
	    }

}
