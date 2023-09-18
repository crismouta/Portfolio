package com.randstad.portfolio.domain.services;

import com.randstad.portfolio.domain.models.Project;
import com.randstad.portfolio.infra.repositories.IProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final IProjectRepository projectRepository;
    private final CloudinaryImageServiceImplementation cloudinaryImageService;

    public ProjectService(IProjectRepository projectRepository, CloudinaryImageServiceImplementation cloudinaryImageService) {
        this.projectRepository = projectRepository;
        this.cloudinaryImageService = cloudinaryImageService;
    }

    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    public Project findById(Long id) {
        Optional<Project> optionalProject = this.projectRepository.findById(id);
        return optionalProject.orElse(null);
    }

    public Project create(String title, MultipartFile img, String description,  String linkGitHub) throws IOException {
        String imageUrl = (String) cloudinaryImageService.upload(img).get("url");

        Project project = new Project(title, imageUrl, description,  linkGitHub);
        return projectRepository.save(project);

    }

    public void deleteProject(Long id) {
        this.projectRepository.deleteById(id);
    }

    public Project update(Long id, String title, MultipartFile img, String description,  String linkGitHub) throws IOException {
        Project project = findById(id);
        if (project != null) {
            String imageUrl = (String) cloudinaryImageService.upload(img).get("url");

            project.setTitle(title);
            project.setImg(imageUrl);
            project.setDescription(description);
            project.setLinkGitHub(linkGitHub);

            return projectRepository.save(project);
        }
        return null;
    }
}
