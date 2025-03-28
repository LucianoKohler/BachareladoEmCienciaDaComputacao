#include <stdio.h>
#include <string.h>

int main() 
{
    char c;
    char word[20];
    char sen[100];
    
    scanf("%c", &c); // Read char
    scanf("%s", word); // Read string
    scanf("\n"); // Skip the blank line so line 13 does not break
    scanf("%[^\n]%*c", sen); // Reads the entire line
    
    printf("%c\n", c);
    printf("%s\n", word);
    printf("%s\n", sen);
    return 0;
}

// Keywords: Input, scanf, string