class Antrian{
    private int ukuran;
    private long[] antrian;
    private int belakang;
    private int jumItem;
    private int depan;

    public Antrian (int s){
        ukuran = s;
        antrian = new long[ukuran];
        depan = 0;
        belakang = -1;
        jumItem = 0;
    }

    public void masuk(long j){
        if (!isFull()){
            throw new RuntimeException("Antrian penuh");

        }
        antrian[++belakang] = j;
        jumItem++;
    }


    public long keluar(){
        if (isEmpty()) {
            throw new RuntimeException("Antrian kosong");
        }

        long temp = antrian[depan];
        for (int i = 0; i < jumItem; i++) {
            antrian[i] = antrian[i + 1];
        }
        jumItem--;
        belakang--;
        return temp;
    }

    public long peekDepan(){
        return antrian[depan];
    }

    public boolean isEmpty(){
        return (jumItem==0);
    }

    public boolean isFull(){
        return (belakang==ukuran-1);
    }

    public int ukuran(){
        return jumItem;
    }

    public void lihat(){

        for (int i=0; i<jumItem; i++)
            System.out.print(antrian[i]+" ");
        System.out.println(" ");

        }

    }


public class queue {

    public static void main(String[] args){
        Antrian antrian = new Antrian(5);
        try {
            antrian.masuk(13);
            antrian.lihat();
            antrian.masuk(32);
            antrian.lihat();
            antrian.masuk(45);
            antrian.lihat();
            antrian.masuk(45);
            antrian.lihat();
            antrian.masuk(12);
            antrian.lihat();
            antrian.masuk(45);
            antrian.lihat();
            antrian.masuk(12);
            antrian.lihat();
            System.out.println("yang diambil dari antrian = " + antrian.keluar());
            antrian.lihat();
            System.out.println("yang diambil dari antrian = " + antrian.keluar());
            antrian.lihat();
            System.out.println("yang diambil dari antrian = " + antrian.keluar());
            antrian.lihat();
            System.out.println("yang diambil dari antrian = " + antrian.keluar());
            antrian.lihat();
            antrian.masuk(99); // Mencoba masukkan saat antrian penuh
            antrian.keluar(); // Mencoba mengeluarkan saat antrian kosong
        }catch (RuntimeException e) {
            System.out.println("antrian penuh ");
        }

    }
}