package com.marcoantonio.dscatalog.resources;
import java.net.URI;
import java.util.List;
import com.marcoantonio.dscatalog.dtos.CategoryDTO;
import com.marcoantonio.dscatalog.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryServices service;
    
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable Long id){
        CategoryDTO categoryDTO = service.findById(id);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createdCategory(@RequestBody CategoryDTO categoryDTO){
        categoryDTO = service.createdCategory(categoryDTO);
        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(categoryDTO.getId())
        .toUri();

        return ResponseEntity.created(uri).body(categoryDTO);

    }

}
