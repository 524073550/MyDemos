/*
 * Copyright (C) 1999-2002, 2016 Free Software Foundation, Inc.
 * This file is part of the GNU LIBICONV Library.
 *
 * The GNU LIBICONV Library is free software; you can redistribute it
 * and/or modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * The GNU LIBICONV Library is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with the GNU LIBICONV Library; see the file COPYING.LIB.
 * If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * CP853
 */

static const unsigned short cp853_2uni[128] = {
  /* 0x80 */
  0x00c7, 0x00fc, 0x00e9, 0x00e2, 0x00e4, 0x00e0, 0x0109, 0x00e7,
  0x00ea, 0x00eb, 0x00e8, 0x00ef, 0x00ee, 0x00ec, 0x00c4, 0x0108,
  /* 0x90 */
  0x00c9, 0x010b, 0x010a, 0x00f4, 0x00f6, 0x00f2, 0x00fb, 0x00f9,
  0x0130, 0x00d6, 0x00dc, 0x011d, 0x00a3, 0x011c, 0x00d7, 0x0135,
  /* 0xa0 */
  0x00e1, 0x00ed, 0x00f3, 0x00fa, 0x00f1, 0x00d1, 0x011e, 0x011f,
  0x0124, 0x0125, 0xfffd, 0x00bd, 0x0134, 0x015f, 0x00ab, 0x00bb,
  /* 0xb0 */
  0x2591, 0x2592, 0x2593, 0x2502, 0x2524, 0x00c1, 0x00c2, 0x00c0,
  0x015e, 0x2563, 0x2551, 0x2557, 0x255d, 0x017b, 0x017c, 0x2510,
  /* 0xc0 */
  0x2514, 0x2534, 0x252c, 0x251c, 0x2500, 0x253c, 0x015c, 0x015d,
  0x255a, 0x2554, 0x2569, 0x2566, 0x2560, 0x2550, 0x256c, 0x00a4,
  /* 0xd0 */
  0xfffd, 0xfffd, 0x00ca, 0x00cb, 0x00c8, 0x0131, 0x00cd, 0x00ce,
  0x00cf, 0x2518, 0x250c, 0x2588, 0x2584, 0xfffd, 0x00cc, 0x2580,
  /* 0xe0 */
  0x00d3, 0x00df, 0x00d4, 0x00d2, 0x0120, 0x0121, 0x00b5, 0x0126,
  0x0127, 0x00da, 0x00db, 0x00d9, 0x016c, 0x016d, 0xfffd, 0x00b4,
  /* 0xf0 */
  0x00ad, 0xfffd, 0x2113, 0x0149, 0x02d8, 0x00a7, 0x00f7, 0x00b8,
  0x00b0, 0x00a8, 0x02d9, 0xfffd, 0x00b3, 0x00b2, 0x25a0, 0x00a0,
};

static int
cp853_mbtowc (conv_t conv, ucs4_t *pwc, const unsigned char *s, size_t n)
{
  unsigned char c = *s;
  if (c < 0x80) {
    *pwc = (ucs4_t) c;
    return 1;
  }
  else {
    unsigned short wc = cp853_2uni[c-0x80];
    if (wc != 0xfffd) {
      *pwc = (ucs4_t) wc;
      return 1;
    }
  }
  return RET_ILSEQ;
}

