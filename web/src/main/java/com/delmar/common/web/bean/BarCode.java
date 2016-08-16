package com.delmar.common.web.bean;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.PrintStream;
import java.io.Serializable;

public class BarCode extends Canvas
  implements Serializable
{
  public static final int CODE39 = 0;
  public static final int CODE39EXT = 1;
  public static final int INTERLEAVED25 = 2;
  public static final int CODE11 = 3;
  public static final int CODABAR = 4;
  public static final int MSI = 5;
  public static final int UPCA = 6;
  public static final int IND25 = 7;
  public static final int MAT25 = 8;
  public static final int CODE93 = 9;
  public static final int EAN13 = 10;
  public static final int EAN8 = 11;
  public static final int UPCE = 12;
  public static final int CODE128 = 13;
  public static final int CODE93EXT = 14;
  public static final int POSTNET = 15;
  public static final int PLANET = 16;
  public static final int UCC128 = 17;
  public int barType;
  public String code;
  public boolean autoSize;
  public int width;
  public int height;
  public boolean showText;
  public Font textFont;
  public Color fontColor;
  public Color barColor;
  public Color backColor;
  public int rotate;
  public double barHeightCM;
  public double X;
  public double N;
  public double leftMarginCM;
  public double topMarginCM;
  public boolean checkCharacter;
  public boolean checkCharacterInText;
  public char Code128Set;
  public char UPCESytem;
  private String qWe;
  public double wEr;
  public double eRt;
  protected int rTy;
  protected int tYu;
  private int yUi;
  private int uIo;
  private int iOp;
  private int oPa;
  private int pAs;
  private int aSd;
  private int sDf;
  private int dFg;
  public String fGh;
  public boolean gHj;
  public String hJk;
  protected int jKl;
  protected int kLz;
  protected double lZx;
  protected double zXc;
  public int xCv;
  protected int cVb;
  private int vBn;
  public char bNm;
  public char nMq;
  public boolean mQw;
  public boolean mNb;
  public double nBv;
  public double bVc;
  public double vCx;
  public double cXz;
  public double xZl;
  protected int zLk;
  protected int lKj;
  protected String[][] kJh = { { "0", "nnnwwnwnn" }, { 
    "1", "wnnwnnnnw" }, { 
    "2", "nnwwnnnnw" }, { 
    "3", "wnwwnnnnn" }, { 
    "4", "nnnwwnnnw" }, { 
    "5", "wnnwwnnnn" }, { 
    "6", "nnwwwnnnn" }, { 
    "7", "nnnwnnwnw" }, { 
    "8", "wnnwnnwnn" }, { 
    "9", "nnwwnnwnn" }, { 
    "A", "wnnnnwnnw" }, { 
    "B", "nnwnnwnnw" }, { 
    "C", "wnwnnwnnn" }, { 
    "D", "nnnnwwnnw" }, { 
    "E", "wnnnwwnnn" }, { 
    "F", "nnwnwwnnn" }, { 
    "G", "nnnnnwwnw" }, { 
    "H", "wnnnnwwnn" }, { 
    "I", "nnwnnwwnn" }, { 
    "J", "nnnnwwwnn" }, { 
    "K", "wnnnnnnww" }, { 
    "L", "nnwnnnnww" }, { 
    "M", "wnwnnnnwn" }, { 
    "N", "nnnnwnnww" }, { 
    "O", "wnnnwnnwn" }, { 
    "P", "nnwnwnnwn" }, { 
    "Q", "nnnnnnwww" }, { 
    "R", "wnnnnnwwn" }, { 
    "S", "nnwnnnwwn" }, { 
    "T", "nnnnwnwwn" }, { 
    "U", "wwnnnnnnw" }, { 
    "V", "nwwnnnnnw" }, { 
    "W", "wwwnnnnnn" }, { 
    "X", "nwnnwnnnw" }, { 
    "Y", "wwnnwnnnn" }, { 
    "Z", "nwwnwnnnn" }, { 
    "-", "nwnnnnwnw" }, { 
    ".", "wwnnnnwnn" }, { 
    " ", "nwwnnnwnn" }, { 
    "$", "nwnwnwnnn" }, { 
    "/", "nwnwnnnwn" }, { 
    "+", "nwnnnwnwn" }, { 
    "%", "nnnwnwnwn" }, { 
    "*", "nwnnwnwnn" } };
  protected String[][] jHg = { { "0", "nnwwn" }, { 
    "1", "wnnnw" }, { 
    "2", "nwnnw" }, { 
    "3", "wwnnn" }, { 
    "4", "nnwnw" }, { 
    "5", "wnwnn" }, { 
    "6", "nwwnn" }, { 
    "7", "nnnww" }, { 
    "8", "wnnwn" }, { 
    "9", "nwnwn" } };
  protected String[][] hGf = { { "0", "nwnwnwnw" }, { 
    "1", "nwnwnwwn" }, { 
    "2", "nwnwwnnw" }, { 
    "3", "nwnwwnwn" }, { 
    "4", "nwwnnwnw" }, { 
    "5", "nwwnnwwn" }, { 
    "6", "nwwnwnnw" }, { 
    "7", "nwwnwnwn" }, { 
    "8", "wnnwnwnw" }, { 
    "9", "wnnwnwwn" } };
  protected String[][] gFd = { { "0", "nnnnw" }, { 
    "1", "wnnnw" }, { 
    "2", "nwnnw" }, { 
    "3", "wwnnn" }, { 
    "4", "nnwnw" }, { 
    "5", "wnwnn" }, { 
    "6", "nwwnn" }, { 
    "7", "nnnww" }, { 
    "8", "wnnwn" }, { 
    "9", "wnnnn" }, { 
    "-", "nnwnn" } };
  protected String[][] fDs = { { "0", "nnnnnww" }, { 
    "1", "nnnnwwn" }, { 
    "2", "nnnwnnw" }, { 
    "3", "wwnnnnn" }, { 
    "4", "nnwnnwn" }, { 
    "5", "wnnnnwn" }, { 
    "6", "nwnnnnw" }, { 
    "7", "nwnnwnn" }, { 
    "8", "nwwnnnn" }, { 
    "9", "wnnwnnn" }, { 
    "-", "nnnwwnn" }, { 
    "$", "nnwwnnn" }, { 
    ":", "wnnnwnw" }, { 
    "/", "wnwnnnw" }, { 
    ".", "wnwnwnn" }, { 
    "+", "nnwnwnw" }, { 
    "A", "nnwwnwn" }, { 
    "B", "nwnwnnw" }, { 
    "C", "nnnwnww" }, { 
    "D", "nnnwwwn" } };
  protected String[][] dSa = { { "0", "131112" }, { 
    "1", "111213" }, { 
    "2", "111312" }, { 
    "3", "111411" }, { 
    "4", "121113" }, { 
    "5", "121212" }, { 
    "6", "121311" }, { 
    "7", "111114" }, { 
    "8", "131211" }, { 
    "9", "141111" }, { 
    "A", "211113" }, { 
    "B", "211212" }, { 
    "C", "211311" }, { 
    "D", "221112" }, { 
    "E", "221211" }, { 
    "F", "231111" }, { 
    "G", "112113" }, { 
    "H", "112212" }, { 
    "I", "112311" }, { 
    "J", "122112" }, { 
    "K", "132111" }, { 
    "L", "111123" }, { 
    "M", "111222" }, { 
    "N", "111321" }, { 
    "O", "121122" }, { 
    "P", "131121" }, { 
    "Q", "212112" }, { 
    "R", "212211" }, { 
    "S", "211122" }, { 
    "T", "211221" }, { 
    "U", "221121" }, { 
    "V", "222111" }, { 
    "W", "112122" }, { 
    "X", "112221" }, { 
    "Y", "122121" }, { 
    "Z", "123111" }, { 
    "-", "121131" }, { 
    ".", "311112" }, { 
    " ", "311211" }, { 
    "$", "321111" }, { 
    "/", "112131" }, { 
    "+", "113121" }, { 
    "%", "211131" }, { 
    "_1", "121221" }, { 
    "_2", "312111" }, { 
    "_3", "311121" }, { 
    "_4", "122211" } };
  protected String[][] sAp = { { "0", "3211" }, { 
    "1", "2221" }, { 
    "2", "2122" }, { 
    "3", "1411" }, { 
    "4", "1132" }, { 
    "5", "1231" }, { 
    "6", "1114" }, { 
    "7", "1312" }, { 
    "8", "1213" }, { 
    "9", "3112" } };
  protected String[][] aPo = { { "0", "3211" }, { 
    "1", "2221" }, { 
    "2", "2122" }, { 
    "3", "1411" }, { 
    "4", "1132" }, { 
    "5", "1231" }, { 
    "6", "1114" }, { 
    "7", "1312" }, { 
    "8", "1213" }, { 
    "9", "3112" } };
  protected String[][] pOi = { { "0", "3211" }, { 
    "1", "2221" }, { 
    "2", "2122" }, { 
    "3", "1411" }, { 
    "4", "1132" }, { 
    "5", "1231" }, { 
    "6", "1114" }, { 
    "7", "1312" }, { 
    "8", "1213" }, { 
    "9", "3112" } };
  protected String[][] oIu = { { "0", "1123" }, { 
    "1", "1222" }, { 
    "2", "2212" }, { 
    "3", "1141" }, { 
    "4", "2311" }, { 
    "5", "1321" }, { 
    "6", "4111" }, { 
    "7", "2131" }, { 
    "8", "3121" }, { 
    "9", "2113" } };
  protected String[] iUy = { 
    "%U", 
    "$A", 
    "$B", 
    "$C", 
    "$D", 
    "$E", 
    "$F", 
    "$G", 
    "$H", 
    "$I", 
    "$J", 
    "$K", 
    "$L", 
    "$M", 
    "$N", 
    "$O", 
    "$P", 
    "$Q", 
    "$R", 
    "$S", 
    "$T", 
    "$U", 
    "$V", 
    "$W", 
    "$X", 
    "$Y", 
    "$Z", 
    "%A", 
    "%B", 
    "%C", 
    "%D", 
    "%E", 
    " ", 
    "/A", 
    "/B", 
    "/C", 
    "/D", 
    "/E", 
    "/F", 
    "/G", 
    "/H", 
    "/I", 
    "/J", 
    "/K", 
    "/L", 
    "-", 
    ".", 
    "/O", 
    "0", 
    "1", 
    "2", 
    "3", 
    "4", 
    "5", 
    "6", 
    "7", 
    "8", 
    "9", 
    "/Z", 
    "%F", 
    "%G", 
    "%H", 
    "%I", 
    "%J", 
    "%V", 
    "A", 
    "B", 
    "C", 
    "D", 
    "E", 
    "F", 
    "G", 
    "H", 
    "I", 
    "J", 
    "K", 
    "L", 
    "M", 
    "N", 
    "O", 
    "P", 
    "Q", 
    "R", 
    "S", 
    "T", 
    "U", 
    "V", 
    "W", 
    "X", 
    "Y", 
    "Z", 
    "%K", 
    "%L", 
    "%M", 
    "%N", 
    "%O", 
    "%W", 
    "+A", 
    "+B", 
    "+C", 
    "+D", 
    "+E", 
    "+F", 
    "+G", 
    "+H", 
    "+I", 
    "+J", 
    "+K", 
    "+L", 
    "+M", 
    "+N", 
    "+O", 
    "+P", 
    "+Q", 
    "+R", 
    "+S", 
    "+T", 
    "+U", 
    "+V", 
    "+W", 
    "+X", 
    "+Y", 
    "+Z", 
    "%P", 
    "%Q", 
    "%R", 
    "%S", 
    "%T" };
  protected String[] uYt = { 
    "_2U", 
    "_1A", 
    "_1B", 
    "_1C", 
    "_1D", 
    "_1E", 
    "_1F", 
    "_1G", 
    "_1H", 
    "_1I", 
    "_1J", 
    "_1K", 
    "_1L", 
    "_1M", 
    "_1N", 
    "_1O", 
    "_1P", 
    "_1Q", 
    "_1R", 
    "_1S", 
    "_1T", 
    "_1U", 
    "_1V", 
    "_1W", 
    "_1X", 
    "_1Y", 
    "_1Z", 
    "_2A", 
    "_2B", 
    "_2C", 
    "_2D", 
    "_2E", 
    " ", 
    "_3A", 
    "_3B", 
    "_3C", 
    "_3D", 
    "_3E", 
    "_3F", 
    "_3G", 
    "_3H", 
    "_3I", 
    "_3J", 
    "_3K", 
    "_3L", 
    "-", 
    ".", 
    "_3O", 
    "0", 
    "1", 
    "2", 
    "3", 
    "4", 
    "5", 
    "6", 
    "7", 
    "8", 
    "9", 
    "_3Z", 
    "_2F", 
    "_2G", 
    "_2H", 
    "_2I", 
    "_2J", 
    "_2V", 
    "A", 
    "B", 
    "C", 
    "D", 
    "E", 
    "F", 
    "G", 
    "H", 
    "I", 
    "J", 
    "K", 
    "L", 
    "M", 
    "N", 
    "O", 
    "P", 
    "Q", 
    "R", 
    "S", 
    "T", 
    "U", 
    "V", 
    "W", 
    "X", 
    "Y", 
    "Z", 
    "_2K", 
    "_2L", 
    "_2M", 
    "_2N", 
    "_2O", 
    "_2W", 
    "_4A", 
    "_4B", 
    "_4C", 
    "_4D", 
    "_4E", 
    "_4F", 
    "_4G", 
    "_4H", 
    "_4I", 
    "_4J", 
    "_4K", 
    "_4L", 
    "_4M", 
    "_4N", 
    "_4O", 
    "_4P", 
    "_4Q", 
    "_4R", 
    "_4S", 
    "_4T", 
    "_4U", 
    "_4V", 
    "_4W", 
    "_4X", 
    "_4Y", 
    "_4Z", 
    "_2P", 
    "_2Q", 
    "_2R", 
    "_2S", 
    "_2T" };
  protected byte[] yTr = { 112, 111, 119, 101, 114, 32, 98, 121, 32, 109, 97, 115, 107 };
  protected String[] tRe = { 
    "EEEOOO", 
    "EEOEOO", 
    "EEOOEO", 
    "EEOOOE", 
    "EOEEOO", 
    "EOOEEO", 
    "EOOOEE", 
    "EOEOEO", 
    "EOEOOE", 
    "EOOEOE" };
  protected String[] rEw = { 
    "OOOEEE", 
    "OOEOEE", 
    "OOEEOE", 
    "OOEEEO", 
    "OEOOEE", 
    "OEEOOE", 
    "OEEEOO", 
    "OEOEOE", 
    "OEOEEO", 
    "OEEOEO" };
  protected String[][] eWq = { { "0", "3211" }, { 
    "1", "2221" }, { 
    "2", "2122" }, { 
    "3", "1411" }, { 
    "4", "1132" }, { 
    "5", "1231" }, { 
    "6", "1114" }, { 
    "7", "1312" }, { 
    "8", "1213" }, { 
    "9", "3112" } };
  protected String[][] wQm = { { "0", "1123" }, { 
    "1", "1222" }, { 
    "2", "2212" }, { 
    "3", "1141" }, { 
    "4", "2311" }, { 
    "5", "1321" }, { 
    "6", "4111" }, { 
    "7", "2131" }, { 
    "8", "3121" }, { 
    "9", "2113" } };
  protected String[][] qMn = { { "0", "3211" }, { 
    "1", "2221" }, { 
    "2", "2122" }, { 
    "3", "1411" }, { 
    "4", "1132" }, { 
    "5", "1231" }, { 
    "6", "1114" }, { 
    "7", "1312" }, { 
    "8", "1213" }, { 
    "9", "3112" } };
  protected String[] q1w = { 
    "AAAAA", 
    "ABABB", 
    "ABBAB", 
    "ABBBA", 
    "BAABB", 
    "BBAAB", 
    "BBBAA", 
    "BABAB", 
    "BABBA", 
    "BBABA" };
  protected String[] w2e = { 
    "EEOOO", 
    "EOEOO", 
    "EOOEO", 
    "EOOOE", 
    "OEEOO", 
    "OOEEO", 
    "OOOEE", 
    "OEOEO", 
    "OEOOE", 
    "OOEOE" };
  protected String[] e3r = { 
    "212222", 
    "222122", 
    "222221", 
    "121223", 
    "121322", 
    "131222", 
    "122213", 
    "122312", 
    "132212", 
    "221213", 
    "221312", 
    "231212", 
    "112232", 
    "122132", 
    "122231", 
    "113222", 
    "123122", 
    "123221", 
    "223211", 
    "221132", 
    "221231", 
    "213212", 
    "223112", 
    "312131", 
    "311222", 
    "321122", 
    "321221", 
    "312212", 
    "322112", 
    "322211", 
    "212123", 
    "212321", 
    "232121", 
    "111323", 
    "131123", 
    "131321", 
    "112313", 
    "132113", 
    "132311", 
    "211313", 
    "231113", 
    "231311", 
    "112133", 
    "112331", 
    "132131", 
    "113123", 
    "113321", 
    "133121", 
    "313121", 
    "211331", 
    "231131", 
    "213113", 
    "213311", 
    "213131", 
    "311123", 
    "311321", 
    "331121", 
    "312113", 
    "312311", 
    "332111", 
    "314111", 
    "221411", 
    "431111", 
    "111224", 
    "111422", 
    "121124", 
    "121421", 
    "141122", 
    "141221", 
    "112214", 
    "112412", 
    "122114", 
    "122411", 
    "142112", 
    "142211", 
    "241211", 
    "221114", 
    "413111", 
    "241112", 
    "134111", 
    "111242", 
    "121142", 
    "121241", 
    "114212", 
    "124112", 
    "124211", 
    "411212", 
    "421112", 
    "421211", 
    "212141", 
    "214121", 
    "412121", 
    "111143", 
    "111341", 
    "131141", 
    "114113", 
    "114311", 
    "411113", 
    "411311", 
    "113141", 
    "114131", 
    "311141", 
    "411131" };
  protected String[] r4t = { 
    " ", 
    "!", 
    "\"", 
    "#", 
    "$", 
    "%", 
    "&", 
    "'", 
    "(", 
    ")", 
    "*", 
    "+", 
    ",", 
    "-", 
    ".", 
    "/", 
    "0", 
    "1", 
    "2", 
    "3", 
    "4", 
    "5", 
    "6", 
    "7", 
    "8", 
    "9", 
    ":", 
    ";", 
    "<", 
    "=", 
    ">", 
    "?", 
    "@", 
    "A", 
    "B", 
    "C", 
    "D", 
    "E", 
    "F", 
    "G", 
    "H", 
    "I", 
    "J", 
    "K", 
    "L", 
    "M", 
    "N", 
    "O", 
    "P", 
    "Q", 
    "R", 
    "S", 
    "T", 
    "U", 
    "V", 
    "W", 
    "X", 
    "Y", 
    "Z", 
    "[", 
    "\\", 
    "]", 
    "^", 
    "_", 
    "`", 
    "a", 
    "b", 
    "c", 
    "d", 
    "e", 
    "f", 
    "g", 
    "h", 
    "i", 
    "j", 
    "k", 
    "l", 
    "m", 
    "n", 
    "o", 
    "p", 
    "q", 
    "r", 
    "s", 
    "t", 
    "u", 
    "v", 
    "w", 
    "x", 
    "y", 
    "z", 
    "{", 
    "|", 
    "}", 
    "~", 
    "Ã", 
    "Ä", 
    "Å", 
    "Æ", 
    "Ç", 
    "È", 
    "É", 
    "Ê" };
  protected String[] t5y = { 
    "00", 
    "01", 
    "02", 
    "03", 
    "04", 
    "05", 
    "06", 
    "07", 
    "08", 
    "09", 
    "10", 
    "11", 
    "12", 
    "13", 
    "14", 
    "15", 
    "16", 
    "17", 
    "18", 
    "19", 
    "20", 
    "21", 
    "22", 
    "23", 
    "24", 
    "25", 
    "26", 
    "27", 
    "28", 
    "29", 
    "30", 
    "31", 
    "32", 
    "33", 
    "34", 
    "35", 
    "36", 
    "37", 
    "38", 
    "39", 
    "40", 
    "41", 
    "42", 
    "43", 
    "44", 
    "45", 
    "46", 
    "47", 
    "48", 
    "49", 
    "50", 
    "51", 
    "52", 
    "53", 
    "54", 
    "55", 
    "56", 
    "57", 
    "58", 
    "59", 
    "60", 
    "61", 
    "62", 
    "63", 
    "64", 
    "65", 
    "66", 
    "67", 
    "68", 
    "69", 
    "70", 
    "71", 
    "72", 
    "73", 
    "74", 
    "75", 
    "76", 
    "77", 
    "78", 
    "79", 
    "80", 
    "81", 
    "82", 
    "83", 
    "84", 
    "85", 
    "86", 
    "87", 
    "88", 
    "89", 
    "90", 
    "91", 
    "92", 
    "93", 
    "94", 
    "95", 
    "96", 
    "97", 
    "98", 
    "99", 
    "ÈÈ", 
    "ÉÉ", 
    "ÊÊ" };
  protected String[][] y6u = { { "0", "11000" }, { 
    "1", "00011" }, { 
    "2", "00101" }, { 
    "3", "00110" }, { 
    "4", "01001" }, { 
    "5", "01010" }, { 
    "6", "01100" }, { 
    "7", "10001" }, { 
    "8", "10010" }, { 
    "9", "10100" } };
  protected String[][] u7i = { { "0", "00111" }, { 
    "1", "11100" }, { 
    "2", "11010" }, { 
    "3", "11001" }, { 
    "4", "10110" }, { 
    "5", "10101" }, { 
    "6", "10011" }, { 
    "7", "01110" }, { 
    "8", "01101" }, { 
    "9", "01011" } };

  public BarCode()
  {
    this.barType = 13;
    this.code = "BATISTUTA";
    this.autoSize = true;
    this.width = 1;
    this.height = 1;
    this.showText = true;
    this.textFont = new Font("Arial", 0, 11);
    this.fontColor = Color.black;
    this.barColor = Color.black;
    this.backColor = Color.white;
    this.rotate = 0;
    this.barHeightCM = 1.0D;
    this.X = 0.03D;
    this.N = 2.0D;
    this.leftMarginCM = 0.3D;
    this.topMarginCM = 0.2D;
    this.checkCharacter = true;
    this.checkCharacterInText = true;
    this.Code128Set = '0';
    this.UPCESytem = '0';
    this.qWe = "";
    this.wEr = 0.3D;
    this.eRt = 0.125D;
    this.rTy = 0;
    this.tYu = 0;
    this.yUi = 0;
    this.uIo = 0;
    this.iOp = 0;
    this.oPa = 0;
    this.pAs = 0;
    this.fGh = "";
    this.gHj = true;
    this.hJk = "";
    this.jKl = 0;
    this.kLz = 0;
    this.lZx = 0.0D;
    this.zXc = 0.0D;
    this.xCv = 38;
    this.cVb = 0;
    this.vBn = 0;
    this.bNm = 'A';
    this.nMq = 'B';
    this.mQw = false;
    this.mNb = false;
    this.nBv = 1.0D;
    this.bVc = 0.45D;
    this.vCx = 0.0D;
    this.cXz = 0.5D;
    this.xZl = 0.8D;
    this.zLk = 0;
    this.lKj = 0;
  }

  public void setSymbologyID(int i) {
    this.barType = i;
    invalidate();
  }

  public int getSymbologyID() {
    return this.barType;
  }

  public void setDataToEncode(String s) {
    this.code = s;
    invalidate();
  }

  public String getDataToEncode() {
    return this.code;
  }

  public void setCheckCharacter(boolean flag) {
    this.checkCharacter = flag;
    invalidate();
  }

  public boolean getCheckCharacter() {
    return this.checkCharacter;
  }

  public void setCheckCharacterInText(boolean flag) {
    this.checkCharacterInText = flag;
    invalidate();
  }

  public boolean getCheckCharacterInText() {
    return this.checkCharacterInText;
  }

  public void setWEr(double d) {
    this.wEr = d;
    invalidate();
  }

  public double getWEr() {
    return this.wEr;
  }

  public void setERt(double d) {
    this.eRt = d;
    invalidate();
  }

  public double getERt() {
    return this.eRt;
  }

  public void setLeftMarginCM(double d) {
    this.leftMarginCM = d;
    invalidate();
  }

  public double getLeftMarginCM() {
    return this.leftMarginCM;
  }

  public void setTopMarginCM(double d) {
    this.topMarginCM = d;
    invalidate();
  }

  public double getTopMarginCM() {
    return this.topMarginCM;
  }

  public void setFGh(String s) {
    this.fGh = s;
    invalidate();
  }

  public String getFGh() {
    return this.fGh;
  }

  public void setBackground(Color color) {
    this.backColor = color;
    invalidate();
  }

  public Color getBackground() {
    return this.backColor;
  }

  public void setXCv(int i) {
    this.xCv = i;
    invalidate();
  }

  public int getXCv() {
    return this.xCv;
  }

  public void setBarHeightCM(double d) {
    this.barHeightCM = d;
    invalidate();
  }

  public double getBarHeightCM() {
    return this.barHeightCM;
  }

  public void setAutoSize(boolean flag) {
    this.autoSize = flag;
    invalidate();
  }

  public boolean getAutoSize() {
    return this.autoSize;
  }

  public Dimension getPreferredSize() {
    return new Dimension(this.width, this.height);
  }

  public Dimension getMinimumSize() {
    Dimension dimension = new Dimension(10, 10);
    return dimension;
  }

  public void setShowText(boolean flag) {
    this.showText = flag;
    invalidate();
  }

  public boolean getShowText() {
    return this.showText;
  }

  public void setFont(Font font) {
    this.textFont = font;
    invalidate();
  }

  public Font getFont() {
    return this.textFont;
  }

  public void setTextFontColor(Color color) {
    this.fontColor = color;
    invalidate();
  }

  public Color getTextFontColor() {
    return this.fontColor;
  }

  public void setForeground(Color color) {
    this.barColor = color;
    invalidate();
  }

  public Color getForeground() {
    return this.barColor;
  }

  public void setUPCESytem(String s) {
    if (s.equals("0"))
      this.UPCESytem = '0';
    if (s.equals("1"))
      this.UPCESytem = '1';
    invalidate();
  }

  public String getUPCESytem() {
    String s = "";
    if (this.UPCESytem == '0')
      s = "0";
    if (this.UPCESytem == '1')
      s = "1";
    return s;
  }

  public void setBNm(String s) {
    if (s.equals("A"))
      this.bNm = 'A';
    if (s.equals("B"))
      this.bNm = 'B';
    if (s.equals("C"))
      this.bNm = 'C';
    if (s.equals("D"))
      this.bNm = 'D';
    invalidate();
  }

  public String getBNm() {
    String s = "";
    if (this.bNm == 'A')
      s = "A";
    if (this.bNm == 'B')
      s = "B";
    if (this.bNm == 'C')
      s = "C";
    if (this.bNm == 'D')
      s = "D";
    return s;
  }

  public void setNMq(String s) {
    if (s.equals("A"))
      this.nMq = 'A';
    if (s.equals("B"))
      this.nMq = 'B';
    if (s.equals("C"))
      this.nMq = 'C';
    if (s.equals("D"))
      this.nMq = 'D';
    invalidate();
  }

  public String getNMq() {
    String s = "";
    if (this.nMq == 'A')
      s = "A";
    if (this.nMq == 'B')
      s = "B";
    if (this.nMq == 'C')
      s = "C";
    if (this.nMq == 'D')
      s = "D";
    return s;
  }

  public void setMQw(boolean flag) {
    this.mQw = flag;
    invalidate();
  }

  public boolean getMQw() {
    return this.mQw;
  }

  public void setMNb(boolean flag) {
    this.mNb = flag;
    invalidate();
  }

  public boolean getMNb() {
    return this.mNb;
  }

  public void setCode128Set(String s) {
    if (s.toUpperCase().equals("A"))
      this.Code128Set = 'A';
    if (s.toUpperCase().equals("B"))
      this.Code128Set = 'B';
    if (s.toUpperCase().equals("C"))
      this.Code128Set = 'C';
    if (s.toUpperCase().equals("AUTO"))
      this.Code128Set = '0';
    if (s.toUpperCase().equals("0"))
      this.Code128Set = '0';
    invalidate();
  }

  public String getCode128Set() {
    String s = "";
    if (this.Code128Set == 'A')
      s = "A";
    if (this.Code128Set == 'B')
      s = "B";
    if (this.Code128Set == 'C')
      s = "C";
    if (this.Code128Set == '0')
      s = "0";
    return s;
  }

  public void setXDimensionCM(double d) {
    this.X = d;
    invalidate();
  }

  public double getXDimensionCM() {
    return this.X;
  }

  public void setNarrowToWideRatio(double d) {
    this.N = d;
    invalidate();
  }

  public double getNarrowToWideRatio() {
    return this.N;
  }

  public void setRotationAngle(int i) {
    this.rotate = i;
    invalidate();
  }

  public int getRotationAngle() {
    return this.rotate;
  }

  protected void qwer(Graphics g, int i, boolean flag, int j) {
    if (flag) {
      g.setColor(this.barColor);
      g.fillRect(this.zLk, this.tYu + j, i, this.cVb + this.vBn - j);
    }
    this.zLk += i;
  }

  protected void wert(Graphics g, String s) {
    int i = (int)(this.wEr * this.xCv);
    int j = (int)(this.eRt * this.xCv);
    g.setColor(this.barColor);
    for (int k = 0; k < s.length(); ++k) {
      char c = s.charAt(k);
      if (c == '0')
        g.fillRect(this.zLk, this.tYu, this.jKl, j + this.vBn);
      if (c == '1')
        g.fillRect(this.zLk, this.tYu + j - i, this.jKl, i + this.vBn);
      this.zLk += this.jKl;
      this.zLk += this.kLz;
    }
  }

  protected void erty(Graphics g)
  {
    int i = 0;
    String s = this.code;
    wert(g, "1");
    for (int j = this.code.length() - 1; j >= 0; --j) {
      String s1 = "" + this.code.charAt(j);
      i += rtyu(this.y6u, s1);
    }

    int k = (int)bnmq(i, 10.0D);
    if (k != 0)
      k = 10 - k;
    if (this.checkCharacter)
      s = s + new Integer(k).toString();
    for (int l = 0; l < s.length(); ++l) {
      String s2 = "" + s.charAt(l);
      int i1 = rtyu(this.y6u, s2);
      wert(g, this.y6u[i1][1]);
    }

    wert(g, "1");
  }

  protected int rtyu(String[][] as, String s) {
    for (int i = 0; i < as.length; ++i)
      if (s.compareTo(as[i][0]) == 0)
        return i;

    return -1;
  }

  protected void tyui(Graphics g) {
    int i = 0;
    String s = this.code;
    wert(g, "1");
    for (int j = this.code.length() - 1; j >= 0; --j) {
      String s1 = "" + this.code.charAt(j);
      i += rtyu(this.u7i, s1);
    }

    int k = (int)bnmq(i, 10.0D);
    if (k != 0)
      k = 10 - k;
    if (this.checkCharacter)
      s = s + new Integer(k).toString();
    for (int l = 0; l < s.length(); ++l) {
      String s2 = "" + s.charAt(l);
      int i1 = rtyu(this.u7i, s2);
      wert(g, this.u7i[i1][1]);
    }

    wert(g, "1");
  }

  protected void yuio(Graphics g) {
    String s = this.code;
    nbvc(g, "bwbw", "nnnn");
    if ((bnmq(this.code.length(), 2.0D) == 0.0D) && (this.checkCharacter))
      s = "0" + this.code;
    if ((bnmq(this.code.length(), 2.0D) == 1.0D) && (!(this.checkCharacter)))
      s = "0" + this.code;
    int i = 0;
    int j = 0;
    boolean flag = true;
    for (int k = s.length() - 1; k >= 0; --k) {
      String s1 = "" + s.charAt(k);
      if (flag)
        i += rtyu(this.jHg, s1);
      else
        j += rtyu(this.jHg, s1);
      flag = !(flag);
    }

    int l = i * 3 + j;
    l = (int)bnmq(l, 10.0D);
    if (l != 0)
      l = 10 - l;
    if (this.checkCharacter)
      s = s + new Integer(l).toString();
    for (int i1 = 0; i1 < s.length(); i1 += 2) {
      String s2 = "" + s.charAt(i1);
      String s3 = "" + s.charAt(i1 + 1);
      int j1 = rtyu(this.jHg, s2);
      int k1 = rtyu(this.jHg, s3);
      for (int l1 = 0; l1 < 5; ++l1) {
        nbvc(g, "b", "" + this.jHg[j1][1].charAt(l1));
        nbvc(g, "w", "" + this.jHg[k1][1].charAt(l1));
      }

    }

    nbvc(g, "bwb", "wnn");
    if (this.checkCharacterInText)
      this.hJk = s;
    else
      this.hJk = this.code;
  }

  protected void uiop(Graphics g) {
    String s = this.code;
    nbvc(g, "bwbwbw", "wwwwnw");
    int i = 0;
    int j = 0;
    boolean flag = true;
    for (int k = s.length() - 1; k >= 0; --k) {
      String s1 = "" + s.charAt(k);
      if (flag)
        i += rtyu(this.jHg, s1);
      else
        j += rtyu(this.jHg, s1);
      flag = !(flag);
    }

    int l = i * 3 + j;
    l = (int)bnmq(l, 10.0D);
    if (l != 0)
      l = 10 - l;
    if (this.checkCharacter)
      s = s + new Integer(l).toString();
    for (int i1 = 0; i1 < s.length(); ++i1) {
      String s2 = "" + s.charAt(i1);
      int j1 = rtyu(this.jHg, s2);
      if (j1 >= 0)
        for (int k1 = 0; k1 < this.jHg[j1][1].length(); ++k1) {
          nbvc(g, "b", "" + this.jHg[j1][1].charAt(k1));
          nbvc(g, "w", "w");
        }


    }

    nbvc(g, "bwbwb", "wwnww");
  }

  protected String iopa(String s) {
    boolean flag = true;
    int i = 0;
    int j = 0;
    int k = 0;
    for (int l = s.length() - 1; l >= 0; --l) {
      if (flag)
        i += new Integer("" + s.charAt(l)).intValue();
      else
        j += new Integer("" + s.charAt(l)).intValue();
      flag = !(flag);
    }

    j = i * 3 + j;
    k = (int)bnmq(j, 10.0D);
    if (k != 0)
      k = 10 - k;
    return "" + k;
  }

  protected void opas(Graphics g) {
    if (this.code.length() < 11)
      return;
    if (this.code.length() == 13) {
      this.fGh = this.code.substring(11, 13);
      this.mQw = true;
    }
    if (this.code.length() == 14) {
      this.fGh = this.code.substring(12, 14);
      this.mQw = true;
    }
    if (this.code.length() == 16) {
      this.fGh = this.code.substring(11, 16);
      this.mNb = true;
    }
    if (this.code.length() == 17) {
      this.fGh = this.code.substring(12, 17);
      this.mNb = true;
    }
    this.code = this.code.substring(0, 11);
    this.code += iopa(this.code);
    vcxz(g, "bwb", "nnn", 0);
    this.yUi = this.zLk;
    for (int i = 0; i < this.code.length(); ++i) {
      String s = "" + this.code.charAt(i);
      if (i <= 5) {
        int j = rtyu(this.sAp, s);
        nbvc(g, "wbwb", this.sAp[j][1]);
      } else {
        int k = rtyu(this.aPo, s);
        nbvc(g, "bwbw", this.aPo[k][1]);
      }
      if (i == 5) {
        this.uIo = this.zLk;
        vcxz(g, "wbwbw", "nnnnn", 0);
        this.iOp = this.zLk;
      }
    }

    this.oPa = this.zLk;
    vcxz(g, "bwb", "nnn", 0);
    this.pAs = this.zLk;
    if (this.mQw)
      hjkl(g, this.fGh);
    else if (this.mNb)
      jklz(g, this.fGh);
  }

  protected void pasd(Graphics g) {
    int i = 0;
    if (this.code.length() < 12)
      return;
    if (this.code.length() == 14) {
      this.fGh = this.code.substring(12, 14);
      this.mQw = true;
    }
    if (this.code.length() == 15) {
      this.fGh = this.code.substring(13, 15);
      this.mQw = true;
    }
    if (this.code.length() == 17) {
      this.fGh = this.code.substring(12, 17);
      this.mNb = true;
    }
    if (this.code.length() == 18) {
      this.fGh = this.code.substring(13, 18);
      this.mNb = true;
    }
    this.code = this.code.substring(0, 12);
    this.code += iopa(this.code);
    vcxz(g, "bwb", "nnn", 0);
    this.yUi = this.zLk;
    String s = this.q1w[new Integer("" + this.code.charAt(0)).intValue()];
    i = rtyu(this.eWq, "" + this.code.charAt(1));
    nbvc(g, "wbwb", this.eWq[i][1]);
    for (int j = 2; j < 12; ++j) {
      String s1 = "" + this.code.charAt(j);
      i = -1;
      if (j <= 6) {
        String[][] as = this.eWq;
        if (s.charAt(j - 2) == 'B')
          as = this.wQm;
        i = rtyu(as, s1);
        nbvc(g, "wbwb", as[i][1]);
      } else {
        i = rtyu(this.qMn, s1);
        nbvc(g, "bwbw", this.qMn[i][1]);
      }
      if (j == 6) {
        this.uIo = this.zLk;
        vcxz(g, "wbwbw", "nnnnn", 0);
        this.iOp = this.zLk;
      }
    }

    i = rtyu(this.qMn, "" + this.code.charAt(12));
    nbvc(g, "bwbw", this.qMn[i][1]);
    this.oPa = this.zLk;
    vcxz(g, "bwb", "nnn", 0);
    this.pAs = this.zLk;
    if (this.mQw)
      hjkl(g, this.fGh);
    else if (this.mNb)
      jklz(g, this.fGh);
  }

  private int asdf(String[] as, String s) {
    for (int i = 0; i < as.length; ++i)
      if (as[i].compareTo(s) == 0)
        return i;

    return -1;
  }

  protected void sdfg(Graphics g) {
    String s = this.code;
    this.hJk = this.code;
    int i = this.code.length();
    String[] as = this.r4t;
    int j = 103;
    if (this.Code128Set != '0') {
      s = "";
      this.hJk = "";
      for (int k = 1; k <= i; ++k) {
        int i1 = this.code.charAt(k - 1);
        if ((i1 < 32) && (i1 >= 0)) {
          if (this.Code128Set == 'A')
            s = s + (char)(i1 + 96);
          if (this.Code128Set == 'B')
            if (this.code.charAt(k) < ' ') {
              s = 
                s + 
                201 + 
                (char)(i1 + 96) + 
                (char)(this.code.charAt(k) + '`') + 
                200;
              ++k;
            } else {
              s = s + 201 + (char)(i1 + 96) + 200;
            }
          if (this.Code128Set == 'C')
            if (this.code.charAt(k) < ' ') {
              s = 
                s + 
                201 + 
                201 + 
                (i1 + 64) + 
                (this.code.charAt(k) + '@') + 
                "99";
              ++k;
            } else {
              s = s + 201 + 201 + (i1 + 64) + "99";
            }
          if ((i1 == 13) || (i1 == 9))
            this.hJk += "  ";
        } else {
          this.hJk += (char)i1;
          s = s + (char)i1;
        }
      }
    }

    if (this.Code128Set == '0') {
      as = this.r4t;
      this.hJk = "";
      s = "";
      char c = 204;
      byte byte0 = 66;
      char c1 = this.code.charAt(0);
      if (c1 < ' ')
        c = 203;
      if ((c1 > '\31') && (c1 < ''))
        c = 204;
      if ((i > 3) && 
        (c1 > '/') && 
        (c1 < ':') && 
        (this.code.charAt(1) > '/') && 
        (this.code.charAt(1) < ':') && 
        (this.code.charAt(2) > '/') && 
        (this.code.charAt(2) < ':') && 
        (this.code.charAt(3) > '/') && 
        (this.code.charAt(3) < ':'))
        c = 205;
      if ((c1 == 202) || (c1 > 211))
        c = 205;
      if (c == 203) {
        byte0 = 65;
        j = 103;
        nbvc(g, "bwbwbw", "211412");
      }
      if (c == 204) {
        byte0 = 66;
        j = 104;
        nbvc(g, "bwbwbw", "211214");
      }
      if (c == 205) {
        byte0 = 67;
        j = 105;
        nbvc(g, "bwbwbw", "211232");
      }
      for (int l1 = 1; l1 <= i; ++l1) {
        int j2 = this.code.charAt(l1 - 1);
        if ((l1 < i - 1) && (((j2 == 202) || (j2 > 211)))) {
          s = s + 202;
        }
        else if (((l1 <= i - 3) && 
          (j2 > 47) && 
          (j2 < 58) && 
          (this.code.charAt(l1) > '/') && 
          (this.code.charAt(l1) < ':') && 
          (this.code.charAt(l1 + 1) > '/') && 
          (this.code.charAt(l1 + 1) < ':') && 
          (this.code.charAt(l1 + 2) > '/') && 
          (this.code.charAt(l1 + 2) < ':')) || (
          (l1 <= i - 1) && 
          (j2 > 47) && 
          (j2 < 58) && 
          (this.code.charAt(l1) > '/') && 
          (this.code.charAt(l1) < ':') && 
          (byte0 == 67))) {
          if (byte0 != 67) {
            s = s + 199;
            byte0 = 67;
          }
          j2 = 
            (this.code.charAt(l1 - 1) - '0') * 10 + 
            this.code.charAt(l1) - '0';
          if ((j2 < 95) && (j2 >= 0))
            s = s + (char)(j2 + 32);
          else if (j2 > 94)
            s = s + (char)(j2 + 100);
          ++l1;
        } else if ((l1 <= i) && (((j2 < 32) || ((byte0 == 65) && (j2 < 96))))) {
          if (byte0 != 65) {
            s = s + 201;
            byte0 = 65;
          }
          if (j2 < 32)
            s = s + (char)(j2 + 96);
          else if (j2 > 31)
            s = s + (char)j2;
        } else if ((l1 <= i) && (j2 > 31) && (j2 < 127)) {
          if (byte0 != 66) {
            s = s + 200;
            byte0 = 66;
          }
          s = s + (char)j2;
        }
      }

      for (int k2 = 1; k2 <= i; ++k2) {
        char c2 = this.code.charAt(k2 - 1);
        boolean flag = false;
        if ((k2 < i - 1) && (((c2 == 202) || (c2 > 211)))) {
          int i3 = 
            (this.code.charAt(k2) - '0') * 10 + 
            this.code.charAt(k2 + 1) - '0';
          if (this.code.charAt(k2 - 1) == 212) {
            this.hJk = 
              this.hJk + 
              " (" + 
              this.code.charAt(k2) + 
              this.code.charAt(k2 + 1) + 
              ") ";
            k2 += 2;
            flag = true;
          } else if ((k2 < i - 2) && (this.code.charAt(k2 - 1) == 213)) {
            this.hJk = 
              this.hJk + 
              " (" + 
              this.code.charAt(k2) + 
              this.code.charAt(k2 + 1) + 
              this.code.charAt(k2 + 2) + 
              ") ";
            k2 += 3;
            flag = true;
          } else if ((k2 < i - 3) && (this.code.charAt(k2 - 1) == 214)) {
            this.hJk = 
              this.hJk + 
              " (" + 
              this.code.charAt(k2) + 
              this.code.charAt(k2 + 1) + 
              this.code.charAt(k2 + 2) + 
              this.code.charAt(k2 + 3) + 
              ") ";
            k2 += 4;
            flag = true;
          } else if ((k2 < i - 4) && (this.code.charAt(k2 - 1) == 215)) {
            this.hJk = 
              this.hJk + 
              " (" + 
              this.code.charAt(k2) + 
              this.code.charAt(k2 + 1) + 
              this.code.charAt(k2 + 2) + 
              this.code.charAt(k2 + 3) + 
              this.code.charAt(k2 + 4) + 
              ") ";
            k2 += 5;
            flag = true;
          } else if (((i3 <= 30) && (i3 >= 0)) || ((i3 <= 99) && (i3 >= 90))) {
            this.hJk = 
              this.hJk + 
              " (" + 
              this.code.charAt(k2) + 
              this.code.charAt(k2 + 1) + 
              ") ";
            k2 += 2;
            flag = true;
          }
          else if ((k2 < i - 2) && ((
            ((i3 <= 49) && (i3 >= 40)) || ((i3 <= 25) && (i3 >= 23))))) {
            this.hJk = 
              this.hJk + 
              " (" + 
              this.code.charAt(k2) + 
              this.code.charAt(k2 + 1) + 
              this.code.charAt(k2 + 2) + 
              ") ";
            k2 += 3;
            flag = true;
          }
          else if ((k2 < i - 3) && ((
            ((i3 <= 81) && (i3 >= 80)) || ((i3 <= 34) && (i3 >= 31))))) {
            this.hJk = 
              this.hJk + 
              " (" + 
              this.code.charAt(k2) + 
              this.code.charAt(k2 + 1) + 
              this.code.charAt(k2 + 2) + 
              this.code.charAt(k2 + 3) + 
              ") ";
            k2 += 4;
            flag = true;
          } else if ((k2 < i - 3) && (!(flag))) {
            this.hJk = 
              this.hJk + 
              " (" + 
              this.code.charAt(k2) + 
              this.code.charAt(k2 + 1) + 
              this.code.charAt(k2 + 2) + 
              this.code.charAt(k2 + 3) + 
              ") ";
            k2 += 4;
          }
        } else if (this.code.charAt(k2 - 1) < ' ') {
          this.hJk += " ";
        }
        else if ((this.code.charAt(k2 - 1) > '\31') && 
          (this.code.charAt(k2 - 1) < 128)) {
          this.hJk += this.code.charAt(k2 - 1);
        }
      }
    }
    if (this.Code128Set == 'B') {
      as = this.r4t;
      j = 104;
    }
    if (this.Code128Set == 'C') {
      as = this.t5y;
      j = 105;
      if (s.length() % 2 == 1) {
        s = "0" + s;
        this.hJk = s;
      }
    }
    if (this.Code128Set == 'B')
      nbvc(g, "bwbwbw", "211214");
    if (this.Code128Set == 'C')
      nbvc(g, "bwbwbw", "211232");
    if (this.Code128Set == 'A')
      nbvc(g, "bwbwbw", "211412");
    int l = 1;
    for (int j1 = 0; j1 < s.length(); ++j1) {
      String s1 = "" + s.charAt(j1);
      if (this.Code128Set == 'C') {
        String s2 = "" + s1;
        if (++j1 < s.length())
          s2 = s2 + s.charAt(j1);
        int l2 = asdf(this.t5y, s2);
        if (l2 >= 0) {
          nbvc(g, "bwbwbw", this.e3r[l2]);
          j += l2 * l;
        }
      } else {
        int i2 = asdf(as, s1);
        if (i2 >= 0) {
          nbvc(g, "bwbwbw", this.e3r[i2]);
          j += i2 * l;
        }
      }
      ++l;
    }

    if (this.checkCharacter) {
      int k1 = (int)bnmq(j, 103.0D);
      nbvc(g, "bwbwbw", this.e3r[k1]);
    }
    nbvc(g, "bwbwbwb", "2331112");
  }

  protected void dfgh(Graphics g) {
    String s = this.code;
    this.hJk = this.code;
    String[] as = this.t5y;
    this.Code128Set = 'C';
    int i = 105;
    if (s.length() % 2 == 1) {
      s = "0" + s;
      this.hJk = s;
    }
    if ((s.charAt(0) != 202) && (s.charAt(1) != 202))
      s = "ÊÊ" + s;
    int j = s.length();
    this.hJk = "";
    for (int k = 0; k < j; ++k)
      if ((k < j - 3) && 
        (s.charAt(k) == 202) && 
        (s.charAt(k + 1) == 202)) {
        int l = (s.charAt(k + 2) - '0') * 10 + s.charAt(k + 3) - '0';
        if ((k < j - 5) && ((((l <= 81) && (l >= 80)) || ((l <= 34) && (l >= 31))))) {
          this.hJk = 
            this.hJk + 
            " (" + 
            s.charAt(k + 2) + 
            s.charAt(k + 3) + 
            s.charAt(k + 4) + 
            s.charAt(k + 5) + 
            ") ";
          k += 5;
        }
        else if ((k < j - 4) && ((((l <= 49) && (l >= 40)) || ((l <= 25) && (l >= 23))))) {
          this.hJk = 
            this.hJk + 
            " (" + 
            s.charAt(k + 2) + 
            s.charAt(k + 3) + 
            s.charAt(k + 4) + 
            ") ";
          k += 4;
        } else if (((l <= 30) && (l >= 0)) || ((l <= 99) && (l >= 90))) {
          this.hJk = this.hJk + " (" + s.charAt(k + 2) + s.charAt(k + 3) + ") ";
          k += 3;
        }
      } else {
        this.hJk += s.charAt(k);
      }

    nbvc(g, "bwbwbw", "211232");
    int i1 = 1;
    for (int j1 = 0; j1 < s.length(); ++j1) {
      String s1 = "" + s.charAt(j1);
      if (this.Code128Set == 'C') {
        String s2 = "" + s1;
        if (++j1 < s.length())
          s2 = s2 + s.charAt(j1);
        int i2 = asdf(this.t5y, s2);
        if (i2 >= 0) {
          nbvc(g, "bwbwbw", this.e3r[i2]);
          i += i2 * i1;
        }
      } else {
        int l1 = asdf(as, s1);
        if (l1 >= 0) {
          nbvc(g, "bwbwbw", this.e3r[l1]);
          i += l1 * i1;
        }
      }
      ++i1;
    }

    if (this.checkCharacter) {
      int k1 = (int)bnmq(i, 103.0D);
      nbvc(g, "bwbwbw", this.e3r[k1]);
    }
    nbvc(g, "bwbwbwb", "2331112");
  }

  protected void fghj(Graphics g) {
    if (this.code.length() < 7)
      return;
    if ((this.code.length() == 7) && (this.checkCharacter))
      this.code += iopa(this.code);
    vcxz(g, "bwb", "nnn", 0);
    this.yUi = this.zLk;
    for (int i = 0; i < 8; ++i) {
      String s = "" + this.code.charAt(i);
      if (i <= 3) {
        int j = rtyu(this.eWq, s);
        nbvc(g, "wbwb", this.eWq[j][1]);
      } else {
        int k = rtyu(this.qMn, s);
        nbvc(g, "bwbw", this.qMn[k][1]);
      }
      if (i == 3) {
        this.uIo = this.zLk;
        vcxz(g, "wbwbw", "nnnnn", 0);
        this.iOp = this.zLk;
      }
    }

    this.oPa = this.zLk;
    vcxz(g, "bwb", "nnn", 0);
    this.pAs = this.zLk;
    if (this.mQw)
      hjkl(g, this.fGh);
    else if (this.mNb)
      jklz(g, this.fGh);
  }

  protected void ghjk(Graphics g) {
    int i = 0;
    String s = "";
    if (this.code.length() == 13) {
      this.fGh = this.code.substring(11, 13);
      this.mQw = true;
    }
    if (this.code.length() == 14) {
      this.fGh = this.code.substring(12, 14);
      this.mQw = true;
    }
    if (this.code.length() == 16) {
      this.fGh = this.code.substring(11, 16);
      this.mNb = true;
    }
    if (this.code.length() == 17) {
      this.fGh = this.code.substring(12, 17);
      this.mNb = true;
    }
    if (this.code.length() < 11)
      return;
    this.code = this.code.substring(0, 11);
    this.code += iopa(this.code);
    i = new Integer("" + this.code.charAt(11)).intValue();
    if ((this.code.substring(3, 6).compareTo("000") == 0) || 
      (this.code.substring(3, 6).compareTo("100") == 0) || 
      (this.code.substring(3, 6).compareTo("200") == 0))
      s = this.code.substring(1, 3) + this.code.substring(8, 11) + this.code.charAt(3);
    if ((this.code.substring(3, 6).compareTo("300") == 0) || 
      (this.code.substring(3, 6).compareTo("400") == 0) || 
      (this.code.substring(3, 6).compareTo("500") == 0) || 
      (this.code.substring(3, 6).compareTo("600") == 0) || 
      (this.code.substring(3, 6).compareTo("700") == 0) || 
      (this.code.substring(3, 6).compareTo("800") == 0) || 
      (this.code.substring(3, 6).compareTo("900") == 0))
      s = this.code.substring(1, 4) + this.code.substring(9, 11) + "3";
    if ((this.code.substring(4, 6).compareTo("10") == 0) || 
      (this.code.substring(4, 6).compareTo("20") == 0) || 
      (this.code.substring(4, 6).compareTo("30") == 0) || 
      (this.code.substring(4, 6).compareTo("40") == 0) || 
      (this.code.substring(4, 6).compareTo("50") == 0) || 
      (this.code.substring(4, 6).compareTo("60") == 0) || 
      (this.code.substring(4, 6).compareTo("70") == 0) || 
      (this.code.substring(4, 6).compareTo("80") == 0) || 
      (this.code.substring(4, 6).compareTo("90") == 0))
      s = this.code.substring(1, 5) + this.code.substring(10, 11) + "4";
    if (this.code.substring(5, 6).compareTo("0") != 0)
      s = this.code.substring(1, 6) + this.code.substring(10, 11);
    this.hJk = "0" + s + i;
    vcxz(g, "bwb", "nnn", 0);
    this.yUi = this.zLk;
    String s1 = this.tRe[i];
    if (this.UPCESytem == '1')
      s1 = this.rEw[i];
    for (int j = 0; j < s.length(); ++j) {
      String s2 = "" + s.charAt(j);
      int k = -1;
      String[][] as = this.pOi;
      if (s1.charAt(j) == 'E')
        as = this.oIu;
      k = rtyu(as, s2);
      nbvc(g, "wbwb", as[k][1]);
    }

    this.oPa = this.zLk;
    vcxz(g, "wbwbwb", "nnnnnn", 0);
    this.pAs = this.zLk;
    if (this.mQw)
      hjkl(g, this.fGh);
    else if (this.mNb)
      jklz(g, this.fGh);
  }

  protected void hjkl(Graphics g, String s) {
    int i;
    if (this.fGh.length() > 0)
      s = this.fGh;
    this.dFg = (int)(this.cVb * (1.0D - this.xZl));
    this.qWe = s;
    if (s.length() != 2)
      return;
    this.zLk = (int)(this.zLk + this.xCv * this.cXz);
    this.aSd = this.zLk;
    try
    {
      i = Integer.valueOf(s).intValue();
    } catch (Exception exception) {
      i = 0;
    }
    String s1 = "OO";
    if (bnmq(i, 4.0D) == 1.0D)
      s1 = "OE";
    if (bnmq(i, 4.0D) == 2.0D)
      s1 = "EO";
    if (bnmq(i, 4.0D) == 3.0D)
      s1 = "EE";
    vcxz(g, "bwb", "112", this.dFg);
    String[][] as = this.pOi;
    if (s1.charAt(0) == 'E')
      as = this.oIu;
    int j = rtyu(as, "" + s.charAt(0));
    vcxz(g, "wbwb", as[j][1], this.dFg);
    vcxz(g, "wb", "11", this.dFg);
    as = this.pOi;
    if (s1.charAt(1) == 'E')
      as = this.oIu;
    j = rtyu(as, "" + s.charAt(1));
    vcxz(g, "wbwb", as[j][1], this.dFg);
    this.sDf = this.zLk;
  }

  protected void jklz(Graphics g, String s) {
    if (this.fGh.length() > 0)
      s = this.fGh;
    this.dFg = (int)(this.cVb * (1.0D - this.xZl));
    this.qWe = s;
    if (s.length() != 5)
      return;
    boolean flag = true;
    int i = 0;
    int j = 0;
    for (int k = s.length() - 1; k >= 0; --k) {
      if (flag)
        i += new Integer("" + s.charAt(k)).intValue();
      else
        j += new Integer("" + s.charAt(k)).intValue();
      flag = !(flag);
    }

    j = i * 3 + j * 9;
    String s1 = "" + j;
    int l = new Integer("" + s1.charAt(s1.length() - 1)).intValue();
    String s2 = this.w2e[l];
    this.zLk = (int)(this.zLk + this.xCv * this.cXz);
    this.aSd = this.zLk;
    vcxz(g, "bwb", "112", this.dFg);
    for (int i1 = 0; i1 < 5; ++i1) {
      String[][] as = this.pOi;
      if (s2.charAt(i1) == 'E')
        as = this.oIu;
      int j1 = rtyu(as, "" + s.charAt(i1));
      vcxz(g, "wbwb", as[j1][1], this.dFg);
      if (i1 < 4)
        vcxz(g, "wb", "11", this.dFg);
    }

    this.sDf = this.zLk;
  }

  protected void klzx(Graphics g) {
    String s = this.code;
    nbvc(g, "bwbwbw", "wnnnnn");
    for (int i = 0; i < s.length(); ++i) {
      String s1 = "" + this.code.charAt(i);
      int j = rtyu(this.jHg, s1);
      if (j >= 0)
        nbvc(g, "bwbwbw", this.jHg[j][1] + "n");
    }

    nbvc(g, "bwbwbw", "wnnnnn");
  }

  protected void lzxc(Graphics g) {
    int i = 0;
    this.code = this.code.toUpperCase();
    nbvc(g, "bwbwbwbwb", this.kJh[rtyu(this.kJh, "*")][1]);
    this.zLk += this.jKl;
    for (int j = 0; j < this.code.length(); ++j) {
      String s = "" + this.code.charAt(j);
      int l = rtyu(this.kJh, s);
      if (l > -1) {
        i += l;
        nbvc(g, "bwbwbwbwb", this.kJh[l][1]);
        this.zLk += this.jKl;
      }
    }

    if (this.checkCharacter) {
      int k = (int)bnmq(i, 43.0D);
      nbvc(g, "bwbwbwbwb", this.kJh[k][1]);
      this.zLk += this.jKl;
      if (this.checkCharacterInText)
        this.hJk = this.code + "" + this.kJh[k][0];
      else
        this.hJk = this.code;
    }
    nbvc(g, "bwbwbwbwb", this.kJh[rtyu(this.kJh, "*")][1]);
  }

  protected void zxcv(Graphics g) {
    int i = 0;
    nbvc(g, "bwbwbw", "nnwwnn");
    int j = 1;
    i = 0;
    for (int k = this.code.length() - 1; k >= 0; --k) {
      i += rtyu(this.gFd, "" + this.code.charAt(k)) * j;
      if (++j == 11)
        j = 1;
    }

    int l = (int)bnmq(i, 11.0D);
    j = 2;
    i = l;
    for (int i1 = this.code.length() - 1; i1 >= 0; --i1) {
      i += rtyu(this.gFd, "" + this.code.charAt(i1)) * j;
      if (++j == 10)
        j = 1;
    }

    int j1 = (int)bnmq(i, 11.0D);
    for (int k1 = 0; k1 < this.code.length(); ++k1) {
      String s = "" + this.code.charAt(k1);
      int l1 = rtyu(this.gFd, s);
      if (l1 > -1)
        nbvc(g, "bwbwbw", this.gFd[l1][1] + "n");
    }

    if (this.checkCharacter) {
      nbvc(g, "bwbwbw", this.gFd[l][1] + "n");
      if (this.checkCharacterInText)
        this.hJk = this.code + this.gFd[l][0];
      else
        this.hJk = this.code;
      if (this.code.length() > 10) {
        nbvc(g, "bwbwbw", this.gFd[j1][1] + "n");
        if (this.checkCharacterInText)
          this.hJk += this.gFd[j1][0];
        else
          this.hJk = this.code;
      }
    }
    nbvc(g, "bwbwbw", "nnwwnn");
  }

  protected void xcvb(Graphics g) {
    int i = 0;
    nbvc(g, "bwbwbwbw", this.fDs[rtyu(this.fDs, new StringBuffer("").append(this.bNm).toString())][1] + "n");
    i = rtyu(this.fDs, "" + this.bNm) + rtyu(this.fDs, "" + this.nMq);
    for (int j = this.code.length() - 1; j >= 0; --j)
      i += rtyu(this.fDs, "" + this.code.charAt(j));

    int k = (int)bnmq(i, 16.0D);
    if (k != 0)
      k = 16 - k;
    for (int l = 0; l < this.code.length(); ++l) {
      String s = "" + this.code.charAt(l);
      int i1 = rtyu(this.fDs, s);
      if (i1 > -1)
        nbvc(g, "bwbwbwbw", this.fDs[i1][1] + "n");
    }

    if (this.checkCharacter) {
      if (this.checkCharacterInText)
        this.hJk = this.code + this.fDs[k][0];
      else
        this.hJk = this.code;
      nbvc(g, "bwbwbwbw", this.fDs[k][1] + "n");
    }
    nbvc(g, "bwbwbwb", this.fDs[rtyu(this.fDs, "" + this.nMq)][1]);
  }

  protected void cvbn(Graphics g) {
    int i = 0;
    nbvc(g, "bw", "wn");
    i = 0;
    String s = "";
    boolean flag = true;
    for (int j = this.code.length() - 1; j >= 0; --j) {
      if (!(flag))
        i += rtyu(this.hGf, "" + this.code.charAt(j));
      if (flag)
        s = rtyu(this.hGf, new StringBuffer("").append(this.code.charAt(j)).toString()) + s;
      flag = !(flag);
    }

    s = "" + (new Long(s).longValue() * 2L);
    for (int k = s.length() - 1; k >= 0; --k)
      i += rtyu(this.hGf, "" + s.charAt(k));

    int l = (int)bnmq(i, 10.0D);
    if (l != 0)
      l = 10 - l;
    for (int i1 = 0; i1 < this.code.length(); ++i1) {
      String s1 = "" + this.code.charAt(i1);
      int j1 = rtyu(this.hGf, s1);
      if (j1 > -1)
        nbvc(g, "bwbwbwbw", this.hGf[j1][1]);
    }

    if (this.checkCharacter) {
      nbvc(g, "bwbwbwb", this.hGf[l][1]);
      if (this.checkCharacterInText)
        this.hJk = this.code + this.hGf[l][0];
      else
        this.hJk = this.code;
    }
    nbvc(g, "wbwb", "nnwn");
  }

  protected void vbnm(Graphics g)
  {
    g.setFont(this.textFont);
    int i = g.getFontMetrics().getHeight();
    g.setColor(this.backColor);
    g.fillRect(this.rTy + 2, this.tYu + 2, 79, i);
    g.setColor(this.barColor);

    String title = "abcdef";
    g.drawString(title, this.rTy + 5, i + 5);
  }

  protected static double bnmq(double d, double d1) {
    double d2 = d / d1;
    double d3 = Math.round(d2);
    if (d3 > d2)
      d3 -= 1.0D;
    return (d - (d1 * d3));
  }

  protected void nmqw(Graphics g) {
    int i = 0;
    nbvc(g, "bwbwbwbwb", this.kJh[rtyu(this.kJh, "*")][1]);
    this.zLk += this.jKl;
    for (int j = 0; j < this.code.length(); ++j) {
      byte byte0 = (byte)this.code.charAt(j);
      if (byte0 <= 128) {
        String s = this.iUy[byte0];
        for (int j1 = 0; j1 < s.length(); ++j1) {
          String s1 = "" + s.charAt(j1);
          int k1 = rtyu(this.kJh, s1);
          if (k1 > -1) {
            i += k1;
            nbvc(g, "bwbwbwbwb", this.kJh[k1][1]);
            this.zLk += this.jKl;
          }
        }
      }

    }

    this.hJk = "";
    for (int k = 1; k <= this.code.length(); ++k) {
      int l = this.code.charAt(k - 1);
      if ((l < 32) && (l >= 0))
        if ((l == 13) || (l == 9))
          this.hJk += "  ";
      else
        this.hJk += (char)l;

    }

    if (this.checkCharacter) {
      int i1 = (int)bnmq(i, 43.0D);
      nbvc(g, "bwbwbwbwb", this.kJh[i1][1]);
      this.zLk += this.jKl;
      if (this.checkCharacterInText)
        this.hJk = this.hJk + "" + this.kJh[i1][0];
    }
    nbvc(g, "bwbwbwbwb", this.kJh[rtyu(this.kJh, "*")][1]);
  }

  protected void mqwe(Graphics g) {
    int i = 0;
    int j = 0;
    int k = 0;
    nbvc(g, "bwbwbw", "111141");
    for (int l = 0; l < this.code.length(); ++l) {
      String s = "" + this.code.charAt(l);
      int j1 = rtyu(this.dSa, s);
      if (j1 > -1) {
        i += j1;
        nbvc(g, "bwbwbw", this.dSa[j1][1]);
      }
    }

    int i1 = 1;
    i = 0;
    for (int k1 = this.code.length() - 1; k1 >= 0; --k1) {
      i += rtyu(this.dSa, "" + this.code.charAt(k1)) * i1;
      if (++i1 == 21)
        i1 = 1;
    }

    k = (int)bnmq(i, 47.0D);
    i1 = 2;
    i = k;
    for (int l1 = this.code.length() - 1; l1 >= 0; --l1) {
      i += rtyu(this.dSa, "" + this.code.charAt(l1)) * i1;
      if (++i1 == 16)
        i1 = 1;
    }

    j = (int)bnmq(i, 47.0D);
    if (this.checkCharacter) {
      nbvc(g, "bwbwbw", this.dSa[k][1]);
      nbvc(g, "bwbwbw", this.dSa[j][1]);
      if (this.checkCharacterInText)
        this.hJk = this.code + this.dSa[k][0].charAt(0) + this.dSa[j][0].charAt(0);
      else
        this.hJk = this.code;
    }
    nbvc(g, "bwbwbwb", "1111411");
  }

  protected void mnbv(Graphics g) {
    int i = 0;
    int j = 0;
    int k = 0;
    nbvc(g, "bwbwbw", "111141");
    for (int l = 0; l < this.code.length(); ++l) {
      byte byte0 = (byte)this.code.charAt(l);
      if (byte0 <= 128) {
        String s1;
        String s = this.uYt[byte0];

        if (s.length() == 3) {
          s1 = "" + s.charAt(0) + s.charAt(1);
          int l1 = rtyu(this.dSa, s1);
          nbvc(g, "bwbwbw", this.dSa[l1][1]);
          s1 = "" + s.charAt(2);
        } else {
          s1 = "" + s.charAt(0);
        }
        int i2 = rtyu(this.dSa, s1);
        i += i2;
        nbvc(g, "bwbwbw", this.dSa[i2][1]);
      }
    }

    int i1 = 1;
    i = 0;
    for (int j1 = this.code.length() - 1; j1 >= 0; --j1) {
      byte byte1 = (byte)this.code.charAt(j1);
      if (byte1 <= 128) {
        String s2 = this.uYt[byte1];
        if (s2.length() == 3) {
          String s3 = "" + s2.charAt(0) + s2.charAt(1);
          int j2 = rtyu(this.dSa, s3);
          i += j2 * (i1 + 1);
          s3 = "" + s2.charAt(2);
          j2 = rtyu(this.dSa, s3);
          i += j2 * i1;
          if (++i1 == 21)
            i1 = 1;
          if (++i1 == 21)
            i1 = 1;
        } else {
          String s4 = "" + s2.charAt(0);
          int k2 = rtyu(this.dSa, s4);
          i += k2 * i1;
          if (++i1 == 21)
            i1 = 1;
        }
      }
    }

    k = (int)bnmq(i, 47.0D);
    i1 = 2;
    i = k;
    for (int k1 = this.code.length() - 1; k1 >= 0; --k1) {
      byte byte2 = (byte)this.code.charAt(k1);
      if (byte2 <= 128) {
        String s5 = this.uYt[byte2];
        if (s5.length() == 3) {
          String s6 = "" + s5.charAt(0) + s5.charAt(1);
          int l2 = rtyu(this.dSa, s6);
          i += l2 * (i1 + 1);
          s6 = "" + s5.charAt(2);
          l2 = rtyu(this.dSa, s6);
          i += l2 * i1;
          if (++i1 == 16)
            i1 = 1;
          if (++i1 == 16)
            i1 = 1;
        } else {
          String s7 = "" + s5.charAt(0);
          int i3 = rtyu(this.dSa, s7);
          i += i3 * i1;
          if (++i1 == 16)
            i1 = 1;
        }
      }
    }

    j = (int)bnmq(i, 47.0D);
    if (this.checkCharacter) {
      nbvc(g, "bwbwbw", this.dSa[k][1]);
      nbvc(g, "bwbwbw", this.dSa[j][1]);
      if (this.checkCharacterInText)
        this.hJk = this.code + this.dSa[k][0].charAt(0) + this.dSa[j][0].charAt(0);
      else
        this.hJk = this.code;
    }
    nbvc(g, "bwbwbwb", "1111411");
  }

  protected void nbvc(Graphics g, String s, String s1) {
    bvcx(g, s, s1, 0);
  }

  protected void bvcx(Graphics g, String s, String s1, int i) {
    for (int j = 0; j < s.length(); ++j) {
      char c = s.charAt(j);
      char c1 = s1.charAt(j);
      if (c1 == 'n')
        qwer(g, this.jKl, (c == 'b') ? true : false, i);
      if (c1 == 'w')
        qwer(g, this.kLz, (c == 'b') ? true : false, i);
      if (c1 == '1')
        qwer(g, this.jKl, (c == 'b') ? true : false, i);
      if (c1 == '2')
        qwer(g, this.jKl * 2, (c == 'b') ? true : false, i);
      if (c1 == '3')
        qwer(g, this.jKl * 3, (c == 'b') ? true : false, i);
      if (c1 == '4')
        qwer(g, this.jKl * 4, (c == 'b') ? true : false, i);
    }
  }

  protected void vcxz(Graphics g, String s, String s1, int i)
  {
    if ((this.textFont != null) && (this.gHj)) {
      g.setFont(this.textFont);
      this.vBn = g.getFontMetrics().getHeight();
    }
    bvcx(g, s, s1, i);
    this.vBn = 0;
  }

  protected void cxzl() {
    int i = this.code.length();
    this.lZx = this.X;
    this.zXc = (this.X * this.N);
    if (this.barType == 2) {
      if ((bnmq(i, 2.0D) == 0.0D) && (this.checkCharacter))
        ++i;
      if ((bnmq(i, 2.0D) == 1.0D) && (!(this.checkCharacter)))
        ++i;
      if (this.checkCharacter)
        ++i;
      this.vCx = (i / 2 * (3.0D + 2.0D * this.N) * this.X + 7.0D * this.X);
    }
    if (this.barType == 6)
      this.vCx = (i * 7 * this.X + 11.0D * this.X);
    if (this.barType == 10)
      this.vCx = (i * 7 * this.X + 11.0D * this.X);
    if (this.barType == 11)
      this.vCx = (i * 7 * this.X + 11.0D * this.X);
    if (this.barType == 13) {
      if (this.checkCharacter)
        ++i;
      if (this.Code128Set == 'C')
        this.vCx = ((11 * i + 35) * this.X);
      else
        this.vCx = ((5.5D * i + 35.0D) * this.X);
    }
    if (this.barType == 12)
      this.vCx = (56.0D * this.X + 11.0D * this.X);
    if (this.barType == 7) {
      if (this.checkCharacter)
        ++i;
      this.vCx = (i * (3.0D + 2.0D * this.N) * this.X + 7.0D * this.X);
    }
    if (this.barType == 8) {
      if (this.checkCharacter)
        ++i;
      this.vCx = (i * (3.0D + 2.0D * this.N) * this.X + 7.0D * this.X);
    }
    if (this.barType == 5) {
      if (this.checkCharacter)
        ++i;
      this.vCx = 
        (i * (4.0D + 4.0D * this.N) * this.X + (1.0D + this.N) * this.X + (2.0D + this.N) * this.X);
    }
    if (this.barType == 4) {
      if (this.checkCharacter)
        ++i;
      this.vCx = ((i + 2) * (4.0D + 3.0D * this.N) * this.X);
    }
    if (this.barType == 3) {
      if ((this.checkCharacter) || (this.code.length() > 10))
        ++i;
      this.vCx = ((i + 2 + 1) * (3.0D + 2.0D * this.N) * this.X);
    }
    if (this.barType == 15) {
      if (this.checkCharacter)
        ++i;
      this.vCx = (this.X * 10.0D);
    }
    if (this.barType == 0) {
      if (this.checkCharacter)
        ++i;
      this.vCx = 
        ((i + 2) * (3.0D * this.N + 6.0D) * this.X + 
        (i + 1) * this.nBv * this.X);
    }
    if (this.barType == 1) {
      int j = 0;
      if (this.checkCharacter)
        ++j;
      for (int l = 0; l < this.code.length(); ++l) {
        byte byte0 = (byte)this.code.charAt(l);
        if (byte0 <= 128) {
          String s = this.iUy[byte0];
          j += s.length();
        }
      }

      this.vCx = 
        ((j + 2) * (3.0D * this.N + 6.0D) * this.X + 
        (j + 1) * this.nBv * this.X);
    }
    if ((this.barType == 9) || (this.barType == 14)) {
      int k = 0;
      if (this.checkCharacter)
        ++k;
      for (int i1 = 0; i1 < this.code.length(); ++i1) {
        byte byte1 = (byte)this.code.charAt(i1);
        if (byte1 <= 128) {
          String s1 = this.iUy[byte1];
          if (s1.length() == 1)
            ++k;
          else
            k += 2;
        }
      }

      this.vCx = ((k + 2) * 9.0D * this.X + (k + 1) * this.nBv * this.X);
    }
    if (this.barHeightCM == 0.0D) {
      this.barHeightCM = (this.vCx * this.bVc);
      if (this.barHeightCM < 0.625D)
        this.barHeightCM = 0.625D;
    }
    if (this.barHeightCM != 0.0D)
      this.cVb = (int)(this.barHeightCM * this.xCv);
    if (this.lZx != 0.0D)
      this.jKl = (int)(this.lZx * this.xCv);
    if (this.zXc != 0.0D)
      this.kLz = (int)(this.jKl * this.N);
    if (this.jKl <= 0)
      this.jKl = 1;
    if (this.kLz <= 1)
      this.kLz = 2;
  }

  public void paint(Graphics g) {
    Graphics g1 = g;
    Image image = null;
    if (this.rotate != 0) {
      String s = System.getProperty("java.version");
      if ((s.indexOf("1.0") == 0) || (s.indexOf("1.1") == 0)) {
        image = createImage(getSize().width, getSize().height);
        g1 = image.getGraphics();
      } else {
        ImgCreator imgcreator = new ImgCreator();
        image = imgcreator.getImage(getSize().width, getSize().height);
        g1 = imgcreator.getGraphics();
      }
    }
    g.setColor(this.backColor);
    g.fillRect(0, 0, getSize().width, getSize().height);
    xzlk(g1);
    if (this.rotate != 0) {
      int i = this.zLk + this.rTy;
      int j = this.lKj + this.tYu;
      Image image1 = zlkj(image, this.rotate, i, j);
      if (image1 == null)
        g.drawImage(image, 0, 0, null);
      else
        g.drawImage(image1, 0, 0, null);
    }
  }

  protected void xzlk(Graphics g)
  {
    this.hJk = "";
    cxzl();
    this.tYu = (int)(this.topMarginCM * this.xCv);
    this.rTy = (int)(this.leftMarginCM * this.xCv);
    this.zLk = this.rTy;
    g.setColor(this.backColor);
    int i = getSize().width;
    int j = getSize().height;
    int k = i;
    if (j > k)
      k = j;
    g.fillRect(0, 0, k, k);
    this.pAs = 0;

    if (this.barType == 3)
      zxcv(g);
    if (this.barType == 5)
      cvbn(g);
    if (this.barType == 4)
      xcvb(g);
    if (this.barType == 0)
      lzxc(g);
    if (this.barType == 1)
      nmqw(g);
    if (this.barType == 2)
      yuio(g);
    if (this.barType == 9)
      mqwe(g);
    if (this.barType == 11)
      fghj(g);
    if (this.barType == 10)
      pasd(g);
    if (this.barType == 6)
      opas(g);
    if (this.barType == 12)
      ghjk(g);
    if (this.barType == 13)
      sdfg(g);
    if (this.barType == 14)
      mnbv(g);
    if (this.barType == 7)
      uiop(g);
    if (this.barType == 8)
      klzx(g);
    if (this.barType == 15)
      erty(g);
    if (this.barType == 16)
      tyui(g);
    if (this.barType == 17)
      dfgh(g);

    if (this.pAs == 0)
      this.pAs = this.zLk;
    if (this.hJk.length() == 0) {
      this.hJk = this.code;
    }

    if ((this.showText) && (this.textFont != null)) {
      g.setFont(this.textFont);
      int l = g.getFontMetrics().getHeight();
      if ((this.rotate == 0) || (this.rotate == 180)) {
        this.height = (this.cVb + l + this.tYu * 2);
        this.width = (this.zLk + this.rTy + 2);
        if ((this.barType == 15) || (this.barType == 16))
          this.height = ((int)(this.wEr * this.xCv) + l + 11 + this.tYu);
      } else {
        this.width = (this.cVb + l + this.tYu * 2);
        this.height = (this.zLk + this.rTy + 2);
        if ((this.barType == 15) || (this.barType == 16))
          this.width = ((int)(this.wEr * this.xCv) + l + 11 + this.tYu);
      }
    } else if ((this.rotate == 0) || (this.rotate == 180)) {
      this.height = (this.cVb + this.tYu * 2);
      this.width = (this.zLk + this.rTy + 2);
      if ((this.barType == 15) || (this.barType == 16))
        this.height = ((int)(this.wEr * this.xCv) + 1 + this.tYu);
    } else {
      this.width = (this.cVb + this.tYu * 2);
      this.height = (this.zLk + this.rTy + 2);
      if ((this.barType == 15) || (this.barType == 16))
        this.width = ((int)(this.wEr * this.xCv) + 1 + this.tYu);
    }
    if (this.autoSize)
      setSize(this.width, this.height);
    this.lKj = (this.cVb + this.tYu);

    if ((this.showText) && (this.textFont != null)) {
      g.setColor(this.fontColor);
      g.setFont(this.textFont);
      int i1 = g.getFontMetrics().getHeight();
      int j1 = g.getFontMetrics().stringWidth("X");
      if ((((this.mQw) || (this.mNb))) && ((
        (this.barType == 11) || 
        (this.barType == 6) || 
        (this.barType == 12) || 
        (this.barType == 10)))) {
        int k1 = (this.sDf - this.aSd - g.getFontMetrics().stringWidth(this.qWe)) / 2;
        if (k1 < 0)
          k1 = 0;
        g.drawString(this.qWe, this.aSd + k1, this.tYu + this.dFg - 2);
      }
      if ((this.barType == 15) || (this.barType == 16)) {
        int l1 = (this.pAs - this.rTy - g.getFontMetrics().stringWidth(this.hJk)) / 2;
        if (l1 < 0)
          l1 = 0;
        g.drawString(
          this.hJk, 
          this.rTy + l1, 
          (int)(this.wEr * this.xCv + 
          i1 + 
          1.0D + 
          this.tYu));
        this.lKj = ((int)(this.wEr * this.xCv) + i1 + 1 + this.tYu);
        return;
      }
      if ((this.barType == 10) && (this.gHj) && (this.hJk.length() >= 13)) {
        int i2 = 0;
        g.drawString(this.hJk.substring(0, 1), this.rTy - j1, this.cVb + i1 + 1 + this.tYu);
        i2 = 
          (this.uIo - 
          this.yUi - 
          g.getFontMetrics().stringWidth(this.hJk.substring(1, 7))) / 
          2;
        if (i2 < 0)
          i2 = 0;
        g.drawString(this.hJk.substring(1, 7), this.yUi + i2, this.cVb + i1 + 1 + this.tYu);
        i2 = 
          (this.oPa - 
          this.iOp - 
          g.getFontMetrics().stringWidth(this.hJk.substring(7, 13))) / 
          2;
        if (i2 < 0)
          i2 = 0;
        g.drawString(
          this.hJk.substring(7, 13), 
          this.iOp + i2, 
          this.cVb + i1 + 1 + this.tYu);
        this.lKj = (this.cVb + i1 + 1 + this.tYu);
        return;
      }
      if ((this.barType == 6) && (this.gHj) && (this.hJk.length() >= 12)) {
        int j2 = 0;
        g.drawString(this.hJk.substring(0, 1), this.rTy - j1, this.cVb + i1 + 1 + this.tYu);
        j2 = 
          (this.uIo - 
          this.yUi - 
          g.getFontMetrics().stringWidth(this.hJk.substring(1, 6))) / 
          2;
        if (j2 < 0)
          j2 = 0;
        g.drawString(this.hJk.substring(1, 6), this.yUi + j2, this.cVb + i1 + 1 + this.tYu);
        j2 = 
          (this.oPa - 
          this.iOp - 
          g.getFontMetrics().stringWidth(this.hJk.substring(6, 11))) / 
          2;
        if (j2 < 0)
          j2 = 0;
        g.drawString(
          this.hJk.substring(6, 11), 
          this.iOp + j2, 
          this.cVb + i1 + 1 + this.tYu);
        g.drawString(
          this.hJk.substring(11, 12), 
          this.pAs + 3, 
          this.cVb + i1 + 1 + this.tYu);
        this.lKj = (this.cVb + i1 + 1 + this.tYu);
        return;
      }
      if ((this.barType == 11) && (this.gHj) && (this.hJk.length() >= 8)) {
        int k2 = 0;
        k2 = 
          (this.uIo - 
          this.yUi - 
          g.getFontMetrics().stringWidth(this.hJk.substring(0, 4))) / 
          2;
        if (k2 < 0)
          k2 = 0;
        g.drawString(this.hJk.substring(0, 4), this.yUi + k2, this.cVb + i1 + 1 + this.tYu);
        k2 = 
          (this.oPa - 
          this.iOp - 
          g.getFontMetrics().stringWidth(this.hJk.substring(4, 8))) / 
          2;
        if (k2 < 0)
          k2 = 0;
        g.drawString(this.hJk.substring(4, 8), this.iOp + k2, this.cVb + i1 + 1 + this.tYu);
        this.lKj = (this.cVb + i1 + 1 + this.tYu);
        return;
      }
      if ((this.barType == 12) && (this.gHj) && (this.hJk.length() >= 8)) {
        int l2 = 0;
        g.drawString(this.hJk.substring(0, 1), this.rTy - j1, this.cVb + i1 + 1 + this.tYu);
        l2 = 
          (this.oPa + 2 - 
          this.yUi - 
          g.getFontMetrics().stringWidth(this.hJk.substring(1, 7))) / 
          2;
        if (l2 < 0)
          l2 = 0;
        g.drawString(this.hJk.substring(1, 7), this.yUi + l2, this.cVb + i1 + 1 + this.tYu);
        g.drawString(this.hJk.substring(7, 8), this.pAs + 2, this.cVb + i1 + 1 + this.tYu);
        this.lKj = (this.cVb + i1 + 1 + this.tYu);
        return;
      }
      int i3 = (this.pAs - this.rTy - g.getFontMetrics().stringWidth(this.hJk)) / 2;
      if (i3 < 0)
        i3 = 0;
      g.drawString(this.hJk, this.rTy + i3, this.cVb + i1 + 1 + this.tYu);
      this.lKj = (this.cVb + i1 + 1 + this.tYu);
    }
  }

  protected Image zlkj(Image image, int i, int j, int k) {
    int l = image.getWidth(null);
    int i1 = image.getHeight(null);
    if (j > l)
      j = l;
    if (k > i1)
      k = i1;
    int[] ai = new int[l * i1];
    int[] ai1 = new int[j * k];
    PixelGrabber pixelgrabber = 
      new PixelGrabber(image, 0, 0, l, i1, ai, 0, l);
    try {
      pixelgrabber.grabPixels();
    } catch (InterruptedException interruptedexception) {
      System.err.println("interrupted waiting for pixels!");
      return null;
    }
    if ((pixelgrabber.getStatus() & 0x80) != 0) {
      System.err.println("image fetch aborted or errored");
      return null;
    }
    if (i == 90) {
      for (int j1 = 0; j1 < j; ++j1) {
        for (int i2 = 0; i2 < k; ++i2)
          ai1[(k * (j - (j1 + 1)) + i2)] = ai[(i2 * l + j1)];

      }

      return Toolkit.getDefaultToolkit().createImage(
        new MemoryImageSource(k, j, ai1, 0, k));
    }
    if (i == 180) {
      for (int k1 = 0; k1 < j; ++k1) {
        for (int j2 = 0; j2 < k; ++j2)
          ai1[((k - (j2 + 1)) * j + j - (k1 + 1))] = ai[(j2 * l + k1)];

      }

      return Toolkit.getDefaultToolkit().createImage(
        new MemoryImageSource(j, k, ai1, 0, j));
    }
    if (i == 270) {
      for (int l1 = 0; l1 < j; ++l1) {
        for (int k2 = 0; k2 < k; ++k2)
          ai1[(k * l1 + k - (k2 + 1))] = ai[(k2 * l + l1)];

      }

      return Toolkit.getDefaultToolkit().createImage(
        new MemoryImageSource(k, j, ai1, 0, k));
    }
    return null;
  }
}