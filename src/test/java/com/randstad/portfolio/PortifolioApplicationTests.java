package com.randstad.portfolio;

import com.randstad.portfolio.domain.models.Project;
import com.randstad.portfolio.infra.repositories.IProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PortifolioApplicationTests {
	@Autowired
	private IProjectRepository projectRepository;

	@Autowired
	private TestRestTemplate api;

	@Test
	void readAllProjects() {
		List<Project> projects = List.of(
				new Project(  "titulo 1", "url img 1", "description 1", "link 1"),
				new Project(  "titulo 2", "url img 1", "description 2", "link 2"),
				new Project(  "titulo 3", "url img 3", "description 3", "link 3")
		);

		projectRepository.saveAll(projects);

		var response = api.getForEntity("/projects", Project[].class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		//assertThat(Arrays.stream(response.getBody()).toList(),containsInAnyOrder(projects.toArray()));

		List<Project> responseProjects = Arrays.stream(response.getBody()).toList();

		// Compara el tamaño de las listas
		assertThat(responseProjects, hasSize(projects.size()));

		// Compara los atributos de cada proyecto
		for (Project project : responseProjects) {
			assertThat(projects, hasItem(
					allOf(
							hasProperty("id", is(project.getId())),
							hasProperty("title", is(project.getTitle())),
							hasProperty("img", is(project.getImg())),
							hasProperty("description", is(project.getDescription())),
							hasProperty("linkGitHub", is(project.getLinkGitHub()))
					)
			));
		}
	}

	@Test
	void saveProject() {
		Project project = new Project ("titulo", "url img", "description", "link ");

		var response = api.postForEntity("/projects", project, Project[].class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		//assertThat(projectRepository.findAll(), contains(project));
	}

}
