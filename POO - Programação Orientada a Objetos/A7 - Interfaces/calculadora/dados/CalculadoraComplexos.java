package dados;

public class CalculadoraComplexos implements IOperacoesBasicas<Complexo> {
    public Complexo soma(Complexo op1, Complexo op2) { return new Complexo(op1.getReal() + op2.getReal(), op1.getImaginaria() + op2.getImaginaria()); }
    public Complexo subtracao(Complexo op1, Complexo op2) { return new Complexo(op1.getReal() - op2.getReal(), op1.getImaginaria() - op2.getImaginaria()); }
}
