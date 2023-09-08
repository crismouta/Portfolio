package com.randstad.portfolio.domain.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.randstad.portfolio.domain.models.Project;
import com.randstad.portfolio.infra.repositories.IProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {
    private final IProjectRepository projectRepository;
    private final Cloudinary cloudinary;

    public ProjectService(IProjectRepository projectRepository, Cloudinary cloudinary) {
        this.projectRepository = projectRepository;
        this.cloudinary = cloudinary;
    }

    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    public Project findById(Long id) {
        var optionalCharacter = this.projectRepository.findById(id);
        if(optionalCharacter.isEmpty()) throw new RuntimeException("El character con id: " + id + " no existe");
        return optionalCharacter.get();
    }

    public Project create(String title, MultipartFile img, String description,  String linkGitHub) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("url");

        // Crear un nuevo proyecto con los datos y la URL de la imagen
        Project project = new Project(title, imageUrl, description,  linkGitHub);
        return projectRepository.save(project);

    }

    public void deleteCharacter(Long id) {
        this.projectRepository.deleteById(id);
    }

    public Project update(Long id, String title, MultipartFile img, String description,  String linkGitHub) throws IOException {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null) {
            // Subir la nueva imagen a Cloudinary y obtener la URL
            Map uploadResult = cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("url");

            // Actualizar los datos del proyecto
            project.setTitle(title);
            project.setImg(imageUrl);
            project.setDescription(description);
            project.setLinkGitHub(description);

            return projectRepository.save(project);
        }
        return null;
    }
}
