package pengpol;
import java.util.Scanner;
/**
 *
 * @author Kelompok Pengpol
 */
public class Pengpol {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner ins = new Scanner(System.in);
        
        data();
        double Pkaya, Psedang, Pmiskin;
        Pkaya = (countStatus("kaya"));//P(kaya)
        Psedang = (countStatus("sedang"));//P(sedang)
        Pmiskin = (countStatus("miskin"));//P(miskin)

        double Pluaskaya, Pluassedang, Pluasmiskin, luasbang;
        System.out.print("Luas Bangunan : ");
        luasbang = in.nextDouble();
        Pluaskaya = luasBangunan(luasbang,"kaya");//P(luas|kaya)
        Pluassedang = luasBangunan(luasbang,"sedang");//P(luas|sedang)
        Pluasmiskin = luasBangunan(luasbang,"miskin");//P(luas|miskin)
        
        System.out.print("Bahan bakar : ");
        String pilihanbb = ins.nextLine();
        double Pbbkaya = 0, Pbbsedang = 0, Pbbmiskin = 0;
        
        if (pilihanbb.equals("kayu bakar")) {
            Pbbkaya = countBB("kayu bakar", "kaya");//P(kayu bakar|kaya)
            Pbbsedang = countBB("kayu bakar", "sedang");//P(kayu bakar|sedang)
            Pbbmiskin = countBB("kayu bakar", "miskin");//P(kayu bakar|miskin)
        }
            
        if (pilihanbb.equals("gas LPG")) {
            Pbbkaya = countBB("gas LPG", "kaya");//P(gas lpg|kaya)
            Pbbsedang = countBB("gas LPG", "sedang");//P(gas lpg|sedang)
            Pbbmiskin = countBB("gas LPG", "miskin");//P(gas lpg|miskin)
        }
            
        if (pilihanbb.equals("kompor listrik")) {
            Pbbkaya = countBB("kompor listrik", "kaya");//P(kompor listrik|kaya)
            Pbbsedang = countBB("kompor listrik", "sedang");//P(kompor listrik|sedang)
            Pbbmiskin = countBB("kompor listrik", "miskin");//P(kompor listrik|miskin)
        }
                
        System.out.print("Lantai : ");
        String pilihanlantai = ins.nextLine();
        double Plantaikaya = 0, Plantaisedang = 0, Plantaimiskin = 0;
                
        if (pilihanlantai.equals("ubin")) {
            Plantaikaya = countLantai("ubin", "kaya");//P(ubin|kaya)
            Plantaisedang = countLantai("ubin", "sedang");//P(ubin|sedang)
            Plantaimiskin = countLantai("ubin", "miskin");//P(ubin|miskin)
        }
        
        if (pilihanlantai.equals("plester")) {
            Plantaikaya = countLantai("plester", "kaya");//P(plester|kaya)
            Plantaisedang = countLantai("plester", "sedang");//P(plester|sedang)
            Plantaimiskin = countLantai("plester", "miskin");//P(plester|miskin)
        }
        
        if (pilihanlantai.equals("tanah")) {
            Plantaikaya = countLantai("tanah", "kaya");//P(tanah|kaya)
            Plantaisedang = countLantai("tanah", "sedang");//P(tanah|sedang)
            Plantaimiskin = countLantai("tanah", "miskin");//P(tanah|miskin)
        }
                                         
        double Pdagingkaya, Pdagingsedang, Pdagingmiskin, frekdaging;
        System.out.print("Frekuensi makan daging : ");
        frekdaging = in.nextDouble();
        Pdagingkaya = daging(frekdaging,"kaya");//P(luas|kaya)
        Pdagingsedang = daging(frekdaging,"sedang");//P(luas|sedang)
        Pdagingmiskin = daging(frekdaging,"miskin");//P(luas|miskin)
        
        //P(x|miskin)
        double Pxmiskin = P(Pmiskin, Pluasmiskin, Pbbmiskin, Plantaimiskin, Pdagingmiskin);
        //P(x|sedang)
        double Pxsedang = P(Psedang, Pluassedang, Pbbsedang, Plantaisedang, Pdagingsedang);  
        //P(x|sedang)
        double Pxkaya = P(Pkaya, Pluaskaya, Pbbkaya, Plantaikaya, Pdagingkaya);
        
