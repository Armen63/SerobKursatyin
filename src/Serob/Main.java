package Serob;

import java.util.*;

/**
 * Created by Armen on 3/3/2017.
 */
public class Main {
    public static void main(String[] args) {
    double n0 = 7, lambda = 5, k = 6, m = Math.pow(2, k), h = (int) Math.pow(2, k - 2), S = 0;

        LinkedList<Integer> n = new LinkedList<Integer>();
        LinkedList<Double> r = new LinkedList<Double>();
        LinkedList<Double> t = new LinkedList<Double>();     // R(a,b)
        LinkedList<Double> tau = new LinkedList<Double>();   // Exp(lambda)
        LinkedList<Double> tt = new LinkedList<Double>();    // N(nyu,sigma)(lambda)

        n.add((int) n0);
                //n[i] u r[i] voroshum
        for(int i = 0; i <= h; ++i){
            n.add(i + 1, (int) (n.get(i) * lambda % m));
            System.out.print("n[" + i + "] = " + n.get(i)+ "   ");

            r.add(i, n.get(i + 1)/m);
            System.out.println("r[" + (i+1) +"] = " + r.get(i));

            if(i < 12)
                S+=r.get(i);
        }

        System.out.println();

        int a = 2, b = 10, nyu = 5, sigma = 2;
        double sum = 0, spasmanLambda = 0.1;

        for(int i = 0; i < k; ++i){
            t.add(i, a + (b - a)* r.get(i));
            System.out.print("t[" + (i + 1) + "] = " + t.get(i) + "   ");
            sum += t.get(i);
            tau.add(i, -Math.log(r.get(i))/spasmanLambda);
            System.out.println("tau["+ (i+1) + "] = " + tau.get(i));

        }
        System.out.println("sum[t(i)] =" + sum);

        LinkedList<Double> sArray = new LinkedList<Double>();
        LinkedList<Integer> sumOfArrays = new LinkedList<>();

        Random rand = new Random();


        for(int i = 0; i < 6; ++i) {
            double sum1 = 0;
            sArray.removeAll(sArray);
            for (int j = 0; j < 12; ++j) {
                int index = rand.nextInt(16) + 1;
                sArray.add(r.get(index));
                sum1 += sArray.get(j);
            }
            int castSum = (int) sum1;
            sumOfArrays.add(castSum);
        }

        System.out.println();

        for(int i = 0; i < sumOfArrays.size(); ++i){
            System.out.println("s[" + (i+1) + "] = " + sumOfArrays.get(i));
        }
    }
}