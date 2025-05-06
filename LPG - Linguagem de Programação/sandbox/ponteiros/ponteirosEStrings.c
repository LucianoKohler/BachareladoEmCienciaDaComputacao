#include <stdio.h>
#include <string.h>

int concatenaString(int* s1, int*s2){
  int n = strlen(s1);

  return strcpy(s1+n, s2);
}

int main(){
  char s1[100] = "Hello, ";
  char s2[100] = "World!";

  concatenaString(s1, s2);

  printf("%s\n", s1);
  printf("%s\n", s2);

  return 0;
}