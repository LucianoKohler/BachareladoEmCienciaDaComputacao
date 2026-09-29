package com.cafeteria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cafeteria.SQL.InfoProdutoDAO;
import com.cafeteria.SQL.AdministrativoDAO;
import com.cafeteria.SQL.ComandaDAO;
import com.cafeteria.SQL.FuncionarioDAO;
import com.cafeteria.SQL.IngredienteDAO;
import com.cafeteria.SQL.ItemPedidoDAO;
import com.cafeteria.SQL.PedidoDAO;
import com.cafeteria.SQL.ProdutoDAO;
import com.cafeteria.dados.Comanda;
import com.cafeteria.dados.Funcionario;
import com.cafeteria.dados.Ingrediente;
import com.cafeteria.dados.ItemRelatorio;
import com.cafeteria.dados.Pedido;
import com.cafeteria.dados.Produto;


public class Main {
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);

        int e = 1;
        while(e == 1){
            e = menu(s);
        }

        s.close();

    }

    /* AUXILIARES + FUNÇÕES SOBRE INGREDIENTES */
    public static int mostraTodosIngredientes(){
        System.out.println("MOSTRANDO TODOS OS INGREDIENTES: ");
        List<Ingrediente> i = IngredienteDAO.selectAll();
        if(i.isEmpty()){
            System.out.println("Não há registros para mostrar.");
            return 0;
        }
        for(Ingrediente ing : i){
            System.out.println("\t" + ing);
        }
        System.out.println();
        return 1;
    }
    public static Ingrediente escolheIngrediente(Scanner s){
        System.out.println("Escolha um ingrediente: ");
        if(mostraTodosIngredientes() == 1){
            System.out.print("Sua escolha: ");
            return IngredienteDAO.procuraIngredientePorID(leInt(s));
        }else{
            System.out.println("Retornando...");
            return null;
        }
    }
    public static void menuEscolhaIngrediente(Scanner s){
        System.out.println("1. Adicionar ingrediente");
        System.out.println("2. Remover ingrediente");
        System.out.println("3. Mostrar todos os ingredientes");
        System.out.println("4. Mostrar um ingrediente");
        System.out.println("5. Voltar");
           
        int escolha = Integer.parseInt(leString(s));

        switch (escolha) {
            case 1:
                Ingrediente novoIng = new Ingrediente();
                System.out.println("Digite o nome do ingrediente:");
                String nome = leString(s);
                novoIng.setNome(nome);
                System.out.println("Digite a descrição do ingrediente:");
                String desc = leString(s);
                novoIng.setDescricao(desc);
                System.out.println("Digite a quantidade do ingrediente:");
                float quant = Float.parseFloat(leString(s));
                novoIng.setQuantidade(quant);
                System.out.println("Digite a unidade de medida da quantidade:");
                String unidade = leString(s);
                novoIng.setUnidade_medida(unidade);

                IngredienteDAO.insereIngrediente(novoIng);

                return;
            case 2:
                System.out.println("Digite o id do ingrediente a ser removido:");
                Ingrediente ing = escolheIngrediente(s);
                if(ing == null){
                    System.out.println("Ingrediente inválido");
                }else{    
                    IngredienteDAO.deletaIngredientePorID(ing.getId());
                }
                return;
            case 3:
                mostraTodosIngredientes();
                return;
            case 4:
                System.out.println("Digite o id do ingrediente a ser mostrado:");
                Ingrediente i = IngredienteDAO.procuraIngredientePorID(leInt(s));
                if(i == null){
                    System.out.println("Ingrediente não encontrado");
                }else{
                    System.out.println("Ingrediente encontrado: ");
                    System.out.println("\t" + i);
                }

                return;
            case 5:
                return;
            default:
                System.out.println("Entrada inválida!");
                return;
        }
    }

    /* AUXILIARES + FUNÇÕES SOBRE PRODUTOS */
    public static void insereIngredienteNoProduto(Scanner s, int idProduto){
        int escolhaIngrediente = 1;
        while(escolhaIngrediente == 1){
            Ingrediente ing = escolheIngrediente(s);
            if(ing != null) {
                System.out.println("Insira a quantidade de ingrediente necessário: ");
                float qtd = leFloat(s);
                System.out.print("Insira observações sobre o uso do ingrediente (ou deixe vazio): ");
                String desc = leString(s);
                InfoProdutoDAO.criaInfoProduto(qtd, desc, ing.getId(), idProduto);
                System.out.println("Adicionar outro ingrediente? (1: Sim, 2: Não)");
                escolhaIngrediente = leInt(s);
            }else{
                System.out.println("Ingrediente inválido!");
            }
        }
    }
    public static int mostraReceitaDeUmProduto(int idProduto){
            System.out.println("Receita: ");
            List<Ingrediente>ings = InfoProdutoDAO.buscaIngredientesDeUmProduto(idProduto);
            if(ings.isEmpty()){
                System.out.println("Não há registros para mostrar.");
                return 0;
            }
            for(Ingrediente i : ings){
                System.out.println("\t- ID: " + i.getId() + "; " + i.getQuantidade() + i.getUnidade_medida() + " de " + i.getNome());

                if(i.getDescricao() != null && !i.getDescricao().trim().isEmpty()){
                    System.out.println("\t\tObs:" + i.getDescricao());
            }
        }
        return 1;
    }
    public static void alteraReceita(Scanner s, int idProduto){
        int idEscolha;
        System.out.println("Quer alterar qual item? (Escolha pelo ID): ");
        if(mostraReceitaDeUmProduto(idProduto) == 1){
            System.out.print("Sua escolha: ");
            idEscolha = leInt(s);
        }else{
            System.out.println("Retornando...");
            return;
        }

        System.out.println("Você quer alterar...");
        System.out.println("1. A quantidade");
        System.out.println("2. A observação");
        System.out.println("3. O ingrediente");
        System.out.print("Sua escolha: ");
        int esc = leInt(s);

        if(esc == 1){
            System.out.print("Insira a nova quantidade: ");
            String qtd = leString(s);
            InfoProdutoDAO.alteraInfoProduto("quantidade", idEscolha, qtd);
        }else if(esc == 2){
            System.out.print("Insira a nova observação (ou deixe em branco): ");
            String obs = leString(s);
            InfoProdutoDAO.alteraInfoProduto("observacao", idEscolha, obs);
        }else if(esc == 3){
            Ingrediente i = escolheIngrediente(s);
            if(i == null){
                System.out.println("Escolha inválida.");
            }else{
                InfoProdutoDAO.alteraInfoProduto("FK_ingrediente", idEscolha, String.valueOf(i.getId()));
            }
        }else{
            System.out.println("Escolha inválida.");
        }
    }
    public static void deletaItemDaReceita(Scanner s, int idProduto){
        System.out.println("Escolha um item para deletar (pelo ID): ");
        if(mostraReceitaDeUmProduto(idProduto) == 1){
            System.out.print("Sua escolha: ");
            int idItemProduto = leInt(s);
            InfoProdutoDAO.deletaItemDaReceita(idItemProduto, idProduto);
        }else{
            System.out.println("Não há registros para mostrar.");
        }
    }
    public static Ingrediente escolheItemDaReceitaDeUmProduto(Scanner s, int idProduto){
        System.out.println("Escolha um item da receita pelo ID: ");
        if(mostraReceitaDeUmProduto(idProduto) == 1){
            System.out.print("Sua escolha: ");
            return IngredienteDAO.procuraIngredientePorID(leInt(s));
        }else{
            System.out.println("Retornando...");
            return null;
        }
    }
    public static int mostraTodosProdutos(){
        System.out.println("MOSTRANDO TODOS OS PRODUTOS");
        List<Produto> list = ProdutoDAO.selectAll();
        if(list.isEmpty()){
            System.out.println("Não há registros para mostrar.");
            return 0;
        }
        for(Produto p : list){
            System.out.println(p);
        }
        System.out.println();
        return 1;
    }
    public static Produto escolheProduto(Scanner s){
        System.out.println("Escolha um produto: ");
        if(mostraTodosProdutos() == 1){
            System.out.print("Sua escolha: ");
            return ProdutoDAO.procuraProdutoPorID(leInt(s));
        }else{
            System.out.println("Retornando...");
            return null;
        }
    }
    public static void menuEscolhaProduto(Scanner s){
        System.out.println("1. Adicionar produto");
        System.out.println("2. Remover produto");
        System.out.println("3. Mostrar todos os produtos");
        System.out.println("4. Mostrar a receita de um produto");
        System.out.println("5. Alterar a receita de um produto");
        System.out.println("6. Voltar");
           
        int escolha = Integer.parseInt(leString(s));

        switch (escolha) {
            case 1:
                Produto novoProd = new Produto();
                System.out.println("Digite o nome do produto:");
                String nome = leString(s);
                novoProd.setNome(nome);
                System.out.println("Digite o preço do produto:");
                float preco = Float.parseFloat(leString(s));
                novoProd.setPreco(preco);
                System.out.println("Digite a categoria do produto:");
                String categoria = leString(s);
                novoProd.setCategoria(categoria);

                int idNovoProduto = ProdutoDAO.insereProduto(novoProd);

                if(idNovoProduto != -1){
                    System.out.println("Deseja adicionar os ingredientes do produto? (1: Sim, 2: Não)");
                    int escolhaIngrediente = leInt(s);
                    if(escolhaIngrediente == 1) insereIngredienteNoProduto(s, idNovoProduto);
                }
                return;
            case 2:
                Produto p = escolheProduto(s);
                if(p == null){
                    System.out.println("Produto inválido");
                }else{
                    ProdutoDAO.deletaProdutoPorID(p.getId());
                }
                return;
            case 3:
                mostraTodosProdutos();
                return;
            case 4:
                Produto prod = escolheProduto(s);
                if(prod == null){
                    System.out.println("Produto inválido");
                }else{
                    mostraReceitaDeUmProduto(prod.getId());
                }
                return;
            case 5:
                System.out.println("Escolha um produto para alterar a receita: ");
                Produto p1 = escolheProduto(s);
                if(p1 == null){
                    System.out.println("Produto inválido.");
                    return;
                }
                System.out.println("Você quer...");
                System.out.println("1. Alterar um item da receita");
                System.out.println("2. Inserir algo na receita");
                System.out.println("3. Deletar algo da receita");
                int escolhaReceita = leInt(s);
                     if(escolhaReceita == 1) alteraReceita(s, p1.getId());
                else if(escolhaReceita == 2) insereIngredienteNoProduto(s, p1.getId());
                else if(escolhaReceita == 3) deletaItemDaReceita(s, p1.getId());
                else                         System.out.println("Escolha inválida!");
                return;
            case 6:
                return;
            default:
                System.out.println("Entrada inválida!");
                return;

        }
    }

    /* AUXILIARES + FUNÇÕES SOBRE FUNCIONÁRIOS */
    public static int mostraTodosFuncionarios(){
        System.out.println("MOSTRANDO TODOS OS FUNCIONÁRIOS: ");
        List<Funcionario> list = FuncionarioDAO.selectAll();
        if(list.isEmpty()){
            System.out.println("Não há registros para mostrar.");
            return 0;
        }
        for(Funcionario f : list){
            System.out.println(f);
        }
        System.out.println();
        return 1;
    }
    public static Funcionario escolheFuncionario(Scanner s){
        if(mostraTodosFuncionarios() == 1){
            System.out.print("Sua escolha: ");
            return FuncionarioDAO.procuraFuncionarioPorID(leInt(s));
        }else{
            System.out.println("Retornando...");
            return null;
        }
    }
    public static void menuEscolhaFuncionario(Scanner s){
        System.out.println("1. Adicionar funcionário");
        System.out.println("2. Remover funcionário");
        System.out.println("3. Mostrar todos os funcionários");
        System.out.println("4. Mostrar um funcionário");
        System.out.println("5. Voltar");
           
        int escolha = Integer.parseInt(leString(s));

        switch (escolha) {
            case 1:
                Funcionario novoFunc = new Funcionario();
                System.out.println("Digite o nome do funcionário:");
                String nome = leString(s);
                novoFunc.setNome(nome);
                System.out.println("Digite o salário do funcionário:");
                float salario = Float.parseFloat(leString(s));
                novoFunc.setSalario(salario);
                System.out.println("Digite a data de contratação do funcionário no formato dd/mm/aaaa:");
                String entrada = leString(s);
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(entrada, fmt);
                novoFunc.setData_contratacao(data);
                System.out.println("Digite o cargo do funcionário:");
                String cargo = leString(s);
                novoFunc.setCargo(cargo);

                FuncionarioDAO.insereFuncionario(novoFunc);
                return;
            case 2:
                Funcionario f = escolheFuncionario(s);
                FuncionarioDAO.deletaFuncionarioPorID(f.getId());
                return;
            case 3:
                mostraTodosFuncionarios();
                return;
            case 4:
                System.out.println("Digite o id do funcionário a ser mostrado:");
                int idd = Integer.parseInt(leString(s));
                Funcionario func = FuncionarioDAO.procuraFuncionarioPorID(idd);
                if(func == null){
                    System.out.println("Funcionário não encontrado");
                }else{
                    System.out.println(func);
                }
                return;
            case 5:
                return;
            default:
                System.out.println("Entrada inválida!");
                return;

           }
    }

    /* AUXILIARES + FUNÇÕES SOBRE COMANDAS */
    public static int mostraTodasComandasAbertas(){
        System.out.println("MOSTRANDO TODAS AS COMANDAS ABERTAS");
        List<Comanda> list = ComandaDAO.selectAll("aberta");
        if(list.isEmpty()){
            System.out.println("Não há registros para mostrar.");
            return 0;
        }
        for(Comanda c : list){
            System.out.println(c);
        }
        System.out.println();
        return 1;
    }
    public static int mostraTodasComandasPagas(){
        System.out.println("MOSTRANDO TODAS AS COMANDAS PAGAS");
        List<Comanda> list = ComandaDAO.selectAll("paga");
        if(list.isEmpty()){
            System.out.println("Não há registros para mostrar.");
            return 0;
        }
        for(Comanda c : list){
            System.out.println(c);
        }
        System.out.println();
        return 1;
    }
    public static int mostraTodasComandas(){
        System.out.println("MOSTRANDO TODAS AS COMANDAS");
        List<Comanda> list = ComandaDAO.selectAll("");
        if(list.isEmpty()){
            System.out.println("Não há registros para mostrar.");
            return 0;
        }
        for(Comanda c : list){
            System.out.println(c);
        }
            System.out.println();
            return 1;
        }
    public static Comanda escolheComanda(Scanner s){
        System.out.println("Escolha uma Comanda: ");
        if(mostraTodasComandas() == 1){
            System.out.print("Sua escolha: ");
            return ComandaDAO.procuraComandaPorID(leInt(s));
        }else{
            System.out.println("Retornando...");
            return null;
        }
    }
    public static Comanda escolheComandaAberta(Scanner s){
        if(mostraTodasComandasAbertas() == 1){
            System.out.print("Sua escolha: ");
            Comanda c = ComandaDAO.procuraComandaPorID(leInt(s));
            if(c != null && c.getStatus_pgto().equals("aberta")){
                return c;
            }else{
                System.out.println("Comanda inválida.");
                return null;
            }
        }
        System.out.println("Não há registros para mostrar.");
        return null;
    }
    public static void menuEscolhaComanda(Scanner s){
        System.out.println("1. Criar comanda");
        System.out.println("2. Fazer checkout de uma comanda");
        System.out.println("3. Remover comanda");
        System.out.println("4. Mostrar todas as comandas abertas");
        System.out.println("5. Mostrar todas as comandas pagas");
        System.out.println("6. Mostrar uma comanda");
        System.out.println("7. Voltar");
           
        int escolha = leInt(s);

        switch (escolha) { 
            case 1:
                System.out.println("Digite o número da mesa da comanda:");
                int numero = leInt(s);

                ComandaDAO.criaComanda(numero);
                return;
            case 2:
                Comanda c = escolheComandaAberta(s);

                if(c == null){
                    System.out.println("Comanda não encontrada");
                }else{
                    if(ComandaDAO.possuiPedidosPendentes(c.getId())){
                        System.out.println("Impossível fechar a comanda pois ela ainda possui pedidos pendentes.");
                        return;
                    }
                    double total = ComandaDAO.calcularTotal(c.getId());
                    System.out.println("Total: R$ " + total);
                    ComandaDAO.alteraComanda("status_pgto", c.getId(), "paga");
                    System.out.println("Checkout concluído!");
                }
                return;
            case 3:
                System.out.println("Escolha uma comanda para remover: ");
                Comanda com = escolheComanda(s);
                ComandaDAO.deletaComandaPorID(com.getId());
                return;
            case 4:
                mostraTodasComandasAbertas();
                return;
            case 5:
                mostraTodasComandasPagas();
                return;
            case 6:
                System.out.println("Insira o ID da comanda a ser mostrada: ");
                int cId = leInt(s);
                Comanda encontrada = ComandaDAO.procuraComandaPorID(cId);
                if(encontrada == null){
                    System.out.println("Comanda não encontrada");
                }else{
                    System.out.println(encontrada);
                }
                return;
            case 7:
                return;
            default:
                System.out.println("Entrada inválida!");
                return;

           }
    }

    /* AUXILIARES + FUNÇÕES SOBRE PEDIDOS */
    public static int mostraItensDeUmPedido(int idPedido){
        System.out.println("\tITENS DO PEDIDO:");
        List<Produto> produtos = ItemPedidoDAO.mostraItensDeUmPedido(idPedido);
        if(produtos.isEmpty()){
            System.out.println("Não há itens no pedido");
            return 0;
        }else{
            for(Produto prod : produtos){
                System.out.println("\t\tID: " + prod.getId() + "; " + (int)prod.getPreco() + "x " + prod.getNome());
            }
            return 1;
        }

    }
    public static int mostraTodosPedidosPendentes(){
        System.out.println("MOSTRANDO TODOS OS PEDIDOS PENDENTES");
        List<Pedido> list = PedidoDAO.selectAll("pendente");
        if(list.isEmpty()){
            System.out.println("Não há registros para mostrar.");
            return 0;
        }
        for(Pedido p : list){
            System.out.println(p);
            mostraItensDeUmPedido(p.getId());
        }
        System.out.println();
        return 1;
    }
    public static int mostraTodosPedidosConcluidos(){
        System.out.println("MOSTRANDO TODOS OS PEIDDOS S");
        List<Pedido> list = PedidoDAO.selectAll("atendido");
        if(list.isEmpty()){
            System.out.println("Não há registros para mostrar.");
            return 0;
        }
        for(Pedido p : list){
            System.out.println(p);
            mostraItensDeUmPedido(p.getId());
        }
        System.out.println();
        return 1;
    }
    public static int mostraTodosPedidos(){
        System.out.println("MOSTRANDO TODOS OS PEDIDOS");
        List<Pedido> list = PedidoDAO.selectAll("");
        if(list.isEmpty()){
            System.out.println("Não há registros para mostrar.");
            return 0;
        }
        for(Pedido p : list){
            System.out.println(p);
            mostraItensDeUmPedido(p.getId());

        }
        System.out.println();
        return 1;

    }
    public static Pedido escolhePedidoConcluido(Scanner s){
        System.out.println("Escolha um pedido: ");
        if(mostraTodosPedidosConcluidos() == 1){
            System.out.print("Sua escolha: ");
            Pedido p =  PedidoDAO.procuraPedidoPorID(leInt(s));
            
            if(p != null && p.getStatus_pedido().equals("atendido")){
                return p;
            }else{
                System.out.println("Pedido inválido.");
            }
        }else{
            System.out.println("Retornando...");
        }
        return null;
    }
    public static Pedido escolhePedidoPendente(Scanner s){
        System.out.println("Escolha um pedido: ");
        if(mostraTodosPedidosPendentes() == 1){
            System.out.print("Sua escolha: ");
            Pedido p =  PedidoDAO.procuraPedidoPorID(leInt(s));
            if(p.getStatus_pedido().equals("pendente")){
                return p;
            }else{
                System.out.println("Pedido inválido.");
            }
        }else{
            System.out.println("Retornando...");
        }
        return null;
    }
    public static Pedido escolhePedido(Scanner s){
        System.out.println("Escolha um pedido: ");
        if(mostraTodosPedidos() == 1){
            System.out.print("Sua escolha: ");
            return PedidoDAO.procuraPedidoPorID(leInt(s));
        }else{
            System.out.println("Retornando...");
            return null;
        }
    }
    public static void menuEscolhaPedido(Scanner s){
        System.out.println("1. Fazer um pedido");
        System.out.println("2. Atender um pedido");
        System.out.println("3. Cancelar um pedido");
        System.out.println("4. Deletar um pedido");
        System.out.println("5. Mostrar todos os pedidos pendentes");
        System.out.println("6. Mostrar todos os pedidos concluídos");
        System.out.println("7. Alterar observação de um pedido");
        System.out.println("8. Voltar");

        int escolha = leInt(s);

        switch (escolha) {
            case 1:
                System.out.println("Qual comanda fez o pedido?");
                Comanda c = escolheComandaAberta(s);
                if(c == null){
                    return;
                }

                int novoPedidoID = PedidoDAO.criaPedido(c.getId(), "");
                int escolhaPedido = 1;
                while(escolhaPedido == 1){
                    System.out.println("Insira o produto pedido: ");
                    Produto p = escolheProduto(s);
                    if(p == null){
                        System.out.println("Escolha de produto inválida!");
                        continue;
                    }
                    System.out.println("Você quer quantos desse item?");
                    int qtd = leInt(s);

                    ItemPedidoDAO.criaItemPedido(qtd, p.getPreco(), novoPedidoID, p.getId());

                    System.out.println("Criado! Quer adicionar outro produto no pedido? (1: Sim, 2: Não)");
                    escolhaPedido = leInt(s);
                }
                System.out.println("Insira observações sobre o pedido (se aplicável, ou só deixe em branco): ");
                String obs = leString(s);
                PedidoDAO.alteraPedido("observacao", novoPedidoID, obs);
                break;
            case 2:
                System.out.println("Escolha o pedido à ser concluído: ");
                Pedido p = escolhePedidoPendente(s);
                if(p == null){
                    System.out.println("Pedido inválido");
                    return;
                }
                System.out.println("Que funcionário atenderá o pedido? ");
                Funcionario f = escolheFuncionario(s);
                if(f == null){
                    System.out.println("Funcionário inválido");
                    return;
                }
                    if(PedidoDAO.alteraPedido("FK_funcionario", p.getId(), String.valueOf(f.getId())) && 
                    PedidoDAO.alteraPedido("status_pedido", p.getId(), "atendido"))
                    System.out.println("Pedido atendido com sucesso!");
                    break;
                case 3:
                    System.out.println("Escolha o pedido à ser cancelado: ");
                    Pedido p1 = escolhePedidoPendente(s);
                    if(p1 == null || p1.getStatus_pedido() == "atendido"){
                        System.out.println("Pedido inválido");
                    }else{
                        if(PedidoDAO.alteraPedido("status_pedido", p1.getId(), "cancelado"))
                            System.out.println("Pedido cancelado!");
                    }
                break;
            case 4:
                System.out.println("Escolha o pedido à ser deletado: ");
                Pedido p2 = escolhePedido(s);
                if(p2 == null){
                    System.out.println("Pedido inválido");
                }else{
                    PedidoDAO.deletaPedidoPorID(p2.getId());
                }
                break;
            case 5:
                mostraTodosPedidosPendentes();
                break;
                case 6:
                mostraTodosPedidosConcluidos();
                break;
                
            case 7:
                System.out.println("Escolha um pedido para alterar a observação: ");
                Pedido p3 = escolhePedidoPendente(s);
                if(p3 != null && p3.getStatus_pedido() != "atendido"){
                    System.out.println("Insira a nova observação (ou deixe em branco): ");
                    String novaObs = leString(s);
                    PedidoDAO.alteraPedido("observacao", p3.getId(), novaObs);
                    }
                break;
        
            default:
                System.out.println("Escolha inválida.");
                break;
        }

    }

    /* AUXILIARES + FUNÇÕES ADMINISTRATIVAS */
    public static void mostraProdutosMaisVendidos(Scanner s, int qtd){
        List<Map.Entry<String, Integer>> produtos = AdministrativoDAO.mostraProdutosMaisVendidos();
        for (Map.Entry<String, Integer> par : produtos) {
            String nome = par.getKey();    
            Integer qtdVenda = par.getValue();  
            
            System.out.println("Item: " + nome + "; Vendas: " + qtdVenda);
            qtd--;
            if(qtd == 0) break;
        }      
    }
    public static void mostraMediaProdutos(){
        List<Map.Entry<String, Float>>medias = AdministrativoDAO.mostraMediaDePrecoDosProdutos();
        for(Map.Entry<String, Float> media : medias){
            System.out.println("Categoria: " + media.getKey() + ": Média: R$ " + media.getValue());
        }
    }
    public static void mostraBaristaDoMes(){
        ItemRelatorio barista = AdministrativoDAO.mostraBaristaDoMes();
        if(barista == null){
            System.out.println("Não há dados suficientes para retornar o barista do mês.");
        }else{
            System.out.println("Parabéns a " + barista.getNome() + " por atender um total de " +
            barista.getQuantidade() + " pedidos e gerar incríveis R$" + barista.getValor() + " de faturamento nesse mês!!!");
        }
    }
    public static void menuEscolhaAdministrativa(Scanner s){
        System.out.println("1. Mostrar os produtos mais vendidos");
        System.out.println("2. Mostrar a média de preço dos produtos");
        System.out.println("3. Mostrar o barista do mês");
        System.out.println("4. Voltar ");

        int escolha = leInt(s);
        switch (escolha) {
            case 1:
                System.out.println("Quantos produtos devem ser mostrados?");
                int qtdProdutos = leInt(s);
                mostraProdutosMaisVendidos(s, qtdProdutos);
                break;
            case 2:
                mostraMediaProdutos();
                break;
            case 3:
                mostraBaristaDoMes();
                break;
            case 4:
                break;
        
            default:
                System.out.println("Escolha inválida!");
                break;
        }
    }

    public static int menu(Scanner s){
        System.out.println("---------------------------------");
        System.out.println(".o°o.o°o.o° CAFETERIA °o.o°o.o°o.");
        System.out.println("1. Opções com Ingredientes");
        System.out.println("2. Opções com Produtos");
        System.out.println("3. Opções com Funcionários");
        System.out.println("4. Opções com Comanda");
        System.out.println("5. Opções com Pedido");
        System.out.println("6. Opções administrativas");
        System.out.println("7. Sair do Programa");
        
        int escolha = Integer.parseInt(leString(s));

        switch (escolha) {
            case 1:
                menuEscolhaIngrediente(s);
                break;
            case 2:
                menuEscolhaProduto(s);
                break;
            case 3:
                menuEscolhaFuncionario(s);
                break;
            case 4:
                menuEscolhaComanda(s);
                break;
            case 5:
                menuEscolhaPedido(s);
                break;
            case 6:
                menuEscolhaAdministrativa(s);
                break;
            case 7:
                System.out.println("Até mais!");
                return 0;
            default:
                System.out.println("Entrada inválida!");
                break;
        }
        return 1;
    }

    public static int leInt(Scanner s){
        int n = s.nextInt();
        s.nextLine();
        return n;
    }

    public static float leFloat(Scanner s){
        float n = s.nextFloat();
        s.nextLine();
        return n;
    }

    public static String leString(Scanner s){
        String n = s.nextLine();
        return n;
    }
}
