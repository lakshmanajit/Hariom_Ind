package com.traffsys.Authservices;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.traffsys.DTO.ProductDto;
import com.traffsys.Repository.ProductRepo;
import com.traffsys.Repository.userRepo;
import com.traffsys.entity.Product;
import com.traffsys.entity.user;

@Service
public class ProductService {

	@Autowired
	private ProductRepo prepo;
	// Add Product

	@Autowired
	private userRepo repo;

	public Object addProduct(ProductDto pdto) {
		
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username= auth.getName();
		System.out.println("DTO Product Code: " + pdto.getProductCode());
		System.out.println("DTO Product Name: " + pdto.getProductName());

		Product exitingCode = prepo.findByProductCode(pdto.getProductCode());

		System.out.println("exiting Product  Code" + exitingCode);

		if (exitingCode != null) {
			throw new RuntimeException("Product Code Already Exist");
		}
		Product product = new Product();
		product.setProductCode(pdto.getProductCode());
		product.setProductName(pdto.getProductName());
		product.setStatus(pdto.getStatus());
		product.setDescription(pdto.getDescription());
		product.setCreatedBy(username);
		product.setCreatedDate(LocalDateTime.now());

		prepo.save(product);

		return "Product Added SuccessFullly";
	}

	public Page<ProductDto> getAllProduct(int page, int size) {

		// Logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		user current_user = repo.findByUsername(username);

		if (current_user == null) {
			throw new RuntimeException("user Not Found");
		}

		// Role Check
		if (!current_user.getRole().getRoleName().equals("ROLE_ADMIN")) {
			throw new RuntimeException("user Have Not Privilege to fetch All data");
		}

		PageRequest pageable = PageRequest.of(page, size);

		Page<Product> response = prepo.findAll(pageable);

		return response.map(product -> {

			ProductDto pdto = new ProductDto();

			pdto.setId(product.getId());
			pdto.setProductCode(product.getProductCode());
			pdto.setProductName(product.getProductName());
			pdto.setDescription(product.getDescription());
			pdto.setStatus(product.getStatus());
			pdto.setCreatedBy(username);
			pdto.setLocalDateTime(product.getCreatedDate());

			return pdto;
		});
	}

	public Object updateProduct(Long id, ProductDto dto) 
	{
		Map<String, Object> response= new HashMap<>();
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		String username=auth.getName();
		Product pid= prepo.findById(id).orElseThrow(() ->new  RuntimeException("Product row id  Not Found"));
		
		pid.setProductName(dto.getProductName());
		pid.setProductCode(dto.getProductCode());
		pid.setDescription(dto.getDescription());
		pid.setStatus(dto.getStatus());
		pid.setUpdatedBy(username);
		pid.setUpdatedDate(LocalDateTime.now());
		
		prepo.save(pid);
		response.put("status",200);
		response.put("message", "updated Successfully");
		return response;
	}

	public Object deleteapi(Long id) 
	{
		Product  pid= prepo.findById(id).orElseThrow(() -> new RuntimeException("pid Not found"));
		prepo.delete(pid);
		return "delete data Successfully";
	}

}
