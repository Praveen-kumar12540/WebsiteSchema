/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteschema.utils;

import java.util.Collection;
import java.util.List;

/**
 * @author Michael Gilleland
 */
public class EditDistance {
    //****************************
    // Get minimum of three values
    //****************************

    private int Minimum(int a, int b, int c) {
        int mi;

        mi = a;
        if (b < mi) {
            mi = b;
        }
        if (c < mi) {
            mi = c;
        }
        return mi;

    }

    //*****************************
    // Compute Levenshtein distance
    //*****************************
    public int LD(String s, String t) {
        int d[][]; // matrix
        int n; // length of s
        int m; // length of t
        int i; // iterates through s
        int j; // iterates through t
        char s_i; // ith character of s
        char t_j; // jth character of t
        int cost; // cost

        // Step 1
        n = s.length();
        m = t.length();
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];

        // Step 2
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        // Step 3
        for (i = 1; i <= n; i++) {
            s_i = s.charAt(i - 1);

            // Step 4
            for (j = 1; j <= m; j++) {
                t_j = t.charAt(j - 1);

                // Step 5
                if (s_i == t_j) {
                    cost = 0;
                } else {
                    cost = 1;
                }

                // Step 6
                d[i][j] = Minimum(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + cost);

            }

        }

        // Step 7
        return d[n][m];
    }

    public double caculateSimilarity(Collection<String> list) {
        double ret = 0.0;

        int count = 0;
        double sum = 0.0;
        String[] array = list.toArray(new String[0]);
        for (int i = 0; i < array.length - 1; i++) {
            String s1 = array[i];
            for (int j = i + 1; j < array.length; j++) {
                String s2 = array[j];
                sum += caculateSimilarityBetweenStrings(s1, s2);
                count++;
            }
        }

        if (count > 0) {
            ret = sum / (double) count;
        }

        return ret;
    }

    public double caculateSimilarityBetweenStrings(String s1, String s2) {
        double ret = 0.0;

        int distance = LD(s1, s2);

        int len = s1.length() > s2.length() ? s1.length() : s2.length();

        int x = len - distance;

        ret = 2 * x / (double) (s1.length() + s2.length());

        return ret;
    }
}
