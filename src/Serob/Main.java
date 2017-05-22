package Serob;

import java.util.*;

public class Main {


    public static void paymanner(int i,double T, LinkedList<Double> kapuxi1, LinkedList<Double> kapuxi2,  LinkedList<Double> t,
                                 LinkedList<Double> azatmanJamank, LinkedList<Double> tSpasarkum,LinkedList<Double> tSpasum) {
        if (t.get(i) > azatmanJamank.get(i-1))
            kapuxi1.add(tSpasarkum.get(i));
        else
            kapuxi2.add(tSpasarkum.get(i));
    }

    public static void main(String[] args) {
        LinkedList<Double> n = new LinkedList<>();
        LinkedList<Double> r = new LinkedList<>();
        LinkedList<Double> t = new LinkedList<>();
        LinkedList<Double> tau = new LinkedList<>();
        LinkedList<Double> tSpasum = new LinkedList<>();
        LinkedList<Double> tSpasarkum = new LinkedList<>();
        LinkedList<Double> saxSpasarkumner = new LinkedList<>();
        LinkedList<Double> kapuxi2 = new LinkedList<>();
        LinkedList<Double> kapuxi1 = new LinkedList<>();
        LinkedList<Double> merjvacneriHav = new LinkedList<>(); // en verji keti havna p(m)
        double fixvacChap = 0;
        int mKapuxi = 2;
        double T = 120, nAstxanish = 130, pmGcik = 0, sigmaVerjin = 0, epsilyon = 0, zAlfa = 1.96;

        for (int aaaa = 1; aaaa <= nAstxanish; ++aaaa) {
            LinkedList<Double> sumOfArrays = new LinkedList<>();
            LinkedList<Double> azatmanJamank = new LinkedList<>();
            System.out.println("\n----------------------------------------- iteration " + aaaa + "-----------------------------------------\n");
            double n0 = 7, lambda = 5, k = 6, m = Math.pow(2, k), h = (int) Math.pow(2, k - 2);
            int a = 2, b = 10;
            double sumTau = 0, sumOfT = 0, spasmanLambda = 0.1;
            int nyu = 5, sigma = 2, count = 0;
            if (aaaa < 2) {
                n.add(n0);
                for (int i = 0; i <= T; ++i) {
                    n.add(i + 1, (n.get(i) * lambda % m));
                    System.out.print("n[" + i + "] = " + n.get(i) + "   ");

                    r.add(i, n.get(i + 1) / m);
                    System.out.println("r[" + (i + 1) + "] = " + r.get(i));
                }

                System.out.println();
                for (int i = 0; sumTau + sumOfT < T; ++i) {
                    tau.add(i, a + (b - a) * r.get(i));
                    if (i == 0) {
                        t.add(0, tau.get(0));
                        sumOfT = t.get(i);
                    } else {
                        t.add(i, t.get(i - 1) + tau.get(i));
                        sumOfT = t.get(i - 1);
                        sumTau = tau.get(i);
                    }
                    if (t.get(i) > T)
                        break;

                    tSpasum.add(i, -Math.log(r.get(i)) / spasmanLambda);
                    System.out.print("tau[" + (i + 1) + "] = " + tau.get(i) + "   ");
                    System.out.print("t[" + (i + 1) + "] = " + t.get(i) + "    ");
                    System.out.println("t_spasum[" + (i + 1) + "] = " + tSpasum.get(i));
                }
            }
            fixvacChap = t.size();
            Random rand = new Random();
            for (int i = 0; i < t.size(); ++i) {
                double sum1 = 0;
                LinkedList<Double> sArray = new LinkedList<>();
                for (int j = 0; j < 12; ++j) {
                    int index = rand.nextInt(16) + 1;
                    sArray.add(r.get(index));
                    sum1 += sArray.get(j);
                }
                sumOfArrays.add(sum1);
            }
            for (int i = 0; i < tau.size(); ++i) {
                System.out.print("s[" + (i + 1) + "] = " + sumOfArrays.get(i) + "     ");
                tSpasarkum.add(i, nyu + sigma * (sumOfArrays.get(i) - 6));

                System.out.println("t spasarkum [" + (i + 1) + "] = " + tSpasarkum.get(i));

                if ((t.get(i) + tSpasarkum.get(i) < T)) {
                    count += 1;
                    saxSpasarkumner.add(tSpasarkum.get(i));
                    azatmanJamank.add(t.get(i) + tSpasarkum.get(i));
                }
            }
            for (int i = 0; i < azatmanJamank.size(); i++) {
                if (i == 0)
                    kapuxi1.add(tSpasarkum.get(i));
                else if (i == 1) {
                    if (t.get(i) < azatmanJamank.get(i - 1)) kapuxi2.add(tSpasarkum.get(i));
                    else kapuxi1.add(tSpasarkum.get(i));
                }
                else if (i == 2) {
                    if (t.get(i) > azatmanJamank.get(i - 2)) kapuxi1.add(tSpasarkum.get(i));
                    else
                        kapuxi2.add(tSpasarkum.get(i));
                }
                else
                    paymanner(i,T,kapuxi1,kapuxi2,t,azatmanJamank,tSpasarkum,tSpasum);

            }
            System.out.println(azatmanJamank);
            System.out.println("spasarkvele " + count + "  isk spasarkman havanakanutyun@ " + count / fixvacChap);
            double jlntoz = ((fixvacChap-count) / fixvacChap);
            merjvacneriHav.add(jlntoz);
        }
        pmGcik = (1 - (saxSpasarkumner.size() / (fixvacChap * nAstxanish)));
        System.out.println("\n\n\narajin kapuxu spasarkvacneri qanak = " + kapuxi1.size() + "     erkrordkapuxu spasarkvacneri qanak = " + kapuxi2.size());
        System.out.println("•  spasarkman havanakanutyun = " + (saxSpasarkumner.size() / (fixvacChap * nAstxanish)));
        System.out.println("•  spasarkman toxunakutyun = " + (saxSpasarkumner.size() / (T * nAstxanish)));
        System.out.println("•  merjman mijin havanakanutyun = " + pmGcik + "\n•  ");
        double sumOfSpasarkvac = 0, sumOfArajin = 0, sumOfErkrord = 0;
        for (Double aSaxSpasarkumner : saxSpasarkumner)
            sumOfSpasarkvac += aSaxSpasarkumner;
        for (Double anArajinKapuxi : kapuxi1)
            sumOfArajin += anArajinKapuxi;
        for (Double anErkrordkapuxi : kapuxi2)
            sumOfErkrord += anErkrordkapuxi;

        for (int i = 0; i < mKapuxi; i++) {
            if (i != 1)
                System.out.println("   " + (i + 1) + " kapuxu zbaxvacutyan havanakanutyun@ = " + sumOfArajin / (T * nAstxanish * (i + 1)));
            else
                System.out.println("   " + (i + 1) + " kapuxu zbaxvacutyan havanakanutyun@ = " + sumOfErkrord / (T * nAstxanish * (i + 1)));
        }
        double arajiniZbaxacutyun = sumOfArajin / (nAstxanish), erkrordizbaxvacutyun = sumOfErkrord / (nAstxanish);
        int kZbaxvac = 2;
        for (int i = 0; i < nAstxanish; i++) {
            kZbaxvac += 2.0 / nAstxanish;
        }
        double sumOfPer = 0;
        for (int i = 0; i < nAstxanish; i++) {
            sumOfPer += Math.pow(merjvacneriHav.get(i) - pmGcik,2);
        }
        sigmaVerjin = Math.sqrt(sumOfPer / (nAstxanish - 1));
        epsilyon = zAlfa * sigmaVerjin / Math.sqrt(nAstxanish);
        System.out.println("     amboxj kapuxu zbaxvacutyan havanakanutyun@ = " + ((sumOfArajin / (T * nAstxanish)) + (sumOfErkrord / (T * nAstxanish * (2)))));
        System.out.println("•  yuraqanchyur kapuxu zbaxvacutyan mijin jamanak " + arajiniZbaxacutyun);
        System.out.println("    yuraqanchyur kapuxu zbaxvacutyan mijin jamanak " + erkrordizbaxvacutyun);
        System.out.println("•  zbaxvac kapuxineri mijin qanak " + kZbaxvac);
        System.out.println("•  parapurti havanakanutyun = " + (T - sumOfSpasarkvac / T ) / nAstxanish);
        System.out.println("•  amboxj hamakargi parapurti havanakanutyun@ = " + (1 - ((sumOfArajin / (T * nAstxanish)) + (sumOfErkrord / (T * nAstxanish * (2))))));
        System.out.println("• hayti spasakman mijin jamamank = " + ((sumOfArajin + sumOfErkrord) / saxSpasarkumner.size()));
        System.out.println("• hamakargum hayti gtnvelu mijin jamanak@ = " + ((sumOfArajin + sumOfErkrord) / saxSpasarkumner.size()));
        System.out.println("• sigma " + sigmaVerjin);
        System.out.println("• epsilyon " + epsilyon);
        System.out.println(saxSpasarkumner.size());
    }
}
