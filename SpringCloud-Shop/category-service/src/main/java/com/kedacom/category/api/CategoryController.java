package com.kedacom.category.api;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kedacom.category.common.ResourceNotFoundException;
import com.kedacom.category.model.Category;
import com.kedacom.category.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Category get(@PathVariable Long id) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Category category = categoryService.findOne(id);
        logger.info("/category, host:" + instance.getHost() + ", serviceId: " + instance.getServiceId() + ",category id: " + category.getId() + ",category name: " + category.getName());
        return category;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Category> save(@RequestBody Category category, UriComponentsBuilder ucb) {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/category, host:" + instance.getHost() + ", serviceId:" + instance.getServiceId() + ",category id:" + category.getId() + ",category name:" + category.getName());

        Category saved = categoryService.save(category);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/category/")
                .path(String.valueOf(saved.getId()))
                .build()
                .toUri();
        headers.setLocation(locationUri);

        ResponseEntity<Category> responseEntity = new ResponseEntity<>(saved, headers, HttpStatus.CREATED);

        return responseEntity;
    }

    /**
     * 集合查询或其他操作都不是资源化的，而是行为化的，因此每种请求方法类型全部写到一个URI，通过查询参数控制行为，而不要每个行为都写一个方法/不同URI
     * @param level
     * @param name
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Category> categories(@RequestParam(value = "level", defaultValue = "0") Integer level, @RequestParam(value = "name", defaultValue = "top") String name) {
        ServiceInstance instance = client.getLocalServiceInstance();
        List<Category> categories = categoryService. findByLevelAndName(level, name);
        logger.info("/category, host:" + instance.getHost() + ", serviceId: " + instance.getServiceId() + ", category level: " + level + ", category name: " + name);
        return categories;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public com.kedacom.category.common.Error resourceNotFound(ResourceNotFoundException e){
        Long resourceId = e.getResourceId();
        return new com.kedacom.category.common.Error(4, "Category [" + resourceId + "] not found");
    }
}
