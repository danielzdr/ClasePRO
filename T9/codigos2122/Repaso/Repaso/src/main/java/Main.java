import contoller.ControllerBD;

public class Main {

    public static void main(String[] args) {
        ControllerBD controllerBD = new ControllerBD();
        //controllerBD.insertarPerfiles();
        //controllerBD.agregarUsuario();
        controllerBD.selectSueldos(500);
    }
}