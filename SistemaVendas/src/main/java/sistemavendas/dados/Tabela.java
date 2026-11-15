package sistemavendas.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import sistemavendas.model.Entidade;

public class Tabela<T extends Entidade> implements Serializable {

    private final List<T> registros = new ArrayList<>();
    private int proximoId = 1;
    private transient File arquivo;

    @SuppressWarnings("unchecked")
    public static <T extends Entidade> Tabela<T> abrir(String nome) {
        File arquivo = new File("dados", nome + ".dat");
        Tabela<T> tabela = new Tabela<>();
        if (arquivo.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
                tabela = (Tabela<T>) in.readObject();
            } catch (Exception e) {
                throw new RuntimeException("Erro ao ler " + arquivo, e);
            }
        }
        tabela.arquivo = arquivo;
        return tabela;
    }

    public void inserir(T registro) {
        registro.setId(proximoId++);
        registros.add(registro);
        salvar();
    }

    public void alterar(T registro) {
        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).getId() == registro.getId()) {
                registros.set(i, registro);
                salvar();
                return;
            }
        }
        throw new IllegalArgumentException("Registro não encontrado.");
    }

    public void excluir(int id) {
        if (!registros.removeIf(r -> r.getId() == id)) {
            throw new IllegalArgumentException("Registro não encontrado.");
        }
        salvar();
    }

    public T consultar(int id) {
        for (T r : registros) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public List<T> listar() {
        return new ArrayList<>(registros);
    }

    private void salvar() {
        arquivo.getParentFile().mkdirs();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(this);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gravar " + arquivo, e);
        }
    }
}
