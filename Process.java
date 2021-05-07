// 0 -> Pronto, 1 -> Em execução, 2 -> Suspenso, 3 -> Finalizado

public class Process implements Comparable<Process> {
    static int idClasse = 0;

    private int id;
    private int estado;
    private int prioridade;
    private long tempoInicial;

    Process(int prioridade) {
        idClasse ++;
        this.id = idClasse + 1;
        this.estado = 0;
        this.prioridade = prioridade;
    };

    // set
    // get

    public int getId() {
        return id;
    }

    void aumentaPrioridade() {
        prioridade++;
    }

    void diminuiPrioridade() {
        prioridade--;
    }

    void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public int compareTo(Process process) {
        if (this.prioridade < process.prioridade) {
            return -1;
        }
        if (this.prioridade > process.prioridade) {
            return 1;
        } else {
            return 0;
        }

    }

}
