package co.com.sofkau.entrenamento.curso;


import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectriz;
import co.com.sofkau.entrenamiento.curso.commands.AgregarMentoria;
import co.com.sofkau.entrenamiento.curso.events.CursoCreado;
import co.com.sofkau.entrenamiento.curso.events.DirectrizAgregadaAMentoria;
import co.com.sofkau.entrenamiento.curso.events.MentoriaCreada;
import co.com.sofkau.entrenamiento.curso.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgregarDirectrizUseCaseTest {

    @InjectMocks
    private AgregarDiretrizUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void AgregarDirectrizUse() {
        //arrange
        CursoId coursoId = CursoId.of("xxxxx");
        MentoriaId mentoriaId = MentoriaId.of("use");
        Directiz directiz = new Directiz("camel case");
        var command = new AgregarDirectriz(coursoId, mentoriaId ,directiz);

        when(repository.getEventsBy("xxxxx")).thenReturn(history());
        useCase.addRepository(repository);
        //act

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(command.getCursoId().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var event = (DirectrizAgregadaAMentoria) events.get(0);
        Assertions.assertEquals("camel case", event.getDirectiz().value());

    }

    private List<DomainEvent> history() {
        Nombre nombre = new Nombre("DDD");
        Descripcion descripcion = new Descripcion("DDD useCase");

        var event = new CursoCreado(
                nombre,
                descripcion


        );


        MentoriaId mentoriaId = MentoriaId.of("use");
        Nombre nombreMentoria = new Nombre("hola");
        Fecha fecha = new Fecha(LocalDateTime.now(), LocalDate.now());
        var event2 = new MentoriaCreada(
                mentoriaId,
                nombreMentoria,
                fecha
        );
        event.setAggregateRootId("xxxxx");
        return List.of(event, event2);
    }

}