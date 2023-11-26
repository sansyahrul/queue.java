# Inisialisasi antrian
        antrian <- BuatAntrian()

        # Fungsi untuk mengecek apakah antrian kosong
        Fungsi isEmpty():
        Jika antrian.Kosong():
        Mengembalikan True
        Jika tidak:
        Mengembalikan False

        # Fungsi untuk menambahkan elemen ke antrian
        Fungsi enqueue(nilai):
        antrian.TambahElemen(nilai)

        # Fungsi untuk menghapus elemen dari antrian dan mengembalikan nilainya
        Fungsi dequeue():
        Jika isEmpty():
        Mengembalikan "Antrian kosong"
        Jika tidak:
        nilai <- antrian.AmbilElemenDepan()
        Mengembalikan nilai

        # Fungsi untuk melihat nilai elemen pertama di antrian tanpa menghapusnya
        Fungsi peek():
        Jika isEmpty():
        Mengembalikan "Antrian kosong"
        Jika tidak:
        nilai <- antrian.AmbilElemenDepan()
        antrian.TambahElemenDepan(nilai)  # Mengembalikan elemen ke antrian setelah mengambilnya
        Mengembalikan nilai