static const unsigned char cp853_page00[96] = {
  0xff, 0x00, 0x00, 0x9c, 0xcf, 0x00, 0x00, 0xf5, /* 0xa0-0xa7 */
  0xf9, 0x00, 0x00, 0xae, 0x00, 0xf0, 0x00, 0x00, /* 0xa8-0xaf */
  0xf8, 0x00, 0xfd, 0xfc, 0xef, 0xe6, 0x00, 0x00, /* 0xb0-0xb7 */
  0xf7, 0x00, 0x00, 0xaf, 0x00, 0xab, 0x00, 0x00, /* 0xb8-0xbf */
  0xb7, 0xb5, 0xb6, 0x00, 0x8e, 0x00, 0x00, 0x80, /* 0xc0-0xc7 */
  0xd4, 0x90, 0xd2, 0xd3, 0xde, 0xd6, 0xd7, 0xd8, /* 0xc8-0xcf */
  0x00, 0xa5, 0xe3, 0xe0, 0xe2, 0x00, 0x99, 0x9e, /* 0xd0-0xd7 */
  0x00, 0xeb, 0xe9, 0xea, 0x9a, 0x00, 0x00, 0xe1, /* 0xd8-0xdf */
  0x85, 0xa0, 0x83, 0x00, 0x84, 0x00, 0x00, 0x87, /* 0xe0-0xe7 */
  0x8a, 0x82, 0x88, 0x89, 0x8d, 0xa1, 0x8c, 0x8b, /* 0xe8-0xef */
  0x00, 0xa4, 0x95, 0xa2, 0x93, 0x00, 0x94, 0xf6, /* 0xf0-0xf7 */
  0x00, 0x97, 0xa3, 0x96, 0x81, 0x00, 0x00, 0x00, /* 0xf8-0xff */
};
static const unsigned char cp853_page01[120] = {
  0x8f, 0x86, 0x92, 0x91, 0x00, 0x00, 0x00, 0x00, /* 0x08-0x0f */
  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0x10-0x17 */
  0x00, 0x00, 0x00, 0x00, 0x9d, 0x9b, 0xa6, 0xa7, /* 0x18-0x1f */
  0xe4, 0xe5, 0x00, 0x00, 0xa8, 0xa9, 0xe7, 0xe8, /* 0x20-0x27 */
  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0x28-0x2f */
  0x98, 0xd5, 0x00, 0x00, 0xac, 0x9f, 0x00, 0x00, /* 0x30-0x37 */
  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0x38-0x3f */
  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0x40-0x47 */
  0x00, 0xf3, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0x48-0x4f */
  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0x50-0x57 */
  0x00, 0x00, 0x00, 0x00, 0xc6, 0xc7, 0xb8, 0xad, /* 0x58-0x5f */
  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0x60-0x67 */
  0x00, 0x00, 0x00, 0x00, 0xec, 0xed, 0x00, 0x00, /* 0x68-0x6f */
  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0x70-0x77 */
  0x00, 0x00, 0x00, 0xbd, 0xbe, 0x00, 0x00, 0x00, /* 0x78-0x7f */
};
static const unsigned char cp853_page02[8] = {
  0xf4, 0xfa, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0xd8-0xdf */
};
static const unsigned char cp853_page25[168] = {
  0xc4, 0x00, 0xb3, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0x00-0x07 */
  0x00, 0x00, 0x00, 0x00, 0xda, 0x00, 0x00, 0x00, /* 0x08-0x0f */
  0xbf, 0x00, 0x00, 0x00, 0xc0, 0x00, 0x00, 0x00, /* 0x10-0x17 */
  0xd9, 0x00, 0x00, 0x00, 0xc3, 0x00, 0x00, 0x00, /* 0x18-0x1f */
  0x00, 0x00, 0x00, 0x00, 0xb4, 0x00, 0x00, 0x00, /* 0x20-0x27 */
  0x00, 0x00, 0x00, 0x00, 0xc2, 0x00, 0x00, 0x00, /* 0x28-0x2f */
  0x00, 0x00, 0x00, 0x00, 0xc1, 0x00, 0x00, 0x00, /* 0x30-0x37 */
  0x00, 0x00, 0x00, 0x00, 0xc5, 0x00, 0x00, 0x00, /* 0x38-0x3f */
  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0x40-0x47 */
  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0x48-0x4f */
  0xcd, 0xba, 0x00, 0x00, 0xc9, 0x00, 0x00, 0xbb, /* 0x50-0x57 */
  0x00, 0x00, 0xc8, 0x00, 0x00, 0xbc, 0x00, 0x00, /* 0x58-0x5f */
  0xcc, 0x00, 0x00, 0xb9, 0x00, 0x00, 0xcb, 0x00, /* 0x60-0x67 */
  0x00, 0xca, 0x00, 0x00, 0xce, 0x00, 0x00, 0x00, /* 0x68-0x6f */
  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0x70-0x77 */
  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0x78-0x7f */
  0xdf, 0x00, 0x00, 0x00, 0xdc, 0x00, 0x00, 0x00, /* 0x80-0x87 */
  0xdb, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0x88-0x8f */
  0x00, 0xb0, 0xb1, 0xb2, 0x00, 0x00, 0x00, 0x00, /* 0x90-0x97 */
  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0x98-0x9f */
  0xfe, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, /* 0xa0-0xa7 */
};

static int
cp853_wctomb (conv_t conv, unsigned char *r, ucs4_t wc, size_t n)
{
  unsigned char c = 0;
  if (wc < 0x0080) {
    *r = wc;
    return 1;
  }
  else if (wc >= 0x00a0 && wc < 0x0100)
    c = cp853_page00[wc-0x00a0];
  else if (wc >= 0x0108 && wc < 0x0180)
    c = cp853_page01[wc-0x0108];
  else if (wc >= 0x02d8 && wc < 0x02e0)
    c = cp853_page02[wc-0x02d8];
  else if (wc == 0x2113)
    c = 0xf2;
  else if (wc >= 0x2500 && wc < 0x25a8)
    c = cp853_page25[wc-0x2500];
  if (c != 0) {
    *r = c;
    return 1;
  }
  return RET_ILUNI;
}
