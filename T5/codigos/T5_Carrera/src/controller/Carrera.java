package controller;

import model.Circuito;
import model.Coche;

import java.util.ArrayList;

public class Carrera {

    private Circuito circuito;
    private Coche ganador;
    private ArrayList<Coche> participantes;

    public Carrera(){}
    public Carrera(Circuito circuito){
        this.circuito = circuito;
        // ganador = null
        this.participantes = new ArrayList<>();
    }

    // crear un metodo mostrarDatos
    // Esta carrera se corre en XXX.
    // Los datos de la carrera son:
    // KM totales: XXX
    // Vueltas: XXX
    // Nº participantes: XXX

    public void mostrarDatos(){
        System.out.println("La carrera se corre en "+circuito.getNombre());
        System.out.println("Los datos de la carrera son");
        System.out.println("KM: "+circuito.getKmTotales());
        System.out.println("Vueltas: "+circuito.getVueltas());
        System.out.println("Nº de participantes: "+participantes.size());
    }

    // inscribir un participante
    // obtengo como parametro un coche
    // no puede haber dos participantes con la misma matricula
    // como máximo hay 20 participantes
    // si un participante se inscribe -> Participante inscrito con existo
    // si no puede se inscribe -> Problema en la inscripcion

    public void inscribirParticipante(Coche coche){
        if (participantes.size()==20){
            System.out.println("Error en la inscripcion: no hay plazas");
        } else {
            // esta la matricula del coche que quiero agregar en la lista?
            // true o false
            if (estaCoche(coche.getMatricula())!=null){
                System.out.println("Error en la inscripcion. Datos duplicados");
            } else {
                participantes.add(coche);
                System.out.println("Agregado correctamente");
            }
        }
    }

    private Coche estaCoche(String matricula){

        Coche coincideCoche = null;
        // recorre la lista y pregunta por las matriculas
        for ( Coche coche : this.participantes ) {
            if (coche.getMatricula().equalsIgnoreCase(matricula)){
                coincideCoche = coche;
                break;
            }
        }
        return coincideCoche;
    }

    public void descalificarParticipante(String matricula){
        // esta el coche en la lista?
        // coche

        Coche cocheEncontrado= estaCoche(matricula);

        if (cocheEncontrado!=null){
            participantes.remove(cocheEncontrado);
            System.out.println("El coche se ha borrado ok");
        } else {
            System.out.println("No esta");
        }

        /*if (participantes.remove(estaCoche(matricula))){
            System.out.println("Borrado correctamente");
        }else {
            System.out.println("No esta");
        }*/
    }

    public void mostrarParrilla(){
        for (int i = 0; i < participantes.size(); i++) {
            System.out.printf("%d %s\n",i+1,participantes.get(i).getNombre());
        }

        /*int posicion = 1;
        for (Coche item: participantes) {
            System.out.printf("%d %s\n",posicion,item.getNombre());
            posicion++;
        } */
    }


    public Circuito getCircuito() {
        return circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    public Coche getGanador() {
        return ganador;
    }

    public void setGanador(Coche ganador) {
        this.ganador = ganador;
    }

    public ArrayList<Coche> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Coche> participantes) {
        this.participantes = participantes;
    }
}