        System.out.println("P(miskin) = "+ Pmiskin);
        System.out.println("P(sedang) = "+ Psedang);
        System.out.println("P(kaya) = "+ Pkaya);
        System.out.println("P(luas : "+ luasbang+" |Kaya) = "+ Pluaskaya);
        System.out.println("P(luas : "+ luasbang+" |Sedang) = "+ Pluassedang);
        System.out.println("P(luas : "+ luasbang+" |Miskin) = "+ Pluasmiskin);
        System.out.println("P(Bahan bakar : "+ pilihanbb+" |kaya) = "+Pbbkaya);
        System.out.println("P(Bahan bakar : "+ pilihanbb+" |sedang) = "+Pbbsedang);
        System.out.println("P(Bahan bakar : "+ pilihanbb+" |miskin) = "+Pbbmiskin);
        System.out.println("P(Jenis lantai : "+ pilihanlantai+" |Kaya) = "+Plantaikaya);
        System.out.println("P(Jenis lantai : "+ pilihanlantai+" |Sedang) = "+Plantaisedang);
        System.out.println("P(Jenis lantai : "+ pilihanlantai+" |miskin) = "+Plantaimiskin);
        System.out.println("P(Frek makan daging : "+ frekdaging+" |kaya) = "+Pdagingkaya);
        System.out.println("P(Frek makan daging : "+ frekdaging+" |sedang) = "+Pdagingsedang);
        System.out.println("P(Frek makan daging : "+ frekdaging+" |miskin) = "+Pdagingmiskin);
        System.out.println("P(x|kaya) = "+Pxkaya);
        System.out.println("P(x|sedang) = "+Pxsedang);
        System.out.println("P(x|miskin) = "+Pxmiskin);
        //hasil akhir
        String hasil = hasil(Pxmiskin, Pxsedang, Pxkaya);
        System.out.println("Masuk kategori = "+hasil);

    }    
    
    //data
    static String b[][] = {
            {"9","kayu bakar", "ubin","3", "sedang"},
            {"10","gas LPG", "ubin","2", "sedang"},
            {"15","gas LPG", "plester","2", "sedang"},
            {"30","gas LPG", "ubin","4", "kaya"},
            {"16","kompor listrik","ubin", "3", "kaya"},
            {"25","gas LPG", "ubin","5", "kaya"},
            {"9","gas LPG", "plester","0.5", "miskin"},
            {"8","kayu bakar", "tanah","1", "miskin"},
            {"10","kayu bakar", "tanah","2", "miskin"},
            {"14","gas LPG", "tanah","1", "miskin"},
        };    
    
    //mencari peluang status
    public static double countStatus(String kategori){
        double jkategori = 0;
        for (int i = 0; i < 10; i++) {
            int j = 4;
            if (b[i][j] == kategori) {
                jkategori+=1;
            }
        }
        double P = jkategori/10;
        return P;
    }
    
    //mencari peluang jenis lantai
    public static double countLantai(String status, String kategori){
        double jstatus = 0, jkategori = 0;
        for (int i = 0; i < 10; i++) {
            int k = 2;
            int j = 4;
            if (b[i][j] == kategori) {
                jkategori+=1;
                if (b[i][k] == status) {
                    jstatus+=1;
                }
            }
        }
        return jstatus/jkategori;
        
    }
    
    //mencari peluang bahan bakar
    public static double countBB(String status, String kategori){
        double jstatus = 0, jkategori = 0;
        for (int i = 0; i < 10; i++) {
            int k = 1;
            int j = 4;
            if (b[i][j] == kategori) {
                jkategori+=1;
                if (b[i][k] == status) {
                    jstatus+=1;
                }
            }
        }
        return jstatus/jkategori;
        
    }
    
    //mencari peluang luas bangunan
    public static double luasBangunan(double x ,String kategori){
        double jluas = 0, jkategori = 0, sigmaxi = 0;
        for (int i = 0; i < 10; i++) {
            int j = 4;
            if (b[i][j] == kategori) {
                jkategori+=1;
                jluas+= Double.parseDouble(b[i][0]);
                sigmaxi+= Math.pow(Double.parseDouble(b[i][0]), 2);
            }
        }
        double mean = jluas/jkategori;
        double variansi = (jkategori * sigmaxi - Math.pow(jluas, 2)) / (jkategori*(jkategori - 1));
        double Pluas = (1/(Math.sqrt(2*3.14*variansi)))*Math.exp(-(Math.pow((x-mean), 2)/(2*variansi)));
        return Pluas;
    }
    
    //mencari peluang frekuesi daging
     public static double daging(double x ,String kategori){
        double jluas = 0, jkategori = 0, sigmaxi = 0;
        for (int i = 0; i < 10; i++) {
            int j = 4;
            if (b[i][j] == kategori) {
                jkategori+=1;
                jluas+= Double.parseDouble(b[i][3]);
                sigmaxi+= Math.pow(Double.parseDouble(b[i][3]), 2);
            }
        }
        double mean = jluas/jkategori;
        double variansi = (jkategori * sigmaxi - Math.pow(jluas, 2)) / (jkategori*(jkategori - 1));
        double Pdaging = (1/(Math.sqrt(2*3.14*variansi)))*Math.exp(-(Math.pow((x-mean), 2)/(2*variansi)));
        return Pdaging;
    }
     
     //mencari nilai peluang tiap kategori
     public static double P(double Pkategori, double Pluas, double Pbb, double Plantai, double Pdaging){
         return Pkategori * Pluas * Pbb * Plantai * Pdaging;
     }
     
     //menentukan kategori
     public static String hasil(double x1, double x2, double x3){
        double[] nilai = {x1, x2, x3};
        double max = 0;
        for (int a = 0; a < nilai.length; a++) {
            if (nilai[a] > max) {
                max = nilai[a];
            }
        }
         if (max == x1) {
             return "miskin";
         }else if (max == x2) {
             return "sedang";
         }else  {
             return "kaya";
         }
         
     }
     
     public static void data(){
         System.out.println(" --------------------------------------------------------------------------------\n"
                           +" |Luas Bangunan\t Bahan Bakar\t Jenis Lantai\t Frek Makan Daging\t Kategori|\n"
                           +" |"+b[0][0]+"\t\t "+b[0][1]+"\t "+b[0][2]+"\t\t "+b[0][3]+"\t\t\t "+b[0][4]+"  |\n"
                           +" |"+b[1][0]+"\t\t "+b[1][1]+"\t "+b[1][2]+"\t\t "+b[1][3]+"\t\t\t "+b[1][4]+"  |\n"
                           +" |"+b[2][0]+"\t\t "+b[2][1]+"\t "+b[2][2]+"\t\t "+b[2][3]+"\t\t "+b[2][4]+"  |\n"
                           +" |"+b[3][0]+"\t\t "+b[3][1]+"\t "+b[3][2]+"\t\t "+b[3][3]+"\t\t\t "+b[3][4]+"\t |\n"
                           +" |"+b[4][0]+"\t\t "+b[4][1]+"\t "+b[4][2]+"\t\t "+b[4][3]+"\t\t\t "+b[4][4]+"\t |\n"
                           +" |"+b[5][0]+"\t\t "+b[5][1]+"\t "+b[5][2]+"\t\t "+b[5][3]+"\t\t\t "+b[5][4]+"\t |\n"
                           +" |"+b[6][0]+"\t\t "+b[6][1]+"\t "+b[6][2]+"\t\t "+b[6][3]+"\t\t "+b[6][4]+"  |\n"
                           +" |"+b[7][0]+"\t\t "+b[7][1]+"\t "+b[7][2]+"\t\t "+b[7][3]+"\t\t\t "+b[7][4]+"  |\n"
                           +" |"+b[8][0]+"\t\t "+b[8][1]+"\t "+b[8][2]+"\t\t "+b[8][3]+"\t\t\t "+b[8][4]+"  |\n"
                           +" |"+b[9][0]+"\t\t "+b[9][1]+"\t "+b[9][2]+"\t\t "+b[9][3]+"\t\t\t "+b[9][4]+"  |\n"
                           +" |?\t\t ?\t\t ?\t\t ?\t\t\t ?\t |\n");
     }
    
}
