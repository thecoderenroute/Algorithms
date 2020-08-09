/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double F = 1.96;
    private final double[] openP;
    private final int trials;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.trials = trials;
        openP = new double[trials];
        Percolation percolation;
        for (int i = 0; i < trials; i++) {

            percolation = new Percolation(n);
            int count;
            while (true) {
                int r = StdRandom.uniform(n) + 1;
                int c = StdRandom.uniform(n) + 1;
                percolation.open(r, c);
                if (percolation.percolates()) {
                    count = percolation.numberOfOpenSites();
                    break;
                }
            }
            openP[i] = (((double) count / (n * n)));
        }
    }


    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(openP);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(openP);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (this.mean() - (F * this.stddev() / Math.sqrt(trials)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (this.mean() + (F * this.stddev() / Math.sqrt(trials)));
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),
                                                   Integer.parseInt(args[1]));

        System.out.println("mean = " + ps.mean());
        System.out.println("stdDev = " + ps.stddev());
        System.out.println(
                "95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");

    }

}
