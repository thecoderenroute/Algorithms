/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF wquf;
    private final WeightedQuickUnionUF wquf1;
    private boolean[] open;
    private final int n;
    private int count;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.wquf = new WeightedQuickUnionUF((n * n) + 2);
        this.wquf1 = new WeightedQuickUnionUF((n * n) + 1);
        this.open = new boolean[(n * n) + 2];
        this.open[0] = true;
        this.open[n * n + 1] = true;
        this.count = 0;

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) { // assuming that rows are from 1-n and cols are from 1-n
        if (!this.validPoints(row, col)) {
            throw new IllegalArgumentException();
        }
        int p = this.get1D(row, col);
        if (open[p]) {
            return;
        }
        int r = row - 1;
        open[p] = true;
        this.count++;
        if (r + 1 > 1) {
            if (open[(r - 1) * n + col]) {
                wquf.union(p, (r - 1) * n + col);
                wquf1.union(p, (r - 1) * n + col);
            }
        }
        else {
            wquf.union(p, 0);
            wquf1.union(p, 0);
        }
        if (r + 1 < n) {
            if (open[(r + 1) * n + col]) {
                wquf.union(p, (r + 1) * n + col);
                wquf1.union(p, (r + 1) * n + col);
            }
        }
        else {
            wquf.union(p, n * n + 1);
        }
        if (col > 1) {
            if (open[(r * n + col - 1)]) {
                wquf.union(p, r * n + col - 1);
                wquf1.union(p, r * n + col - 1);
            }
        }
        if (col < n) {
            if (open[r * n + col + 1]) {
                wquf.union(p, r * n + col + 1);
                wquf1.union(p, r * n + col + 1);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (!this.validPoints(row, col)) {
            throw new IllegalArgumentException();
        }
        return this.open[this.get1D(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!this.validPoints(row, col)) {
            throw new IllegalArgumentException();
        }
        if (isOpen(row, col)) {
            return wquf1.find(this.get1D(row, col)) == wquf1.find(0);
        }
        return false;

    }

    public int numberOfOpenSites() {
        return this.count;
    }

    private int get1D(int row, int col) {
        return ((row - 1) * this.n) + col;
    }

    private boolean validPoints(int row, int col) {
        return !(row < 1 || row > n || col < 1 || col > n);
    }

    public boolean percolates() {
        return wquf.find(n * n + 1) == wquf.find(0);
    }

}