package negocio;

import java.util.List;
import java.util.ArrayList;

import dados.FormaGeometrica;

public class GerenciadorFormasGeometricas {
  private List<FormaGeometrica> formas;

  public GerenciadorFormasGeometricas() {
    this.formas = new ArrayList<FormaGeometrica>();
  }

  public List<FormaGeometrica> getFormas() {
    return formas;
  }
  public void setFormas(List<FormaGeometrica> formas) {
    this.formas = formas;
  }

  public void adicionarForma(FormaGeometrica forma){
    formas.add(forma);
  }
}
