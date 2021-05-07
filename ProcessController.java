import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ProcessController {
    List<Process> listaDeProcessos = new ArrayList<>();
    List<Process> listaDeProcessosProntosParaExecucao = new ArrayList<>();
    List<Process> listaDeProcessosEmExecucao = new ArrayList<>();
    List<Process> listaDeProcessosTerminados = new ArrayList<>();
    List<Process> listaDeProcessosBloqueados = new ArrayList<>();

    Random numeroDePrioridadeAleatoria = new Random();
    Random numeroAleatorioQueBloqueiaProcesso = new Random();

    Scanner entrada = new Scanner(System.in);

    void iniciarExecucao() {
        iniciarProcessos();

        int option;

        do {

            for (int i = 0; i < 4; i++) {
                Process processoComMaiorPrioridade = retornaProcessosPorPrioridade();
                executaProcesso(processoComMaiorPrioridade);

                int numeroAleatorio = numeroAleatorioQueBloqueiaProcesso.nextInt(2);

                if (numeroAleatorio == 1) {
                    Process processoASerBloqueado = bloqueiaProcessoDeModoAleatorio();
                    // System.out.println("Interrupção por Preempição");
                    processoASerBloqueado.diminuiPrioridade();

                } else {
                    Process processoASerBloqueado = bloqueiaProcessoDeModoAleatorio();
                    // System.out.println("Interrupção por chamada de sistema");
                    processoASerBloqueado.aumentaPrioridade();
                }

            }
            String leftAlignFormat = "| %-18s | %n";

            System.out.format("+--------------------+%n");
            System.out.format("| A serem executados |%n");
            System.out.format("+--------------------+%n");

            for (Process process : listaDeProcessosProntosParaExecucao) {
                System.out.format(leftAlignFormat, Integer.toString(process.getId()));
            }
            System.out.format("+--------------------+%n");

            leftAlignFormat = "| %-11s | %n";

            System.out.format("+-------------+%n");
            System.out.format("| Em execução |%n");
            System.out.format("+-------------+%n");

            for (Process process : listaDeProcessosEmExecucao) {
                System.out.format(leftAlignFormat, Integer.toString(process.getId()));
            }
            System.out.format("+--------------------+%n");

            leftAlignFormat = "| %-10s | %n";

            System.out.format("+------------+%n");
            System.out.format("| Terminados |%n");
            System.out.format("+------------+%n");

            for (Process process : listaDeProcessosTerminados) {
                System.out.format(leftAlignFormat, Integer.toString(process.getId()));
            }
            System.out.format("+--------------------+%n");


            leftAlignFormat = "| %-10s | %n";

            System.out.format("+------------+%n");
            System.out.format("| Bloqueados |%n");
            System.out.format("+------------+%n");

            for (Process process : listaDeProcessosBloqueados) {
                System.out.format(leftAlignFormat, Integer.toString(process.getId()));
            }
            System.out.format("+--------------------+%n");

            System.out.println("Continuar na simulação ? 1 - SIM, 2 - NÃO");
            option = entrada.nextInt();
        } while (option != 2);

    }

    void iniciarProcessos() {
        for (int i = 0; i < 20; i++) {
            Process processo = new Process(numeroDePrioridadeAleatoria.nextInt(20));
            listaDeProcessos.add(processo);
            listaDeProcessosProntosParaExecucao.add(processo);
        }
    }

    Process retornaProcessosPorPrioridade() {
        Collections.sort(listaDeProcessosProntosParaExecucao);
        return listaDeProcessosProntosParaExecucao.get(0);
    }

    void executaProcesso(Process processo) {
        processo.setEstado(1);
        listaDeProcessosEmExecucao.add(processo);
        listaDeProcessosProntosParaExecucao.remove(processo);
    }

    Process bloqueiaProcessoDeModoAleatorio() {
        Process processoASerBloqueado = listaDeProcessosEmExecucao
                .get(numeroAleatorioQueBloqueiaProcesso.nextInt(listaDeProcessosEmExecucao.size()));
        listaDeProcessosEmExecucao.remove(processoASerBloqueado);
        listaDeProcessosBloqueados.add(processoASerBloqueado);
        return processoASerBloqueado;
    }
}
