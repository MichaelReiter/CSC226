#include <stdio.h>

void minmax (int* a, int i, int j, int* min, int* max) {
  int lmin, lmax, rmin, rmax, mid;
  if (i == j) {
    *min = a[i];
    *max = a[j];
  } else if (j == i + 1) {
    if (a[i] > a[j]) {
      *min = a[j];
      *max = a[i];
    } else {
      *min = a[i];
      *max = a[j];
    }
  } else {
    mid = (i + j) / 2;
    minmax(a, i, mid, &lmin, &lmax);
    minmax(a, mid + 1, j, &rmin, &rmax);
    *min = (lmin > rmin) ? rmin : lmin;
    *max = (lmax > rmax) ? lmax : rmax;
  }
}

int main () {
  int a [] = {3, 4, 2, 6, 8, 1, 9, 12, 15, 11};
  int min, max;
  minmax (a, 0, 9, &min, &max);
  printf ("Min : %d, Max: %d\n", min, max);
  return 0;
}