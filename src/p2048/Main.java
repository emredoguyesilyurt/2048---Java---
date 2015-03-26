/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2048;

import java.util.*;

public class Main {

    private static int[][] Board;
    private static int skor = 0;
    // soruda verilen fonksiyon
    public static void displayBoard(){
        
        for (int i=0; i<4; i++){
            
            for (int j=0; j<4; j++){
                
                // Board[i][j] elemanı sıfıra eşitse orada 2'nin katı bir sayı yoktur
                if(Board[i][j] == 0)
                    System.out.print(". ");
                else
                    System.out.print(Board[i][j] + " ");
            }
            
            System.out.println();
        }
    }
    
    // oyunun bitme durumları:
    // 1. Board array'inde 2048'e eşit bir eleman varsa oyun bitmiştir

    public static boolean gameEnds(){
        
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++)
                if(Board[i][j]==2048) return true;

        // hiç bir eleman 2048 değilse, oyun bitmemiştir
        return false;
    }
    
    // 2. Board array'indeki her eleman için, o elemanın komşularından hiçbiri o elemana eşit değilse, başka hamle yapılamaz. oyun bitmiştir
    public static boolean gameEnds2(){

        // eğer herhangi bir eleman sıfıra eşitse, oyun bitmemiştir
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++)
                if(Board[i][j]==0)
                    return false;
        
        for(int i=0; i<4; i++){
            // en üstteki satır için
            if(i==0){
                for(int j=0; j<4; j++){
                    // en soldaki eleman için
                    if(j==0){
                        // elemanın sağındaki ve altındakiyle aynı olup olmadığına bak
                        if(Board[i][j]==Board[i][j+1] || Board[i][j]==Board[i+1][j])
                            return false;
                    }
                    // en sağdaki eleman için
                    else if(j==3){
                        // elemanın altındaki ve solundakiyle aynı olup olmadığına bak
                        if(Board[i][j]==Board[i+1][j] || Board[i][j]==Board[i][j-1])
                            return false;
                    }
                    // ortadaki iki eleman için
                    else {
                        // elemanın altındaki, sağındaki ve solundakiyle eşit olup olmadığına bak
                        if(Board[i][j]==Board[i+1][j] || Board[i][j]==Board[i][j+1] || Board[i][j]==Board[i][j-1])
                            return false;
                    }
                }
            }
            // en alttaki satır için
            else if(i==3){
                for(int j=0; j<4; j++){
                    // en soldaki eleman için
                    if(j==0){
                        // elemanın üstündeki ve sağındakiyle eşit olup olmadığına bak
                        if(Board[i][j]==Board[i-1][j] || Board[i][j]==Board[i][j+1])
                            return false;
                    }
                    // en sağdaki eleman için
                    else if(j==3){
                        // elemanın üstündeki ve solundakiyle eşit olup olmadığına bak
                        if(Board[i][j]==Board[i-1][j] || Board[i][j]==Board[i][j-1])
                            return false;
                    }
                    // ortadaki iki eleman için
                    else {
                        // elemanın solundaki, sağındaki ve üstündeki elemana eşit olup olmadığına bak
                        if(Board[i][j]==Board[i][j-1] || Board[i][j]==Board[i][j+1] || Board[i][j]==Board[i-1][j])
                            return false;
                    }
                }
            }
            else {
                // ortadaki iki satır için
                for(int j=0; j<4; j++){
                    // en soldaki eleman için
                    if(j==0){
                        // elemanın üstündeki, altındaki ve sağındakiyle eşitliğine bak
                        if(Board[i][j]==Board[i-1][j] || Board[i][j]==Board[i+1][j] || Board[i][j]==Board[i][j+1])
                            return false;
                    }
                    // en sağdaki eleman için
                    else if(j==3){
                        // elemanın üstündeki, altındaki ve solundakiyle eşitliğine bak
                        if(Board[i][j]==Board[i-1][j] || Board[i][j]==Board[i+1][j] || Board[i][j]==Board[i][j-1])
                            return false;
                    }
                    // ortadaki iki eleman için
                    else {
                        // elemanın üstündeki, altındaki, sağındaki ve solundakiyle eşitliğine bak
                        if(Board[i][j]==Board[i-1][j] || Board[i][j]==Board[i+1][j] || Board[i][j]==Board[i][j+1] || Board[i][j]==Board[i][j-1])
                            return false;
                    }
                }
            }
        }
        
        return true;
        
    }
    
    
    public static void moveLeft(){
        
        System.out.println("Move left");

        // eğer bu hamlede hiç bir sayı yer değiştirmezse, oyun tahtasına yeni bir eleman eklemeyeceğiz
        boolean movedThisTurn = false;
        
        // moveLeft fonksiyonu için Board[0], Board[1], Board[2] ve Board[3] satırlarını tek tek hesapla
        for(int i=0; i<4; i++){
        
        // her satır için:
            // bütün dolu kolonları en sola diz, ...
            for(int a=1; a<4; a++){
                if(Board[i][a] != 0){
                    for(int b=0; b<a; b++){
                        if(Board[i][b]==0){
                            Board[i][b] = Board[i][a];
                            Board[i][a] = 0;
                            b = 4;
                        
                            // herhangi bir elemanın yeri değiştiyse, bu el hareket etmişizdir
                            movedThisTurn = true;
                        }
                    }
                }
            }
            // ... daha sonra sıradaki işlemleri yap:
            
            // ilk kolon:
                // ilk kolon sıfırsa bir şey yapma
            if(Board[i][0] == 0) ;
            else {
            // ilk kolon sıfır değilse:
                // ikinci kolon sıfırsa bir şey yapma
                if(Board[i][1] == 0) ;
                else {
                // ikinci kolon sıfır değilse:
                    // ikinci kolon ilk kolona eşit değilse: bir şey yapma
                    if(Board[i][0] != Board[i][1]) ;
                    else if(Board[i][0] == Board[i][1]) {
                    // ikinci kolon ilk kolona eşitse: ilk kolonu ikiyle çarp ve o satırdaki üçüncü ve dördüncü kolonları birer sola kaydır
                        
                        Board[i][0] *= 2;
                        Board[i][1] = Board[i][2];
                        Board[i][2] = Board[i][3];
                        Board[i][3] = 0;
                        skor += Board[i][0];
                        // herhangi bir eleman değiştiyse, bu el hareket etmişizdir
                        movedThisTurn = true;
                    }
                }
            }
            
            // ikinci kolon:
                // ikinci kolon sıfırsa bir şey yapma
            if(Board[i][1] == 0) ;
            else {
            // ikinci kolon sıfır değilse:
                // üçüncü kolon sıfırsa bir şey yapma
                if(Board[i][2] == 0) ;
                else {
                // üçüncü kolon sıfır değilse:
                    // üçüncü kolon ikinci kolona eşit değilse: bir şey yapma
                    if(Board[i][2] != Board[i][1]) ;
                    else {
                    // üçüncü kolon ikinci kolona eşitse: ikinci kolonu ikiyle çarp ve dördüncü kolonu bir sola kaydır
                        Board[i][1] *= 2;
                        Board[i][2] = Board[i][3];
                        Board[i][3] = 0;
                        skor += Board[i][1];
                        // herhangi bir eleman değiştiyse, bu el hareket etmişizdir
                        movedThisTurn = true;
                    }
                }
            }
            
            // üçüncü kolon:
                // üçüncü kolon sıfırsa bir şey yapma
            if(Board[i][2] == 0) ;
            else {
            // üçüncü kolon sıfır değilse:
                // dördüncü kolon sıfırsa bir şey yapma
                if(Board[i][3] == 0) ;
                else {
                // dördüncü kolon sıfır değilse:
                    // dördüncü kolon üçüncü kolona eşit değilse: bir şey yapma
                    if(Board[i][3] != Board[i][2]) ;
                    else {
                    // dördüncü kolon üçüncüye eşitse: üçüncüyü ikiyle çarp, dördüncü kolonu sıfıra eşitle
                        Board[i][2] *= 2;
                        Board[i][3] = 0;
                        skor += Board[i][2];
                        // herhangi bir eleman değiştiyse, bu el hareket etmişizdir
                        movedThisTurn = true;

                    }
                }
            }

        }
        
        
        // en sonda rastgele bir sayı oluşturup, rastgele boş bir koordinata yerleştir
        // eğer herhangi bir eleman yer değiştirdiyse rastgele sayıyı tahtaya ekle
        if(movedThisTurn){
            Random rand = new Random();

            int rand11 = rand.nextInt(4) + 0;
            int rand12 = rand.nextInt(4) + 0;
            int rand1 = rand.nextInt(2) + 1;
            rand1 *= 2;

            // rastgele koordinatın sıfıra eşit olup olmadığına bak
            boolean isZero = Board[rand11][rand12] != 0;

            // koordinat sıfıra eşit olmadığı sürece tekrar rastgele koordinat seç
            while(isZero){
                rand11 = rand.nextInt(4) + 0; 
                rand12 = rand.nextInt(4) + 0;

                // isZero boolean'ını tekrar hesapla
                isZero = Board[rand11][rand12] != 0;
            }

            Board[rand11][rand12] = rand1;
        }
    }
    
    public static void moveRight(){
        System.out.println("Move right");

        // eğer bu hamlede hiç bir sayı yer değiştirmezse, oyun tahtasına yeni bir eleman eklemeyeceğiz
        boolean movedThisTurn = false;
        
        // Right fonksiyonu için Board[0], Board[1], Board[2] ve Board[3] satırlarını tek tek hesapla
        for(int i=0; i<4; i++){
        
        // her satır için:
            // bütün dolu kolonları en sağa diz, ...
            for(int a=2; a>-1; a--){
                if(Board[i][a] != 0){
                    for(int b=3; b>a; b--){
                        if(Board[i][b]==0){
                            Board[i][b] = Board[i][a];
                            Board[i][a] = 0;
                            b = 0;
                        
                            // herhangi bir elemanın yeri değiştiyse, bu el hareket etmişizdir
                            movedThisTurn = true;
                        }
                    }
                }
            }
            // ... daha sonra sıradaki işlemleri yap:
            
            // ilk kolon:
                // dördüncü kolon sıfırsa bir şey yapma
            if(Board[i][3] == 0) ;
            else {
            // dördüncü kolon sıfır değilse:
                // üçüncü kolon sıfırsa bir şey yapma
                if(Board[i][2] == 0) ;
                else {
                // üçüncü kolon sıfır değilse:
                    // üçüncü kolon dördüncü kolona eşit değilse: bir şey yapma
                    if(Board[i][3] != Board[i][2]) ;
                    else if(Board[i][3] == Board[i][2]) {
                    // üçüncü kolon dördüncü kolona eşitse: dördüncü kolonu ikiyle çarp ve o satırdaki üçüncü ve ikinci kolonları birer sağa kaydır
                        
                        Board[i][3] *= 2;
                        Board[i][2] = Board[i][1];
                        Board[i][1] = Board[i][0];
                        Board[i][0] = 0;
                        skor += Board[i][3];
                        // herhangi bir eleman değiştiyse, bu el hareket etmişizdir
                        movedThisTurn = true;
                    }
                }
            }
            
            // üçüncü kolon:
                // üçüncü kolon sıfırsa bir şey yapma
            if(Board[i][2] == 0) ;
            else {
            // üçüncü kolon sıfır değilse:
                // ikinci kolon sıfırsa bir şey yapma
                if(Board[i][1] == 0) ;
                else {
                // ikinci kolon sıfır değilse:
                    // ikinci kolon üçüncü kolona eşit değilse: bir şey yapma
                    if(Board[i][1] != Board[i][2]) ;
                    else {
                    // ikinci kolon üçüncü kolona eşitse: üçüncü kolonu ikiyle çarp ve birinci kolonu bir sağa kaydır
                        Board[i][2] *= 2;
                        Board[i][1] = Board[i][0];
                        Board[i][0] = 0;
                        skor += Board[i][2];
                        // herhangi bir eleman değiştiyse, bu el hareket etmişizdir
                        movedThisTurn = true;
                    }
                }
            }
            
            // ikinci kolon:
                // ikinci kolon sıfırsa bir şey yapma
            if(Board[i][1] == 0) ;
            else {
            // ikinci kolon sıfır değilse:
                // birinci kolon sıfırsa bir şey yapma
                if(Board[i][0] == 0) ;
                else {
                // birinci kolon sıfır değilse:
                    // birinci kolon ikinci kolona eşit değilse: bir şey yapma
                    if(Board[i][0] != Board[i][1]) ;
                    else {
                    // birinci kolon ikinciye eşitse: ikinciyi ikiyle çarp, birinci kolonu sıfıra eşitle
                        Board[i][1] *= 2;
                        Board[i][0] = 0;
                        skor += Board[i][1];
                        // herhangi bir eleman değiştiyse, bu el hareket etmişizdir
                        movedThisTurn = true;

                    }
                }
            }

        }
        
        
        // en sonda rastgele bir sayı oluşturup, rastgele boş bir koordinata yerleştir
        // eğer herhangi bir eleman yer değiştirdiyse rastgele sayıyı tahtaya ekle
        if(movedThisTurn){
            Random rand = new Random();

            int rand11 = rand.nextInt(4) + 0;
            int rand12 = rand.nextInt(4) + 0;
            int rand1 = rand.nextInt(2) + 1;
            rand1 *= 2;

            // rastgele koordinatın sıfıra eşit olup olmadığına bak
            boolean isZero = Board[rand11][rand12] != 0;

            // koordinat sıfıra eşit olmadığı sürece tekrar rastgele koordinat seç
            while(isZero){
                rand11 = rand.nextInt(4) + 0; 
                rand12 = rand.nextInt(4) + 0;

                // isZero boolean'ını tekrar hesapla
                isZero = Board[rand11][rand12] != 0;
            }

            Board[rand11][rand12] = rand1;
        }
    }
    
    public static void moveUp(){
        System.out.println("Move up");

        // eğer bu hamlede hiç bir sayı yer değiştirmezse, oyun tahtasına yeni bir eleman eklemeyeceğiz
        boolean movedThisTurn = false;
        
        // moveUp fonksiyonu için Board[][0], Board[][1], Board[][2] ve Board[][3] kolonlarını tek tek hesapla
        for(int i=0; i<4; i++){
        
        // her satır için:
            // bütün dolu satırları en üste diz, ...
            for(int a=1; a<4; a++){
                if(Board[a][i] != 0){
                    for(int b=0; b<a; b++){
                        if(Board[b][i]==0){
                            Board[b][i] = Board[a][i];
                            Board[a][i] = 0;
                            b = 4;
                        
                            // herhangi bir elemanın yeri değiştiyse, bu el hareket etmişizdir
                            movedThisTurn = true;
                        }
                    }
                }
            }
            // ... daha sonra sıradaki işlemleri yap:
            
            // ilk satır:
                // ilk satır sıfırsa bir şey yapma
            if(Board[0][i] == 0) ;
            else {
            // ilk satır sıfır değilse:
                // ikinci satır sıfırsa bir şey yapma
                if(Board[1][i] == 0) ;
                else {
                // ikinci satır sıfır değilse:
                    // ikinci satır ilk satıra eşit değilse: bir şey yapma
                    if(Board[0][i] != Board[1][i]) ;
                    else if(Board[0][i] == Board[1][i]) {
                    // ikinci satır ilk satıra eşitse: ilk satırı ikiyle çarp ve o kolondaki üçüncü ve dördüncü satırları birer üste kaydır
                        
                        Board[0][i] *= 2;
                        Board[1][i] = Board[2][i];
                        Board[2][i] = Board[3][i];
                        Board[3][i] = 0;
                        skor += Board[0][i];
                        // herhangi bir eleman değiştiyse, bu el hareket etmişizdir
                        movedThisTurn = true;
                    }
                }
            }
            
            // ikinci satır:
                // ikinci satır sıfırsa bir şey yapma
            if(Board[1][i] == 0) ;
            else {
            // ikinci satır sıfır değilse:
                // üçüncü satır sıfırsa bir şey yapma
                if(Board[2][i] == 0) ;
                else {
                // üçüncü satır sıfır değilse:
                    // üçüncü satır ikinci satıra eşit değilse: bir şey yapma
                    if(Board[2][i] != Board[1][i]) ;
                    else {
                    // üçüncü satır ikinci satıra eşitse: ikinci satırı ikiyle çarp ve dördüncü satırı bir sola kaydır
                        Board[1][i] *= 2;
                        Board[2][i] = Board[3][i];
                        Board[3][i] = 0;
                        skor += Board[1][i];
                        // herhangi bir eleman değiştiyse, bu el hareket etmişizdir
                        movedThisTurn = true;
                    }
                }
            }
            
            // üçüncü satır:
                // üçüncü satır sıfırsa bir şey yapma
            if(Board[2][i] == 0) ;
            else {
            // üçüncü satır sıfır değilse:
                // dördüncü satır sıfırsa bir şey yapma
                if(Board[3][i] == 0) ;
                else {
                // dördüncü satır sıfır değilse:
                    // dördüncü satır üçüncü satıra eşit değilse: bir şey yapma
                    if(Board[3][i] != Board[2][i]) ;
                    else {
                    // dördüncü satır üçüncüye eşitse: üçüncüyü ikiyle çarp, dördüncü satırı sıfıra eşitle
                        Board[2][i] *= 2;
                        Board[3][i] = 0;
                        skor += Board[2][i];
                        // herhangi bir eleman değiştiyse, bu el hareket etmişizdir
                        movedThisTurn = true;

                    }
                }
            }

        }
        
        
        // en sonda rastgele bir sayı oluşturup, rastgele boş bir koordinata yerleştir
        // eğer herhangi bir eleman yer değiştirdiyse rastgele sayıyı tahtaya ekle
        if(movedThisTurn){
            Random rand = new Random();

            int rand11 = rand.nextInt(4) + 0;
            int rand12 = rand.nextInt(4) + 0;
            int rand1 = rand.nextInt(2) + 1;
            rand1 *= 2;

            // rastgele koordinatın sıfıra eşit olup olmadığına bak
            boolean isZero = Board[rand11][rand12] != 0;

            // koordinat sıfıra eşit olmadığı sürece tekrar rastgele koordinat seç
            while(isZero){
                rand11 = rand.nextInt(4) + 0; 
                rand12 = rand.nextInt(4) + 0;

                // isZero boolean'ını tekrar hesapla
                isZero = Board[rand11][rand12] != 0;
            }

            Board[rand11][rand12] = rand1;
        }
    }
    
    public static void moveDown(){
        System.out.println("Move down");
        
        // eğer bu hamlede hiç bir sayı yer değiştirmezse, oyun tahtasına yeni bir eleman eklemeyeceğiz
        boolean movedThisTurn = false;
        
        // Down fonksiyonu için Board[][0], Board[][1], Board[][2] ve Board[][3] kolonlarını tek tek hesapla
        for(int i=0; i<4; i++){
        
        // her kolon için:
            // bütün dolu satırları en alta diz, ...
            for(int a=2; a>-1; a--){
                if(Board[a][i] != 0){
                    for(int b=3; b>a; b--){
                        if(Board[b][i]==0){
                            Board[b][i] = Board[a][i];
                            Board[a][i] = 0;
                            b = 0;
                        
                            // herhangi bir elemanın yeri değiştiyse, bu el hareket etmişizdir
                            movedThisTurn = true;
                        }
                    }
                }
            }
            // ... daha sonra sıradaki işlemleri yap:
            
            // ilk satır:
                // dördüncü satır sıfırsa bir şey yapma
            if(Board[3][i] == 0) ;
            else {
            // dördüncü satır sıfır değilse:
                // üçüncü satır sıfırsa bir şey yapma
                if(Board[2][i] == 0) ;
                else {
                // üçüncü satır sıfır değilse:
                    // üçüncü satır dördüncü satıra eşit değilse: bir şey yapma
                    if(Board[3][i] != Board[2][i]) ;
                    else if(Board[3][i] == Board[2][i]) {
                    // üçüncü satır dördüncü satıra eşitse: dördüncü satırı ikiyle çarp ve o kolondaki üçüncü ve ikinci satırları birer aşağı kaydır
                        
                        Board[3][i] *= 2;
                        Board[2][i] = Board[1][i];
                        Board[1][i] = Board[0][i];
                        Board[0][i] = 0;
                        skor += Board[3][i];
                        // herhangi bir eleman değiştiyse, bu el hareket etmişizdir
                        movedThisTurn = true;
                    }
                }
            }
            
            // üçüncü satır:
                // üçüncü satır sıfırsa bir şey yapma
            if(Board[2][i] == 0) ;
            else {
            // üçüncü satır sıfır değilse:
                // ikinci satır sıfırsa bir şey yapma
                if(Board[1][i] == 0) ;
                else {
                // ikinci satır sıfır değilse:
                    // ikinci satır üçüncü satıra eşit değilse: bir şey yapma
                    if(Board[1][i] != Board[2][i]) ;
                    else {
                    // ikinci satır üçüncü satıra eşitse: üçüncü satırı ikiyle çarp ve birinci satırı bir aşağı kaydır
                        Board[2][i] *= 2;
                        Board[1][i] = Board[0][i];
                        Board[0][i] = 0;
                        skor += Board[2][i];
                        // herhangi bir eleman değiştiyse, bu el hareket etmişizdir
                        movedThisTurn = true;
                    }
                }
            }
            
            // ikinci satır:
                // ikinci satır sıfırsa bir şey yapma
            if(Board[1][i] == 0) ;
            else {
            // ikinci satır sıfır değilse:
                // birinci satır sıfırsa bir şey yapma
                if(Board[0][i] == 0) ;
                else {
                // birinci satır sıfır değilse:
                    // birinci satır ikinci satıra eşit değilse: bir şey yapma
                    if(Board[0][i] != Board[1][i]) ;
                    else {
                    // birinci satır ikinciye eşitse: ikinciyi ikiyle çarp, birinci satırı sıfıra eşitle
                        Board[1][i] *= 2;
                        Board[0][i] = 0;
                        skor += Board[1][i];
                        // herhangi bir eleman değiştiyse, bu el hareket etmişizdir
                        movedThisTurn = true;

                    }
                }
            }

        }
        
        
        // en sonda rastgele bir sayı oluşturup, rastgele boş bir koordinata yerleştir
        // eğer herhangi bir eleman yer değiştirdiyse rastgele sayıyı tahtaya ekle
        if(movedThisTurn){
            Random rand = new Random();

            int rand11 = rand.nextInt(4) + 0;
            int rand12 = rand.nextInt(4) + 0;
            int rand1 = rand.nextInt(2) + 1;
            rand1 *= 2;

            // rastgele koordinatın sıfıra eşit olup olmadığına bak
            boolean isZero = Board[rand11][rand12] != 0;

            // koordinat sıfıra eşit olmadığı sürece tekrar rastgele koordinat seç
            while(isZero){
                rand11 = rand.nextInt(4) + 0; 
                rand12 = rand.nextInt(4) + 0;

                // isZero boolean'ını tekrar hesapla
                isZero = Board[rand11][rand12] != 0;
            }

            Board[rand11][rand12] = rand1;
        }
    }
    
    public static void main(String[] args) {
        
        // en başta Board array'inin bütün elemanlarını sıfıra eşitle
        Board = new int[4][4];
        
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++)
                Board[i][j] = 0;
        
        
        // oyunun başında koyacağımız iki random kareyi belirlemek için 4 koordinat belirleyelim
        Random rand = new Random();
        
        int rand11 = rand.nextInt(4) + 0;
        int rand12 = rand.nextInt(4) + 0;
        int rand21 = rand.nextInt(4) + 0; 
        int rand22 = rand.nextInt(4) + 0;
        
            //System.out.println(rand11 + " " + rand12);
            //System.out.println(rand21 + " " + rand22);
          
        // eğer ikinci random koordinat, ilk random koordinata eşitse, ikinciyi tekrar hesapla
        boolean isEqual = (rand11 == rand21) && (rand12 == rand22);

        // ikinci koord., birinci koord'a eşit olduğu sürece ikinci koordinatı tekrar hesapla
        while(isEqual){
            rand21 = rand.nextInt(4) + 0; 
            rand22 = rand.nextInt(4) + 0;
            
              //System.out.println(rand21 + " " + rand22);
            
            // isEqual boolean'ını tekrar hesapla
            isEqual = (rand11 == rand21) && (rand12 == rand22);
        }
        
        // rand1 ve rand2 koordinatlarına başlangıçta yerleştireceğin sayıları hesapla
        int rand1 = rand.nextInt(2) + 1;
        int rand2 = rand.nextInt(2) + 1;
        // rand1 ve rand2'yi ikiyle çarp. eğer rand1=2 ise yerleştireceğin sayı 4, rand1=1 ise yerleştireceğin sayı 2 olur.
        rand1 *= 2;
        rand2 *= 2;
        
        // bulduğumuz random koordinatlara göre Board array'ini ayarla
        Board[rand11][rand12] = rand1;
        Board[rand21][rand22] = rand2;
        

        // oyun için gereken değişkenleri tanımla
        boolean oyunBitti = false;
        String input = "";
        
        Scanner keyboard = new Scanner(System.in);

        //Board[1][0] = 512; Board[1][1] = 512; Board[1][2] = 1024; //Board[1][3] = 16;
        
        ////// OYUNA BAŞLA VE oyunBitti TRUE OLANA KADAR DEVAM ET
        while(!oyunBitti){
            
            displayBoard();    
            
            System.out.println("Skor : " + skor);
            System.out.print("Komutu giriniz : ");
            input = keyboard.nextLine();
            
            if(input.equals("L"))
                moveLeft();
            else if(input.equals("R"))
                moveRight();
            else if(input.equals("U"))
                moveUp();
            else moveDown();
            
            System.out.println();
            
            
            oyunBitti = gameEnds() || gameEnds2();

        
        }
        
        displayBoard();
        System.out.println("Skor : " + skor);
        System.out.print("Oyun Bitti. ");
        
        // 2048'e ulaşmışsa
        if(gameEnds()) System.out.println("Kazandın!");
        else if(gameEnds2()) System.out.println("Kaybettin!");
    }
    
}
