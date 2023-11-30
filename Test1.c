#include <stdio.h>

void swap(int* p1, int* p2) {
  int temp = *p1;
  *p1 = *p2;
  *p2 = temp;
}

int main() {
  int a = 100;
  int b = 200;
  swap(&a, &b);

  printf("a = %d, b = %d\n", a, b);
  return 0;
}