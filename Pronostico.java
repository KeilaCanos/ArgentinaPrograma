public class Pronostico {
    private Partido partido;
    private ResultadoEnum resultadoPronostico;
    private int idRonda;
    private Persona participante;
    public Pronostico(Partido partido, ResultadoEnum resultadoPronostico, int idRonda, Persona perso)
    {
        this.partido = partido;
        this.resultadoPronostico = resultadoPronostico;
        this.idRonda = idRonda;
        this.participante = perso;
    }

    public void puntos()
    {
        if(this.partido.resultadoPartido() == this.resultadoPronostico)
        {
            participante.setPuntos(1);
        }
    }

    public Partido getPartido() {
        return partido;
    }

    public int getIdRonda() {
        return idRonda;
    }

    public void setNomParticipante(String participante) {
         this.participante.setNombre(participante);
    }

    public Persona getParticipante() {
        return participante;
    }
    
    
    
}
