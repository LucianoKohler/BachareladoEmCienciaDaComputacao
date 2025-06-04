package dados;

import exceptions.NomeInvalidoException;

public class Arquivo {
  private String nome;
  private String extensao;

  public Arquivo(String nome, String extensao) throws NomeInvalidoException{
    // Checando se o nome é válido
    if(nome.contains("\n")){ throw new NomeInvalidoException("O nome do arquivo possui quebra de linhas"); };
    
    if(nome.contains("]") ||
       nome.contains("[") ||
       nome.contains("{") ||
       nome.contains("}")){ throw new NomeInvalidoException("O nome do arquivo não pode conter colchetes nem chaves"); };

    if(nome.contains("'") ||
         nome.contains("\"")){ throw new NomeInvalidoException("O nome do arquivo não pode conter aspas"); };

    /* No exemplo, o professor diz que deve ser entre 
    10 e 256 chars, porém instancia o elemento "resumo",
    que tem só 6 caracteres, então diminui para 6
    */
    if(nome.length() < 6 || 
       nome.length() > 256) {throw new NomeInvalidoException("O arquivo deve ter entre 10 e 256 caracteres"); };
    // Fim da verificação
    
    this.nome = nome;
    this.extensao = extensao;
  }

  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getExtensao() {
    return extensao;
  }
  public void setExtensao(String extensao) {
    this.extensao = extensao;
  }
}
