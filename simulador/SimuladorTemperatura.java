import java.util.Random;

public class SimuladorTemperatura {

    static String[] opciones = {
        "BiblioTec",
        "LibroYa",
        "PrestaLibro",
        "LectoGo",
        "PaginaLibre",
        "NubeDeTinta"
    };

    static double[] puntajes = {
        3.0, 2.5, 2.0, 1.0, 0.5, 0.1
    };

    public static void main(String[] args) {

        double temperatura = args.length > 0
                ? Double.parseDouble(args[0])
                : 1.0;

        if (temperatura < 0) {
            System.out.println("Error: la temperatura no puede ser negativa.");
            return;
        }

        double[] prob = calcularProbabilidades(temperatura);

        System.out.println("Temperatura: " + temperatura);
        System.out.println("----------------------------------------");

        for (int i = 0; i < opciones.length; i++) {

            int barras = (int) Math.round(prob[i] * 40);

            System.out.printf(
                "%-12s %5.1f%% %s%n",
                opciones[i],
                prob[i] * 100,
                "#".repeat(barras)
            );
        }

        System.out.println("----------------------------------------");
        System.out.print("5 intentos: ");

        Random azar = new Random();

        for (int i = 0; i < 5; i++) {
            System.out.print(
                elegir(prob, azar) + (i < 4 ? ", " : "\n")
            );
        }
    }

    static double[] calcularProbabilidades(double t) {

        double[] prob = new double[puntajes.length];

        if (t == 0) {
            prob[0] = 1.0;
            return prob;
        }

        // Evita desbordamientos numéricos.
        double max = puntajes[0];

        for (double puntaje : puntajes) {
            if (puntaje > max) {
                max = puntaje;
            }
        }

        double suma = 0;

        for (int i = 0; i < puntajes.length; i++) {
            prob[i] = Math.exp((puntajes[i] - max) / t);
            suma += prob[i];
        }

        for (int i = 0; i < prob.length; i++) {
            prob[i] /= suma;
        }

        return prob;
    }

    static String elegir(double[] prob, Random azar) {

        double r = azar.nextDouble();
        double acumulado = 0;

        for (int i = 0; i < prob.length; i++) {

            acumulado += prob[i];

            if (r < acumulado) {
                return opciones[i];
            }
        }

        return opciones[opciones.length - 1];
    }
}