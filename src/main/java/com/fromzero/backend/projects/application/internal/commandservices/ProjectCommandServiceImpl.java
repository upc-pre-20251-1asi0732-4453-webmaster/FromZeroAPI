package com.fromzero.backend.projects.application.internal.commandservices;

import com.fromzero.backend.deliverables.domain.model.aggregates.Deliverable;
import com.fromzero.backend.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.fromzero.backend.deliverables.domain.valueobjects.DeliverableStatus;
import com.fromzero.backend.deliverables.infrastructure.persistence.jpa.repositories.DeliverableRepository;
import com.fromzero.backend.projects.domain.model.aggregates.Project;
import com.fromzero.backend.projects.domain.model.commands.*;
import com.fromzero.backend.projects.domain.services.ProjectCommandService;
import com.fromzero.backend.projects.domain.valueobjects.ProjectState;
import com.fromzero.backend.projects.domain.valueobjects.ProjectType;
import com.fromzero.backend.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectCommandServiceImpl implements ProjectCommandService {
    private final ProjectRepository projectRepository;
    private final DeliverableRepository deliverableRepository;

    public ProjectCommandServiceImpl(ProjectRepository projectRepository, DeliverableRepository deliverableRepository) {
        this.deliverableRepository = deliverableRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> handle(CreateProjectCommand command) {
        var project= new Project(command);
        this.projectRepository.save(project);

        project.getLanguages().addAll(command.languages());
        project.getFrameworks().addAll(command.frameworks());

        List<Deliverable> deliverables = createDefaultDeliverables(project.getType(), project);
        this.projectRepository.save(project);
        return Optional.of(project);
    }

    @Override
    public Optional<Project> handle(UpdateProjectCandidatesListCommand command) {
        var project = command.project();
        project.getCandidates().add(command.developer());
        this.projectRepository.save(project);
        return Optional.of(project);
    }

    @Override
    public Optional<Project> handle(AssignProjectDeveloperCommand command) {
        var project =command.project();
        project.setDeveloper(command.developer());
        project.getCandidates().clear();
        project.setState(ProjectState.IN_PROCESS);
        this.projectRepository.save(project);
        return Optional.of(project);
    }

    @Override
    public Optional<Project> handle(UpdateProjectProgressCommand command) {
        var project = command.project();
        project.setProgress(command.progress());

        boolean allDeliverablesApproved = deliverableRepository.findAllByProject(project)
                .stream()
                .allMatch(deliverable -> {
                    return deliverable.getState() == DeliverableStatus.APPROVED;
                });

        if (allDeliverablesApproved) {
            project.setState(ProjectState.COMPLETED);
        }

        this.projectRepository.save(project);
        return Optional.of(project);
    }

    @Override
    @Transactional
    public void handle(DeleteProjectCommand command) {
        var project = projectRepository.findById(command.projectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));


        //if the project's state is COMPLETED or IN PROCESS
        if (project.getState() == ProjectState.COMPLETED || project.getState() == ProjectState.IN_PROCESS) {
            throw new IllegalArgumentException("Cannot delete a completed or an in process project");
        }

        //if the project have a developer working on it
        if(project.getDeveloper() != null) {
            throw new IllegalArgumentException("Cannot delete a project with an assigned developer");
        }


        deliverableRepository.deleteAllByProjectId(project.getId());

        this.projectRepository.delete(project);
    }


    private List<Deliverable> createDefaultDeliverables(ProjectType projectType, Project project) {
        List<Deliverable> deliverables = new ArrayList<>();

        switch (projectType) {
            case LANDING_PAGE -> {
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Definiendo requisitos del proyecto",
                        "Se realizará una reunión para especificar quienes serán los usuarios y propósito principal",
                        LocalDateTime.parse(LocalDate.now().plusDays(5) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 1), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Estructura y contenidos",
                        "Wireframes y Mockups del Landing Page. Incluye recursos visuales/multimedia. Se solicita un informe detallado.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(1) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 2), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Desarrollo inicial",
                        "Entrega de la primera versión funcional del Landing Page con navegación básica y diseño responsivo.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(2) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 3), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Validación de experiencia de usuario",
                        "Evaluación de usabilidad con usuarios reales, reporte de resultados y mejoras aplicadas.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(3) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 4), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Optimización SEO",
                        "Implementación de técnicas de posicionamiento básico para motores de búsqueda. Informe de herramientas utilizadas.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(4) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 5), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Entrega final y despliegue",
                        "Entrega del producto final, documentación técnica y despliegue en entorno de producción.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(5) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 6), project));
            }
            case ECOMMERCE -> {
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Análisis del negocio",
                        "Recolección de requisitos, tipos de productos, métodos de pago y logística.",
                        LocalDateTime.parse(LocalDate.now() + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 1), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Diseño UI/UX",
                        "Wireframes del portal de ventas, formularios de checkout y perfiles de usuario.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(1) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 2), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Desarrollo del catálogo",
                        "Implementación del catálogo de productos, filtros y detalles.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(2) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 3), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Integración de pagos",
                        "Configuración de pasarelas de pago y simulaciones de compra seguras.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(3) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 4), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Pruebas de flujo completo",
                        "Pruebas funcionales desde la selección de producto hasta la confirmación del pedido.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(4) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 5), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Lanzamiento del ecommerce",
                        "Entrega final y publicación en entorno de producción con monitoreo inicial.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(5) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 6), project));
            }
            case ONE_PAGE_WEBSITE -> {
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Definición del contenido",
                        "Reunión para definir las secciones clave: inicio, sobre nosotros, servicios, contacto, etc.",
                        LocalDateTime.parse(LocalDate.now() + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 1), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Diseño del flujo",
                        "Wireframe del flujo vertical de una sola página con transiciones suaves.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(1) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 2), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Primera versión funcional",
                        "Una versión navegable con contenido simulado y diseño responsivo.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(2) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 3), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Optimización y animaciones",
                        "Animaciones suaves al hacer scroll, optimización de recursos visuales.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(3) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 4), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Validación con stakeholders",
                        "Revisión con el cliente del contenido real aplicado y cambios necesarios.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(4) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 5), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Publicación final",
                        "Versión final subida a producción con documentación de cambios.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(5) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 6), project));
            }
            case MOBILE_APPLICATION -> {
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Requisitos y definición de plataformas",
                        "Se define si es Android, iOS o híbrida, con foco en casos de uso clave.",
                        LocalDateTime.parse(LocalDate.now() + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 1), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Prototipo navegable",
                        "Diseño en herramienta como Figma para mostrar la experiencia de usuario.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(1) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 2), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Desarrollo MVP",
                        "Primera versión con funcionalidades mínimas operativas.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(2) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 3), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Integración de servicios",
                        "Conexión con backend, APIs y almacenamiento.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(3) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 4), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Pruebas de dispositivo",
                        "Validación en distintos tamaños de pantalla y versiones del sistema operativo.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(4) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 5), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Publicación en tienda",
                        "Entrega final con instalación en Google Play / App Store, y documentación de uso.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(5) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 6), project));
            }
            case DESKTOP_APPLICATION -> {
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Requisitos funcionales",
                        "Se definen los módulos principales, tipos de usuarios y entornos de ejecución.",
                        LocalDateTime.parse(LocalDate.now() + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 1), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Diseño de interfaz",
                        "Mockups de ventanas, menús y navegación, respetando guías de sistema operativo.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(1) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 2), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Desarrollo de módulos base",
                        "Implementación de la arquitectura y primeros módulos funcionales.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(2) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 3), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Gestión de datos",
                        "Implementación de base de datos local y pruebas de persistencia.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(3) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 4), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Pruebas unitarias y rendimiento",
                        "Ejecución de pruebas automatizadas, detección de cuellos de botella.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(4) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 5), project));
                deliverables.add(new Deliverable(new CreateDeliverableCommand(
                        "Instalador y documentación",
                        "Entrega de instalador para usuario final, manual técnico y de usuario.",
                        LocalDateTime.parse(LocalDate.now().plusWeeks(5) + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString(), project.getId(), 6), project));
            }
        }

        return deliverableRepository.saveAll(deliverables);
    }

}
