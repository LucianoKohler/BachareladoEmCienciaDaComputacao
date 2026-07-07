import qualified Lexer as L
import qualified Parser as P
import qualified Semantico as S
import qualified Gerador as G

compila = do
    file <- readFile "inputs/input.j--"
    let programa = P.parser (L.alexScanTokens file)
    let S.Result (status, mensagem, progVerificado) = S.checaPrograma programa
    let nomeClasse = "Output"
    if status == True then putStrLn ("\nErro de compilacao: \n" ++ mensagem ++ "\n")
    else do
        putStrLn ("Logs: \n" ++ mensagem ++ "\n")
        let bytecode = G.gerador nomeClasse progVerificado
        writeFile ("./" ++ nomeClasse ++ ".j") bytecode
        putStrLn ("\nCompilacao completa, arquivo " ++ nomeClasse ++ ".j gerado com sucesso.\n")