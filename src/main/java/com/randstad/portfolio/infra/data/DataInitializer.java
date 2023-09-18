package com.randstad.portfolio.infra.data;

import com.randstad.portfolio.domain.models.Project;
import com.randstad.portfolio.infra.repositories.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private IProjectRepository projectRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crear instancias de libros y guardarlos en la base de datos
        Project project1 = new Project();
        project1.setTitle("Project 1");
        project1.setImg("https://t.ctcdn.com.br/mh0foo99fyjt6M7kgmsEr67RslQ=/1024x576/smart/i595923.jpeg");
        project1.setDescription("Description 1");
        project1.setLinkGitHub("Link gitHub 1");

        Project project2 = new Project();
        project2.setTitle("Project 2");
        project2.setImg("https://t.ctcdn.com.br/mh0foo99fyjt6M7kgmsEr67RslQ=/1024x576/smart/i595923.jpeg");
        project2.setDescription("Description 2");
        project2.setLinkGitHub("Link gitHub 2");

        Project project3 = new Project();
        project3.setTitle("Project 3");
        project3.setImg("https://t.ctcdn.com.br/mh0foo99fyjt6M7kgmsEr67RslQ=/1024x576/smart/i595923.jpeg");
        project3.setDescription("Description 3");
        project3.setLinkGitHub("Link gitHub 3");


        // Guardar los libros en la base de datos
        projectRepository.save(project1);
        projectRepository.save(project2);
        projectRepository.save(project3);

    }
}
