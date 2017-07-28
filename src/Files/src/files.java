import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;

/**
 * Created by ladislav on 12/03/17.
 */

public class files {

    private String fname;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public files(String fname) throws IOException,ClassNotFoundException{
        this.fname = fname;
        out = new ObjectOutputStream(new FileOutputStream(fname));
        in = new ObjectInputStream(new FileInputStream(fname));
        this.start();
    }

    public void write(Customer customer, String fname) throws IOException {
            out.writeObject(customer);
    }

    public  Customer read(String fname) throws IOException, ClassNotFoundException {
        return (Customer) in.readObject();
    }

    public void writeR(String fname, int n) throws IOException {
        RandomAccessFile out = new RandomAccessFile(fname, "rw");
        for (int i = 0; i < n; ++i) {
            out.writeInt(i);
            System.out.println("write: " + i);
        }
        out.close();
    }


    public void demoStreamCopy(String inputFile, String outputFile) throws IOException {
        FileInputStream in = new FileInputStream(inputFile);
        FileOutputStream out = new FileOutputStream(outputFile);

        int b = in.read(); // read byte of data
        while (b != -1) {

            out.write(b);
            b = in.read();
        }
        out.close();
        in.close();
    }

    public void start() throws IOException, ClassNotFoundException {
        Customer customer = new Customer("AAA", "BBB", 47);
        write(customer, fname);
        customer = new Customer("ZZZ", "WWW", 17);
        write(customer, fname);

        customer = read(fname);
        System.out.println("Customer: " + customer);
        customer = read(fname);
        System.out.println("Customer: " + customer);
    }

    public static void main(String[] args) {
        String input = "input.txt";
        String output = "output.txt";
        System.out.println(new File(".").getAbsolutePath());
        try {
            files demo = new files("customer.txt");
            demo.demoStreamCopy(input, output);
            demo.writeR(output, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}




