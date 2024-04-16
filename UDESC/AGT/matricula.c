#include <stdio.h>
#include <string.h>
int main()
{
    char validacao, escolha; 
    // escolha foi uma variável usada em todo o código para decidir coisas rápidas (S/N)
    char materiasValidadas[30], curso[2]; 
    // materiasValidadas é uma string que, se uma materia for validada, será concatenada na string
    int fase, creditos = 0, totalDeDisciplinas = 0;
    
    printf("Escolha o curso à se matricular (escreva a sigla): ");
    scanf("%s", curso);
    
    if(strcmp(curso, "CC") == 0){
        printf("Qual a sua fase: ");
        scanf("%d", &fase);
        
        if(fase == 1){
            
            printf("Tentar validação (S/N): ");
            scanf(" %c", &validacao);
            
            if(validacao == 'S'){
            
                printf("Vai validar AGT (S/N): ");
                scanf(" %c", &escolha);
                if(escolha == 'S'){strcat(materiasValidadas, "AGT ");}
                
                printf("Vai validar TGS (S/N): ");
                scanf(" %c", &escolha);
                if(escolha == 'S'){strcat(materiasValidadas, "TGS ");}
            
            }
            
            // Saída de dados
            
            printf("\nMatrícula compulsória em:\n");
            printf("Primeira Fase --- Cred. Teo. --- Cred. Prat\n");
            printf("AGT0001       ---     02     ---     02    \n");
            printf("GAN0001       ---     04     ---           \n");
            printf("ICD0001       ---     04     ---           \n");
            printf("LMA0001       ---     04     ---           \n");
            printf("PFN0001       ---     02     ---     02    \n");
            printf("TGS0001       ---     02     ---     02    \n");
            printf("\nTotal de Creditos: 24");
            printf("\nTotal de Disciplinas: 6");
            // Se houverem materiasValidadas, mostrá-las
            if(strcmp(materiasValidadas, "") != 0){printf("\nPedidos de validação: %s", materiasValidadas);}
            
        }else if(fase == 2){
            printf("\nVai fazer ALI (S/N): ");
            scanf(" %c", &escolha);
            
            if(escolha == 'S'){
                printf("Ja fez GAN (S/N): ");
                scanf(" %c", &escolha);
                if(escolha == 'S'){
                    creditos+=4;
                    totalDeDisciplinas++;
                }else{
                    printf("E necessario completar GAN para se matricular nessa materia\n");
                }
            }
            printf("\nVai fazer CDI (S/N): ");
            scanf(" %c", &escolha);
            if(escolha == 'S'){
                printf("Ja fez ICD (S/N): ");
                scanf(" %c", &escolha);
                if(escolha == 'S'){
                    creditos+=6;
                    totalDeDisciplinas++;
                }else{
                    printf("E necessario completar ICD para se matricular nessa materia\n");
                }
            }else{
                printf("Vai validar CDI (S/N): ");
                scanf(" %c", &escolha);
                if(escolha == 'S'){
                    strcat(materiasValidadas, "CDI ");
                    // No exemplo, ao validar, não se ganha crédito, logo não adicionei aqui também
                }
            }
            printf("\nVai fazer ECC (S/N): ");
            scanf(" %c", &escolha);
            if(escolha == 'S'){
                creditos+=4;
                totalDeDisciplinas++;
            }
            
            // Saída de dados
            
            printf("\nSegunda Fase --- Cred. Teo. --- Cred. Prat --- Pre-requisitos\n");
            printf("ALI0001      ---     04     ---            ---    GAN0001      \n");
            printf("CDI0001      ---     06     ---            ---    ICD0001      \n");
            printf("ECC0001      ---     02     ---     02     ---                 \n");
            if(totalDeDisciplinas == 0){
                printf("\nSua matricula ira constar como trancada.\nE preciso escolher ao menos uma disciplina\n");
            }
            printf("\nTotal de Creditos: %d", creditos);            
            printf("\nTotal de Disciplinas: %d", totalDeDisciplinas);
            // Se houverem materiasValidadas, mostrá-las
            if(strcmp(materiasValidadas, "") != 0){printf("\nPedidos de validação: %s", materiasValidadas);}
            
        }else{printf("Periodo invalido.\n");}
    }else{printf("Curso Invalido.\n");}
    return 0;
}
