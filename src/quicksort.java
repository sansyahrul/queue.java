import java.sql.*;
import java.util.Scanner;

class Student {
    private String nama;
    private int nim;
    private String jurusan;

    public Student(String nama, int nim, String jurusan) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    // Getters and Setters

    public String getName() {
        return nama;
    }

    public void setName(String name) {
        this.nama = name;
    }

    public int getNim() {
        return nim;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setjurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}



public class CRUD {

    public static void main(String[] args) {
        CRUD studentDAO = new CRUD();
        Scanner userInput = new Scanner (System.in);
        String pilihanUser, namaBaru, jurusanBaru;
        int nimBaru;
        boolean isLanjutkan = true;
        studentDAO.connect();

        while(isLanjutkan) {
            System.out.println("\nDatabase Mahasiswa");
            System.out.println("1.\tLihat seluruh mahasiswa");
            System.out.println("2.\tTambah data mahasiswa");
            System.out.println("3.\tUbah data mahasiswa");
            System.out.println("4.\tHapus data mahasiswa");

            System.out.print("Pilihan Anda: ");
            pilihanUser = userInput.next();

            switch (pilihanUser) {
                case "1":
                    System.out.println("\n======================");
                    System.out.println("LIST SELURUH MAHASISWA");
                    System.out.println("======================\n");

                    System.out.println("|Id|\tNama\t|\tNIM       \t|\tJurusan\t");

                    // Mendapatkan data siswa berdasarkan ID
                    int id = 3;
                    while (isLanjutkan){
                        id++;
                        Student retrievedStudent = studentDAO.getStudent(id);
                        if (retrievedStudent != null) {
                            System.out.println("| "+ id + " |\t" + retrievedStudent.getName() + "\t|\t" + retrievedStudent.getNim() + "\t|\t" + retrievedStudent.getJurusan());
                        } else{
                            isLanjutkan = false;
                        }
                    }
//
                    break;
                case "2":
                    System.out.println("\n=====================");
                    System.out.println("TAMBAH DATA MAHASISWA");
                    System.out.println("=====================");
                    // Menambahkan data siswa baru
                    System.out.print("Masukan nama mahasiswa baru: ");
                    namaBaru = userInput.next();
                    System.out.print("Masukan nim mahasiswa baru: ");
                    nimBaru = userInput.nextInt();
                    System.out.print("Masukan jurusan mahasiswa baru: ");
                    jurusanBaru = userInput.next();
                    Student newStudent = new Student(namaBaru, nimBaru, jurusanBaru);
                    studentDAO.addStudent(newStudent);
                    break;
                case "3":
                    System.out.println("\n===================");
                    System.out.println("UBAH DATA MAHASISWA");
                    System.out.println("===================");

                    System.out.println("|Id|\tNama\t|\tNIM       \t|\tJurusan\t");

                    // Mendapatkan data siswa berdasarkan ID
                    int id2 = 3;
                    while (isLanjutkan){
                        id2++;
                        Student retrievedStudent = studentDAO.getStudent(id2);
                        if (retrievedStudent != null) {
                            System.out.println("| "+ id2 + " |\t" + retrievedStudent.getName() + "\t|\t" + retrievedStudent.getNim() + "\t|\t" + retrievedStudent.getJurusan());
                        } else{
                            isLanjutkan = false;
                        }
                    }
                    System.out.print("Masukan id yang ingin diubah namanya: ");
                    int getId = userInput.nextInt();
                    System.out.print("Masukan nama baru: ");
                    namaBaru = userInput.next();
                    Student retrievedStudent = studentDAO.getStudent(getId);
                    if (retrievedStudent != null) {
                        retrievedStudent.setName(namaBaru);
                        studentDAO.updateStudent(getId, retrievedStudent);
                    } else {
                        System.err.println("Masukan Id yang benar!");
                    }
                    break;
                case "4":
                    System.out.println("\n====================");
                    System.out.println("HAPUS DATA MAHASISWA");
                    System.out.println("====================");
                    System.out.println("|Id|\tNama\t|\tNIM       \t|\tJurusan\t");

                    // Mendapatkan data siswa berdasarkan ID
                    int id3 = 3;
                    while (isLanjutkan){
                        id3++;
                        Student retrievedStudent2 = studentDAO.getStudent(id3);
                        if (retrievedStudent2 != null) {
                            System.out.println("| "+ id3 + " |\t" + retrievedStudent2.getName() + "\t|\t" + retrievedStudent2.getNim() + "\t|\t" + retrievedStudent2.getJurusan());
                        } else{
                            isLanjutkan = false;
                        }
                    }
                    System.out.print("Masukan id yang ingin dihapus datanya: ");
                    int getId2 = userInput.nextInt();
                    studentDAO.deleteStudent(getId2);

                    break;
                default:
                    System.err.println("\nInput anda tidak ditemukan\nSilakan masukan input yang benar");
            }

            System.out.print("Apakah mau dilanjutkan (y/n): ");
            pilihanUser = userInput.next();

            isLanjutkan = pilihanUser.equalsIgnoreCase("y");
        }



        // Menghapus data siswa
//            studentDAO.deleteStudent(1);

        studentDAO.close();
    }


    private Connection connection;

    // Membuat koneksi ke database
    public void connect() {
        try {

            String url = "jdbc:ucanaccess://C:/Users/INFINIX/Documents/tblMhs.accdb";
            connection = DriverManager.getConnection(url);
//            System.out.println("Connected to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menutup koneksi ke database
    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.err.println("Program Berakhir");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menambahkan data siswa baru ke database
    public void addStudent(Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO tblMhs (Nama, NIM, Jurusan) VALUES (?, ?, ?)"
            );
            statement.setString(1, student.getName());
            statement.setInt(2, student.getNim());
            statement.setString(3, student.getJurusan());
            statement.executeUpdate();

            System.out.println("Berhasil!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mengambil data siswa berdasarkan ID
    public Student getStudent(int id) {
        Student student = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM tblMhs WHERE id = ?"
            );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student = new Student(
                        resultSet.getString("Nama"),
                        resultSet.getInt("NIM"),
                        resultSet.getString("Jurusan")
                );

            } else {
                System.out.println("Akhir dari database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    // Memperbarui data siswa berdasarkan ID
    public void updateStudent(int id, Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE tblMhs SET Nama = ?, NIM = ?, Jurusan = ? WHERE id = ?"
            );
            statement.setString(1, student.getName());
            statement.setInt(2, student.getNim());
            statement.setString(3, student.getJurusan());
            statement.setInt(4, id);
            statement.executeUpdate();
            System.out.println("Berhasil Di Update!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menghapus data siswa berdasarkan ID
    public void deleteStudent(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM tblMhs WHERE id = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Berhasil Di Delete");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}