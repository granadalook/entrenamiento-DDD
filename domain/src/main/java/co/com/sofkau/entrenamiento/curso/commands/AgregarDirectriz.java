package co.com.sofkau.entrenamiento.curso.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofkau.entrenamiento.curso.values.CursoId;
import co.com.sofkau.entrenamiento.curso.values.Directiz;
import co.com.sofkau.entrenamiento.curso.values.MentoriaId;

public class AgregarDirectriz extends Command {
    private CursoId cursoId;
  private MentoriaId mentoriaId;
    private Directiz directiz;


    public AgregarDirectriz(CursoId cursoId, MentoriaId mentoriaId, Directiz directiz) {
        this.cursoId = cursoId;
        this.mentoriaId = mentoriaId;
        this.directiz = directiz;
    }

    public CursoId getCursoId() {
        return cursoId;
    }

    public void setCursoId(CursoId cursoId) {
        this.cursoId = cursoId;
    }

    public MentoriaId getMentoriaId() {
        return mentoriaId;
    }

    public void setMentoriaId(MentoriaId mentoriaId) {
        this.mentoriaId = mentoriaId;
    }

    public Directiz getDirectiz() {
        return directiz;
    }

    public void setDirectiz(Directiz directiz) {
        this.directiz = directiz;
    }
}
