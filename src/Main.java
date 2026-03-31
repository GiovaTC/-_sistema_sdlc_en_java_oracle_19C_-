// clase principal ( simulacion sdlc )
public class Main {

    public static void main(String[] args) {

        // 1. crear proyecto
        int idProyecto = SDLCService.crearProyecto(
                "Sistema de ventas",
                "Aplicacion para gestion de ventas en tienda"
        );

        // 2. registrar etapas del ciclo de vida .
        SDLCService.registrarEtapa(idProyecto, "Requerimientos",
                "Levantamiento de necesidades del cliente", "COMPLETADO");

        SDLCService.registrarEtapa(idProyecto, "Analisis y diseño",
                "Modelado UML y arquitectura del sistema", "COMPLETADO");

        SDLCService.registrarEtapa(idProyecto, "Implementacion",
                "Desarrollo en JAVA + Oracle", "EN PROCESO ");

        SDLCService.registrarEtapa(idProyecto, "Pruebas",
                "Pruebas unitarias y de integracion", "PENDIENTE");

        SDLCService.registrarEtapa(idProyecto, "Instalacion",
                "Despliegue en servidor", "PENDIENTE");

        // 3. mostrar resultado completo
        SDLCService.mostrarProyecto(idProyecto);
    }
}