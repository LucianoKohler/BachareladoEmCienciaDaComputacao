#include <stdio.h>
#include <string.h>

int main() 
{
  // Strings with always X characters
  char s[6] = "string";
  printf("%10s\n", s); // Align right
  printf("%-10s\n", s); // Align left

  // Numbers with always X digits
  int num = 2;
  printf("%09d\n", num); // Places var at the end

  return 0;
}

// Keywords: Output, formatting, printf, string, int