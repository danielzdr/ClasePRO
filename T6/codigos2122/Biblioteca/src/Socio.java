import java.util.ArrayList;

public final class Socio extends Persona {
    private int numeroSocio;
    private ArrayList<Prestable> listaPrestamos;

    public Socio(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
        this.listaPrestamos = new ArrayList();
    }

    public Socio() {
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println(numeroSocio);
        System.out.println("Prestamos");
        for (Object item: listaPrestamos) {
            System.out.println(item);
        }
    }

    public int getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public ArrayList<Prestable> getListaPrestamos() {
        return listaPrestamos;
    }

    public void setListaPrestamos(ArrayList<Prestable> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
    }
}