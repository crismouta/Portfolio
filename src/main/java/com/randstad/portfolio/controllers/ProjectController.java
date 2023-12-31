package com.randstad.portfolio.controllers;

import com.randstad.portfolio.domain.models.Project;
import com.randstad.portfolio.domain.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping(path = "api/v1/projects")
public class ProjectController {

    public final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll(){
        return ResponseEntity.ok(this.projectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.projectService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Project> create(@RequestParam("title") String title,
                                          @RequestParam("img") MultipartFile img,
                                          @RequestParam("description") String description,
                                          @RequestParam("linkGitHub") String linkGitHub) throws IOException {
        return ResponseEntity.ok(this.projectService.create(title, img, description, linkGitHub));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.projectService.deleteProject(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable Long id, @RequestParam("title") String title,
                                          @RequestParam("img") MultipartFile img,
                                          @RequestParam("description") String description,
                                          @RequestParam("linkGitHub") String linkGitHub) throws IOException {
        return ResponseEntity.ok(this.projectService.update(id, title, img, description, linkGitHub));
    }
}
