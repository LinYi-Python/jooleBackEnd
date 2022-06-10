package com.itlizeSession.joole.Service;

import com.itlizeSession.joole.Entity.Product;
import com.itlizeSession.joole.Entity.Project;
import com.itlizeSession.joole.Entity.ProjectProduct;

import java.util.Collection;
import java.util.List;

public interface ProjectProductService {
    public Collection<ProjectProduct> findALl();
    public ProjectProduct findById(Integer Id);
    public boolean create(ProjectProduct projectProduct, Project project, Product product);
    public boolean delete(ProjectProduct projectProduct);
    public boolean update(ProjectProduct projectProduct);
    public ProjectProduct get(Integer id);

    public List<Product> findProductsFromProject(Integer id);

    public boolean addProductFromProject(Integer productId, Integer projectId);

    public boolean deleteProductFromProject(Integer productId, Integer projectId);
}
